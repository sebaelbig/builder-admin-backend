package ar.org.hospitalespanol.model.turnos.estadosTurno;

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

import ar.org.hospitalespanol.model.core.datosSalud.Prestacion;
import ar.org.hospitalespanol.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.model.core.usuarios.roles.pacientes.Paciente;
import ar.org.hospitalespanol.vo.turnos.estados.EstadoTurno_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table(name = "estado_turno")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "estado_turno", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("estado_turno_base")
public abstract class EstadoTurno {

	/**
	 * 
	 */
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_estado_turno", name = "seq_estado_turno", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado_turno")
	private Long id;

	@Version
	private Integer version;

	public static final String RESERVADO = "Reservado";
	public static final String SIN_RESERVAR = "Sin reservar";
	public static final String TOMADO = "Tomado";
	public static final String PRESENTE = "Presente";

	private String nombre;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	@Column(name = "fecha_establecido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEstablecido;

	// Constructores
	public EstadoTurno() {
		setFechaEstablecido(new Date());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoTurno) {
			EstadoTurno o = (EstadoTurno) objeto;
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

	public abstract boolean estaSinReservar();

	public abstract boolean estaReservado();

	public abstract boolean estaPresentado();

	public abstract boolean estaTomado();

	public abstract EstadoTurno cancelar();

	// Acciones sobre turnos
	public abstract EstadoTurno reservar(Paciente paciente,
			ProductoObraSocialPaciente obraSocialPaciente,
			Rol personalReservoTurno, Float[] costos, Date fechaReserva,
			Prestacion prestacion);

	public abstract EstadoTurno presentar(Date horaPresentado,
			Rol personalPresentoTurno);

	public abstract EstadoTurno presentar(
			ProductoObraSocialPaciente productoAlPresentar,
			Date horaPresentado, Rol personalPresentoTurno);

	public abstract EstadoTurno tomar(Date horaTomado, Rol personalTomoTurno);

	public abstract boolean estaReservadoPor(Paciente paciente);

	public abstract Paciente getPacienteQueReservo();

	public abstract ProductoObraSocialPaciente getProductoObraSocialReserva();

	public abstract void setProductoObraSocialReserva(
			ProductoObraSocialPaciente obraPac);

	public abstract void setDiferenciadoReserva(Float diferenciado);

	public abstract void setHonorariosReserva(Float honorarios);

	public abstract EstadoTurno_VO toValueObject();

}