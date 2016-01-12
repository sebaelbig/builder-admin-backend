package ar.com.builderadmin.ws.planificacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.controllers.planificacion.Admin_Planificacion;
import ar.com.builderadmin.vo.planificacion.Planificacion_VO;

import com.google.gson.Gson;


@RestController
@RequestMapping(value = "/planificacion", produces = "application/json;charset=utf-8")
public class WS_Planificacion {
	
	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_planificacion -> ping";
	}
	
	@Autowired
	private Admin_Planificacion admin_planificacion;
	

	
	@RequestMapping(value = "/guardar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String crear(@RequestBody String jsonPrivado) {
		System.out.println("WS_planificacion -> crear(" + jsonPrivado + ")");
		
		Planificacion_VO udm =getGson().fromJson(jsonPrivado, Planificacion_VO.class);
		
		return this.getAdmin_planificacion().guardarPlanificacion(udm, "carlalu");
	}

	@RequestMapping(value = "/listarPlanificaciones", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarPlanificaciones() {
		System.out.println("WS_planificacion -> listarDeUnidadesDeMedida()");
		String respuesta=this.getAdmin_planificacion().listarPlanificaciones();
		System.out.println("WS_planificacion -> listarDeUnidadesDeMedida() responde:"+respuesta);
		return respuesta;
	}

	@RequestMapping(value = "/modificar", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String modificar(@RequestBody String jsonPrivado) {
		System.out.println("WS_planificacion -> modificar(" + jsonPrivado + ")");
		
		Planificacion_VO udm = getGson().fromJson(jsonPrivado, Planificacion_VO.class);
		
		return this.getAdmin_planificacion().modificar(udm,"carlalu");
	}
	
	@RequestMapping(value = "/eliminar", produces = "application/json;charset=utf-8", method = RequestMethod.DELETE)
	public String eliminar(@RequestBody String jsonPrivado) {
		System.out.println("WS_planificacion -> eliminar(" + jsonPrivado + ")");

		Planificacion_VO udm= getGson().fromJson(jsonPrivado, Planificacion_VO.class);

		return this.getAdmin_planificacion().eliminar(udm, "carlalu");
	}
	
	private Gson getGson(){
		return new Gson();
	}

	public Admin_Planificacion getAdmin_planificacion() {
		return admin_planificacion;
	}

	public void setAdmin_planificacion(Admin_Planificacion admin_planificacion) {
		this.admin_planificacion = admin_planificacion;
	}
	

}
