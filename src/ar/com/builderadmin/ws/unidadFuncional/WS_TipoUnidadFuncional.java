package ar.com.builderadmin.ws.unidadFuncional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.controllers.unidadFuncional.Admin_TipoUnidadFuncional;
import ar.com.builderadmin.vo.designacion.UnidadDeMedida_VO;
import ar.com.builderadmin.vo.unidadFuncional.TipoUnidadFuncional_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/tipoUnidadFuncional", produces = "application/json;charset=utf-8")
public class WS_TipoUnidadFuncional extends WS_Abstracto{
	
	@Autowired
	private Admin_TipoUnidadFuncional admin_tipoUnidadFuncional;
	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_TipoUnidadFuncional -> ping";
	}

	
	@RequestMapping(value = "/guardar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoUnidadFuncional -> crear(" + jsonPrivado + ")");
		
		TipoUnidadFuncional_VO udm =getGson().fromJson(jsonPrivado, TipoUnidadFuncional_VO.class);
		
		return admin_tipoUnidadFuncional.guardarUnidad(udm, "carlalu");
	}

	@RequestMapping(value = "/listarTipoDeUnidades", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarTipoDeUnidades() {
		System.out.println("WS_TipoUnidadFuncional -> listarDeUnidadesDeMedida()");
		String respuesta=admin_tipoUnidadFuncional.listarTipoUnidades();
		System.out.println("WS_TipoUnidadFuncional -> listarDeUnidadesDeMedida() responde:"+respuesta);
		return respuesta;
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoUnidadFuncional -> modificar(" + jsonPrivado + ")");
		
		TipoUnidadFuncional_VO udm = getGson().fromJson(jsonPrivado, TipoUnidadFuncional_VO.class);
		
		return admin_tipoUnidadFuncional.modificar(udm,"carlalu");
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_TipoUnidadFuncional -> eliminar(" + jsonPrivado + ")");

		TipoUnidadFuncional_VO udm= getGson().fromJson(jsonPrivado, TipoUnidadFuncional_VO.class);

		return admin_tipoUnidadFuncional.eliminar(udm, "carlalu");
	}
	
	private Gson getGson(){
		return new Gson();
	}

}
