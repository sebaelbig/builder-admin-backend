package ar.com.builderadmin.fx.core.datosSalud.prestaciones;

import java.util.Date;
import java.util.HashMap;
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

public class FX_CrearTipoPrestacionHorus implements I_FX {
	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearTipoPrestacionHorus.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_TipoPrestacionHorus dao;
	private EntityManager em;
	private TipoPrestacionHorus_VO tipo;
	private String usuario;

	public FX_CrearTipoPrestacionHorus(DAO dao, TipoPrestacionHorus_VO tipo, String usuario) {
		setDao((DAO_TipoPrestacionHorus) dao);
		setUsuario(usuario);
		setTipo(tipo);
		setEm(getDao().getEntityManager());
	}

	private boolean validar() {
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass()
						+ ".nombre) = :nomTipoPrestacion ");
		
		if (getTipo().getNombre()!=null)
			getDao().getCondiciones().put("nomTipoPrestacion",
					getTipo().getNombre().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getTipo().getNombre() == null
				|| getTipo().getNombre().length() == 0) {
			ok = false;
		}

		getDao().resetQuery();

		return ok;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {

//				R_CrearTipoDeID resul = getDao().guardar(
				getDao().guardar(getTipo());
				getDao().resetQuery();

				String detalle = "El estudio " + getTipo().getCodigo()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTipo()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_CrearTipoPrestacionHorus", "ejecutar",  getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTipo()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

				e.printStackTrace();
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje("Debe ingresar un tipo de ID o el tipo de id ya existe.");

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

		resp.put(this.getClass().getSimpleName(), "El tipo de prestación "
				+ getTipo().getCodigo() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the tipo
	 */
	public TipoPrestacionHorus_VO getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoPrestacionHorus_VO tipo) {
		this.tipo = tipo;
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
