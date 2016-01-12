package ar.com.builderadmin.dao.unidadFuncional;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.unidadFuncional.UnidadFuncional;
import ar.com.builderadmin.vo.unidadFuncional.UnidadFuncional_VO;

@Service
public class DAO_UnidadFuncional extends DAO<UnidadFuncional_VO>{

	UnidadFuncional_VO unidadFuncional;

	public DAO_UnidadFuncional() {
		this.setQueryEncabezado("SELECT new "
				+ UnidadFuncional_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() );
	

		this.setQueryFinal("");

		this.setQueryCondiciones("");

	}

	@Override
	protected Class getClazz() {
		return UnidadFuncional.class;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return "unidad_funcional";
	}




	public List<UnidadFuncional_VO> listarUnidadesFuncionales() {
		this.resetQuery();

		List<UnidadFuncional_VO> unidades = this.listarTodo();

		this.resetQuery();
		return unidades;
	}

}
