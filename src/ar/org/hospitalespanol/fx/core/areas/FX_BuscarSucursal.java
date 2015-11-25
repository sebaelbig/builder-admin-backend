package ar.org.hospitalespanol.fx.core.areas;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.areas.DAO_Sucursal;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.areas.Sucursal_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarSucursal implements I_FX {

	private DAO_Sucursal dao;
	private Sucursal_VO sucursal;
	private String usuario;

	public FX_BuscarSucursal(DAO<Sucursal_VO> dao, Sucursal_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_Sucursal) dao);
		setSucursal(sucursal);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";

		if (this.getSucursal().getNombre()!=null &&
				!this.getSucursal().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getSucursal().getNombre().toLowerCase() + "%");
		}

		if (this.getSucursal().getCodigo()!=null &&
				!this.getSucursal().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getSucursal().getCodigo().toLowerCase() + "%");
		}

//		if (!getSucursal().getNombre().equals("Cualquiera")) {
//			if (condicion.equals(""))
//				condicion = " WHERE " + getDao().getIdClass() + ".id = :sucu ";
//			else
//				condicion += " AND " + getDao().getIdClass() + ".id = :sucu ";
//
//			getDao().getCondiciones().put("sucu",
//					getSucursal().getId());
//		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Sucursal_VO getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal_VO sucursal) {
		this.sucursal = sucursal;
	}

	public DAO_Sucursal getDao() {
		return dao;
	}

	public void setDao(DAO_Sucursal dao) {
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

	@Override
	public Boolean listar() {
		return true;
	}
}
