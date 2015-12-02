package ar.com.builderadmin.model.historiaClinica.derivaciones.estados;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.historiaClinica.episodios.Episodio;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table( name = "estado_derivacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "estado_derivacion", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("estado_derivacion_padre")
public abstract class EstadoDerivacion {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_estado_derivacion", name = "seq_estado_derivacion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado_derivacion")
	private Long id;

	@Version
	private Integer version;

	public static final String PENDIENTE = "Pendiente";
	public static final String CERRADO = "Cerrado";
	public static final String CADUCA = "Caduca";

	private String nombre;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_establecido")
	@Temporal(TemporalType.DATE)
	private Date fechaEstablecido;

	// Constructores
	public EstadoDerivacion() {
		setFechaEstablecido(new Date());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoDerivacion) {
			EstadoDerivacion o = (EstadoDerivacion) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaEstablecido() {
		return fechaEstablecido;
	}

	public void setFechaEstablecido(Date fechaEstablecido) {
		this.fechaEstablecido = fechaEstablecido;
	}

	public abstract boolean estaCaduca();

	public abstract DerivacionCaduca caducarDerivacion(Date fechaCaducidad);

	public abstract boolean estaPendiente();

	public abstract boolean estaCerrado();

	public abstract DerivacionCerrada cerrarDerivacion(Date fechaCierre,
			Episodio episodio);

	public abstract Date getFechaCaduco();

	public abstract Date getFechaCierre();

	public abstract Episodio getEpisodioCierre();

}