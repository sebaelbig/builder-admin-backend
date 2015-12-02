package ar.com.builderadmin.vo.turnos;

import java.io.Serializable;
import java.util.Date;

import ar.com.builderadmin.model.turnos.Turno;
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
public class Turno_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PARTICULAR = "Particular";

	private Long id;
	private Integer version;

	/**
	 * Numero del turno
	 */
	private Integer numero;

	/**
	 * Duracion del turno
	 */
	private Integer duracion;

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
	private String str_hora;

	/**
	 * <th>Estado turno</th> <th>Paciente</th> <th>Nombre Obra Social</th> <th>
	 * Tipo de turno</th> <th>Prestacion</th> <th>Acciones</th>
	 */

	public Turno_DTO(Turno turno) {
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
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

}
