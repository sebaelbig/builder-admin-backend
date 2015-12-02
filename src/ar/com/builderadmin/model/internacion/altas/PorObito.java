package ar.com.builderadmin.model.internacion.altas;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;

import ar.com.builderadmin.vo.internacion.altas.AltaInternacion_VO;
import ar.com.builderadmin.vo.internacion.altas.PorObito_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
//@Entity 
@DiscriminatorValue("por_obito")
public class PorObito extends AltaInternacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorObito() {
		setNombre(AltaInternacion.OBITO);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorObito) {
			PorObito o = (PorObito) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public PorObito cancelar() {
		return this;
	}

	@Override
	public AltaInternacion_VO toValueObject() {
		return new PorObito_VO(this);
	}

	@Override
	public AltaInternacion_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new PorObito_VO(this, profundidadActual, profundidadDeseada);
	}

}