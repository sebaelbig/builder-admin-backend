package ar.org.hospitalespanol.vo.core.usuarios.perfiles;

import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.PerfilServicio;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;

public class PerfilServicio_VO implements I_ValueObject<PerfilServicio> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;
	
    private Usuario_VO usuario;
    private	Perfil_VO perfil;
	private Servicio_VO servicio;
	
	public PerfilServicio_VO() {

	}

	public PerfilServicio_VO(PerfilServicio perfilServicio) {
		setObject(perfilServicio);
	}

	public PerfilServicio_VO(Usuario usr, Perfil perfil, Servicio srv) {
		this.usuario = usr.toValueObject(0,0);
		this.perfil = perfil.toValueObject(0,1);
		this.servicio = srv.toValueObject(0,0);
	}
	
	public PerfilServicio_VO(PerfilServicio perfilServicio, int profundidadActual,
			int profundidadDeseada) {
		setObject(perfilServicio, profundidadActual, profundidadDeseada);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object objeto) {
		if ((objeto instanceof PerfilServicio_VO)) {
			PerfilServicio_VO o = (PerfilServicio_VO) objeto;
			return o.getId().equals(getId());
		}
		return false;
	}

	
	public Perfil_VO getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil_VO perfil_vo) {
		this.perfil = perfil_vo;
	}

	public Servicio_VO getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio_VO servicio_vo) {
		this.servicio = servicio_vo;
	}
	
	public Usuario_VO getUsuario() {
		return this.usuario;
	}

	
	public void setUsuario(Usuario_VO usuario_vo) {
		this.usuario = usuario_vo;
	}
	

	@Override
	public void setObject(PerfilServicio perfilServicio) {

		this.setId(perfilServicio.getId());
		this.setVersion(perfilServicio.getVersion());
		
		if (perfilServicio.getPerfil() != null) {
			// Para arriba las conversiones son plenas
			setPerfil(perfilServicio.getPerfil().toValueObject());
		}

		if (perfilServicio.getServicio() != null) {
			// Para arriba las conversiones son plenas
			setServicio(perfilServicio.getServicio().toValueObject());
		}
		
	    if (perfilServicio.getUsuario() != null) {
			// Para arriba las conversiones son plenas
	    	setUsuario(perfilServicio.getUsuario().toValueObject());
		}

	}

	@Override
	public void setObject(PerfilServicio perfilServicio, int profundidadActual,
			int profundidadDeseada) {

		this.setId(perfilServicio.getId());
		this.setVersion(perfilServicio.getVersion());
		
		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
			if (perfilServicio.getUsuario() != null) {
			//	setArea(perfilServicio.getUsuario().toValueObject(profundidadActual + 1,profundidadDeseada));
				setUsuario(perfilServicio.getUsuario().toValueObject(profundidadActual + 1,profundidadDeseada));
			}
			if (perfilServicio.getPerfil() != null) {
				setPerfil(perfilServicio.getPerfil().toValueObject(profundidadActual + 1,
						profundidadDeseada));
			}
			if (perfilServicio.getServicio() != null) {
				setServicio(perfilServicio.getServicio().toValueObject(profundidadActual + 1,
						profundidadDeseada));
			}
		}

	}
	


	public PerfilServicio toObject(int profundidadActual, int profundidadDeseada) {
		PerfilServicio resul = new PerfilServicio();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setBorrado(this.getBorrado());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			if (this.getPerfil() != null) {
				resul.setPerfil(this.getPerfil().toObject(profundidadActual + 1,
						profundidadDeseada));
			}
			if (this.getServicio() != null) {
				resul.setServicio(this.getServicio().toObject(profundidadActual + 1,
						profundidadDeseada));
			}
			if (this.getUsuario() != null) {
				resul.setUsuario(this.getUsuario().toObject(profundidadActual + 1,
						profundidadDeseada));
			}
		}

		return resul;
	}

	@Override
	public PerfilServicio toObject() {

		PerfilServicio perfilServicio = new PerfilServicio(this.getId(), this.getVersion());
		perfilServicio.setBorrado(this.getBorrado());
				
		return perfilServicio;

	}
	

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	
	
}