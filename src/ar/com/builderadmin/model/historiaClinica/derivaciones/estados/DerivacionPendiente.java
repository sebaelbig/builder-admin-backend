package ar.com.builderadmin.model.historiaClinica.derivaciones.estados;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.builderadmin.model.historiaClinica.episodios.Episodio;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("derivacion_pendiente")
public class DerivacionPendiente extends EstadoDerivacion implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public DerivacionPendiente() {
		setNombre(EstadoDerivacion.PENDIENTE);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof DerivacionPendiente) {
			DerivacionPendiente o = (DerivacionPendiente) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public boolean estaCerrado() {
		return false;
	}

	@Override
	public boolean estaPendiente() {
		return true;
	}

	// Comportamiento
	@Override
	public DerivacionCerrada cerrarDerivacion(Date fechaCierre,
			Episodio episodio) {
		return new DerivacionCerrada(fechaCierre, episodio);
	}

	@Override
	public DerivacionCaduca caducarDerivacion(Date fechaCaducidad) {
		return new DerivacionCaduca(fechaCaducidad);
	}

	@Override
	public boolean estaCaduca() {
		return false;
	}

	@Override
	public Episodio getEpisodioCierre() {
		return null;
	}

	@Override
	public Date getFechaCaduco() {
		return null;
	}

	@Override
	public Date getFechaCierre() {
		return null;
	}
}