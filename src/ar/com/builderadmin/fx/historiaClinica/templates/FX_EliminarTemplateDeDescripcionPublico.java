package ar.com.builderadmin.fx.historiaClinica.templates;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPublico;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

public class FX_EliminarTemplateDeDescripcionPublico implements I_FX {
	
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarTemplateDeDescripcionPublico.class);

	private DAO_TemplateDeDescripcionPublico dao;
	private TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO;
	private String usuario;

	public FX_EliminarTemplateDeDescripcionPublico(
			DAO_TemplateDeDescripcionPublico d,
			TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO,
			String nombreUsuario) {
		setTemplateDeDescripcionPublico_VO(templateDeDescripcionPublico_VO);
		setUsuario(nombreUsuario);
		setDao(d);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		this.logger
				.debug("executing FX_EliminarTemplateDeDescripcionPublico._execute()");

		try {

			getDao().eliminar(getTemplateDeDescripcionPublico_VO());

			String detalle = "El template público  "
					+ getTemplateDeDescripcionPublico_VO().getTitulo()
					+ " se eliminó correctamente";

			getDao().resetQuery();

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(),
					getTemplateDeDescripcionPublico_VO().getId(),
					getTemplateDeDescripcionPublico_VO().getTitulo(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

			DAO_Utils.info(logger, "FX_EliminarTemplateDeDescripcionPublico", "ejecutar", getUsuario(), detalle);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getTemplateDeDescripcionPublico_VO()));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje(
					"Se produjo un error al intentar eliminar el template");

		}

		return getRespuesta();

	}

	public DAO_TemplateDeDescripcionPublico getDao() {
		return dao;
	}

	public void setDao(DAO_TemplateDeDescripcionPublico dao) {
		this.dao = dao;
	}

	public TemplateDeDescripcionPublico_VO getTemplateDeDescripcionPublico_VO() {
		return templateDeDescripcionPublico_VO;
	}

	public void setTemplateDeDescripcionPublico_VO(
			TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO) {
		this.templateDeDescripcionPublico_VO = templateDeDescripcionPublico_VO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return perfil.getNombreRol().equals(Rol.PROFESIONAL);
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * return JSON
	 */
	@Override
	public Map<String, Object> armarDatosPublicacionComet(EntityManager em) {

		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getCanonicalName(), "El template publico "
				+ getTemplateDeDescripcionPublico_VO().getTitulo()
				+ " se creó correctamente");

		return resp;
	}
}
