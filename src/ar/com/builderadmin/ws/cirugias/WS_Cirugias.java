package ar.com.builderadmin.ws.cirugias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.controllers.cirugia.Admin_CirugiaProgramada;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value="/cirugias", produces="application/json;charset=utf-8")
public class WS_Cirugias extends WS_Abstracto{
	
	
	/**
	 * Parameter DAO
	 */
	@Autowired
	private Admin_CirugiaProgramada adminCirugias;
	
	
	@RequestMapping(value="/ping", produces="application/json;charset=utf-8",  method= RequestMethod.GET)
	public  String ping() {
		return "WS_cirugias -> ping";
	}

	@RequestMapping(value="/seguro/obtenerCirugiasDeQuirofanoParaUnaFecha/{dia}/{mes}/{anio}/{nroSala}", produces="application/json;charset=utf-8", method= RequestMethod.GET)
	public String obtenerCirugiasDeQuirofanoParaUnaFecha(@PathVariable String dia, @PathVariable String mes,
			@PathVariable String anio, @PathVariable Integer nroSala){
		
		String fecha = dia+"/"+mes+"/"+anio;
		
		System.out.println("WS_cirugias -> obtenerCirugiasDeQuirofanoParaUnaFecha("
				+ fecha + ", " + nroSala +")");
	
		
		return adminCirugias.obtenerCirugiasDeQuirofanoParaUnaFecha(fecha, nroSala);
		
	}


	class Requerimiento{
		
		private String fecha;
		private Integer nroSala;
		Requerimiento(){
		}
		/**
		 * @return the fecha
		 */
		public String getFecha() {
			return fecha;
		}
		/**
		 * @param fecha the fecha to set
		 */
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		/**
		 * @return the nroSala
		 */
		public Integer getNroSala() {
			return nroSala;
		}
		/**
		 * @param nroSala the nroSala to set
		 */
		public void setNroSala(Integer nroSala) {
			this.nroSala = nroSala;
		}
		
	}

}