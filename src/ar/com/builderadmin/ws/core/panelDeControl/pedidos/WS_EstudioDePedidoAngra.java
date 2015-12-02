package ar.com.builderadmin.ws.core.panelDeControl.pedidos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.I_WebContextHolder;
import ar.com.builderadmin.controllers.WebContextHolder;
import ar.com.builderadmin.controllers.core.usuarios.Admin_Usuarios;
import ar.com.builderadmin.controllers.panelDeControl.pedidos.Admin_EstudioDePedidosAngra;
import ar.com.builderadmin.model.historiaClinica.pedidos.PedidoFiltrado;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/panelDeControl/pedidos", produces = "application/json;charset=utf-8")
public class WS_EstudioDePedidoAngra extends WS_Abstracto {

	private final Logger log = LoggerFactory.getLogger(WS_EstudioDePedidoAngra.class);
	
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
		return "WS_EstudioDePedidoAngra -> ping";
	}
	
	/**
	
	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_EstudioDePedidosAngra admin_EstudioDePedidosAngra;
	
	@Autowired
	private Admin_Usuarios admin_Usuarios;

//	@RequestMapping(value = "/seguro/buscar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
//	public String buscar(@RequestBody String jsonPrivado) {		
//		System.out.println("WS_Pedidos -> buscar(" + jsonPrivado + ")");
//	
//		Pedido_VO pedido = getGson().fromJson(jsonPrivado, Pedido_VO.class);
//	
//		return admin_EstudioDePedidosAngra.buscar(pedido, getUsuarioAccion());
//	}
	
	/* traigo tipo+nroDni del pacienteLogeado */
	@RequestMapping(value = "/seguro/getDatosDePacienteWeb", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getDatosPacienteWeb(@RequestBody String jsonUsuario) {		
		System.out.println("WS_Pedidos -> getDatosDePacienteWeb(" + jsonUsuario + ")");
			
		return admin_Usuarios.getDatosDePacienteWeb(jsonUsuario);
	}
	
	@RequestMapping(value = "/seguro/listarPedidosPorFiltro", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarPedidosPorFiltro(@RequestBody String jsonPedidoFiltrado) {		
		System.out.println("WS_EstudioDePedidoAngra -> listarPedidosPorFiltro(" + jsonPedidoFiltrado + ")");
		
		PedidoFiltrado pedidoFiltrado = getGson().fromJson(jsonPedidoFiltrado, PedidoFiltrado.class);
		
		return admin_EstudioDePedidosAngra.listarPedidosPorFiltro(pedidoFiltrado, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Pedidos -> listAll()");
		
		return admin_EstudioDePedidosAngra.listarTodos(getUsuarioAccion());
	}
	
//	@RequestMapping(value = "/seguro/findByNro/{nro}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
//	public String findByNro(@PathVariable String nro) {
//		System.out.println("WS_Pedidos -> findByNro(" + nro + ")");
//		
//		
//		return admin_EstudioDePedidosAngra.findByNro(nro,  getUsuarioAccion());
//	}
//	
//	@RequestMapping(value = "/seguro/findById/{id}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
//	public String findById(@PathVariable String id) {
//		System.out.println("WS_Pedidos -> findById(" + id + ")");
//		
//		return admin_EstudioDePedidosAngra.findById(Long.parseLong(id),  getUsuarioAccion());
//	}
	
//	@RequestMapping(value = "/seguro/findByIdByEstudio/{id}/{idEstudio}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
//	public String findByIdByEstudio(@PathVariable String id, @PathVariable String idEstudio) {
//		System.out.println("WS_Pedidos -> findByIdByEstudio(" + id + ", "+idEstudio+")");
//		
//		return admin_EstudioDePedidosAngra.findByIdByEstudio(Long.parseLong(id), Long.parseLong(idEstudio), getUsuarioAccion());
//	}
	
//	@RequestMapping(value = "/seguro/findByIdBloqueante/{id}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
//	public String findByIdBloqueante(@PathVariable String id) {
//		System.out.println("WS_Pedidos -> findByIdBloqueante(" + id + ")");
//		
//		return admin_EstudioDePedidosAngra.findByIdBloqueante(Long.parseLong(id),  getUsuarioAccion());
//	}
	
	@RequestMapping(value = "/seguro/findByAccessionNumber/{an}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByAccessionNumber(@PathVariable String an) {
		System.out.println("WS_Pedidos -> findByAccessionNumber(" + an + ")");
		
		return admin_EstudioDePedidosAngra.findByAccessionNumber(an,  getUsuarioAccion());
	}
	
	
	
	/**********************************************************************************/
	
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
	 * @return the admin_EstudioDePedidosAngra
	 */
	public Admin_EstudioDePedidosAngra getAdmin_Pedidos() {
		return admin_EstudioDePedidosAngra;
	}

	/**
	 * @param admin_EstudioDePedidosAngra the admin_EstudioDePedidosAngra to set
	 */
	public void setAdmin_Pedidos(Admin_EstudioDePedidosAngra admin_EstudioDePedidosAngra) {
		this.admin_EstudioDePedidosAngra = admin_EstudioDePedidosAngra;
	}
	
	private Gson getGson(){
		return new Gson();
	}

	class Estado{
		private String nombre;

		public Estado(String n){this.nombre=n;}

		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
	}
}