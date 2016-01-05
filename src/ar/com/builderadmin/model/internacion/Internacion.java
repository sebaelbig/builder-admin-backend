package ar.com.builderadmin.model.internacion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.datosSalud.Modulo;
import ar.com.builderadmin.model.core.datosSalud.diagnosticos.Diagnostico;
import ar.com.builderadmin.model.core.obrasSociales.Producto_OS;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.Profesional;
import ar.com.builderadmin.model.historiaClinica.episodios.Episodio;
import ar.com.builderadmin.model.internacion.altas.AltaInternacion;
import ar.com.builderadmin.vo.internacion.Internacion_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
@Entity @Table
public class Internacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_internacion", name = "seq_internacion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_internacion")
	private Long id;

	private Long numero;

	@Version
	private Integer version;

	/**
	 * Datos del alquiler
	 */
	@Column(name = "fecha_asignacion")
	@Temporal(TemporalType.DATE)
	private Date fechaAsignacion;

	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Column(name = "hora_inicio")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;

	@Column(name = "cant_hora_inicio")
	private Integer cantHoraInicio;

	@Column(name = "cant_hora_fin")
	private Integer cantHoraFin;

	@Column(name = "hora_fin")
	@Temporal(TemporalType.TIME)
	private Date horaFin;

	/**
	 * Episodios asociados a una internacion
	 */
	@OneToMany
	private List<Episodio> episodios;

	/**
	 * Modulo asignado a esta internacion
	 */
	@ManyToOne
	@JoinColumn(name = "id_modulo")
	private Modulo modulo;

	/**
	 * Modulo asignado a esta internacion
	 */
	@ManyToMany
	@JoinColumn(name = "id_diagnostico")
	private List<Diagnostico> diagnosticos;

	@Column(columnDefinition = "text")
	private String tratamiento;

	@Column(name = "tipo_internacion")
	private String tipoInternacion;

	@Column(name = "motivo_internacion")
	private String motivoInternacion;

	/**
	 * Producto de la obra social
	 */
	@ManyToOne
	@JoinColumn(name = "id_producto_os")
	private Producto_OS productoOS;

	/**
	 * Paciente al cual perteneciente a la intarnacion
	 */
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	/**
	 * Profesional que solicita la internacion
	 */
	@ManyToOne
	@JoinColumn(name = "id_profesional_solicitante")
	private Profesional profesionalSolicitante;

	/**
	 * Profesional cirujano que interviene en la internacion
	 */
	@ManyToOne
	@JoinColumn(name = "id_profesional_cirujano")
	private Profesional profesionalCirujano;

	/**
	 * Profesional que deriva la internacion
	 */
	@ManyToOne
	@JoinColumn(name = "id_profesional_derivacion")
	private Profesional profesionalDerivacion;

	/**
	 * Motivo de la alta de la internacion
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_alta_internacion")
	private AltaInternacion altaInternacion;

	@Column(name = "dado_de_alta")
	private Boolean dadoDeAlta = false;

	@Column(name = "cierre_administrativo")
	private Boolean cierreAdministrativo = false;

	/**
	 * Reservas que intervinieron en la internacion
	 */
	@OneToMany(mappedBy = "internacion")
	private List<Reserva> reservas;

	/**
	 * Atributo que indica si la internacion ya fue tramitada en administracion
	 */
	private Boolean activo = true;

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	// Constructores
	public Internacion() {
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Internacion) {
			Internacion o = (Internacion) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		this.setNumero(id);
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getCantHoraInicio() {
		return cantHoraInicio;
	}

	public void setCantHoraInicio(Integer cantHoraInicio) {
		this.cantHoraInicio = cantHoraInicio;
	}

	public Integer getCantHoraFin() {
		return cantHoraFin;
	}

	public void setCantHoraFin(Integer cantHoraFin) {
		this.cantHoraFin = cantHoraFin;
	}

	public boolean igualA(Internacion reserva) {

		return (reserva != null)
				&& // Hota Inicio
				(this.getHoraInicio().compareTo(reserva.getHoraInicio()) == 0)
				&& // Hora Fin
				(this.getHoraFin().compareTo(reserva.getHoraFin()) == 0)
		// && //Internacion
		// (this.getEspecialidadPrestada().getEspecialidad().getNombre().equals(reserva.getEspecialidadPrestada().getEspecialidad().getNombre()))
		;

	}

	public Float horasOcupadas() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getHoraFin());

		Calendar aux = Calendar.getInstance();
		aux.setTime(getHoraInicio());
		int hora = aux.get(Calendar.HOUR_OF_DAY);
		int min = aux.get(Calendar.MINUTE);

		cal.add(Calendar.HOUR_OF_DAY, -hora);
		cal.add(Calendar.MINUTE, -min);

		hora = cal.get(Calendar.HOUR_OF_DAY);
		min = cal.get(Calendar.MINUTE);

		return new Float(hora + min * 0.1);
	}

	public boolean intersectaCon(Internacion reserva) {

		return (reserva.getHoraInicio().compareTo(this.getHoraInicio()) >= 0 && reserva
				.getHoraInicio().compareTo(this.getHoraFin()) < 0)
				|| (reserva.getHoraFin().compareTo(this.getHoraInicio()) > 0 && reserva
						.getHoraFin().compareTo(this.getHoraFin()) <= 0);
	}

	public Internacion_VO toValueObject() {
		return new Internacion_VO(this);
	}

	public Internacion_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Internacion_VO(this, profundidadActual, profundidadDeseada);
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Producto_OS getProductoOS() {
		return productoOS;
	}

	public void setProductoOS(Producto_OS productoOS) {
		this.productoOS = productoOS;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public AltaInternacion getAltaInternacion() {
		return altaInternacion;
	}

	public void setAltaInternacion(AltaInternacion altaInternacion) {
		this.altaInternacion = altaInternacion;
	}

	public Boolean getCierreAdministrativo() {
		return cierreAdministrativo;
	}

	public void setCierreAdministrativo(Boolean cierreAdministrativo) {
		this.cierreAdministrativo = cierreAdministrativo;
	}

	public Boolean getDadoDeAlta() {
		return dadoDeAlta;
	}

	public void setDadoDeAlta(Boolean dadoDeAlta) {
		this.dadoDeAlta = dadoDeAlta;
	}

	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public Profesional getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Profesional profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Profesional getProfesionalCirujano() {
		return profesionalCirujano;
	}

	public void setProfesionalCirujano(Profesional profesionalCirujano) {
		this.profesionalCirujano = profesionalCirujano;
	}

	public Profesional getProfesionalDerivacion() {
		return profesionalDerivacion;
	}

	public void setProfesionalDerivacion(Profesional profesionalDerivacion) {
		this.profesionalDerivacion = profesionalDerivacion;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getTipoInternacion() {
		return tipoInternacion;
	}

	public void setTipoInternacion(String tipoInternacion) {
		this.tipoInternacion = tipoInternacion;
	}

	public String getMotivoInternacion() {
		return motivoInternacion;
	}

	public void setMotivoInternacion(String motivoInternacion) {
		this.motivoInternacion = motivoInternacion;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

}