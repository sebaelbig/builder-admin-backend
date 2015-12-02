package ar.com.builderadmin.ws.historiaClinica.informes;

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
import ar.com.builderadmin.controllers.historiaClinica.informes.Admin_Informes;
import ar.com.builderadmin.vo.historiaClinica.informes.Informe_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/historiaClinica/informes/seguro", produces = "application/json;charset=utf-8")
public class WS_Informes extends WS_Abstracto {

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
		return "WS_Informes -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Informes admin_Informes;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Informes -> crear(" + jsonPrivado + ")");

		Informe_VO rol =getGson().fromJson(jsonPrivado, Informe_VO.class);

		return admin_Informes.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Informes -> modificar(" + jsonPrivado + ")");

		Informe_VO rol = getGson().fromJson(jsonPrivado, Informe_VO.class);

		return admin_Informes.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Informees -> eliminar(" + jsonPrivado + ")");

		Informe_VO informe = getGson().fromJson(jsonPrivado, Informe_VO.class);

		return admin_Informes.eliminar(informe, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String jsonPrivado) {
		System.out.println("WS_Informes -> buscar(" + jsonPrivado + ")");
		
		Informe_VO informe = getGson().fromJson(jsonPrivado, Informe_VO.class);
		
		return admin_Informes.buscar(informe, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Informes -> listAll()");
		
		return admin_Informes.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Informes -> findById(" + codigo + ")");

		return admin_Informes.findById(Long.parseLong(codigo),  getUsuarioAccion());
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
	 * @return the admin_Informes
	 */
	public Admin_Informes getAdmin_Informes() {
		return admin_Informes;
	}

	/**
	 * @param admin_Informes the admin_Informes to set
	 */
	public void setAdmin_Informes(Admin_Informes admin_Informes) {
		this.admin_Informes = admin_Informes;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}