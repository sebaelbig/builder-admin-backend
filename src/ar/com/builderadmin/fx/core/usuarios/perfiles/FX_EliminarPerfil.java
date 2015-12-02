package ar.com.builderadmin.fx.core.usuarios.perfiles;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarPerfil implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarPerfil.class);

	private DAO_Perfil dao;
	private EntityManager em;
	private Perfil_VO sucursal;
	private String usuario;

	public FX_EliminarPerfil(DAO dao, Perfil_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_Perfil) dao);
		setPerfil(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		try {
			
			getDao().eliminar(getPerfil());

			String detalle = "El perfil " + getPerfil().getNombre()
					+ " se eliminó correctamente";

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getPerfil()
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
					this);

			DAO_Utils.info(logger, "FX_ModificarPerfil", "ejecutar", getUsuario(), detalle);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getPerfil()));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {
			e.printStackTrace();
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"No es posible eliminar el perfil");

		}
		
		return getRespuesta();

	}

	public DAO_Perfil getDao() {
		return dao;
	}

	public void setDao(DAO_Perfil dao) {
		this.dao = dao;
	}

	public Perfil_VO getPerfil() {
		return sucursal;
	}

	public void setPerfil(Perfil_VO sucursal) {
		this.sucursal = sucursal;
	}

	public String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	

}
