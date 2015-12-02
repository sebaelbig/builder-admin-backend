package ar.com.builderadmin.model.turnos.agenda.estados;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
//@Entity 
@DiscriminatorValue("agendaModificada")
public class AgendaModificada extends EstadoAgenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public AgendaModificada() {

	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof AgendaModificada) {
			AgendaModificada o = (AgendaModificada) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

}