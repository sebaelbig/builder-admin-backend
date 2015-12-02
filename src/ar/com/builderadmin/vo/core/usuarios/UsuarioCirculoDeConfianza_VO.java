package ar.com.builderadmin.vo.core.usuarios;

import java.io.Serializable;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
public class UsuarioCirculoDeConfianza_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

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

	public UsuarioCirculoDeConfianza_VO(Long id, Long sucursal,
			String apellido, String nombres, String nroDocumento) {
		this.setId(id);
		this.setSucursal(sucursal);
		this.setApellido(apellido);
		this.setNombres(nombres);
		this.setNroDocumento(nroDocumento);
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRol(Long rol) {
		this.id = rol;
	}

	@Override
	public String toString() {
		return this.getId().toString();
	}

	public Integer getVersion() {
		return null;
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