package ar.com.builderadmin.vo.turnos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import ar.com.builderadmin.model.I_Consumo;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.turnos.Turno;
import ar.com.builderadmin.utils.Admin_JSONCalendario;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.com.builderadmin.vo.turnos.estados.EstadoTurno_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoPresente_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoSinReservar_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoTomado_VO;

/**
 * Componente para el manejo de las obras sociales
 * 
 * @author seba garcia
 */
public class Turno_VO implements Serializable, I_ValueObject<Turno>, I_Consumo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PARTICULAR = "Particular";

	private Long id;
	private Boolean borrado = false;

	private Integer version;

	/**
	 * Duracion del turno
	 */
	private Integer duracion;

	/**
	 * Numero del turno
	 */
	private Integer numero;

	/**
	 * Tipo de turno (por defecto es normal)
	 */
	private Boolean sobreTurno = false;

	/**
	 * Estado del turno
	 */
	private EstadoTurno_VO estado;

	private BloqueTurno_VO bloqueTurno;

	/**
	 * Fecha del turno
	 */
	private Fecha_VO fecha;

	public Turno_VO(Turno turno) {
		setObject(turno);
	}

	public Turno_VO() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void setObject(Turno turno) {

		this.setId(turno.getId());
		this.setVersion(turno.getVersion());

		this.setDuracion(turno.getDuracion());

		this.setBloqueTurno(turno.getBloqueTurno().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setEstado(turno.getEstado().toValueObject());
		this.setFecha(turno.getFecha().toValueObject());
		this.setNumero(turno.getNumero());
		this.setSobreTurno(turno.getSobreTurno());
	}

	@Override
	public Turno toObject() {
		Turno t = new Turno();

		t.setId(this.getId());
		t.setVersion(this.getVersion());

		t.setDuracion(this.getDuracion());

		t.setBloqueTurno(this.getBloqueTurno().toObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		t.setEstado(this.getEstado().toObject());
		t.setFecha(this.getFecha().toObject());
		t.setNumero(this.getNumero());
		t.setSobreTurno(this.getSobreTurno());

		return t;
	}

	@Override
	public void setObject(Turno objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	// Constructor para crear un sobre turno
	public Turno_VO(Turno_VO turno) {
		setBloqueTurno(turno.getBloqueTurno());
		setDuracion(turno.getDuracion());

		Fecha_VO fecha = new Fecha_VO();
		fecha.setFecha(turno.getFecha().getFecha());
		fecha.setHora(new Admin_JSONCalendario().sumarDuracionAHora(turno
				.getFecha().getHora(), turno.getDuracion()));
		setFecha(fecha);

		setNumero(turno.getNumero() + 1);
		setSobreTurno(true);
		setEstado(new TurnoSinReservar_VO());

	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getSobreTurno() {
		return sobreTurno;
	}

	public void setSobreTurno(Boolean sobreTurno) {
		this.sobreTurno = sobreTurno;
	}

	public EstadoTurno_VO getEstado() {
		return estado;
	}

	public void setEstado(EstadoTurno_VO estado) {
		this.estado = estado;
	}

	public BloqueTurno_VO getBloqueTurno() {
		return bloqueTurno;
	}

	public void setBloqueTurno(BloqueTurno_VO bloqueTurno) {
		this.bloqueTurno = bloqueTurno;
	}

	public Fecha_VO getFecha() {
		return fecha;
	}

	public void setFecha(Fecha_VO fecha) {
		this.fecha = fecha;
	}

	public boolean reservar(Paciente_VO pac,
			ProductoObraSocialPaciente_VO prod, Rol_VO rolUsrReservoTurno,
			Float[] costos, Date fechaReserva) {
		EstadoTurno_VO est = null;

		if (this.getEstado().getNombre().equals(EstadoTurno_VO.SIN_RESERVAR)) {
			est = ((TurnoSinReservar_VO) this.getEstado()).reservar(pac, prod,
					rolUsrReservoTurno, costos, fechaReserva);
		} else if (this.getEstado().getNombre()
				.equals(EstadoTurno_VO.RESERVADO)) {
			est = ((TurnoReservado_VO) this.getEstado()).reservar(pac, prod,
					rolUsrReservoTurno, costos, fechaReserva);
		} else if (this.getEstado().getNombre().equals(EstadoTurno_VO.PRESENTE)) {
			est = ((TurnoPresente_VO) this.getEstado()).reservar(pac, prod,
					rolUsrReservoTurno, costos, fechaReserva);
		} else if (this.getEstado().getNombre().equals(EstadoTurno_VO.TOMADO)) {
			est = ((TurnoTomado_VO) this.getEstado()).reservar(pac, prod,
					rolUsrReservoTurno, costos, fechaReserva);
		}

		return est != null;
	}

	@Override
	public String toString() {
		return this.getFecha().getStr_hora() + " - Nro: " + this.getNumero();
	}

	public boolean estaReservado() {
		return this.getEstado().estaReservado();
	}

	public boolean estaPresente() {
		return this.getEstado().estaPresentado();
	}

	public boolean estaTomado() {
		return this.getEstado().estaTomado();
	}

	public boolean esLibre() {
		return this.getEstado().estaTomado()
				|| this.getEstado().estaReservado()
				|| this.getEstado().estaPresentado();
	}

	/**************** I_Consumo ****************************************/
	@Override
	public Integer getCantidad() {
		return 0;
	}

	@Override
	public String getClaseConsumo() {
		return Turno.class.getCanonicalName();
	}

	@Override
	public String getDescripcion() {

		String desc = "Turno del d�a " + this.getFecha().getStr_fecha()
				+ " a las " + this.getFecha().getStr_hora() + " - Nro: "
				+ this.getNumero() + " - " + this.getEstado().getNombre();

		return desc;
	}

	@Override
	public Date getFechaPrecio() {
		// TODO Auto-generated method stub
		return new Date();
	}

	@Override
	public Float getIVA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getIdConsumo() {
		return this.getId();
	}

	@Override
	public String getNombre() {
		return I_Consumo.TURNO;
	}

	@Override
	public BigDecimal getPrecioUnitario() {
		// TODO Auto-generated method stub
		return new BigDecimal(0);
	}

	@Override
	public Servicio getServicio() {
		return null;
	}

	@Override
	public void setCantidad(Integer cantidadNueva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDescripcion(String descripcionNueva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFechaPrecio(Date nuevaFechaPrecio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIVA(Float nuevoIVA) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNombre(String nombreNuevo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPrecioUnitario(BigDecimal precioNuevo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServicio(Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
	
	/**************** FIN I_Consumo ****************************************/

	public void borrarInformacionPersonal() {
		this.getEstado().borrarInformacionPersonal();

	}

	public String getLabel() {
		String resul = "Nro: " + this.getNumero() + " a las "
				+ this.getFecha().getStr_hora() + " hs. ";
		/*
		 * if (this.estaReservado()) resul =
		 * ((TurnoReservado_VO)this.getEstado()).getLabel(); else if
		 * (this.estaPresente()) resul =
		 * ((TurnoPresente_VO)this.getEstado()).getLabel(); else if
		 * (this.estaTomado()) resul =
		 * ((TurnoTomado_VO)this.getEstado()).getLabel();
		 */
		return resul;
	}
}
