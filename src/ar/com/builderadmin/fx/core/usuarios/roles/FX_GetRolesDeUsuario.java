package ar.com.builderadmin.fx.core.usuarios.roles;

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
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class FX_GetRolesDeUsuario implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_GetRolesDeUsuario.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Rol dao;
	private EntityManager em;
	private String usuarioLDAP;
	private String usuario;

	public FX_GetRolesDeUsuario(DAO dao, String usr, String usuario) {
		setDao((DAO_Rol) dao);
		setUsuarioLDAP(usr);
		setUsuario(usuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {
		
		List<Rol_VO> roles = getDao().getRolesDeUsuario( this.getUsuarioLDAP());
		
		getRespuesta().setPaginador(new JSON_Paginador(roles));
		getRespuesta().setOk(true);

		return getRespuesta();
		
	}

	/**
	 * @return the dao
	 */
	public DAO_Rol getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_Rol dao) {
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

		resp.put(this.getClass().getSimpleName(),
				"Se obtuvieron los roles del usuario:  " + getUsuarioLDAP());

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the usuarioLDAP
	 */
	public String getUsuarioLDAP() {
		return usuarioLDAP;
	}

	/**
	 * @param usuarioLDAP
	 *            the usuarioLDAP to set
	 */
	public void setUsuarioLDAP(String usuarioLDAP) {
		this.usuarioLDAP = usuarioLDAP;
	}

	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}
