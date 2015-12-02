package ar.com.builderadmin.fx.core.usuarios.roles;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class FX_BuscarRol implements I_FX {

	private DAO_Rol dao;
	private Rol_VO rol;
	private String usuario;

	public FX_BuscarRol(DAO dao, Rol_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_Rol) dao);
		setRol(sucursal);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		this.getDao().resetQuery();
		
		String condicion = "";

		if (this.getRol().getNombre()!=null &&
				!this.getRol().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getRol().getNombre().toLowerCase() + "%");
		}

		if (this.getRol().getCodigo()!=null &&
				!this.getRol().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getRol().getCodigo().toLowerCase() + "%");
		}

//		if (!getRol().getNombre().equals("Cualquiera")) {
//			if (condicion.equals(""))
//				condicion = " WHERE " + getDao().getIdClass() + ".id = :sucu ";
//			else
//				condicion += " AND " + getDao().getIdClass() + ".id = :sucu ";
//
//			getDao().getCondiciones().put("sucu",
//					getRol().getId());
//		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Rol_VO getRol() {
		return rol;
	}

	public void setRol(Rol_VO sucursal) {
		this.rol = sucursal;
	}

	public DAO_Rol getDao() {
		return dao;
	}

	public void setDao(DAO_Rol dao) {
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
