package ar.com.builderadmin.controllers.unidadFuncional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.unidadFuncional.DAO_UnidadFuncional;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.unidadFuncional.TipoUnidadFuncional_VO;
import ar.com.builderadmin.vo.unidadFuncional.UnidadFuncional_VO;

import com.google.gson.Gson;

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
	
	public String listarUnidadFuncionales() {
		List<UnidadFuncional_VO> lista=this.getDao_unidadFuncional().listarUnidadesFuncionales();
		JSON_Paginador pag = new JSON_Paginador(lista);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		resp.setMensaje("Se Obtuvieron las designaciones correctamente");
		return this.getGson().toJson(resp);
	}
	
	public String guardarUnidadFuncional(UnidadFuncional_VO uf, String usuarioAction) {

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			
			this.getDao_unidadFuncional().guardar(uf);

			jsonResp.setMensaje("Se Guardo la unidad funcional correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	public DAO_UnidadFuncional getDao_unidadFuncional() {
		return dao_unidadFuncional;
	}

	public void setDao_unidadFuncional(DAO_UnidadFuncional dao_unidadFuncional) {
		this.dao_unidadFuncional = dao_unidadFuncional;
	}


}
