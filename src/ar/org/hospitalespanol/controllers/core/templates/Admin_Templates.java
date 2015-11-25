package ar.org.hospitalespanol.controllers.core.templates;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.especialidades.DAO_Especialidad;
import ar.org.hospitalespanol.dao.core.templates.DAO_Templates;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.templates.FX_BuscarTemplate;
import ar.org.hospitalespanol.fx.core.templates.FX_CrearTemplate;
import ar.org.hospitalespanol.fx.core.templates.FX_EliminarTemplate;
import ar.org.hospitalespanol.fx.core.templates.FX_ModificarTemplate;
import ar.org.hospitalespanol.vo.core.templates.Template_VO;

/**
 * Componente para el manejo de las areas
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Templates extends Admin_Abstracto<Template_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Template_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Templates dao_Templates;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Template_VO propiedad;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Template_VO area, String usr) {
		return new FX_BuscarTemplate(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(Template_VO area, String usr) {
		return new FX_CrearTemplate(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Template_VO area, String usr) {
		return new FX_EliminarTemplate(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Template_VO area, String usr) {
		return new FX_ModificarTemplate(getDao(),  area, usr);
	}

	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("area")){
			resul = this.buscarTemplates(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	public String[] buscar(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("area")){
			resul = this.buscarTemplates(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarEspecialidadPorNombre(valorABuscar),valorABuscar);
	
	}

	private String[] buscarTemplates(String valorABuscar) {
		return armarResultado(getDao_Templates().buscarTemplatesPorNombreOCodigo(valorABuscar),valorABuscar);
	}

	public String findByServicio(long idServicio, String usuarioAccion) {
		
		JSON_Paginador tmps = new JSON_Paginador(getDao_Templates().buscarTemplatePorServicio(idServicio));
		getDao_Templates().resetQuery();
		
		if (tmps.getElementos().size()>0){
			return getGson().toJson( new JSON_Respuesta(tmps) );
		}else{
			return getGson().toJson( new JSON_Respuesta("No hay templates con ese servicio") );
		}
	}
	
	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Template_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Template_VO> getDao() {
		return getDao_Templates();
	}

	/**
	 * @return the dao_Templates
	 */
	public DAO_Templates getDao_Templates() {
		return dao_Templates;
	}

	/**
	 * @param dao_Templates the dao_Templates to set
	 */
	public void setDao_Templates(DAO_Templates dao_Templates) {
		this.dao_Templates = dao_Templates;
	}


}