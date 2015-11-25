package ar.org.hospitalespanol.model.turnos.agenda.estados;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:09 a.m.
 */
@Entity 
@DiscriminatorValue("agendaAceptada")
public class AgendaAceptada extends EstadoAgenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public AgendaAceptada() {

	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof AgendaAceptada) {
			AgendaAceptada o = (AgendaAceptada) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

}