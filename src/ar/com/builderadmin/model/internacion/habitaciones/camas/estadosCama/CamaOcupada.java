package ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.model.internacion.Reserva;
import ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama.CamaOcupada_VO;
import ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama.EstadoCama_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("cama_ocupada")
public class CamaOcupada extends EstadoCama implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_ocupacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaOcupacion;

	@OneToOne
	private Reserva reserva;

	// Constructores
	public CamaOcupada() {
		setNombre(EstadoCama.OCUPADA);
		setFechaOcupacion(new Date());

	}

	public CamaOcupada(Reserva r) {
		this.setReserva(r);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CamaOcupada) {
			CamaOcupada o = (CamaOcupada) objeto;
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
		return new CamaOcupada_VO(this);
	}

	@Override
	public boolean estaLibre() {
		return false;
	}

	@Override
	public boolean estaOcupado() {
		return true;
	}

	@Override
	public boolean estaSuspendido() {
		return false;
	}

	public Date getFechaOcupacion() {
		return fechaOcupacion;
	}

	public void setFechaOcupacion(Date fechaOcupacion) {
		this.fechaOcupacion = fechaOcupacion;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public EstadoCama ocupar(Reserva r) {
		this.setReserva(r);
		return this;
	}
}