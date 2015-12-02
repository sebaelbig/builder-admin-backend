package ar.com.builderadmin.ldap.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.AD_Service;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.DAO_Estadisticas;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_GetRolesDeUsuario;
import ar.com.builderadmin.ldap.vo.UsuarioLDAP_VO;
import ar.com.builderadmin.model.core.POST_Credenciales;
import ar.com.builderadmin.security.TokenManager;
import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

@RestController
@RequestMapping(value = "/autenticador", produces = "text/json;charset=utf-8")
public class AutenticadorLDAP {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private Admin_Alertas admin_Alertas;

	@Autowired
	private DAO_Rol daoRol;
	
	@Autowired
	private DAO_Estadisticas daoEstadisticas;

	protected static final Logger logger = LoggerFactory
			.getLogger(AutenticadorLDAP.class);

	// import static org.springframework.ldap.query.LdapQueryBuilder.query;
	public String autenticar(String usuario) {
		
		String resultado = null;
		try {
			DAO_Utils.info(logger, "AutenticadorLDAP", "autenticar", "ldap",
					"Intentando autenticar: " + usuario);


			UsuarioLDAP_VO userAD = AD_Service
					.findAccountByAccountName(usuario);
			userAD.setRoles(getRoles(userAD.getUsuario(), userAD.getUsuario()));

			// Obtengo los roles del usuario y los guardo en caso de tener
			// alguno
			Usuario_FrontEnd ufe = new Usuario_FrontEnd(userAD);
			tokenManager.crearToken(ufe);

			resultado = new Gson().toJson(new R_Autenticacion(true, ufe));

		} catch (org.springframework.ldap.CommunicationException conExc) {

			logger.error("[AutenticadorLDAP][autenticar] Connection TimeOut",
					conExc);

			resultado = "[AutenticadorLDAP][autenticar] Login failed, Conexion no establecida con el LDAP.";

		} catch (Exception e) {
			// Context creation failed - authentication did not succeed
			logger.error("[AutenticadorLDAP][autenticar] Login failed", e);

			// Error al autenticar, devuelvo el error
			resultado = "[AutenticadorLDAP][autenticar] Login failed";
		}
		
		return resultado;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestBody String str_cred) {

		POST_Credenciales credenciales = new Gson().fromJson(str_cred,
				POST_Credenciales.class);
		String resul = null;
		try {

			DAO_Utils.info(logger, "AutenticadorLDAP", "authenticate",
					credenciales.getUsuario(), "Intentando autenticar");

			// Intento autenticar al usuario con las credenciales
			Boolean correcto = AD_Service.autenticarConAD(
					credenciales.getUsuario(), credenciales.getPassword());

			if (!correcto) {
				correcto = AD_Service.autenticarConADSecundario(
						credenciales.getUsuario(), credenciales.getPassword());
			}
			
			if (correcto) {

				DAO_Utils.info(
						logger,
						"AutenticadorLDAP",
						"authenticate",
						credenciales.getUsuario(),
						"Se autenticó correctamente al usuario:"
								+ credenciales.getUsuario());

				// Recupero el usuario de LDAP
				UsuarioLDAP_VO user = AD_Service
						.findAccountByAccountName(credenciales.getUsuario());

				if (user == null)

					resul = new Gson().toJson(new R_Autenticacion(false,
							"Error de autenticación"));
				// Le inyecto el token de validacion y lo devuelvo en la
				// respuesta
				user.setRoles(getRoles(user.getUsuario(), user.getUsuario()));

				// Obtengo los roles del usuario y los guardo en caso de tener
				// alguno
				Usuario_FrontEnd ufe = new Usuario_FrontEnd(user);
				tokenManager.crearToken(ufe);

				resul = new Gson().toJson(new R_Autenticacion(true, ufe));
				
				//Persisto la estadistica
				
				getDaoEstadisticas().guardarNueva(credenciales.getUsuario(), "Se logueó el usuario: "+credenciales.getUsuario(), "FX_DoLogin");
				
			} else {
				// resul= new Gson().toJson(new R_Autenticacion(false,
				// "Error de autenticación"));
				DAO_Utils.error(
						logger,
						"AutenticadorLDAP",
						"authenticate",
						credenciales.getUsuario(),
						"No se pudo autenticar el usaurio, ocurrió una excepción al querer validar contra el AD.");
			}

		} catch (org.springframework.ldap.CommunicationException conExc) {

			DAO_Utils.error(
					logger,
					"AutenticadorLDAP",
					"authenticate",
					credenciales.getUsuario(),
					"Connection TimeOut.");


		} catch (Exception e) {

			// Context creation failed - authentication did not succeed
			DAO_Utils.error(
					logger,
					"AutenticadorLDAP",
					"authenticate",
					credenciales.getUsuario(),
					"Login failed.");
			
			e.printStackTrace();

		}
		
		return resul;
	}

	/**
	 * Devuelve los roles del usuario
	 * 
	 * @param ur
	 * @param usuarioPide
	 * @return
	 */
	private List<Rol_VO> getRoles(String ur, String usuarioPide) {

		FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDaoRol(), ur,
				usuarioPide);
		JSON_Respuesta json = fx.ejecutar(admin_Alertas);

		if (json.getOk() && json.getPaginador().getElementos().size() > 0) {
			return json.getPaginador().getElementos();
		} else {
			return new ArrayList<>();
		}
	}

	/*******************************************************************************/

	/**
	 * @return the admin_Alertas
	 */
	public Admin_Alertas getAdmin_Alertas() {
		return admin_Alertas;
	}

	/**
	 * @param admin_Alertas
	 *            the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
	}

	/**
	 * @return the daoRol
	 */
	public DAO_Rol getDaoRol() {
		return daoRol;
	}

	/**
	 * @param daoRol
	 *            the daoRol to set
	 */
	public void setDaoRol(DAO_Rol daoRol) {
		this.daoRol = daoRol;
	}

	public DAO_Estadisticas getDaoEstadisticas() {
		return daoEstadisticas;
	}

	public void setDaoEstadisticas(DAO_Estadisticas daoEstadisticas) {
		this.daoEstadisticas = daoEstadisticas;
	}
	
	

}