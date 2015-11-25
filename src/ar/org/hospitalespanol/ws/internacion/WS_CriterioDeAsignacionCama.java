package ar.org.hospitalespanol.ws.internacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.I_WebContextHolder;
import ar.org.hospitalespanol.controllers.WebContextHolder;
import ar.org.hospitalespanol.controllers.internacion.Admin_CriterioDeAsignacionCama;
import ar.org.hospitalespanol.vo.internacion.CriterioDeAsignacionCama_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;


@RestController
@RequestMapping(value = "/criterio_de_camas", produces = "application/json;charset=utf-8")
public class WS_CriterioDeAsignacionCama  extends WS_Abstracto {
	
	@Autowired
	private I_WebContextHolder webContextHolder;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public I_WebContextHolder webContextHolder() {
		return new WebContextHolder();
	}
	
	@Autowired
	private Admin_CriterioDeAsignacionCama adminCriterio;

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_CriterioDeAsignacionCama -> ping";
	}
	
	@RequestMapping(value = "/getCriteriosDeAsignacion", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String getCriteriosDeAsignacion() {		
		System.out.println("WS_CriterioDeAsignacionCama -> getCriteriosDeAsignacion()");
	
		String listado = getAdminCriterio().getListadoCriteriosDeAsignacion();
		
		return listado;
	}
	
	@RequestMapping(value = "/guardarCriterioDeAsignacion", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String guardarCriteriosDeAsignacion(@RequestBody String criterio) {		
		System.out.println("WS_CriterioDeAsignacionCama -> guardarCriteriosDeAsignacion("+criterio+")");
	
		CriterioDeAsignacionCama_VO resp= getGson().fromJson(criterio,
				CriterioDeAsignacionCama_VO.class);
		
		return getAdminCriterio().guardarCriterio(resp);
	}
	
	@RequestMapping(value = "/modificarCriterioDeAsignacion", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String modificarCriterioDeAsignacion(@RequestBody String criterio) {		
		System.out.println("WS_CriterioDeAsignacionCama -> modificarCriteriosDeAsignacion("+criterio+")");
	
		CriterioDeAsignacionCama_VO modif= getGson().fromJson(criterio,CriterioDeAsignacionCama_VO.class);
		
		return getAdminCriterio().modificarCriterio(modif);
	}
	
	@RequestMapping(value = "/eliminarCriterioDeAsignacion", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String eliminarCriterioDeAsignacion(@RequestBody String criterio) {		
		System.out.println("WS_CriterioDeAsignacionCama -> eliminarCriteriosDeAsignacion("+criterio+")");
	
		CriterioDeAsignacionCama_VO obj= getGson().fromJson(criterio,CriterioDeAsignacionCama_VO.class);
		
		return getAdminCriterio().eliminarCriterio(obj);
	}
	
	
	/**Geters y seters**/	
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}


	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}


	public Admin_CriterioDeAsignacionCama getAdminCriterio() {
		return adminCriterio;
	}


	public void setAdminCriterio(Admin_CriterioDeAsignacionCama adminCriterio) {
		this.adminCriterio = adminCriterio;
	}
	
	private Gson getGson() {
		return new Gson();
	}
}
