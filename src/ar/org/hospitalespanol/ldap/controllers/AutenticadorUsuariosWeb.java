package ar.org.hospitalespanol.ldap.controllers;

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

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.AD_Service;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_Usuario;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_Rol;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_GetRolesDeUsuario;
import ar.org.hospitalespanol.ldap.vo.UsuarioLDAP_VO;
import ar.org.hospitalespanol.model.core.POST_Credenciales;
import ar.org.hospitalespanol.security.TokenManager;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_FrontEnd;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/autenticadorWeb", produces = "text/json;charset=utf-8")
public class AutenticadorUsuariosWeb {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	@Autowired
	private DAO_Rol daoRol;
	
	protected static final Logger logger = LoggerFactory
			.getLogger(AutenticadorUsuariosWeb.class);

	
	
//	import static org.springframework.ldap.query.LdapQueryBuilder.query;
	public String autenticar(String usuario) {
		String resultado=null;
		try {
			DAO_Utils.info(logger, "AutenticadorLDAP", "autenticar", "autenticador", "Intentando autenticar: "+usuario);

			//Recupero el usuario de LDAP
//			UsuarioLDAP user = new UsuarioLDAP(); user.setUsuario("sebastianga"); user.setNombreCompleto("Sebastian Garcia");
			
   		UsuarioLDAP_VO userAD = AD_Service.findAccountByAccountName(usuario);

//          Produccion			
//			UsuarioLDAP user = ldapTemplate.findOne(query().where("sAMAccountName").is(usuario), UsuarioLDAP.class);
//          Solo en desa
//		UsuarioLDAP user = new UsuarioLDAP(); user.setUsuario("mariaco"); user.setNombreCompleto("Danila Moya");
	//		UsuarioLDAP user = new UsuarioLDAP(); user.setUsuario("carolinall"); user.setNombreCompleto("Carolina Llarul");
			//Le inyecto el token de validacion y lo devuelvo en la respuesta
			userAD.setRoles( getRoles(userAD.getUsuario(), userAD.getUsuario()));
			
			//Obtengo los roles del usuario y los guardo en caso de tener alguno
			Usuario_FrontEnd ufe = new Usuario_FrontEnd(userAD);
			tokenManager.crearToken(ufe);
			
			resultado= new Gson().toJson(new R_Autenticacion(true, ufe));
			
		} catch (org.springframework.ldap.CommunicationException conExc) {
			
			logger.error("[AutenticadorLDAP][autenticar] Connection TimeOut", conExc);
			
			resultado= "[AutenticadorLDAP][autenticar] Login failed, Conexion no establecida con el LDAP.";
			
		} catch (Exception e) {
			// Context creation failed - authentication did not succeed
			logger.error("[AutenticadorLDAP][autenticar] Login failed", e);

			//Error al autenticar, devuelvo el error
			resultado= "[AutenticadorLDAP][autenticar] Login failed";
		}
		return resultado;
	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestBody String str_cred ) {
		
		POST_Credenciales credenciales = new Gson().fromJson(str_cred,
				POST_Credenciales.class);
		
		R_Autenticacion resul=null;
		String resultado=null;
		
			DAO_Usuario daous= new DAO_Usuario();
			resul=daous.autenticarUsuario(credenciales.getUsuario(), credenciales.getPassword());
			if (resul.getOk()){
				resul.setUser(new Usuario_FrontEnd(credenciales.getUsuario()));
				Usuario_FrontEnd ufe = resul.getUser();
				//ufe.setNombre(nombre);
				tokenManager.crearToken(ufe);
				resul.setUser(ufe);
				resultado=new Gson().toJson(new R_Autenticacion(true, ufe));
			}else{
				resultado="Error de autenticacion";
			}
			
		return resultado;
	}
	
	/**
	 * Devuelve los roles del usuario 
	 * 
	 * @param ur
	 * @param usuarioPide
	 * @return
	 */
	private List<Rol_VO> getRoles(String ur, String usuarioPide){
		
		FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDaoRol(), ur, usuarioPide);
		JSON_Respuesta json = fx.ejecutar(admin_Alertas);
		
		if (json.getOk() && json.getPaginador().getElementos().size()>0){
			return json.getPaginador().getElementos();
		}else{
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
	 * @param admin_Alertas the admin_Alertas to set
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
	 * @param daoRol the daoRol to set
	 */
	public void setDaoRol(DAO_Rol daoRol) {
		this.daoRol = daoRol;
	}

	
	
}