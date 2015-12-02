package ar.com.builderadmin.vo.internacion.altas;

import java.io.Serializable;

import ar.com.builderadmin.model.internacion.altas.AltaInternacion;
import ar.com.builderadmin.model.internacion.altas.PorTraslado;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class PorTraslado_VO extends AltaInternacion_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorTraslado_VO() {
		super();
		setNombre(AltaInternacion.POR_TRASLADO);
	}

	public PorTraslado_VO(PorTraslado porAltaMedica) {
		setObject(porAltaMedica);
	}

	public PorTraslado_VO(PorTraslado porAltaMedica, int profundidadActual,
			int profundidadDeseada) {
		setObject(porAltaMedica, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorTraslado_VO) {
			PorTraslado_VO o = (PorTraslado_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setObject(PorTraslado objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
	}

	public void setObject(PorTraslado objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());

	}

	@Override
	public AltaInternacion toObject() {
		PorTraslado resul = new PorTraslado();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		return resul;
	}

}