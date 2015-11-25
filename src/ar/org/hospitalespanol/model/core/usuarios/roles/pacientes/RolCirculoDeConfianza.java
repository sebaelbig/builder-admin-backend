package ar.org.hospitalespanol.model.core.usuarios.roles.pacientes;

import java.io.Serializable;

public class RolCirculoDeConfianza implements Serializable{
	  /** @generated */
    private static final long serialVersionUID = 1L;
    
	private Long rol;
	
	private Long sucursal;
	
	/**
	 * Apellido del usuario
	 */
	private String apellido;
	
	/**
	 * Usuario del rol
	 */
	private String nombres;
	
	private String nroDocumento;
    /** Metodo Constructor sin Argumentos */
    public RolCirculoDeConfianza() {
    }
    
	/** Se implementa el metodo hashCode() de esta forma */
    public int hashCode() {
        return  rol.hashCode();
    }
    
    /** Se implementa el metodo equals() de esta forma */
    public boolean equals (Object obj){
        if (obj == this) return true;
        if (!(obj instanceof RolCirculoDeConfianza)) return false;
        if (obj == null) return false;
        RolCirculoDeConfianzaPK per = (RolCirculoDeConfianzaPK) obj;
        return per.getRol().equals(rol) && per.getSucursal().equals(sucursal);
    }
    
    public Long getRol() {
		return rol;
	}
	public void setRol(Long rol) {
		this.rol = rol;
	}
	public Long getSucursal() {
		return sucursal;
	}
	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
}
