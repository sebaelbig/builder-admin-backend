package ar.com.builderadmin.ws.historiaClinica.pedidos;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.controllers.I_WebContextHolder;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.WebContextHolder;
import ar.com.builderadmin.controllers.core.usuarios.Admin_Usuarios;
import ar.com.builderadmin.controllers.historiaClinica.pedidos.Admin_Modalidades;
import ar.com.builderadmin.controllers.historiaClinica.pedidos.Admin_Pedidos;
import ar.com.builderadmin.controllers.historiaClinica.pedidos.estados.Admin_EstudioDePedidos;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.model.historiaClinica.pedidos.PedidoFiltrado;
import ar.com.builderadmin.utils.HTTP_Service;
import ar.com.builderadmin.utils.Mailer;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.usuarios.Mensaje_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;
import ar.com.builderadmin.ws.RespuestaCorta;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/historiaClinica/pedidos", produces = "application/json;charset=utf-8")
public class WS_Pedidos extends WS_Abstracto {

	private final Logger log = LoggerFactory.getLogger(WS_Pedidos.class);
	
	@Autowired
	private I_WebContextHolder webContextHolder;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public I_WebContextHolder webContextHolder() {
		return new WebContextHolder();
	}
	
	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_Pedidos -> ping";
	}
	
	/**
	 * Ping de disponibilidad
	 * 
	 * Aclaracion sobre espacios en blanco y ^

		PN:Te consulto respecto del apellido y nombre del paciente y del médico, en caso de tener mas de un apellido y mas de un nombre, ¿se ponen separados por ^ en el siguiente orden?:
		Apellido1^Apellido2^Nombre1^Nombre2
		Es decir, que en lugar de carácter blanco va el carácter ^
		
		
		ANGRAS:No cada ^ hace un salto de campo por lo que si pones Apellido1^Apellido2^Nombre1^Nombre2, 
		en el PACS lo reconoce como apellido nombre prefijo sufij
 		por lo que el apellido2 y nombre2 te conviene separarlo con un carácter blanco o |

	 * @return
	 */
	@RequestMapping(value = "/ping-charrua", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String pingCharrua() {
		String url = "http://172.20.32.249:8090/charrua-connector/worklist" +
					"?"+
					"PatientID=9988" +
					"&PatientName=Jorge^Lema" +
					"&PatientBD=19941109" +//yyyyMMdd
					"&PatientSex=M" +
					"&Modality=US" +
					"&ReferringPhysician=Rodriguez^Jose" +
					"&StudyDescription=ECOGRAFIA^DE^CODO^DERECHO^ADULTO" +
					"&AccessionNumber=9988" +
					"&ScheduledStation=L5-C3"+
					"&simular=false" +
					"&urlResponse=http://172.20.32.249:8090/horus_restfull/api/historiaClinica/pedidos/ping";
		
		HTTP_Service.get(url, "respuestaPingCharrua", "errorRespuestaPingCharrua", this);
		
		return "WS_Pedidos -> pingCharrua";
	}
	/**
	 * Metodo que se ejecuta a la vuelta del envio de un pedido a la Worklist
	 * 
	 * @param response un JSON con el codigo y detalle de la transaccion
	 */
	public void respuestaEnvioAWorklist(String response) {
		
		R_RespuestaCharrua resul = new Gson().fromJson(response, R_RespuestaCharrua.class);
		
		System.out.println(resul.toString());
		DAO_Utils.info(log, "Admin_Pedidos", "respuestaEnvioAWorklist", "-interno-", resul.toString());
	}
	
	/**
	 * Metodo que se ejecuta si la llamada HTTP no funciona 
	 * 
	 * @param response
	 */
	public void errorRespuestaEnvioAWorklist(String response) {
		
		R_RespuestaCharrua resul = new Gson().fromJson(response, R_RespuestaCharrua.class);
		
		DAO_Utils.error(log, "Admin_Pedidos", "errorRespuestaEnvioAWorklist", "-interno-", resul.toString());
	}
	
//	@Value("classpath:mails/historiaClinica/pedidos/mail_informeConfirmado.html")
	@Autowired
	private Mailer mailer;
	
/*	@RequestMapping(value = "/mail", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String mail() {
		
		Mail_VO mail = new Mail_VO();
	
	//	mailer.enviarMail("/historiaClinica/pedidos/mail_informeConfirmado", mail);
		
		return "{ok:true}";
	}*/
	
	/****************************************************************************************/
	/****************************************************************************************/
	@Autowired
	private Admin_Pedidos admin_Pedidos;
	
	@Autowired
	private Admin_Usuarios admin_Usuarios;
	
	@Autowired
	private Admin_Modalidades admin_Modalidades;

	@RequestMapping(value = "/estados", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String estados() {		
		System.out.println("WS_Pedidos -> estados()");
	
		Estado[] estados = {new Estado("En Atención"), new Estado("Atendido"), new Estado("Informado"), new Estado("Cancelado")};
		JSON_Respuesta respuesta = new JSON_Respuesta(new JSON_Paginador(Arrays.asList(estados)));
		
		return getGson().toJson(respuesta);
	}
	
	@RequestMapping(value = "/modalidades", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String modalidades() {		
		System.out.println("WS_Pedidos -> modalidades()");
		return this.getAdmin_Modalidades().listarTodos(this.getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/buscar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String buscar(@RequestBody String jsonPrivado) {		System.out.println("WS_Pedidos -> buscar(" + jsonPrivado + ")");
	
		Pedido_VO pedido = getGson().fromJson(jsonPrivado, Pedido_VO.class);
	
		return admin_Pedidos.buscar(pedido, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/listarDeServicio", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarDeServicio(@RequestBody String jsonServicio) {		
		System.out.println("WS_Pedidos -> listarDeServicio(" + jsonServicio + ")");
		
		Servicio_VO serv = getGson().fromJson(jsonServicio, Servicio_VO.class);
		
		return admin_Pedidos.listarDeServicio(serv, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/mensajes/{nroPedido}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String mensajes(@PathVariable String nroPedido) {		
		System.out.println("WS_Pedidos -> mensajes(" + nroPedido + ")");
		
		return admin_Pedidos.getMensajes(Integer.parseInt(nroPedido), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/mensajes", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String guardarMensaje(@RequestBody String jsonMensaje) {		
		System.out.println("WS_Pedidos -> guardarMensaje(" + jsonMensaje + ")");
		
		Mensaje_VO msj = getGson().fromJson(jsonMensaje, Mensaje_VO.class);
		
		return admin_Pedidos.guardarMensaje(msj, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/listarDePaciente/{idPedido}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listarDePaciente(@PathVariable String idPedido) {		
		System.out.println("WS_Pedidos -> listarDePaciente(" + idPedido + ")");
		
		return admin_Pedidos.listarDePaciente(Long.parseLong(idPedido), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/listarDeServicioDeUsuario", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listarDeServicioDeUsuario() {	
		
		System.out.println("WS_Pedidos -> listarDeServicioDeUsuario()");
		
		return admin_Pedidos.listarDeServicioDeUsuario(getUsuarioAccion());
	}
	/* traigo tipo+nroDni del pacienteLogeado */
	@RequestMapping(value = "/seguro/getDatosDePacienteWeb", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getDatosPacienteWeb(@RequestBody String jsonUsuario) {		
		System.out.println("WS_Pedidos -> getDatosDePacienteWeb(" + jsonUsuario + ")");
			
		return admin_Usuarios.getDatosDePacienteWeb(jsonUsuario);
	}
	
	@RequestMapping(value = "/seguro/listarPedidosPorFiltro", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String listarPedidosPorFiltro(@RequestBody String jsonPedidoFiltrado) {		
		System.out.println("WS_Pedidos -> listarPedidosPorFiltro(" + jsonPedidoFiltrado + ")");
		
		PedidoFiltrado pedidoFiltrado = getGson().fromJson(jsonPedidoFiltrado, PedidoFiltrado.class);
		
		return admin_Pedidos.listarPedidosPorFiltro(pedidoFiltrado, getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_Pedidos -> listAll()");
		
		return admin_Pedidos.listarTodos(getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/findByNro/{nro}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByNro(@PathVariable String nro) {
		System.out.println("WS_Pedidos -> findByNro(" + nro + ")");
		
		
		return admin_Pedidos.findByNro(nro,  getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/findById/{id}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String id) {
		System.out.println("WS_Pedidos -> findById(" + id + ")");
		
		return admin_Pedidos.findById(Long.parseLong(id),  getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/findByIdByEstudio/{id}/{idEstudio}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByIdByEstudio(@PathVariable String id, @PathVariable String idEstudio) {
		System.out.println("WS_Pedidos -> findByIdByEstudio(" + id + ", "+idEstudio+")");
		
		return admin_Pedidos.findByIdByEstudio(Long.parseLong(id), Long.parseLong(idEstudio), getUsuarioAccion());
	}
	/*Agrego el ws findByEstudioBloqueante para los estudios de rayos*/
	@RequestMapping(value = "/seguro/findByEstudioBloqueante/{id}/{idEstudio}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByIdByEstudioBloqueante(@PathVariable String id, @PathVariable String idEstudio) {
		System.out.println("WS_Pedidos -> findByIdByEstudioBloqueante(" + id + ", "+idEstudio+")");
		
		return admin_Pedidos.findByIdByEstudioBloqueante(Long.parseLong(id), Long.parseLong(idEstudio), getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/findByIdBloqueante/{id}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findByIdBloqueante(@PathVariable String id) {
		System.out.println("WS_Pedidos -> findByIdBloqueante(" + id + ")");
		
		return admin_Pedidos.findByIdBloqueante(Long.parseLong(id),  getUsuarioAccion());
	}
	
	@RequestMapping(value = "/seguro/desbloquear", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
	public String desbloquear(@RequestBody String jsonPrivado) {
		System.out.println("WS_Pedidos -> desbloquear(" + jsonPrivado + ")");
		
		//PedidoIds pids = getGson().fromJson(jsonPrivado, PedidoIds.class);
		PedidoIds pids=new PedidoIds(jsonPrivado);
		return admin_Pedidos.desbloquear(pids.id, pids.idEstudio, getUsuarioAccion());
	}
	
	private class PedidoIds{
		public Long id, idEstudio;
		public PedidoIds(String json){
			Pedido_VO pedido= getGson().fromJson(json, Pedido_VO.class);
			this.id=pedido.getId();
			if(pedido.getUnEstudioPorPedido()){
				this.idEstudio=pedido.getEstudiosPaciente().get(0).getId();
			}else{
				this.idEstudio=null;
			}
		}
	}
 	
	/**
	 * Posibles respuestas:
	 	1) Error al llamar al sp: sp_horus_get_pedido, No se pudo conectar con la BD
		2) Error al llamar al sp: sp_horus_get_pedido, resultado incorrecto o fuera de formato
		3*) El sp: sp_horus_get_pedido responde que No existe un pedido con el nro ingresado: nroPedido
		3) El sp: sp_horus_get_pedido responde que No existe el pedido con el nro ingresado
		4*) Error al recuperar el profesional efector llamando al sp: sp_horus_get_profesional con el nro de matricula: nroMatricula
		4) Error al recuperar el profesional efector llamando al sp: sp_horus_get_profesional
		5*) Error al recuperar el profesional solicitante llamando al sp: sp_horus_get_profesional con el nro de matricula: nroMatricula
		5) Error al recuperar el profesional solicitante llamando al sp: sp_horus_get_profesional
		6) Error al guardar el pedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: nombreEsquema )
		7*) Pedido nroPedido recibido correctamente
		7) Pedido recibido correctamente
		
		
		(Estado: En Atencion)
	 * @param nroPedido
	 * @return
	 */
	@RequestMapping(value = "/registrarNuevoPedido/{nroPedido}", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String registrarNuevoPedido(@PathVariable String nroPedido) {
		System.out.println("WS_Pedidos -> registrarNuevoPedido(" + nroPedido + ")");
		
		RespuestaCorta resp = admin_Pedidos.registrarNuevoPedido(nroPedido, getUsuarioAccion());
		
		return resp.getMensaje();
	}
	
	/**********************************************************************************/
	@Autowired
	private Admin_EstudioDePedidos admin_EstudioDePedidos;
	
	/**
	 * (Estado: Atendido)
	 * Este WS es llamado por los PACS 
	 * 
	 * http://localhost:8084/charrua-connector/worklist?urlResponse=http://localhost:8084/
charrua-connector/worklist/
response&patientID=9988&PatientName=Jorge^Lema&PatientBD=19941109&PatientSex=M&M
odality=CR&ReferringPhysician=Rodriguez^Jose&StudyDescription=RX_TORAX&AccessionNumber=9988&ScheduledStation=AET_CR
	 * 
	 * 
	 * @param nroPedido
	 * @return
	 */
	@RequestMapping(value = "/atender", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String atender(@RequestParam(value="accessionNumber") String accessionNumber, @RequestParam(value="status") String status) {
		System.out.println("WS_Pedidos -> atender(" + accessionNumber + ", "+ status +")");

		return admin_EstudioDePedidos.atender(accessionNumber, status, "PACS");
	}
	/**********************************************************************************/

	/**
	 * (Estado: Informado)
	 * 
	 * @param jsonPrivado
	 * @return
	 */
	@RequestMapping(value = "/seguro/reabrir", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String reabrir(@RequestBody String jsonPrivado) {
		System.out.println("WS_Pedidos -> reabrir(" + jsonPrivado + ")");
		
		Pedido_VO pedido = getGson().fromJson(jsonPrivado, Pedido_VO.class);
		
		return admin_Pedidos.reabrir(pedido, getUsuarioAccion());
	}
	
	/**
	 * (Estado: Informado)
	 * 
	 * @param jsonPrivado
	 * @return
	 */
	@RequestMapping(value = "/seguro/informar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String informar(@RequestBody String jsonPrivado) {
		DAO_Utils.info(log, "WS_Pedidos", "informar", getUsuarioAccion(), "WS_Pedidos -> informar(" + jsonPrivado + ")");
		
		Pedido_VO pedido = getGson().fromJson(jsonPrivado, Pedido_VO.class);
		pedido.setSolicitante(pedido.getSolicitanteRaw());
		
		return admin_Pedidos.informar(pedido, getUsuarioAccion());
	}
	
	/**
	 * Imprimir
	 * 
	 * @param jsonPrivado
	 * @return
	 */
	@RequestMapping(value = "/imprimir/pdf/{idPedido}/{idEstudio}/{timestamp}", produces = "application/pdf;", method = RequestMethod.GET)
	public byte[] imprimir(@PathVariable String idPedido, @PathVariable String idEstudio, @PathVariable String timestamp) {
		System.out.println("WS_Pedidos -> imprimir(" + idEstudio + ", "+idPedido+")");
		
		return admin_Pedidos.imprimir(Long.parseLong(idPedido), Long.parseLong(idEstudio), getUsuarioAccion());
	}
	
	
	/**
	 * (Estado: Confirmado)
	 * 
	 * @param jsonPrivado
	 * @return
	 */
	@RequestMapping(value = "/seguro/cancelar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String cancelar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Pedidos -> cancelar(" + jsonPrivado + ")");
		
		Pedido_VO pedido = getGson().fromJson(jsonPrivado, Pedido_VO.class);
		
		return admin_Pedidos.cancelar(pedido, getUsuarioAccion());
	}
	
	
	/**
	 * (Estado: Confirmado)
	 * 
	 * @param jsonPrivado
	 * @return
	 */
	@RequestMapping(value = "/seguro/confirmar", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String confirmar(@RequestBody String jsonPrivado) {
		System.out.println("WS_Pedidos -> confirmar(" + jsonPrivado + ")");
		
		Pedido_VO pedido = getGson().fromJson(jsonPrivado, Pedido_VO.class);
		
		return admin_Pedidos.confirmar(pedido, getUsuarioAccion());
	}
	/**
	 * Ultimo pedido de un paciente dado en el que coincida alguno de los estudios(idEstudio) con los estudios actuales
	 * 
	 * @param jsonPrivado
	 * @return
	 */
	@RequestMapping(value = "/seguro/ultimo", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String estudioPrevio(@RequestBody String jsonPrivado) {
		System.out.println("WS_EstudioDePedidos -> estudioPrevio(" + jsonPrivado + ")");
		
		JsonObject params = getGson().fromJson(jsonPrivado, JsonObject.class);
		Type listType = new TypeToken<List<Long>>() {}.getType();
		List<Long> idEstudios = getGson().fromJson(params.get("idEstudio"),listType);
		String numeroDniPaciente = params.get("numeroDniPaciente").getAsString();
		String tipoDniPaciente = params.get("tipoDniPaciente").getAsString();
		String numeroPedido = params.get("numeroPedido").getAsString();
		
		return admin_Pedidos.estudioPrevio(idEstudios,numeroDniPaciente,tipoDniPaciente,numeroPedido,getUsuarioAccion());
	}
	
	@RequestMapping(value="/getPedidosCtg", produces ="application/json;charset=utf-8", method= RequestMethod.POST)
	public String getPedidosCtg(@RequestBody String fecha){
		
		System.out.println("WS_Pedidos -> getPedidosCtg"+fecha);
		
		return admin_Pedidos.getPedidosEnCTG(fecha);
	}
	@RequestMapping(value="/setPedidoEnAtencion", produces ="application/json;charset=utf-8", method= RequestMethod.POST)
	public String setPedidoEnAtencion(@RequestBody String nroPedido){
		
		System.out.println("WS_Pedidos -> setPedidoEnAtencion(pedido: "+nroPedido+", usuario: "+getUsuarioAccion());
		
		return admin_Pedidos.setEstadoPedidoEnAtencion(nroPedido,getUsuarioAccion());
	}
	
	
	/**
	 * @return the webContextHolder
	 */
	public I_WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	/**
	 * @param webContextHolder
	 *            the webContextHolder to set
	 */
	public void setWebContextHolder(I_WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}

	/**
	 * @return the admin_Pedidos
	 */
	public Admin_Pedidos getAdmin_Pedidos() {
		return admin_Pedidos;
	}

	/**
	 * @param admin_Pedidos the admin_Pedidos to set
	 */
	public void setAdmin_Pedidos(Admin_Pedidos admin_Pedidos) {
		this.admin_Pedidos = admin_Pedidos;
	}
	
	/**
	 * @return the admin_Modalidades
	 */
	public Admin_Modalidades getAdmin_Modalidades() {
		return admin_Modalidades;
	}

	/**
	 * @param admin_Modalidades the admin_Modalidades to set
	 */
	public void setAdmin_Modalidades(Admin_Modalidades admin_Modalidades) {
		this.admin_Modalidades = admin_Modalidades;
	}

	private Gson getGson(){
		return new Gson();
	}

	class Estado{
		private String nombre;

		public Estado(String n){this.nombre=n;}

		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
	}
}