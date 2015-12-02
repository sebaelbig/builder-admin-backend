package ar.com.builderadmin.ws.core.usuarios.funciones;

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
import ar.com.builderadmin.controllers.core.usuarios.funciones.Admin_Funciones;
import ar.com.builderadmin.vo.FuncionHorus_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/core/usuarios/funciones/seguro", produces = "application/json;charset=utf-8")
public class WS_Funciones extends WS_Abstracto {

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
		return "WS_Funciones -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Funciones admin_Funciones;

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Funciones -> modificar(" + jsonPrivado + ")");

		FuncionHorus_VO fx = getGson().fromJson(jsonPrivado, FuncionHorus_VO.class);

		return admin_Funciones.modificar(fx, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Funciones -> eliminar(" + jsonPrivado + ")");

		FuncionHorus_VO area = getGson().fromJson(jsonPrivado, FuncionHorus_VO.class);

		return admin_Funciones.eliminar(area, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Funciones -> buscar(" + nombre + ")");
		
		FuncionHorus_VO fx = new FuncionHorus_VO();
		
		return admin_Funciones.buscar(fx, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Funciones -> listAll()");
		
		return admin_Funciones.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Funciones -> findById(" + codigo + ")");

		return admin_Funciones.findById(Long.parseLong(codigo), getUsuarioAccion());
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
	 * @return the admin_Funciones
	 */
	public Admin_Funciones getAdmin_Funciones() {
		return admin_Funciones;
	}

	/**
	 * @param admin_Funciones the admin_Funciones to set
	 */
	public void setAdmin_Funciones(Admin_Funciones admin_Funciones) {
		this.admin_Funciones = admin_Funciones;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}