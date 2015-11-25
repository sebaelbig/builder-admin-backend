package ar.org.hospitalespanol.vo.core.usuarios.roles;

import ar.org.hospitalespanol.vo.I_ValueObject;

public class RolHE_VO implements I_ValueObject<RolHE_VO> {

	public static final String ACTIVO = "ACTIVO";
	public static final String INACTIVO = "INACTIVO";
	
	private Long id;
	private Integer version;
	
	
	private String id_rol;
	private String rol;
	private String estado;
	private String sitio;
	private String prioridad;
	private String login;
	private String fechaModifico;
	
	//Tipo de ID por defecto
	private String codigoID_x_default;
	
	public RolHE_VO() {

	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id_rol = id;
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

	@Override
	public RolHE_VO toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(RolHE_VO paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObject(RolHE_VO paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
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

	@Override
	public String toString() {
		return "Rol: "+this.getRol()+" ("+getId_rol()+") - "+getEstado();
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

	@Override
	public Boolean getBorrado() {
		return this.getEstado()!= INACTIVO;
	}

	@Override
	public void setBorrado(Boolean deleted) {
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
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
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}


}