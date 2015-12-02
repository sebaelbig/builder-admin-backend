package ar.com.builderadmin.ws.internacion.epicrisis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.I_WebContextHolder;
import ar.com.builderadmin.controllers.WebContextHolder;
import ar.com.builderadmin.controllers.internacion.epicrisis.Admin_Epicrisis;
import ar.com.builderadmin.vo.internacion.epicrisis.Epicrisis_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/internacion/epicrisis", produces = "application/json;charset=utf-8")
public class WS_Epicrisis extends WS_Abstracto {

	@Autowired
	private I_WebContextHolder webContextHolder;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public I_WebContextHolder webContextHolder() {
		return new WebContextHolder();
	}

	@Autowired
	private Admin_Epicrisis admin_epicrisis;

	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_Epicrisis -> ping";
	}

	@RequestMapping(value = "/seguro/guardarEpicrisis", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String guardarEpicrisis(@RequestBody String jsonPrivado) {
		System.out.println("WS_Epicrisis -> guardarEpicrisis(" + jsonPrivado
				+ ")");

		Epicrisis_VO epicrisis = getGson().fromJson(jsonPrivado,
				Epicrisis_VO.class);

		return admin_epicrisis.guardarEpicrisis(epicrisis, getUsuarioAccion());
	}

	@RequestMapping(value = "/seguro/getEpicrisis", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getEpicrisis(@RequestBody String idCarpeta) {
		System.out.println("WS_Epicrisis -> getEpicrisis(" + idCarpeta + ")");

		return admin_epicrisis.getDatosEpicrisis(idCarpeta, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/getEpicrisisCerrada", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getEpicrisisCerrada(@RequestBody String idCarpeta) {
		System.out.println("WS_Epicrisis -> getEpicrisisCerrada(" + idCarpeta + ")");

		return admin_epicrisis.getEpicrisisCerrada(idCarpeta, getUsuarioAccion());
	}

	/**
	 * Para cerrar
	 */
	@RequestMapping(value = "/seguro/cerrarEpicrisis", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String cerrarEpicrisis(@RequestBody String idCarpeta) {
		
		System.out.println("WS_epicrisis -> cerrarepicrisis(" + idCarpeta + ")");
		
//		Epicrisis_VO epicrisis = getGson().fromJson(jsonEpicrisis,Epicrisis_VO.class);
		
		return admin_epicrisis.cerrarEpicrisis(idCarpeta, getUsuarioAccion());
	}

	/**
	 * Para imprimir PDF
	 * 
	 * @return
	 */
	@RequestMapping(value = "/imprimir/pdf/{idCarpeta}/{idEpicrisis}/{timestamp}", produces = "application/pdf;", method = RequestMethod.GET)
	public byte[] imprimir(@PathVariable String idCarpeta,
			@PathVariable String idEpicrisis, @PathVariable String timestamp) {
		System.out.println("WS_epicrisis -> imprimir(" + idCarpeta + ", "
				+ idEpicrisis + ")");

		return admin_epicrisis.imprimir(Long.parseLong(idCarpeta),
				Long.parseLong(idEpicrisis), getUsuarioAccion());
	}

	/**
	 * 
	 * @return pdf de HC digitalizada
	 */
	@RequestMapping(value = "/getHCDigital/{idCarpeta}", produces = "application/pdf;", method = RequestMethod.GET)
	public byte[] getHCDigital(@PathVariable String idCarpeta) {
		System.out.println("WS_epicrisis -> getHCdigital( " + idCarpeta + " )");

		return admin_epicrisis.getHCDigital(idCarpeta, getUsuarioAccion());
	}

	
	public Admin_Epicrisis getAdmin_epicrisis() {
		return admin_epicrisis;
	}

	public void setAdmin_epicrisis(Admin_Epicrisis admin_epicrisis) {
		this.admin_epicrisis = admin_epicrisis;
	}

	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	private Gson getGson() {
		return new Gson();
	}

}
