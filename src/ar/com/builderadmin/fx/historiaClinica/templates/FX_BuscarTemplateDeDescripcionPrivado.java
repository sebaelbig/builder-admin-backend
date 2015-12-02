package ar.com.builderadmin.fx.historiaClinica.templates;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPrivado_VO;

public class FX_BuscarTemplateDeDescripcionPrivado implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_TemplateDeDescripcionPrivado dao;
	private TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO;
	private String usuario;

	public FX_BuscarTemplateDeDescripcionPrivado(
			DAO_TemplateDeDescripcionPrivado d,
			TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO,
			String nombreUsuario) {
		setTemplateDeDescripcionPrivado_VO(templateDeDescripcionPrivado_VO);
		setUsuario(nombreUsuario);
		setDao(d);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		String condicion = "";

		if (!getTemplateDeDescripcionPrivado_VO().getTitulo().equals("")) {
			condicion = " WHERE LOWER(titulo) like :titulo ";
			getDao().getCondiciones().put(
					"titulo",
					templateDeDescripcionPrivado_VO.getTitulo().toLowerCase()
							+ "%");
		}

		getDao().setQueryCondiciones(condicion);

		this.getRespuesta().setOk(true);

		return getRespuesta();
	}

	public DAO_TemplateDeDescripcionPrivado getDao() {
		return dao;
	}

	public void setDao(DAO_TemplateDeDescripcionPrivado dao) {
		this.dao = dao;
	}

	public TemplateDeDescripcionPrivado_VO getTemplateDeDescripcionPrivado_VO() {
		return templateDeDescripcionPrivado_VO;
	}

	public void setTemplateDeDescripcionPrivado_VO(
			TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO) {
		this.templateDeDescripcionPrivado_VO = templateDeDescripcionPrivado_VO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return perfil.getNombreRol().equals(Rol.PROFESIONAL);
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * return JSON
	 */
	@Override
	public Map<String, Object> armarDatosPublicacionComet(EntityManager em) {

		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getCanonicalName(), "Los template privado "
				+ getTemplateDeDescripcionPrivado_VO().getTitulo()
				+ " se recuperaron correctamente");

		return resp;
	}
}
