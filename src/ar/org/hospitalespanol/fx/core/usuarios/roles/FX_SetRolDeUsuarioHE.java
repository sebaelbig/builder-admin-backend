package ar.org.hospitalespanol.fx.core.usuarios.roles;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.ldap.modelo.RolDeUsuarioHE;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_SetRolDeUsuarioHE implements I_FX {
	
	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_SetRolDeUsuarioHE.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_RolHE dao;
	private RolDeUsuarioHE rol;
	private String usuario;

	public FX_SetRolDeUsuarioHE(DAO dao, RolDeUsuarioHE rol, String usuario) {
		setDao((DAO_RolHE) dao);
		setUsuario(usuario);
		setRol(rol);
	}

	private boolean validar() {
		return true;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

//		if (validar()) {
//
//			try {
//
//				R_SetRolDeUsuario roles = getDao().setRolDeUsuario(getDao(),
//						getRol());
//
//				// JSON_Paginador pag = new JSON_Paginador();
//				// pag.setElementos(roles.getRoles());
//				// pag.setMensaje(roles.getMensaje());
//
//				// getRespuesta().setPaginador(pag);
//				getRespuesta().setPaginador(null);
//				getRespuesta().setMensaje(roles.getMensaje());
//				getRespuesta().setOk(roles.getOk());
//
//			} catch (Exception e) {
//				this.getRespuesta().setOk(false);
//
//				this.getRespuesta().setMensaje(
//						"Ocurrió un error en la grabación");
//				this.logger.error("Ocurrió un error en la grabación");
//				e.printStackTrace();
//			}
//
//		} else {
//
//			this.getRespuesta().setOk(false);
//			this.getRespuesta().setMensaje("Error al obtener los roles.");
//
//		}

		return getRespuesta();
	}

	/**
	 * @return the dao
	 */
	public DAO_RolHE getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_RolHE dao) {
		this.dao = dao;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO paramPerfil_VO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "Se seteo el rol: "
				+ getRol().getRol() + " al usuario LDAP:  "
				+ getRol().getUsuario());

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the rol
	 */
	public RolDeUsuarioHE getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(RolDeUsuarioHE rol) {
		this.rol = rol;
	}

	@Override
	public Boolean listar() {
		return false;
	}
	
}
