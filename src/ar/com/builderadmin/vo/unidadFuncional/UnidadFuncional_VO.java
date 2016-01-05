package ar.com.builderadmin.vo.unidadFuncional;

import ar.com.builderadmin.model.unidadFuncional.UnidadFuncional;
import ar.com.builderadmin.vo.I_ValueObject;

public class UnidadFuncional_VO implements I_ValueObject<UnidadFuncional>{
	
	private Long id;
	private Integer version;
	private Boolean borrado = false;
	private String nombre;
	private String descripcion; 
	private TipoUnidadFuncional_VO tipoDeUnidad_vo;
	
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
	public UnidadFuncional toObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setObject(UnidadFuncional paramT) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setObject(UnidadFuncional paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}
}
