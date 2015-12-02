package ar.com.builderadmin.vo;

import java.util.HashMap;

public class Mail_VO {
	
	private String nombreCandidato;
	private String emailCandidato;
	
	private String nombreEmisor;
	private String emailEmisor;
	
	private String usuarioMail;
	private String contraseniaMail;
	
	private String asunto;
	private String encabezado;
	private String pie;
	
	private HashMap<String, Object> data = new HashMap<>();
	
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getNombreCandidato() {
		return this.nombreCandidato;
	}

	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}

	public String getEmailCandidato() {
		return this.emailCandidato;
	}

	public void setEmailCandidato(String emailCandidato) {
		this.emailCandidato = emailCandidato;
	}

	public String getEmailEmisor() {
		return this.emailEmisor;
	}

	public void setEmailEmisor(String emailEmisor) {
		this.emailEmisor = emailEmisor;
	}

	public String getNombreEmisor() {
		return this.nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getContraseniaMail() {
		return this.contraseniaMail;
	}

	public void setContraseniaMail(String contraseniaMail) {
		this.contraseniaMail = contraseniaMail;
	}

	public String getEncabezado() {
		return this.encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public String getPie() {
		return this.pie;
	}

	public void setPie(String pie) {
		this.pie = pie;
	}

	public String getUsuarioMail() {
		return this.usuarioMail;
	}

	public void setUsuarioMail(String usuarioMail) {
		this.usuarioMail = usuarioMail;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	public String getAsunto() {
		return asunto;
	}
	
}
