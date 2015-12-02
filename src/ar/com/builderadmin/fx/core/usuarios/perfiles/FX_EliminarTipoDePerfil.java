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
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_TipoDePerfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

public class FX_EliminarTipoDePerfil implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarTipoDePerfil.class);

	private DAO_TipoDePerfil dao;
	private EntityManager em;
	private TipoDePerfil_VO sucursal;
	private String usuario;

	public FX_EliminarTipoDePerfil(DAO<TipoDePerfil_VO> dao, TipoDePerfil_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDePerfil) dao);
		setTipoDePerfil(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		try {
			
			getTipoDePerfil().setBorrado(true);
			getDao().guardar(getTipoDePerfil());

			String detalle = "El tipo de perfil " + getTipoDePerfil().getNombre()
					+ " se eliminó correctamente";

			// Pasaje de la entidad activa a la entidad historica
			// getEm().persist(sucursal.toHistoricoObject());

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getTipoDePerfil()
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
					this);

			DAO_Utils.info(logger, "FX_ModificarTipoDePerfil", "ejecutar", getUsuario(), detalle);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getTipoDePerfil()));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"No es posible eliminar el tipo de perfil");

		}
		
		return getRespuesta();

	}

	public DAO_TipoDePerfil getDao() {
		return dao;
	}

	public void setDao(DAO_TipoDePerfil dao) {
		this.dao = dao;
	}

	public TipoDePerfil_VO getTipoDePerfil() {
		return sucursal;
	}

	public void setTipoDePerfil(TipoDePerfil_VO sucursal) {
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
