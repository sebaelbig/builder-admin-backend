// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PublicadorComet.java

package ar.com.builderadmin.controllers.comet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.alerta.Alerta;

//@Name("publicadorComet")
//@Scope(ScopeType.APPLICATION)
// @Startup
// @AutoCreate
@Controller
public class PublicadorComet {

	// @Logger
	private final Logger logger = LoggerFactory
			.getLogger(PublicadorComet.class);

	// @In(required=true)
	// private EntityManager em;

	/**
	 * Mapa que contiene los administradores, por cada administrador el canal a
	 * donde publicar y el servlet publicador que ejecuta la publicacion
	 * 
	 * Map< Administrador, Map< Publicador, canalDondePublicar > >
	 * 
	 * Map<String, Map<String, String>>
	 * 
	 */
	private Map<String, List<PublicacionComet>> canalaesAPublicar = new HashMap<String, List<PublicacionComet>>();

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
	 *               nombreDeAdmin, Map<String, String> canalesMetodo )
	 */

	/**
	 * Cola de publicaciones a la espera de ser publicadas
	 */
	// private final Set<PublicadorComet.Publicacion> colaDePublicaciones = new
	// HashSet<PublicadorComet.Publicacion>();

	// private Reloj relojPublicaciones = new Reloj();

	public PublicadorComet() {
		/*
		 * try { this.relojPublicaciones.start(120); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	/**
	 * Registra al administrador con los canales en donde quiere publicar
	 * 
	 * @param nombreDeAdmin
	 * @param canales
	 */
	// @Asynchronous
	public void registrarCanalesAPublicar(String nombreDeAdmin,
			List<PublicacionComet> nombresPublicadorCanal) {

		if (!this.getCanalaesAPublicar().containsKey(nombreDeAdmin)) {
			this.logger.info("Se registró el administrador " + nombreDeAdmin
					+ ", para publicar a los canales: "
					+ nombresPublicadorCanal.toString());

			// Si no esta en el mapa
			this.getCanalaesAPublicar().put(nombreDeAdmin,
					nombresPublicadorCanal);
		} else {
			this.logger
					.error("El administrador " + nombreDeAdmin
							+ ", para publicar a los canales: "
							+ nombresPublicadorCanal.toString()
							+ " ya fue registrado.");
		}

	}

	/**
	 * Dado un administrador, se publica su mensaje en todos los canales
	 * subscriptos en el comienzo
	 * 
	 * @param admin
	 * @param funcion
	 */
	// @Asynchronous
	public void publicar(String nombreAdmin, I_FX funcion) {
		this.logger.info("Se publicara en los canales del admin: "
				+ nombreAdmin);

		// Se utiliza una cola de publicaciones que se enviar�n todas juntas
		// cada cierto tiempo para no estar
		// sobrecargando constantemente las red

		// this.encolarPublicacion(new Publicacion(nombreAdmin, funcion));
		// Publicacion publi = new Publicacion(nombreAdmin, funcion);
		//
		// // Itero la lista de publicaciones comet que se deben hacer para este
		// // Admin
		// for (PublicacionComet publicacion : this.getCanalaesAPublicar().get(
		// publi.getNombreAdmin())) {
		//
		// // Obtengo el mapa de datos que quiere publicar la funcion
		// Map<String, Object> datosAPublicar = publi.getFuncion()
		// .armarDatosPublicacionComet(this.em);
		//
		// // Obtengo el canal en donde se quiere publicar
		// String canal = publicacion.getCanalDondePublicar() + "/"
		// + publi.getFuncion().getClass().getSimpleName();
		//
		// // Recupero el servicio publicador del contexto aplicacion
		// Abstract_Publisher publicador = (Abstract_Publisher) Contexts
		// .getApplicationContext().get(publicacion.getPublicador());
		//
		// // Publico en el canal que se registro
		// publicador.publicarEn(canal, datosAPublicar);
		//
		// }

	}

	/**
	 * Dado un administrador, se publica su mensaje en todos los canales
	 * subscriptos en el comienzo
	 * 
	 * @param admin
	 * @param funcion
	 */
	// @Asynchronous
	public void publicarAlerta(EntityManager em, String nombreAdmin,
			Alerta alerta) {
//		
//		this.logger.info("Se publicara en los canales del admin: #0",
//				nombreAdmin);
//
//		// Itero la lista de publicaciones comet que se deben hacer para este
//		// Admin
//		for (PublicacionComet publicacion : this.getCanalaesAPublicar().get(
//				nombreAdmin)) {
//
//			// Obtengo el mapa de datos que quiere publicar la funcion
//			Map<String, Object> datosAPublicar = alerta
//					.armarDatosPublicacionComet(em);
//
//			// Obtengo el canal en donde se quiere publicar
//			// String canal = publicacion.getCanalDondePublicar() +
//			// ((usarFx)?"/"+ publi.getFuncion().getClass().getSimpleName():"")
//			// ;
//			String canal = publicacion.getCanalDondePublicar();
//
//			// Recupero el servicio publicador del contexto aplicacion
//			Abstract_Publisher publicador = (Abstract_Publisher) Contexts
//					.getApplicationContext().get(publicacion.getPublicador());
//
//			// Publico en el canal que se registro
//			publicador.publicarEn(canal, datosAPublicar);
//
//		}

	}

	/*
	 * public void procesarCola(){
	 * log.info("Proceso cola de publicaciones....");
	 * 
	 * //Itero sobre la cola de publicaciones a ejecutar for (Publicacion publi
	 * : this.getColaDePublicaciones()){
	 * 
	 * //Itero la lista de publicaciones comet que se deben hacer para este
	 * Admin for ( PublicacionComet publicacion :
	 * this.getCanalaesAPublicar().get(publi.getNombreAdmin())){
	 * 
	 * //Obtengo el mapa de datos que quiere publicar la funcion Map<String,
	 * Object> datosAPublicar = publi.getFuncion().armarDatosPublicacionComet();
	 * 
	 * //Obtengo el canal en donde se quiere publicar String canal =
	 * publicacion.getCanalDondePublicar() +"/"+
	 * publi.getFuncion().getClass().getSimpleName();
	 * 
	 * //Recupero el servicio publicador del contexto aplicacion I_Publicador
	 * publicador = (I_Publicador)
	 * Contexts.getApplicationContext().get(publicacion.getPublicador());
	 * 
	 * //Publico en el canal que se registro publicador.publicarEn(canal,
	 * datosAPublicar);
	 * 
	 * } }
	 * 
	 * //Una vez q se realizaron todas las publicaciones se limpia la coleccion
	 * this.getColaDePublicaciones().clear(); }
	 */

	class Publicacion {
		private String nombreAdmin;
		private I_FX funcion;

		Publicacion(String nombreAdmin, I_FX funcion) {
			this.setNombreAdmin(nombreAdmin);
			this.setFuncion(funcion);
		}

		public I_FX getFuncion() {
			return this.funcion;
		}

		public String getNombreAdmin() {
			return this.nombreAdmin;
		}

		private void setFuncion(I_FX funcion) {
			this.funcion = funcion;
		}

		private void setNombreAdmin(String nombreAdmin) {
			this.nombreAdmin = nombreAdmin;
		}
	}

//	private Set<Publicacion> getColaDePublicaciones() {
//		return this.colaDePublicaciones;
//	}

	public Map<String, List<PublicacionComet>> getCanalaesAPublicar() {
		return this.canalaesAPublicar;
	}

	public void setCanalaesAPublicar(
			Map<String, List<PublicacionComet>> canalaesAPublicar) {
		this.canalaesAPublicar = canalaesAPublicar;
	}

	/*
	 * class Reloj {
	 * 
	 * Timer timer = new Timer(); // El timer que se encarga de administrar los
	 * tiempo de repeticion public boolean frozen; // manejar el estado del
	 * contador TIMER AUTOMATICO -- True Detenido | False Corriendo
	 * 
	 * // clase interna que representa una tarea, se puede crear varias tareas y
	 * asignarle al timer luego class ProcesadorDeCola extends TimerTask {
	 * 
	 * public void run() { procesarCola(); }// end run()
	 * 
	 * }// end SincronizacionAutomatica
	 * 
	 * public void start(int pSeg) throws Exception { frozen = false; // le
	 * asignamos una tarea al timer timer.schedule(new ProcesadorDeCola(), 0,
	 * pSeg * 1000 * 60); }// end Start
	 * 
	 * public void stop() { System.out.println("Stop"); frozen = true; }// end
	 * Stop
	 * 
	 * public void reset() { System.out.println("Reset"); frozen = true; }// end
	 * Reset
	 * 
	 * }// end Reloj
	 */

	// /**
	// * @return the em
	// */
	// public EntityManager getEm() {
	// return em;
	// }
	//
	// /**
	// * @param em
	// * the em to set
	// */
	// public void setEm(EntityManager em) {
	// this.em = em;
	// }

}