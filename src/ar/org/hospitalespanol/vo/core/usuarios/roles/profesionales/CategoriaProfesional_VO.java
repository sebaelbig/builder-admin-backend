package ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales;

import java.io.Serializable;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.CategoriaProfesional;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class CategoriaProfesional_VO implements
		I_ValueObject<CategoriaProfesional>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private String nombre;

	public CategoriaProfesional_VO() {
	}

	public CategoriaProfesional_VO(CategoriaProfesional categoriaProfesional) {
		this.setObject(categoriaProfesional);
	}

	public CategoriaProfesional_VO(CategoriaProfesional categoriaProfesional,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(categoriaProfesional, profundidadActual,
				profundidadDeseada);
	}

	@Override
	public void setObject(CategoriaProfesional cate) {

		this.setId(cate.getId());
		this.setVersion(cate.getVersion());
		this.setNombre(cate.getNombre());

	}

	@Override
	public void setObject(CategoriaProfesional cate, int profundidadActual,
			int profundidadDeseada) {
		this.setId(cate.getId());
		this.setVersion(cate.getVersion());
		this.setNombre(cate.getNombre());
	}

	@Override
	public CategoriaProfesional toObject() {
		CategoriaProfesional c = new CategoriaProfesional();

		c.setId(this.getId());
		c.setVersion(this.getVersion());
		c.setNombre(this.getNombre());

		return c;
	}

	public CategoriaProfesional toObject(int profundidadActual,
			int profundidadDeseada) {
		CategoriaProfesional c = new CategoriaProfesional();

		c.setId(this.getId());
		c.setVersion(this.getVersion());
		c.setNombre(this.getNombre());

		return c;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
