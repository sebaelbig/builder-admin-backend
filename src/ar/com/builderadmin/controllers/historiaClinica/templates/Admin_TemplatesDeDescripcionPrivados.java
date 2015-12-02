package ar.com.builderadmin.controllers.historiaClinica.templates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.dao.historiaClinica.templates.DAO_TemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.historiaClinica.templates.FX_BuscarTemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.historiaClinica.templates.FX_CrearTemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.historiaClinica.templates.FX_EliminarTemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.historiaClinica.templates.FX_ListarTemplateDeDescripcionPrivado;
import ar.com.builderadmin.fx.historiaClinica.templates.FX_ModificarTemplateDeDescripcionPrivado;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPrivado_VO;

@Controller
//@Scope("request")
public class Admin_TemplatesDeDescripcionPrivados extends
		Admin_Abstracto<TemplateDeDescripcionPrivado_VO> {

//	private final Logger logger = LoggerFactory.getLogger(Admin_TemplatesDeDescripcionPrivados.class);
	// @Autowired
	// private EntityManager em;
	//
	// @Autowired
	// private Usuario usuario;

	// @Autowired
	// private MenuManager menuManager;

	@Autowired
	private Paginador<TemplateDeDescripcionPrivado_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TemplateDeDescripcionPrivado dao_TemplateDeDescripcionPrivado;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO;

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

	public void setDao(DAO_TemplateDeDescripcionPrivado dao) {
		this.dao_TemplateDeDescripcionPrivado = dao;
	}

	public TemplateDeDescripcionPrivado_VO getTemplateDeDescripcionPrivado_VO() {
		return templateDeDescripcionPrivado_VO;
	}

	public void setTemplateDeDescripcionPrivado_VO(
			TemplateDeDescripcionPrivado_VO templateDeDescripcionPrivado_VO) {
		this.templateDeDescripcionPrivado_VO = templateDeDescripcionPrivado_VO;
	}

	@Override
	protected DAO_TemplateDeDescripcionPrivado getDao() {
		return dao_TemplateDeDescripcionPrivado;
	}

	@Override
	protected Paginador<TemplateDeDescripcionPrivado_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(
			Paginador<TemplateDeDescripcionPrivado_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected I_FX getFX_Crear(TemplateDeDescripcionPrivado_VO template, String usuarioAccion) {
		return new FX_CrearTemplateDeDescripcionPrivado(getDao(), template, usuarioAccion);
	}

	@Override
	protected I_FX getFX_Modificar(TemplateDeDescripcionPrivado_VO template, String usuarioAccion) {
		return new FX_ModificarTemplateDeDescripcionPrivado(getDao(), template, usuarioAccion);
	}

	@Override
	protected I_FX getFX_Eliminar(TemplateDeDescripcionPrivado_VO template, String usuarioAccion) {
		return new FX_EliminarTemplateDeDescripcionPrivado(getDao(), template, usuarioAccion);
	}

	@Override
	protected I_FX getFX_Buscar(TemplateDeDescripcionPrivado_VO template, String usuarioAccion) {
		return new FX_BuscarTemplateDeDescripcionPrivado(getDao(), template, usuarioAccion);
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	public String listar(String usuarioAccion) {
		FX_ListarTemplateDeDescripcionPrivado fx = new FX_ListarTemplateDeDescripcionPrivado(getDao(), usuarioAccion);
		
		return ejecutarFuncion(fx);
	}

	public String buscarPorMatricula(String nroMatriculaActuante,
			String usuarioAccion) {
		
		this.getDao().buscarPorMatricula(nroMatriculaActuante, usuarioAccion);
		List<TemplateDeDescripcionPrivado_VO> lista = this.getDao().listarTodo();
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador( new JSON_Paginador(lista));
		resp.setOk(true);
		
		return this.getGson().toJson(resp);
	}

	public String listarDeServicioDeUsuario(String usuario) {
		
		//Obtengo los servicios de usuario
		DAO_Usuario daoUsr = this.getDao().getInstance(DAO_Usuario.class);
		List<Servicio_VO> servicio = daoUsr.listarServiciosDeUsuario(usuario);

		List<TemplateDeDescripcionPrivado_VO> todos = new ArrayList<>();
		List<TemplateDeDescripcionPrivado_VO> temp;
		
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
