package ar.org.hospitalespanol.vo.internacion.altas;

import java.io.Serializable;

import ar.org.hospitalespanol.model.internacion.altas.AltaInternacion;
import ar.org.hospitalespanol.model.internacion.altas.PorObito;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class PorObito_VO extends AltaInternacion_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorObito_VO() {
		super();
		setNombre(AltaInternacion.OBITO);
	}

	public PorObito_VO(PorObito porAltaMedica) {
		setObject(porAltaMedica);
	}

	public PorObito_VO(PorObito porAltaMedica, int profundidadActual,
			int profundidadDeseada) {
		setObject(porAltaMedica, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorObito_VO) {
			PorObito_VO o = (PorObito_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setObject(PorObito objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
	}

	public void setObject(PorObito objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());

	}

	@Override
	public AltaInternacion toObject() {
		PorObito resul = new PorObito();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		return resul;
	}

}