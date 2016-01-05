package ar.com.builderadmin.model.historiaClinica.derivaciones.estados;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.model.historiaClinica.episodios.Episodio;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("derivacion_cerrada")
public class DerivacionCerrada extends EstadoDerivacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "fecha_cierre")
	@Temporal(TemporalType.DATE)
	private Date fechaCierre;

	@OneToOne
	@JoinColumn(name = "id_episodio")
	private Episodio episodio;

	// Constructores
	public DerivacionCerrada() {
		setNombre(EstadoDerivacion.CERRADO);
	}

	public DerivacionCerrada(Date fechaCierre, Episodio episodio) {
		setNombre(EstadoDerivacion.CERRADO);
		setFechaCierre(fechaCierre);
		setEpisodio(episodio);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof DerivacionCerrada) {
			DerivacionCerrada o = (DerivacionCerrada) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public boolean estaCerrado() {
		return true;
	}

	@Override
	public boolean estaPendiente() {
		return false;
	}

	@Override
	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Episodio getEpisodio() {
		return episodio;
	}

	public void setEpisodio(Episodio episodio) {
		this.episodio = episodio;
	}

	// Comportamiento
	@Override
	public DerivacionCerrada cerrarDerivacion(Date fechaCierre,
			Episodio episodio) {
		return this;
	}

	@Override
	public DerivacionCaduca caducarDerivacion(Date fechaCaducidad) {
		return null;
	}

	@Override
	public boolean estaCaduca() {
		return false;
	}

	@Override
	public Episodio getEpisodioCierre() {
		return getEpisodio();
	}

	@Override
	public Date getFechaCaduco() {
		return getFechaCierre();
	}
}