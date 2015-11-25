package ar.org.hospitalespanol.vo.turnos.estados;

import java.util.Date;

import ar.org.hospitalespanol.model.turnos.estadosTurno.EstadoTurno;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.datosSalud.Prestacion_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class EstadoTurno_VO implements I_ValueObject<EstadoTurno> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	public static final String RESERVADO = "Reservado";
	public static final String SIN_RESERVAR = "Sin reservar";
	public static final String TOMADO = "Tomado";
	public static final String PRESENTE = "Presente";

	private String nombre;

	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	private Date fechaEstablecido;

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoTurno_VO) {
			EstadoTurno_VO o = (EstadoTurno_VO) objeto;
			return o.getNombre().equals(this.getNombre());
		}
		return false;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaEstablecido() {
		return fechaEstablecido;
	}

	public void setFechaEstablecido(Date fechaEstablecido) {
		this.fechaEstablecido = fechaEstablecido;
	}

	public boolean estaReservado() {
		return false;
	}

	public boolean estaPresentado() {
		return false;
	}

	public boolean estaTomado() {
		return false;
	}

	public boolean estaSinReservar() {
		return false;
	}

	public EstadoTurno_VO() {
		setFechaEstablecido(new Date());
	}

	public EstadoTurno_VO cancelar() {
		return null;
	}

	public EstadoTurno_VO reservar(Paciente_VO paciente,
			ProductoObraSocialPaciente_VO obraSocialPaciente,
			Rol_VO nombreUsrDio, Float[] costos, Date fechaReserva) {
		return null;
	}

	public EstadoTurno_VO presentar(Date horaPresentado, Rol_VO rolPresentoTurno) {
		return null;
	}

	public EstadoTurno_VO tomar(Date horaTomado, Rol_VO rolTomoTurno) {
		return null;
	}

	public boolean estaReservadoPor(Paciente_VO paciente) {
		return false;
	}

	public Paciente_VO getPacienteQueReservo() {
		return null;
	}

	public ProductoObraSocialPaciente_VO getProductoObraSocialReserva() {
		return null;
	}

	public void setProductoObraSocialReserva(
			ProductoObraSocialPaciente_VO obraPac) {
	}

	public void setDiferenciadoReserva(Float diferenciado) {
	}

	public void setHonorariosReserva(Float honorarios) {
	}

	public Prestacion_VO getPrestacion() {
		return null;
	}

	@Override
	public void setObject(EstadoTurno objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObject(EstadoTurno objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstadoTurno toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public void borrarInformacionPersonal() {
		// Solo quita la subclase reservado
	}

	public String getLabel() {
		// Lo ejecuta la clase hija
		return null;
	}

}