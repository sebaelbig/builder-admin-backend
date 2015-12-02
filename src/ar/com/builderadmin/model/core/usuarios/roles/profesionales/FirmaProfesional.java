package ar.com.builderadmin.model.core.usuarios.roles.profesionales;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import ar.com.builderadmin.model.I_Entidad;

//@Entity
@Table( name="firma_profesional")
public class FirmaProfesional implements I_Entidad {

	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "firmaProfesional_seq")
	@SequenceGenerator( name = "firmaProfesional_seq", sequenceName = "firmaProfesional_seq", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	@Version
	private Integer version;

	private Boolean borrado = false;

	@Column(name="email")
	private String email="";
	
	@Column(name="nro_matricula")
	private String nroMatricula;
	
	@Column(name="nombre_apellido")
	private String nombreApellido;
	
	@Enumerated(EnumType.STRING)
	private TipoDeMatricula tipoDeMatricula;
	
	@Column(name="especialidad")
	private String especialidad;
	
	@Column(name="especialidad_renglon")
	private String especialidad_renglon;

	public FirmaProfesional() {}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the tipoDeMatricula
	 */
	public TipoDeMatricula getTipoDeMatricula() {
		return tipoDeMatricula;
	}

	/**
	 * @param tipoDeMatricula the tipoDeMatricula to set
	 */
	public void setTipoDeMatricula(TipoDeMatricula tipoDeMatricula) {
		this.tipoDeMatricula = tipoDeMatricula;
	}

	/**
	 * @return the nroMatricula
	 */
	public String getNroMatricula() {
		return nroMatricula;
	}

	/**
	 * @param nroMatricula the nroMatricula to set
	 */
	public void setNroMatricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}

	/**
	 * @return the nombreApellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * @param nombreApellido the nombreApellido to set
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * @return pra el seg renglon
	 */
	public String getEspecialidad_renglon() {
		return especialidad_renglon;
	}
	
	/**
	 * @param especialidad_renglon para el seg renglon
	 */
	public void setEspecialidad_renglon(String especialidad_renglon) {
		this.especialidad_renglon = especialidad_renglon;
	}
	
	
	
}