package ar.com.builderadmin.ldap.modelo;

import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class RolDeUsuarioHE {

	private String usuario;
	
	private String rol;
	// ID del rol
	private String id_rol;

	// Tipo de ID
	private String codigo_tipo_id;
	private String idhe;

	// Usuario asigno el rol
	private String login;
	// Estado de la relación
	private String estado;
	// Fecha de ult. Modific.
	private String fecha;

	
	public RolDeUsuarioHE() {
	}

	public RolDeUsuarioHE(Rol_VO rol) {
		this.setUsuario(rol.getUsuario().getUsuario());
		this.setEstado(rol.getEstado());
		this.setId_rol(rol.getCodigo());
		this.setCodigo_tipo_id(rol.getTipoId().getId_tipoId());
		this.setIdhe(rol.getValorTipoID());
		this.setLogin(rol.getLogin());
	}

	/**
	 * @return the id_rol
	 */
	public String getId_rol() {
		return id_rol;
	}

	/**
	 * @param id_rol
	 *            the id_rol to set
	 */
	public void setId_rol(String id_rol) {
		this.id_rol = id_rol;
	}

	/**
	 * @return the codigo_tipo_id
	 */
	public String getCodigo_tipo_id() {
		return codigo_tipo_id;
	}

	/**
	 * @param codigo_tipo_id
	 *            the codigo_tipo_id to set
	 */
	public void setCodigo_tipo_id(String codigo_tipo_id) {
		this.codigo_tipo_id = codigo_tipo_id;
	}

	/**
	 * @return the idhe
	 */
	public String getIdhe() {
		return idhe;
	}

	/**
	 * @param idhe
	 *            the idhe to set
	 */
	public void setIdhe(String idhe) {
		this.idhe = idhe;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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

}
