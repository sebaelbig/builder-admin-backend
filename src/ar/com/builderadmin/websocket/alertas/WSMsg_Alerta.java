package ar.com.builderadmin.websocket.alertas;

/**
 * Representa al mensaje que se envia ante un alerta
 * 
 * @author segarcia
 * 
 */
public class WSMsg_Alerta {

	private final Msg_Alerta messageInfo;

	public WSMsg_Alerta(String from, String to, String message) {
		this.messageInfo = new Msg_Alerta(from, to, message);
	}

	public class Msg_Alerta {

		private final String from;

		private final String to;

		private final String message;

		public Msg_Alerta(String from, String to, String message) {
			this.from = from;
			this.to = to;
			this.message = message;
		}

		public String getFrom() {
			return from;
		}

		public String getTo() {
			return to;
		}

		public String getMessage() {
			return message;
		}
	}

	public Msg_Alerta getMensaje() {
		return messageInfo;
	}
	
}
