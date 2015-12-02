package ar.com.builderadmin.controllers;

import org.springframework.stereotype.Component;

@Component
public class JSON_Respuesta {
	
	private Boolean ok = Boolean.valueOf(true);
	private String mensaje;
	private JSON_Paginador paginador;

	/**
	 * Construye una respuesta por default correcta 
	 * @param b
	 * @param string
	 */
	public JSON_Respuesta() {}
	
	public JSON_Respuesta(JSON_Paginador pag) {
		this.setOk(true);
		this.setPaginador(pag);
	}
	
	/**
	 * Construye una respuesta por defaul erronea recibiendo el mensaje a mostrar
	 * @param string
	 */
	public JSON_Respuesta(String string) {
		this.setOk(false);
		this.setMensaje(string);
	}

	public Boolean getOk() {
		return this.ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public JSON_Paginador getPaginador() {
		return this.paginador;
	}

	public void setPaginador(JSON_Paginador paginador) {
		this.paginador = paginador;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}