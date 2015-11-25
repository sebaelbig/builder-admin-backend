package ar.org.hospitalespanol.websocket.alertas;

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
import org.springframework.scheduling.annotation.Async;

import ar.org.hospitalespanol.dao.DAO_Utils;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/alertas")
public class WebSocketAlertasServlet extends WebSocketServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(WebSocketAlertasServlet.class);

	/**
	 * Lista de subscriptores
	 */
	private static final Map<String, Cx_Alerta> conexiones = new HashMap<>();


	/**
	 * Handshake - registro el subscriptor de alertas, lo encolo
	 */
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {

		// Obtengo el id de sesion del cliente
		final String connectionId = request.getSession().getId();
		DAO_Utils.info(log, "WebSocketAlertasServlet", "createWebSocketInbound", "websocket", "Se recibio un handshake. SID:"+connectionId);

		// Obtengo el nombre de usuario que envia el mensaje
//		final String data = request.getParameter("data");
		
		// Creo una conexion de Chat que acepte entradas
		Cx_Alerta conexion = new Cx_Alerta(connectionId);
		
		getConexiones().put(connectionId, conexion);
		
		return conexion;
	}
	
	public synchronized static void enviarAlerta(String datosAPublicar){
		
		DAO_Utils.info(log, "WebSocketAlertasServlet", "enviarAlerta", "websocket",
				"Enviando la alerta a todos los subscriptos: "+datosAPublicar);
		
		// Obtengo el mapa de datos que quiere publicar la funcion
//		Map<String, Object> datosAPublicar = alerta.armarDatosPublicacionComet(em);

		//Datos a enviar por WS
		String json_datos = new Gson().toJson(datosAPublicar);
		
		// Wrapeo el mensaje a enviar
		final CharBuffer jsonMessage = CharBuffer.wrap(json_datos);
		
		for (Cx_Alerta cx : getConexiones().values()) {
			
			try {
				
				cx.getWsOutbound().writeTextMessage(jsonMessage);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
				
		}
		
	}
	
	/**
	 * Clase que representa una conexion de chat, tiene el id de conexion junto
	 * con el usuario que crea la conexion
	 * 
	 * @author segarcia
	 * 
	 */
	private static class Cx_Alerta extends MessageInbound {

		private final String connectionId;
		
		public Cx_Alerta(String id){
			this.connectionId = id;
		}
		
		@Override
		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
			// No necesito escuchar ninguna alerta, solo envio
		}

		@Override
		protected void onTextMessage(CharBuffer arg0) throws IOException {
			// No necesito escuchar ninguna alerta, solo envio
		}

		/**
		 * Se desconecto
		 */
		@Override
		protected void onClose(int status) {
			
			DAO_Utils.info(log, "Cx_Alerta", "onClose", "websocket", "Cerro la conexion WebSocket: " + getConnectionId());
			getConexiones().remove(connectionId);
			
		}

		/**
		 * @return the connectionId
		 */
		public String getConnectionId() {
			return connectionId;
		}
		
		
		
	}

	/**
	 * @return the conexiones
	 */
	public static Map<String, Cx_Alerta> getConexiones() {
		return conexiones;
	}

}