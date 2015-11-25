package ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes;

import ar.org.hospitalespanol.model.core.usuarios.roles.pacientes.PacienteAsistidor;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class PacienteAsistidor_VO implements I_ValueObject<PacienteAsistidor> {
	
	private Long id;
	private Integer version;
	private Boolean borrado = false;



	private String nombre;
	private String apellido;
	private String nombres;
	private String nroDocumento;
	private Long idUsuario;
	private String idPerfil;
	private Long idPaciente;

	public PacienteAsistidor_VO() {
	}

	public PacienteAsistidor_VO(PacienteAsistidor pac) {
		this.setObject(pac);
	}

	public PacienteAsistidor_VO(PacienteAsistidor pac, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(pac, profundidadActual, profundidadDeseada);
	}

	public PacienteAsistidor toObject(int profundidadActual,
			int profundidadDeseada) {

		PacienteAsistidor resul = new PacienteAsistidor();

		resul.setId(this.getId());
		resul.setNombre(this.getNombre());
		resul.setNombres(this.getNombres());
		resul.setApellido(this.getApellido());
		resul.setNroDocumento(this.getNroDocumento());
		resul.setIdUsuario(this.getIdUsuario());
		resul.setIdPaciente(this.getIdPaciente());

		return resul;

	}

	@Override
	public PacienteAsistidor toObject() {

		PacienteAsistidor resul = new PacienteAsistidor();

		resul.setId(this.getId());
		resul.setNombre(this.getNombre());
		resul.setNombres(this.getNombres());
		resul.setApellido(this.getApellido());
		resul.setIdUsuario(this.getIdUsuario());
		resul.setIdPaciente(this.getIdPaciente());
		resul.setNroDocumento(this.getNroDocumento());

		return resul;

	}

	@Override
	public void setObject(PacienteAsistidor r) {

		this.setId(r.getId());
		this.setNombre(r.getNombre());
		this.setNombres(r.getNombres());
		this.setApellido(r.getApellido());
		this.setNroDocumento(r.getNroDocumento());
		this.setIdUsuario(r.getIdUsuario());
		this.setIdPaciente(r.getIdPaciente());

	}

	@Override
	public void setObject(PacienteAsistidor r, int profundidadActual,
			int profundidadDeseada) {

		this.setId(r.getId());
		this.setNombre(r.getNombre());
		this.setNombres(r.getNombres());
		this.setApellido(r.getApellido());
		this.setIdUsuario(r.getIdUsuario());
		this.setIdPaciente(r.getIdPaciente());
		this.setNroDocumento(r.getNroDocumento());

	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Integer getVersion() {
		return Integer.valueOf(0);
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

	public String getNroDocumento() {
		return this.nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Override
	public String toString() {
		return getApellido() + ", " + getNombres() + " (" + getNroDocumento()
				+ ")";
	}

	/**
	 * @return the borrado
	 */
	@Override
	public Boolean getBorrado() {
		return borrado;
	}

	/**
	 * @param borrado the borrado to set
	 */
	@Override
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}