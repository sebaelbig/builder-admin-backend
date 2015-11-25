package ar.org.hospitalespanol.model.core.usuarios.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.vo.core.usuarios.TipoIDHE_VO;

@Entity
@Table( name = "tipo_id")
public class TipoIDHE implements I_Entidad{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;
	

	@Id
	@SequenceGenerator( sequenceName = "seq_tipo_id", name = "seq_tipo_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_id")
	private Long id;

	@Version
	private Integer version;
	
	public static final String ACTIVO = "Activo";
	public static final String INACTIVO = "Inactivo";
	
	@Column(name="id_tipo_id")
	private String id_tipoId;
	
	@Column(name="tipo_id")
	private String tipoID;
	private String estado;
	private String login;
	
	@Column(name="fecha_modifico")
	private String fechaModifico;
	
	public TipoIDHE(){}
	
	public TipoIDHE_VO toValueObject(){
		return new TipoIDHE_VO(this);
		
	}
	public TipoIDHE_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return this.toValueObject();
	}
	
	@Override
	public String toString() {
		return "Rol: "+this.getTipoID()+" ("+getId_tipoId()+") - "+getEstado();
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
//		return this.getEstado()!= INACTIVO;

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
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	@Override
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the id_tipoId
	 */
	public String getId_tipoId() {
		return id_tipoId;
	}

	/**
	 * @param id_tipoId the id_tipoId to set
	 */
	public void setId_tipoId(String id_tipoId) {
		this.id_tipoId = id_tipoId;
	}

	/**
	 * @return the tipoID
	 */
	public String getTipoID() {
		return tipoID;
	}

	/**
	 * @param tipoID the tipoID to set
	 */
	public void setTipoID(String tipoID) {
		this.tipoID = tipoID;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaModifico
	 */
	public String getFechaModifico() {
		return fechaModifico;
	}

	/**
	 * @param fechaModifico the fechaModifico to set
	 */
	public void setFechaModifico(String fechaModifico) {
		this.fechaModifico = fechaModifico;
	}


	
	
}