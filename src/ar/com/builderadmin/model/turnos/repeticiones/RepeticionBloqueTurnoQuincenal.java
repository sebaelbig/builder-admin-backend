package ar.com.builderadmin.model.turnos.repeticiones;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
//@Entity 
@DiscriminatorValue("sobre_turno")
public class RepeticionBloqueTurnoQuincenal extends RepeticionBloqueTurno
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public RepeticionBloqueTurnoQuincenal() {
		setFrecuencia(14);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof RepeticionBloqueTurnoQuincenal) {
			RepeticionBloqueTurnoQuincenal o = (RepeticionBloqueTurnoQuincenal) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String getNombre() {

		return "Cada 2 semanas";
	}

}