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
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPublico;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

public class FX_CrearTemplateDeDescripcionPublico implements I_FX {

	@Override
	public Boolean listar() {
		return false;
	}


	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getCanonicalName(), "El template público "
				+ getTemplateDeDescripcionPublico_VO().getTitulo()
				+ " se creó correctamente");

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
	
				getDao().guardar(getTemplateDeDescripcionPublico_VO());
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
						JSON_Paginador.solo(getTemplateDeDescripcionPublico_VO()));
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

		getDao().resetQuery();
		
		String cond = " WHERE ";

		if (getTemplateDeDescripcionPublico_VO().getNombreServicio()!=null
				&&
			getTemplateDeDescripcionPublico_VO().getTitulo()!=null){
			
			//Nombre del servicio
			cond += " lower(" + getDao().getIdClass()+ ".servicio.nombre) = :nombreServicio ";
			getDao().getCondiciones().put("nombreServicio",
					getTemplateDeDescripcionPublico_VO().getNombreServicio().toLowerCase());
			
			//Titulo de la plantilla 
			cond += " AND lower(" + getDao().getIdClass()+ ".titulo) = :titulo";
			getDao().getCondiciones().put("titulo",
					getTemplateDeDescripcionPublico_VO().getTitulo().toLowerCase());
			
			
			getDao().setQueryCondiciones(cond);
		}

				
		boolean ok = true;

		if (getDao().listarTodo().size() > 0 //Si existe alguno
				|| getTemplateDeDescripcionPublico_VO().getNombreServicio()==null 
				|| getTemplateDeDescripcionPublico_VO().getTitulo()==null 
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
