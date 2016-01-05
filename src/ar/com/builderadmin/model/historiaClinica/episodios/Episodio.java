package ar.com.builderadmin.model.historiaClinica.episodios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.com.builderadmin.model.core.templates.I_Template;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.Profesional;
import ar.com.builderadmin.vo.historiaClinica.episodios.Episodio_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity
@Table
public class Episodio implements Serializable, I_Template {

	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_episodio", name = "seq_episodio", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_episodio")
	private Long id;

	@Version
	private Integer version;

	@OneToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_producto_obra_social_paciente")
	private ProductoObraSocialPaciente producto_OSPaciente;

	@ManyToMany
	@JoinTable(
			 
			name = "episodio_prestacion", 
			joinColumns = @JoinColumn(name = "id_episodio"), 
			inverseJoinColumns = @JoinColumn(name = "id_prestacion", unique = false), 
			uniqueConstraints = @UniqueConstraint(columnNames = {"id_episodio", "id_prestacion" })
	)
	private List<Prestacion> prestaciones = new ArrayList<Prestacion>();

	@Column(name = "informe_institucional", columnDefinition = "text")
	private String informeInstitucional;

	@Column(name = "fecha_realizado")
	@Temporal(TemporalType.DATE)
	private Date fechaRealizado;

	@ManyToOne
	@JoinColumn(name = "id_profesional_solicitante")
	private Profesional profesionalSolicitante;

	@ManyToOne
	@JoinColumn(name = "id_rol_actuante")
	private Rol profesionalActuante;

	@Column(name = "tipo_profesional", length = 20)
	private String tipoProfesional = "Institucional";

	@Column(name = "paciente_internado")
	private Boolean pacienteInternado = false;

	@Column(name = "nro_cama_paciente_internado")
	private Integer nroCamaPacienteInternado;

	@Column(name = "numero_episodio")
	private String nroEpisodio;

	@Column(name = "id_episodio_origen")
	private Long id_episodioOrigen;

	public Episodio() {

	}

	public Episodio(Paciente paciente) {
		setPaciente(paciente);
		setPacienteInternado(false);
	}

	public Episodio(Paciente paciente, Profesional profesionalSolicitante) {
		setPaciente(paciente);
		setProfesionalSolicitante(profesionalSolicitante);
		setPacienteInternado(false);
	}

	public Episodio(Paciente paciente, Profesional profesionalSolicitante,
			Profesional profesionalActuante) {
		setPaciente(paciente);
		setProfesionalSolicitante(profesionalSolicitante);
		setProfesionalActuante(profesionalActuante);
		setPacienteInternado(false);
	}

	public Episodio(Episodio episodio) {
		setFechaRealizado(episodio.getFechaRealizado());
		setPaciente(episodio.getPaciente());
		setInformeInstitucional(episodio.getInformeInstitucional());
		setNroEpisodio(episodio.getNroEpisodio());
		setProducto_OSPaciente(episodio.getProducto_OSPaciente());
		setPacienteInternado(episodio.getPacienteInternado());
		if (episodio.getPacienteInternado()) {
			setNroCamaPacienteInternado(episodio.getNroCamaPacienteInternado());
		}
		setPrestaciones(episodio.getPrestaciones());
		setProfesionalActuante(episodio.getProfesionalActuante());
		setProfesionalSolicitante(episodio.getProfesionalSolicitante());
		setTipoProfesional(episodio.getTipoProfesional());
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
		if (objeto instanceof Episodio) {
			Episodio o = (Episodio) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public ProductoObraSocialPaciente getProducto_OSPaciente() {
		return producto_OSPaciente;
	}

	public void setProducto_OSPaciente(
			ProductoObraSocialPaciente producto_OSPaciente) {
		this.producto_OSPaciente = producto_OSPaciente;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getFechaRealizado() {
		return fechaRealizado;
	}

	public void setFechaRealizado(Date fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}

	public String getInformeInstitucional() {
		return informeInstitucional;
	}

	public void setInformeInstitucional(String informeInstitucional) {
		this.informeInstitucional = informeInstitucional;
	}

	public Profesional getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Profesional profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Rol getProfesionalActuante() {
		return profesionalActuante;
	}

	public void setProfesionalActuante(Rol profesionalActuante) {
		this.profesionalActuante = profesionalActuante;
	}

	public Boolean getPacienteInternado() {
		return pacienteInternado;
	}

	public void setPacienteInternado(Boolean pacienteInternado) {
		this.pacienteInternado = pacienteInternado;
	}

	public String getTipoProfesional() {
		return tipoProfesional;
	}

	public void setTipoProfesional(String tipoProfesional) {
		this.tipoProfesional = tipoProfesional;
	}

	@Override
	public String toString() {
		return "Paciente: " + getPaciente() + ", Prof. Solic: "
				+ getProfesionalSolicitante() + ", Prof. Act.: "
				+ getProfesionalActuante();
	}

	public String getNroEpisodio() {
		return nroEpisodio;
	}

	public void setNroEpisodio(String nroEpisodio) {
		this.nroEpisodio = nroEpisodio;
	}

	public Integer getNroCamaPacienteInternado() {
		return nroCamaPacienteInternado;
	}

	public void setNroCamaPacienteInternado(Integer nroCamaPacienteInternado) {
		this.nroCamaPacienteInternado = nroCamaPacienteInternado;
	}

	public List<Prestacion> getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(List<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}

	public void internarEnCama(Integer nroCama) {
		this.setPacienteInternado(true);
		this.setNroCamaPacienteInternado(nroCama);
	}

	/**
	 * Devuelve la cantidad de prestaciones que involucra este episodio
	 */
	public Integer getCantidad() {
		return this.getPrestaciones().size();
	}

	public String getClaseConsumo() {
		return Episodio.class.getCanonicalName();
	}

	public String getDescripcion() {
		String desc = "El paciente " + this.getPaciente().toString();
		desc += " se realiz� el "
				+ new SimpleDateFormat("dd/MM/yy - HH:mm").format(this
						.getFechaRealizado());

		//
		if (this.getProfesionalSolicitante() != null) {
			desc += " bajo la solicitud del profesional "
					+ this.getProfesionalSolicitante().toString();
		}

		// Listo las prestaciones
		desc += ", las siguientes prestaciones: " + " \n ";
		for (Integer i = 0; i < this.getPrestaciones().size(); i++) {
			desc += i + ") "
					+ this.getPrestaciones().get(i.intValue()).toString()
					+ " \n ";
		}

		// Si o si alguien registro este episodio
		desc += " realizadas por " + this.getProfesionalActuante().toString();

		this.setDescripcion(desc);

		return desc;
	}

	/**
	 * Devuelve la fecha en la que se aplico el precio
	 */
	public Date getFechaPrecio() {
		return new Date();
	}

	public Float getIVA() {
		return null;
	}

	public Long getIdConsumo() {
		return this.getId();
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Devuelve el precio que valdria uno de estos consumos
	 */
	public Float getPrecioUnitario() {
		return new Float(0);
	}

	public void setCantidad(Integer cantidadNueva) {
		// TODO Auto-generated method stub

	}

	public void setDescripcion(String descripcionNueva) {
		// TODO Auto-generated method stub

	}

	public void setFechaPrecio(Date nuevaFechaPrecio) {
		// TODO Auto-generated method stub

	}

	public void setIVA(Float nuevoIVA) {
		// TODO Auto-generated method stub

	}

	public void setNombre(String nombreNuevo) {
		// TODO Auto-generated method stub

	}

	public void setPrecioUnitario(Float precioNuevo) {
		// TODO Auto-generated method stub

	}

	public Episodio_VO toValueObject() {
		return new Episodio_VO(this);
	}

	public Episodio_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Episodio_VO(this, profundidadActual, profundidadDeseada);
	}

	@Override
	public String getDocumentoT() {
		return getPaciente().getUsuario().getNroDocumento();
	}

	@Override
	public String getEstudioT() {
		String practicas = getPrestaciones().toString();
		practicas = practicas.substring(1, practicas.length() - 1);
		return practicas;
	}

	@Override
	public String getFechaT() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(getFechaRealizado());
	}

	@Override
	public String getInformeT() {
		return getInformeInstitucional();
	}

	@Override
	public String getMedicoT() {
		return getProfesionalActuante().getUsuario().getApellido() + ", "
				+ getProfesionalActuante().getUsuario().getNombres();
	}

	@Override
	public String getPacienteT() {
		return getPaciente().getUsuario().getApellido() + ", "
				+ getPaciente().getUsuario().getNombres();
	}

	@Override
	public String getUbicacionT() {
		if (getPacienteInternado()) {
			return "Cama: " + getNroCamaPacienteInternado();
		} else {
			return "Ambulatorio";
		}
	}

	public Long getId_episodioOrigen() {
		return id_episodioOrigen;
	}

	public void setId_episodioOrigen(Long id_episodioOrigen) {
		this.id_episodioOrigen = id_episodioOrigen;
	}
	
	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

}