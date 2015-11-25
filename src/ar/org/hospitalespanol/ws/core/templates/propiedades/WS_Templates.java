package ar.org.hospitalespanol.ws.core.templates.propiedades;

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
import ar.org.hospitalespanol.controllers.core.templates.Admin_Templates;
import ar.org.hospitalespanol.vo.core.templates.Template_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/core/templates/seguro", produces = "application/json;charset=utf-8")
public class WS_Templates extends WS_Abstracto {

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
		return "WS_Templates -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Templates admin_Templates;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Templates -> crear(" + jsonPrivado + ")");

		Template_VO rol =getGson().fromJson(jsonPrivado, Template_VO.class);

		return admin_Templates.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Templates -> modificar(" + jsonPrivado + ")");

		Template_VO rol = getGson().fromJson(jsonPrivado, Template_VO.class);

		return admin_Templates.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Templates -> eliminar(" + jsonPrivado + ")");

		Template_VO area = getGson().fromJson(jsonPrivado, Template_VO.class);

		return admin_Templates.eliminar(area, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Templates -> buscar(" + nombre + ")");
		
		Template_VO servicio = new Template_VO();
		servicio.setNombreServicio(nombre);
		
		return admin_Templates.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Templates -> listAll()");
		
		return admin_Templates.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Templates -> findById(" + codigo + ")");
		
		return admin_Templates.findById(Long.parseLong(codigo), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findByServicio/{idSrv}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByServicio(@PathVariable String idSrv) {
		System.out.println("WS_Templates -> findByServicio(" + idSrv + ")");

		return admin_Templates.findByServicio(Long.parseLong(idSrv), getUsuarioAccion());
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
	 * @return the admin_Templates
	 */
	public Admin_Templates getAdmin_Templates() {
		return admin_Templates;
	}

	/**
	 * @param admin_Templates the admin_Templates to set
	 */
	public void setAdmin_Templates(Admin_Templates admin_Templates) {
		this.admin_Templates = admin_Templates;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}