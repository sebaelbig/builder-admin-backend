package ar.org.hospitalespanol.ldap.controllers;

import ar.org.hospitalespanol.vo.core.usuarios.Usuario_FrontEnd;

public class R_Autenticacion {
	public Boolean ok;
	public String code;
	public String mensaje;
	public Usuario_FrontEnd data;
	public Usuario_FrontEnd user;

	public R_Autenticacion(){
		
	}
	
	public R_Autenticacion(boolean b, Usuario_FrontEnd ufe) {
		this.setOk(b);
		this.setUser(ufe);
		this.setCode("200");
	}

	public R_Autenticacion(boolean b, String msg) {
		this.setOk(b);
		this.setMensaje(msg);
		this.setCode("403");
	}

	/**
	 * @return the ok
	 */
	public Boolean getOk() {
		return ok;
	}

	/**
	 * @param ok
	 *            the ok to set
	 */
	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the user
	 */
	public Usuario_FrontEnd getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Usuario_FrontEnd user) {
		this.user = user;
		this.data = user;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}