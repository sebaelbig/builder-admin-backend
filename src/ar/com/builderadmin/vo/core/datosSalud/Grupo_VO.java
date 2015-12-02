package ar.com.builderadmin.vo.core.datosSalud;

import ar.com.builderadmin.model.core.datosSalud.Grupo;
import ar.com.builderadmin.vo.I_ValueObject;

public class Grupo_VO implements I_ValueObject<Grupo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String codigo;
	private String nombre;

	public Grupo_VO() {

	}

	public Grupo_VO(Grupo grupo) {
		setObject(grupo);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(Grupo grupo) {
		this.setId(grupo.getId());
		this.setVersion(grupo.getVersion());
		this.setCodigo(grupo.getCodigo());
		this.setNombre(grupo.getNombre());

	}

	@Override
	public Grupo toObject() {
		return new Grupo(this.getId(), this.getVersion(), this.getCodigo(),
				this.getNombre());
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void setObject(Grupo objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}
}
