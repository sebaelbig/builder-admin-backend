package ar.com.builderadmin.ws.core.areas;

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
import ar.com.builderadmin.controllers.core.areas.Admin_Sucursales;
import ar.com.builderadmin.vo.core.areas.Sucursal_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/core/areas/sucursales/seguro", produces = "application/json;charset=utf-8")
public class WS_Sucursales extends WS_Abstracto {

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
		return "WS_Sucursales -> ping";
	}

	/****************************************************************************************/
	/* Templates Publicos */
	/****************************************************************************************/
	@Autowired
	private Admin_Sucursales admin_Sucursales;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Sucursales -> crear(" + jsonPrivado + ")");

		Sucursal_VO rol =getGson().fromJson(jsonPrivado, Sucursal_VO.class);

		return admin_Sucursales.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Sucursales -> modificar(" + jsonPrivado + ")");

		Sucursal_VO rol = getGson().fromJson(jsonPrivado, Sucursal_VO.class);

		return admin_Sucursales.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Sucursales -> eliminar(" + jsonPrivado + ")");

		Sucursal_VO sucu = getGson().fromJson(jsonPrivado, Sucursal_VO.class);

		return admin_Sucursales.eliminar(sucu, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Sucursales -> buscar(" + nombre + ")");
		
		Sucursal_VO servicio = new Sucursal_VO();
		servicio.setNombre(nombre);
		
		return admin_Sucursales.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Sucursales -> listAll()");
		
		return admin_Sucursales.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Sucursales -> findById(" + codigo + ")");

		Sucursal_VO servicio = new Sucursal_VO();
		servicio.setCodigo(codigo);

		return admin_Sucursales.buscar(servicio, getUsuarioAccion());
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
	 * @return the admin_Sucursals
	 */
	public Admin_Sucursales getAdmin_Sucursals() {
		return admin_Sucursales;
	}

	/**
	 * @param admin_Sucursals the admin_Sucursals to set
	 */
	public void setAdmin_Sucursals(Admin_Sucursales admin_Sucursals) {
		this.admin_Sucursales = admin_Sucursals;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}