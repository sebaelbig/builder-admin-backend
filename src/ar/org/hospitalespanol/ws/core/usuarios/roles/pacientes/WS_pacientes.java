package ar.org.hospitalespanol.ws.core.usuarios.roles.pacientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.org.hospitalespanol.controllers.I_WebContextHolder;
import ar.org.hospitalespanol.controllers.WebContextHolder;
import ar.org.hospitalespanol.controllers.core.usuarios.roles.pacientes.Admin_Pacientes;
import ar.org.hospitalespanol.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/pacientes", produces = "application/json;charset=utf-8")
public class WS_pacientes extends WS_Abstracto{
	
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
		return "WS_Pacientes -> ping";
	}
	
	@Autowired
	private Admin_Pacientes adminPacientes ;
	
	
	@RequestMapping(value = "/datosDePaciente/{nroDoc}/{tipoDoc}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String datosDePaciente(@PathVariable String nroDoc, @PathVariable String tipoDoc) {		
		System.out.println("WS_Pacientes -> datosDePaciente()");
	
		String listado = this.getAdminPacientes().getDatosDePaciente(tipoDoc,nroDoc);
		
		return listado;
	}
	
	@RequestMapping(value = "/buscarPaciente/{apellido}/{nombre}/{nroDoc}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buscarPaciente(@PathVariable String apellido, @PathVariable String nombre, @PathVariable String nroDoc) {		
		System.out.println("WS_Pacientes -> buscarPaciente(apellido:"+apellido+", "+nombre+"nro:"+nroDoc+")");
	
		String listado = this.getAdminPacientes().buscarPacientePorNroDocApellido(apellido,nombre, nroDoc);
		
		return listado;
	}

	public Admin_Pacientes getAdminPacientes() {
		return adminPacientes;
	}

	public void setAdminPacientes(Admin_Pacientes adminPacientes) {
		this.adminPacientes = adminPacientes;
	}
	
	

}
