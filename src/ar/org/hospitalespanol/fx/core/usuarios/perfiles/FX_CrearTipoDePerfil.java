package ar.org.hospitalespanol.fx.core.usuarios.perfiles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_TipoDePerfil;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.TipoDePerfil_VO;

public class FX_CrearTipoDePerfil implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearTipoDePerfil.class);

	private DAO_TipoDePerfil dao;
	private EntityManager em;
	private TipoDePerfil_VO sucursal;
	private String usuario;

	public FX_CrearTipoDePerfil(DAO<TipoDePerfil_VO> dao, TipoDePerfil_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDePerfil) dao);
		setTipoDePerfil(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearTipoDePerfil.ejecutar()");

			try {

				TipoDePerfil a = (TipoDePerfil) getDao().guardar(getTipoDePerfil());

				getDao().resetQuery();

				String detalle = "El tipo de perfil " + getTipoDePerfil().getNombre()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTipoDePerfil()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_CrearTipoDePerfil", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTipoDePerfil()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un tipo de perfil con el mismo nombre o código ");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".nombre) = :nombre AND lower("
						+ getDao().getIdClass() + ".codigo) = :codigo ) ");

		getDao().getCondiciones().put("nombre",
				getTipoDePerfil().getNombre().toLowerCase());

		getDao().getCondiciones().put("codigo",
				getTipoDePerfil().getCodigo().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getTipoDePerfil().getNombre().length() == 0
				|| getTipoDePerfil().getCodigo().length() == 0) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_TipoDePerfil getDao() {
		return dao;
	}

	public void setDao(DAO_TipoDePerfil dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
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

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El tipo de perfil "
				+ getTipoDePerfil().getNombre() + " se creó correctamente");

		return resp;
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
		return true;
	}
}
