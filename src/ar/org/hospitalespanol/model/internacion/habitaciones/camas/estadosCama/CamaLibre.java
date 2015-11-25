package ar.org.hospitalespanol.model.internacion.habitaciones.camas.estadosCama;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.org.hospitalespanol.model.internacion.Reserva;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.estadosCama.CamaLibre_VO;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.estadosCama.EstadoCama_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("cama_libre")
public class CamaLibre extends EstadoCama implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public CamaLibre() {
		setNombre(EstadoCama.LIBRE);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CamaLibre) {
			CamaLibre o = (CamaLibre) objeto;
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
		return new CamaLibre_VO(this);
	}

	@Override
	public boolean estaLibre() {
		return true;
	}

	@Override
	public boolean estaOcupado() {
		return false;
	}

	@Override
	public boolean estaSuspendido() {
		return false;
	}

	@Override
	public EstadoCama ocupar(Reserva r) {
		return new CamaOcupada(r);
	}

}