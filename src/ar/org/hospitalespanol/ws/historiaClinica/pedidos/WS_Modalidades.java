package ar.org.hospitalespanol.ws.historiaClinica.pedidos;

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
import ar.org.hospitalespanol.controllers.historiaClinica.pedidos.Admin_Modalidades;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Modalidad_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/historiaClinica/modalidades/seguro", produces = "application/json;charset=utf-8")
public class WS_Modalidades extends WS_Abstracto {

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
		return "WS_Modalidades -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Modalidades admin_Modalidades;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Modalidades -> crear(" + jsonPrivado + ")");

		Modalidad_VO modalidad_vo =getGson().fromJson(jsonPrivado, Modalidad_VO.class);

		return admin_Modalidades.crear(modalidad_vo, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Modalidades -> modificar(" + jsonPrivado + ")");

		Modalidad_VO modalidad_vo = getGson().fromJson(jsonPrivado, Modalidad_VO.class);

		return admin_Modalidades.modificar(modalidad_vo, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Modalidades -> eliminar(" + jsonPrivado + ")");

		Modalidad_VO modalidad_vo = getGson().fromJson(jsonPrivado, Modalidad_VO.class);

		return admin_Modalidades.eliminar(modalidad_vo, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String codigo) {
		System.out.println("WS_Modalidades -> buscar(" + codigo+ ")");
		
		Modalidad_VO modalidad_vo = new Modalidad_VO();
		modalidad_vo.setCodigo(codigo);
		
		return admin_Modalidades.buscar(modalidad_vo, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Modalidades -> listAll()");
		
		return admin_Modalidades.buscar(new Modalidad_VO(), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{id}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String id) {
		System.out.println("WS_Modalidades -> findById(" + id + ")");

		Modalidad_VO modalidad_vo = new Modalidad_VO();
		modalidad_vo.setId(Long.valueOf(id));
		
		return admin_Modalidades.buscar(modalidad_vo, getUsuarioAccion());
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
	 * @return the admin_Modalidades
	 */
	public Admin_Modalidades getAdmin_Modalidades() {
		return admin_Modalidades;
	}

	/**
	 * @param admin_Modalidades the admin_Modalidades to set
	 */
	public void setAdmin_Modalidades(Admin_Modalidades admin_Modalidades) {
		this.admin_Modalidades = admin_Modalidades;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}