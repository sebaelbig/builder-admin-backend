package ar.com.builderadmin.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.utils.comparators.core.PrettyPrint;

public abstract class Admin_Abstracto<I_ValueObject> {
	
	
	/**
	 * Logger.
	 */
	protected final Logger log = LoggerFactory.getLogger(Admin_Abstracto.class);
	
	protected Long idElemento;

	protected String jsonElemento;

	protected abstract Paginador<I_ValueObject> getPaginador();

	protected abstract JSON_Paginador getJson_paginador();

	protected abstract DAO<I_ValueObject> getDao();

	/**
	 * Prepara la funcion para crear una entidad 
	 * 
	 * @param vo ValueObject a crear
	 * @param usr Usuario que impulsa la acción
	 * @return FX La Funcion instanciada para ejecutar 
	 */
	protected abstract I_FX getFX_Crear(I_ValueObject vo, String usr);

	/**
	 * Prepara la funcion para modificar
	 * 
	 * @param vo ValueObject a modificar
	 * @param usr Usuario que impulsa la acción
	 * @return FX La Funcion instanciada para ejecutar
	 */
	protected abstract I_FX getFX_Modificar(I_ValueObject vo, String usr);

	/**
	 * Prepara la funcion para eliminar
	 * 
	 * @param vo ValueObject a modificar
	 * @param usr Usuario que impulsa la acción
	 * @return FX La Funcion instanciada para ejecutar
	 */
	protected abstract I_FX getFX_Eliminar(I_ValueObject vo, String usr);

	/**
	 * Prepara la funcion para buscar
	 * 
	 * @param vo ValueObject con los parámetros a buscar
	 * @param usr Usuario que impulsa la acción
	 * @return FX La Funcion instanciada para ejecutar
	 */
	protected abstract I_FX getFX_Buscar(I_ValueObject vo, String usr);
	
	protected abstract Admin_Alertas getAdminAlertas();

	public String crear(I_ValueObject vo, String usr) {
		I_FX funcion = getFX_Crear(vo, usr);

		return ejecutarFuncion(funcion);
	}

	public String modificar(I_ValueObject vo, String usr) {
		I_FX funcion = getFX_Modificar(vo, usr);

		return ejecutarFuncion(funcion);
	}

//	public String eliminar(Integer pagina, Integer cantidad, I_ValueObject vo, String usr) {
	public String eliminar(I_ValueObject vo, String usr) {
		I_FX funcion = getFX_Eliminar(vo, usr);

		return ejecutarFuncion(funcion);
	}

	protected String listar(Integer pagina, Integer cantidad) {
		return paginadorToJSON(listarElementos(pagina, cantidad));
	}

	public String listarTodos(String usuario) {
		DAO_Utils.info(log, getClass().getSimpleName(), "listarTodo", usuario, "Se listan todos los: "+this.getClass().getSimpleName());
		
		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try{
			
			JSON_Paginador paginador = listarElementos(0,-1);
			
			jsonResp.setMensaje("Se listan todos los: "+this.getClass().getSimpleName());
			jsonResp.setOk(true);
			jsonResp.setPaginador(paginador);
			
		}catch(Exception e){
			
			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "+this.getClass().getSimpleName());
			
			e.printStackTrace();
			
			DAO_Utils.error(log, getClass().getSimpleName(), "listarTodo", usuario, "Se listan todos los: "+this.getClass().getSimpleName());		}
		
		return new Gson().toJson(jsonResp);
	}
	
	/**
	 * Busca sin paginar
	 * 
	 * @param json_elemento
	 * @return
	 */
	public String buscar(I_ValueObject vo, String usr) {

		I_FX funcion = getFX_Buscar(vo,usr);
		
		return ejecutarFuncion(funcion);
	}
	
	public String buscar(Integer pagina, Integer cantidad, I_ValueObject vo, String usr) {
		getPaginador().iniciar(pagina, cantidad);

		I_FX funcion = getFX_Buscar(vo, usr);

		return ejecutarFuncion(funcion);
	}

	public String findById(Long id, String usuarioAccion) {
		Object obj = this.getDao().findById(id);
		
		I_ValueObject vo = (I_ValueObject) DAO_Utils.entityToValueObject(obj);
		
		DAO_Utils.info(this.log, this.getClass().getName(), "findById", usuarioAccion, "Se recupera la entidad con id: "+id);
		
		return getGson().toJson(vo);
	}
	
	protected String ejecutarFuncion(I_FX funcion) {
		
		long inicio = new Date().getTime();
		JSON_Respuesta respuesta = funcion.ejecutar(getAdminAlertas());
		
		if (respuesta.getOk().booleanValue() 
				&& funcion.listar()) {
			
			respuesta.setPaginador(
					listarElementos(
							getPaginador().getPaginaActual(), 
							getPaginador().getCantPorPagina()
					)
			);
			
		}
		
		long fin = new Date().getTime();
		if (fin-inicio>10000){
			DAO_Utils.info(log, "Admin_Abstracto", "ejecutarFuncion",funcion.getUsuario(), "---------------->>> OJO!!! '"+funcion.getClass().getSimpleName()+"' TARDA DEMASIADO!!!! "+DAO_Utils.formatExactlyHour(new Date(fin-inicio)));
		}
		
		return new Gson().toJson(respuesta);
	}

	public String ejecutarFuncion(I_FX funcion, Gson gson) {
		JSON_Respuesta respuesta = funcion.ejecutar(getAdminAlertas());
		if (respuesta.getOk().booleanValue()) {
			respuesta.setPaginador(listarElementos(getPaginador()
					.getPaginaActual(), getPaginador().getCantPorPagina()));
		}
		return gson.toJson(respuesta);
	}

	public JSON_Paginador listarElementos(Integer pagina, Integer cantidad) {
		Paginador<I_ValueObject> pag = getPaginador();
		pag.iniciar(pagina, cantidad);

		pag.listar(getDao());

		JSON_Paginador json_paginador = getJson_paginador();
		json_paginador.setHayPaginaAnterior(pag.getHayAnterior());
		json_paginador.setHayPaginaSiguiente(pag.getHaySiguiente());
		json_paginador.setPaginaActual(pag.getPaginaActual());
		json_paginador.setTotalPaginas(pag.getTotalPaginas());
		json_paginador.setElementos(pag.getElementos());
		json_paginador.setMensaje(pag.getMensaje());

		return json_paginador;
	}

	
	protected String paginadorToJSON(JSON_Paginador json_paginador) {
		Gson gson = getGson();

		String resul = gson.toJson(json_paginador);

		return resul;
	}

//	public String guardarAuditar(String json_especialidad) {
//		return crear(json_especialidad);
//	}

	public static String capitalizarString(String s) {
		String[] aCapitalizar = s.split(" ");

		String[] temps = new String[aCapitalizar.length];
		for (int i = 0; i < aCapitalizar.length; i++) {
			String word = aCapitalizar[i].toLowerCase();
			String temp = word.substring(0, 1).toUpperCase()
					+ word.substring(1);
			temps[i] = temp;
		}
		String resul = "";
		for (int i = 0; i < temps.length; i++) {
			resul = resul + temps[i] + " ";
		}
		return resul.trim();
	}


//	public abstract String[] buscar(String atributoEntidad, String valorABuscar);
	
	private static final Integer CANTIDAD_DATOS_POR_ELEMENTO = 3;
	
	/**
	 * 	Armo el arreglo de String que utilizara la vista para mostrar los elementos
	 * 
	 * Posicion:
	 * 1) HTML que se mostrara en la lista de seleccion
	 * 2) JSON que representa al elemento
	 * 3) LABEl que se copiara en el input asociado
	 *  
	 * @param elementos Elementos a transformar 
	 * @param valorABuscar El valor que se utilizo en la busqueda de los elementos
	 * 
	 * @return Un arreglo de String que contiene los datos de los elementos del resultado
	 */
	protected String[] armarResultado(List elementos, String valorABuscar){
		
		String[] arregloResul = new String[elementos.size()*CANTIDAD_DATOS_POR_ELEMENTO];
		String label, valor;
		
		Gson gson = this.getGson();
		
		for (int i=0; i<elementos.size(); i++) {
			valor = elementos.get(i).toString();
			
			label = PrettyPrint.resaltarHTMLConValor(valor, valorABuscar);
			arregloResul[i*CANTIDAD_DATOS_POR_ELEMENTO] 	= label;
			
			arregloResul[i*CANTIDAD_DATOS_POR_ELEMENTO+1]	= gson.toJson(elementos.get(i));
			
			arregloResul[i*CANTIDAD_DATOS_POR_ELEMENTO+2]	= valor;
			
		}
		
		return arregloResul;
	}

	//Si se requiere de un gson especifico lo debera suministrar el admin que extienda esta clase
	protected Gson getGson() {
		return new Gson();
	}

	private static String esquema;
	
	public String generarNroDocumentoProvisional() {
		return "P" + new SimpleDateFormat("yyyy").format(new Date()) +
				this.getDao().getEntityManager().createNativeQuery("SELECT nextval('"+esquema+".nro_provisional')").getSingleResult().toString();
	}

	public static Long getNextAlertaID(EntityManager em) {
		return Long.parseLong(
				em.createNativeQuery("SELECT nextval('"+esquema+".seq_alerta_webSocket')").getSingleResult().toString());
		
	}

}