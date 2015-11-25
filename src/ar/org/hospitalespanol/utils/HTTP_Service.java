package ar.org.hospitalespanol.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.dao.DAO_Utils;

public class HTTP_Service {

	private final static Logger log = LoggerFactory.getLogger(HTTP_Service.class);
	
	public HTTP_Service() {
	}

	/**
	 * Realiza una llamada GET a @param dirUrl y ejecuta el metodo @param callbackFx del 
	 * objeto @param callbackObject pasandole como parametro el resultado de esa llamada. 
	 * 
	 *  En caso de error se llama @param errorCallbackFx 
	 * 
	 * @param dirUrl
	 * @param callbackFx
	 * @param errorCallbackFx
	 * @param callbackObject
	 */
	public static void get(String dirUrl, String callbackFx,
			String errorCallbackFx, Object callbackObject, String... params) {
		
		DAO_Utils.info(log, "HTTP_Service", "get", "cliente_http", dirUrl);
		try {
			/* Se hace un get a dirUrl */
			
			URL url = new URL(dirUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int codigoRespuesta = connection.getResponseCode();
			
			DAO_Utils.info(log, "HTTP_Service", "get", "cliente_http","Código de respuesta:"+codigoRespuesta+" ");
			
			try {
				
				if (codigoRespuesta == 200) {
					
					//leo el contenido de la pagina
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				    String inputLine;
				    String contenido = "";
				    while ((inputLine = in.readLine()) != null) {
				        contenido += inputLine + "\n";
				    }
				    in.close();	
				    
				    Method metodo = DAO_Utils.getMetodo(callbackObject, callbackFx, contenido.getClass(), params.getClass());
					DAO_Utils.info(log, "HTTP_Service", "get", "cliente_http", "Método a ejecutar: " + metodo.getName());
					metodo.invoke(callbackObject, contenido, params);
					
				} else {
					
					Method metodo = DAO_Utils.getMetodo(callbackObject, errorCallbackFx, params.getClass());
					DAO_Utils.info(log, "HTTP_Service", "get", "cliente_http", "Método a ejecutar: " + metodo.getName());
					metodo.invoke(callbackObject);
				}
				
				
			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e1) {
			// Error de url
			e1.printStackTrace();
			
		} catch (IOException e) {
			// error de conexion
			e.printStackTrace();
		}
		
	}

}
