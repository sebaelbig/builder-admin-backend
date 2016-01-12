package ar.com.builderadmin.vo.unidadFuncional;

import ar.com.builderadmin.model.unidadFuncional.UnidadFuncional;
import ar.com.builderadmin.vo.I_ValueObject;

public class UnidadFuncional_VO implements I_ValueObject<UnidadFuncional> {

	private Long id;
	private Integer version;
	private Boolean borrado = false;
	private String nombre;
	private String descripcion;
	private TipoUnidadFuncional_VO tipoDeUnidad_vo;

	public UnidadFuncional_VO() {

	}

	public UnidadFuncional_VO(UnidadFuncional unidad) {
		setObject(unidad);
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return this.version;
	}

	@Override
	public void setVersion(Integer id) {
		// TODO Auto-generated method stub
		this.version = id;
	}

	@Override
	public Boolean getBorrado() {
		// TODO Auto-generated method stub
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoUnidadFuncional_VO getTipoDeUnidad_vo() {
		return tipoDeUnidad_vo;
	}

	public void setTipoDeUnidad_vo(TipoUnidadFuncional_VO tipoDeUnidad_vo) {
		this.tipoDeUnidad_vo = tipoDeUnidad_vo;
	}

	@Override
	public UnidadFuncional toObject() {
		UnidadFuncional unidad = new UnidadFuncional();
		unidad.setBorrado(this.getBorrado());
		unidad.setDescripcion(this.getDescripcion());
		unidad.setId(this.getId());
		unidad.setNombre(this.getNombre());
		unidad.setVersion(this.getVersion());
		unidad.setTipoDeUnidad(this.getTipoDeUnidad_vo().toObject());
		return unidad;

	}

	@Override
	public void setObject(UnidadFuncional u) {
		this.setBorrado(u.getBorrado());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
		this.setTipoDeUnidad_vo(u.getTipoDeUnidad().toValueObject());

	}

	@Override
	public void setObject(UnidadFuncional u, int profundidadActual,
			int profundidadDeseada) {
		this.setBorrado(u.getBorrado());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
		this.setTipoDeUnidad_vo(u.getTipoDeUnidad().toValueObject());

	}
}
