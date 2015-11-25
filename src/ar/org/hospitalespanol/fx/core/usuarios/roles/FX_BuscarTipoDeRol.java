package ar.org.hospitalespanol.fx.core.usuarios.roles;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;

public class FX_BuscarTipoDeRol implements I_FX {

	private DAO_TipoDeRol dao;
	private TipoDeRol_VO sucursal;
	private String usuario;

	public FX_BuscarTipoDeRol(DAO<TipoDeRol_VO> dao, TipoDeRol_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDeRol) dao);
		setTipoDeRol(sucursal);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		this.getDao().resetQuery();
		
		String condicion = "";

		if (this.getTipoDeRol().getNombre()!=null &&
				!this.getTipoDeRol().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getTipoDeRol().getNombre().toLowerCase() + "%");
		}

		if (this.getTipoDeRol().getCodigo()!=null &&
				!this.getTipoDeRol().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getTipoDeRol().getCodigo().toLowerCase() + "%");
		}

//		if (!getTipoDeRol().getNombre().equals("Cualquiera")) {
//			if (condicion.equals(""))
//				condicion = " WHERE " + getDao().getIdClass() + ".id = :sucu ";
//			else
//				condicion += " AND " + getDao().getIdClass() + ".id = :sucu ";
//
//			getDao().getCondiciones().put("sucu",
//					getTipoDeRol().getId());
//		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public TipoDeRol_VO getTipoDeRol() {
		return sucursal;
	}

	public void setTipoDeRol(TipoDeRol_VO sucursal) {
		this.sucursal = sucursal;
	}

	public DAO_TipoDeRol getDao() {
		return dao;
	}

	public void setDao(DAO_TipoDeRol dao) {
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
