package ar.com.builderadmin.fx.core.usuarios.perfiles;

import java.util.Date;
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
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_TipoDePerfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

public class FX_ModificarTipoDePerfil implements I_FX {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarTipoDePerfil.class);

	private DAO_TipoDePerfil dao;
	private DAO_Perfil daoPerfil;
	private EntityManager em;
	private TipoDePerfil_VO sucursal;
	private String usuario;

	public FX_ModificarTipoDePerfil(DAO<TipoDePerfil_VO> dao, DAO<Perfil_VO> daoPerfil, TipoDePerfil_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDePerfil) dao);
		setDaoPerfil((DAO_Perfil) daoPerfil);
		setTipoDePerfil(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarTipoDePerfil.ejecutar()");

			try {

				getDao().guardar(getTipoDePerfil());
				getDao().resetQuery();

				String detalle = "El tipo de perfil " + getTipoDePerfil().getNombre()
						+ " se modificó correctamente";

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
				e.printStackTrace();
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un tipo de rol con el mismo nombre o código");

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

		List<TipoDePerfil_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (TipoDePerfil_VO tipoPerfil : elementos) {
				if (!tipoPerfil.equals(getTipoDePerfil())) {
					ok = false;
				}
			}
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
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El tipo de perfil "
				+ getTipoDePerfil().getNombre() + " se modificó correctamente");

		return resp;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * @return the daoPerfil
	 */
	public DAO_Perfil getDaoPerfil() {
		return daoPerfil;
	}

	/**
	 * @param daoPerfil the daoPerfil to set
	 */
	public void setDaoPerfil(DAO_Perfil daoPerfil) {
		this.daoPerfil = daoPerfil;
	}

}
