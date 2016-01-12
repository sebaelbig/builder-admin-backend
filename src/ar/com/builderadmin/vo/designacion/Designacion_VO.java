package ar.com.builderadmin.vo.designacion;

import ar.com.builderadmin.model.designacion.Designacion;
import ar.com.builderadmin.vo.I_ValueObject;

public class Designacion_VO implements I_ValueObject<Designacion>{
	
	private Long id;
	private Integer version;
	private String nombre;
	private String descripcion;
	private UnidadDeMedida_VO unidadPorDefault;
	private boolean borrado;
	
	public Designacion_VO(){
		
	}
	public Designacion_VO(Designacion designacion) {
		setObject(designacion);
	}
	
	@Override
	public Long getId() {
		return this.id;
	}
	@Override
	public void setId(Long id) {
		this.id=id;
	}
	@Override
	public Integer getVersion() {
		return this.version;
	}
	@Override
	public void setVersion(Integer id) {
		this.version=id;
	}
	@Override
	public Boolean getBorrado() {
		// TODO Auto-generated method stub
		return this.borrado;
	}
	@Override
	public void setBorrado(Boolean b) {
		// TODO Auto-generated method stub
		this.borrado=b;
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
	public UnidadDeMedida_VO getUnidadPorDefault() {
		return unidadPorDefault;
	}
	public void setUnidadPorDefault(UnidadDeMedida_VO unidadPorDefault) {
		this.unidadPorDefault = unidadPorDefault;
	}
	
	@Override
	public Designacion toObject() {
		Designacion unidad = new Designacion();
		unidad.setBorrado(this.getBorrado());
		unidad.setDescripcion(this.getDescripcion());
		unidad.setId(this.getId());
		unidad.setNombre(this.getNombre());
		unidad.setVersion(this.getVersion());
		unidad.setUnidadPorDefault(this.getUnidadPorDefault().toObject());
		return unidad;
	}

	@Override
	public void setObject(Designacion u) {
		this.setBorrado(u.getBorrado());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
		this.setUnidadPorDefault(u.getUnidadPorDefault().toValueObject());
		
	}

	@Override
	public void setObject(Designacion u, int profundidadActual,
			int profundidadDeseada) {
		this.setBorrado(u.getBorrado());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
		this.setUnidadPorDefault(u.getUnidadPorDefault().toValueObject());
	}
	

}
