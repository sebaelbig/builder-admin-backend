package ar.org.hospitalespanol.vo.core.usuarios.perfiles;

import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.usuarios.funciones.FuncionHorus;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.vo.FuncionHorus_VO;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class Perfil_VO implements I_ValueObject<Perfil> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String nombre;
	private String codigo;
	
	private TipoDePerfil_VO tipoPerfil;
	private List<FuncionHorus_VO> funciones = new ArrayList<FuncionHorus_VO>();
	
	private Long idRol;
	private String nombreRol;
//	private Rol_VO rol;
	
	private Long idServicio;
	private String nombreServicio;
//	private Servicio_VO servicio;
	
//	private Boolean borrar = Boolean.valueOf(false);

	public Perfil_VO() {}

	public Perfil_VO(TipoDePerfil_VO tipo) {
		
		this.setNombre(tipo.getNombre());
		this.setCodigo(tipo.getCodigo());

		this.setTipoPerfil(tipo);
		this.setFunciones(tipo.getFunciones());

	}

//	public Perfil_VO(TipoDePerfil_VO tp, Rol_VO rol) {
//		this(tp);
//		if (rol != null) {
//			setIdRol(rol.getId());
//		}
//	}

	public Perfil_VO(Perfil perfil) {
		setObject(perfil);
	}

	public Perfil_VO(Perfil perfil, int profundidadActual,
			int profundidadDeseada) {
		setObject(perfil, profundidadActual, profundidadDeseada);
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

	public TipoDePerfil_VO getTipoPerfil() {
		return this.tipoPerfil;
	}

	public void setTipoPerfil(TipoDePerfil_VO tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
		if (tipoPerfil != null) {
			setNombre(tipoPerfil.getNombre());
			setCodigo(tipoPerfil.getCodigo());
		}
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public List<FuncionHorus_VO> getFunciones() {
		return this.funciones;
	}

	public void setFunciones(List<FuncionHorus_VO> funciones) {
		this.funciones = funciones;
	}

	public Long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

//	public void setRol(Rol_VO rolSeleccionado) {
//		setIdRol(rolSeleccionado.getId());
//		setNombreRol(rolSeleccionado.getNombre());
//		this.rol = rolSeleccionado;
//	}

	public boolean tieneFuncion(FuncionHorus_VO fx) {
		return getFunciones().contains(fx);
	}

	public void agregarFuncion(FuncionHorus_VO fx) {
		getFunciones().add(fx);
	}

	public void quitarFuncion(FuncionHorus_VO fx) {
		getFunciones().remove(fx);
	}

//	public Servicio_VO getServicio() {
//		return this.servicio;
//	}
//
//	public void setServicio(Servicio_VO servicio) {
//		this.servicio = servicio;
//	}
//
//	public Boolean getBorrar() {
//		return this.borrar;
//	}
//
//	public void setBorrar(Boolean borrar) {
//		this.borrar = borrar;
//	}

	public boolean tieneServicio() {
//		return getServicio() != null;
		return getIdServicio() != null;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

//	public Rol_VO getRol() {
//		return this.rol;
//	}

//	public boolean tieneSucursal(Sucursal_VO sucursalMenu) {
//		return getServicio().getArea().getSucursal().equals(sucursalMenu);
//	}

	@Override
	public void setObject(Perfil p) {
		
		this.setId(p.getId());
		this.setVersion(p.getVersion());
//		this.setNombre(p.getTipoPerfil().getNombre());
		
		if (p.getRol() != null) {
			this.setIdRol(p.getRol().getId());
			this.setNombreRol(p.getRol().getNombre());
//			this.setRol(p.getRol().toValueObject(
//					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}

		if (p.getTipoPerfil() != null) {
			this.setNombre(p.getNombre());
			this.setCodigo(p.getCodigo());

			// Se mete SOLO hasta la especialidad/prestacion/AreaHorus
			this.setTipoPerfil(p.getTipoPerfil().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, I_ValueObject.PROFUNDIDAD_BASE));

			List<FuncionHorus_VO> fxs = new ArrayList<FuncionHorus_VO>();
			for (FuncionHorus fx : p.getTipoPerfil().getFunciones()) {
				fxs.add(fx.toValueObject());
			}
			this.setFunciones(fxs);

		}

		List<FuncionHorus_VO> fxs = new ArrayList<FuncionHorus_VO>();
		for (FuncionHorus fx : p.getFunciones()) {
			fxs.add(fx.toValueObject());
		}
		this.setFunciones(fxs);

//		Servicio_VO srv = null;
//		if (p instanceof Perfil) {
//			srv = (p).getServicio().toValueObject();
//		}
//		this.setServicio(srv);
		if (p.getServicio() != null) {
			this.setIdServicio(p.getServicio().getId());
			this.setNombreServicio(p.getServicio().getNombre());
//			this.setRol(p.getRol().toValueObject(
//					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
	}

	@Override
	public void setObject(Perfil p, int profundidadActual,
			int profundidadDeseada) {
		
		this.setId(p.getId());
		this.setVersion(p.getVersion());
		
		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			if (p.getRol() != null) {
				this.setIdRol(p.getRol().getId());
				this.setNombreRol(p.getRol().getNombre());
//				this.setRol(p.getRol().toValueObject(
//						I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

			if (p.getTipoPerfil() != null) {
				this.setNombre(p.getNombre());
				this.setCodigo(p.getCodigo());

				this.setTipoPerfil(p.getTipoPerfil().toValueObject());

				List<FuncionHorus_VO> fxs = new ArrayList<FuncionHorus_VO>();
				for (FuncionHorus fx : p.getFunciones()) {
					fxs.add(fx.toValueObject());
				}
				this.setFunciones(fxs);

			}

			List<FuncionHorus_VO> fxs = new ArrayList<FuncionHorus_VO>();
			for (FuncionHorus fx : p.getFunciones()) {
				fxs.add(fx.toValueObject(profundidadActual + 1,
						profundidadDeseada));
			}
			this.setFunciones(fxs);

//			Servicio_VO srv = null;
//			if (p instanceof Perfil) {
//				srv = (p).getServicio().toValueObject(profundidadActual + 1,
//						profundidadDeseada);
//			}
//			this.setServicio(srv);

			if (p.getServicio() != null) {
				this.setIdServicio(p.getServicio().getId());
				this.setNombreServicio(p.getServicio().getNombre());
//				this.setRol(p.getRol().toValueObject(
//						I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
		}
	}

	@Override
	public Perfil toObject() {
		

		Perfil resul = new Perfil();
		resul.setId(getId());
		resul.setVersion(getVersion());
		resul.setBorrado(getBorrado());
		
		TipoDePerfil tp = new TipoDePerfil();
		if (this.getTipoPerfil() != null) {
			tp = this.getTipoPerfil().toObject();
			resul.setNombre(tp.getNombre());
			resul.setCodigo(tp.getCodigo());
		}
//		tp.setId(getTipoPerfil().getId());
		resul.setTipoPerfil(tp);
		
		
//		resul = new Perfil(this.getId(), this.getVersion(), tp, this
//				.getServicio().toObject());
		for (FuncionHorus_VO fx : this.getFunciones()) {
			resul.getFunciones().add(fx.toObject());
		}

		Rol r = new Rol();
		r.setId(getIdRol());
		resul.setRol(r);
		
		Servicio serv = new Servicio();
		serv.setId(getIdServicio());
		resul.setServicio(serv);
		
//		if (this.getIdRol() != null) {
//
//			r = new Rol();
//			r.setId(this.getIdRol());
//
//			resul.setRol(r);
//		}

		return resul;
	}

	public Perfil toObject(int profActual, int profDeseado) {
		Perfil resul = new Perfil();

		if (profActual < profDeseado) {

//			TipoDePerfil tp = null;
//			if (this.getTipoPerfil() != null) {
//				tp = this.getTipoPerfil().toObject(profActual + 1, profDeseado);
//			}
//
//			resul = new Perfil(this.getId(), this.getVersion(), tp, this
//					.getServicio().toObject(profActual + 1, profDeseado));
//
//			for (FuncionHorus_VO fx : this.getFunciones()) {
//				resul.getFunciones().add(
//						fx.toObject(profActual + 1, profDeseado));
//			}
//
//			Rol r = null;
//			if (this.getIdRol() != null) {
//
//				r = new Rol();
//				r.setId(this.getIdRol());
//
//				resul.setRol(r);
//			}
			resul = this.toObject();
		}

		return resul;
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
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * @param nombreServicio the nombreServicio to set
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public void refreshValues() {
		this.setTipoPerfil(getTipoPerfil());
	}
	
	@Override
	public boolean equals(Object object) {
		if ((object instanceof Perfil_VO)) {
			Perfil_VO per = (Perfil_VO) object;
			return per.getId().equals(getId()) 
					&& per.getVersion().equals(getVersion());
		}
		return false;
	}

	public void prepareToJson() {
		
		this.getTipoPerfil().getFunciones().clear();
		this.getTipoPerfil().setTipoRol(null);
		
	}
	
}