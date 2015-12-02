package ar.com.builderadmin.model.historiaClinica.informes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import ar.com.builderadmin.model.core.datosSalud.TipoPrestacionHorus;
import ar.com.builderadmin.vo.historiaClinica.informes.Informe_VO;

//@Entity
@Table
public class Informe {

	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_informe")
	@SequenceGenerator( name = "seq_informe", sequenceName = "seq_informe", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;

	@Version
	private Integer version;

	/**
	 * Numero del informe
	 */
	private String numero;

	@Column(name = "tipo_dni_paciente")
	private String tipoDniPaciente;

	@Column(name = "nro_dni_paciente")
	private String nroDniPaciente;

	@Column(name = "nro_carpeta")
	private String nroCarpeta;

	@Column(name = "fecha_pedida")
	private String fechaPedida; // dd/MM/yyyy

	@Column(name = "dt_fecha_pedida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_fechaPedida;

	@Column(name = "matricula_profesional_solicitante")
	private String matriculaProfesionalSolicitante;

	@Column(name = "matricula_profesional_actuante")
	private String matriculaProfesionalActuante;

	private String usuario;
	private String estado;
	private String modalidad;
	private String equipo;

	@ManyToMany
	@JoinTable( name = "informe_tipo_prestacion_horus", joinColumns = @JoinColumn(name = "id_informe"), inverseJoinColumns = @JoinColumn(name = "id_tipo_prestacion_horus", unique = false), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_informe", "id_tipo_prestacion_horus" }))
	private List<TipoPrestacionHorus> estudiosPaciente;

	private String paciente;
	private String solicitante;
	private String fecha;
	private String estudios;

	@Column(columnDefinition = "text")
	private String texto;

	public Informe() {
		this.setEstudiosPaciente(new ArrayList<TipoPrestacionHorus>());
	}

	public Informe_VO toValueObject() {
		return new Informe_VO(this);
	}

	/**
	 * @return the tipoDniPaciente
	 */
	public String getTipoDniPaciente() {
		return tipoDniPaciente;
	}

	/**
	 * @param tipoDniPaciente
	 *            the tipoDniPaciente to set
	 */
	public void setTipoDniPaciente(String tipoDniPaciente) {
		this.tipoDniPaciente = tipoDniPaciente;
	}

	/**
	 * @return the nroDniPaciente
	 */
	public String getNroDniPaciente() {
		return nroDniPaciente;
	}

	/**
	 * @param nroDniPaciente
	 *            the nroDniPaciente to set
	 */
	public void setNroDniPaciente(String nroDniPaciente) {
		this.nroDniPaciente = nroDniPaciente;
	}

	/**
	 * @return the nroCarpeta
	 */
	public String getNroCarpeta() {
		return nroCarpeta;
	}

	/**
	 * @param nroCarpeta
	 *            the nroCarpeta to set
	 */
	public void setNroCarpeta(String nroCarpeta) {
		this.nroCarpeta = nroCarpeta;
	}

	/**
	 * @return the fechaPedida
	 */
	public String getFechaPedida() {
		return fechaPedida;
	}

	/**
	 * @param fechaPedida
	 *            the fechaPedida to set
	 */
	public void setFechaPedida(String fechaPedida) {
		this.fechaPedida = fechaPedida;
	}

	/**
	 * @return the dt_fechaPedida
	 */
	public Date getDt_fechaPedida() {
		return dt_fechaPedida;
	}

	/**
	 * @param dt_fechaPedida
	 *            the dt_fechaPedida to set
	 */
	public void setDt_fechaPedida(Date dt_fechaPedida) {
		this.dt_fechaPedida = dt_fechaPedida;
	}

	/**
	 * @return the matriculaProfesionalSolicitante
	 */
	public String getMatriculaProfesionalSolicitante() {
		return matriculaProfesionalSolicitante;
	}

	/**
	 * @param matriculaProfesionalSolicitante
	 *            the matriculaProfesionalSolicitante to set
	 */
	public void setMatriculaProfesionalSolicitante(
			String matriculaProfesionalSolicitante) {
		this.matriculaProfesionalSolicitante = matriculaProfesionalSolicitante;
	}

	/**
	 * @return the matriculaProfesionalActuante
	 */
	public String getMatriculaProfesionalActuante() {
		return matriculaProfesionalActuante;
	}

	/**
	 * @param matriculaProfesionalActuante
	 *            the matriculaProfesionalActuante to set
	 */
	public void setMatriculaProfesionalActuante(
			String matriculaProfesionalActuante) {
		this.matriculaProfesionalActuante = matriculaProfesionalActuante;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad
	 *            the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo
	 *            the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the estudiosPaciente
	 */
	public List<TipoPrestacionHorus> getEstudiosPaciente() {
		return estudiosPaciente;
	}

	/**
	 * @param estudiosPaciente
	 *            the estudiosPaciente to set
	 */
	public void setEstudiosPaciente(List<TipoPrestacionHorus> estudiosPaciente) {
		this.estudiosPaciente = estudiosPaciente;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the borrado
	 */
	public Boolean getBorrado() {
		return borrado;
	}

	/**
	 * @param borrado
	 *            the borrado to set
	 */
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante
	 *            the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the estudios
	 */
	public String getEstudios() {
		return estudios;
	}

	/**
	 * @param estudios
	 *            the estudios to set
	 */
	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

}