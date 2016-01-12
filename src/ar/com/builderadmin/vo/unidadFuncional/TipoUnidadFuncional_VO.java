package ar.com.builderadmin.vo.unidadFuncional;

import ar.com.builderadmin.model.unidadFuncional.TipoUnidadFuncional;
import ar.com.builderadmin.vo.I_ValueObject;

public class TipoUnidadFuncional_VO implements I_ValueObject<TipoUnidadFuncional>{
	
	private Long id;
	private Integer version;
	private Boolean borrado = false;
	private String nombre;
	private String descripcion;
	
	public TipoUnidadFuncional_VO(){
		
	}
	public TipoUnidadFuncional_VO(TipoUnidadFuncional e){
		this.setObject(e);
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


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return this.version;
	}
	@Override
	public void setVersion(Integer v) {
		// TODO Auto-generated method stub
		this.version=v;
	}
	@Override
	public Boolean getBorrado() {
		return borrado;
	}

	@Override
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}


	@Override
	public TipoUnidadFuncional toObject() {
		TipoUnidadFuncional unidad = new TipoUnidadFuncional();
		unidad.setBorrado(this.getBorrado());
		unidad.setDescripcion(this.getDescripcion());
		unidad.setId(this.getId());
		unidad.setNombre(this.getNombre());
		unidad.setVersion(this.getVersion());
//		unidad.setDesignaciones(this.getDesignaciones());
		return unidad;
	}
	@Override
	public void setObject(TipoUnidadFuncional u) {
		this.setBorrado(u.getBorrado());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
		
	}
	@Override
	public void setObject(TipoUnidadFuncional u, int profundidadActual,
			int profundidadDeseada) {
		this.setBorrado(u.getBorrado());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
		
	} 
	


}
