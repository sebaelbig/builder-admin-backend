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
import ar.com.builderadmin.dao.unidadFuncional.DAO_TipoUnidadFuncional;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.unidadFuncional.TipoUnidadFuncional_VO;

import com.google.gson.Gson;

@Controller
public class Admin_TipoUnidadFuncional extends Admin_Abstracto<TipoUnidadFuncional_VO>{

	@Autowired
	private Paginador<TipoUnidadFuncional_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TipoUnidadFuncional dao_tipoUnidad;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private TipoUnidadFuncional_VO tipoUnidad_VO;

	private JSON_Respuesta json_respuesta;


	/**
	 * @return the paginador
	 */
	@Override
	public Paginador<TipoUnidadFuncional_VO> getPaginador() {
		return paginador;
	}

	/**
	 * @param paginador
	 *            the paginador to set
	 */
	public void setPaginador(Paginador<TipoUnidadFuncional_VO> paginador) {
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




	public DAO_TipoUnidadFuncional getDao_tipoUnidad() {
		return dao_tipoUnidad;
	}

	public void setDao_tipoUnidad(DAO_TipoUnidadFuncional dao_tipoUnidad) {
		this.dao_tipoUnidad = dao_tipoUnidad;
	}

	public TipoUnidadFuncional_VO getTipoUnidad_VO() {
		return tipoUnidad_VO;
	}

	public void setTipoUnidad_VO(TipoUnidadFuncional_VO tipoUnidad_VO) {
		this.tipoUnidad_VO = tipoUnidad_VO;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return admin_Alertas;
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

	public TipoUnidadFuncional_VO getUnidadMed_VO() {
		return tipoUnidad_VO;
	}

	public void setUnidadMed_VO(TipoUnidadFuncional_VO un) {
		this.tipoUnidad_VO = un;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public String listarTipoUnidades() {
		List<TipoUnidadFuncional_VO> lista=this.getDao_tipoUnidad().listarTipoUnidades();
		JSON_Paginador pag = new JSON_Paginador(lista);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		return this.getGson().toJson(resp);
	}
	
	public String guardarUnidad(TipoUnidadFuncional_VO unidad, String usuarioAction) {

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			
			this.getDao_tipoUnidad().guardar(unidad);

			jsonResp.setMensaje("Se Guardo la unidad correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	@Override
	protected DAO<TipoUnidadFuncional_VO> getDao() {
		// TODO Auto-generated method stub
		return dao_tipoUnidad;
	}





}
