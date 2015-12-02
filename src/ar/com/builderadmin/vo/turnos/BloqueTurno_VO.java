package ar.com.builderadmin.vo.turnos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ar.com.builderadmin.model.turnos.BloqueTurno;
import ar.com.builderadmin.utils.VariablesGlobales;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Consultorio_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.EspecialidadProfesional_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.Profesional_VO;
import ar.com.builderadmin.vo.turnos.agenda.Dia_VO;

public class BloqueTurno_VO implements Comparable<BloqueTurno_VO>,
		Serializable, I_ValueObject<BloqueTurno> {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	private Boolean borrado = false;

	private Integer version;

	private Boolean admiteSobreturno;

	private Boolean porOrdenDeLlegada;

	private Boolean aceptaListaDeEspera;

	private Boolean mostrarAviso;
	private String mensajeAviso;

	private Boolean restringirReservaTurnos;
	private Integer lapsoRestriccionReservarTurnos;

	private Integer cantidadTurnos;

	private Integer duracionPrimerTurno;
	private Integer duracionTurno;

	private String nombreDia;
	private int numero_semana;

	private Date fechaInicio;
	private Date fechaFin;
	private String str_fechaInicio;
	private String str_fechaFin;

	private Date horaInicio;
	private Date horaFin;
	private String str_horaInicio;
	private String str_horaFin;

	private Integer cantHoraInicio;
	private Integer cantHoraFin;

	private Date diaProximoTurno;
	private String str_diaProximoTurno;
	
	private Date diaPrimerTurnoLibre;
	private String str_diaPrimerTurnoLibre;
	private Date horaPrimerTurnoLibre;
	private String str_horaPrimerTurnoLibre;
	private String str_horaPrimerTurno;

	private EspecialidadProfesional_VO especialidadPrestada;

	private Profesional_VO profesional;
	private Integer matricula;
	private String apellido;

	private String nombreProfesional;

	private String servicio;

	private Dia_VO dia;

	private Consultorio_VO consultorio;

	private Boolean activo = true;
	private Integer numeroTurno;

	public BloqueTurno_VO(BloqueTurno bt) {
		this.setObject(bt);
	}

	public BloqueTurno_VO(BloqueTurno bt, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(bt, profundidadActual, profundidadDeseada);
	}

	public BloqueTurno_VO() {
	}

	@Override
	public void setObject(BloqueTurno bt) {
		this.setActivo(bt.getActivo());
		this.setAdmiteSobreturno(bt.getAdmiteSobreturno());
		this.setCantidadTurnos(bt.getCantidadTurnos());
		this.setDuracionPrimerTurno(bt.getDuracionPrimerTurno());
		this.setDuracionTurno(bt.getDuracionTurno());
		this.setCantHoraInicio(bt.getCantHoraInicio());
		this.setCantHoraFin(bt.getCantHoraFin());
		this.setHoraInicio(bt.getHoraInicio());
		this.setHoraFin(bt.getHoraFin());
		this.setFechaFin(bt.getFechaFin());
		this.setFechaInicio(bt.getFechaInicio());
		this.setId(bt.getId());
		this.setVersion(bt.getVersion());
		this.setLapsoRestriccionReservarTurnos(bt
				.getLapsoRestriccionReservarTurnos());
		this.setMensajeAviso(bt.getMensajeAviso());
		this.setMostrarAviso(bt.getMostrarAviso());
		this.setPorOrdenDeLlegada(bt.getPorOrdenDeLlegada());
		this.setRestringirReservaTurnos(bt.getRestringirReservaTurnos());

		// Para arriba las relaciones son planas
		if (bt.getDia() != null) {
			this.setNombreDia(bt.getDia().getNombre());
			this.setNumero_semana(bt.getDia().getNumero_semana());
			this.setDia(bt.getDia().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
		this.setEspecialidadPrestada(bt.getEspecialidadPrestada()
				.toValueObject());
		this.setConsultorio(bt.getConsultorio().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setProfesional(bt.getEspecialidadPrestada()
				.getContratoProfesional().getProfesional()
				.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setNombreProfesional(bt.getEspecialidadPrestada()
				.getContratoProfesional().getProfesional().getUsuario()
				.toString());
	}

	@Override
	public void setObject(BloqueTurno bt, int profundidadActual,
			int profundidadDeseada) {
		this.setActivo(bt.getActivo());
		this.setAdmiteSobreturno(bt.getAdmiteSobreturno());
		this.setCantidadTurnos(bt.getCantidadTurnos());
		this.setDuracionPrimerTurno(bt.getDuracionPrimerTurno());
		this.setDuracionTurno(bt.getDuracionTurno());
		this.setHoraInicio(bt.getHoraInicio());
		this.setHoraFin(bt.getHoraFin());
		this.setFechaFin(bt.getFechaFin());
		this.setFechaInicio(bt.getFechaInicio());
		this.setCantHoraInicio(bt.getCantHoraInicio());
		this.setCantHoraFin(bt.getCantHoraFin());
		this.setId(bt.getId());
		this.setVersion(bt.getVersion());
		this.setLapsoRestriccionReservarTurnos(bt
				.getLapsoRestriccionReservarTurnos());
		this.setMensajeAviso(bt.getMensajeAviso());
		this.setMostrarAviso(bt.getMostrarAviso());
		this.setPorOrdenDeLlegada(bt.getPorOrdenDeLlegada());
		this.setRestringirReservaTurnos(bt.getRestringirReservaTurnos());
		if (bt.getDia() != null) {
			this.setNombreDia(bt.getDia().getNombre());
			this.setNumero_semana(bt.getDia().getNumero_semana());
		}

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			if (bt.getDia() != null)
				this.setDia(bt.getDia().toValueObject(profundidadActual + 1,
						profundidadDeseada));
			this.setEspecialidadPrestada(bt.getEspecialidadPrestada()
					.toValueObject(profundidadActual + 1, profundidadDeseada));
			this.setConsultorio(bt.getConsultorio().toValueObject(
					profundidadActual + 1, profundidadDeseada));

		}

	}

	@Override
	public BloqueTurno toObject() {
		BloqueTurno bt = new BloqueTurno();

		bt.setActivo(this.getActivo());
		bt.setAdmiteSobreturno(this.getAdmiteSobreturno());
		bt.setCantidadTurnos(this.getCantidadTurnos());
		bt.setDuracionPrimerTurno(this.getDuracionPrimerTurno());
		bt.setDuracionTurno(this.getDuracionTurno());
		bt.setHoraInicio(this.getHoraInicio());
		bt.setHoraFin(this.getHoraFin());
		bt.setCantHoraInicio(this.getCantHoraInicio());
		bt.setCantHoraFin(this.getCantHoraFin());
		bt.setFechaInicio(this.getFechaInicio());
		bt.setFechaFin(this.getFechaFin());
		bt.setId(this.getId());
		bt.setVersion(this.getVersion());
		bt.setLapsoRestriccionReservarTurnos(this
				.getLapsoRestriccionReservarTurnos());
		bt.setMensajeAviso(this.getMensajeAviso());
		bt.setMostrarAviso(this.getMostrarAviso());
		bt.setPorOrdenDeLlegada(this.getPorOrdenDeLlegada());
		bt.setRestringirReservaTurnos(this.getRestringirReservaTurnos());

		if (this.getDia() != null)
			bt.setDia(this.getDia().toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		bt.setEspecialidadPrestada(this.getEspecialidadPrestada().toObject());
		if (this.getConsultorio()!=null)
			bt.setConsultorio(this.getConsultorio().toObject());

		return bt;
	}

	// public boolean igualA(BloqueTurno bloqueTurno) {
	// SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	// try {
	// return (bloqueTurno != null)
	// && //Hota Inicio
	// (bloqueTurno.getHoraInicio().compareTo(format.parse(this.getHoraInicio()))
	// == 0)
	// && //Hora Fin
	// (bloqueTurno.getHoraFin().compareTo(format.parse(this.getHoraInicio()))
	// == 0)
	// && //Especialidad
	// (bloqueTurno.getEspecialidadPrestada().getEspecialidad().getNombre().equals(this.getEspecialidad()));
	//
	// } catch (ParseException e) {
	// return false;
	// }
	// }

	// public boolean equals(BloqueTurno_VO btR){
	// return btR.getId().equals(this.getId());
	// }

	@Override
	public int compareTo(BloqueTurno_VO bt) {
		return 0;
	}

	// DateFormat format_d = new SimpleDateFormat("dd/MM/yy");
	// Date primerTurno = null, primerTurnoO = null;
	// try {
	// primerTurno = format_d.parse(bt.getPrimerTurnoLibre());
	// primerTurnoO = format_d.parse(bt.getPrimerTurnoLibre());
	// } catch (ParseException e) {e.printStackTrace();}
	//
	// int comp = primerTurno.compareTo(primerTurnoO);
	//
	// if (comp==0){
	// //Chequeo la hora. ya que los dias son iguales
	// SimpleDateFormat format_h = new SimpleDateFormat("HH:mm");
	// Date primerTurno_h = null, primerTurnoO_h = null;
	// try {
	// primerTurno_h = format_h.parse(this.getHoraInicio());
	// primerTurnoO_h = format_h.parse(bt.getHoraInicio());
	//
	// comp = primerTurno_h.compareTo(primerTurnoO_h);
	//
	// } catch (ParseException e) {e.printStackTrace();}
	// }
	//
	// return comp;
	// }

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

	public Boolean getRestringirReservaTurnos() {
		return restringirReservaTurnos;
	}

	public void setRestringirReservaTurnos(Boolean restringirReservaTurnos) {
		this.restringirReservaTurnos = restringirReservaTurnos;
	}

	public Integer getLapsoRestriccionReservarTurnos() {
		return lapsoRestriccionReservarTurnos;
	}

	public void setLapsoRestriccionReservarTurnos(
			Integer lapsoRestriccionReservarTurnos) {
		this.lapsoRestriccionReservarTurnos = lapsoRestriccionReservarTurnos;
	}

	public Integer getCantidadTurnos() {
		return cantidadTurnos;
	}

	public void setCantidadTurnos(Integer cantidadTurnos) {
		this.cantidadTurnos = cantidadTurnos;
	}

	public Integer getDuracionPrimerTurno() {
		return duracionPrimerTurno;
	}

	public void setDuracionPrimerTurno(Integer duracionPrimerTurno) {
		this.duracionPrimerTurno = duracionPrimerTurno;
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
		if (horaFin != null) {
			this.setStr_horaFin(new SimpleDateFormat("HH:mm").format(horaFin));
		}
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
		if (horaInicio != null) {
			this.setStr_horaInicio(new SimpleDateFormat("HH:mm")
					.format(horaInicio));
		}
	}

	public EspecialidadProfesional_VO getEspecialidadPrestada() {
		return especialidadPrestada;
	}

	public void setEspecialidadPrestada(
			EspecialidadProfesional_VO especialidadPrestada) {
		this.especialidadPrestada = especialidadPrestada;
	}

	public Dia_VO getDia() {
		return dia;
	}

	public void setDia(Dia_VO dia) {
		this.dia = dia;
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

	public BloqueTurno toObject(int profundidadActual, int profundidadDeseada) {
		BloqueTurno bt = new BloqueTurno();

		bt.setActivo(this.getActivo());
		bt.setAdmiteSobreturno(this.getAdmiteSobreturno());
		bt.setCantidadTurnos(this.getCantidadTurnos());
		bt.setDuracionPrimerTurno(this.getDuracionPrimerTurno());
		bt.setDuracionTurno(this.getDuracionTurno());
		bt.setHoraInicio(this.getHoraInicio());
		bt.setCantHoraInicio(this.getCantHoraInicio());
		bt.setCantHoraFin(this.getCantHoraFin());
		bt.setHoraFin(this.getHoraFin());
		bt.setId(this.getId());
		bt.setVersion(this.getVersion());
		bt.setLapsoRestriccionReservarTurnos(this
				.getLapsoRestriccionReservarTurnos());
		bt.setMensajeAviso(this.getMensajeAviso());
		bt.setMostrarAviso(this.getMostrarAviso());
		bt.setPorOrdenDeLlegada(this.getPorOrdenDeLlegada());
		bt.setRestringirReservaTurnos(this.getRestringirReservaTurnos());

		if (this.getConsultorio()!=null)
			bt.setConsultorio(this.getConsultorio().toObject());
		
		if (this.getDia() != null)
			bt.setDia(this.getDia().toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
			bt.setEspecialidadPrestada(this.getEspecialidadPrestada().toObject(
					profundidadActual + 1, profundidadDeseada));
			bt.setConsultorio(this.getConsultorio().toObject(
					profundidadActual + 1, profundidadDeseada));
		}

		return bt;
	}

	public BloqueTurno_VO acortar(int profundidadActual, int profundidadDeseada) {
		BloqueTurno_VO bt = new BloqueTurno_VO();

		bt.setAdmiteSobreturno(this.getAdmiteSobreturno());
		bt.setCantidadTurnos(this.getCantidadTurnos());
		bt.setDuracionPrimerTurno(this.getDuracionPrimerTurno());
		bt.setDuracionTurno(this.getDuracionTurno());
		bt.setHoraInicio(this.getHoraInicio());
		bt.setHoraFin(this.getHoraFin());
		bt.setCantHoraInicio(this.getCantHoraInicio());
		bt.setCantHoraFin(this.getCantHoraFin());
		bt.setFechaFin(this.getFechaFin());
		bt.setFechaInicio(this.getFechaInicio());
		bt.setId(this.getId());
		bt.setVersion(this.getVersion());
		bt.setLapsoRestriccionReservarTurnos(this
				.getLapsoRestriccionReservarTurnos());
		bt.setMensajeAviso(this.getMensajeAviso());
		bt.setMostrarAviso(this.getMostrarAviso());
		bt.setPorOrdenDeLlegada(this.getPorOrdenDeLlegada());
		bt.setRestringirReservaTurnos(this.getRestringirReservaTurnos());

		if (this.getDia() != null)
			bt.setDia(this.getDia());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
			bt.setEspecialidadPrestada(this.getEspecialidadPrestada());
		}

		return bt;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
		if (fechaInicio != null) {
			this.setStr_fechaInicio(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaInicio));
		}
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
		if (fechaFin != null) {
			this.setStr_fechaFin(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaFin));
		}
	}

	public String getStr_fechaInicio() {
		return str_fechaInicio;
	}

	public void setStr_fechaInicio(String str_fechaInicio) {
		this.str_fechaInicio = str_fechaInicio;
	}

	public String getStr_fechaFin() {
		return str_fechaFin;
	}

	public void setStr_fechaFin(String str_fechaFin) {
		this.str_fechaFin = str_fechaFin;
	}

	public String getStr_horaInicio() {
		return str_horaInicio;
	}

	public void setStr_horaInicio(String str_horaInicio) {
		this.str_horaInicio = str_horaInicio;
	}

	public String getStr_horaFin() {
		return str_horaFin;
	}

	public void setStr_horaFin(String str_horaFin) {
		this.str_horaFin = str_horaFin;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof BloqueTurno_VO) {
			BloqueTurno_VO o = (BloqueTurno_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Profesional_VO getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional_VO profesional) {
		this.profesional = profesional;
	}

	public String getNombreProfesional() {
		return nombreProfesional;
	}

	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Devuelve la fecha mas cercana hacia al futuro dentro de las del bloque
	 * turno
	 * 
	 * @param bloqueTurno
	 * @param fechaPuntero
	 * @return
	 */
	private Date obtenerFechaSiguiente(Date fechaPuntero) {
		boolean tenerEnCuentaHoras = true;

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPuntero);

		int diaSemana = VariablesGlobales.diaHorusToDiaCalendario(this.getDia()
				.getNumero_semana());

		// Me posiciono en el dia que corresponde al bloque turno
		if (cal.get(Calendar.DAY_OF_WEEK) != diaSemana) {

			tenerEnCuentaHoras = false;

			while (cal.get(Calendar.DAY_OF_WEEK) != diaSemana) {
				cal.add(Calendar.DAY_OF_WEEK, 1);
			}

		}

		/* Chequeo que este antes del cierre de atencion */
		Calendar cal_bt = Calendar.getInstance();
		cal_bt.setTime(this.getHoraFin());

		int hora_fechaPunt = cal.get(Calendar.HOUR_OF_DAY), min_fechaPunt = cal
				.get(Calendar.MINUTE), hora_f_bt = cal_bt
				.get(Calendar.HOUR_OF_DAY), min_f_bt = cal_bt
				.get(Calendar.MINUTE);

		if ((hora_fechaPunt > hora_f_bt)
				|| ((hora_fechaPunt == hora_f_bt) && (min_fechaPunt >= min_f_bt))) {

			// Ya no hay tiempo de atenderse, le sumo la cantidad de dias que
			// tenga la frecuencia
			if (tenerEnCuentaHoras)
				cal.add(Calendar.DAY_OF_YEAR, 7);

		} else {

			// Hay tiempo para atenderse en el dia cal.getTime(), como lo unico
			// que me interesa es la fecha
			// le seteo la hora para que si vuelvo a invocar este metodo, me
			// retorne el de la siguiente semana
			cal.set(Calendar.HOUR_OF_DAY, hora_f_bt);
			cal.set(Calendar.MINUTE, min_f_bt);
		}

		Date resul = null;

		// Si la nueva fecha es mas futura que el fin del contrato, no se toma y
		// queda el resul en null
		if (!cal.getTime().after(this.getFechaFin())) {

			// La fecha esta dentro del contrato
			resul = cal.getTime();
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
	private Date obtenerFechaAnterior(Date fechaPuntero) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPuntero);

		int diaSemana = VariablesGlobales.diaHorusToDiaCalendario(this.getDia()
				.getNumero_semana());

		// Me posiciono en el dia que corresponde al bloque turno
		while (cal.get(Calendar.DAY_OF_WEEK) != diaSemana) {
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}

		/* Chequeo que este despues del cierre de atencion */
		Calendar cal_bt = Calendar.getInstance();
		cal_bt.setTime(this.getHoraFin());

		int hora_fechaPunt = cal.get(Calendar.HOUR_OF_DAY), min_fechaPunt = cal
				.get(Calendar.MINUTE), hora_f_bt = cal_bt
				.get(Calendar.HOUR_OF_DAY), min_f_bt = cal_bt
				.get(Calendar.MINUTE);

		if ((hora_fechaPunt > hora_f_bt)
				|| ((hora_fechaPunt == hora_f_bt) && (min_fechaPunt >= min_f_bt))) {

			// Ya no hay tiempo de atenderse, es el dia de hoy la fecha de
			// respuesta, como lo unico que me interesa
			// es la fecha le seteo la hora para que si vuelvo a invocar este
			// metodo, me retorne el de la semana anterior
			cal.set(Calendar.HOUR_OF_DAY, hora_f_bt);
			cal.set(Calendar.MINUTE, min_f_bt--);

		} else {

			// Hay tiempo para atenderse en el dia cal.getTime(), como lo unico
			// que me interesa es la fecha
			cal.add(Calendar.DAY_OF_YEAR, -7);
		}

		Date resul = null;

		// Si la nueva fecha es mas futura que el fin del contrato, no se toma y
		// queda el resul en null
		if (!cal.getTime().after(this.getFechaFin())) {

			// La fecha esta dentro del contrato
			resul = cal.getTime();
		}

		return resul;
	}

	/**
	 * Me devuelve una fecha valida para este bloque turno. Si la fecha calza en
	 * un dia habil para este bloque turno, devuelve la misma, sino se calcula
	 * la siguiente
	 * 
	 * @param fechaPuntero
	 * @return
	 */
	public Date getFechaAnterior(Date fechaPuntero) {
		return this.obtenerFechaAnterior(fechaPuntero);
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

		return this.obtenerFechaSiguiente(fechaPuntero);
	}

	public Consultorio_VO getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio_VO consultorio) {
		this.consultorio = consultorio;
	}

	public String getNombreDia() {
		return nombreDia;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public int getNumero_semana() {
		return numero_semana;
	}

	public void setNumero_semana(int numero_semana) {
		this.numero_semana = numero_semana;
	}

	/**
	 * @return the aceptaListaDeEspera
	 */
	public Boolean getAceptaListaDeEspera() {
		return aceptaListaDeEspera;
	}

	/**
	 * @param aceptaListaDeEspera
	 *            the aceptaListaDeEspera to set
	 */
	public void setAceptaListaDeEspera(Boolean aceptaListaDeEspera) {
		this.aceptaListaDeEspera = aceptaListaDeEspera;
	}

	/**
	 * @return the diaPrimerTurnoLibre
	 */
	public Date getDiaPrimerTurnoLibre() {
		return diaPrimerTurnoLibre;
	}

	/**
	 * @param diaPrimerTurnoLibre
	 *            the diaPrimerTurnoLibre to set
	 */
	public void setDiaPrimerTurnoLibre(Date diaPrimerTurnoLibre) {
		this.diaPrimerTurnoLibre = diaPrimerTurnoLibre;
		if (diaPrimerTurnoLibre!= null){
			this.setStr_diaPrimerTurnoLibre(new SimpleDateFormat("dd/MM/yyyy").format(diaPrimerTurnoLibre));
		}
	}

	/**
	 * @return the str_diaPrimerTurnoLibre
	 */
	public String getStr_diaPrimerTurnoLibre() {
		return str_diaPrimerTurnoLibre;
	}

	/**
	 * @param str_diaPrimerTurnoLibre
	 *            the str_diaPrimerTurnoLibre to set
	 */
	public void setStr_diaPrimerTurnoLibre(String str_diaPrimerTurnoLibre) {
		this.str_diaPrimerTurnoLibre = str_diaPrimerTurnoLibre;
	}

	/**
	 * @return the horaPrimerTurnoLibre
	 */
	public Date getHoraPrimerTurnoLibre() {
		return horaPrimerTurnoLibre;
	}

	/**
	 * @param horaPrimerTurnoLibre
	 *            the horaPrimerTurnoLibre to set
	 */
	public void setHoraPrimerTurnoLibre(Date horaPrimerTurnoLibre) {
		this.horaPrimerTurnoLibre = horaPrimerTurnoLibre;
	}

	/**
	 * @return the str_horaPrimerTurnoLibre
	 */
	public String getStr_horaPrimerTurnoLibre() {
		return str_horaPrimerTurnoLibre;
	}

	/**
	 * @param str_horaPrimerTurnoLibre
	 *            the str_horaPrimerTurnoLibre to set
	 */
	public void setStr_horaPrimerTurnoLibre(String str_horaPrimerTurnoLibre) {
		this.str_horaPrimerTurnoLibre = str_horaPrimerTurnoLibre;
	}

	/**
	 * @return the str_horaPrimerTurno
	 */
	public String getStr_horaPrimerTurno() {
		return str_horaPrimerTurno;
	}

	/**
	 * @param str_horaPrimerTurno
	 *            the str_horaPrimerTurno to set
	 */
	public void setStr_horaPrimerTurno(String str_horaPrimerTurno) {
		this.str_horaPrimerTurno = str_horaPrimerTurno;
	}

	/**
	 * @return the matricula
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula
	 *            the matricula to set
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * @param servicio
	 *            the servicio to set
	 */
	public void setServicio(String servicio) {
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

	public void setNumeroTurno(Integer nroTurno) {
		this.numeroTurno = nroTurno;
	}
	public Integer getNumeroTurno() {
		return this.numeroTurno;
	}

	public Date getDiaProximoTurno() {
		return diaProximoTurno;
	}

	public void setDiaProximoTurno(Date diaProximoTurno) {
		this.diaProximoTurno = diaProximoTurno;
		if (diaProximoTurno!= null){
			this.setStr_diaProximoTurno(new SimpleDateFormat("dd/MM/yyyy").format(diaProximoTurno));
		}
	}

	public String getStr_diaProximoTurno() {
		return str_diaProximoTurno;
	}

	public void setStr_diaProximoTurno(String str_diaProximoTurno) {
		this.str_diaProximoTurno = str_diaProximoTurno;
	}
	
	


}