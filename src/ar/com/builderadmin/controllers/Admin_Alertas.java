package ar.com.builderadmin.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.alerta.Alerta;

/**
 * Componente para el manejo de las obras sociales
 * 
 * @author seba garcia
 */
@Component
public class Admin_Alertas  {

	private static Admin_Alertas instance;
	
	public static Admin_Alertas getInstance(){
		if (instance==null){
			instance = new Admin_Alertas();
		}
		return instance;
	}
	
	/**
	 * Guarda un alerta y emite un publish
	 * 
	 * @param em
	 * @param al
	 * @param funcionDisparadora 
	 * 
	 * @return devuelve si todo fue correcto
	 */
	@Transactional
	public Boolean guardarAlerta(EntityManager em, Alerta al, I_FX funcionDisparadora) {
		Boolean resul = false;
		
		try {

			//Guardo la alerta
			al.setTimestamp(new Date());
			al = em.merge(al);
			
			// Le pido a la funcion que arme lo que quiere enviar
//			Map<String, Object> datosAPublicar = funcionDisparadora.armarDatosPublicacionComet(em);
			Map<String, Object> datosAPublicar = new HashMap<>();
			
			//Le agrego el alerta generado
			datosAPublicar.put("Alerta", al);
			
			//Envio lo que dice la funcion, y le agrego el Alerta
//			WebSocketAlertasServlet.enviarAlerta(new Gson().toJson(datosAPublicar));
			
			resul = true;
			
		} catch (Exception e) {
			System.out.println("No se pudo enviar la alerta: " + al);
			resul = false;
			e.printStackTrace();
		}
		
		return resul;
	}
	
}