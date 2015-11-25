package ar.org.hospitalespanol.fx.core.templates;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.templates.DAO_Templates;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.templates.Template_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

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
