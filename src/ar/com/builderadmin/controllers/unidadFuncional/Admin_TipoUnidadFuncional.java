package ar.com.builderadmin.controllers.unidadFuncional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.unidadFuncional.DAO_TipoUnidadFuncional;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.unidadFuncional.TipoUnidadFuncional_VO;

@Controller
public class Admin_TipoUnidadFuncional extends Admin_Abstracto<TipoUnidadFuncional_VO>{

	@Autowired
	private Paginador<TipoUnidadFuncional_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TipoUnidadFuncional dao_tipoUnidadFuncional;

	@Override
	protected Paginador<TipoUnidadFuncional_VO> getPaginador() {
		// TODO Auto-generated method stub
		return this.paginador;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DAO<TipoUnidadFuncional_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(TipoUnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(TipoUnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(TipoUnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(TipoUnidadFuncional_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

}
