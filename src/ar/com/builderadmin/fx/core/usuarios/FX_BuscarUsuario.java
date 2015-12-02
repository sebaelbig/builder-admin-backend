package ar.com.builderadmin.fx.core.usuarios;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarUsuario implements I_FX {

	private DAO_Usuario dao;
	private Usuario_VO user;
	private String usuario;

	public FX_BuscarUsuario(DAO<Usuario_VO> dao, Usuario_VO user,
			String nombreUsuario) {
		setDao((DAO_Usuario) dao);
		setUser(user);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";

		if (this.getUser().getNombreUsuario()!=null &&
				!this.getUser().getNombreUsuario().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombreUsuario) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getUser().getNombreUsuario().toLowerCase() + "%");
		}


//		if (!getUsuario().getNombres().equals("Cualquiera")) {
//			if (condicion.equals(""))
//				condicion = " WHERE " + getDao().getIdClass() + ".id = :sucu ";
//			else
//				condicion += " AND " + getDao().getIdClass() + ".id = :sucu ";
//
//			getDao().getCondiciones().put("sucu",
//					getUsuario().getId());
//		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public DAO_Usuario getDao() {
		return dao;
	}

	public void setDao(DAO_Usuario dao) {
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

	/**
	 * @return the user
	 */
	public Usuario_VO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Usuario_VO user) {
		this.user = user;
	}
	
	
}
