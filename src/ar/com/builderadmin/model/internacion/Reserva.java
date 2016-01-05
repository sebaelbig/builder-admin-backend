package ar.com.builderadmin.model.internacion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
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

import ar.com.builderadmin.model.internacion.habitaciones.camas.AgendaCama;
import ar.com.builderadmin.vo.internacion.Reserva_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
@Entity @Table
public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_reserva", name = "seq_reserva", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva")
	private Long id;
	
	@Version
	private Integer version;

	/**
	 * Datos del alquiler
	 */
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

	@Column(name = "hora_fin")
	@Temporal(TemporalType.TIME)
	private Date horaFin;
	
	@Column(name = "cant_hora_inicio")
	private Integer cantHoraInicio;
	
	@Column(name = "cant_hora_fin")
	private Integer cantHoraFin;
	
	@Column(name = "cant_medias_horas")
	private Integer cantMediasHoras;
	
	@ManyToOne
	@JoinColumn(name = "id_agenda_cama")
	private AgendaCama agendaCama;

	@ManyToOne
	@JoinColumn(name = "id_internacion")
	private Internacion internacion;

	private Boolean activo = true;

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	// Constructores
	public Reserva() {
		this.setFechaAsignacion(new Date());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Reserva) {
			Reserva o = (Reserva) objeto;
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

	public boolean igualA(Reserva reserva) {

		return (reserva != null)
				&& // Hota Inicio
				(this.getHoraInicio().compareTo(reserva.getHoraInicio()) == 0)
				&& // Hora Fin
				(this.getHoraFin().compareTo(reserva.getHoraFin()) == 0)
		// && //Internacion
		// (this.getEspecialidadPrestada().getEspecialidad().getNombre().equals(reserva.getEspecialidadPrestada().getEspecialidad().getNombre()))
		;

	}

	/**
	 * Calcula las medias horas ocupadas por la reserva
	 * 
	 * @return
	 */
	public long mediasHorasOcupadas() {
		Calendar calInicio = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		Calendar calTemp = Calendar.getInstance();

		// Seteo la fechaInicio, y la horaInicio
		calInicio.setTime(getFechaInicio());
		calTemp.setTime(getHoraInicio());
		calInicio.set(Calendar.HOUR_OF_DAY, calTemp.get(Calendar.HOUR_OF_DAY));
		calInicio.set(Calendar.MINUTE, calTemp.get(Calendar.MINUTE));
		calInicio.set(Calendar.SECOND, calTemp.get(Calendar.SECOND));
		calInicio.set(Calendar.MILLISECOND, calTemp.get(Calendar.MILLISECOND));

		// Seteo la fechaFin, y la horaFin
		calFin.setTime(getFechaFin());
		calTemp.setTime(getHoraFin());
		calFin.set(Calendar.HOUR_OF_DAY, calTemp.get(Calendar.HOUR_OF_DAY));
		calFin.set(Calendar.MINUTE, calTemp.get(Calendar.MINUTE));
		calFin.set(Calendar.SECOND, calTemp.get(Calendar.SECOND));
		calFin.set(Calendar.MILLISECOND, calTemp.get(Calendar.MILLISECOND));

		long milisecsTotales = calFin.getTimeInMillis()
				- calInicio.getTimeInMillis();
		long minutosTotales = Math.round(milisecsTotales / 60.000);

		return Math.round(minutosTotales / 30);
	}

	public boolean intersectaCon(Reserva reserva) {

		return (reserva.getHoraInicio().compareTo(this.getHoraInicio()) >= 0 && reserva
				.getHoraInicio().compareTo(this.getHoraFin()) < 0)
				|| (reserva.getHoraFin().compareTo(this.getHoraInicio()) > 0 && reserva
						.getHoraFin().compareTo(this.getHoraFin()) <= 0);
	}

	public Reserva_VO toValueObject() {
		return new Reserva_VO(this);
	}

	public Reserva_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Reserva_VO(this, profundidadActual, profundidadDeseada);
	}

	public Internacion getInternacion() {
		return internacion;
	}

	public void setInternacion(Internacion internacion) {
		this.internacion = internacion;
	}

	public AgendaCama getAgendaCama() {
		return agendaCama;
	}

	public void setAgendaCama(AgendaCama agendaCama) {
		this.agendaCama = agendaCama;
	}

	public Integer getCantMediasHoras() {
		return cantMediasHoras;
	}

	public void setCantMediasHoras(Integer cantMediasHoras) {
		this.cantMediasHoras = cantMediasHoras;
	}

	public void clonar(Reserva reservaDestino) {
		reservaDestino.setId(this.getId());
		reservaDestino.setVersion(this.getVersion());
		reservaDestino.setActivo(this.getActivo());
		reservaDestino.setCantHoraFin(this.getCantHoraFin());
		reservaDestino.setCantHoraInicio(this.getCantHoraInicio());
		reservaDestino.setFechaFin(this.getFechaFin());
		reservaDestino.setFechaInicio(this.getFechaInicio());
		reservaDestino.setHoraFin(this.getHoraFin());
		reservaDestino.setHoraInicio(this.getHoraInicio());
		reservaDestino.setFechaAsignacion(this.getFechaAsignacion());
	}

}