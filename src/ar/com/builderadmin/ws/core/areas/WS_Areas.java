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
import ar.com.builderadmin.controllers.core.areas.Admin_Areas;
import ar.com.builderadmin.vo.core.areas.Area_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/core/areas/seguro", produces = "application/json;charset=utf-8")
public class WS_Areas extends WS_Abstracto {

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
		return "WS_Areas -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Areas admin_Areas;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Areas -> crear(" + jsonPrivado + ")");

		Area_VO rol =getGson().fromJson(jsonPrivado, Area_VO.class);

		return admin_Areas.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Areas -> modificar(" + jsonPrivado + ")");

		Area_VO rol = getGson().fromJson(jsonPrivado, Area_VO.class);

		return admin_Areas.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Areaes -> eliminar(" + jsonPrivado + ")");

		Area_VO area = getGson().fromJson(jsonPrivado, Area_VO.class);

		return admin_Areas.eliminar(area, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Areas -> buscar(" + nombre + ")");
		
		Area_VO servicio = new Area_VO();
		servicio.setNombre(nombre);
		
		return admin_Areas.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Areas -> listAll()");
		
		return admin_Areas.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Areas -> findById(" + codigo + ")");

		return admin_Areas.findById(Long.parseLong(codigo),  getUsuarioAccion());
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
	 * @return the admin_Areas
	 */
	public Admin_Areas getAdmin_Areas() {
		return admin_Areas;
	}

	/**
	 * @param admin_Areas the admin_Areas to set
	 */
	public void setAdmin_Areas(Admin_Areas admin_Areas) {
		this.admin_Areas = admin_Areas;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}