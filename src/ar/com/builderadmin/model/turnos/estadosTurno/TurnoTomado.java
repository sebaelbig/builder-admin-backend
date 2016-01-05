package ar.com.builderadmin.model.turnos.estadosTurno;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.turnos.estados.EstadoTurno_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoTomado_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("turno_tomado")
public class TurnoTomado extends TurnoReservado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "hora_presento_paciente")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaPresentoPaciente;

	@ManyToOne
	@JoinColumn(name = "personal_presento_turno")
	private Rol personalPresentoTurno;

	@Column(name = "nombre_personal_regsitro_presencia")
	private String nombrePersonalRegistroPresencia;

	@Column(name = "hora_tomo_paciente")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaTomoPaciente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "personal_tomo_turno")
	private Rol personalTomoTurno;

	@Column(name = "nombre_personal_regsitro_toma_turno")
	private String nombrePersonalRegistroToma;

	@Column(name = "tiempo_de_espera")
	private String tiempoDeEspera;

	// Constructores
	public TurnoTomado() {
		this.setNombre(EstadoTurno.TOMADO);
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
		return false;
	}

	@Override
	public boolean estaTomado() {
		return true;
	}

	public TurnoTomado(TurnoPresente turnoPresente, Date horaTomado, Rol rol) {
		/**
		 * Datos de la reserva
		 */
		this.setFechaEstablecido(turnoPresente.getFechaEstablecido());
		this.setPaciente(turnoPresente.getPaciente());
		this.setProductoObraSocialPaciente(turnoPresente
				.getProductoObraSocialPaciente());
		this.setPersonalAsignoTurno(turnoPresente.getPersonalAsignoTurno());
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setDiferenciado(turnoPresente.getDiferenciado());
		this.setHonorarios(turnoPresente.getHonorarios());
		this.setDer(turnoPresente.getDer());
		this.setPrestacion(turnoPresente.getPrestacion());

		/**
		 * Datos del presente
		 */
		this.setHoraPresentoPaciente(turnoPresente.getHoraPresentoPaciente());
		this.setPersonalPresentoTurno(turnoPresente.getPersonalPresentoTurno());
		this.setNombrePersonalRegistroPresencia(this.getPersonalPresentoTurno()
				.getUsuario().toString());

		/**
		 * Datos del tomado
		 */
		this.setNombre(EstadoTurno.TOMADO);
		this.setHoraTomoPaciente(horaTomado);
		this.setPersonalTomoTurno(rol);
		this.setNombrePersonalRegistroToma(rol.getUsuario().toString());

		long diferenciaMils = horaTomado.getTime()
				- turnoPresente.getHoraPresentoPaciente().getTime();
		long segundos = diferenciaMils / 1000;
		long horas = segundos / 3600;
		segundos -= horas * 3600;
		long minutos = segundos / 60;
		segundos -= minutos * 60;

		this.setTiempoDeEspera(horas + ":" + minutos + ":" + segundos);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TurnoTomado) {
			TurnoTomado o = (TurnoTomado) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Date getHoraPresentoPaciente() {
		return this.horaPresentoPaciente;
	}

	public void setHoraPresentoPaciente(Date horaPresentoPaciente) {
		this.horaPresentoPaciente = horaPresentoPaciente;
	}

	public Rol getPersonalPresentoTurno() {
		return this.personalPresentoTurno;
	}

	public void setPersonalPresentoTurno(Rol personalPresentoTurno) {
		this.personalPresentoTurno = personalPresentoTurno;
	}

	public String getNombrePersonalRegistroPresencia() {
		return this.nombrePersonalRegistroPresencia;
	}

	public void setNombrePersonalRegistroPresencia(
			String nombrePersonalRegistroPresencia) {
		this.nombrePersonalRegistroPresencia = nombrePersonalRegistroPresencia;
	}

	public Date getHoraTomoPaciente() {
		return this.horaTomoPaciente;
	}

	public void setHoraTomoPaciente(Date horaTomoPaciente) {
		this.horaTomoPaciente = horaTomoPaciente;
	}

	public Rol getPersonalTomoTurno() {
		return this.personalTomoTurno;
	}

	public void setPersonalTomoTurno(Rol personalTomoTurno) {
		this.personalTomoTurno = personalTomoTurno;
	}

	public String getNombrePersonalRegistroToma() {
		return this.nombrePersonalRegistroToma;
	}

	public void setNombrePersonalRegistroToma(String nombrePersonalRegistroToma) {
		this.nombrePersonalRegistroToma = nombrePersonalRegistroToma;
	}

	public String getTiempoDeEspera() {
		return this.tiempoDeEspera;
	}

	public void setTiempoDeEspera(String tiempoDeEspera) {
		this.tiempoDeEspera = tiempoDeEspera;
	}

	@Override
	public EstadoTurno_VO toValueObject() {
		TurnoTomado_VO turno = new TurnoTomado_VO();

		turno.setNombre(this.getNombre());

		turno.setFechaEstablecido(this.getFechaEstablecido());

		turno.setDiferenciado(this.getDiferenciado());
		turno.setHonorarios(this.getHonorarios());
		turno.setDer(this.getDer());
		turno.setPrestacion(this.getPrestacion().toValueObject());

		turno.setPaciente(this.getPaciente().toValueObject());
		if (this.getProductoObraSocialPaciente() != null) {
			turno.setProductoObraSocialPaciente(this
					.getProductoObraSocialPaciente().toValueObject(
							I_ValueObject.PROFUNDIDAD_BASE, 1));
		} else {
			turno.setProductoObraSocialPaciente(null);
		}

		turno.setPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.toValueObject());
		turno.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		/**
		 * Datos de la reserva
		 */
		turno.setHoraPresentoPaciente(this.getHoraPresentoPaciente());
		turno.setPersonalPresentoTurno(this.getPersonalPresentoTurno()
				.toValueObject());
		turno.setNombrePersonalRegistroPresencia(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		/**
		 * Datos del tomado
		 */
		turno.setHoraTomoPaciente(this.getHoraTomoPaciente());
		turno.setPersonalTomoTurno(this.getPersonalTomoTurno().toValueObject());
		turno.setNombrePersonalRegistroToma(this.getPersonalTomoTurno()
				.getUsuario().toString());
		turno.setTiempoDeEspera(this.getTiempoDeEspera());

		return turno;
	}

}