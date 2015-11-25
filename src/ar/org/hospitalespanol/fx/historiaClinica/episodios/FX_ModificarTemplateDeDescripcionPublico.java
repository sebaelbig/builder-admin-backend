package ar.org.hospitalespanol.fx.historiaClinica.episodios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

public class FX_ModificarTemplateDeDescripcionPublico implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarTemplateDeDescripcionPublico.class);

	private DAO_TemplateDeDescripcionPublico dao;
	private TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO;
	private String usuario;

	public FX_ModificarTemplateDeDescripcionPublico(
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
		.debug("executing FX_ModificarTemplateDeDescripcionPublico._execute()");

		if (validar()) {
			
			try {
	
				getDao().guardar(getTemplateDeDescripcionPublico_VO());
				getDao().resetQuery();

				String detalle = "El template público "
						+ getTemplateDeDescripcionPublico_VO().getNombreServicio()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTemplateDeDescripcionPublico_VO()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTemplateDeDescripcionPublico_VO()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);
	
			} catch (Exception e) {
				this.getRespuesta().setOk(false);
	
				this.getRespuesta().setMensaje("Ocurrió un error en la grabación");
	
			}
		}else{
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un template con el mismo titulo y servicio: "
							+ getTemplateDeDescripcionPublico_VO());
		}
		return getRespuesta();
	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".nombreServicio) = :nombre ) ");

		getDao().getCondiciones().put("nombre",
				getTemplateDeDescripcionPublico_VO().getNombreServicio().toLowerCase());

		boolean ok = true;

		List<TemplateDeDescripcionPublico_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (TemplateDeDescripcionPublico_VO templ : elementos) {
				if (!templ.equals(getTemplateDeDescripcionPublico_VO())) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
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
				+ " se eliminó correctamente");

		return resp;
	}
}
