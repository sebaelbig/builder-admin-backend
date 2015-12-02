package ar.com.builderadmin.fx.core.areas.servicios;

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
import ar.com.builderadmin.dao.core.areas.servicios.DAO_Servicio;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearServicio implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearServicio.class);

	private DAO_Servicio dao_Servicio;
	private EntityManager em;
	private Servicio_VO servicio;
	private String usuario;

	public FX_CrearServicio(DAO dao, Servicio_VO servicio_VO,
			String nombreUsuario) {
		this.setDao((DAO_Servicio) dao);
		this.setServicio(servicio_VO);
		this.setEm(getDao().getEntityManager());
		this.setUsuario(nombreUsuario);
	}

	@Override
	@Transactional
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {

			this.logger.debug("executing FX_CrearServicio.ejecutar()");

			try {

				getDao().guardar(getServicio());
				getDao().resetQuery();

				String detalle = "El servicio " + getServicio().getNombre()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				Alerta al = new Alerta(getUsuario(), new Date(), getServicio()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlerta
						.guardarAlerta(getDao().getEntityManager(), al, this);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getServicio()));
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
					"Ya existe un servicio con el mismo nombre, código y área");

		}

		return getRespuesta();
	}

	private boolean validar() {

		getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass() + ".nombre) = :nombre AND "
						+ getDao().getIdClass() + ".area.id = :idArea ");

		getDao().getCondiciones().put("nombre",
				getServicio().getNombre().toLowerCase());

		getDao().getCondiciones()
				.put("idArea", getServicio().getArea().getId());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getServicio().getNombre().length() == 0
				|| getServicio().getCodigo().length() == 0
				|| getServicio().getArea() == null) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Servicio getDao() {
		return dao_Servicio;
	}

	public void setDao(DAO_Servicio dao) {
		this.dao_Servicio = dao;
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio_VO) {
		this.servicio = servicio_VO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return perfil.tieneServicio();
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

		resp.put(this.getClass().getCanonicalName(), "El servicio "
				+ getServicio().getNombre() + " se creó correctamente");

		return resp;
	}

	/**
	 * @return the dao_Servicio
	 */
	public DAO_Servicio getDao_Servicio() {
		return dao_Servicio;
	}

	/**
	 * @param dao_Servicio
	 *            the dao_Servicio to set
	 */
	public void setDao_Servicio(DAO_Servicio dao_Servicio) {
		this.dao_Servicio = dao_Servicio;
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
