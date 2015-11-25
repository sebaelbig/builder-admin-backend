package ar.org.hospitalespanol.ws.core;

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
import ar.org.hospitalespanol.controllers.core.Admin_Parametros;
import ar.org.hospitalespanol.vo.core.Parametro_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/core/parametros/seguro", produces = "application/json;charset=utf-8")
public class WS_Parametros extends WS_Abstracto {

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
		return "WS_Parametros -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Parametros admin_Parametros;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Parametros -> crear(" + jsonPrivado + ")");

		Parametro_VO rol =getGson().fromJson(jsonPrivado, Parametro_VO.class);

		return admin_Parametros.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Parametros -> modificar(" + jsonPrivado + ")");

		Parametro_VO rol = getGson().fromJson(jsonPrivado, Parametro_VO.class);

		return admin_Parametros.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Parametros -> eliminar(" + jsonPrivado + ")");

		Parametro_VO area = getGson().fromJson(jsonPrivado, Parametro_VO.class);

		return admin_Parametros.eliminar(area, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Parametros -> buscar(" + nombre + ")");
		
		Parametro_VO servicio = new Parametro_VO();
		servicio.setNombre(nombre);
		
		return admin_Parametros.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listarTipos", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listarTipos() {
		System.out.println("WS_Parametros -> listarTipos()");
		
		return admin_Parametros.listarTipos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Parametros -> listAll()");
		
		return admin_Parametros.buscar(new Parametro_VO(), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Parametros -> findById(" + codigo + ")");

		Parametro_VO prop = new Parametro_VO();
		prop.setNombre(codigo);
		
		return admin_Parametros.buscar(prop, getUsuarioAccion());
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
	 * @return the admin_Parametros
	 */
	public Admin_Parametros getAdmin_Parametros() {
		return admin_Parametros;
	}

	/**
	 * @param admin_Parametros the admin_Parametros to set
	 */
	public void setAdmin_Parametros(Admin_Parametros admin_Parametros) {
		this.admin_Parametros = admin_Parametros;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}