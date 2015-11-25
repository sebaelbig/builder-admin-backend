package ar.org.hospitalespanol.fx.historiaClinica.episodios;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.historiaClinica.templates.TemplateDeDescripcionPublico;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

public class FX_CrearTemplateDeDescripcionPublico implements I_FX {

	@Override
	public Boolean listar() {
		return false;
	}


	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getCanonicalName(), "El template privado "
				+ getTemplateDeDescripcionPublico_VO().getTitulo()
				+ " se creo correctamente");

		return resp;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearTemplateDeDescripcionPublico.class);

	private DAO_TemplateDeDescripcionPublico dao_TemplateDeDescripcionPublico;

	private TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO;

	private String usuario;

	public FX_CrearTemplateDeDescripcionPublico(DAO dao,
			TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO,
			String nombreUsuario) {
		this.setDao((DAO_TemplateDeDescripcionPublico) dao);
		setTemplateDeDescripcionPublico_VO(templateDeDescripcionPublico_VO);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {
			
			try {
	
				TemplateDeDescripcionPublico t = (TemplateDeDescripcionPublico) getDao().guardar(getTemplateDeDescripcionPublico_VO());
				getTemplateDeDescripcionPublico_VO().setId(t.getId());
				
	//			R_CrearEntidad resul = getDao()
	//					.guardar(getDao().getEntityManager(), getTemplateDeDescripcionPublico_VO());
				getDao().resetQuery();
	
				String detalle = "El template público "
						+ getTemplateDeDescripcionPublico_VO().getTitulo()
						+ " se creó correctamente";
	
				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				// Genero un ID de tipo Long
				Alerta al = new Alerta(getUsuario(), new Date(), getTemplateDeDescripcionPublico_VO()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);
	
				adminAlerta.guardarAlerta(getDao().getEntityManager(), al,
						this);
	
				DAO_Utils.info(logger, "FX_CrearTemplateDeDescripcionPublico", "ejecutar", getUsuario(), detalle);
	
				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(t.toValueObject()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);
	
			} catch (Exception e) {
				this.getRespuesta().setOk(false);
	
				this.getRespuesta().setMensaje("Ocurrió un error en la grabación");
				e.printStackTrace();
			}
		}else{
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un template para este servicio con el titulo: "+this.getTemplateDeDescripcionPublico_VO().getTitulo());
		}
		
		return getRespuesta();
	}
	
	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass()
						+ ".servicio.nombre) = :nombreServicio " +
						"AND "
						+"lower(" + getDao().getIdClass()
						+ ".titulo) = :titulo"
				
				);

		getDao().getCondiciones().put("nombreServicio",
				getTemplateDeDescripcionPublico_VO().getNombreServicio().toLowerCase());
		getDao().getCondiciones().put("titulo",
				getTemplateDeDescripcionPublico_VO().getTitulo().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0 //Si existe alguno
				|| getTemplateDeDescripcionPublico_VO().getNombreServicio().length() == 0 //Que haya seleccionado un sevicio
				|| getTemplateDeDescripcionPublico_VO().getTitulo().length() == 0 ) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_TemplateDeDescripcionPublico getDao() {
		return dao_TemplateDeDescripcionPublico;
	}

	public void setDao(DAO_TemplateDeDescripcionPublico dao) {
		this.dao_TemplateDeDescripcionPublico = dao;
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
		return perfil.tieneServicio();
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

}
