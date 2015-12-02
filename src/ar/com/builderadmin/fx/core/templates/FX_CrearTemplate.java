package ar.com.builderadmin.fx.core.templates;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.templates.DAO_Templates;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.templates.Template_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearTemplate implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_CrearTemplate.class);

	private DAO_Templates dao;
	private EntityManager em;
	private Template_VO area;
	private String usuario;

	public FX_CrearTemplate(DAO<Template_VO> dao, Template_VO area, String nombreUsuario) {
		setDao((DAO_Templates) dao);
		setTemplate(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearTemplate.ejecutar()");

			try {

				getDao().guardar(getTemplate());
				getDao().resetQuery();

				String detalle = "El template  " + getTemplate().toString()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTemplate()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_CrearTemplate", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTemplate()));
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
					"Ya existe un template para este servicio con el nombre: "+this.getTemplate().getNombreServicio());

		}

		return getRespuesta();

	}

	private boolean validar() {

		this.getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass()
						+ ".servicio.nombre) = :nombreServicio ");

		getDao().getCondiciones().put("nombreServicio",
				getTemplate().getNombreServicio().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getTemplate().getNombreServicio().length() == 0 ) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Templates getDao() {
		return dao;
	}

	public void setDao(DAO_Templates dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Template_VO getTemplate() {
		return area;
	}

	public void setTemplate(Template_VO area) {
		this.area = area;
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

}
