package ar.com.builderadmin.vo.designacion;

import ar.com.builderadmin.model.designacion.UnidadDeMedida;
import ar.com.builderadmin.vo.I_ValueObject;

public class UnidadDeMedida_VO implements I_ValueObject<UnidadDeMedida>{
	
	private Long id;
	private Integer version;
	private boolean borrado;
	private String nombre;
	private String descripcion;
	private boolean granel;
	
	
	
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
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getBorrado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBorrado(Boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnidadDeMedida toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(UnidadDeMedida paramT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObject(UnidadDeMedida paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}


}
