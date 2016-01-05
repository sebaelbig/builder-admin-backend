package ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama.CamaSuspendida_VO;
import ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama.EstadoCama_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("cama_suspendida")
public class CamaSuspendida extends EstadoCama implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_suspendida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSuspendida;

	private String motivo;

	// Constructores
	public CamaSuspendida() {
		setNombre(EstadoCama.SUSPENDIDO);
		setFechaSuspendida(new Date());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CamaSuspendida) {
			CamaSuspendida o = (CamaSuspendida) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public EstadoCama cancelar() {
		return this;
	}

	@Override
	public EstadoCama_VO toValueObject() {
		return new CamaSuspendida_VO(this);
	}

	@Override
	public boolean estaLibre() {
		return false;
	}

	@Override
	public boolean estaOcupado() {
		return false;
	}

	@Override
	public boolean estaSuspendido() {
		return true;
	}

	public Date getFechaSuspendida() {
		return fechaSuspendida;
	}

	public void setFechaSuspendida(Date fechaSuspendida) {
		this.fechaSuspendida = fechaSuspendida;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}