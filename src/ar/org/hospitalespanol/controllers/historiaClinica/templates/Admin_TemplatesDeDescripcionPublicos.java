package ar.org.hospitalespanol.controllers.historiaClinica.templates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_Usuario;
import ar.org.hospitalespanol.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.historiaClinica.templates.FX_BuscarTemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.historiaClinica.templates.FX_CrearTemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.historiaClinica.templates.FX_EliminarTemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.historiaClinica.templates.FX_ListarTemplateDeDescripcionPublico;
import ar.org.hospitalespanol.fx.historiaClinica.templates.FX_ModificarTemplateDeDescripcionPublico;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

import com.google.gson.Gson;

@Controller
//@Scope("request")
public class Admin_TemplatesDeDescripcionPublicos extends
		Admin_Abstracto<TemplateDeDescripcionPublico_VO> {

	@Autowired
	private Paginador<TemplateDeDescripcionPublico_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TemplateDeDescripcionPublico dao_TemplateDeDescripcionPublico;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private TemplateDeDescripcionPublico_VO templateDeDescripcionPublico_VO;

	private JSON_Respuesta json_respuesta;

	@Override
	protected JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
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

	@Override
	protected DAO_TemplateDeDescripcionPublico getDao() {
		return dao_TemplateDeDescripcionPublico;
	}

	@Override
	protected Paginador<TemplateDeDescripcionPublico_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(
			Paginador<TemplateDeDescripcionPublico_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected I_FX getFX_Crear(TemplateDeDescripcionPublico_VO template, String usuarioAccion) {
		return new FX_CrearTemplateDeDescripcionPublico(getDao(), template, usuarioAccion);
	}

	@Override
	protected I_FX getFX_Modificar(TemplateDeDescripcionPublico_VO template, String usuarioAccion) {
		return new FX_ModificarTemplateDeDescripcionPublico(getDao(), template, usuarioAccion);
	}

	@Override
	protected I_FX getFX_Eliminar(TemplateDeDescripcionPublico_VO template, String usuarioAccion) {
		return new FX_EliminarTemplateDeDescripcionPublico(getDao(), template, usuarioAccion);
	}

	@Override
	protected I_FX getFX_Buscar(TemplateDeDescripcionPublico_VO template, String usuarioAccion) {
		return new FX_BuscarTemplateDeDescripcionPublico(getDao(), template, usuarioAccion);
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	public String listar(String usuarioAccion) {
		FX_ListarTemplateDeDescripcionPublico fx = new FX_ListarTemplateDeDescripcionPublico(getDao(), usuarioAccion);
		
		return ejecutarFuncion(fx);
	}

	public String listarServiciosDeUsuario(String usuario) {
			
		//Obtengo los servicios de usuario
		DAO_Usuario daoUsr = this.getDao().getInstance(DAO_Usuario.class);
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setOk(true);
		
		List<Servicio_VO> servicios = daoUsr.listarServiciosDeUsuario(usuario);
		JSON_Paginador pag = new JSON_Paginador(servicios);
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}
	
	public String listarDeServicioDeUsuario(String usuario) {
		
		//Obtengo los servicios de usuario
		DAO_Usuario daoUsr = this.getDao().getInstance(DAO_Usuario.class);
		List<Servicio_VO> servicio = daoUsr.listarServiciosDeUsuario(usuario);

		List<TemplateDeDescripcionPublico_VO> todos = new ArrayList<>();
		List<TemplateDeDescripcionPublico_VO> temp;
		
		//Recorre todos los servicios del usuario y por cada uno obtengo los templates
		for (Servicio_VO servicio_VO : servicio) {
			temp = this.getDao().listarDeServicio(servicio_VO, usuario);
			todos.addAll(temp);
		}
		JSON_Paginador pag = new JSON_Paginador(todos);
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}
	
	
}