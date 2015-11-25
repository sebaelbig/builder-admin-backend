package ar.org.hospitalespanol.ws.core.areas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.I_WebContextHolder;
import ar.org.hospitalespanol.controllers.WebContextHolder;
import ar.org.hospitalespanol.controllers.core.areas.servicios.Admin_Servicios;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/core/areas/servicios/seguro", produces = "application/json;charset=utf-8")
public class WS_Servicios extends WS_Abstracto {

	@Autowired
	private I_WebContextHolder webContextHolder;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public I_WebContextHolder webContextHolder() {
		return new WebContextHolder();
	}

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_Servicios -> ping";
	}

	/****************************************************************************************/
	/* Templates Publicos */
	/****************************************************************************************/
	@Autowired
	private Admin_Servicios admin_Servicios;
	
	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Servicios -> crear(" + jsonPrivado + ")");
		
		Servicio_VO srv =getGson().fromJson(jsonPrivado, Servicio_VO.class);
		
		return admin_Servicios.crear(srv, getUsuarioAccion());
	}

	@RequestMapping(value = "/listarDeUsuario", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarDeUsuario(@RequestBody String usuario) {
		System.out.println("WS_Servicios -> listarDeUsuario(" + usuario + ")");

		return admin_Servicios.listarDeUsuario( getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Servicios -> modificar(" + jsonPrivado + ")");
		
		Servicio_VO srv = getGson().fromJson(jsonPrivado, Servicio_VO.class);
		
		return admin_Servicios.modificar(srv, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Servicios -> eliminar(" + jsonPrivado + ")");

		Servicio_VO srv = getGson().fromJson(jsonPrivado, Servicio_VO.class);

		return admin_Servicios.eliminar(srv, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Servicios -> buscar(" + nombre + ")");
		
		Servicio_VO servicio = new Servicio_VO();
		servicio.setNombre(nombre);
		
		return admin_Servicios.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Servicios -> listAll()");
		
		return admin_Servicios.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAllFromHE", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAllFromHE() {
		System.out.println("WS_Servicios -> listAllFromHE()");
		
		return admin_Servicios.listarTodosLosDelHE(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Servicios -> findById(" + codigo + ")");

		Servicio_VO servicio = new Servicio_VO();
		servicio.setCodigo(codigo);

		return admin_Servicios.buscar(servicio, getUsuarioAccion());
	}

	
	
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

	/**
	 * @return the webContextHolder
	 */
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	/**
	 * @param webContextHolder
	 *            the webContextHolder to set
	 */
	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	/**
	 * @return the admin_Servicios
	 */
	public Admin_Servicios getAdmin_Servicios() {
		return admin_Servicios;
	}

	/**
	 * @param admin_Servicios the admin_Servicios to set
	 */
	public void setAdmin_Servicios(Admin_Servicios admin_Servicios) {
		this.admin_Servicios = admin_Servicios;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}