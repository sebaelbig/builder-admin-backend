package ar.com.builderadmin.ws.core.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.core.usuarios.Admin_TipoIDHE;
import ar.com.builderadmin.vo.core.usuarios.TipoIDHE_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController  
@RequestMapping(value = "/core/usuarios/tiposDeID_he/seguro", produces = "application/json;charset=utf-8")
public class WS_TipoIDHE extends WS_Abstracto{

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_TipoIDHE -> ping";
	}

	/****************************************************************************************/
	/* TipoIDes */
	/****************************************************************************************/
	@Autowired
	private Admin_TipoIDHE admin_TipoID;

	@RequestMapping(value = "/crear", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoIDHE -> crear(" + jsonPrivado + ")");
		
		TipoIDHE_VO tipo =new Gson().fromJson(jsonPrivado, TipoIDHE_VO.class);
		tipo.setLogin(getUsuarioAccion());
		
		return admin_TipoID.crear(tipo, getUsuarioAccion());
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoIDHE -> modificar(" + jsonPrivado + ")");

		TipoIDHE_VO tipo =new Gson().fromJson(jsonPrivado, TipoIDHE_VO.class);
		tipo.setLogin(getUsuarioAccion());
		
		return admin_TipoID.modificar(tipo, getUsuarioAccion());
	}

	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoIDHE -> eliminar(" + jsonPrivado + ")");

		TipoIDHE_VO tipo = new Gson().fromJson(jsonPrivado, TipoIDHE_VO.class);
		tipo.setLogin(getUsuarioAccion());
		
		return admin_TipoID.eliminar(tipo, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/buscar/{tipoID}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscar(@PathVariable String tipoID) {
		System.out.println("WS_TipoIDHE -> buscar(" + tipoID + ")");
		
		TipoIDHE_VO tipo = new TipoIDHE_VO();
		tipo.setTipoID(tipoID);
		
		return admin_TipoID.buscar(tipo, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_TipoIDHE -> listAll()");
		
		return admin_TipoID.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/findById/{codigo}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String codigo) {
		System.out.println("WS_TipoIDHE -> findById(" + codigo + ")");

		return admin_TipoID.findById(Long.parseLong(codigo), getUsuarioAccion());
	}

}