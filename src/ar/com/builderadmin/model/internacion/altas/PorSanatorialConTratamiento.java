package ar.com.builderadmin.model.internacion.altas;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.builderadmin.vo.internacion.altas.AltaInternacion_VO;
import ar.com.builderadmin.vo.internacion.altas.PorSanatorialConTratamiento_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("por_sanatorial_con_tratamiento")
public class PorSanatorialConTratamiento extends AltaInternacion implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorSanatorialConTratamiento() {
		setNombre(AltaInternacion.SANATORIAL_CON_TRATAMIENTO);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorSanatorialConTratamiento) {
			PorSanatorialConTratamiento o = (PorSanatorialConTratamiento) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public PorSanatorialConTratamiento cancelar() {
		return this;
	}

	@Override
	public AltaInternacion_VO toValueObject() {
		return new PorSanatorialConTratamiento_VO(this);
	}

	@Override
	public AltaInternacion_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new PorSanatorialConTratamiento_VO(this, profundidadActual,
				profundidadDeseada);
	}

}