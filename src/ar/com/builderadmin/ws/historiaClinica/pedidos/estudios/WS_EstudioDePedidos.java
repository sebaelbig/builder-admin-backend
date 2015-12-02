package ar.com.builderadmin.ws.historiaClinica.pedidos.estudios;

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
import ar.com.builderadmin.controllers.historiaClinica.pedidos.estados.Admin_EstudioDePedidos;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/historiaClinica/estudioDePedidos", produces = "application/json;charset=utf-8")
public class WS_EstudioDePedidos extends WS_Abstracto {

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
		return "WS_EstudioDePedidos -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_EstudioDePedidos admin_EstudioDePedidos;
	
	@RequestMapping(value = "/seguro/findById/{id}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String id) {
		System.out.println("WS_EstudioDePedidos -> findById(" + id + ")");
		
		return admin_EstudioDePedidos.findById(Long.parseLong(id),  getUsuarioAccion());
	}
	
	
	@RequestMapping(value = "/seguro/informar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String informar(@RequestBody String jsonPrivado) {
		System.out.println("WS_EstudioDePedidos -> informar(" + jsonPrivado + ")");

		EstudioDePedido_VO pedido = getGson().fromJson(jsonPrivado, EstudioDePedido_VO.class);

		return admin_EstudioDePedidos.informar(pedido, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/atender", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String atender(@RequestBody String jsonPrivado) {
		System.out.println("WS_EstudioDePedidos -> atender(" + jsonPrivado + ")");
		
		EstudioDePedido_VO pedido = getGson().fromJson(jsonPrivado, EstudioDePedido_VO.class);
		
		return admin_EstudioDePedidos.atender(pedido, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/cancelar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String cancelar(@RequestBody String jsonPrivado) {
		System.out.println("WS_EstudioDePedidos -> cancelar(" + jsonPrivado + ")");
		
		EstudioDePedido_VO pedido = getGson().fromJson(jsonPrivado, EstudioDePedido_VO.class);
		
		return admin_EstudioDePedidos.cancelar(pedido, getUsuarioAccion());
	}
	
	/**
	 * @return the webContextHolder
	 */
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	/**
	 * @param webContextHolder
	 *            the webContextHolder to set
	 */
	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	private Gson getGson(){
		return new Gson();
	}
} 