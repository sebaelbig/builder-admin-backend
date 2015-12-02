package ar.com.builderadmin.security.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ar.com.builderadmin.controllers.I_WebContextHolder;
import ar.com.builderadmin.controllers.WebContextHolder;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.security.TokenManager;
import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;

/**
 * Intercepts all requests and cheks the token existence (if necesary)
 * 
 * @author fgonzalez
 * 
 */
public class TokenAuthenticationInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(TokenAuthenticationInterceptor.class);

	/**
	 * Web Context Holder.
	 */
	@Autowired
	private WebContextHolder webContextHolder;
	
	@Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS) 
	public I_WebContextHolder webContextHolder() {
		if (webContextHolder!=null)
			return webContextHolder;
		else
			return new WebContextHolder();
	}
	
	@Autowired
	private TokenManager tokenManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		this.logger.debug("[TokenAuthenticationInterceptor][preHandle] Calling TokenAuthenticationInterceptor.preHandle()");

		boolean tieneAutorizacion = request.getHeader("Authorization") != null && !request.getHeader("Authorization").trim().equals("");
		boolean requiereAutorizacion = request.getRequestURI().contains("seguro");

		//Vemos si el header trae el token
		if (requiereAutorizacion && !tieneAutorizacion) {

			this.logger.error("No tiene autorizaci�n para realizar esta petici�n"
					+ "ip {}, to {}", request.getRemoteAddr(), request.getRequestURI());

			//NO lo trae, acceso prohibido (CODE 43)
			response.sendError(403);
			
			this.webContextHolder.setMensajes("No tiene autorizaci�n para realizar esta petici�n.");
			
			return false;
			
		} else if(requiereAutorizacion && tieneAutorizacion) {
			
			
			String token = request.getHeader("Authorization");

			Usuario_FrontEnd userVo = this.getTokenManager().getUsuarioDeToken(token);

			if (userVo != null) {
				//Existe el usuario con ese token
				
				this.logger.debug("Usuario recuperado correctamente, tiene permiso.");
				DAO_Utils.info(this.logger, "TokenAuthenticationInterceptor", "preHandle", userVo.getUsuario(), " Usuario recuperado correctamente, tiene permiso");
				
				this.webContextHolder.setMensajes("Usuario recuperado correctamente, tiene permiso.");
				this.webContextHolder.setUsuario(userVo);
				
				return true;
		
			} else {
				//NO existe el usuario con ese token
				
				DAO_Utils.error(this.logger, "TokenAuthenticationInterceptor", "preHandle", "-usuario no encontrado-", "ERROR!! Token inválido - Accion no permitida, no se encontr� el usuario con el token suministrado.");

				//NO lo trae, acceso prohibido (CODE 43)
				response.sendError(403);

				this.webContextHolder.setMensajes("No tiene autorizaci�n para realizar esta petici�n.(Token invalido)");
				
				return false;
			}
		}else{
			//No requiere autorizacion, acceso publico
			this.logger.info("No requiere autorizacion");
			
			return true;
		}
	}

	/**
	 * @return the webContextHolder
	 */
	public WebContextHolder getWebContextHolder() {
		return this.webContextHolder;
	}

	/**
	 * @param webContextHolder
	 *            the webContextHolder to set
	 */
	public void setWebContextHolder(WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
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

	
}
