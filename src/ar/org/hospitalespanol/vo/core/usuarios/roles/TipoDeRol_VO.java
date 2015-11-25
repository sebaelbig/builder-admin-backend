package ar.org.hospitalespanol.vo.core.usuarios.roles;

import ar.org.hospitalespanol.model.core.usuarios.roles.TipoDeRol;
import ar.org.hospitalespanol.model.core.usuarios.roles.TipoIDHE;
import ar.org.hospitalespanol.vo.I_ValueObject;

import com.google.gson.Gson;

public class TipoDeRol_VO implements I_ValueObject<TipoDeRol> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String nombre;
	private String codigo;
	
	//Perfil
//	private Long idPerfil;
//	private String nombrePerfil;
	
	public static final String ACTIVO = "ACTIVO";
	public static final String INACTIVO = "INACTIVO";
	
	private String id_rol; //ID del rol en el HE
	private String estado = ACTIVO; //ACTIVO - INACTIVO
	private String sitio; //Sitio por default
	private String prioridad; //Prioridad
	private String login; //Login
	private String fechaModifico; //fechaModifico
	
	//Tipo ID
	private Long idTipoID;
	private String codigoID_x_default; //Codigo x default
	
	
	public TipoDeRol_VO() {
	}

	public TipoDeRol_VO(TipoDeRol tipoRol) {
		setObject(tipoRol);
	}
	public TipoDeRol_VO(TipoDeRol tipoRol, Integer profundidadBase, Integer profundidadDesada) {
		setObject(tipoRol,profundidadBase,profundidadDesada);
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(TipoDeRol tr) {
		if (tr != null) {
			
			this.setId(tr.getId());
			this.setVersion(tr.getVersion());
			this.setCodigo(tr.getCodigo());
			this.setNombre(tr.getNombre());
			this.setBorrado(tr.getBorrado());
			this.setEstado(tr.getEstado());
			this.setSitio(tr.getSitio());
			this.setPrioridad(tr.getPrioridad());
			this.setLogin(tr.getLogin());
			this.setFechaModifico(tr.getFechaModifico());
			this.setId_rol(tr.getId_rol());
			this.setFechaModifico(this.getFechaModifico());
			
			if (tr.getTipoID()!=null){
				this.setCodigoID_x_default(tr.getTipoID().getTipoID());
				this.setIdTipoID(tr.getTipoID().getId());
			}
			
//			if (tr.getTipoPerfil()!=null){
//				this.setNombrePerfil(tr.getTipoPerfil().getNombre());
//				this.setIdPerfil(tr.getTipoPerfil().getId());
//				
//			}
				
		}
	}

	@Override
	public TipoDeRol toObject() {
		TipoDeRol tr = new TipoDeRol();
		
		tr.setId(this.getId());
		tr.setVersion(this.getVersion());
		tr.setCodigo(this.getCodigo());
		tr.setNombre(this.getNombre());
		tr.setBorrado(this.getBorrado());
		tr.setEstado(this.getEstado());
		tr.setSitio(this.getSitio());
		tr.setPrioridad(this.getPrioridad());
		tr.setId_rol(this.getId_rol());
		tr.setLogin(this.getLogin());
		tr.setFechaModifico(this.getFechaModifico());
		
		if (tr.getTipoID()!=null){
			tr.setCodigoID_x_default(getCodigoID_x_default());
			TipoIDHE tipoID= new TipoIDHE();
			tipoID.setId(this.getIdTipoID());
			tipoID.setTipoID(this.getCodigoID_x_default());
			tr.setTipoID(tipoID);
		}
		
//		if (tr.getTipoPerfil()!=null){
//			TipoDePerfil tipoP= new TipoDePerfil();
//			tipoP.setId(this.getIdPerfil());
//			tipoP.setNombre(this.getNombrePerfil());
//			tr.setTipoPerfil(tipoP);
//		}
		
		return tr;
	}

	@Override
	public String toString() {
		return "Tipo rol: Codigo = " + this.getCodigo() + ", Nombre = "
				+ this.getNombre();
	}

	@Override
	public void setObject(TipoDeRol tr, int profundidadActual,
			int profundidadDeseada) {
		if (tr != null) {
			
			this.setId(tr.getId());
			this.setVersion(tr.getVersion());
			this.setCodigo(tr.getCodigo());
			this.setNombre(tr.getNombre());
			this.setBorrado(tr.getBorrado());
			this.setEstado(tr.getEstado());
			this.setSitio(tr.getSitio());
			this.setPrioridad(tr.getPrioridad());
			this.setLogin(tr.getLogin());
			this.setFechaModifico(tr.getFechaModifico());
			this.setId_rol(tr.getId_rol());
			this.setFechaModifico(this.getFechaModifico());
			
			// Se chequea que no se halla llegado a la profundidad deseada
			if (profundidadActual < profundidadDeseada) {
				
				if (tr.getTipoID()!=null){
					this.setCodigoID_x_default(tr.getTipoID().getTipoID());
					this.setIdTipoID(tr.getTipoID().getId());
				}
				
			}
			
		}
	}

	public TipoDeRol toObject(int profundidadActual, int profundidadDeseada) {
		return this.toObject();
	}

//	public Rol_VO crearRol(Usuario_VO usr) {
//
//		Rol_VO r = null;
//
//		if (this.getNombre().equals(Rol.PACIENTE)) {
//			r = new Paciente_VO(this, usr);
//		} else if (this.getNombre().equals(Rol.PROFESIONAL)) {
//			r = new Profesional_VO(this, usr);
//		} else {
//			r = new Rol_VO(this, usr);
//		}
//
//		return r;
//	}

	public String toJson() {
		return new Gson().toJson(this);
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
	 * @return the idTipoID
	 */
	public Long getIdTipoID() {
		return idTipoID;
	}

	/**
	 * @param idTipoID the idTipoID to set
	 */
	public void setIdTipoID(Long idTipoID) {
		this.idTipoID = idTipoID;
	}

//	/**
//	 * @return the nombrePerfil
//	 */
//	public String getNombrePerfil() {
//		return nombrePerfil;
//	}
//
//	/**
//	 * @param nombrePerfil the nombrePerfil to set
//	 */
//	public void setNombrePerfil(String nombrePerfil) {
//		this.nombrePerfil = nombrePerfil;
//	}

//	/**
//	 * @return the idPerfil
//	 */
//	public Long getIdPerfil() {
//		return idPerfil;
//	}
//
//	/**
//	 * @param idPerfil the idPerfil to set
//	 */
//	public void setIdPerfil(Long idPerfil) {
//		this.idPerfil = idPerfil;
//	}
	
	@Override
	public boolean equals(Object object) {
		if ((object instanceof TipoDeRol_VO)) {
			TipoDeRol_VO tp = (TipoDeRol_VO) object;
			return tp.getId().equals(getId()) 
					&& tp.getVersion().equals(getVersion());
		}
		return false;
	}

}