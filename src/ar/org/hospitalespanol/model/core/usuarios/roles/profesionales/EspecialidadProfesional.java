package ar.org.hospitalespanol.model.core.usuarios.roles.profesionales;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.especialidades.Especialidad;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.EspecialidadProfesional_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity
@Table( name = "especialidad_profesional")
public class EspecialidadProfesional implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_especialidad_profesional", name = "seq_especialidad_profesional", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especialidad_profesional")
	private Long id;

	@Version
	private Integer version;

	@Column(name = "mensaje_a_paciente", length = 500)
	private String mensajeAPaciente;

	@Column(name = "mensaje_interno", length = 500)
	private String mensajeInterno;

	/**
	 * Categoria del profesional en esta especialidad
	 */
	@ManyToOne
	@JoinColumn(name = "id_categoria_profesional")
	private CategoriaProfesional categoria;

	@ManyToOne
	@JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;

	@OneToOne
	@JoinColumn(name = "id_contrato_profesional")
	private ContratoProfesional contratoProfesional;

	/**
	 * importe que debera pagar el paciente en primera visita
	 */
	@Column(name = "primera_visita")
	private Float primeraVisita;

	/**
	 * importe que debera pagar el paciente en la siguiente visita
	 */
	@Column(name = "siguiente_visita")
	private Float siguienteVisita;

	@ManyToOne
	@JoinColumn(name = "id_servicio")
	private Servicio servicio;

	// Constructores
	public EspecialidadProfesional() {
		setCategoria(new CategoriaProfesional());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EspecialidadProfesional) {
			EspecialidadProfesional o = (EspecialidadProfesional) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public String getMensajeAPaciente() {
		return mensajeAPaciente;
	}

	public void setMensajeAPaciente(String mensajeAPaciente) {
		this.mensajeAPaciente = mensajeAPaciente;
	}

	public CategoriaProfesional getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProfesional categoria) {
		this.categoria = categoria;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Float getPrimeraVisita() {
		return primeraVisita;
	}

	public void setPrimeraVisita(Float primeraVisita) {
		this.primeraVisita = primeraVisita;
	}

	public Float getSiguienteVisita() {
		return siguienteVisita;
	}

	public void setSiguienteVisita(Float siguienteVisita) {
		this.siguienteVisita = siguienteVisita;
	}

	public String getMensajeInterno() {
		return mensajeInterno;
	}

	public void setMensajeInterno(String mensajeAInterno) {
		this.mensajeInterno = mensajeAInterno;
	}

	public ContratoProfesional getContratoProfesional() {
		return contratoProfesional;
	}

	public void setContratoProfesional(ContratoProfesional contratoProfesional) {
		this.contratoProfesional = contratoProfesional;
	}

	public EspecialidadProfesional_VO toValueObject() {
		return new EspecialidadProfesional_VO(this);
	}

	public EspecialidadProfesional_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new EspecialidadProfesional_VO(this, profundidadActual,
				profundidadDeseada);
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

}