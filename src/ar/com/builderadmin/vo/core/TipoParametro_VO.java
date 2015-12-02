package ar.com.builderadmin.vo.core;

import ar.com.builderadmin.model.core.E_TipoParametro;

public class TipoParametro_VO {

	private E_TipoParametro type;
	private String nombre;

	public TipoParametro_VO() {
	}
	public TipoParametro_VO(E_TipoParametro e) {
		this.setNombre(e.toString());
	}
	/**
	 * @return the type
	 */
	public E_TipoParametro getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(E_TipoParametro type) {
		this.type = type;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
