package ar.org.hospitalespanol.model.core.usuarios.roles.pacientes;

import java.io.Serializable;

public class RolCirculoDeConfianzaPK implements Serializable{
	  /** @generated */
    private static final long serialVersionUID = 1L;
    
    /** Metodo Constructor sin Argumentos */
    public RolCirculoDeConfianzaPK() {
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


	private Long rol;
	private Long sucursal;
	
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
        return per.rol.equals(rol) && per.sucursal.equals(sucursal);
    }
}
