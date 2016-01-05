package ar.com.builderadmin.model.turnos.estadosTurno;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.vo.turnos.estados.EstadoTurno_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoSinReservar_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("turno_sin_reservar")
public class TurnoSinReservar extends EstadoTurno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public TurnoSinReservar() {
		setNombre(EstadoTurno.SIN_RESERVAR);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TurnoSinReservar) {
			TurnoSinReservar o = (TurnoSinReservar) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public EstadoTurno cancelar() {
		return this;
	}

	@Override
	public EstadoTurno reservar(Paciente paciente,
			ProductoObraSocialPaciente obraSocialPaciente,
			Rol personalReservoTurno, Float[] costos, Date f, Prestacion presta) {
		return new TurnoReservado(paciente, obraSocialPaciente,
				personalReservoTurno, costos, f, presta);
	}

	@Override
	public EstadoTurno presentar(Date horaPresentado, Rol rol) {
		return null;
	}

	@Override
	public EstadoTurno tomar(Date horaTomado, Rol rol) {
		return null;
	}

	@Override
	public boolean estaReservadoPor(Paciente paciente) {
		return false;
	}

	@Override
	public Paciente getPacienteQueReservo() {
		return null;
	}

	@Override
	public ProductoObraSocialPaciente getProductoObraSocialReserva() {
		return null;
	}

	@Override
	public boolean estaSinReservar() {
		return true;
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
		return false;
	}

	@Override
	public void setProductoObraSocialReserva(ProductoObraSocialPaciente obraPac) {
		// Solo la modifica los turnos reservados
	}

	@Override
	public void setDiferenciadoReserva(Float diferenciado) {
		// Solo la modifica los turnos reservados
	}

	@Override
	public void setHonorariosReserva(Float honorarios) {
		// Solo la modifica los turnos reservados
	}

	@Override
	public EstadoTurno_VO toValueObject() {
		return new TurnoSinReservar_VO();
	}

	@Override
	public EstadoTurno presentar(
			ProductoObraSocialPaciente productoAlPresentar,
			Date horaPresentado, Rol personalPresentoTurno) {
		return null;
	}

}