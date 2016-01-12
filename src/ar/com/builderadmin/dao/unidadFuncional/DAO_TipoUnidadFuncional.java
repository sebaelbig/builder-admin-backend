package ar.com.builderadmin.dao.unidadFuncional;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.unidadFuncional.TipoUnidadFuncional;
import ar.com.builderadmin.vo.unidadFuncional.TipoUnidadFuncional_VO;

@Service
public class DAO_TipoUnidadFuncional extends DAO<TipoUnidadFuncional_VO>{
	
	TipoUnidadFuncional_VO unidad;

	public DAO_TipoUnidadFuncional() {
		this.setQueryEncabezado("SELECT new "
				+ TipoUnidadFuncional_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal("");

		this.setQueryCondiciones("");

	}

	@Override
	protected Class getClazz() {
		return TipoUnidadFuncional.class;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return "unidad_funcional";
	}


	
	public List<TipoUnidadFuncional_VO> listarTipoUnidades(){
		this.resetQuery();
		
		//this.setQueryCondiciones(" WHERE ");
		
		List<TipoUnidadFuncional_VO> unidades = this.listarTodo();
		
		this.resetQuery();
		return unidades;
	}
}
