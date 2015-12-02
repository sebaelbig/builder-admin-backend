package ar.com.builderadmin.fx.core.usuarios;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarUsuario implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarUsuario.class);

	private DAO_Usuario dao;
	private EntityManager em;
	private Usuario_VO usuario;
	private String usuarioAccion;

	public FX_EliminarUsuario(DAO<Usuario_VO> dao, Usuario_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_Usuario) dao);
		setUsuario(sucursal);
		setUsuarioAccion(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		try {
			
			this.usuario.setBorrado(true);
			getDao().guardar(this.usuario);

			String detalle = "El usuario " + this.usuario.getNombres()
					+ " se eliminó correctamente";

			// Pasaje de la entidad activa a la entidad historica
			// getEm().persist(sucursal.toHistoricoObject());

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuarioAccion(), new Date(), this.usuario
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
					this);

			DAO_Utils.info(logger, "FX_ModificarUsuario", "ejecutar", getUsuarioAccion(), detalle);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(this.usuario));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"No es posible eliminar el tipo de rol");

		}
		
		return getRespuesta();

	}

	public DAO_Usuario getDao() {
		return dao;
	}

	public void setDao(DAO_Usuario dao) {
		this.dao = dao;
	}


	public void setUsuario(Usuario_VO sucursal) {
		this.usuario = sucursal;
	}

	private String getUsuarioAccion() {
		return usuarioAccion;
	}

	private void setUsuarioAccion(String usuario) {
		this.usuarioAccion = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		return new java.util.HashMap<String, Object>();
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public Boolean listar() {
		return false;
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

	@Override
	public String getUsuario() {
		return usuarioAccion;
	}
	
	

}
