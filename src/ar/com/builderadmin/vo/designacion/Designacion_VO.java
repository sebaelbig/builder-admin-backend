package ar.com.builderadmin.vo.designacion;

import ar.com.builderadmin.model.designacion.Designacion;
import ar.com.builderadmin.model.designacion.UnidadDeMedida;
import ar.com.builderadmin.vo.I_ValueObject;

public class Designacion_VO implements I_ValueObject<Designacion>{
	
	private Long id;
	private Integer version;
	private String nombre;
	private String descripcion;
	private UnidadDeMedida unidadPorDefault;
	private boolean borrado;
	
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
	@Override
	public Designacion toObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setObject(Designacion paramT) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setObject(Designacion paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
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
	public UnidadDeMedida getUnidadPorDefault() {
		return unidadPorDefault;
	}
	public void setUnidadPorDefault(UnidadDeMedida unidadPorDefault) {
		this.unidadPorDefault = unidadPorDefault;
	}
	
	

}
