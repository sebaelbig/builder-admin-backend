package ar.org.hospitalespanol.model.internacion.habitaciones.camas.estadosCama;

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

import ar.org.hospitalespanol.model.internacion.Reserva;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.estadosCama.EstadoCama_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table(name = "estado_cama")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "estado_cama", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("estado_cama_base")
public abstract class EstadoCama {

	/**
	 * 
	 */
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_estado_cama", name = "seq_estado_cama", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado_cama")
	private Long id;
	@Version
	private Integer version;

	public static final String LIBRE = "Libre";
	public static final String OCUPADA = "Ocupada";
	public static final String SUSPENDIDO = "Suspendida";

	private String nombre;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_establecido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEstablecido;

	// Constructores
	public EstadoCama() {
		setFechaEstablecido(new Date());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoCama) {
			EstadoCama o = (EstadoCama) objeto;
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

	public abstract boolean estaLibre();

	public abstract boolean estaOcupado();

	public abstract boolean estaSuspendido();

	public abstract EstadoCama cancelar();

	// Acciones sobre camas

	public abstract EstadoCama_VO toValueObject();

	public EstadoCama ocupar(Reserva r) {
		// TODO Auto-generated method stub
		return null;
	}

}