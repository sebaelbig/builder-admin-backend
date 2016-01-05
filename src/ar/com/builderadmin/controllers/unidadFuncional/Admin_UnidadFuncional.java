package ar.com.builderadmin.controllers.unidadFuncional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.unidadFuncional.DAO_UnidadFuncional;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.unidadFuncional.TipoUnidadFuncional_VO;
import ar.com.builderadmin.vo.unidadFuncional.UnidadFuncional_VO;

@Controller
public class Admin_UnidadFuncional extends Admin_Abstracto<UnidadFuncional_VO>{
	
	@Autowired
	private Paginador<TipoUnidadFuncional_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_UnidadFuncional dao_unidadFuncional;

	@Override
	protected Paginador<UnidadFuncional_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DAO<UnidadFuncional_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(UnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(UnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(UnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(UnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

}
