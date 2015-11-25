package ar.org.hospitalespanol.fx.core.templates.propiedades;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.templates.propiedades.DAO_Propiedades;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.templates.Propiedad_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarPropiedad implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Propiedades dao;
	private Propiedad_VO area;
	private String usuario;

	public FX_BuscarPropiedad(DAO<Propiedad_VO> dao, Propiedad_VO area, String nombreUsuario) {
		setDao((DAO_Propiedades) dao);
		setPropiedad(area);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";
		this.getDao().resetQuery();
		
		if (this.getPropiedad().getNombre()!=null &&
				!this.getPropiedad().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getPropiedad().getNombre().toLowerCase() + "%");
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Propiedad_VO getPropiedad() {
		return area;
	}

	public void setPropiedad(Propiedad_VO area) {
		this.area = area;
	}

	public DAO_Propiedades getDao() {
		return dao;
	}

	public void setDao(DAO_Propiedades dao) {
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
