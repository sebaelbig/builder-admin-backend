package ar.com.builderadmin.controllers.comet;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

//@Name("subscriptorComet")
@Controller
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class SubscriptorComet {

	// @Logger
	protected Log log;

	/**
	 * Mapa que contiene los administradores, por cada administrador el canal a
	 * donde subscribir, el servlet publicador que ejecuta la publicacion y el
	 * metodo al cual se debe llamar para indicar la escucha
	 * 
	 * Map< Administrador, SubscripcionComet >
	 * 
	 * Map<String, Map<String, String>>
	 * 
	 */
	private Map<String, SubscripcionComet> canalaesASubscribir = new HashMap<String, SubscripcionComet>();

	/**
	 * TENTATIVO: la idea es que los administradores tambien le indiquen al
	 * publicador de que canales quieren subscribirse y que mensaje enviarles
	 * ante esa subscipcion, algo asi como:
	 * 
	 * Admin_Turnos ->escuchaDe-> reservoTurno ->llamaAMetodo ->
	 * Admin_Turnos.seReservoTurno()
	 * 
	 * Estructura: Map <nombreAdmin, Map<nombreCanalSubscripto,
	 * nombreMetodoALlamar> > Map <String, Map<String, String> >
	 * 
	 * Se llamaria utilizando Reflex
	 * 
	 * private Map<String, Set<String>> canalaesSubscriptos = new
	 * HashMap<String, Set<String>>();
	 * 
	 * @Asynchronous public void registrarCanalesASubscribirse( String
	 *               nombreDeAdmin, List<String, String> canalMetodo )
	 */

	public SubscriptorComet() {
	}

	/**
	 * Registra al administrador con los canales en donde quiere escuchar
	 * 
	 * @param nombreDeAdmin
	 * @param canales
	 */
	// @Asynchronous
	public void registrarCanalesSubscribir(SubscripcionComet subscComet) {

		log.info(subscComet.toString());

		if (!this.getCanalaesASubscribir().containsKey(
				subscComet.getAdminEscuchador())) {

			// Si no esta en el mapa
			this.getCanalaesASubscribir().put(subscComet.getAdminEscuchador(),
					subscComet);

			// Recorro y me hago escuchador de los servicios
			this.subscribir(subscComet);

		}
	}

	/**
	 * Se recibe un aviso de subscripcion
	 * 
	 * @param admin
	 * @param funcion
	 */
	// @Asynchronous
	public void subscribir(SubscripcionComet sc) {
		log.info("Se subscribira a: " + sc.getAdminEscuchador()
				+ ", en el canal: " + sc.getCanalDondeEscuchar()
				+ ", y llamara al metodo: " + sc.getMetodoADisparar());

		// addService("/echo", "echo")
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Map<String, SubscripcionComet> getCanalaesASubscribir() {
		return canalaesASubscribir;
	}

	public void setCanalaesASubscribir(
			Map<String, SubscripcionComet> canalaesASubscribir) {
		this.canalaesASubscribir = canalaesASubscribir;
	}

}