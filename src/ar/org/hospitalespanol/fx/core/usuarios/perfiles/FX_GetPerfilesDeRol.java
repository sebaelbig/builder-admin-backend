package ar.org.hospitalespanol.fx.core.usuarios.perfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_GetPerfilesDeRol implements I_FX {
	

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_GetPerfilesDeRol.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Perfil dao;
	private EntityManager em;
	private Long idRol;
	private String usuario;

	public FX_GetPerfilesDeRol(DAO dao, Long idRol, String usuario) {
		setDao((DAO_Perfil) dao);
		setIdRol(idRol);
		setEm(dao.getEntityManager());
		setUsuario(usuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {
		
		DAO_Utils.info(logger, this.getClass().getSimpleName(), "ejecutar", getUsuario(), "Se recuperan los perfiles del rol: "+getIdRol());
		
		List<Perfil_VO> perfiles = getDao().getPerfilesDeRol(this.getIdRol());
		
		getRespuesta().setPaginador(new JSON_Paginador(perfiles));
		getRespuesta().setOk(true);

		return getRespuesta();
		
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
				"Se obtuvieron los roles del usuario:  " + getUsuario());

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
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

	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(DAO_Perfil dao) {
		this.dao = dao;
	}

	/**
	 * @return the dao
	 */
	public DAO_Perfil getDao() {
		return dao;
	}
	
	
}
