package ar.com.builderadmin.model.historiaClinica;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.usuarios.roles.Rol;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "solicitud_ver_historia_clinica_paciente")
public class SolicitudVerHistoriaClinicaPaciente {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_solicitud_ver_historia_clinica_paciente", name = "seq_solicitud_ver_historia_clinica_paciente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_solicitud_ver_historia_clinica_paciente")
	private Long id;
	
	@Version
	private Integer version;

	/**
	 * Fecha en la que se realizo la solicitud
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/**
	 * Indica si la solicitud ha sido atendida
	 */
	private Boolean atendido;

	/**
	 * Profesional que solicita el permiso
	 */
	@ManyToOne
	@JoinColumn(name = "id_profesional_solicitante")
	private Rol profesionalSolicitante;

	/**
	 * Profesional que atendio la solicitud
	 */
	@ManyToOne
	@JoinColumn(name = "id_profesional_atendio")
	private Rol profesionalAtendio;

	/**
	 * Paciente al cual se le quiere ver la HC
	 */
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Rol paciente;

	/**
	 * Indica si la solicitud fue aceptada o no
	 */
	private Boolean aceptada;

	/**
	 * Motivo por el cual fue rechazada la solicitud
	 */
	private String motivo;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getAtendido() {
		return atendido;
	}

	public void setAtendido(Boolean atendido) {
		this.atendido = atendido;
	}

	public Rol getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Rol profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Rol getProfesionalAtendio() {
		return profesionalAtendio;
	}

	public void setProfesionalAtendio(Rol profesionalAtendio) {
		this.profesionalAtendio = profesionalAtendio;
	}

	public Rol getPaciente() {
		return paciente;
	}

	public void setPaciente(Rol paciente) {
		this.paciente = paciente;
	}

	public Boolean getAceptada() {
		return aceptada;
	}

	public void setAceptada(Boolean aceptada) {
		this.aceptada = aceptada;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}