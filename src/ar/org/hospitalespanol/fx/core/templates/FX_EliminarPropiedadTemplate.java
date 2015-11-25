package ar.org.hospitalespanol.fx.core.templates;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.templates.DAO_PropiedadTemplate;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.templates.PropiedadTemplate_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarPropiedadTemplate implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarPropiedadTemplate.class);

	private DAO_PropiedadTemplate dao;
	private EntityManager em;
	private PropiedadTemplate_VO propiedad;
	private String usuario;

	public FX_EliminarPropiedadTemplate(DAO<PropiedadTemplate_VO> dao, PropiedadTemplate_VO area,
			String nombreUsuario) {
		setDao((DAO_PropiedadTemplate) dao);
		setPropiedad(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		try {

			getDao().eliminar(getPropiedad());

			String detalle = "La propiedad del template de servicio "
					+ getPropiedad().getPropiedad().getNombre()
					+ " se eliminó correctamente";

			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getPropiedad()
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

			System.out.println(detalle);
			this.logger.info(detalle);

			this.getRespuesta()
					.setPaginador(JSON_Paginador.solo(getPropiedad()));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"No es posible eliminar la propiedad el template: "
							+ getPropiedad());

		}
		return getRespuesta();

	}

	public DAO_PropiedadTemplate getDao() {
		return dao;
	}

	public void setDao(DAO_PropiedadTemplate dao) {
		this.dao = dao;
	}

	/**
	 * @return the propiedad
	 */
	public PropiedadTemplate_VO getPropiedad() {
		return propiedad;
	}

	/**
	 * @param propiedad the propiedad to set
	 */
	public void setPropiedad(PropiedadTemplate_VO propiedad) {
		this.propiedad = propiedad;
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
		return false;
	}

}
