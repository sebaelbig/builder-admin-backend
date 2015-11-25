package ar.org.hospitalespanol.model;

import ar.org.hospitalespanol.model.core.DatosAccion;

/**
 *
 * @author segarcia
 *
 */
public interface I_Bloqueable {


	/**
	 * @return the bloqueado
	 */
	public Boolean getBloqueado();
	
	/**
	 * @param bloqueado the bloqueado to set
	 */
	public void setBloqueado(Boolean bloqueado);
	
	/**
	 * @return the bloqueado
	 */
	public DatosAccion getDatosAccion();

	/**
	 * @param bloqueado the bloqueado to set
	 */
	public void setDatosAccion(DatosAccion data);
	
}
