package ar.org.hospitalespanol.model.core.usuarios.perfiles;

import java.io.Serializable;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.PerfilServicio_VO;

//@Entity
//@Table
public class PerfilServicio implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	private Long id;

	private Integer version;

    private Usuario usuario;
    private	Perfil perfil;
	private Servicio servicio;
	
	public PerfilServicio() {

	}

	public PerfilServicio(Long id, Integer version) {

		this.setId(id);
		this.setVersion(version);

	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	
	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PerfilServicio) {
			PerfilServicio o = (PerfilServicio) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}



	public PerfilServicio_VO toValueObject() {
		return new PerfilServicio_VO(this);
	}

	public PerfilServicio_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new PerfilServicio_VO(this, profundidadActual, profundidadDeseada);
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