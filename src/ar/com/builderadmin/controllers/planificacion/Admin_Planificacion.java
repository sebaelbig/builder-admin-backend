package ar.com.builderadmin.controllers.planificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.planificacion.DAO_Planificacion;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.planificacion.Planificacion_VO;

import com.google.gson.Gson;

@Controller
public class Admin_Planificacion extends Admin_Abstracto<Planificacion_VO>{
	
	@Autowired
	private Paginador<Planificacion_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Planificacion dao_planificacion;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private Planificacion_VO planificacion_vo;

	private JSON_Respuesta json_respuesta;


	/**
	 * @return the paginador
	 */
	@Override
	public Paginador<Planificacion_VO> getPaginador() {
		return paginador;
	}

	/**
	 * @param paginador
	 *            the paginador to set
	 */
	public void setPaginador(Paginador<Planificacion_VO> paginador) {
		this.paginador = paginador;
	}

	/**
	 * @return the json_paginador
	 */
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	/**
	 * @param json_paginador
	 *            the json_paginador to set
	 */
	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}


	/**
	 * @return the admin_Alertas
	 */
	public Admin_Alertas getAdmin_Alertas() {
		return admin_Alertas;
	}

	/**
	 * @param admin_Alertas
	 *            the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
	}



	@Override
	protected Admin_Alertas getAdminAlertas() {
		return admin_Alertas;
	}

	@Override
	protected I_FX getFX_Crear(Planificacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Planificacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Planificacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Planificacion_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}



	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public String listarPlanificaciones() {
		List<Planificacion_VO> lista=this.getDao_planificacion().listarPlanificaciones();
		JSON_Paginador pag = new JSON_Paginador(lista);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		return this.getGson().toJson(resp);
	}
	
	public String guardarPlanificacion(Planificacion_VO pl, String usuarioAction) {

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			
			this.getDao_planificacion().guardar(pl);

			jsonResp.setMensaje("Se Guardo la unidad correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	public DAO_Planificacion getDao_planificacion() {
		return dao_planificacion;
	}

	public void setDao_planificacion(DAO_Planificacion dao_planificacion) {
		this.dao_planificacion = dao_planificacion;
	}

	public Planificacion_VO getPlanificacion_vo() {
		return planificacion_vo;
	}

	public void setPlanificacion_vo(Planificacion_VO planificacion_vo) {
		this.planificacion_vo = planificacion_vo;
	}

	@Override
	protected DAO<Planificacion_VO> getDao() {
		// TODO Auto-generated method stub
		return this.dao_planificacion;
	}
	

}
