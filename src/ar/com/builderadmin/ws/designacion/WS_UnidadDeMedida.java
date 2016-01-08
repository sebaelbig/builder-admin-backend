package ar.com.builderadmin.ws.designacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.I_WebContextHolder;
import ar.com.builderadmin.controllers.designacion.Admin_UnidadDeMedida;
import ar.com.builderadmin.vo.designacion.UnidadDeMedida_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/unidadDeMedida", produces = "application/json;charset=utf-8")
public class WS_UnidadDeMedida extends WS_Abstracto {
	
	@Autowired
	private Admin_UnidadDeMedida admin_unidadMedida;
	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_UnidadDeMedida -> ping";
	}

	/****************************************************************************************/
	/* Templates Publicos */
	/****************************************************************************************/
	
	
	@RequestMapping(value = "/guardar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_UnidadDeMedida -> crear(" + jsonPrivado + ")");
		
		UnidadDeMedida_VO udm =getGson().fromJson(jsonPrivado, UnidadDeMedida_VO.class);
		
		return admin_unidadMedida.guardarUnidad(udm, "carlalu");
	}

	@RequestMapping(value = "/listarDeUnidadesDeMedida", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarDeUnidadesDeMedida() {
		System.out.println("WS_UnidadDeMedida -> listarDeUnidadesDeMedida()");
		String respuesta=admin_unidadMedida.listarUnidadDeMedida();
		System.out.println("WS_UnidadDeMedida -> listarDeUnidadesDeMedida() responde:"+respuesta);
		return respuesta;
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Servicios -> modificar(" + jsonPrivado + ")");
		
		UnidadDeMedida_VO udm = getGson().fromJson(jsonPrivado, UnidadDeMedida_VO.class);
		
		return admin_unidadMedida.modificar(udm,"carlalu");
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Servicios -> eliminar(" + jsonPrivado + ")");

		UnidadDeMedida_VO udm= getGson().fromJson(jsonPrivado, UnidadDeMedida_VO.class);

		return admin_unidadMedida.eliminar(udm, "carlalu");
	}
	
	private Gson getGson(){
		return new Gson();
	}

}
