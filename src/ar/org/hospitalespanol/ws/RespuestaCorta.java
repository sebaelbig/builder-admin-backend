package ar.org.hospitalespanol.ws;


public class RespuestaCorta{
	/**
	 * 
	 */
	private Boolean ok =true;
	private String mensaje;
	
	public RespuestaCorta(){
		
	}
	
	public RespuestaCorta(Boolean ok, String msg){
		this.setOk(ok);
		this.setMensaje(msg);
	}
	
	/**
	 * @return the ok
	 */
	public Boolean getOk() {
		return ok;
	}

	/**
	 * @param ok the ok to set
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
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}