//package ar.org.hospitalespanol.mock.ldap.controllers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import ar.org.hospitalespanol.controllers.Admin_Alertas;
//import ar.org.hospitalespanol.controllers.JSON_Respuesta;
//import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE;
//import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_GetRolesDeUsuarioHE;
//import ar.org.hospitalespanol.ldap.controllers.AutenticadorLDAP;
//import ar.org.hospitalespanol.ldap.controllers.R_Autenticacion;
//import ar.org.hospitalespanol.ldap.modelo.RolDeUsuarioHE;
//import ar.org.hospitalespanol.ldap.modelo.UsuarioLDAP;
//import ar.org.hospitalespanol.ldap.vo.UsuarioLDAP_VO;
//import ar.org.hospitalespanol.model.core.POST_Credenciales;
//import ar.org.hospitalespanol.security.TokenManager;
//import ar.org.hospitalespanol.vo.core.usuarios.Usuario_FrontEnd;
//
//import com.google.gson.Gson;
//
//@RestController
//@RequestMapping(value = "/autenticador", produces = "text/json;charset=utf-8")
//public class MAutenticadorLDAP {
//
//	@Autowired
//	private TokenManager tokenManager;
//
//	@Autowired
//	private Admin_Alertas admin_Alertas;
//	
//	protected final Logger logger = LoggerFactory
//			.getLogger(AutenticadorLDAP.class);
//
//	public String autenticar(String usuario) {
//		
//		try {
//			
//			System.out.println("[AutenticadorLDAP][autenticar] Intentando autenticar");
//			logger.info("[AutenticadorLDAP][autenticar] Intentando autenticar");
//
//			//Recupero el usuario de LDAP
//			UsuarioLDAP user = new UsuarioLDAP();
//			user.setUsuario("sebastianga");
//			user.setNombreCompleto("Sebastian A. Garcia");
//			
//			//Le inyecto el token de validacion y lo devuelvo en la respuesta
//			UsuarioLDAP_VO resul = new UsuarioLDAP_VO(user, getRoles(user.getUsuario(), user.getUsuario()));
//			
//			//Obtengo los roles del usuario y los guardo en caso de tener alguno
//			Usuario_FrontEnd ufe = new Usuario_FrontEnd(resul);
//			tokenManager.crearToken(ufe);
//			
//			return new Gson().toJson(new R_Autenticacion(true, ufe));
//
//		} catch (org.springframework.ldap.CommunicationException conExc) {
//			
//			logger.error("[AutenticadorLDAP][autenticar] Connection TimeOut", conExc);
//			
//			return "[AutenticadorLDAP][autenticar] Login failed, Conexion no establecida con el LDAP.";
//			
//			
//		} catch (Exception e) {
//			// Context creation failed - authentication did not succeed
//			logger.error("[AutenticadorLDAP][autenticar] Login failed", e);
//
//			//e.printStackTrace();
//			
//			//Error al autenticar, devuelvo el error
//			return "[AutenticadorLDAP][autenticar] Login failed";
//		}
//	}
//	
//	
//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//	public String authenticate(@RequestBody String str_cred) {
//
//		POST_Credenciales credenciales = new Gson().fromJson(str_cred,
//				POST_Credenciales.class);
//		
//		try {
//			
//			System.out.println("[AutenticadorLDAP][authenticate] Intentando autenticar");
//			logger.info("[AutenticadorLDAP][authenticate] Intentando autenticar");
//
//			//Intento autenticar al usuario con las credenciales
//			System.out.println("[AutenticadorLDAP][authenticate] Se autenticó correctamente.");
//			logger.info("[AutenticadorLDAP][authenticate] Se autenticó correctamente al usuario:"+credenciales.getUsuario());
//			
//			//Recupero el usuario de LDAP
//			UsuarioLDAP user = new UsuarioLDAP();user.setUsuario("sebastianga");user.setNombreCompleto("Sebastian A. Garcia");
//			
//			//Le inyecto el token de validacion y lo devuelvo en la respuesta
//			UsuarioLDAP_VO resul = new UsuarioLDAP_VO(user, getRoles(user.getUsuario(), user.getUsuario()));
//			
//			//Obtengo los roles del usuario y los guardo en caso de tener alguno
//			Usuario_FrontEnd ufe = new Usuario_FrontEnd(resul);
//			tokenManager.crearToken(ufe);
//			
//			return new Gson().toJson(new R_Autenticacion(true, ufe));
//			
//		} catch (org.springframework.ldap.CommunicationException conExc) {
//			
//			logger.error("[AutenticadorLDAP][authenticate] Connection TimeOut", conExc);
//			
//			return new Gson().toJson(new R_Autenticacion(false, "ERROR, Conexión no establecida con el LDAP"));
//			
//		} catch (Exception e) {
//
//			// Context creation failed - authentication did not succeed
//			logger.error("[AutenticadorLDAP][authenticate] Login failed", e);
//
//			e.printStackTrace();
//			
//			//Error al autenticar, devuelvo el error
//			return new Gson().toJson(new R_Autenticacion(false, "Error de autenticacion"));
//
//		}
//	}
//	
//	/**
//	 * Devuelve los roles del usuario 
//	 * 
//	 * @param ur
//	 * @param usuarioPide
//	 * @return
//	 */
//	private List<RolDeUsuarioHE> getRoles(String ur, String usuarioPide){
//		
//		FX_GetRolesDeUsuarioHE fx = new FX_GetRolesDeUsuarioHE(new DAO_RolHE(), ur, usuarioPide);
//		JSON_Respuesta json = fx.ejecutar(admin_Alertas);
//		
//		if (json.getOk() && json.getPaginador().getElementos().size()>0){
//			return json.getPaginador().getElementos();
//		}else{
//			return new ArrayList<>();
//		}
//	}
//	
//	/*******************************************************************************/
//
//	/**
//	 * @return the admin_Alertas
//	 */
//	public Admin_Alertas getAdmin_Alertas() {
//		return admin_Alertas;
//	}
//
//
//	/**
//	 * @param admin_Alertas the admin_Alertas to set
//	 */
//	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
//		this.admin_Alertas = admin_Alertas;
//	}
//
//	
//}