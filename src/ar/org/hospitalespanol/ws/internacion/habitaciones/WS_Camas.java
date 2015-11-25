package ar.org.hospitalespanol.ws.internacion.habitaciones;

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
import ar.org.hospitalespanol.controllers.internacion.habitaciones.Admin_Camas;
import ar.org.hospitalespanol.vo.internacion.CriterioDeAsignacionCama_VO;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.Cama_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;


@RestController
@RequestMapping(value = "/camas", produces = "application/json;charset=utf-8")
public class WS_Camas  extends WS_Abstracto{
	
	@Autowired
	private I_WebContextHolder webContextHolder;
	
	@Autowired
	private Admin_Camas adminCama;

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
		return "WS_Camas -> ping";
	}
	
	@RequestMapping(value = "/getCamas", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String getCriteriosDeAsignacion() {		
		System.out.println("WS_Camas -> getCamas()");
	
		String listado = getAdminCama().getCamas();
		
		return listado;
	}
	
	@RequestMapping(value ="/setCriterioACama", produces = "application/json;charset=utf-8", method= RequestMethod.POST)
	public String setCriterioACama(@RequestBody String cama){
		System.out.println("WS_Camas -> setCamaACriterio()");
		
		Cama_VO obj=getGson().fromJson(cama,Cama_VO.class);
		
		return getAdminCama().setCriterioACama(obj);
	}
	
	
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	public Admin_Camas getAdminCama() {
		return adminCama;
	}

	public void setAdminCama(Admin_Camas adminCama) {
		this.adminCama = adminCama;
	}

	private Gson getGson(){
		return new Gson();
	}
}
