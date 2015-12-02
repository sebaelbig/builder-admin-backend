package ar.com.builderadmin.vo.turnos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.builderadmin.model.turnos.estadosTurno.EstadoTurno;
import ar.com.builderadmin.vo.core.datosSalud.Prestacion_VO;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.Profesional_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoPresente_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoTomado_VO;

/**
 * Componente para el manejo de las obras sociales
 * 
 * @author seba garcia
 */
public class TurnoReservado_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PARTICULAR = "Particular";

	private Long id;

	// <th>N#{messages['u']}mero</th>
	private Integer numero;

	// this.json.fecha
	private String fecha;

	// this.json.hora
	private String hora;

	// this.json.nombre
	private String nombreDia;

	// this.json.estado
	private String estado;

	// <th>Profesional apellido</th>
	// <th>Profesional nombres</th>
	private String apellido;
	private String nombres;
	private Long idProfesional;
	private String usuarioProfesional;

	// <th>Especialidad</th>
	private String especialidad;
	private Long idEspecialidad;

	// <th>Consultorio</th>
	private Integer consultorio;

	/**
	 * Turno RESERVADO
	 */
	private String nombreUsuarioPersonalAsignoTurno;
	private String fechaEstablecido;

	/**
	 * Datos del PRESENTE
	 */
	private Date horaPresentoPaciente;
	private String nombrePersonalRegistroPresencia;

	/**
	 * Datos del TOMADO
	 */
	private Date horaTomoPaciente;
	private String nombrePersonalRegistroToma;
	private String tiempoDeEspera;

	private String nombreProductoOS;

	private Integer duracion;

	private Boolean sobreTurno = false;

	// Prestacion
	private String prestacion;
	private Long idPrestacion;

	// Paciente
	private String nombresPaciente;
	private String apellidoPaciente;
	private Long idPaciente;
	private String usuarioPaciente;

	public TurnoReservado_DTO() {
	}

	public TurnoReservado_DTO(Long idTurno, Integer version, Integer nroTurno,
			Date hora, String nombreDia, String apellidoProfesional,
			String nombreEspecialidad) {
		this.setId(idTurno);
		this.setNumero(nroTurno);
		this.setHora(new SimpleDateFormat("HH:mm").format(hora));
		this.setNombreDia(nombreDia);
		this.setApellido(apellidoProfesional);
		this.setEspecialidad(nombreEspecialidad);
	}

	public TurnoReservado_DTO(Long idTurno, Integer nroTurno, Date fecha,
			Date hora, Integer duracion, Boolean sobreTurno, String estado,
			String nombreDia, Long idPrestacion, String prestacion, String ape,
			String nom, Long idProfesional, String usuarioProfesional,
			String nombreEspecialidad, Long idEspecialidad,
			String nombreProdOS, String usuarioReservo, Date fechaReservo,
			String nombrePersonalRegistroPresencia, Date horaPresentoPaciente,
			String nombrePersonalRegistroToma, Date horaTomoPaciente,
			String tiempoDeEspera, Long idPaciente, String apePac,
			String nombresPac, String usuarioPaciente) {
		this.setId(idTurno);
		this.setNumero(nroTurno);
		this.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		this.setHora(new SimpleDateFormat("HH:mm").format(hora));
		this.setDuracion(duracion);
		this.setSobreTurno(sobreTurno);
		this.setNombreDia(nombreDia);
		this.setEstado(estado);

		this.setApellido(ape);
		this.setNombres(nom);
		this.setIdProfesional(idProfesional);
		this.setUsuarioProfesional(usuarioProfesional);

		this.setEspecialidad(nombreEspecialidad);
		this.setIdEspecialidad(idEspecialidad);
		this.setFechaEstablecido(new SimpleDateFormat("dd/MM/yyyy")
				.format(fechaReservo));

		// Especialidad
		if (!estado.equalsIgnoreCase(EstadoTurno.SIN_RESERVAR)) {
			this.setNombreUsuarioPersonalAsignoTurno(usuarioReservo);
			this.setApellidoPaciente(apePac);
			this.setNombresPaciente(nombresPac);
			this.setIdPaciente(idPaciente);
			this.setUsuarioPaciente(usuarioPaciente);
			this.setNombreProductoOS((nombreProdOS != null) ? nombreProdOS
					: "Particular");
			// Prestacion
			this.setPrestacion(prestacion);
			this.setIdPrestacion(idPrestacion);

			if (estado.equalsIgnoreCase(EstadoTurno.TOMADO)) {
				// Tomado
				this.setHoraPresentoPaciente(horaPresentoPaciente);
				this.setNombrePersonalRegistroPresencia(nombrePersonalRegistroPresencia);
				this.setHoraTomoPaciente(horaTomoPaciente);
				this.setNombrePersonalRegistroToma(nombrePersonalRegistroToma);
				this.setTiempoDeEspera(tiempoDeEspera);
			} else {
				// Presente
				this.setHoraPresentoPaciente(horaPresentoPaciente);
				this.setNombrePersonalRegistroPresencia(nombrePersonalRegistroPresencia);
			}

		} else {
			this.setIdPaciente(null);
			this.setNombreUsuarioPersonalAsignoTurno(null);
			this.setHoraPresentoPaciente(null);
			this.setNombrePersonalRegistroPresencia(null);
			this.setHoraTomoPaciente(null);
			this.setNombrePersonalRegistroToma(null);
			this.setTiempoDeEspera(null);
			this.setApellidoPaciente(null);
			this.setNombresPaciente(null);
			this.setNombreProductoOS(null);
			// Prestacion
			this.setPrestacion(null);
			this.setIdPrestacion(null);
		}
	}

	public TurnoReservado_DTO(Turno_VO turno, Profesional_VO prof) {

		Especialidad_VO espe = turno.getBloqueTurno().getEspecialidadPrestada()
				.getEspecialidad();

		this.setId(turno.getId());
		this.setNumero(turno.getNumero());
		this.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(turno
				.getFecha().getFecha()));
		this.setHora(new SimpleDateFormat("HH:mm").format(turno.getFecha()
				.getHora()));
		this.setDuracion(turno.getDuracion());
		this.setSobreTurno(turno.getSobreTurno());
		this.setNombreDia(turno.getBloqueTurno().getDia().getNombre());
		this.setEstado(turno.getEstado().getNombre());

		this.setApellido(prof.getUsuario().getApellido());
		this.setNombres(prof.getUsuario().getNombres());
		this.setUsuarioProfesional(prof.getUsuario().getNombreUsuario());
		this.setIdProfesional(prof.getId());

		this.setEspecialidad(espe.getNombre());
		this.setIdEspecialidad(espe.getId());
		this.setFechaEstablecido(new SimpleDateFormat("dd/MM/yyyy")
				.format(turno.getEstado().getFechaEstablecido()));

		// Especialidad
		if (!turno.getEstado().getNombre()
				.equalsIgnoreCase(EstadoTurno.SIN_RESERVAR)) {
			// Turno reservado

			TurnoReservado_VO treservado = (TurnoReservado_VO) turno
					.getEstado();
			Prestacion_VO presta = treservado.getPrestacion();
			ProductoObraSocialPaciente_VO os = treservado
					.getProductoObraSocialReserva();
			Usuario_VO pac = treservado.getPaciente().getUsuario();

			this.setNombreUsuarioPersonalAsignoTurno(treservado
					.getPersonalAsignoTurno().getUsuario().getNombreUsuario());
			this.setApellidoPaciente(pac.getApellido());
			this.setNombresPaciente(pac.getNombres());
			this.setIdPaciente(pac.getId());
			this.setUsuarioPaciente(pac.getNombreUsuario());
			this.setNombreProductoOS((os != null) ? os.getProducto()
					.getNombre() : "Particular");
			// Prestacion
			this.setPrestacion(presta.getNombre());
			this.setIdPrestacion(presta.getId());

			if (turno.getEstado().getNombre()
					.equalsIgnoreCase(EstadoTurno.TOMADO)) {
				// Tomado
				TurnoTomado_VO ttomado = (TurnoTomado_VO) treservado;

				this.setHoraPresentoPaciente(ttomado.getHoraPresentoPaciente());
				this.setNombrePersonalRegistroPresencia(ttomado
						.getNombrePersonalRegistroPresencia());
				this.setHoraTomoPaciente(ttomado.getHoraTomoPaciente());
				this.setNombrePersonalRegistroToma(ttomado
						.getNombrePersonalRegistroToma());
				this.setTiempoDeEspera(ttomado.getTiempoDeEspera());
			} else if (turno.getEstado().getNombre()
					.equalsIgnoreCase(EstadoTurno.PRESENTE)) {
				// Presente
				TurnoPresente_VO tpresentado = (TurnoPresente_VO) turno
						.getEstado();

				this.setHoraPresentoPaciente(tpresentado
						.getHoraPresentoPaciente());
				this.setNombrePersonalRegistroPresencia(tpresentado
						.getNombrePersonalRegistroPresencia());
			}

		} else {
			this.setIdPaciente(null);
			this.setNombreUsuarioPersonalAsignoTurno(null);
			this.setHoraPresentoPaciente(null);
			this.setNombrePersonalRegistroPresencia(null);
			this.setHoraTomoPaciente(null);
			this.setNombrePersonalRegistroToma(null);
			this.setTiempoDeEspera(null);
			this.setApellidoPaciente(null);
			this.setNombresPaciente(null);
			this.setNombreProductoOS(null);
			// Prestacion
			this.setPrestacion(null);
			this.setIdPrestacion(null);
		}
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return this.getHora() + " - Nro: " + this.getNumero();
	}

	public String getNombreDia() {
		return this.nombreDia;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Integer getConsultorio() {
		return this.consultorio;
	}

	public void setConsultorio(Integer consultorio) {
		this.consultorio = consultorio;
	}

	public Long getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(Long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public void setIdProfesional(Long idProfesional) {
		this.idProfesional = idProfesional;
	}

	public Long getIdProfesional() {
		return this.idProfesional;
	}

	public String getNombreUsuarioPersonalAsignoTurno() {
		return this.nombreUsuarioPersonalAsignoTurno;
	}

	public void setNombreUsuarioPersonalAsignoTurno(
			String nombreUsuarioPersonalAsignoTurno) {
		this.nombreUsuarioPersonalAsignoTurno = nombreUsuarioPersonalAsignoTurno;
	}

	public String getFechaEstablecido() {
		return this.fechaEstablecido;
	}

	public void setFechaEstablecido(String fechaEstablecido) {
		this.fechaEstablecido = fechaEstablecido;
	}

	public String getNombreProductoOS() {
		return this.nombreProductoOS;
	}

	public void setNombreProductoOS(String nombreProductoOS) {
		this.nombreProductoOS = nombreProductoOS;
	}

	public Boolean getSobreTurno() {
		return this.sobreTurno;
	}

	public void setSobreTurno(Boolean sobreTurno) {
		this.sobreTurno = sobreTurno;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getPrestacion() {
		return this.prestacion;
	}

	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}

	public Long getIdPrestacion() {
		return this.idPrestacion;
	}

	public void setIdPrestacion(Long idPrestacion) {
		this.idPrestacion = idPrestacion;
	}

	public String getNombresPaciente() {
		return this.nombresPaciente;
	}

	public void setNombresPaciente(String nombresPaciente) {
		this.nombresPaciente = nombresPaciente;
	}

	public String getApellidoPaciente() {
		return this.apellidoPaciente;
	}

	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}

	public Long getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public void borrarInformacionPersonal() {
		this.setIdPaciente(null);
		this.setApellidoPaciente(null);
		this.setNombresPaciente(null);
		this.setUsuarioPaciente(null);

		this.setPrestacion(null);
		this.setIdPrestacion(null);

		this.setNombreProductoOS(null);
	}

	public Date getHoraPresentoPaciente() {
		return this.horaPresentoPaciente;
	}

	public void setHoraPresentoPaciente(Date horaPresentoPaciente) {
		this.horaPresentoPaciente = horaPresentoPaciente;
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

	public String getUsuarioProfesional() {
		return this.usuarioProfesional;
	}

	public void setUsuarioProfesional(String usuarioProfesional) {
		this.usuarioProfesional = usuarioProfesional;
	}

	public String getUsuarioPaciente() {
		return this.usuarioPaciente;
	}

	public void setUsuarioPaciente(String usuarioPaciente) {
		this.usuarioPaciente = usuarioPaciente;
	}

}
