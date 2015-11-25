package ar.org.hospitalespanol.ws.internacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.I_WebContextHolder;
import ar.org.hospitalespanol.controllers.WebContextHolder;
import ar.org.hospitalespanol.controllers.internacion.Admin_ControlInternacion;
import ar.org.hospitalespanol.model.internacion.Carpeta;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/internacion", produces = "application/json;charset=utf-8")
public class WS_ControlInternacion  extends WS_Abstracto {

	@Autowired
	private I_WebContextHolder webContextHolder;

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
		return "WS_ControlInternacion -> ping";
	}
	
	@Autowired
	private Admin_ControlInternacion admin_controlint;
	
	
	@RequestMapping(value = "/control_dias", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String control_dias() {		
		System.out.println("WS_ControlInternacion -> control_dias()");
	
		String listado = getAdmin_controlint().getListadoInernadosHoy("getUsuarioAccion()");
		
		return listado;
	}
	
	@RequestMapping(value = "/datosInternacionPaciente/{idCarpeta}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String datosInternacionPaciente(@PathVariable String idCarpeta) {		
		System.out.println("WS_ControlInternacion -> datosInternacionPaciente("+idCarpeta+")");
	
		String resul = getAdmin_controlint().getDatosIntPaciente(idCarpeta);
		
		return resul;
	}
	
	@RequestMapping(value = "/seguro/carpetasPorFiltro", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getCarpetasPorFiltro(@RequestBody String carpetaFiltro) {		
		System.out.println("WS_ControlInternacion -> getCarpetasPorFiltro("+carpetaFiltro+")");
		
		Carpeta carpeta=getGson().fromJson(carpetaFiltro, Carpeta.class);
	
		String listado = getAdmin_controlint().getCapetasPorFiltro(getUsuarioAccion(), carpeta);
		
		return listado;
	}
	
	@RequestMapping(value = "/seguro/actualizarAltaMedicaCarpeta", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String setAltaMedicaCarpeta(@RequestBody String carpetaAlta) {		
		System.out.println("WS_ControlInternacion -> getCarpetasPorFiltro("+carpetaAlta+")");
		
		Carpeta carpeta=getGson().fromJson(carpetaAlta, Carpeta.class);
	
		String dato = getAdmin_controlint().setCarpetaAltaMedica(getUsuarioAccion(), carpeta);
		
		return dato;
	}
	
	@RequestMapping(value = "/pacientesInternadosDeProfesional", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String pacientesInternadosDeProfesional(@RequestBody String matricula) {		
		System.out.println("WS_ControlInternacion -> pacientesInternadosDeProfesional("+matricula+")");
	
		String listado = getAdmin_controlint().getListadoInernadosPorProfesional(matricula, getUsuarioAccion());
		
		return listado;
	}
		
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	public Admin_ControlInternacion getAdmin_controlint() {
		return admin_controlint;
	}

	public void setAdmin_controlint(Admin_ControlInternacion admin_controlint) {
		this.admin_controlint = admin_controlint;
	}
	
	private Gson getGson(){
		return new Gson();
	}

}
