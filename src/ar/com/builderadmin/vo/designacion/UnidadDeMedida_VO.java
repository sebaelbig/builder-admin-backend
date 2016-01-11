package ar.com.builderadmin.vo.designacion;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.model.designacion.UnidadDeMedida;
import ar.com.builderadmin.vo.I_ValueObject;

public class UnidadDeMedida_VO implements I_ValueObject<UnidadDeMedida>{
	
	private Long id;
	private Integer version;
	private boolean borrado;
	private String nombre;
	private String descripcion;
	private boolean granel;
	private List<Designacion_VO> designaciones;
	
	public UnidadDeMedida_VO(){
		setDesignaciones(new ArrayList<Designacion_VO>());
	}
	public UnidadDeMedida_VO(UnidadDeMedida e){
		this.setObject(e);
	}
	
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
	public boolean getGranel() {
		return granel;
	}
	public void setGranel(boolean granel) {
		this.granel = granel;
	}

	

	@Override
	public UnidadDeMedida toObject() {
		UnidadDeMedida unidad = new UnidadDeMedida();
		unidad.setBorrado(this.getBorrado());
		unidad.setDescripcion(this.getDescripcion());
		unidad.setId(this.getId());
		unidad.setNombre(this.getNombre());
		unidad.setGranel(this.getGranel());
		unidad.setVersion(this.getVersion());
//		unidad.setDesignaciones(this.getDesignaciones());
		return unidad;
	}

	@Override
	public void setObject(UnidadDeMedida u) {
		this.setBorrado(u.getBorrado());
		this.setGranel(u.getGranel());
		this.setDescripcion(u.getDescripcion());
		this.setNombre(u.getNombre());
		this.setVersion(u.getVersion());
		this.setId(u.getId());
//		setDesignaciones(new ArrayList<Designacion_VO>());
//		if (u.getDesignaciones() != null) {
//
//			for (Designacion d : u.getDesignaciones()) {
//				this.getDesignaciones().add(
//						d.toValueObject());
//			}
//
//		}
	}

	@Override
	public void setObject(UnidadDeMedida paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}

	@Override
	public Boolean getBorrado() {
		// TODO Auto-generated method stub
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado=b;
		
	}
	public List<Designacion_VO> getDesignaciones() {
		return designaciones;
	}
	public void setDesignaciones(List<Designacion_VO> designaciones) {
		this.designaciones = designaciones;
	}


}
