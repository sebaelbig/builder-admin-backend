package ar.com.builderadmin.vo.internacion.altas;

import java.io.Serializable;

import ar.com.builderadmin.model.internacion.altas.AltaInternacion;
import ar.com.builderadmin.model.internacion.altas.PorSanatorialConTratamiento;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class PorSanatorialConTratamiento_VO extends AltaInternacion_VO
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorSanatorialConTratamiento_VO() {
		super();
		setNombre(AltaInternacion.SANATORIAL_CON_TRATAMIENTO);
	}

	public PorSanatorialConTratamiento_VO(
			PorSanatorialConTratamiento porAltaMedica) {
		setObject(porAltaMedica);
	}

	public PorSanatorialConTratamiento_VO(
			PorSanatorialConTratamiento porAltaMedica, int profundidadActual,
			int profundidadDeseada) {
		setObject(porAltaMedica, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorSanatorialConTratamiento_VO) {
			PorSanatorialConTratamiento_VO o = (PorSanatorialConTratamiento_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setObject(PorSanatorialConTratamiento objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
	}

	public void setObject(PorSanatorialConTratamiento objeto,
			int profundidadActual, int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());

	}

	@Override
	public AltaInternacion toObject() {
		PorSanatorialConTratamiento resul = new PorSanatorialConTratamiento();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		return resul;
	}

}