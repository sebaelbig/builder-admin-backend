package ar.com.builderadmin.model.turnos;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.areas.servicios.Consultorio;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.EspecialidadProfesional;
import ar.com.builderadmin.model.turnos.agenda.Dia;
import ar.com.builderadmin.model.turnos.repeticiones.RepeticionBloqueTurno;
import ar.com.builderadmin.model.turnos.repeticiones.RepeticionBloqueTurnoSemanal;
import ar.com.builderadmin.vo.turnos.BloqueTurno_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
@Entity @Table( name = "bloque_turno")
public class BloqueTurno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_bloque_turno", name = "seq_bloque_turno", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bloque_turno")
	private Long id;
	
	@Version
	private Integer version;

	/**
	 * Datos del alquiler
	 */
	@Column(name = "fecha_asignacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAsignacion;

	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@Column(name = "cant_hora_inicio")
	private Integer cantHoraInicio;

	@Column(name = "cant_hora_fin")
	private Integer cantHoraFin;

	@ManyToOne
	@JoinColumn(name = "id_consultorio")
	private Consultorio consultorio;

	/**
	 * Datos del bloque turno
	 */
	@Column(name = "admite_sobre_turno")
	private Boolean admiteSobreturno;

	@Column(name = "por_orden_de_llegada")
	private Boolean porOrdenDeLlegada;

	@Column(name = "mostrar_aviso")
	private Boolean mostrarAviso;

	@Column(name = "mensaje_aviso", length = 500)
	private String mensajeAviso;

	@Column(name = "restringir_reserva_turnos")
	private Boolean restringirReservaTurnos;

	@Column(name = "lapso_restriccion_reservar_turnos")
	private Integer lapsoRestriccionReservarTurnos;

	@Column(name = "cantidad_turnos")
	private Integer cantidadTurnos;

	@Column(name = "duracion_primer_turno")
	private Integer duracionPrimerTurno;

	@Column(name = "duracion_turno")
	private Integer duracionTurno;

	@Column(name = "hora_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;

	@Column(name = "hora_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFin;

	@ManyToOne
	@JoinColumn(name = "id_especialidad_prestada")
	private EspecialidadProfesional especialidadPrestada;

	@OneToOne
	@JoinColumn(name = "id_dia")
	private Dia dia;

	@OneToOne(mappedBy = "bloqueTurno")
	@JoinColumn(name = "id_repeticion")
	private RepeticionBloqueTurno repeticion;

	private Boolean activo = true;

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	// Constructores
	public BloqueTurno() {
		setCantidadTurnos(0);
	}

	public BloqueTurno(Dia dia) {
		setDia(dia);
		setCantidadTurnos(0);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof BloqueTurno) {
			BloqueTurno o = (BloqueTurno) objeto;
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

	public Boolean getAdmiteSobreturno() {
		return admiteSobreturno;
	}

	public void setAdmiteSobreturno(Boolean admiteSobreturno) {
		this.admiteSobreturno = admiteSobreturno;
	}

	public Boolean getPorOrdenDeLlegada() {
		return porOrdenDeLlegada;
	}

	public void setPorOrdenDeLlegada(Boolean porOrdenDeLlegada) {
		this.porOrdenDeLlegada = porOrdenDeLlegada;
	}

	public Integer getDuracionPrimerTurno() {
		return duracionPrimerTurno;
	}

	public Integer getDuracionTurno() {
		return duracionTurno;
	}

	public void setDuracionTurno(Integer duracionTurno) {
		this.duracionTurno = duracionTurno;
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

	public EspecialidadProfesional getEspecialidadPrestada() {
		return especialidadPrestada;
	}

	public void setEspecialidadPrestada(EspecialidadProfesional profesional) {
		this.especialidadPrestada = profesional;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public void setDuracionPrimerTurno(Integer duracionPrimerTurno) {
		this.duracionPrimerTurno = duracionPrimerTurno;
	}

	public Integer getCantidadTurnos() {
		return cantidadTurnos;
	}

	public void setCantidadTurnos(Integer cantidadTurnos) {
		this.cantidadTurnos = cantidadTurnos;
	}

	public RepeticionBloqueTurno getRepeticion() {
		return repeticion;
	}

	public void setRepeticion(RepeticionBloqueTurno repeticion) {
		this.repeticion = repeticion;
	}

	public Boolean getMostrarAviso() {
		return mostrarAviso;
	}

	public void setMostrarAviso(Boolean mostrarAviso) {
		this.mostrarAviso = mostrarAviso;
	}

	public String getMensajeAviso() {
		return mensajeAviso;
	}

	public void setMensajeAviso(String mensajeAviso) {
		this.mensajeAviso = mensajeAviso;
	}

	public Integer getLapsoRestriccionReservarTurnos() {
		return lapsoRestriccionReservarTurnos;
	}

	public void setLapsoRestriccionReservarTurnos(
			Integer lapsoRestriccionReservarTurnos) {
		this.lapsoRestriccionReservarTurnos = lapsoRestriccionReservarTurnos;
	}

	public Boolean getRestringirReservaTurnos() {
		return restringirReservaTurnos;
	}

	public void setRestringirReservaTurnos(Boolean restringirReservaTurnos) {
		this.restringirReservaTurnos = restringirReservaTurnos;
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

	public boolean igualA(BloqueTurno bloqueTurno) {

		return (bloqueTurno != null)
				&& // Hota Inicio
				(this.getHoraInicio().compareTo(bloqueTurno.getHoraInicio()) == 0)
				&& // Hora Fin
				(this.getHoraFin().compareTo(bloqueTurno.getHoraFin()) == 0)
				&& // Especialidad
				(this.getEspecialidadPrestada().getEspecialidad().getNombre()
						.equals(bloqueTurno.getEspecialidadPrestada()
								.getEspecialidad().getNombre()));

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

	public Date fechaSiguiente(Date fechaPuntero) {
		if (getRepeticion() == null) {
			setRepeticion(new RepeticionBloqueTurnoSemanal());
			getRepeticion().setBloqueTurno(this);
		}
		Date resul = fechaPuntero;
		resul = getRepeticion().obtenerFechaSiguiente(fechaPuntero);
		return resul;
	}

	public Date fechaAnterior(Date fechaPuntero) {
		if (getRepeticion() == null) {
			setRepeticion(new RepeticionBloqueTurnoSemanal());
			getRepeticion().setBloqueTurno(this);
		}
		Date resul = fechaPuntero;
		resul = getRepeticion().obtenerFechaAnterior(fechaPuntero);
		return resul;
	}

	public boolean intersectaCon(BloqueTurno bloqueTurno) {

		return (bloqueTurno.getHoraInicio().compareTo(this.getHoraInicio()) >= 0 && bloqueTurno
				.getHoraInicio().compareTo(this.getHoraFin()) < 0)
				|| (bloqueTurno.getHoraFin().compareTo(this.getHoraInicio()) > 0 && bloqueTurno
						.getHoraFin().compareTo(this.getHoraFin()) <= 0);
	}

	/**
	 * Me devuelve una fecha valida para este bloque turno. Si la fecha calza en
	 * un dia habil para este bloque turno, devuelve la misma, sino se calcula
	 * la siguiente
	 * 
	 * @param fechaPuntero
	 * @return
	 */
	public Date getFechaSiguiente(Date fechaPuntero) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPuntero);

		Date resul = fechaPuntero;

		// Si el dia de la fecha que llega NO coincide con la del bt, debo
		// obtener la siguiente
		if (cal.get(Calendar.DAY_OF_WEEK) != this.getDia().getNumero_semana()) {

			resul = this.obtenerFechaSiguiente(fechaPuntero);

		}

		return resul;
	}

	/**
	 * Devuelve la fecha mas cercana hacia al futuro dentro de las del bloque
	 * turno
	 * 
	 * @param bloqueTurno
	 * @param fechaPuntero
	 * @return
	 */
	public Date obtenerFechaSiguiente(Date fechaPuntero) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPuntero);

		// Me posiciono en el dia que corresponde al bloque turno
		while (cal.get(Calendar.DAY_OF_WEEK) != this.getDia()
				.getNumero_semana()) {
			cal.add(Calendar.DAY_OF_WEEK, 1);
		}

		Calendar cal_bt = Calendar.getInstance();
		cal_bt.setTime(this.getHoraFin());

		int hora_fechaPunt = cal.get(Calendar.HOUR_OF_DAY), min_fechaPunt = cal
				.get(Calendar.MINUTE), hora_f_bt = cal_bt
				.get(Calendar.HOUR_OF_DAY), min_f_bt = cal_bt
				.get(Calendar.MINUTE);

		// Si estamos antes del cierre de atencion, se tiene que devolver ese
		// dia
		if ((hora_fechaPunt < hora_f_bt)
				|| ((hora_fechaPunt == hora_f_bt) && (min_fechaPunt < min_f_bt))) {

			// Establezco la hora final para que la proxima vez q se pida el
			// siguiente dia quede fuera
			cal.set(Calendar.HOUR_OF_DAY, hora_f_bt);
			cal.set(Calendar.MINUTE, min_f_bt);

		} else {

			// Ya no hay tiempo de atenderse, si estamos parados en el mismo
			// dia, le sumo la cantidad de dias que tenga la frecuencia
			Calendar cal_Punta = Calendar.getInstance();
			cal_Punta.setTime(fechaPuntero);

			if (cal.get(Calendar.DAY_OF_YEAR) == cal_Punta
					.get(Calendar.DAY_OF_YEAR)) {
				cal.add(Calendar.DAY_OF_WEEK, 7);
			}

		}

		Date resul = null;

		// Si la nueva fecha es mas futura que el fin del contrato, no se toma
		if (!cal.getTime().after(this.getFechaFin())) {
			resul = cal.getTime();
		}

		return resul;
	}

	public BloqueTurno_VO toValueObject() {
		return new BloqueTurno_VO(this);
	}

	public BloqueTurno_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new BloqueTurno_VO(this, profundidadActual, profundidadDeseada);
	}

	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}

}