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
import ar.com.builderadmin.dao.designacion.DAO_UnidadDeMedida;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.designacion.UnidadDeMedida_VO;

@Controller
public class Admin_UnidadDeMedida extends Admin_Abstracto<UnidadDeMedida_VO>{

	@Autowired
	private Paginador<UnidadDeMedida_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_UnidadDeMedida dao_unidadMedida;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private UnidadDeMedida_VO unidadMed_VO;

	private JSON_Respuesta json_respuesta;


	/**
	 * @return the paginador
	 */
	@Override
	public Paginador<UnidadDeMedida_VO> getPaginador() {
		return paginador;
	}

	/**
	 * @param paginador
	 *            the paginador to set
	 */
	public void setPaginador(Paginador<UnidadDeMedida_VO> paginador) {
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
	 * @return the dao_Servicio
	 */
	public DAO_UnidadDeMedida getDao_UnidadDeMedida() {
		return dao_unidadMedida;
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
	protected DAO<UnidadDeMedida_VO> getDao() {
		return this.dao_unidadMedida;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return admin_Alertas;
	}

	@Override
	protected I_FX getFX_Crear(UnidadDeMedida_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(UnidadDeMedida_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(UnidadDeMedida_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(UnidadDeMedida_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	public UnidadDeMedida_VO getUnidadMed_VO() {
		return unidadMed_VO;
	}

	public void setUnidadMed_VO(UnidadDeMedida_VO unidadMed_VO) {
		this.unidadMed_VO = unidadMed_VO;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public String listarUnidadDeMedida() {
		List<UnidadDeMedida_VO> lista=this.getDao_UnidadDeMedida().listarUnidades();
		return this.getGson().toJson(lista);
	}



}
