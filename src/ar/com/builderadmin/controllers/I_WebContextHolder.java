package ar.com.builderadmin.controllers;

import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;

public interface I_WebContextHolder {

	public Usuario_FrontEnd getUsuario();
	public void setUsuario(Usuario_FrontEnd usuario);
	
	public String getMensajes();
	public void setMensajes(String mensajes);
}
