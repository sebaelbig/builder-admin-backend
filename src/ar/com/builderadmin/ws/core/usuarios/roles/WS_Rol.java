package ar.com.builderadmin.ws.core.usuarios.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.builderadmin.controllers.core.usuarios.roles.Admin_Roles;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController  
@RequestMapping(value = "/core/usuarios/roles/seguro", produces = "application/json;charset=utf-8")
public class WS_Rol extends WS_Abstracto{

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_Rol -> ping";
	}

	/****************************************************************************************/
	/* Roles */
	/****************************************************************************************/
	@Autowired
	private Admin_Roles admin_Rol;
	
	@Autowired
	private WS_RolHE ws_RolHE;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {

		//Creo el rol en Horus
		System.out.println("WS_Rol -> crear(" + jsonPrivado + ")");
		Rol_VO rol = getGson().fromJson(jsonPrivado, Rol_VO.class);

		return admin_Rol.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Rol -> modificar(" + jsonPrivado + ")");

		Rol_VO rol = getGson().fromJson(jsonPrivado, Rol_VO.class);

		return admin_Rol.modificar(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/buscar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String buscar(@RequestBody String  json_rol) {
		System.out.println("WS_Rol -> buscar(" + json_rol + ")");
		
		Rol_VO rol = this.getGson().fromJson(json_rol, Rol_VO.class);
		
		return admin_Rol.buscar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Rol -> listAll()");
		
		return admin_Rol.listarTodos( getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{id_rol}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String id_rol) {
		System.out.println("WS_Rol -> findById(" + id_rol + ")");

		return admin_Rol.findById(Long.parseLong(id_rol), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Rol -> eliminar(" + jsonPrivado + ")");

		Rol_VO rol = getGson().fromJson(jsonPrivado, Rol_VO.class);

		return admin_Rol.eliminar(rol, getUsuarioAccion());
	}
	
	private Gson getGson(){
		return new GsonBuilder()
//		.registerTypeAdapter(Rol_VO.class, new RolSimpleAdapter())
		.create();
	}
	

}