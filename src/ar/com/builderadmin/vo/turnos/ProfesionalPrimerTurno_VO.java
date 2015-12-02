package ar.com.builderadmin.vo.turnos;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfesionalPrimerTurno_VO {

	private Long idUsuario;

	private String apellidoProfesional;
	private String nombresProfesional;
	private String nombreUsuarioProfesional;

	private Long idProfesional;

	private Long idTurno;
	private Integer numeroTurno;

	private Long idBloqueTurno;

	private Date horaInicioBloqueTurno;
	private String str_horaInicioBloqueTurno;

	private Date horaFinBloqueTurno;
	private String str_horaFinBloqueTurno;

	private Date fecha;
	private String str_fecha;

	private String categoria;

	private Date hora;
	private String str_hora;

	private String diaSemana;

	private Boolean mostrarAviso;
	private String mensajeAviso;

	public ProfesionalPrimerTurno_VO(BigInteger bigInteger,
			String apellidoProfesional, String nombresProfesional,
			String nombreUsuarioProfesional, BigInteger idTurno,
			Integer numeroTurno, BigInteger idBloqueTurno, Date horaInicio,
			Date horaFin, Date fecha, Date hora, String diaSemana,
			BigInteger idProfesional, String categoria, Boolean mostrarAviso,
			String mensajeAviso) {
		setApellidoProfesional(apellidoProfesional);
		setDiaSemana(diaSemana);
		setFecha(fecha);
		setHora(hora);
		setIdBloqueTurno(Long.valueOf(idBloqueTurno.toString()));
		setHoraInicioBloqueTurno(horaInicio);
		setHoraFinBloqueTurno(horaFin);
		setIdTurno(Long.valueOf(idTurno.toString()));
		setIdUsuario(Long.valueOf(bigInteger.toString()));
		setNombresProfesional(nombresProfesional);
		setNombreUsuarioProfesional(nombreUsuarioProfesional);
		setNumeroTurno(numeroTurno);
		setIdProfesional(Long.valueOf(idProfesional.toString()));
		setCategoria(categoria);
		setMostrarAviso(mostrarAviso);
		setMensajeAviso(mensajeAviso);
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidoProfesional() {
		return apellidoProfesional;
	}

	public void setApellidoProfesional(String apellidoProfesional) {
		this.apellidoProfesional = apellidoProfesional;
	}

	public String getNombresProfesional() {
		return nombresProfesional;
	}

	public void setNombresProfesional(String nombresProfesional) {
		this.nombresProfesional = nombresProfesional;
	}

	public String getNombreUsuarioProfesional() {
		return nombreUsuarioProfesional;
	}

	public void setNombreUsuarioProfesional(String nombreUsuarioProfesional) {
		this.nombreUsuarioProfesional = nombreUsuarioProfesional;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Integer getNumeroTurno() {
		return numeroTurno;
	}

	public void setNumeroTurno(Integer numeroTurno) {
		this.numeroTurno = numeroTurno;
	}

	public Long getIdBloqueTurno() {
		return idBloqueTurno;
	}

	public void setIdBloqueTurno(Long idBloqueTurno) {
		this.idBloqueTurno = idBloqueTurno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		if (fecha != null) {
			this.setStr_fecha(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		}
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
		if (hora != null) {
			this.setStr_hora(new SimpleDateFormat("HH:mm").format(hora));
		}
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Long getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(Long idProfesional) {
		this.idProfesional = idProfesional;
	}

	public String getStr_fecha() {
		return str_fecha;
	}

	public void setStr_fecha(String str_fecha) {
		this.str_fecha = str_fecha;
	}

	public String getStr_hora() {
		return str_hora;
	}

	public void setStr_hora(String str_hora) {
		this.str_hora = str_hora;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getHoraInicioBloqueTurno() {
		return horaInicioBloqueTurno;
	}

	public void setHoraInicioBloqueTurno(Date horaInicioBloqueTurno) {
		this.horaInicioBloqueTurno = horaInicioBloqueTurno;
		if (horaInicioBloqueTurno != null) {
			this.setStr_horaInicioBloqueTurno(new SimpleDateFormat("HH:mm")
					.format(horaInicioBloqueTurno));
		}
	}

	public void setHoraFinBloqueTurno(Date horaFinBloqueTurno) {
		this.horaFinBloqueTurno = horaFinBloqueTurno;
		if (horaFinBloqueTurno != null) {
			this.setStr_horaFinBloqueTurno(new SimpleDateFormat("HH:mm")
					.format(horaFinBloqueTurno));
		}
	}

	public String getStr_horaInicioBloqueTurno() {
		return str_horaInicioBloqueTurno;
	}

	public void setStr_horaInicioBloqueTurno(String str_horaInicioBloqueTurno) {
		this.str_horaInicioBloqueTurno = str_horaInicioBloqueTurno;
	}

	public Date getHoraFinBloqueTurno() {
		return horaFinBloqueTurno;
	}

	public String getStr_horaFinBloqueTurno() {
		return str_horaFinBloqueTurno;
	}

	public void setStr_horaFinBloqueTurno(String str_horaFinBloqueTurno) {
		this.str_horaFinBloqueTurno = str_horaFinBloqueTurno;
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
