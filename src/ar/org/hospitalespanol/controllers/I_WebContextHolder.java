package ar.org.hospitalespanol.controllers;

import ar.org.hospitalespanol.vo.core.usuarios.Usuario_FrontEnd;

public interface I_WebContextHolder {

	public Usuario_FrontEnd getUsuario();
	public void setUsuario(Usuario_FrontEnd usuario);
	
	public String getMensajes();
	public void setMensajes(String mensajes);
}
