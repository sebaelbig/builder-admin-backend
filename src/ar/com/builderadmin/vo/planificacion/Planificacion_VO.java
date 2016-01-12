package ar.com.builderadmin.vo.planificacion;

import ar.com.builderadmin.model.planificacion.Planificacion;
import ar.com.builderadmin.vo.I_ValueObject;

public class Planificacion_VO implements I_ValueObject<Planificacion>{
	
	private Long id;
	private Integer version;
	private Boolean borrado = false;
	private String nombre;
	private String descripcion;
//	private List<UnidadFuncional> unidades;
	
	public Planificacion_VO(){
		
	}
	
	public Planificacion_VO(Planificacion p){
		this.setObject(p);
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
	public void setVersion(Integer id) {
		// TODO Auto-generated method stub
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
	@Override
	public Planificacion toObject() {
		Planificacion p = new Planificacion();
		p.setBorrado(this.getBorrado());
		p.setDescripcion(this.getDescripcion());
		p.setId(this.getId());
		p.setNombre(this.getNombre());
		p.setVersion(this.getVersion());
//		unidad.setDesignaciones(this.getDesignaciones());
		return p;
	}
	@Override
	public void setObject(Planificacion p) {
		this.setBorrado(p.getBorrado());
		this.setDescripcion(p.getDescripcion());
		this.setNombre(p.getNombre());
		this.setVersion(p.getVersion());
		this.setId(p.getId());
		
	}
	@Override
	public void setObject(Planificacion p, int profundidadActual,
			int profundidadDeseada) {
		this.setBorrado(p.getBorrado());
		this.setDescripcion(p.getDescripcion());
		this.setNombre(p.getNombre());
		this.setVersion(p.getVersion());
		this.setId(p.getId());
		
	}
}
