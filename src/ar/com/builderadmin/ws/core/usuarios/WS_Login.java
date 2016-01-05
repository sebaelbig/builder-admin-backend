package ar.com.builderadmin.ws.core.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.core.DAO_Estadisticas;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.fx.core.usuarios.FX_ListarUsuario;
import ar.com.builderadmin.security.TokenManager;
import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/login", produces = "application/json;charset=utf-8")
public class WS_Login extends WS_Abstracto {

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_Login -> ping";
	}

	@Autowired
	private Admin_Alertas admin_Alertas;

	/****************************************************************************************/
	/* Autenticacion */
	/****************************************************************************************/
	// @Autowired
	// private Autenticador autenticador;

	// @Autowired
	// private AutenticadorUsuariosWeb autenticadorWeb;

	@Autowired
	private DAO_Estadisticas daoEstadisticas;

	@RequestMapping(value = "/doLogin", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String doLogin(@RequestBody String jsonPrivado) {
		System.out.println("WS_Login -> doLogin()");
		
		Gson g = new Gson();
		Usuario_VO usr = g.fromJson(jsonPrivado, Usuario_VO.class);
		
		usr = getDao_Usuario().autenticarUsuario(usr.getUsuario(), usr.getContrasena());
			
		if (usr!=null){
			
			Usuario_FrontEnd ufe = new Usuario_FrontEnd(usr, usr.getRoles());
			
			return new Gson().toJson(new R_Autenticacion( ufe ));

		} else {
		
			return new Gson().toJson(new R_Autenticacion( "Error de autenticacion"));
		}
	}
	
	@Autowired
	private TokenManager tokenManager;

	@RequestMapping(value = "/seguro/getLoggedUser", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getLoggedUser(@RequestBody String token) {
		System.out.println("WS_Login -> getLoggedUser(" + token + ")");

		Usuario_FrontEnd userVo = this.getTokenManager().getUsuarioDeToken(token);

		if (userVo != null) {

//			Usuario_VO usr = getDao_Usuario().aute(userVo.getUsuario());

//			Usuario_FrontEnd ufe = new Usuario_FrontEnd(usr);
			userVo.setToken(token);

			return new Gson().toJson(new R_Autenticacion( userVo));

		} else {

			return new Gson().toJson(new R_Autenticacion( "Error de autenticacion"));
		}
	}

	/****************************************************************************************/
	/* Usuarios */
	/****************************************************************************************/
	@Autowired
	private DAO_Usuario dao_Usuario;

	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_RolHE -> listAll()");

		FX_ListarUsuario fx = new FX_ListarUsuario(getDao_Usuario(), null, getUsuarioAccion());

		JSON_Respuesta respuesta = fx.ejecutar(getAdmin_Alertas());

		return new Gson().toJson(respuesta);
	}

	/**
	 * @return the tokenManager
	 */
	public TokenManager getTokenManager() {
		return tokenManager;
	}

	/**
	 * @param tokenManager
	 *            the tokenManager to set
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
	 * @param admin_Alertas
	 *            the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
	}

	public DAO_Estadisticas getDaoEstadisticas() {
		return daoEstadisticas;
	}

	public void setDaoEstadisticas(DAO_Estadisticas daoEstadisticas) {
		this.daoEstadisticas = daoEstadisticas;
	}

	public DAO_Usuario getDao_Usuario() {
		return dao_Usuario;
	}

	public void setDao_Usuario(DAO_Usuario dao_Usuario) {
		this.dao_Usuario = dao_Usuario;
	}

}