package ar.com.builderadmin.fx.core.templates;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.templates.DAO_PropiedadTemplate;
import ar.com.builderadmin.dao.core.templates.DAO_Templates;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.templates.PropiedadTemplate_VO;
import ar.com.builderadmin.vo.core.templates.Template_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarTemplate implements I_FX {
	
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarTemplate.class);

	private DAO_Templates dao;
	private EntityManager em;
	private Template_VO propiedad;
	private String usuario;

	public FX_ModificarTemplate(DAO<Template_VO> dao, Template_VO area,
			String nombreUsuario) {
		setDao((DAO_Templates) dao);
		setTemplate(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarTemplate.ejecutar()");

			try {
				
				//Guardo las modificaciones a las propiedades de los templates
				DAO_PropiedadTemplate daoPropTemp = getDao().getInstance(DAO_PropiedadTemplate.class);

				FX_EliminarPropiedadTemplate fxEliminar = new FX_EliminarPropiedadTemplate(daoPropTemp,  null, getUsuario());
				FX_ModificarPropiedadTemplate fxModif = new FX_ModificarPropiedadTemplate(daoPropTemp,  null, getUsuario());
				I_FX fx;
				
				//Actualizo todas las propiedades del template
				for (PropiedadTemplate_VO propTemp : getTemplate().getPropiedades()) {
					
					propTemp.setIdTemplate(getTemplate().getId());
					
					if (propTemp.getBorrado()){
						fxEliminar.setPropiedad(propTemp);
						fx = fxEliminar;
					}else{
						fxModif.setPropiedadTemplate(propTemp);
						fx = fxModif;
					}
					
					fx.ejecutar(adminAlertas);
				}
				
				getDao().guardar(getTemplate());
				getDao().resetQuery();

				String detalle = "El template de servicio "
						+ getTemplate().getNombreServicio()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTemplate()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils.info(logger, "FX_ModificarTemplate", "ejecutar", detalle, getUsuario());
				System.out.println(detalle);
				this.logger.info(detalle);

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
					"Ya existe un template de servicio con el mismo nombre: "
							+ getTemplate());

		}
		return getRespuesta();
	}

	private boolean validar() {

		this.getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".servicio.nombre) = :nombre ) ");

		getDao().getCondiciones().put("nombre",
				getTemplate().getNombreServicio().toLowerCase());

		boolean ok = true;

		List<Template_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Template_VO templ : elementos) {
				if (!templ.equals(getTemplate())) {
					ok = false;
				}
			}
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

	public Template_VO getTemplate() {
		return propiedad;
	}

	public void setTemplate(Template_VO area) {
		this.propiedad = area;
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

}
