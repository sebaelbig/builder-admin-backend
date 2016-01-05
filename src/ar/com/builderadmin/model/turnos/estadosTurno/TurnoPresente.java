package ar.com.builderadmin.model.turnos.estadosTurno;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.turnos.estados.EstadoTurno_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoPresente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("turno_presente")
public class TurnoPresente extends TurnoReservado implements Serializable {

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

	// Constructores
	public TurnoPresente() {
		this.setNombre(EstadoTurno.PRESENTE);
	}

	@Override
	public EstadoTurno_VO toValueObject() {
		TurnoPresente_VO turno = new TurnoPresente_VO();

		turno.setNombre(this.getNombre());

		turno.setFechaEstablecido(this.getFechaEstablecido());

		turno.setDiferenciado(this.getDiferenciado());
		turno.setHonorarios(this.getHonorarios());
		turno.setDer(this.getDer());

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
				.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 2));
		turno.setNombrePersonalRegistroPresencia(this.getPersonalAsignoTurno()
				.getUsuario().toString());
		turno.setPrestacion(this.getPrestacion().toValueObject());
		return turno;
	}

	public TurnoPresente(TurnoReservado turnoReservado, Date horaPresentado,
			Rol personalPresento) {

		/**
		 * Datos de la reserva
		 */
		this.setFechaEstablecido(turnoReservado.getFechaEstablecido());
		this.setPaciente(turnoReservado.getPaciente());
		this.setProductoObraSocialPaciente(turnoReservado
				.getProductoObraSocialPaciente());
		this.setPersonalAsignoTurno(turnoReservado.getPersonalAsignoTurno());
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setDiferenciado(turnoReservado.getDiferenciado());
		this.setHonorarios(turnoReservado.getHonorarios());
		this.setDer(turnoReservado.getDer());
		this.setPrestacion(turnoReservado.getPrestacion());
		/**
		 * Datos del presente
		 */
		this.setNombre(EstadoTurno.PRESENTE);
		this.setHoraPresentoPaciente(horaPresentado);
		this.setPersonalPresentoTurno(personalPresento);
		this.setNombrePersonalRegistroPresencia(personalPresento.getUsuario()
				.toString());

	}

	public TurnoPresente(TurnoReservado turnoReservado,
			ProductoObraSocialPaciente productoAlPresentar,
			Date horaPresentado, Rol personalPresento) {
		/**
		 * Datos de la reserva
		 */
		this.setFechaEstablecido(turnoReservado.getFechaEstablecido());
		this.setPaciente(turnoReservado.getPaciente());
		this.setProductoObraSocialPaciente(productoAlPresentar);
		this.setPersonalAsignoTurno(turnoReservado.getPersonalAsignoTurno());
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setDiferenciado(turnoReservado.getDiferenciado());
		this.setHonorarios(turnoReservado.getHonorarios());
		this.setDer(turnoReservado.getDer());
		this.setPrestacion(turnoReservado.getPrestacion());
		/**
		 * Datos del presente
		 */
		this.setNombre(EstadoTurno.PRESENTE);
		this.setHoraPresentoPaciente(horaPresentado);
		this.setPersonalPresentoTurno(personalPresento);
		this.setNombrePersonalRegistroPresencia(personalPresento.getUsuario()
				.toString());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TurnoPresente) {
			TurnoPresente o = (TurnoPresente) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public EstadoTurno tomar(Date horaTomado, Rol rol) {
		return new TurnoTomado(this, horaTomado, rol);
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

}