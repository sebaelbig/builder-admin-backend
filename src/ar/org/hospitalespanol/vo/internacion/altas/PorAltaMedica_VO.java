package ar.org.hospitalespanol.vo.internacion.altas;


import java.io.Serializable;

import ar.org.hospitalespanol.model.internacion.altas.AltaInternacion;
import ar.org.hospitalespanol.model.internacion.altas.PorAltaMedica;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class PorAltaMedica_VO extends AltaInternacion_VO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Constructores
	public PorAltaMedica_VO(){
		super();
		setNombre(AltaInternacion.MEDICA);
	}

	public PorAltaMedica_VO(PorAltaMedica porAltaMedica) {
		setObject(porAltaMedica);
	}

	public PorAltaMedica_VO(PorAltaMedica porAltaMedica, int profundidadActual, int profundidadDeseada) {
		setObject(porAltaMedica, profundidadActual, profundidadDeseada);
	}

	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorAltaMedica_VO) {
			PorAltaMedica_VO o = (PorAltaMedica_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}


	public void setObject(PorAltaMedica objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());
		
		this.setFechaEstablecido(objeto.getFechaEstablecido());
	}

	public void setObject(PorAltaMedica objeto, int profundidadActual, int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());
		
		this.setFechaEstablecido(objeto.getFechaEstablecido());
		
	}

	@Override
	public AltaInternacion toObject() {
		PorAltaMedica resul = new PorAltaMedica();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		
		resul.setFechaEstablecido(this.getFechaEstablecido());
		return resul;
	}

}