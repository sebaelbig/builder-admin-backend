package ar.org.hospitalespanol.vo.core.usuarios.roles;

import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.TipoIDHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.TipoDePerfil_VO;

import com.google.gson.Gson;

public class Rol_VO implements I_ValueObject<Rol> {

	public static final String DIRECTIVO = "Directivo";
	public static final String PACIENTE = "Paciente";
	public static final String PROFESIONAL = "Profesional";
	public static final String SECRETARIA = "Secretaria";
	public static final String PERSONAL_CONTABLE = "Personal contable";
	public static final String ADMINISTRADOR_CONSULTORIOS = "Administrador de consultorios";
	public static final String SOLICITANTE = "Solicitante";
	public static final String ADMINISTRADOR_HORUS = "Administrador Horus";

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String nombre;
	private String codigo;
	
	private Usuario_VO usuario;
	
	private TipoDeRol_VO tipoRol;
	private List<Perfil_VO> perfiles = new ArrayList<Perfil_VO>();
	
	private TipoIDHE_VO tipoId; 
	private String valorTipoID;
	
	/***********************************************************/

//	public static final String ACTIVO = "ACTIVO";
//	public static final String INACTIVO = "INACTIVO";
	
//	private String id_rol; Ahora es el Codigo
//	private String rol; Ahora es el Nombre
	private String estado;
	private String sitio;
	private String prioridad;
	private String login;
	private String fechaModifico;
	
	//Tipo de ID por defecto
	private String codigoID_x_default;
	
	public Rol_VO() {}

	public Rol_VO(Rol r) {
		setObject(r);
	}

	public Rol_VO(Rol rol, int profundidadActual, int profundidadDeseada) {
		this.setObject(rol, profundidadActual, profundidadDeseada);
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return this.codigo;
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
	public Long getId() {
		return this.id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public String toString() {
		String resul = getNombre();
		if (getUsuario() != null) {
			resul = getUsuario().toString();
		}
		return resul;
	}

//	public boolean tienePerfil(TipoDePerfil_VO tp) {
//		boolean resul = false;
//		for (Perfil_VO p : getPerfiles()) {
//			resul = (resul)
//					|| (p.getTipoPerfil().getCodigo().equals(tp.getCodigo()));
//		}
//		return resul;
//	}

//	public boolean tienePerfilConServicio(TipoDePerfil_VO tp, Servicio_VO s) {
//		boolean resul = false;
//		for (Perfil_VO p : getPerfiles()) {
//			resul = (resul)
//					|| ((p.getTipoPerfil().getCodigo().equals(tp.getCodigo())) 
//							&& (p.getServicio().getCodigo().equals(s.getCodigo())));
//		}
//		return resul;
//	}

//	public void marcarParaEliminar() {
//		for (Perfil_VO p : getPerfiles()) {
//			p.setBorrar(Boolean.valueOf(true));
//		}
//		setUsuario(null);
//
//		setBorrar(Boolean.valueOf(true));
//	}

	public void marcarParaEliminarPerfil(TipoDePerfil_VO tp) {
		for (Perfil_VO p : getPerfiles()) {
			if (p.getTipoPerfil().equals(tp)) {
				p.setBorrado(Boolean.valueOf(true));
			}
		}
	}

	public List<Perfil_VO> getPerfiles() {
		return this.perfiles;
	}

	public void setPerfiles(List<Perfil_VO> perfiles) {
		this.perfiles = perfiles;
	}

	public void agregarPerfil(Perfil_VO p) {
		getPerfiles().add(p);
	}

	public void quitarPerfil(Perfil_VO p) {
		getPerfiles().remove(p);
	}

	public void agregarPerfil(TipoDePerfil_VO tp) {
		getPerfiles().add(new Perfil_VO(tp));
	}

	public Usuario_VO getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario_VO usr) {
		this.usuario = usr;
	}

	public TipoDeRol_VO getTipoRol() {
		return this.tipoRol;
	}

	public void setTipoRol(TipoDeRol_VO tr) {
		this.tipoRol = tr;
		if (tr != null) {
			setNombre(tr.getNombre());
			setCodigo(tr.getCodigo());
		}
	}

	public boolean tengoPerfil(TipoDePerfil_VO tp) {
		boolean resul = false;
		for (Perfil_VO r : getPerfiles()) {
			resul = (resul)
					|| (r.getTipoPerfil().getCodigo().equals(tp.getCodigo()));
		}
		return resul;
	}

	public Perfil_VO recuperarPerfil(TipoDePerfil_VO tp) {
		Perfil_VO resul = null;
		for (Perfil_VO r : getPerfiles()) {
			if (r.getTipoPerfil().getCodigo().equals(tp.getCodigo())) {
				resul = r;
			}
		}
		return resul;
	}

	public boolean tengoPerfil(Perfil_VO p) {
		boolean resul = false;
		for (Perfil_VO r : getPerfiles()) {
			resul = (resul) || (r.getCodigo().equals(p.getCodigo()));
		}
		return resul;
	}

	public void quitarPerfil(TipoDePerfil_VO tp) {
		Perfil_VO resul = null;
		for (Perfil_VO p : getPerfiles()) {
			if (p.getTipoPerfil().getCodigo().equals(tp.getCodigo())) {
				resul = p;
			}
		}
		getPerfiles().remove(resul);
//		resul.setBorrar(Boolean.valueOf(true));
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public Rol toObject() {

		Rol resul = new Rol(this.getId(), this.getVersion(), this.getBorrado());

		resul.setNombre(this.getNombre());
		resul.setCodigo(this.getCodigo());
		
		for (Perfil_VO p_vo : this.getPerfiles()) {
			resul.getPerfiles().add(p_vo.toObject());
		}

		if (this.getUsuario() != null) {
			resul.setUsuario(this.getUsuario().toObject());
		}

		if (this.getTipoRol() != null) {
			resul.setTipoRol(this.getTipoRol().toObject());
		}
		
		if (this.getTipoId() != null) {
			resul.setTipoID(this.getTipoId().toObject());
			resul.setValorTipoID(getValorTipoID());
		}

		return resul;
	}

	public Rol toObject(int profundidadActual, int profundidadDeseada) {

		Rol resul = new Rol(this.getId(), this.getVersion(), this.getBorrado());
		
		resul.setNombre(this.getNombre());
		resul.setCodigo(this.getCodigo());
		
		if (profundidadActual < profundidadDeseada) {

			for (Perfil_VO p_vo : this.getPerfiles()) {
				resul.getPerfiles()
						.add(p_vo.toObject(profundidadActual + 1,
								profundidadDeseada));
			}

			if (this.getUsuario() != null) {
				// Llamada hacia "atras", solo un nivel
				resul.setUsuario(this.getUsuario().toObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

			if (this.getTipoRol() != null) {
				resul.setTipoRol(this.getTipoRol().toObject(
						profundidadActual + 1, profundidadDeseada));
			}
			
			if (this.getTipoId() != null) {
				resul.setTipoID(this.getTipoId().toObject());
				resul.setValorTipoID(getValorTipoID());
			}
		}

		return resul;
	}

	@Override
	public void setObject(Rol r) {

		this.setId(r.getId());
		this.setVersion(r.getVersion());
		this.setBorrado(r.getBorrado());
		
		this.setNombre(r.getNombre());
		this.setCodigo(r.getCodigo());

		this.setEstado(r.getTipoRol().getEstado());
		this.setSitio(r.getTipoRol().getSitio());
		this.setPrioridad(r.getTipoRol().getPrioridad());
		this.setLogin(r.getTipoRol().getLogin());
		this.setFechaModifico(r.getTipoRol().getFechaModifico());
		
		if (r.getUsuario() != null) {
			// Tomo los datos basicos del usuario
			this.setUsuario(r.getUsuario().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}

		if (r.getPerfiles() != null) {

			List<Perfil_VO> prfs = new ArrayList<Perfil_VO>();

			for (Perfil p : r.getPerfiles()) {
				prfs.add(p.toValueObject());
			}

			this.setPerfiles(prfs);
		}

		if (r.getTipoRol() != null) {
			this.setTipoRol(r.getTipoRol().toValueObject());
		}
		
		if (r.getTipoID() != null) {
			this.setValorTipoID(r.getValorTipoID());
			this.setTipoId(r.getTipoID().toValueObject());
		}

	}

	@Override
	public void setObject(Rol r, int profundidadActual, int profundidadDeseada) {

		this.setId(r.getId());
		this.setVersion(r.getVersion());
		this.setBorrado(r.getBorrado());
		
		this.setNombre(r.getNombre());
		this.setCodigo(r.getCodigo());

		this.setEstado(r.getTipoRol().getEstado());
		this.setSitio(r.getTipoRol().getSitio());
		this.setPrioridad(r.getTipoRol().getPrioridad());
		this.setLogin(r.getTipoRol().getLogin());
		this.setFechaModifico(r.getTipoRol().getFechaModifico());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			if (r.getUsuario() != null) {
				// Tomo los datos basicos del usuario
				this.setUsuario(r.getUsuario().toValueObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

			if (r.getPerfiles() != null) {

				List<Perfil_VO> prfs = new ArrayList<Perfil_VO>();

				for (Perfil p : r.getPerfiles()) {
					prfs.add(p.toValueObject(profundidadActual + 1,
							profundidadDeseada));
				}

				this.setPerfiles(prfs);
			}

			if (r.getTipoRol() != null) {
				this.setTipoRol(r.getTipoRol().toValueObject(
						profundidadActual + 1, profundidadDeseada));
			}
			
			if (r.getTipoID() != null) {
				this.setValorTipoID(r.getValorTipoID());
				this.setTipoId(r.getTipoID().toValueObject());
			}

		}
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
	 * @return the tipoId
	 */
	public TipoIDHE_VO getTipoId() {
		return tipoId;
	}

	/**
	 * @param tipoId the tipoId to set
	 */
	public void setTipoId(TipoIDHE_VO tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * @return the valorTipoID
	 */
	public String getValorTipoID() {
		return valorTipoID;
	}

	/**
	 * @param valorTipoID the valorTipoID to set
	 */
	public void setValorTipoID(String valorTipoID) {
		this.valorTipoID = valorTipoID;
	}

	/**
	 * @return the id_rol
	 */
	public String getId_rol() {
		return this.getCodigo();
	}

	/**
	 * @param id_rol the id_rol to set
	 */
	public void setId_rol(String id_rol) {
		this.setCodigo(id_rol);
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return this.nombre;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.setNombre(rol);
	}

//	/**
//	 * @return the estado
//	 */
//	public String getEstado() {
//		return estado;
//	}
//
//	/**
//	 * @param estado the estado to set
//	 */
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}
//
//	/**
//	 * @return the sitio
//	 */
//	public String getSitio() {
//		return sitio;
//	}
//
//	/**
//	 * @param sitio the sitio to set
//	 */
//	public void setSitio(String sitio) {
//		this.sitio = sitio;
//	}
//
//	/**
//	 * @return the prioridad
//	 */
//	public String getPrioridad() {
//		return prioridad;
//	}
//
//	/**
//	 * @param prioridad the prioridad to set
//	 */
//	public void setPrioridad(String prioridad) {
//		this.prioridad = prioridad;
//	}
//
//	/**
//	 * @return the login
//	 */
//	public String getLogin() {
//		return login;
//	}
//
//	/**
//	 * @param login the login to set
//	 */
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//	/**
//	 * @return the fechaModifico
//	 */
//	public String getFechaModifico() {
//		return fechaModifico;
//	}
//
//	/**
//	 * @param fechaModifico the fechaModifico to set
//	 */
//	public void setFechaModifico(String fechaModifico) {
//		this.fechaModifico = fechaModifico;
//	}

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

	public void refreshValues() {
		this.setTipoRol(this.getTipoRol());
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

	@Override
	public boolean equals(Object object) {
		if ((object instanceof Rol_VO)) {
			Rol_VO r = (Rol_VO) object;
			return r.getId().equals(getId()) 
					&& r.getVersion().equals(getVersion());
		}
		return false;
	}

	public void prepareToJson() {
		
		this.getUsuario().getRoles().clear();
		for (Perfil_VO per : getPerfiles()) {
			per.prepareToJson();
		}
		
	}
	
}