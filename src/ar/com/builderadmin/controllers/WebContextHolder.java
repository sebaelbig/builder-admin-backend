package ar.com.builderadmin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.builderadmin.security.interceptors.WebContextHolderInterceptor;
import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;

/**
 * Holds web context specific objects 
 * 
 * @author segarcia
 *
 */
@Component
public class WebContextHolder implements I_WebContextHolder{
	
	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(WebContextHolderInterceptor.class);

	private Usuario_FrontEnd usuario;

	private String mensajes;
	
	/**
	 * Empty Constructor.
	 */
	public WebContextHolder() {
		super();
	}

	/**
	 * @return the usuario
	 */
	@Override
	public Usuario_FrontEnd getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	@Override
	public void setUsuario(Usuario_FrontEnd usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the mensajes
	 */
	@Override
	public String getMensajes() {
		return mensajes;
	}

	/**
	 * @param mensajes the mensajes to set
	 */
	@Override
	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}
	
}
