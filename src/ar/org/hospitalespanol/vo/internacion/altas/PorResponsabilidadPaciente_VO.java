package ar.org.hospitalespanol.vo.internacion.altas;

import java.io.Serializable;

import ar.org.hospitalespanol.model.internacion.altas.AltaInternacion;
import ar.org.hospitalespanol.model.internacion.altas.PorResponsabilidadPaciente;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class PorResponsabilidadPaciente_VO extends AltaInternacion_VO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorResponsabilidadPaciente_VO() {
		super();
		setNombre(AltaInternacion.RESPONSABILIDAD_PACIENTE);
	}

	public PorResponsabilidadPaciente_VO(
			PorResponsabilidadPaciente porAltaMedica) {
		setObject(porAltaMedica);
	}

	public PorResponsabilidadPaciente_VO(
			PorResponsabilidadPaciente porAltaMedica, int profundidadActual,
			int profundidadDeseada) {
		setObject(porAltaMedica, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorResponsabilidadPaciente_VO) {
			PorResponsabilidadPaciente_VO o = (PorResponsabilidadPaciente_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setObject(PorResponsabilidadPaciente objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
	}

	public void setObject(PorResponsabilidadPaciente objeto,
			int profundidadActual, int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());

	}

	@Override
	public AltaInternacion toObject() {
		PorResponsabilidadPaciente resul = new PorResponsabilidadPaciente();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		return resul;
	}

}