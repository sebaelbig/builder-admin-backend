package ar.com.builderadmin.model.turnos.estadosTurno;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.turnos.estados.EstadoTurno_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity 
@DiscriminatorValue("turno_reservado")
public class TurnoReservado extends EstadoTurno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "producto_obra_social_paciente")
	private ProductoObraSocialPaciente productoObraSocialPaciente;

	@ManyToOne
	@JoinColumn(name = "id_prestacion")
	private Prestacion prestacion;

	@ManyToOne
	@JoinColumn(name = "id_personal_asigno_turno")
	private Rol personalAsignoTurno;

	@Column(name = "nombre_usuario_personal_asigno_turno")
	private String nombreUsuarioPersonalAsignoTurno;

	private Float honorarios;
	private Float diferenciado;
	private Float der;

	// Constructores
	public TurnoReservado() {
		this.setNombre(EstadoTurno.RESERVADO);
	}

	public TurnoReservado(Paciente paciente,
			ProductoObraSocialPaciente obraSocialPaciente,
			Rol personalReservoTurno, Float[] costos, Date fechaReserva,
			Prestacion presta) {

		this.setNombre(EstadoTurno.RESERVADO);

		this.setFechaEstablecido(fechaReserva);
		this.setPaciente(paciente);
		this.setProductoObraSocialPaciente(obraSocialPaciente);
		this.setPersonalAsignoTurno(personalReservoTurno);
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setPrestacion(presta);

		if (costos != null) {
			this.setDiferenciado(costos[0]);
			this.setHonorarios(costos[1]);
			this.setDer(costos[2]);
		} else {
			this.setDiferenciado(0F);
			this.setHonorarios(0F);
			this.setDer(0F);
		}
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TurnoReservado) {
			TurnoReservado o = (TurnoReservado) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Float getHonorarios() {
		return this.honorarios;
	}

	public void setHonorarios(Float honorarios) {
		this.honorarios = honorarios;
	}

	public Float getDiferenciado() {
		return this.diferenciado;
	}

	public void setDiferenciado(Float diferenciado) {
		this.diferenciado = diferenciado;
	}

	public Float getDer() {
		return this.der;
	}

	public void setDer(Float der) {
		this.der = der;
	}

	@Override
	public EstadoTurno cancelar() {
		return new TurnoSinReservar();
	}

	@Override
	public EstadoTurno reservar(Paciente paciente,
			ProductoObraSocialPaciente obraSocialPaciente, Rol rolReservo,
			Float[] costos, Date f, Prestacion presta) {
		return this;
	}

	@Override
	public EstadoTurno presentar(Date horaPresentado, Rol personalPresentoTurno) {
		return new TurnoPresente(this, horaPresentado, personalPresentoTurno);
	}

	@Override
	public EstadoTurno presentar(
			ProductoObraSocialPaciente productoAlPresentar,
			Date horaPresentado, Rol personalPresentoTurno) {
		return new TurnoPresente(this, productoAlPresentar, horaPresentado,
				personalPresentoTurno);
	}

	@Override
	public EstadoTurno tomar(Date horaTomado, Rol rolTomo) {
		// Implementado por la subclases
		return null;
	}

	@Override
	public boolean estaReservadoPor(Paciente paciente) {
		return this.getPaciente().equals(paciente);
	}

	@Override
	public Paciente getPacienteQueReservo() {
		return this.getPaciente();
	}

	@Override
	public ProductoObraSocialPaciente getProductoObraSocialReserva() {
		return this.getProductoObraSocialPaciente();
	}

	@Override
	public boolean estaSinReservar() {
		return false;
	}

	@Override
	public boolean estaReservado() {
		return true;
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
		this.setProductoObraSocialPaciente(obraPac);
	}

	@Override
	public void setDiferenciadoReserva(Float diferenciado) {
		this.setDiferenciado(diferenciado);
	}

	@Override
	public void setHonorariosReserva(Float honorarios) {
		this.setHonorarios(honorarios);
	}

	public ProductoObraSocialPaciente getProductoObraSocialPaciente() {
		return this.productoObraSocialPaciente;
	}

	public void setProductoObraSocialPaciente(
			ProductoObraSocialPaciente productoObraSocialPaciente) {
		this.productoObraSocialPaciente = productoObraSocialPaciente;
	}

	public Rol getPersonalAsignoTurno() {
		return this.personalAsignoTurno;
	}

	public void setPersonalAsignoTurno(Rol personalAsignoTurno) {
		this.personalAsignoTurno = personalAsignoTurno;
	}

	public String getNombreUsuarioPersonalAsignoTurno() {
		return this.nombreUsuarioPersonalAsignoTurno;
	}

	public void setNombreUsuarioPersonalAsignoTurno(
			String nombreUsuarioPersonalAsignoTurno) {
		this.nombreUsuarioPersonalAsignoTurno = nombreUsuarioPersonalAsignoTurno;
	}

	@Override
	public EstadoTurno_VO toValueObject() {
		TurnoReservado_VO turno = new TurnoReservado_VO();

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

		if (this.getPrestacion() != null) {
			turno.setPrestacion(this.getPrestacion().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 1));
		} else {
			turno.setPrestacion(null);
		}

		turno.setPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.toValueObject());
		turno.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		return turno;
	}

	public Prestacion getPrestacion() {
		return this.prestacion;
	}

	public void setPrestacion(Prestacion prestacion) {
		this.prestacion = prestacion;
	}

}