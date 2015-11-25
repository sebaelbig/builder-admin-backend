package ar.org.hospitalespanol.model.core.obrasSociales;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.org.hospitalespanol.vo.core.obrasSociales.EstadoObraSocial_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.ObraSocialSuspendida_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
@Entity 
@DiscriminatorValue("os_suspendida")
public class ObraSocialSuspendida extends EstadoObraSocial implements
		Serializable {


	private static final long serialVersionUID = 1L;

	@Column(name="fecha_suspension")
	@Temporal(TemporalType.DATE)
	private Date fechaSuspension;

	@Column(length = 150)
	private String motivo;

	// Constructores
	public ObraSocialSuspendida() {
		setNombre(EstadoObraSocial.SUSPENDIDA);
	}

	public ObraSocialSuspendida(String motivoSuspendida) {
		setNombre(EstadoObraSocial.SUSPENDIDA);
		setMotivo(motivoSuspendida);
	}

	public ObraSocialSuspendida(Long id, Integer version, String nombre,
			 String motivo, Date fechaSuspension) {
		super(id, version, nombre);
		setMotivo(motivo);
		setFechaSuspension(fechaSuspension);
	}

	
	/* Metodo */
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ObraSocialSuspendida) {
			ObraSocialSuspendida o = (ObraSocialSuspendida) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public ObraSocialSuspendida_VO toValueObject() {
		return new ObraSocialSuspendida_VO(this);
	}

	@Override
	public EstadoObraSocial_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new ObraSocialSuspendida_VO(this, profundidadActual, profundidadDeseada);
	}
	
	// Getters & Setters
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}

}