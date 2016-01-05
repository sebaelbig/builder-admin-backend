package ar.com.builderadmin.vo.planificacion;

import java.util.List;

import ar.com.builderadmin.model.planificacion.Planificacion;
import ar.com.builderadmin.model.unidadFuncional.UnidadFuncional;
import ar.com.builderadmin.vo.I_ValueObject;

public class Planificacion_VO implements I_ValueObject<Planificacion>{
	
	private Long id;
	private Integer version;
	private Boolean borrado = false;
	private String nombre;
	private String descripcion;
	private List<UnidadFuncional> unidades;
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setVersion(Integer id) {
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
	public Planificacion toObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setObject(Planificacion paramT) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setObject(Planificacion paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}
}
