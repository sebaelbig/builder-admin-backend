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
import ar.com.builderadmin.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPrivado_VO;

public class FX_ModificarTemplateDeDescripcionPrivado implements I_FX {
	
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarTemplateDeDescripcionPrivado.class);

	private DAO_TemplateDeDescripcionPrivado dao;
	private TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO;
	private String usuario;

	public FX_ModificarTemplateDeDescripcionPrivado(
			DAO_TemplateDeDescripcionPrivado d,
			TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO,
			String nombreUsuario) {
		setTemplateDeDescripcionPrivado_VO(templateDeDescripcionPrivado_VO);
		setUsuario(nombreUsuario);
		setDao(d);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		this.logger
				.debug("executing FX_ModificarTemplateDeDescripcionPrivado._execute()");

		try {

			getDao().guardar(getTemplateDeDescripcionPrivado_VO());

			String detalle = "El template privado "
					+ getTemplateDeDescripcionPrivado_VO().getTitulo()
					+ " se modificó correctamente";

			getDao().resetQuery();

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(),
					getTemplateDeDescripcionPrivado_VO().getId(),
					getTemplateDeDescripcionPrivado_VO().getTitulo(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

			System.out.println(detalle);
			this.logger.info(detalle);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getTemplateDeDescripcionPrivado_VO()));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje("Ocurri� un error en la grabaci�n");

		}
		return getRespuesta();
	}

	public DAO_TemplateDeDescripcionPrivado getDao() {
		return dao;
	}

	public void setDao(DAO_TemplateDeDescripcionPrivado dao) {
		this.dao = dao;
	}

	public TemplateDeDescripcionPrivado_VO getTemplateDeDescripcionPrivado_VO() {
		return templateDeDescripcionPrivado_VO;
	}

	public void setTemplateDeDescripcionPrivado_VO(
			TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO) {
		this.templateDeDescripcionPrivado_VO = templateDeDescripcionPrivado_VO;
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

		resp.put(this.getClass().getCanonicalName(), "El template privado "
				+ getTemplateDeDescripcionPrivado_VO().getTitulo()
				+ " se eliminó correctamente");

		return resp;
	}
}
