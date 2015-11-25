package ar.org.hospitalespanol.ws.core.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.core.DAO_Estadisticas;
import ar.org.hospitalespanol.fx.core.usuarios.FX_ListarUsuarioLDAP;
import ar.org.hospitalespanol.ldap.controllers.AutenticadorLDAP;
import ar.org.hospitalespanol.ldap.controllers.AutenticadorUsuariosWeb;
import ar.org.hospitalespanol.ldap.controllers.R_Autenticacion;
import ar.org.hospitalespanol.ldap.dao.DAO_UsuarioLDAP;
import ar.org.hospitalespanol.ldap.vo.UsuarioLDAP_VO;
import ar.org.hospitalespanol.security.TokenManager;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_FrontEnd;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/login", produces="application/json;charset=utf-8")
public class WS_Login extends WS_Abstracto {
	
	/**
	 * 	Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value="/ping", produces="text/plain;charset=utf-8",  method= RequestMethod.GET)
	public  String ping() {
		return "WS_Login -> ping";
	}
	
	@Autowired
	private Admin_Alertas admin_Alertas;
	
	
	/****************************************************************************************/
	/*                               Autenticacion  										*/
	/****************************************************************************************/
	@Autowired
	private AutenticadorLDAP autenticadorLDAP;
	
	@Autowired
	private AutenticadorUsuariosWeb autenticadorWeb;
	
	@Autowired
	private DAO_Estadisticas daoEstadisticas;
	
	@RequestMapping(value="/doLogin", 
	produces="application/json;charset=utf-8",  
	method= RequestMethod.POST)
	public String doLogin(@RequestBody String jsonPrivado)
	{
		
		System.out.println("WS_Login -> doLogin()");
		
		String resul= getAutenticadorLDAP().authenticate(jsonPrivado);
		if(resul==null){
			/*Autentica con usuarios de Alfresco*/
			resul= getAutenticadorWeb().authenticate(jsonPrivado);
		}
		return resul;
	}
	
	@RequestMapping(value="/doSecureLogin", 
	produces="application/json;charset=utf-8",  
	method= RequestMethod.POST)
	public String doSecureLogin(@RequestBody String strUsuario)
	{
		System.out.println("WS_Login -> doSecureLogin(" + strUsuario + ")");
		
		String resul=getAutenticadorLDAP().autenticar(strUsuario);
		return resul;
		
	}
	
	@Autowired
	private TokenManager tokenManager;
	
	@RequestMapping(value="/seguro/getLoggedUser", 
	produces="application/json;charset=utf-8",  
	method= RequestMethod.POST)
	public String getLoggedUser(@RequestBody String token)
	{
		System.out.println("WS_Login -> getLoggedUser(" + token + ")");
		
		Usuario_FrontEnd userVo = this.getTokenManager().getUsuarioDeToken(token);
		
		if (userVo != null){
			
			UsuarioLDAP_VO usr = getDao_UsuarioLDAP().getUsuarioLDAP(userVo.getUsuario());
			
			Usuario_FrontEnd ufe= new Usuario_FrontEnd(usr);
			ufe.setToken(token);
			
			return new Gson().toJson(new R_Autenticacion(true, ufe));
			
		}else{
		
			return new Gson().toJson(new R_Autenticacion(false, "Error de autenticacion"));
		}
	}

	/****************************************************************************************/
	/*                               Usuarios        										*/
	/****************************************************************************************/
	@Autowired
	private DAO_UsuarioLDAP dao_UsuarioLDAP;
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_RolHE -> listAll()");
		
		FX_ListarUsuarioLDAP fx = new FX_ListarUsuarioLDAP(getDao_UsuarioLDAP(), null, getUsuarioAccion());
		
		JSON_Respuesta respuesta = fx.ejecutar(getAdmin_Alertas());
		
		return new Gson().toJson(respuesta);
	}
	
	/**
	 * @return the autenticadorLDAP
	 * 
	 */
	public AutenticadorLDAP getAutenticadorLDAP() {
		return autenticadorLDAP;
	}

	/**
	 * @param autenticadorLDAP the autenticadorLDAP to set
	 */
	public void setAutenticadorLDAP(AutenticadorLDAP autenticadorLDAP) {
		this.autenticadorLDAP = autenticadorLDAP;
	}

	/**
	 * @return the tokenManager
	 */
	public TokenManager getTokenManager() {
		return tokenManager;
	}

	/**
	 * @param tokenManager the tokenManager to set
	 */
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

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
	 * @return the dao_UsuarioLDAP
	 */
	public DAO_UsuarioLDAP getDao_UsuarioLDAP() {
		return dao_UsuarioLDAP;
	}

	/**
	 * @param dao_UsuarioLDAP the dao_UsuarioLDAP to set
	 */
	public void setDao_UsuarioLDAP(DAO_UsuarioLDAP dao_UsuarioLDAP) {
		this.dao_UsuarioLDAP = dao_UsuarioLDAP;
	}

	public AutenticadorUsuariosWeb getAutenticadorWeb() {
		return autenticadorWeb;
	}

	public void setAutenticadorWeb(AutenticadorUsuariosWeb autenticadorWeb) {
		this.autenticadorWeb = autenticadorWeb;
	}
	
	
}