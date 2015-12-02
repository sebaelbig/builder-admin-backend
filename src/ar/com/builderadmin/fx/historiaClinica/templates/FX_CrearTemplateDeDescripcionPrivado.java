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

public class FX_CrearTemplateDeDescripcionPrivado implements I_FX {

	@Override
	public Boolean listar() {
		return false;
	}

		
	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearTemplateDeDescripcionPrivado.class);

	private DAO_TemplateDeDescripcionPrivado dao;
	private TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO;
	private String usuario;

	public FX_CrearTemplateDeDescripcionPrivado(
			DAO_TemplateDeDescripcionPrivado d,
			TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO,
			String nombreUsuario) {
		setTemplateDeDescripcionPrivado_VO(templateDeDescripcionPrivado_VO);
		setUsuario(nombreUsuario);
		setDao(d);
	}

	
	public String getIdClass() {
		return "templatePrivado";
	}
	
	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		this.logger
				.debug("executing FX_CrearTemplateDeDescripcionPrivado._execute()");
		if (validar()) {
			try {
	
				getDao()
						.guardar(getTemplateDeDescripcionPrivado_VO());
	
				String detalle = "El template privado "
						+ getTemplateDeDescripcionPrivado_VO().getTitulo()
						+ " se creo correctamente";
	
				getDao().resetQuery();
	
				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getTemplateDeDescripcionPrivado_VO().getId(),
						getTemplateDeDescripcionPrivado_VO().getTitulo(), detalle,
						E_Priority.BAJA);
	
				adminAlerta.guardarAlerta(getDao().getEntityManager(), al, this);
	
				System.out.println(detalle);
				this.logger.info(detalle);
	
				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTemplateDeDescripcionPrivado_VO()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);
	
			} catch (Exception e) {
	
				this.getRespuesta().setMensaje("Ocurrió un error en la grabación");
				this.getRespuesta().setOk(false);
	
			}

		}
		else{
			this.getRespuesta().setOk(false);
//			
//			this.getRespuesta().setMensaje(
//					"Ya existe un template para este profesional con el titulo: "+this.getTemplateDeDescripcionPrivado_VO().getTitulo());
		}
		
		return getRespuesta();

	}


	private boolean validar() {

		getDao().resetQuery();
		
		String cond = " WHERE ";

		if (getTemplateDeDescripcionPrivado_VO().getIdPerfil()!=null
				&&
				getTemplateDeDescripcionPrivado_VO().getTitulo()!=null){
			
			//Nombre del servicio
			cond += getDao().getIdClass()+ ".perfil.id = :perfil ";
			getDao().getCondiciones().put("perfil",
					getTemplateDeDescripcionPrivado_VO().getIdPerfil());
			
			//Titulo de la plantilla 
			cond += " AND lower(" + getDao().getIdClass()+ ".titulo) = :titulo";
			getDao().getCondiciones().put("titulo",
					getTemplateDeDescripcionPrivado_VO().getTitulo().toLowerCase());
			
			
			getDao().setQueryCondiciones(cond);
		}

				
		boolean ok = true;

		if (getDao().listarTodo().size() > 0 ){ //Si existe alguno
			this.getRespuesta().setMensaje("Ya existe un template para este profesional con el titulo: "+this.getTemplateDeDescripcionPrivado_VO().getTitulo());
			ok = false;
		} 
		if(getTemplateDeDescripcionPrivado_VO().getIdPerfil()==null){
			this.getRespuesta().setMensaje("Debe seleccionar un médico");
			ok = false;
		}
		if(getTemplateDeDescripcionPrivado_VO().getTitulo()==null || getTemplateDeDescripcionPrivado_VO().getTitulo().length() == 0){
			this.getRespuesta().setMensaje("Debe ingresar un Título");
			ok = false;
		}
//		if(getTemplateDeDescripcionPrivado_VO().getNombreServicio().length() == 0){//Que haya seleccionado un sevicio
//			this.getRespuesta().setMensaje("Debe seleccionar un servicio");
//			ok = false;
//		}		 
			 	

		getDao().resetQuery();

		return ok;
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
				+ " se creó correctamente");

		return resp;
	}
	


}
