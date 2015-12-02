package ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama;

import java.io.Serializable;
import java.util.Date;

import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.CamaOcupada;
import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.EstadoCama;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.internacion.Reserva_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class CamaOcupada_VO extends EstadoCama_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fechaOcupacion;

	private Reserva_VO reserva;

	// Constructores
	public CamaOcupada_VO() {
		super();
		setNombre(EstadoCama.OCUPADA);
	}

	// Constructores
	public CamaOcupada_VO(CamaOcupada obj) {
		setNombre(EstadoCama.OCUPADA);
		this.setObject(obj);
	}

	public CamaOcupada_VO(CamaOcupada obj, int profundidadActual,
			int profundidadDeseada) {
		setNombre(EstadoCama.OCUPADA);
		this.setObject(obj, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CamaOcupada_VO) {
			CamaOcupada_VO o = (CamaOcupada_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setObject(CamaOcupada objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setFechaOcupacion(objeto.getFechaOcupacion());
		this.setReserva(objeto.getReserva().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 4));
	}

	public void setObject(CamaOcupada objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setFechaOcupacion(objeto.getFechaOcupacion());
		this.setReserva(objeto.getReserva().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 4));
	}

	@Override
	public EstadoCama toObject() {
		CamaOcupada resul = new CamaOcupada();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setFechaEstablecido(this.getFechaEstablecido());
		resul.setFechaOcupacion(this.getFechaOcupacion());
		resul.setReserva(this.getReserva().toObject());

		return resul;
	}

	public Date getFechaOcupacion() {
		return fechaOcupacion;
	}

	public void setFechaOcupacion(Date fechaOcupacion) {
		this.fechaOcupacion = fechaOcupacion;
	}

	public Reserva_VO getReserva() {
		return reserva;
	}

	public void setReserva(Reserva_VO reserva) {
		this.reserva = reserva;
	}

}