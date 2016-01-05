package ar.com.builderadmin.model.core.usuarios.roles.pacientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.PacienteAsistidor_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
@Entity @Table( name = "paciente_asistidor")
public class PacienteAsistidor {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_paciente_asistidor", name = "seq_paciente_asistidor", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paciente_asistidor")
	private Long id;

	/**
	 * Nombre del rol el cual se autentica
	 */
	@Column(length = 200)
	private String nombre;

	/**
	 * Apellido del usuario
	 */
	@Column(length = 30)
	private String apellido;

	/**
	 * Usuario del rol
	 */
	@Column(length = 200)
	private String nombres;

	@Column(length = 30, name = "nro_documento")
	private String nroDocumento;

	@Column(name = "id_usuario")
	private Long idUsuario;

	@Column(name = "id_perfil")
	private Long idPerfil;

	@Column(name = "id_paciente")
	private Long idPaciente;

	public PacienteAsistidor() {
		setNombre(Rol.PACIENTE);
	}

	public PacienteAsistidor(Usuario usuarioActual) {
		setNombre(Rol.PACIENTE);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PacienteAsistidor) {
			PacienteAsistidor o = (PacienteAsistidor) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public PacienteAsistidor_VO toValueObject() {
		return new PacienteAsistidor_VO(this);
	}

	public PacienteAsistidor_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new PacienteAsistidor_VO(this, profundidadActual,
				profundidadDeseada);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

}