package ar.org.hospitalespanol.vo.core.usuarios;

import ar.org.hospitalespanol.model.core.usuarios.roles.TipoIDHE;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class TipoIDHE_VO implements I_ValueObject<TipoIDHE>{

	private Long id;
	
	public static final String ACTIVO = "Activo";
	public static final String INACTIVO = "Inactivo";
	
	private String id_tipoId;
	private String tipoID;
	private String estado;
	private String login;
	private String fechaModifico;
	private Integer version;
	
	private Boolean borrado = false;
	
	public TipoIDHE_VO() {
	}
	public TipoIDHE_VO(TipoIDHE tipo) {
		this.setObject(tipo);
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
	@Override
	public void setId(Long id) {
		this.id = id;
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

	public TipoIDHE toObject(int a, int b) {
		return toObject();
	}
	
	@Override
	public TipoIDHE toObject() {
		TipoIDHE t = new TipoIDHE();
		
		t.setBorrado(this.getBorrado());
		t.setEstado(this.getEstado());
		t.setFechaModifico(this.getFechaModifico());
		t.setId(this.getId());
		t.setId_tipoId(this.getId_tipoId());
		t.setLogin(this.getLogin());
		t.setTipoID(this.getTipoID());
		t.setVersion(getVersion());
		return t;
	}

	@Override
	public void setObject(TipoIDHE paramT) {
		
		this.setBorrado(paramT.getBorrado());
		this.setEstado(paramT.getEstado());
		this.setFechaModifico(paramT.getFechaModifico());
		this.setId(paramT.getId());
		this.setId_tipoId(paramT.getId_tipoId());
		this.setLogin(paramT.getLogin());
		this.setTipoID(paramT.getTipoID());
		this.setVersion(paramT.getVersion());
		
	}

	@Override
	public void setObject(TipoIDHE paramT, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(paramT);
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

//	return this.getEstado()!= INACTIVO;
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean deleted) {
		this.borrado = deleted;
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			if (this.getId()!=null && this.getVersion()!=null)
				return this.getId().equals(((TipoIDHE_VO) obj).getId())
						&&  this.getVersion().equals(((TipoIDHE_VO) obj).getVersion());
			else
				return false;
		} catch (Exception e) {
			return false;
		}
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

}