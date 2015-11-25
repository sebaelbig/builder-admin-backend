package ar.org.hospitalespanol.ws.core.usuarios.roles;

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
import ar.org.hospitalespanol.controllers.core.usuarios.roles.Admin_TipoDeRol;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/core/usuarios/roles/tipoDeRoles/seguro", produces = "application/json;charset=utf-8")
public class WS_TipoDeRoles extends WS_Abstracto {

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
		return "WS_TipoDeRoles -> ping";
	}

	/****************************************************************************************/
	@Autowired
	private Admin_TipoDeRol admin_TipoDeRoles;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoDeRoles -> crear(" + jsonPrivado + ")");

		TipoDeRol_VO rol = getGson().fromJson(jsonPrivado, TipoDeRol_VO.class);
		
		return admin_TipoDeRoles.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoDeRoles -> modificar(" + jsonPrivado + ")");

		TipoDeRol_VO rol = getGson().fromJson(jsonPrivado, TipoDeRol_VO.class);

		return admin_TipoDeRoles.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoDeRoles -> eliminar(" + jsonPrivado + ")");

		TipoDeRol_VO sucu = getGson().fromJson(jsonPrivado, TipoDeRol_VO.class);

		return admin_TipoDeRoles.eliminar(sucu, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_TipoDeRoles -> buscar(" + nombre + ")");
		
		TipoDeRol_VO servicio = new TipoDeRol_VO();
		servicio.setNombre(nombre);
		
		return admin_TipoDeRoles.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_TipoDeRoles -> listAll()");
		
		return admin_TipoDeRoles.buscar(new TipoDeRol_VO(), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_TipoDeRoles -> findById(" + codigo + ")");

		TipoDeRol_VO servicio = new TipoDeRol_VO();
		servicio.setCodigo(codigo);

		return admin_TipoDeRoles.buscar(servicio, getUsuarioAccion());
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
	 * @return the admin_TipoDeRols
	 */
	public Admin_TipoDeRol getAdmin_TipoDeRols() {
		return admin_TipoDeRoles;
	}

	/**
	 * @param admin_TipoDeRols the admin_TipoDeRols to set
	 */
	public void setAdmin_TipoDeRols(Admin_TipoDeRol admin_TipoDeRols) {
		this.admin_TipoDeRoles = admin_TipoDeRols;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}