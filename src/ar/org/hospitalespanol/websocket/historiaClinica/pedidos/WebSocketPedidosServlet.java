package ar.org.hospitalespanol.websocket.historiaClinica.pedidos;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.dao.DAO_Utils;

import com.google.gson.Gson;

@WebServlet(urlPatterns={"/pedidos/bloquear", "/pedidos/desbloquear"})
public class WebSocketPedidosServlet extends WebSocketServlet {

	private static final String CANAL_BLOQUEAR = "/pedidos/bloquear";
	private static final String CANAL_DESBLOQUEAR = "/pedidos/desbloquear";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(WebSocketPedidosServlet.class);

	/**
	 * Lista de conexiones
	 */
	private static final Map<String, Cx_Pedido> cxs_bloquear = new HashMap<>();
	private static final Map<String, Cx_Pedido> cxs_desbloquear = new HashMap<>();


	/**
	 * Handshake - registro el subscriptor de alertas, lo encolo
	 */
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {

		// Obtengo el id de sesion del cliente
		final String connectionId = request.getSession().getId();
		
		// Obtengo el nombre de usuario que envia el mensaje
//		final String data = request.getParameter("data");
		
		// Creo una conexion de Chat que acepte entradas
		Cx_Pedido conexion = new Cx_Pedido(connectionId);
			
		//Obtengo el url Pattern por el cual se conecto
		String urlPattern = request.getRequestURI().replace("/horus_restfull", "");
		switch (urlPattern) {
			case CANAL_BLOQUEAR:
				getCxsBloquear().put(connectionId, conexion);
				break;
	
			case CANAL_DESBLOQUEAR:
				getCxsDesbloquear().put(connectionId, conexion);
				break;
		}
		DAO_Utils.info(log, "WebSocketPedidosServlet", "createWebSocketInbound", "websocket_pedidos","Se unió al canal: "+urlPattern);
		
		return conexion;
	}
	
	
	public synchronized static boolean bloquearPedido(Map<String, Object> datosAPublicar){
		
		DAO_Utils.info(log, "WebSocketPedidosServlet", "bloquearPedido", "websocwebsocket_pedidosket",
				"Avisando a todos los subscriptos: "+datosAPublicar);
		
		// Obtengo el mapa de datos que quiere publicar la funcion
//		Map<String, Object> datosAPublicar = alerta.armarDatosPublicacionComet(em);
		
		//Datos a enviar por WS
		String json_datos = new Gson().toJson(datosAPublicar);
		
		// Wrapeo el mensaje a enviar
		final CharBuffer jsonMessage = CharBuffer.wrap(json_datos);
		boolean todoOK = true;
		for (Cx_Pedido cx : getCxsBloquear().values()) {
			
			try {
				
				cx.getWsOutbound().writeTextMessage(jsonMessage);
				
			} catch (IOException e) {
				todoOK = false;
				DAO_Utils.error(log, "WebSocketPedidosServlet", "bloquearPedido", "websocket-user", "Error al querer bloquear un pedido");
				//e.printStackTrace();
				
			}
			
		}
		
		return todoOK;
	}
	
	public static boolean desbloquearPedido(Map<String, Object> datosAPublicar){
		
		DAO_Utils.info(log, "WebSocketPedidosServlet", "desbloquearPedido", "websocket_pedidos",
				"Avisando a todos los subscriptos: "+datosAPublicar);
		
		// Obtengo el mapa de datos que quiere publicar la funcion
//		Map<String, Object> datosAPublicar = alerta.armarDatosPublicacionComet(em);

		//Datos a enviar por WS
		String json_datos = new Gson().toJson(datosAPublicar);
		
		// Wrapeo el mensaje a enviar
		final CharBuffer jsonMessage = CharBuffer.wrap(json_datos);
		boolean todoOK = true;
		for (Cx_Pedido cx : getCxsDesbloquear().values()) {
			
			try {
				
				cx.getWsOutbound().writeTextMessage(jsonMessage);
				
			} catch (IOException e) {
				todoOK = false;
				DAO_Utils.error(log, "WebSocketPedidosServlet", "desbloquearPedido", "websocket-user", "Error al querer desbloquear un pedido");
				//e.printStackTrace();
			}
				
		}
		return todoOK;
	}
	
	/**
	 * Clase que representa una conexion de chat, tiene el id de conexion junto
	 * con el usuario que crea la conexion
	 * 
	 * @author segarcia
	 * 
	 */
	private static class Cx_Pedido extends MessageInbound {

		private final String connectionId;
		
		public Cx_Pedido(String id){
			this.connectionId = id;
		}
		
		@Override
		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
			// No necesito escuchar ninguna alerta, solo envio
		}

		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
			// No necesito escuchar ninguna alerta, solo envio
			getWsOutbound().writeTextMessage(message);
		}

		/**
		 * Se desconecto
		 */
		@Override
		protected void onClose(int status) {
			
			DAO_Utils.info(log, "Cx_Pedido", "onClose", "websocket_pedidos","Cerro la conexion WebSocket: " + getConnectionId());
			getCxsBloquear().remove(connectionId);
			getCxsDesbloquear().remove(connectionId);
			
		}

		/**
		 * @return the connectionId
		 */
		public String getConnectionId() {
			return connectionId;
		}
		
	}

	/**
	 * @return the cxsBloquear
	 */
	public static Map<String, Cx_Pedido> getCxsBloquear() {
		return cxs_bloquear;
	}


	/**
	 * @return the cxsDesbloquear
	 */
	public static Map<String, Cx_Pedido> getCxsDesbloquear() {
		return cxs_desbloquear;
	}


}