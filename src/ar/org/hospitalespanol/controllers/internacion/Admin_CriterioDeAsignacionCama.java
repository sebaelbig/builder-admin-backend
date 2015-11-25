package ar.org.hospitalespanol.controllers.internacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.internacion.DAO_CriterioDeAsignacionCama;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.R_CrearEntidad;
import ar.org.hospitalespanol.vo.internacion.Carpeta_VO;
import ar.org.hospitalespanol.vo.internacion.CriterioDeAsignacionCama_VO;
import ar.org.hospitalespanol.vo.internacion.Pabellon_VO;

@Controller
public class Admin_CriterioDeAsignacionCama extends
		Admin_Abstracto<CriterioDeAsignacionCama_VO> {

	@Autowired
	private DAO_CriterioDeAsignacionCama daoCriterios;

	@Autowired
	private Paginador<CriterioDeAsignacionCama_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	private JSON_Respuesta json_respuesta;

	/**
	 * Get listado
	 * 
	 * @return
	 */
	public String getListadoCriteriosDeAsignacion() {
		List<CriterioDeAsignacionCama_VO> criterios = this.getDaoCriterios()
				.listadoDeCriteriosDeAsignacion();

		JSON_Paginador pag = new JSON_Paginador(criterios);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);

		return new Gson().toJson(resp);
	}

	/***
	 * Guardar
	 */
	public String guardarCriterio(CriterioDeAsignacionCama_VO criterio) {

		R_CrearEntidad resp= this.getDaoCriterios().guardarCriterioDeAsignacion(criterio);

		return new Gson().toJson(resp);

	}
	
	public String modificarCriterio(CriterioDeAsignacionCama_VO criterio) {

		R_CrearEntidad resp= this.getDaoCriterios().modificarCriterioDeAsignacion(criterio);

		return new Gson().toJson(resp);

	}
	
	public String eliminarCriterio(CriterioDeAsignacionCama_VO criterio) {

		R_CrearEntidad resp= this.getDaoCriterios().eliminarCriterioDeAsignacion(criterio);

		return new Gson().toJson(resp);

	}

	@Override
	protected Paginador<CriterioDeAsignacionCama_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DAO<CriterioDeAsignacionCama_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(CriterioDeAsignacionCama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(CriterioDeAsignacionCama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(CriterioDeAsignacionCama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(CriterioDeAsignacionCama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

	public DAO_CriterioDeAsignacionCama getDaoCriterios() {
		return daoCriterios;
	}

	public void setDaoCriterios(DAO_CriterioDeAsignacionCama daoCriterios) {
		this.daoCriterios = daoCriterios;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public void setPaginador(Paginador<CriterioDeAsignacionCama_VO> paginador) {
		this.paginador = paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

}
