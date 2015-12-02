package ar.com.builderadmin.fx.core.areas;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.areas.DAO_Areas;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.areas.Area_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarArea implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Areas dao;
	private Area_VO area;
	private String usuario;

	public FX_BuscarArea(DAO<Area_VO> dao, Area_VO area, String nombreUsuario) {
		setDao((DAO_Areas) dao);
		setArea(area);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";

		if (this.getArea().getNombre()!=null &&
				!this.getArea().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getArea().getNombre().toLowerCase() + "%");
		}

		if (this.getArea().getCodigo()!=null &&
				!this.getArea().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getArea().getCodigo().toLowerCase() + "%");
		}

		if (this.getArea().getSucursal()!=null &&
				!getArea().getSucursal().getNombre().equals("Cualquiera")) {
			if (condicion.equals(""))
				condicion = " WHERE " + getDao().getIdClass()
						+ ".sucursal.id = :sucu ";
			else
				condicion += " AND " + getDao().getIdClass()
						+ ".sucursal.id = :sucu ";

			getDao().getCondiciones().put("sucu",
					getArea().getSucursal().getId());
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Area_VO getArea() {
		return area;
	}

	public void setArea(Area_VO area) {
		this.area = area;
	}

	public DAO_Areas getDao() {
		return dao;
	}

	public void setDao(DAO_Areas dao) {
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
