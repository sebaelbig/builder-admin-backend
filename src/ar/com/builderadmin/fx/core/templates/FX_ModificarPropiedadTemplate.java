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
import ar.com.builderadmin.dao.core.templates.DAO_PropiedadTemplate;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.core.templates.PropiedadTemplate;
import ar.com.builderadmin.vo.core.templates.PropiedadTemplate_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarPropiedadTemplate implements I_FX {

	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarPropiedadTemplate.class);

	private DAO_PropiedadTemplate dao;
	private EntityManager em;
	private PropiedadTemplate_VO propiedadTemplate;
	private String usuario;

	public FX_ModificarPropiedadTemplate(DAO<PropiedadTemplate_VO> dao,
			PropiedadTemplate_VO area, String nombreUsuario) {
		setDao((DAO_PropiedadTemplate) dao);
		setPropiedadTemplate(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarTemplate.ejecutar()");

			try {

				PropiedadTemplate prop = (PropiedadTemplate) getDao().guardar(getPropiedadTemplate());
				setPropiedadTemplate(prop.toValueObject());
				
				getDao().resetQuery();

				String detalle = "La propeidad del template de servicio "
						+ getPropiedadTemplate().getPropiedad().getNombre()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getPropiedadTemplate().getId(), this.getClass()
								.getCanonicalName(), detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils.info(logger, "FX_ModificarPropiedadTemplate",
						"ejecutar", detalle, getUsuario());
				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getPropiedadTemplate()));
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
					"Ya existe la propiedad del template de servicio con el mismo nombre: "
							+ getPropiedadTemplate());

		}
		return getRespuesta();
	}

	private boolean validar() {

		// this.getDao().resetQuery();
		//
		// getDao().setQueryCondiciones(
		// " WHERE (" + getDao().getIdClass()
		// + ".propiedad.id) = :idProp  " +
		// " AND " +
		// getDao().getIdClass()+".template.id ");
		//
		// getDao().getCondiciones().put("idProp",
		// getPropiedadTemplate().getPropiedad().getId());

		boolean ok = true;

		// List<PropiedadTemplate_VO> elementos = getDao().listarTodo();
		//
		// if (elementos.size() > 0) {
		// for (PropiedadTemplate_VO templ : elementos) {
		// if (!templ.equals(getPropiedadTemplate())) {
		// ok = false;
		// }
		// }
		// }
		//
		// getDao().resetQuery();

		return ok;
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
		return new java.util.HashMap<String, Object>();
	}

	/**
	 * @return the dao
	 */
	public DAO_PropiedadTemplate getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_PropiedadTemplate dao) {
		this.dao = dao;
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

	/**
	 * @return the propiedad
	 */
	public PropiedadTemplate_VO getPropiedadTemplate() {
		return propiedadTemplate;
	}

	/**
	 * @param propiedad
	 *            the propiedad to set
	 */
	public void setPropiedadTemplate(PropiedadTemplate_VO propiedad) {
		this.propiedadTemplate = propiedad;
	}

}
