package ar.com.builderadmin.fx.core.usuarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ListarUsuario implements I_FX {
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ListarUsuario.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Usuario dao;
	private Usuario usuario;
	private String str_usuario;

	public FX_ListarUsuario(DAO dao, Usuario usr, String usuario) {
		setDao((DAO_Usuario) dao);
		setUsuario(usr);
		setStrUsuario(usuario);
	}

	private boolean validar() {
		return true;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {

			try {

//				R_GetUsuarios resul = getDao().listarTodo();
				List users = getDao().listarTodo();

				JSON_Paginador pag = new JSON_Paginador();
				pag.setElementos(users);
//				pag.setMensaje(resul.getMensaje());

				getRespuesta().setPaginador(pag);
//				getRespuesta().setMensaje(resul.getMensaje());
//				getRespuesta().setOk(resul.getOk());

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
	public String getStrUsuario() {
		return str_usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setStrUsuario(String usuario) {
		this.str_usuario = str_usuario;
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

		resp.put(this.getClass().getSimpleName(), "El usuario  "
				+ getUsuario().toString() + " se listó.");

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
	public DAO_Usuario getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_Usuario dao) {
		this.dao = dao;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return str_usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
