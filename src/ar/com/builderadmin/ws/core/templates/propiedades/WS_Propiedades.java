package ar.com.builderadmin.ws.core.templates.propiedades;

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
import ar.com.builderadmin.controllers.core.templates.propiedades.Admin_Propiedades;
import ar.com.builderadmin.vo.core.templates.Propiedad_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/core/templates/propiedades/seguro", produces = "application/json;charset=utf-8")
public class WS_Propiedades extends WS_Abstracto {

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
		return "WS_Propiedades -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Propiedades admin_Propiedades;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Propiedades -> crear(" + jsonPrivado + ")");

		Propiedad_VO rol =getGson().fromJson(jsonPrivado, Propiedad_VO.class);

		return admin_Propiedades.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Propiedades -> modificar(" + jsonPrivado + ")");

		Propiedad_VO rol = getGson().fromJson(jsonPrivado, Propiedad_VO.class);

		return admin_Propiedades.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Propiedades -> eliminar(" + jsonPrivado + ")");

		Propiedad_VO area = getGson().fromJson(jsonPrivado, Propiedad_VO.class);

		return admin_Propiedades.eliminar(area, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Propiedades -> buscar(" + nombre + ")");
		
		Propiedad_VO servicio = new Propiedad_VO();
		servicio.setNombre(nombre);
		
		return admin_Propiedades.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Propiedades -> listAll()");
		
		return admin_Propiedades.buscar(new Propiedad_VO(), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Propiedades -> findById(" + codigo + ")");

		Propiedad_VO prop = new Propiedad_VO();

		return admin_Propiedades.buscar(prop, getUsuarioAccion());
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
	 * @return the admin_Propiedades
	 */
	public Admin_Propiedades getAdmin_Propiedades() {
		return admin_Propiedades;
	}

	/**
	 * @param admin_Propiedades the admin_Propiedades to set
	 */
	public void setAdmin_Propiedades(Admin_Propiedades admin_Propiedades) {
		this.admin_Propiedades = admin_Propiedades;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}