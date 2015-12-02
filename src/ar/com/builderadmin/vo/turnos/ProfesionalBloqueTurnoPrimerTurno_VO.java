package ar.com.builderadmin.vo.turnos;

import java.math.BigInteger;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfesionalBloqueTurnoPrimerTurno_VO {

	private Long idBloqueTurno;

	private Time horaInicioBloqueTurno;
	private String str_horaInicioBloqueTurno;

	private Time horaFinBloqueTurno;
	private String str_horaFinBloqueTurno;

	private String diaSemana;

	/**
	 * id del turno
	 */
	private Long idTurno;

	/**
	 * Fecha del primer turno libre de este bloque turno
	 */
	private Date fechaTurno;
	private String str_fechaTurno;

	/**
	 * Hora del primer turno libre de este bloque turno
	 */
	private Time horaTurno;
	private String str_horaTurno;

	private Long idProfesional;

	private Long idContrato;

	private Long idEspecialidad;

	private String nombreEspecialidad;

	private String categoria;
	private Float precioConsulta;

	private Boolean mostrarAviso;
	private String mensajeAviso;

	public ProfesionalBloqueTurnoPrimerTurno_VO() {
	}

	public ProfesionalBloqueTurnoPrimerTurno_VO(BigInteger idProfesional,
			BigInteger idContrato, BigInteger idEspecialidad,
			String nombreEspecialidad, String diaSemana,
			BigInteger idBloqueTurno, Time horaInicioBloqueTurno,
			Time horaFinBloqueTurno, String categoria, BigInteger idTurno,
			Date fechaTurno, Time horaTurno, Float precioConsulta,
			Boolean mostrar, String mensaje) {
		setCategoria(categoria);
		setDiaSemana(diaSemana);
		setFechaTurno(fechaTurno);
		setHoraFinBloqueTurno(horaFinBloqueTurno);
		setHoraInicioBloqueTurno(horaInicioBloqueTurno);
		setHoraTurno(horaTurno);
		setIdBloqueTurno(Long.valueOf(idBloqueTurno.toString()));
		setIdContrato(Long.valueOf(idContrato.toString()));
		setIdEspecialidad(Long.valueOf(idEspecialidad.toString()));
		setIdProfesional(Long.valueOf(idProfesional.toString()));
		setIdTurno(Long.valueOf(idTurno.toString()));
		setNombreEspecialidad(nombreEspecialidad);
		setPrecioConsulta(precioConsulta);
		setMostrarAviso(mostrar);
		setMensajeAviso(mensaje);
	}

	public Long getIdBloqueTurno() {
		return idBloqueTurno;
	}

	public void setIdBloqueTurno(Long idBloqueTurno) {
		this.idBloqueTurno = idBloqueTurno;
	}

	public Time getHoraInicioBloqueTurno() {
		return horaInicioBloqueTurno;
	}

	public void setHoraInicioBloqueTurno(Time horaInicioBloqueTurno) {
		this.horaInicioBloqueTurno = horaInicioBloqueTurno;
		if (horaInicioBloqueTurno != null) {
			this.setStr_horaInicioBloqueTurno(new SimpleDateFormat("HH:mm")
					.format(horaInicioBloqueTurno));
		}
	}

	public Time getHoraFinBloqueTurno() {
		return horaFinBloqueTurno;
	}

	public void setHoraFinBloqueTurno(Time horaFinBloqueTurno) {
		this.horaFinBloqueTurno = horaFinBloqueTurno;
		if (horaFinBloqueTurno != null) {
			this.setStr_horaFinBloqueTurno(new SimpleDateFormat("HH:mm")
					.format(horaFinBloqueTurno));
		}
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Date getFechaTurno() {
		return fechaTurno;
	}

	public void setFechaTurno(Date fechaTurno) {
		this.fechaTurno = fechaTurno;
		if (fechaTurno != null) {
			this.setStr_fechaTurno(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaTurno));
		}
	}

	public Time getHoraTurno() {
		return horaTurno;
	}

	public void setHoraTurno(Time horaTurno) {
		this.horaTurno = horaTurno;
		if (horaTurno != null) {
			this.setStr_horaTurno(new SimpleDateFormat("HH:mm")
					.format(horaTurno));
		}
	}

	public Long getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(Long idProfesional) {
		this.idProfesional = idProfesional;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public Long getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getStr_fechaTurno() {
		return str_fechaTurno;
	}

	public void setStr_fechaTurno(String str_fechaTurno) {
		this.str_fechaTurno = str_fechaTurno;
	}

	public String getStr_horaTurno() {
		return str_horaTurno;
	}

	public void setStr_horaTurno(String str_horaTurno) {
		this.str_horaTurno = str_horaTurno;
	}

	public String getStr_horaInicioBloqueTurno() {
		return str_horaInicioBloqueTurno;
	}

	public void setStr_horaInicioBloqueTurno(String str_horaInicioBloqueTurno) {
		this.str_horaInicioBloqueTurno = str_horaInicioBloqueTurno;
	}

	public String getStr_horaFinBloqueTurno() {
		return str_horaFinBloqueTurno;
	}

	public void setStr_horaFinBloqueTurno(String str_horaFinBloqueTurno) {
		this.str_horaFinBloqueTurno = str_horaFinBloqueTurno;
	}

	public Float getPrecioConsulta() {
		return precioConsulta;
	}

	public void setPrecioConsulta(Float precioConsulta) {
		this.precioConsulta = precioConsulta;
	}

	@Override
	public String toString() {

		return this.getDiaSemana() + " de "
				+ this.getStr_horaInicioBloqueTurno() + " a "
				+ this.getStr_horaFinBloqueTurno() + " - Cat. "
				+ this.getCategoria() + " $ " + this.getPrecioConsulta()
				+ " (1� T: " + this.getStr_fechaTurno() + " - "
				+ this.getStr_horaTurno() + " )";
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

}
