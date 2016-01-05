package ar.com.builderadmin.controllers.designacion;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.designacion.DAO_Designacion;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.designacion.Designacion_VO;
import ar.com.builderadmin.vo.designacion.UnidadDeMedida_VO;

public class Admin_Designacion<DAO_Designacion>  extends Admin_Abstracto<Designacion_VO>{

	@Autowired
	private Paginador<UnidadDeMedida_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Designacion dao_designacion;
	
	@Override
	protected Paginador<Designacion_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DAO<Designacion_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(Designacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Designacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Designacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Designacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

}
