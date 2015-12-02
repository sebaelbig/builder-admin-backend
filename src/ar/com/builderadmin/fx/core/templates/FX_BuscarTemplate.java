package ar.com.builderadmin.fx.core.templates;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.templates.DAO_Templates;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.templates.Template_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarTemplate implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Templates dao;
	private Template_VO area;
	private String usuario;

	public FX_BuscarTemplate(DAO<Template_VO> dao, Template_VO area, String nombreUsuario) {
		setDao((DAO_Templates) dao);
		setTemplate(area);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";

		if (this.getTemplate().getNombreServicio()!=null &&
				!this.getTemplate().getNombreServicio().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".servicio.nombre) like :nombreServicio ";
			getDao().getCondiciones().put("nombreServicio",
					this.getTemplate().getNombreServicio().toLowerCase() + "%");
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Template_VO getTemplate() {
		return area;
	}

	public void setTemplate(Template_VO area) {
		this.area = area;
	}

	public DAO_Templates getDao() {
		return dao;
	}

	public void setDao(DAO_Templates dao) {
		this.dao = dao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		return new java.util.HashMap<String, Object>();
	}

}
