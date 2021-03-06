package ar.com.builderadmin.fx.historiaClinica.pedidos.modalidad;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Modalidad;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Modalidad_VO;

public class FX_CrearModalidad implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearModalidad.class);

	private DAO_Modalidad dao_Modalidad;
	private EntityManager em;
	private Modalidad_VO modalidad;
	private String usuario;

	public FX_CrearModalidad(DAO dao, Modalidad_VO modalidad_VO,
			String nombreUsuario) {
		this.setDao((DAO_Modalidad) dao);
		this.setModalidad(modalidad_VO);
		this.setEm(getDao().getEntityManager());
		this.setUsuario(nombreUsuario);
	}

	@Override
	@Transactional
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {

			this.logger.debug("executing FX_CrearModalidad.ejecutar()");

			try {

				getDao().guardar(getModalidad());
				getDao().resetQuery();

				String detalle = "La modalidad " + getModalidad().getDescripcion()+"("+getModalidad().getCodigo()+")"
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				Alerta al = new Alerta(getUsuario(), new Date(), getModalidad()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlerta
						.guardarAlerta(getDao().getEntityManager(), al, this);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getModalidad()));
				this.getRespuesta().setOk(true);
				this.getRespuesta().setMensaje(detalle);

				System.out.println(detalle);
				this.logger.info(detalle);

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");
				e.printStackTrace();
			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe una modalidad con el mismo codigo");

		}

		return getRespuesta();
	}

	private boolean validar() {

		getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass() + ".codigo) = :codigo");

		getDao().getCondiciones().put("codigo",
				getModalidad().getCodigo().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getModalidad().getCodigo().length() == 0) {
			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Modalidad getDao() {
		return dao_Modalidad;
	}

	public void setDao(DAO_Modalidad dao) {
		this.dao_Modalidad = dao;
	}

	public Modalidad_VO getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad_VO modalidad_VO) {
		this.modalidad = modalidad_VO;
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

		resp.put(this.getClass().getCanonicalName(), "La modalidad " + getModalidad().getDescripcion()
				+"("+getModalidad().getCodigo()+")"+ " se creó correctamente");

		return resp;
	}

	/**
	 * @return the dao_Modalidad
	 */
	public DAO_Modalidad getDao_Modalidad() {
		return dao_Modalidad;
	}

	/**
	 * @param dao_Modalidad
	 *            the dao_Modalidad to set
	 */
	public void setDao_Modalidad(DAO_Modalidad dao_Modalidad) {
		this.dao_Modalidad = dao_Modalidad;
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
}
