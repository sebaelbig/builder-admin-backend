package ar.org.hospitalespanol.ws.core.usuarios.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.core.usuarios.roles.Admin_RolesHE;
import ar.org.hospitalespanol.vo.core.usuarios.roles.RolHESimpleAdapter;
import ar.org.hospitalespanol.vo.core.usuarios.roles.RolHE_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController  
@RequestMapping(value = "/core/usuarios/roles_he/seguro", produces = "application/json;charset=utf-8")
public class WS_RolHE extends WS_Abstracto{

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_RolHE -> ping";
	}

	/****************************************************************************************/
	/* Roles */
	/****************************************************************************************/
	@Autowired
	private Admin_RolesHE admin_Rol;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_RolHE -> crear(" + jsonPrivado + ")");

		RolHE_VO rol =getGson().fromJson(jsonPrivado, RolHE_VO.class);

		return admin_Rol.crear(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_RolHE -> modificar(" + jsonPrivado + ")");

		RolHE_VO rol = getGson().fromJson(jsonPrivado, RolHE_VO.class);

		return admin_Rol.modificar(rol, getUsuarioAccion());
	}

	@RequestMapping(value = "/buscar/{nombre}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String nombre) {
		System.out.println("WS_RolHE -> buscar(" + nombre + ")");
		
		RolHE_VO rol = new RolHE_VO();
		rol.setRol(nombre);
		
		return admin_Rol.buscar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_RolHE -> listAll()");
		
		RolHE_VO rol = new RolHE_VO();
		
		return admin_Rol.buscar(rol, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{id_rol}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String id_rol) {
		System.out.println("WS_RolHE -> findById(" + id_rol + ")");

		RolHE_VO rol = new RolHE_VO();
		rol.setId_rol(id_rol);

		return admin_Rol.buscar(rol, getUsuarioAccion());
	}
	
	private Gson getGson(){
		return new GsonBuilder()
		.registerTypeAdapter(RolHE_VO.class, new RolHESimpleAdapter())
		.create();
	}
	

}