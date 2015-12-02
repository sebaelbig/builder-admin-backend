package ar.com.builderadmin.model.core.usuarios.roles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

/**
 * Estudio
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:39 a.m.
 */
//@Entity
@Table( name = "tipo_de_rol")
public class TipoDeRol implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_tipo_de_rol", name = "seq_tipo_de_rol", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_de_rol")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 35)
	private String nombre;

	@Column(length = 10)
	private String codigo;

	public static final String ACTIVO = "ACTIVO";
	public static final String INACTIVO = "INACTIVO";
	
	@Column(name = "id_rol")
	private String id_rol;
	
	private String estado; //ACTIVO - INACTIVO
	private String sitio; //Sitio por default
	private String prioridad; //Prioridad
	private String codigoID_x_default; //Codigo x default

	private String login; //Login
	
	@Column(name = "fecha_modifico")
	private String fechaModifico; //fechaModifico

//	/**
//	 * Tipo de Perfil por defecto
//	 */
//	@OneToOne
//	@JoinColumn(nullable = true, name = "id_tipo_de_perfil")
//	private TipoDePerfil tipoPerfil;
	
	/**
	 * Tipo de ID por defecto
	 */
	@ManyToOne
	@JoinColumn(nullable = true, name = "tipo_de_id")
	private TipoIDHE tipoID;

	// ---------------- CONSTRUCTOR
	public TipoDeRol() {
		super();
	}

	public TipoDeRol(String nombre) {
		super();
		this.nombre = nombre;
	}

	// ------------------------ OPERACIONES
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoDeRol) {
			TipoDeRol o = (TipoDeRol) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		return "Tipo rol: Codigo = " + this.getCodigo() + ", Nombre = "
				+ this.getNombre();
	}

	// ---------------- GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public TipoDeRol_VO toValueObject() {
		return new TipoDeRol_VO(this);
	}
	
//	public TipoDePerfil getTipoPerfil() {
//		return tipoPerfil;
//	}
//
//	public void setTipoPerfil(TipoDePerfil tipoPerfil) {
//		this.tipoPerfil = tipoPerfil;
//	}

	public TipoDeRol_VO toValueObject(int i, int profundidadDeseada) {
		return new TipoDeRol_VO(this, i, profundidadDeseada);
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
	 * @return the sitio
	 */
	public String getSitio() {
		return sitio;
	}

	/**
	 * @param sitio the sitio to set
	 */
	public void setSitio(String sitio) {
		this.sitio = sitio;
	}

	/**
	 * @return the prioridad
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad the prioridad to set
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
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

	/**
	 * @return the id_rol
	 */
	public String getId_rol() {
		return id_rol;
	}

	/**
	 * @param id_rol the id_rol to set
	 */
	public void setId_rol(String id_rol) {
		this.id_rol = id_rol;
	}

	/**
	 * @return the codigoID_x_default
	 */
	public String getCodigoID_x_default() {
		return codigoID_x_default;
	}

	/**
	 * @param codigoID_x_default the codigoID_x_default to set
	 */
	public void setCodigoID_x_default(String codigoID_x_default) {
		this.codigoID_x_default = codigoID_x_default;
	}

	/**
	 * @return the tipoID
	 */
	public TipoIDHE getTipoID() {
		return tipoID;
	}

	/**
	 * @param tipoID the tipoID to set
	 */
	public void setTipoID(TipoIDHE tipoID) {
		this.tipoID = tipoID;
	}
	
	
}
