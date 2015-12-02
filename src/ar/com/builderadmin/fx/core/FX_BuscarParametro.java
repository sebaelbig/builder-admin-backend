package ar.com.builderadmin.fx.core;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.DAO_Parametro;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.Parametro_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

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
