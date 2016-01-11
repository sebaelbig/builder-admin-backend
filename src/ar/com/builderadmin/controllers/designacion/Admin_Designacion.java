package ar.com.builderadmin.controllers.designacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.designacion.DAO_Designacion;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.designacion.Designacion_VO;

import com.google.gson.Gson;

@Controller
public class Admin_Designacion extends
		Admin_Abstracto<Designacion_VO> {

	@Autowired
	private Paginador<Designacion_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Designacion dao_designacion;
	private JSON_Respuesta json_respuesta;

	@Override
	protected Paginador<Designacion_VO> getPaginador() {
		// TODO Auto-generated method stub
		return this.paginador;
	}
	/**
	 * @param paginador
	 *            the paginador to set
	 */
	public void setPaginador(Paginador<Designacion_VO> paginador) {
		this.paginador = paginador;
	}



	public DAO_Designacion getDao_designacion() {
		return dao_designacion;
	}



	public void setDao_designacion(DAO_Designacion dao_designacion) {
		this.dao_designacion = dao_designacion;
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
	
	public String listarDesignaciones() {
		List<Designacion_VO> lista=this.getDao_designacion().listarDesignaciones();
		JSON_Paginador pag = new JSON_Paginador(lista);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		resp.setMensaje("Se Obtuvieron las designaciones correctamente");
		return this.getGson().toJson(resp);
	}
	
	public String guardarDesignacion(Designacion_VO des, String usuarioAction) {

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			
			this.getDao_designacion().guardar(des);

			jsonResp.setMensaje("Se Guardo la designacion correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}



	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return this.json_paginador;
	}



	@Override
	protected DAO<Designacion_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}
	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}


}
