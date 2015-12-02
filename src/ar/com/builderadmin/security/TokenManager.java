package ar.com.builderadmin.security;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import ar.com.builderadmin.utils.HashUtils;
import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;

@Controller
public class TokenManager {

	/**
	 * Mapa que relaciona el usuario con el token generado para el
	 */
	private static final HashMap<String, Usuario_FrontEnd> usuario_token = new HashMap<String, Usuario_FrontEnd>();
	
	public String crearToken(Usuario_FrontEnd ufe){
		
		ufe.setToken(HashUtils.generateUserToken());
		usuario_token.put(ufe.getToken(), ufe);
		
		return ufe.getToken();
	}


	public Usuario_FrontEnd getUsuarioDeToken(String token) {
		return usuario_token.get(token);
	}
	
}