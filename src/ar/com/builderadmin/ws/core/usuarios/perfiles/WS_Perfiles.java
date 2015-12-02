package ar.com.builderadmin.ws.core.usuarios.perfiles;

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
import ar.com.builderadmin.controllers.core.usuarios.perfiles.Admin_Perfil;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/core/usuarios/perfiles/seguro", produces = "application/json;charset=utf-8")
public class WS_Perfiles extends WS_Abstracto {

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
	/*	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_Perfiles -> ping";
	}*/

	@Autowired
	private Admin_Perfil admin_Perfiles;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Perfiles.java -> crear(" + jsonPrivado + ")");

		Perfil_VO rol =getGson().fromJson(jsonPrivado, Perfil_VO.class);

		return admin_Perfiles.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Perfiles.java -> modificar(" + jsonPrivado + ")");

		Perfil_VO rol = getGson().fromJson(jsonPrivado, Perfil_VO.class);

		return admin_Perfiles.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Perfiles.java -> eliminar(" + jsonPrivado + ")");

		Perfil_VO sucu = getGson().fromJson(jsonPrivado, Perfil_VO.class);

		return admin_Perfiles.eliminar(sucu, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_Perfiles.java -> buscar(" + nombre + ")");
		
		Perfil_VO perfil = new Perfil_VO();
			
		return admin_Perfiles.buscar(perfil, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscarPorServicio/{idServicio}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscarPorRol(@PathVariable String idServicio) {
		System.out.println("WS_Perfiles.java -> buscarPorServicio(" + idServicio + ")");
		
		Servicio srv = new Servicio();
		if(idServicio!=null && !idServicio.equals("null")&& !idServicio.equals("undefined")){
			srv.setId(Long.parseLong(idServicio));
		}
		return admin_Perfiles.getPerfilPorServicio(srv);
		
	}

	/**
	 * Dado el nombre de usuario de un usuario, se devuelven todos los perfiles 
	 * 
	 * @param usuario
	 * @return JSON_Response de lista de perfiles
	 */
	@RequestMapping(value = "/buscarMedicosDelServicioDelUsuario/{usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscarMedicosDelServicioDelUsuario(@PathVariable String usuario) {
		System.out.println("WS_Perfiles -> buscarMedicosDelServicioDelUsuario(" + usuario + ")");
			
		return admin_Perfiles.buscarMedicosDelServicioDelUsuario(usuario);

	}
	

/*	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Perfiles.java -> listAll()");

		return admin_Perfiles.buscar(new Perfil_VO(), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_Perfiles.java -> findById(" + codigo + ")");

		return admin_Perfiles.findById(Long.parseLong(codigo), getUsuarioAccion());
	}
*/
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
	 * @return the Admin_Perfil
	 */
	public Admin_Perfil getAdmin_Perfil() {
		return admin_Perfiles;
	}

	/**
	 * @param Admin_Perfil the Admin_Perfil to set
	 */
	public void setAdmin_Perfils(Admin_Perfil Admin_Perfil) {
		this.admin_Perfiles = Admin_Perfil;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}