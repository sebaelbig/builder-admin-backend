package ar.com.builderadmin.dao.designacion;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.designacion.UnidadDeMedida;
import ar.com.builderadmin.vo.designacion.UnidadDeMedida_VO;

@Service
public class DAO_UnidadDeMedida extends DAO<UnidadDeMedida_VO>{
	
	UnidadDeMedida_VO unidadDeMedida;
	
	public DAO_UnidadDeMedida() {
		this.setQueryEncabezado("SELECT new "
				+ UnidadDeMedida_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal("");

		this.setQueryCondiciones("");

	}

	@Override
	protected Class getClazz() {
		return UnidadDeMedida.class;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return "unidad_de_medida";
	}

	public UnidadDeMedida_VO getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(UnidadDeMedida_VO unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	
	public List<UnidadDeMedida_VO> listarUnidades(){
		this.resetQuery();
		
		//this.setQueryCondiciones(" WHERE ");
		
		List<UnidadDeMedida_VO> unidades = this.listarTodo();
		
		this.resetQuery();
		return unidades;
	}
	
	

}
