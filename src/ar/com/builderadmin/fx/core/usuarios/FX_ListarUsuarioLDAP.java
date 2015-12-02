package ar.com.builderadmin.fx.core.usuarios;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.ldap.dao.DAO_UsuarioLDAP;
import ar.com.builderadmin.ldap.dao.DAO_UsuarioLDAP.R_GetUsuariosLDAP;
import ar.com.builderadmin.ldap.modelo.UsuarioLDAP;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ListarUsuarioLDAP implements I_FX {
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ListarUsuarioLDAP.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_UsuarioLDAP dao;
	private UsuarioLDAP usuarioLDAP;
	private String usuario;

	public FX_ListarUsuarioLDAP(DAO dao, UsuarioLDAP usr, String usuario) {
		setDao((DAO_UsuarioLDAP) dao);
		setUsuarioLDAP(usr);
		setUsuario(usuario);
	}

	private boolean validar() {
		return true;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {

			try {

				R_GetUsuariosLDAP resul = getDao().getUsuariosLDAP();

				JSON_Paginador pag = new JSON_Paginador();
				pag.setElementos(resul.getUsuarios());
				pag.setMensaje(resul.getMensaje());

				getRespuesta().setPaginador(pag);
				getRespuesta().setMensaje(resul.getMensaje());
				getRespuesta().setOk(resul.getOk());

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");
				e.printStackTrace();
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje("Error al obtener los roles.");

		}

		return getRespuesta();
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

		resp.put(this.getClass().getSimpleName(), "El usuario LDAP "
				+ getUsuarioLDAP().toString() + " se listó.");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the dao
	 */
	public DAO_UsuarioLDAP getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_UsuarioLDAP dao) {
		this.dao = dao;
	}

	/**
	 * @return the usuarioLDAP
	 */
	public UsuarioLDAP getUsuarioLDAP() {
		return usuarioLDAP;
	}

	/**
	 * @param usuarioLDAP
	 *            the usuarioLDAP to set
	 */
	public void setUsuarioLDAP(UsuarioLDAP usuarioLDAP) {
		this.usuarioLDAP = usuarioLDAP;
	}

}
