package ar.org.hospitalespanol.ws.core.usuarios.perfiles;

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
import ar.org.hospitalespanol.controllers.core.usuarios.perfiles.Admin_TipoDePerfil;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.TipoDePerfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/core/usuarios/perfiles/tipoDePerfiles/seguro", produces = "application/json;charset=utf-8")
public class WS_TipoDePerfiles extends WS_Abstracto {

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
		return "WS_TipoDePerfiles -> ping";
	}

	/****************************************************************************************/
	/* Templates Publicos */
	/****************************************************************************************/
	@Autowired
	private Admin_TipoDePerfil admin_TipoDePerfiles;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoDePerfiles -> crear(" + jsonPrivado + ")");

		TipoDePerfil_VO rol =getGson().fromJson(jsonPrivado, TipoDePerfil_VO.class);

		return admin_TipoDePerfiles.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoDePerfiles -> modificar(" + jsonPrivado + ")");

		TipoDePerfil_VO rol = getGson().fromJson(jsonPrivado, TipoDePerfil_VO.class);

		return admin_TipoDePerfiles.modificar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoDePerfiles -> eliminar(" + jsonPrivado + ")");

		TipoDePerfil_VO sucu = getGson().fromJson(jsonPrivado, TipoDePerfil_VO.class);

		return admin_TipoDePerfiles.eliminar(sucu, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_TipoDePerfiles -> buscar(" + nombre + ")");
		
		TipoDePerfil_VO servicio = new TipoDePerfil_VO();
		servicio.setNombre(nombre);
		
		return admin_TipoDePerfiles.buscar(servicio, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscarPorRol/{idTipoRol}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscarPorRol(@PathVariable String idTipoRol) {
		System.out.println("WS_TipoDePerfiles -> buscarPorRol(" + idTipoRol + ")");
		
		TipoDePerfil_VO tp = new TipoDePerfil_VO();
		tp.setTipoRol(new TipoDeRol_VO());
		tp.getTipoRol().setId(Long.parseLong(idTipoRol));
		
		return admin_TipoDePerfiles.buscar(tp, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_TipoDePerfiles -> listAll()");
		
		return admin_TipoDePerfiles.buscar(new TipoDePerfil_VO(), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_TipoDePerfiles -> findById(" + codigo + ")");

		return admin_TipoDePerfiles.findById(Long.parseLong(codigo), getUsuarioAccion());
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
	 * @return the admin_TipoDePerfils
	 */
	public Admin_TipoDePerfil getAdmin_TipoDePerfils() {
		return admin_TipoDePerfiles;
	}

	/**
	 * @param admin_TipoDePerfils the admin_TipoDePerfils to set
	 */
	public void setAdmin_TipoDePerfils(Admin_TipoDePerfil admin_TipoDePerfils) {
		this.admin_TipoDePerfiles = admin_TipoDePerfils;
	}

	private Gson getGson(){
		return new Gson();
	}
	
}