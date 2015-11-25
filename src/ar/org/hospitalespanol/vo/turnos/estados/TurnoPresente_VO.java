package ar.org.hospitalespanol.vo.turnos.estados;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.org.hospitalespanol.model.turnos.estadosTurno.EstadoTurno;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class TurnoPresente_VO extends TurnoReservado_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date horaPresentoPaciente;

	private Rol_VO personalPresentoTurno;

	private String nombrePersonalRegistroPresencia;

	// Constructores
	public TurnoPresente_VO() {
		setNombre(EstadoTurno.PRESENTE);
	}

	public TurnoPresente_VO(TurnoReservado_VO turno, Date horaPresentado,
			Rol_VO rolPresentoTurno) {

		super.copiarDatos(turno, this);

		this.setHoraPresentoPaciente(horaPresentado);
		this.setPersonalPresentoTurno(rolPresentoTurno);
		this.setNombreUsuarioPersonalAsignoTurno(rolPresentoTurno.getUsuario()
				.toString());

	}

	@Override
	public boolean estaSinReservar() {
		return false;
	}

	@Override
	public boolean estaReservado() {
		return false;
	}

	@Override
	public boolean estaPresentado() {
		return true;
	}

	@Override
	public boolean estaTomado() {
		return false;
	}

	public Date getHoraPresentoPaciente() {
		return horaPresentoPaciente;
	}

	public void setHoraPresentoPaciente(Date horaPresentoPaciente) {
		this.horaPresentoPaciente = horaPresentoPaciente;
	}

	public Rol_VO getPersonalPresentoTurno() {
		return personalPresentoTurno;
	}

	public void setPersonalPresentoTurno(Rol_VO personalPresentoTurno) {
		this.personalPresentoTurno = personalPresentoTurno;
	}

	public String getNombrePersonalRegistroPresencia() {
		return nombrePersonalRegistroPresencia;
	}

	public void setNombrePersonalRegistroPresencia(
			String nombrePersonalRegistroPresencia) {
		this.nombrePersonalRegistroPresencia = nombrePersonalRegistroPresencia;
	}

	@Override
	public EstadoTurno_VO tomar(Date horaTomado, Rol_VO rolTomoTurno) {
		return new TurnoTomado_VO(this, horaTomado, rolTomoTurno);
	}

	/*
	 * public TurnoPresente(Date horaPresentado, Rol personalPresentado) {
	 * setNombre(EstadoTurno.PRESENTE); this.setHoraPresentado(horaPresentado);
	 * this.setPersonalPresentado(personalPresentado); }
	 */
	// public TurnoPresente(Date horaPresentado, String nombreUsrPresentado) {
	// setNombre(EstadoTurno.PRESENTE);
	// this.setHoraPresentado(horaPresentado);
	// this.setNombrePersonalPresentado(nombreUsrPresentado);
	// }
	//
	// //Gettters & Setters
	// public boolean equals(Object objeto) {
	// if (objeto instanceof TurnoPresente) {
	// TurnoPresente o = (TurnoPresente) objeto;
	// return (o.getId().equals(this.getId()));
	// }
	// return false;
	// }
	//
	// public Date getHoraPresentado() {
	// return horaPresentado;
	// }
	//
	// public void setHoraPresentado(Date horaPresentado) {
	// this.horaPresentado = horaPresentado;
	// }
	//
	// /*
	// @Override
	// public EstadoTurno tomar(Date horaTomado, Rol personalTomado) {
	// TurnoTomado tTomado = new TurnoTomado(getHoraPresentado(),
	// getPersonalPresentado(), horaTomado, personalTomado);
	// copiar(this, tTomado);
	// return tTomado;
	// }
	// */
	// public EstadoTurno tomar(Date horaTomado, String nombreUsrPresentado) {
	// TurnoTomado tTomado = new TurnoTomado(getHoraPresentado(),
	// getNombrePersonalPresentado(), horaTomado, nombreUsrPresentado);
	// copiar(this, tTomado);
	// return tTomado;
	// }
	//
	//
	// public Rol getPersonalPresentado() {
	// return personalPresentado;
	// }
	//
	// public void setPersonalPresentado(Rol personalPresentado) {
	// this.personalPresentado = personalPresentado;
	// }
	//
	// public String getNombrePersonalPresentado() {
	// return nombrePersonalPresentado;
	// }
	//
	// public void setNombrePersonalPresentado(String nombrePersonalPresentado)
	// {
	// this.nombrePersonalPresentado = nombrePersonalPresentado;
	// }
	//
	// public boolean estaPresentado(){
	// return true;
	// }

	@Override
	public String getLabel() {
		String resul = super.getLabel();

		SimpleDateFormat fmt_fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		resul += " se presentó el: "
				+ fmt_fecha.format(getHoraPresentoPaciente())
				+ " registrado por: " + getNombrePersonalRegistroPresencia();

		return resul;
	}

}