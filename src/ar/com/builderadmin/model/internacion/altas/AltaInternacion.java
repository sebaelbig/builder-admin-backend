package ar.com.builderadmin.model.internacion.altas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.vo.internacion.altas.AltaInternacion_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "alta_internacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "alta_internacion", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("alta_internacion_base")
public abstract class AltaInternacion {

	/**
	 * 
	 */
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_alta_internacion", name = "seq_alta_internacion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alta_internacion")
	private Long id;
	@Version
	private Integer version;

	public static final String POR_TRASLADO = "Alta por traslado";
	public static final String OBITO = "Alta por �bito";
	public static final String RESPONSABILIDAD_PACIENTE = "Alta por fuga o resonsabilidad del paciente";
	public static final String SANATORIAL_CON_TRATAMIENTO = "Alta sanatorial con tratamiento";
	public static final String MEDICA = "Alta m�dica";

	private String nombre;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_establecido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEstablecido;

	// Constructores
	public AltaInternacion() {
		setFechaEstablecido(new Date());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof AltaInternacion) {
			AltaInternacion o = (AltaInternacion) objeto;
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

	public abstract AltaInternacion_VO toValueObject();

	public abstract AltaInternacion_VO toValueObject(int profundidadActual,
			int profundidadDeseada);

}