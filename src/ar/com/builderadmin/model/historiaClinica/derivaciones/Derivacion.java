package ar.com.builderadmin.model.historiaClinica.derivaciones;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.Profesional;
import ar.com.builderadmin.model.historiaClinica.derivaciones.estados.DerivacionPendiente;
import ar.com.builderadmin.model.historiaClinica.derivaciones.estados.EstadoDerivacion;
import ar.com.builderadmin.model.historiaClinica.episodios.Episodio;

/**
 * 
 * La Clasificaci�n de Derivaciones F�rmaco-terap�uticas (CDF) es una taxonom�a
 * para definir y agrupar las situaciones que requieren de la derivaci�n entre
 * los farmac�uticos y los m�dicos (o viceversa), en relaci�n con la
 * f�rmaco-terapia usada por los pacientes
 * 
 * Ver tambien: El c�digo ATC o Sistema de Clasificaci�n Anat�mica, Terap�utica,
 * Qu�mica
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "derivacion")
public class Derivacion {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_derivacion", name = "seq_derivacion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_derivacion")
	private Long id;
	@Version
	private Integer version;

	@OneToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Column(name = "fecha_emicion")
	@Temporal(TemporalType.DATE)
	private Date fechaEmicion;

	@ManyToOne
	@JoinColumn(name = "id_profesional_solicitante")
	private Profesional profesionalSolicitante;

	@ManyToOne
	@JoinColumn(name = "id_prestacion")
	private Prestacion prestacion;

	@Column(name = "observacion")
	private String observacion;

	/**
	 * Estado de la derivacion
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estado")
	private EstadoDerivacion estado;

	public Derivacion() {
		setEstado(new DerivacionPendiente());
		setFechaEmicion(new Date());
	}

	public Derivacion(Paciente paciente, Profesional profesional) {
		setEstado(new DerivacionPendiente());
		setPaciente(paciente);
		setFechaEmicion(new Date());
		setProfesionalSolicitante(profesional);
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

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Derivacion) {
			Derivacion o = (Derivacion) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getFechaEmicion() {
		return fechaEmicion;
	}

	public void setFechaEmicion(Date fechaEmicion) {
		this.fechaEmicion = fechaEmicion;
	}

	public Profesional getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Profesional profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Prestacion getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(Prestacion prestacion) {
		this.prestacion = prestacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public EstadoDerivacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoDerivacion estado) {
		this.estado = estado;
	}

	public void cerrarDerivacion(Episodio episodio) {
		this.setEstado(this.getEstado().cerrarDerivacion(new Date(), episodio));
	}

	public boolean estaCaduca() {
		return this.getEstado().estaCaduca();
	}

	public boolean estaPendiente() {
		return this.getEstado().estaPendiente();
	}

	public boolean estaCerrado() {
		return this.getEstado().estaCerrado();
	}

	public Date getFechaCaduco() {
		return getEstado().getFechaCaduco();
	}

	public Date getFechaCierre() {
		return getEstado().getFechaCierre();
	}

	public Episodio getEpisodioCierre() {
		return getEstado().getEpisodioCierre();
	}

}