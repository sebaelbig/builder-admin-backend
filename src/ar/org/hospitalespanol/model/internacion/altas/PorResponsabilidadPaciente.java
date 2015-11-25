package ar.org.hospitalespanol.model.internacion.altas;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.org.hospitalespanol.vo.internacion.altas.AltaInternacion_VO;
import ar.org.hospitalespanol.vo.internacion.altas.PorResponsabilidadPaciente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("por_responsabilidad_paciente")
public class PorResponsabilidadPaciente extends AltaInternacion implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public PorResponsabilidadPaciente() {
		setNombre(AltaInternacion.RESPONSABILIDAD_PACIENTE);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PorResponsabilidadPaciente) {
			PorResponsabilidadPaciente o = (PorResponsabilidadPaciente) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public PorResponsabilidadPaciente cancelar() {
		return this;
	}

	@Override
	public AltaInternacion_VO toValueObject() {
		return new PorResponsabilidadPaciente_VO(this);
	}

	@Override
	public AltaInternacion_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new PorResponsabilidadPaciente_VO(this, profundidadActual,
				profundidadDeseada);
	}

}