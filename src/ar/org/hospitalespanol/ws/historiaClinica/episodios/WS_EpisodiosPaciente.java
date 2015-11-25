package ar.org.hospitalespanol.ws.historiaClinica.episodios;

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
import ar.org.hospitalespanol.controllers.historiaClinica.episodios.Admin_EpisodiosPaciente;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/episodioPaciente", produces = "application/json;charset=utf-8")
public class WS_EpisodiosPaciente extends WS_Abstracto{
	
	@Autowired
	private I_WebContextHolder webContextHolder;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public I_WebContextHolder webContextHolder() {
		return new WebContextHolder();
	}
	
	@Autowired
	private Admin_EpisodiosPaciente adminEpisodioPaciente;

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_EpisodiosPaciente -> ping";
	}
	
	@RequestMapping(value = "/historialDePaciente/{nroDoc}/{tipoDoc}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String historialDePaciente(@PathVariable String nroDoc, @PathVariable String tipoDoc) {
		System.out.println("WS_EpisodiosPaciente -> historialDePaciente(tipo:"+tipoDoc+"nro:"+nroDoc+")");
		
		String listado = getAdminEpisodioPaciente().getEpisodiosDePaciente(Integer.parseInt(tipoDoc), Integer.parseInt(nroDoc));
		
		System.out.println("WS_EpisodiosPaciente -> historialDePaciente Responde("+listado+")");
		
		return listado;

	}
	
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	public Admin_EpisodiosPaciente getAdminEpisodioPaciente() {
		return adminEpisodioPaciente;
	}

	public void setAdminEpisodioPaciente(
			Admin_EpisodiosPaciente adminEpisodioPaciente) {
		this.adminEpisodioPaciente = adminEpisodioPaciente;
	}

	private Gson getGson(){
		return new Gson();
	}
	
	
}
