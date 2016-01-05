package ar.com.builderadmin.model.core.usuarios.roles.profesionales.relaciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.builderadmin.model.core.areas.servicios.Division;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
@Entity 
@DiscriminatorValue("accionista")
public class Accionista extends RelacionConInstitucion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public Accionista() {
		setNombre(ACCIONISTA);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Accionista) {
			Accionista o = (Accionista) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public BigDecimal costoConsultorio(Division consul) {
		return consul.getCostoAccionista();
	}

}