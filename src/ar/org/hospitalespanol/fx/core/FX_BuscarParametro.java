package ar.org.hospitalespanol.fx.core;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.DAO_Parametro;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.Parametro_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarParametro implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Parametro dao;
	private Parametro_VO area;
	private String usuario;

	public FX_BuscarParametro(DAO<Parametro_VO> dao, Parametro_VO area, String nombreUsuario) {
		setDao((DAO_Parametro) dao);
		setParametro(area);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";
		this.getDao().resetQuery();
		
		if (this.getParametro().getNombre()!=null &&
				!this.getParametro().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getParametro().getNombre().toLowerCase() + "%");
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Parametro_VO getParametro() {
		return area;
	}

	public void setParametro(Parametro_VO area) {
		this.area = area;
	}

	public DAO_Parametro getDao() {
		return dao;
	}

	public void setDao(DAO_Parametro dao) {
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
