package ar.org.hospitalespanol.vo.core.usuarios;

import java.util.Date;

import ar.org.hospitalespanol.model.core.usuarios.Usuario;

public class InfoIngresoUsuario {

	private static final int INTENTOS_MAXIMOS = 5;

	private Boolean loginOK = false;
	private Integer intentosRestantes = 5;
	private Boolean usuarioActivo = true;
	private Date fechaUltimoLogueo;
	private Boolean usuarioTieneMail = false;
	private String email;
	private String mensaje;

	public InfoIngresoUsuario() {
	}

	public InfoIngresoUsuario(Usuario u) {
	}

	public Boolean getLoginOK() {
		return loginOK;
	}

	public void setLoginOK(Boolean loginOK) {
		this.loginOK = loginOK;
	}

	public Integer getIntentosRestantes() {
		return intentosRestantes;
	}

	public void setIntentosRestantes(Integer intentosRestantes) {
		this.intentosRestantes = intentosRestantes;
	}

	public Boolean getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(Boolean usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	public Date getFechaUltimoLogueo() {
		return fechaUltimoLogueo;
	}

	public void setFechaUltimoLogueo(Date fechaUltimoLogueo) {
		this.fechaUltimoLogueo = fechaUltimoLogueo;
	}

	public Boolean getUsuarioTieneMail() {
		return usuarioTieneMail;
	}

	public void setUsuarioTieneMail(Boolean usuarioTieneMail) {
		this.usuarioTieneMail = usuarioTieneMail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsuario(Usuario u) {
		if (u != null) {

			this.setLoginOK(true);

			this.setFechaUltimoLogueo(new Date());
			this.setIntentosRestantes(INTENTOS_MAXIMOS
					- u.getCantidadIntentos());
			this.setUsuarioActivo(this.getIntentosRestantes() > 0);
			this.setUsuarioTieneMail(u.getEmail() != null);

		} else {
			this.setLoginOK(false);
		}
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

}
