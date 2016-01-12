package ar.com.builderadmin.ws.designacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.controllers.designacion.Admin_Designacion;
import ar.com.builderadmin.vo.designacion.Designacion_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/designacion", produces = "application/json;charset=utf-8")
public class WS_Designacion extends WS_Abstracto {
	
	
	@Autowired
	private Admin_Designacion admin_designacion;
	
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_Designacion -> ping";
	}
	
	@RequestMapping(value = "/guardar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Designacion -> crear(" + jsonPrivado + ")");
		
		Designacion_VO des =getGson().fromJson(jsonPrivado, Designacion_VO.class);
			
		return admin_designacion.guardarDesignacion(des, "carlalu");
	}

	public Admin_Designacion getAdmin_designacion() {
		return admin_designacion;
	}

	public void setAdmin_designacion(Admin_Designacion admin_designacion) {
		this.admin_designacion = admin_designacion;
	}

	@RequestMapping(value = "/listarDesignaciones", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarDeUnidadesDeMedida() {
		System.out.println("WS_Designacion -> listarDeUnidadesDeMedida()");
		String respuesta=admin_designacion.listarDesignaciones();
		System.out.println("WS_Designacion -> listarDeUnidadesDeMedida() responde:"+respuesta);
		return respuesta;
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Designacion -> modificar(" + jsonPrivado + ")");
		
		Designacion_VO des = getGson().fromJson(jsonPrivado, Designacion_VO.class);
		
		return admin_designacion.modificar(des,"carlalu");
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Designacion -> eliminar(" + jsonPrivado + ")");

		Designacion_VO des= getGson().fromJson(jsonPrivado, Designacion_VO.class);

		return admin_designacion.eliminar(des, "carlalu");
	}
	
	private Gson getGson(){
		return new Gson();
	}

}
