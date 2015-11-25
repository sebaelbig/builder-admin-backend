package ar.org.hospitalespanol.websocket.chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.websocket.chat.messages.ConnectionInfoMessage;
import ar.org.hospitalespanol.websocket.chat.messages.MessageInfoMessage;
import ar.org.hospitalespanol.websocket.chat.messages.StatusInfoMessage;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/chat")
public class WebSocketChatServlet extends WebSocketServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(WebSocketChatServlet.class);

	private static final Map<String, ChatConnection> connections = new HashMap<String, ChatConnection>();

	@Override
	protected boolean verifyOrigin(String origin) {
		return true;
	}

	/**
	 * Handshake
	 */
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {

		// Obtengo el id de sesion del cliente
		final String connectionId = request.getSession().getId();

		// Obtengo el nombre de usuario que envia el mensaje
		final String userName = request.getParameter("userName");

		// Creo una conexion de Chat que acepte entradas
		return new ChatConnection(connectionId, userName);
	}

	/**
	 * Clase que representa una conexion de chat, tiene el id de conexion junto
	 * con el usuario que crea la conexion
	 * 
	 * @author segarcia
	 * 
	 */
	private static class ChatConnection extends MessageInbound {

		private final String connectionId;

		private final String userName;

		private final Gson jsonProcessor;

		private ChatConnection(String connectionId, String userName) {
			this.connectionId = connectionId;
			this.userName = userName;
			this.jsonProcessor = new Gson();
		}

		/**
		 * Alguien se conecto
		 */
		@Override
		protected void onOpen(WsOutbound outbound) {

			sendConnectionInfo(outbound);

			sendStatusInfoToOtherUsers(new StatusInfoMessage(userName,
					StatusInfoMessage.STATUS.CONNECTED));

			connections.put(connectionId, this);
		}

		/**
		 * Se desconecto
		 */
		@Override
		protected void onClose(int status) {
			
			sendStatusInfoToOtherUsers(new StatusInfoMessage(userName,
					StatusInfoMessage.STATUS.DISCONNECTED));
			
			connections.remove(connectionId);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer byteBuffer)
				throws IOException {
			throw new UnsupportedOperationException(
					"Binary messages not supported");
		}

		/**
		 * Llega un mensaje de texto
		 * 
		 */
		@Override
		protected void onTextMessage(CharBuffer charBuffer) throws IOException {

			// Parseo el JSON, en un Mensaje
			final MessageInfoMessage message = jsonProcessor.fromJson(
					charBuffer.toString(), MessageInfoMessage.class);

			// Obtengo la conexion con el usuario destino
			final ChatConnection destinationConnection = getDestinationUserConnection(message
					.getMessageInfo().getTo());

			if (destinationConnection != null) {
				// Se encontro la conexion

				// Wrapeo el mensaje a enviar
				final CharBuffer jsonMessage = CharBuffer.wrap(jsonProcessor
						.toJson(message));

				// Envio el mensaje a destino
				destinationConnection.getWsOutbound().writeTextMessage(
						jsonMessage);

			} else {
				log.warn("Se está intentando enviar un mensaje a un usuario no conectado");
			}
		}

		public String getUserName() {
			return userName;
		}

		/**
		 * Envio la informacion de la conexion 
		 * 
		 * @param outbound Canal de salida
		 */
		private void sendConnectionInfo(WsOutbound outbound) {

			final List<String> activeUsers = getActiveUsers();

			final ConnectionInfoMessage connectionInfoMessage = new ConnectionInfoMessage(
					userName, activeUsers);
			
			try {
				
				outbound.writeTextMessage(CharBuffer.wrap(jsonProcessor
						.toJson(connectionInfoMessage)));
				
			} catch (IOException e) {
				log.error("No se pudo enviar el mensaje", e);
			}
		}

		/**
		 * Listo los usuarios con conexiones activas
		 * 
		 * @return
		 */
		private List<String> getActiveUsers() {

			final List<String> activeUsers = new ArrayList<String>();
			
			for (ChatConnection connection : connections.values()) {
				
				activeUsers.add(connection.getUserName());
				
			}
			
			return activeUsers;
		}

		/**
		 * Envio el estado de a todas las conexiones 
		 * 
		 * @param message
		 */
		private void sendStatusInfoToOtherUsers(StatusInfoMessage message) {
			
			final Collection<ChatConnection> otherUsersConnections = getAllChatConnectionsExceptThis();
			
			for (ChatConnection connection : otherUsersConnections) {
				try {
					
					connection.getWsOutbound().writeTextMessage(
							CharBuffer.wrap(jsonProcessor.toJson(message)));
					
				} catch (IOException e) {
					log.error("No se pudo enviar el mensaje", e);
				}
			}
		}

		/**
		 * IObtiene todas las conexiones activas excepto la actual
		 * 
		 * @return
		 */
		private Collection<ChatConnection> getAllChatConnectionsExceptThis() {
			
			final Collection<ChatConnection> allConnections = connections
					.values();
			
			allConnections.remove(this);
			
			return allConnections;
		}

		/**
		 * Dado un nombre de usuario devuelve, si existe, la conexion activa 
		 * 
		 * @param destinationUser
		 * @return
		 */
		private ChatConnection getDestinationUserConnection(
				String destinationUser) {
			
			for (ChatConnection connection : connections.values()) {
				
				if (destinationUser.equals(connection.getUserName())) {
					
					return connection;
					
				}
				
			}
			
			return null;
		}

	}

}