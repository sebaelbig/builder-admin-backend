package ar.org.hospitalespanol.ws.historiaClinica.pedidos;

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
import ar.org.hospitalespanol.controllers.historiaClinica.pedidos.Admin_Pedidos;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.ws.WS_Abstracto;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/pedidos", produces = "application/json;charset=utf-8")
public class WS_PedidosHE extends WS_Abstracto {

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
		return "WS_PedidosHE -> ping";
	}

	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Pedidos admin_Pedidos;

	@RequestMapping(value = "/registrarNuevoPedido/{nroPedido}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String registrarNuevoPedido(@PathVariable String nroPedido) {
		System.out.println("WS_PedidosHE -> registrarNuevoPedido(" + nroPedido + ")");

		Pedido_VO pedido = new Pedido_VO();
		pedido.setNumero(nroPedido);

//		return admin_Pedidos.buscar(pedido, getUsuarioAccion());
		return "Recibido OK - Nro de pedido: "+nroPedido;
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

	/**
	 * @return the admin_Pedidos
	 */
	public Admin_Pedidos getAdmin_Pedidos() {
		return admin_Pedidos;
	}

	/**
	 * @param admin_Pedidos the admin_Pedidos to set
	 */
	public void setAdmin_Pedidos(Admin_Pedidos admin_Pedidos) {
		this.admin_Pedidos = admin_Pedidos;
	}

	private Gson getGson(){
		return new Gson();
	}
	
	class RespuestaCorta{
		private Boolean ok =true;
		
		RespuestaCorta(){}
		
		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}
		
	}
}