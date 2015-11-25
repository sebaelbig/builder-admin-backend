package ar.org.hospitalespanol.vo.turnos.estados;

import java.io.Serializable;
import java.util.Date;

import ar.org.hospitalespanol.model.turnos.estadosTurno.EstadoTurno;
import ar.org.hospitalespanol.model.turnos.estadosTurno.TurnoSinReservar;
import ar.org.hospitalespanol.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class TurnoSinReservar_VO extends EstadoTurno_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public TurnoSinReservar_VO() {
		super();
		setNombre(EstadoTurno_VO.SIN_RESERVAR);
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
	public void setProductoObraSocialReserva(
			ProductoObraSocialPaciente_VO obraPac) {
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
	public EstadoTurno_VO cancelar() {
		// Ya esta cancelado/Disponible, se devuelve el mismo
		return this;
	}

	@Override
	public boolean estaReservadoPor(Paciente_VO paciente) {
		// No esta reservado, por lo tanto devuelve falso
		return false;
	}

	@Override
	public Paciente_VO getPacienteQueReservo() {
		// No esta reservado, por lo tanto devuelve null
		return null;
	}

	@Override
	public EstadoTurno_VO presentar(Date horaPresentado, Rol_VO rolPresentoTurno) {
		// Solo se puede presentar si esta reservado
		return null;
	}

	@Override
	public EstadoTurno_VO tomar(Date horaTomado, Rol_VO rolTomoTurno) {
		// Solo se puede tomar si esta presentado
		return null;
	}

	@Override
	public EstadoTurno_VO reservar(Paciente_VO paciente,
			ProductoObraSocialPaciente_VO productoOSPaciente,
			Rol_VO asignoTurno, Float[] costos, Date fechaReservado) {

		return new TurnoReservado_VO(paciente, productoOSPaciente, asignoTurno,
				costos, fechaReservado);
	}

	@Override
	public ProductoObraSocialPaciente_VO getProductoObraSocialReserva() {
		// No esta reservado, por lo tanto devuelve null
		return null;
	}

	@Override
	public void setObject(EstadoTurno objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
	}

	@Override
	public void setObject(EstadoTurno objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());

	}

	@Override
	public EstadoTurno toObject() {
		TurnoSinReservar resul = new TurnoSinReservar();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setFechaEstablecido(this.getFechaEstablecido());
		return resul;
	}

	@Override
	public String getLabel() {
		return "Sin reservar";
	}

}