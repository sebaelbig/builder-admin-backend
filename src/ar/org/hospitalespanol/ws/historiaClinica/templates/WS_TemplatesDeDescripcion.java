package ar.org.hospitalespanol.ws.historiaClinica.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.historiaClinica.templates.Admin_TemplatesDeDescripcionPrivados;
import ar.org.hospitalespanol.controllers.historiaClinica.templates.Admin_TemplatesDeDescripcionPublicos;
import ar.org.hospitalespanol.vo.historiaClinica.templates.TemplateDeDescripcionPrivado_VO;
import ar.org.hospitalespanol.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;


@RestController
@RequestMapping(value = "/templatesDeDescripcion", produces = "application/json;charset=utf-8")
public class WS_TemplatesDeDescripcion extends WS_Abstracto {

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_TemplatesDeDescripcion -> ping";
	}

	/****************************************************************************************/
	/* Templates Privados */
	/****************************************************************************************/
	@Autowired
	private Admin_TemplatesDeDescripcionPrivados admin_TemplatesDeDescripcionPrivados;

	@RequestMapping(value = "/privados/seguro/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crearPrivado(@RequestBody String jsonPrivado) {
		System.out.println("WS_TemplatesDeDescripcion -> crearPrivado("
				+ jsonPrivado + ")");

		TemplateDeDescripcionPrivado_VO template = new Gson().fromJson(
				jsonPrivado, TemplateDeDescripcionPrivado_VO.class);

		return admin_TemplatesDeDescripcionPrivados.crear(template,
				getUsuarioAccion());
	}
	
	@RequestMapping(value = "/privados/seguro", 
				produces = "application/json;charset=utf-8", 
				method = RequestMethod.GET)
	public String listarPrivados(@RequestBody String str_template) {
		System.out.println("WS_TemplatesDeDescripcion -> listarPrivados()");
	
		return admin_TemplatesDeDescripcionPrivados.listarTodos(getUsuarioAccion());
	}

	@RequestMapping(value = "/privados/seguro/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByIdPrivados(@PathVariable String codigo) {
		System.out.println("WS_TemplatesDeDescripcion -> findByIdPrivados(" + codigo + ")");

		return admin_TemplatesDeDescripcionPrivados.findById(Long.parseLong(codigo), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/privados/seguro/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TemplatesDeDescripcion -> eliminar(" + jsonPrivado + ")");

		TemplateDeDescripcionPrivado_VO template = new Gson().fromJson(
				jsonPrivado, TemplateDeDescripcionPrivado_VO.class);

		return admin_TemplatesDeDescripcionPrivados.eliminar(template, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/privados/seguro/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificarPrivado(@RequestBody String str_json) {
		System.out.println("WS_TemplatesDeDescripcion -> modificar("
				+ str_json + ")");
		
		TemplateDeDescripcionPrivado_VO template = new Gson().fromJson(
				str_json, TemplateDeDescripcionPrivado_VO.class);
		
		return admin_TemplatesDeDescripcionPrivados.modificar(template,
				getUsuarioAccion());
	}
	
	@RequestMapping(value = "/privados/seguro/buscarPorMatricula", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String buscarPorMatricula(@RequestBody String nroMatriculaActuante) {
		System.out.println("WS_TemplatesDeDescripcion -> buscarPorMatricula("
				+ nroMatriculaActuante + ")");

		return admin_TemplatesDeDescripcionPrivados.buscarPorMatricula(nroMatriculaActuante,
				getUsuarioAccion());
	}

	@RequestMapping(value = "/privados/seguro/listarPrivadosServiciosDeUsuario", 
			produces = "application/json;charset=utf-8", 
			method = RequestMethod.GET)
	public String listarPrivadosDeServicioDeUsuario() {
		System.out.println("WS_TemplatesDeDescripcion -> listarPrivadosServiciosDeUsuario("+getUsuarioAccion()+")");
		
		return admin_TemplatesDeDescripcionPrivados.listarDeServicioDeUsuario(getUsuarioAccion());
	}
	/****************************************************************************************/
	/* Templates Publicos */
	/****************************************************************************************/
	@Autowired
	private Admin_TemplatesDeDescripcionPublicos admin_TemplatesDeDescripcionPublicos;

	@RequestMapping(value = "/publicos/seguro/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crearPublico(@RequestBody String str_json) {
		System.out.println("WS_TemplatesDeDescripcion -> crearPublico("
				+ str_json + ")");

		TemplateDeDescripcionPublico_VO template = new Gson().fromJson(
				str_json, TemplateDeDescripcionPublico_VO.class);

		return admin_TemplatesDeDescripcionPublicos.crear(template,
				getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String str_json) {
		System.out.println("WS_TemplatesDeDescripcion -> modificar("
				+ str_json + ")");

		TemplateDeDescripcionPublico_VO template = new Gson().fromJson(
				str_json, TemplateDeDescripcionPublico_VO.class);

		return admin_TemplatesDeDescripcionPublicos.modificar(template,
				getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro", 
			produces = "application/json;charset=utf-8", 
			method = RequestMethod.GET)
	public String listar(@RequestBody String str_template) {
		System.out.println("WS_TemplatesDeDescripcion -> listar()");
		
		return admin_TemplatesDeDescripcionPublicos.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro/listarDeServicioDeUsuario", 
			produces = "application/json;charset=utf-8", 
			method = RequestMethod.GET)
	public String listarDeServicioDeUsuario() {
		System.out.println("WS_TemplatesDeDescripcion -> listarDeServicioDeUsuario("+getUsuarioAccion()+")");
		
		return admin_TemplatesDeDescripcionPublicos.listarDeServicioDeUsuario(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro/listarServiciosDeUsuario", 
				produces = "application/json;charset=utf-8", 
				method = RequestMethod.GET)
	public String listarServiciosDeUsuario() {
		System.out.println("WS_TemplatesDeDescripcion -> listarServiciosDeUsuario("+getUsuarioAccion()+")");

		return admin_TemplatesDeDescripcionPublicos.listarServiciosDeUsuario(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_TemplatesDeDescripcion -> findById(" + codigo + ")");

		return admin_TemplatesDeDescripcionPublicos.findById(Long.parseLong(codigo), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro/buscar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String buscar(@RequestBody String str_json) {
		System.out.println("WS_TemplatesDeDescripcion -> buscar("
				+ str_json + ")");

		TemplateDeDescripcionPublico_VO template = new Gson().fromJson(
				str_json, TemplateDeDescripcionPublico_VO.class);

		return admin_TemplatesDeDescripcionPublicos.buscar(template,
				getUsuarioAccion());
	}
	
	@RequestMapping(value = "/publicos/seguro/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminarPublico(@RequestBody String jsonPrivado) {
		System.out.println("WS_TemplatesDeDescripcion -> eliminar(" + jsonPrivado + ")");

		TemplateDeDescripcionPublico_VO template = new Gson().fromJson(
				jsonPrivado, TemplateDeDescripcionPublico_VO.class);

		return admin_TemplatesDeDescripcionPublicos.eliminar(template, getUsuarioAccion());
	}
	/****************************************************************************************/
	/****************************************************************************************/

	class R_buscarPorMatricula {

		Integer pagina = 1;
		Integer cantidad = 10;
		Integer nroMatricula;

		public R_buscarPorMatricula() {
		}

		/**
		 * @return the pagina
		 */
		public Integer getPagina() {
			return pagina;
		}

		/**
		 * @param pagina
		 *            the pagina to set
		 */
		public void setPagina(Integer pagina) {
			this.pagina = pagina;
		}

		/**
		 * @return the cantidad
		 */
		public Integer getCantidad() {
			return cantidad;
		}

		/**
		 * @param cantidad
		 *            the cantidad to set
		 */
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		/**
		 * @return the nroMatricula
		 */
		public Integer getNroMatricula() {
			return nroMatricula;
		}

		/**
		 * @param nroMatricula
		 *            the nroMatricula to set
		 */
		public void setNroMatricula(Integer nroMatricula) {
			this.nroMatricula = nroMatricula;
		}

	}

	class R_buscarPorEspecialidad {

		Integer pagina;
		Integer cantidad;
		Integer codigoEspe;

		public R_buscarPorEspecialidad() {
		}

		/**
		 * @return the pagina
		 */
		public Integer getPagina() {
			return pagina;
		}

		/**
		 * @param pagina
		 *            the pagina to set
		 */
		public void setPagina(Integer pagina) {
			this.pagina = pagina;
		}

		/**
		 * @return the cantidad
		 */
		public Integer getCantidad() {
			return cantidad;
		}

		/**
		 * @param cantidad
		 *            the cantidad to set
		 */
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		/**
		 * @return the codigoEspe
		 */
		public Integer getCodigoEspe() {
			return codigoEspe;
		}

		/**
		 * @param codigoEspe
		 *            the codigoEspe to set
		 */
		public void setCodigoEspe(Integer codigoEspe) {
			this.codigoEspe = codigoEspe;
		}
	}

}