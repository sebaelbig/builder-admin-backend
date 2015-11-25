package ar.org.hospitalespanol.model.historiaClinica.derivaciones.estados;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.org.hospitalespanol.model.historiaClinica.episodios.Episodio;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("derivacion_caduca")
public class DerivacionCaduca extends EstadoDerivacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_caduco")
	@Temporal(TemporalType.DATE)
	private Date fechaCaduco;

	// Constructores
	public DerivacionCaduca() {
		setNombre(EstadoDerivacion.CADUCA);
	}

	public DerivacionCaduca(Date fechaCaducidad) {
		setNombre(EstadoDerivacion.CADUCA);
		setFechaCaduco(fechaCaducidad);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof DerivacionCaduca) {
			DerivacionCaduca o = (DerivacionCaduca) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public boolean estaPendiente() {
		return true;
	}

	@Override
	public boolean estaCerrado() {
		return false;
	}

	@Override
	public DerivacionCerrada cerrarDerivacion(Date fechaCierre,
			Episodio episodio) {
		return null;
	}

	@Override
	public DerivacionCaduca caducarDerivacion(Date fechaCaducidad) {
		return this;
	}

	@Override
	public boolean estaCaduca() {
		return true;
	}

	@Override
	public Date getFechaCaduco() {
		return fechaCaduco;
	}

	public void setFechaCaduco(Date fechaCaduco) {
		this.fechaCaduco = fechaCaduco;
	}

	@Override
	public Episodio getEpisodioCierre() {
		return null;
	}

	@Override
	public Date getFechaCierre() {
		return null;
	}

}