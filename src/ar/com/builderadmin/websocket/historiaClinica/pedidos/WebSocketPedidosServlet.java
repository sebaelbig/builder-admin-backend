package ar.com.builderadmin.websocket.historiaClinica.pedidos;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.dao.DAO_Utils;

@ServerEndpoint("/pedidos/bloquear")
		//, "/pedidos/desbloquear")
public class WebSocketPedidosServlet {

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
	private static final Map<String, Session> cxs_bloquear = new HashMap<>();
	private static final Map<String, Session> cxs_desbloquear = new HashMap<>();

	private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	 
    

    /**
     * Callback hook for Connection close events. This method will be invoked when a
     * client closes a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnClose
    public void onClose(Session userSession) {
        userSessions.remove(userSession);
    }
    

	/**
	 * Handshake - registro el subscriptor de alertas, lo encolo
	 */
    /**
     * Callback hook for Connection open events. This method will be invoked when a 
     * client requests for a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
	@OnOpen
	public void createWebSocketInbound(Session userSession){
//			String subProtocol,
//			HttpServletRequest request) {
//	
//
//		// Obtengo el id de sesion del cliente
//		
//		// Obtengo el nombre de usuario que envia el mensaje
//		final String data = request.getParameter("data");
//		
//		// Creo una conexion de Chat que acepte entradas
//		Session conexion = new Session(connectionId);
//			
//		//Obtengo el url Pattern por el cual se conecto
		String urlPattern = userSession.getRequestURI().getPath().replace("/horus_restfull", "");
		switch (urlPattern) {
			case CANAL_BLOQUEAR:
				getCxsBloquear().put(userSession.getId(), userSession);
				break;
	
			case CANAL_DESBLOQUEAR:
				getCxsDesbloquear().put(userSession.getId(), userSession);
				break;
		}
		DAO_Utils.info(log, "WebSocketPedidosServlet", "createWebSocketInbound", "websocket_pedidos","Se unió al canal: "+urlPattern);
		
	}
	
    /**
     * Callback hook for Message Events. This method will be invoked when a client
     * send a message.
     * @param message The text message
     * @param userSession The session of the client
     */
    @OnMessage
    public void bloquearPedido(String message, Session userSession) {
        System.out.println("Message Received: " + message);
        for (Session session : userSessions) {
            System.out.println("Sending to " + session.getId());
            session.getAsyncRemote().sendText(message);
        }
//    }
//	public synchronized static boolean bloquearPedido(Map<String, Object> datosAPublicar){
//		
		DAO_Utils.info(log, "WebSocketPedidosServlet", "bloquearPedido", "websocwebsocket_pedidosket",
				"Avisando a todos los subscriptos: "+message);
//		
//		// Obtengo el mapa de datos que quiere publicar la funcion
////		Map<String, Object> datosAPublicar = alerta.armarDatosPublicacionComet(em);
//		
//		//Datos a enviar por WS
//		String json_datos = new Gson().toJson(datosAPublicar);
//		
//		// Wrapeo el mensaje a enviar
//		final CharBuffer jsonMessage = CharBuffer.wrap(json_datos);
//		boolean todoOK = true;
//		for (Session cx : getCxsBloquear().values()) {
//			
//			try {
//				
//				cx.getWsOutbound().writeTextMessage(jsonMessage);
//				
//			} catch (IOException e) {
//				todoOK = false;
//				DAO_Utils.error(log, "WebSocketPedidosServlet", "bloquearPedido", "websocket-user", "Error al querer bloquear un pedido");
//				//e.printStackTrace();
//				
//			}
//			
//		}
//		
//		return todoOK;
	}
//	
//	public static boolean desbloquearPedido(Map<String, Object> datosAPublicar){
//		
//		DAO_Utils.info(log, "WebSocketPedidosServlet", "desbloquearPedido", "websocket_pedidos",
//				"Avisando a todos los subscriptos: "+datosAPublicar);
//		
//		// Obtengo el mapa de datos que quiere publicar la funcion
////		Map<String, Object> datosAPublicar = alerta.armarDatosPublicacionComet(em);
//
//		//Datos a enviar por WS
//		String json_datos = new Gson().toJson(datosAPublicar);
//		
//		// Wrapeo el mensaje a enviar
//		final CharBuffer jsonMessage = CharBuffer.wrap(json_datos);
//		boolean todoOK = true;
//		for (Session cx : getCxsDesbloquear().values()) {
//			
//			try {
//				
//				cx.getWsOutbound().writeTextMessage(jsonMessage);
//				
//			} catch (IOException e) {
//				todoOK = false;
//				DAO_Utils.error(log, "WebSocketPedidosServlet", "desbloquearPedido", "websocket-user", "Error al querer desbloquear un pedido");
//				//e.printStackTrace();
//			}
//				
//		}
//		return todoOK;
//	}
//	
//	/**
//	 * Clase que representa una conexion de chat, tiene el id de conexion junto
//	 * con el usuario que crea la conexion
//	 * 
//	 * @author segarcia
//	 * 
//	 */
//	private static class Session extends MessageInbound {
//
//		private final String connectionId;
//		
//		public Session(String id){
//			this.connectionId = id;
//		}
//		
//		@Override
//		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
//			// No necesito escuchar ninguna alerta, solo envio
//		}
//
//		@Override
//		protected void onTextMessage(CharBuffer message) throws IOException {
//			// No necesito escuchar ninguna alerta, solo envio
//			getWsOutbound().writeTextMessage(message);
//		}
//
//		/**
//		 * Se desconecto
//		 */
//		@Override
//		protected void onClose(int status) {
//			
//			DAO_Utils.info(log, "Session", "onClose", "websocket_pedidos","Cerro la conexion WebSocket: " + getConnectionId());
//			getCxsBloquear().remove(connectionId);
//			getCxsDesbloquear().remove(connectionId);
//			
//		}
//
//		/**
//		 * @return the connectionId
//		 */
//		public String getConnectionId() {
//			return connectionId;
//		}
//		
//	}
//
	/**
	 * @return the cxsBloquear
	 */
	public static Map<String, Session> getCxsBloquear() {
		return cxs_bloquear;
	}


	/**
	 * @return the cxsDesbloquear
	 */
	public static Map<String, Session> getCxsDesbloquear() {
		return cxs_desbloquear;
	}


}