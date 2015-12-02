package ar.com.builderadmin.fx.core.datosSalud.prestaciones;

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
import ar.com.builderadmin.dao.core.datosSalud.prestaciones.DAO_TipoPrestacionHorus;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.datosSalud.TipoPrestacionHorus_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarTipoPrestacionHorus implements I_FX {
	
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarTipoPrestacionHorus.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_TipoPrestacionHorus dao;
	private EntityManager em;
	private TipoPrestacionHorus_VO tipo;
	private String usuario;

	public FX_ModificarTipoPrestacionHorus(DAO dao, TipoPrestacionHorus_VO rol, String usuario) {
		setDao((DAO_TipoPrestacionHorus) dao);
		setTipo(rol);
		setUsuario(usuario);
		setEm(getDao().getEntityManager());
	}

	private boolean validar() {

		if (getTipo().getCodigo()!=null){
			getDao().setQueryCondiciones(
					" WHERE lower(" + getDao().getIdClass()
					+ ".codigo ) = :codigo");
			getDao().getCondiciones().put("codigo",
					getTipo().getCodigo().toLowerCase());
		}
		
		boolean ok = true;

		List<TipoPrestacionHorus_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (TipoPrestacionHorus_VO templ : elementos) {
				if (templ.equals(getTipo())) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarTipoDePerfil.ejecutar()");

			try {

				getDao().guardar(getTipo());
				getDao().resetQuery();

				String detalle = "El tipo de ID " + getTipo().getCodigo()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTipo()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_ModificarTipoDePerfil", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTipo()));
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
					"Ya existe un tipo de ID con el mismo nombre o código");

		}
		return getRespuesta();
	}

	/**
	 * @return the dao
	 */
	public DAO_TipoPrestacionHorus getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_TipoPrestacionHorus dao) {
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

		resp.put(this.getClass().getSimpleName(), "El Tipo ID "
				+ getTipo().getCodigo() + " se modificó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
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
	 * @return the tipo
	 */
	public TipoPrestacionHorus_VO getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoPrestacionHorus_VO tipo) {
		this.tipo = tipo;
	}
	
	

}
