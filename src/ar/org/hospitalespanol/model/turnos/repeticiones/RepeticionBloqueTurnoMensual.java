package ar.org.hospitalespanol.model.turnos.repeticiones;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
@Entity 
@DiscriminatorValue("sobre_turno")
public class RepeticionBloqueTurnoMensual extends RepeticionBloqueTurno
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public RepeticionBloqueTurnoMensual() {
		setFrecuencia(28);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof RepeticionBloqueTurnoMensual) {
			RepeticionBloqueTurnoMensual o = (RepeticionBloqueTurnoMensual) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String getNombre() {
		return "Cada mes";
	}

}