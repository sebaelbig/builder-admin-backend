package ar.com.builderadmin.security.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;

/**
 * Holds web context specific objects 
 * 
 * @author segarcia
 *
 */
public class WebContextHolderInterceptor extends HandlerInterceptorAdapter  {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(WebContextHolderInterceptor.class);

	private Usuario_FrontEnd usuario;

	private String mensajes;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
//		this.logger.debug("calling WebContextHolderInterceptor.preHandle()");

		return true;
	}
	
	
	public void setUsuario(Usuario_FrontEnd userVo) {
		this.usuario = userVo;
	}

	/**
	 * @return the usuario
	 */
	public Usuario_FrontEnd getUsuario() {
		return usuario;
	}

	/**
	 * @return the mensajes
	 */
	public String getMensajes() {
		return mensajes;
	}

	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}
}