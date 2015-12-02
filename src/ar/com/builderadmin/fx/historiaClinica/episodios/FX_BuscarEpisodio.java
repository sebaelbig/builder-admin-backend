package ar.com.builderadmin.fx.historiaClinica.episodios;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPublico;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

public class FX_BuscarEpisodio implements I_FX { @Override public Boolean listar(){return true;}

	private DAO_TemplateDeDescripcionPublico dao;
	private TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO;
	private String usuario;

	public FX_BuscarEpisodio(DAO_TemplateDeDescripcionPublico d,
			TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO,
			String nombreUsuario) {
		setTemplateDeDescripcionPublico_VO(templateDeDescripcionPublico_VO);
		setUsuario(nombreUsuario);
		setDao(d);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		String condicion = "";

		if (!getTemplateDeDescripcionPublico_VO().getTitulo().equals("")) {
			condicion = " WHERE LOWER(titulo) like :titulo ";
			getDao().getCondiciones().put(
					"titulo",
					templateDeDescripcionPublico_VO.getTitulo().toLowerCase()
							+ "%");
		}

		getDao().setQueryCondiciones(condicion);
		
		this.getRespuesta().setOk(true);

		return getRespuesta();
	}

	public DAO_TemplateDeDescripcionPublico getDao() {
		return dao;
	}

	public void setDao(DAO_TemplateDeDescripcionPublico dao) {
		this.dao = dao;
	}

	public TemplateDeDescripcionPublico_VO getTemplateDeDescripcionPublico_VO() {
		return templateDeDescripcionPublico_VO;
	}

	public void setTemplateDeDescripcionPublico_VO(
			TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO) {
		this.templateDeDescripcionPublico_VO = templateDeDescripcionPublico_VO;
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
	public Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		
		Map<String, Object> resp = new HashMap<String, Object>();
		
		resp.put(this.getClass().getCanonicalName(), "Los template público "
				+ getTemplateDeDescripcionPublico_VO().getTitulo()
				+ " se recuperaron correctamente");
		
		return resp;
	}
}
