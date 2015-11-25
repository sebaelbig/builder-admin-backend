package ar.org.hospitalespanol.vo.turnos.estados;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;


/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class TurnoTomado_VO extends TurnoReservado_VO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Datos del PRESENTE
	 */
	private Date horaPresentoPaciente;
	private Rol_VO personalPresentoTurno;
	private String nombrePersonalRegistroPresencia;
	
	/**
	 * Datos del TOMADO
	 */
	private Date horaTomoPaciente;
	private Rol_VO personalTomoTurno;
	private String nombrePersonalRegistroToma;
	private String tiempoDeEspera;
	
	//Constructores
	public TurnoTomado_VO(){
		this.setNombre(EstadoTurno_VO.TOMADO);
	}

	public TurnoTomado_VO(TurnoPresente_VO turno, Date horaTomado, Rol_VO rolTomoTurno) {
		
		super.copiarDatos(turno, this);
		
		this.setHoraPresentoPaciente(turno.getHoraPresentoPaciente());
		this.setPersonalPresentoTurno(turno.getPersonalPresentoTurno());
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalPresentoTurno().getUsuario().toString());
		
		this.setHoraTomoPaciente(horaTomado);
		this.setPersonalTomoTurno(rolTomoTurno);
		this.setNombrePersonalRegistroToma(rolTomoTurno.getUsuario().toString());
		
		long diferenciaMils = horaTomado.getTime() - turno.getHoraPresentoPaciente().getTime();
		long segundos = diferenciaMils / 1000;
		long horas = segundos / 3600;
		segundos -= horas * 3600;
		long minutos = segundos / 60;
		segundos -= minutos * 60;
		
		this.setTiempoDeEspera(horas+":"+minutos+":"+segundos);
		
	}

	public void setTiempoDeEspera(String tiempoDeEspera) {
		this.tiempoDeEspera = tiempoDeEspera;
	}

	public Date getHoraPresentoPaciente() {
		return this.horaPresentoPaciente;
	}

	public void setHoraPresentoPaciente(Date horaPresentoPaciente) {
		this.horaPresentoPaciente = horaPresentoPaciente;
	}

	public Date getHoraTomoPaciente() {
		return this.horaTomoPaciente;
	}

	public void setHoraTomoPaciente(Date horaTomoPaciente) {
		this.horaTomoPaciente = horaTomoPaciente;
	}

	public String getNombrePersonalRegistroToma() {
		return this.nombrePersonalRegistroToma;
	}

	public void setNombrePersonalRegistroToma(String nombrePersonalRegistroToma) {
		this.nombrePersonalRegistroToma = nombrePersonalRegistroToma;
	}

	public void setPersonalPresentoTurno(Rol_VO personalPresentoTurno) {
		this.personalPresentoTurno = personalPresentoTurno;
	}

	public String getNombrePersonalRegistroPresencia() {
		return this.nombrePersonalRegistroPresencia;
	}

	public void setNombrePersonalRegistroPresencia(
			String nombrePersonalRegistroPresencia) {
		this.nombrePersonalRegistroPresencia = nombrePersonalRegistroPresencia;
	}

	public Rol_VO getPersonalTomoTurno() {
		return this.personalTomoTurno;
	}

	public void setPersonalTomoTurno(Rol_VO personalTomoTurno) {
		this.personalTomoTurno = personalTomoTurno;
	}

	public Rol_VO getPersonalPresentoTurno() {
		return this.personalPresentoTurno;
	}

	/*
	public TurnoTomado(Date horaPresentado, Rol personalPresentado, Date horaTomado, Rol personalTomado) {
		setNombre(EstadoTurno.TOMADO);
		this.setHoraPresentado(horaPresentado);
		this.setPersonalPresentado(personalPresentado);
		
		this.setHoraTomado(horaTomado);
		this.setPersonalTomado(personalTomado);
	}
	*/
//	public TurnoTomado(Date horaPresentado, String personalPresentado, Date horaTomado, String personalTomado) {
//		setNombre(EstadoTurno.TOMADO);
//		this.setHoraPresentado(horaPresentado);
//		this.setNombrePersonalPresentado(personalPresentado);
//		
//		this.setHoraTomado(horaTomado);
//		this.setNombrePersonalTomado(personalTomado);
//	}
//	
//
//	//Gettters & Setters
//	public boolean equals(Object objeto) {
//		if (objeto instanceof TurnoTomado) {
//			TurnoTomado o = (TurnoTomado) objeto;
//			return (o.getId().equals(this.getId()));
//		}
//		return false;
//	}
//
//	public Date getHoraPresentado() {
//		return horaPresentado;
//	}
//
//	public void setHoraPresentado(Date horaPresentado) {
//		this.horaPresentado = horaPresentado;
//	}
//
//	public Date getHoraTomado() {
//		return horaTomado;
//	}
//
//	public void setHoraTomado(Date horaTomado) {
//		this.horaTomado = horaTomado;
//	}
//	/*
//	@Override
//	public EstadoTurno tomar(Date horaTomado, Rol personalTomado) {
//		return null;
//	}
//	*/
//	public EstadoTurno tomar(Date horaTomado, String nombrePersonaTomo) {
//		return null;
//	}
//	
//
//	public Rol getPersonalPresentado() {
//		return personalPresentado;
//	}
//
//	public void setPersonalPresentado(Rol personalPresentado) {
//		this.personalPresentado = personalPresentado;
//	}
//
//	public Rol getPersonalTomado() {
//		return personalTomado;
//	}
//
//	public void setPersonalTomado(Rol personalTomado) {
//		this.personalTomado = personalTomado;
//	}
//
//	public String getNombrePersonalPresentado() {
//		return nombrePersonalPresentado;
//	}
//
//	public void setNombrePersonalPresentado(String nombrePersonalPresentado) {
//		this.nombrePersonalPresentado = nombrePersonalPresentado;
//	}
//
//	public String getNombrePersonalTomado() {
//		return nombrePersonalTomado;
//	}
//
//	public void setNombrePersonalTomado(String nombrePersonalTomado) {
//		this.nombrePersonalTomado = nombrePersonalTomado;
//	}
//
	@Override
	public boolean estaSinReservar() {
		return false;
	}
	@Override
	public boolean estaReservado() {
		return false;
	}
	@Override
	public boolean estaPresentado(){
		return false;
	}
	@Override
	public boolean estaTomado() {
		return true;
	}
	
	@Override
	public String getLabel() {
		String resul = super.getLabel();
		
		SimpleDateFormat fmt_fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		resul += " se presentó el: "+fmt_fecha.format( this.getHoraPresentoPaciente() )+" registrado por: "+this.getNombrePersonalRegistroPresencia();
		
		resul += " fue tomado: "+fmt_fecha.format( this.getHoraTomoPaciente() )+" registrado por: "+this.getNombrePersonalRegistroToma();
		
		resul += ". Tiempo de espera: "+this.getTiempoDeEspera();
		
		return resul;
	}

	public String getTiempoDeEspera() {
		return this.tiempoDeEspera;
	}
	
}