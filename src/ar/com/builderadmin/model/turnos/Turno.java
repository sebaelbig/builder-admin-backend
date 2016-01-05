package ar.com.builderadmin.model.turnos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.model.turnos.estadosTurno.EstadoTurno;
import ar.com.builderadmin.model.turnos.estadosTurno.TurnoSinReservar;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.turnos.Turno_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity @Table
public class Turno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_turno", name = "seq_turno", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_turno")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Duracion del turno
	 */
	private Integer duracion;

	/**
	 * Estado del turno
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	private EstadoTurno estado;

	/**
	 * Numero del turno
	 */
	private Integer numero;

	/**
	 * Tipo de turno (por defecto es normal)
	 */
	@Column(name = "sobre_turno")
	private Boolean sobreTurno = false;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bloque_turno")
	private BloqueTurno bloqueTurno;

	/**
	 * Fecha del turno
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_fecha")
	private Fecha fecha;

	public Turno() {
		this.setEstado(new TurnoSinReservar());
		this.setSobreTurno(false);
	}

	public Turno(BloqueTurno bt, Fecha fecha, Date hora, Integer nro,
			Integer duracion) {
		this.setEstado(new TurnoSinReservar());

		this.setSobreTurno(false);
		this.setBloqueTurno(bt);
		fecha.setHora(hora);
		this.setFecha(fecha);
		this.setNumero(nro);
		this.setDuracion(duracion);
	}

	public Turno(BloqueTurno bt, Fecha fecha, Integer nro, Integer duracion) {
		this.setEstado(new TurnoSinReservar());
		this.setSobreTurno(false);
		this.setBloqueTurno(bt);
		this.setFecha(fecha);
		this.setNumero(nro);
		this.setDuracion(duracion);
	}

	public Turno(BloqueTurno bt, Fecha fecha, Integer nro, Integer duracion,
			Boolean sobreTurno) {
		this.setEstado(new TurnoSinReservar());
		this.setSobreTurno(sobreTurno);
		this.setBloqueTurno(bt);
		this.setFecha(fecha);
		this.setNumero(nro);
		this.setDuracion(duracion);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Turno) {
			Turno o = (Turno) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public EstadoTurno getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoTurno estado) {
		this.estado = estado;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BloqueTurno getBloqueTurno() {
		return this.bloqueTurno;
	}

	public void setBloqueTurno(BloqueTurno bloqueTurno) {
		this.bloqueTurno = bloqueTurno;
	}

	public Fecha getFecha() {
		return this.fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public boolean estaReservado() {
		return this.getEstado().estaReservado();
	}

	public boolean estaReservadoPor(Paciente paciente) {
		return this.getEstado().estaReservadoPor(paciente);
	}

	public Paciente getPacienteQueReservo() {
		return this.getEstado().getPacienteQueReservo();
	}

	public boolean estaPresentado() {
		return this.getEstado().estaPresentado();
	}

	public boolean estaTomado() {
		return this.getEstado().estaTomado();
	}

	public void setDiferenciadoReserva(Float diferenciado) {
		this.getEstado().setDiferenciadoReserva(diferenciado);
	}

	public void setHonorariosReserva(Float honorarios) {
		this.getEstado().setHonorariosReserva(honorarios);
	}

	public Boolean getSobreTurno() {
		return this.sobreTurno;
	}

	public void setSobreTurno(Boolean sobreTurno) {
		this.sobreTurno = sobreTurno;
	}

	// this.getPaciente(), this.getProductoOS(), this.getRol(), null, new Date()
	public boolean reservar(Paciente paciente,
			ProductoObraSocialPaciente productoOS, Rol rol, Float[] costos,
			Date fecha, Prestacion presta) {
		EstadoTurno est = this.getEstado().reservar(paciente, productoOS, rol,
				costos, fecha, presta);

		this.setEstado(est);

		return est != null;

	}

	public boolean cancelar() {
		EstadoTurno est = this.getEstado().cancelar();

		this.setEstado(est);

		return est != null;
	}

	public boolean presentar(Date horaPresentado, Rol personalPresentoTurno) {
		EstadoTurno est = this.getEstado().presentar(horaPresentado,
				personalPresentoTurno);

		this.setEstado(est);

		return est != null;
	}

	public boolean presentar(ProductoObraSocialPaciente productoAlPresentar,
			Date horaPresentado, Rol personalPresentoTurno) {
		EstadoTurno est = this.getEstado().presentar(productoAlPresentar,
				horaPresentado, personalPresentoTurno);

		this.setEstado(est);

		return est != null;
	}

	public boolean tomar(Date horaTomado, Rol personalTomoTurno) {
		EstadoTurno est = this.getEstado().tomar(horaTomado, personalTomoTurno);

		this.setEstado(est);

		return est != null;
	}

	public Turno_VO toValueObject() {

		Turno_VO resul = new Turno_VO();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setDuracion(this.getDuracion());

		resul.setBloqueTurno(this.getBloqueTurno().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 5));
		resul.setEstado(this.getEstado().toValueObject());
		resul.setFecha(this.getFecha().toValueObject());
		resul.setNumero(this.getNumero());
		resul.setSobreTurno(this.getSobreTurno());

		return resul;
	}

	public Turno_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return this.toValueObject();
	}

	public boolean libre() {
		return !(this.estaReservado() || this.estaPresentado() || this
				.estaTomado());
	}

	// public String toString(){
	// DateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy"),
	// formatoHora = new SimpleDateFormat("HH:mm");
	//
	// return
	// "Turno "+getTipoTurno().getNombre()+" en estado "+getEstado().getNombre()+". Nro: "+getNumero()+", "
	// +
	// " para el "+formatoDia.format(getFecha().getFecha())+" " +
	// " a las "+formatoHora.format(getFecha().getHora()) ;
	// }
}