package ar.com.builderadmin.ws.core.usuarios;

import ar.com.builderadmin.vo.core.usuarios.Usuario_FrontEnd;

public class R_Autenticacion {
	
	private Boolean ok;
	private Usuario_FrontEnd data;
	private String mensaje;
	
	public R_Autenticacion( String mensaje) {
		this.mensaje = mensaje;
		this.ok = false;
	}

	public R_Autenticacion( Usuario_FrontEnd data) {
		this.ok = true;
		this.data = data;
	}
	
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
	public Usuario_FrontEnd getData() {
		return data;
	}
	public void setData(Usuario_FrontEnd data) {
		this.data = data;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
