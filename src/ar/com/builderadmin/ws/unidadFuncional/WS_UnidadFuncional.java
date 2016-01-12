package ar.com.builderadmin.ws.unidadFuncional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.controllers.unidadFuncional.Admin_UnidadFuncional;
import ar.com.builderadmin.vo.unidadFuncional.UnidadFuncional_VO;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/unidad_funcional", produces = "application/json;charset=utf-8")
public class WS_UnidadFuncional {
	
	@Autowired
	private Admin_UnidadFuncional admin_unidadFuncional;
	
	public Admin_UnidadFuncional getAdmin_unidadFuncional() {
		return admin_unidadFuncional;
	}

	public void setAdmin_unidadFuncional(Admin_UnidadFuncional admin_unidadFuncional) {
		this.admin_unidadFuncional = admin_unidadFuncional;
	}

	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_UnidadFuncional -> ping";
	}
	
	@RequestMapping(value = "/guardar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_UnidadFuncional -> crear(" + jsonPrivado + ")");
		
		UnidadFuncional_VO uf =getGson().fromJson(jsonPrivado, UnidadFuncional_VO.class);
			
		return admin_unidadFuncional.guardarUnidadFuncional(uf, "carlalu");
	}
	
	@RequestMapping(value = "/listarUnidadesFuncionales", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarDeUnidadesDeMedida() {
		System.out.println("WS_UnidadFuncional -> listarUnidadesFuncioanes()");
		String respuesta=admin_unidadFuncional.listarUnidadFuncionales();
		System.out.println("WS_UnidadFuncional -> listarUnidadesFuncioanes() responde:"+respuesta);
		return respuesta;
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_UnidadFuncional -> modificar(" + jsonPrivado + ")");
		
		UnidadFuncional_VO des = getGson().fromJson(jsonPrivado, UnidadFuncional_VO.class);
		
		return admin_unidadFuncional.modificar(des,"carlalu");
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_UnidadFuncional -> eliminar(" + jsonPrivado + ")");

		UnidadFuncional_VO uf= getGson().fromJson(jsonPrivado, UnidadFuncional_VO.class);

		return admin_unidadFuncional.eliminar(uf, "carlalu");
	}
	
	private Gson getGson(){
		return new Gson();
	}


}
