package ar.com.builderadmin.controllers.historiaClinica.pedidos;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.DBPropertiesReader;
import ar.com.builderadmin.dao.core.DAO_Parametro;
import ar.com.builderadmin.dao.core.areas.servicios.DAO_Servicio;
import ar.com.builderadmin.dao.core.templates.DAO_Templates;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.dao.core.usuarios.roles.pacientes.DAO_Paciente;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Modalidad;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Pedido;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Pedido.R_GetMensajes;
import ar.com.builderadmin.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_AtenderPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_BuscarPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_CancelarPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_ConfirmarPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_CrearPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_InformarPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.FX_ReabrirPedido;
import ar.com.builderadmin.fx.historiaClinica.pedidos.estudios.ItemLista;
import ar.com.builderadmin.model.core.DatosAccion;
import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.model.historiaClinica.pedidos.EstudioDePedidoAngra;
import ar.com.builderadmin.model.historiaClinica.pedidos.Pedido;
import ar.com.builderadmin.model.historiaClinica.pedidos.PedidoFiltrado;
import ar.com.builderadmin.utils.Angra_Service;
import ar.com.builderadmin.utils.HTTP_Service;
import ar.com.builderadmin.utils.Mailer;
import ar.com.builderadmin.utils.PdfUtils;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.templates.PropiedadTemplate_VO;
import ar.com.builderadmin.vo.core.templates.Template_VO;
import ar.com.builderadmin.vo.core.usuarios.Mensaje_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;
import ar.com.builderadmin.ws.RespuestaCorta;

/**
 * Componente para el manejo de las pedidos
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Pedidos extends Admin_Abstracto<Pedido_VO> implements
		Serializable {

	private final Logger log = LoggerFactory.getLogger(Admin_Pedidos.class);
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Pedido_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Pedido dao_Pedido;
	
	@Autowired
	private DAO_Modalidad dao_Modalidad;

	@Autowired
	private Admin_Alertas admin_Alertas;

	@Autowired
	private Mailer mailer;
	
	@Autowired
	private PdfUtils pdfUtils;
	
	private Pedido_VO pedido;

	private JSON_Respuesta json_respuesta;

	private static final String SERVICIO_NO_EXISTE = "El servicio del pedido NO existe en Horus";
	
	private static final String FORMATO_FECHA_PEDIDO = "horus_formato_fecha_pedido";
	private static final String FORMATO_FECHA_PEDIDA_PEDIDO = "horus_formato_fecha_pedida_pedido";
	private static final String CANTIDAD_ESTUDIOS_POR_PEDIDO = "horus_cantidad_estudios_por_pedido";

	/**
	 * Posibles respuestas:
	 	1) Error al llamar al sp: sp_horus_get_pedido, No se pudo conectar con la BD
		2) Error al llamar al sp: sp_horus_get_pedido, resultado incorrecto o fuera de formato
		3) El sp: sp_horus_get_pedido responde que No existe el pedido con el nro ingresado
		4) Error al recuperar el profesional efector llamando al sp: sp_horus_get_profesional
		5) Error al recuperar el profesional solicitante llamando al sp: sp_horus_get_profesional
		6) Error al guardar el pedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: nombreEsquema )
		7) Pedido recibido correctamente
		
		(Estado: En Atencion)
	 * @param nroPedido
	 * @return
	 */
	public static final String FALLO_EN_CONEXION_A_BD= "Error al llamar al sp: sp_horus_get_detallepedido, No se pudo conectar con la BD";
	public static final String RESULTADO_INCORRECTO = "Error al llamar al sp: sp_horus_get_detallepedido, resultado incorrecto o fuera de formato";
	public static final String PEDIDO_INEXISTENTE= "El sp: sp_horus_get_detallepedido responde que No existe el pedido con el numero ingresado.";
	public static final String ERROR_RECUPERAR_EFECTOR = "Error al recuperar el profesional efector llamando al sp: sp_horus_get_profesional.";
	public static final String ERROR_RECUPERAR_SOLICITANTE = "Error al recuperar el profesional solicitante llamando al sp: sp_horus_get_profesional.";
	public static final String ERROR_RECUPERAR_PACIENTE = "Error al recuperar el paciente";
	public static final String PEDIDO_YA_EXISTE = "Debe ingresar un nro o el pedido ya existe.";
	public static final String ERROR_GUARDAR_PEDIDO_HORUS = "Error al guardar el pedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: "+DBPropertiesReader.getEsquema()+" )";
	private static final String PEDIDO_RECIBIDO = "Pedido recibido correctamente.";
	private static final String MUCHOS_ESTUDIOS = "Supero la cantidad de estudios posibles";


	@Autowired
	private DAO_Parametro daoParametros;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Pedido_VO pedido, String usr) {
		return new FX_BuscarPedido(getDao(), pedido, usr);
	}

	/*******************************************************
	 * Por el momento no se pueden crear/elimnar/modificar
	 */
	@Override
	protected I_FX getFX_Crear(Pedido_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Pedido_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Pedido_VO pedido, String usr) {
		return null;
	}

	/***************************************************************/

	// MOCK
	@Override
	public String listarTodos(String usuario) {
		DAO_Utils.info(log, "Admin_Pedidos", "listarTodo",usuario,
				"Se listan todos los: pedidos");

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {

			List<Pedido_VO> pedidos = getDao_Pedido().listarTodo();
			List<Pedido_VO> pedidosProcesados = new ArrayList<>();
			
			procesarEstudiosDePedido(pedidos, pedidosProcesados);
			
			JSON_Paginador pag = new JSON_Paginador();
			pag.setElementos(pedidosProcesados);
			pag.setHayPaginaAnterior(false);
			pag.setHayPaginaSiguiente(false);
			pag.setMensaje("Se listaron los pedidos correctamente");
			pag.setPaginaActual(1);
			pag.setTotalPaginas(1);
			pag.setCantPorPagina(pedidos.size());

			jsonResp.setPaginador(pag);
			jsonResp.setMensaje("Se listaron los pedidos correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ "Admin_Pedidos");

			DAO_Utils.error(log, "Admin_Pedidos", "[listarTodo]",
					usuario, "Se listan todos los: "
							+ "Admin_Pedidos");
		}

		return new Gson().toJson(jsonResp);
	}

	private void procesarEstudiosDePedido(List<Pedido_VO> pedidos,
			List<Pedido_VO> pedidosProcesados) {
		
		//Proceso los pedidos, y si son de un estudio por pedido, multiplico el pedido y le dejo solo un estudio
		for (Pedido_VO ped : pedidos) {
			
			if (ped.getUnEstudioPorPedido()){
				//Debo agregar un pedido por estudio
				
				List<EstudioDePedido_VO> estudios = ped.getEstudiosPaciente();
				Pedido_VO pedidoConUnEstudio = null;
				for (EstudioDePedido_VO ep : estudios) {
					
					//Hago un pedido por Estudio
					pedidoConUnEstudio = ped.clonarConEstudio(ep);
					pedidoConUnEstudio.setEstudios(formatearEstudios(pedidoConUnEstudio));
					pedidoConUnEstudio.setEstadoEstudio(ep.getEstado());
					//Al ser un pedido por estudio, el bloqueado lo toma del estudio
					pedidoConUnEstudio.setBloqueado(ep.getBloqueado());
					pedidoConUnEstudio.setDatosAccion(ep.getDatosAccion());
					
					pedidosProcesados.add(pedidoConUnEstudio);
				}
				
				
			}else{
				//El servicio del pedido informa TODOS los estudios a la vez
				
				ped.setEstudios(formatearEstudios(ped));
				pedidosProcesados.add(ped);

				//Siempre un pedido tiene al menos un estudio 
				try{
					ped.setEstadoEstudio(ped.getEstudiosPaciente().get(0).getEstado());
				}catch(IndexOutOfBoundsException indexE){
					DAO_Utils.error(log, "Admin_Pedidos", "procesarEstudiosDePedido", "-interno-", "El pedido "+ ped.getNumero()+" NO TIENE ESTUDIOS!!!!");
				}
			}
			
		}
	}

	public String findByNro(String nroPedido, String usuarioAccion) {
		Pedido_VO vo = this.getDao_Pedido().findById(nroPedido);

		DAO_Utils.info(this.log, "Admin_Pedidos",
				"findById", usuarioAccion, "Se recupera la entidad con id: "
						+ nroPedido);

		return getGson().toJson(vo);
	}

	/***************************************************************/

	/**
	 * @return the dao_Pedido
	 */
	public DAO_Pedido getDao_Pedido() {
		return dao_Pedido;
	}

	/**
	 * @param dao_Pedido
	 *            the dao_Pedido to set
	 */
	public void setDao_Pedidos(DAO_Pedido dao_Pedido) {
		this.dao_Pedido = dao_Pedido;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Pedido_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Pedido_VO> getDao() {
		return getDao_Pedido();
	}

	/**
	 * @return the admin_Alertas
	 */
	public Admin_Alertas getAdmin_Alertas() {
		return admin_Alertas;
	}

	/**
	 * @param admin_Alertas
	 *            the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
	}

	/**
	 * @return the pedido
	 */
	public Pedido_VO getPedido() {
		return pedido;
	}

	/**
	 * @param pedido
	 *            the pedido to set
	 */
	public void setPedido(Pedido_VO pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return the json_respuesta
	 */
	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	/**
	 * @param json_respuesta
	 *            the json_respuesta to set
	 */
	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	/**
	 * @param paginador
	 *            the paginador to set
	 */
	public void setPaginador(Paginador<Pedido_VO> paginador) {
		this.paginador = paginador;
	}
	
	/**
	 * Re-abre un pedido  (Estado: En Atencion)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String reabrir(Pedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, "Admin_Pedidos", "reabrir",usuarioAccion,
				"Se reabrirá: " + "Admin_Pedidos");
		
		FX_ReabrirPedido fx_modif = new FX_ReabrirPedido(this.getDao_Pedido(), pedido, usuarioAccion);
		
		return ejecutarFuncion(fx_modif);
	}
	
	/**
	 * Informa un pedido  (Estado: Informado)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String informar(Pedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, "Admin_Pedidos", "informar", usuarioAccion,
				"Se informará: " + "Admin_Pedidos");
		
		FX_InformarPedido fx_inf = new FX_InformarPedido(this.getDao_Pedido(), pedido, usuarioAccion);
		
		//Si es de un estudio por pedido, le paso el id del primer estudio
		if (pedido.getUnEstudioPorPedido())
			fx_inf.setIdEstudio(pedido.getEstudiosPaciente().get(0).getId());
			
		return ejecutarFuncion(fx_inf);
	}
	
	/**
	 * Cancela un pedido  (Estado: Cancelado)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String cancelar(Pedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, "Admin_Pedidos", "cancelar",usuarioAccion,
				"Se cancelará: " + "Admin_Pedidos");
		
		FX_CancelarPedido fx_modif = new FX_CancelarPedido(this.getDao_Pedido(), pedido, usuarioAccion);
		
		return ejecutarFuncion(fx_modif);
	}
	
	/**
	 * Confirmar un pedido  (Estado: confirmar)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String confirmar(Pedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, "Admin_Pedidos", "confirmar", usuarioAccion,
				"Se confirmará el pedido: " + pedido.getNumero());

		FX_ConfirmarPedido fx_modif = new FX_ConfirmarPedido(this.getDao_Pedido(), pedido, usuarioAccion, pdfUtils, mailer );
		
		return ejecutarFuncion(fx_modif);
	}
	
	
	/**
	 * Atiende un pedido (Estado: Atendido)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String atender(Pedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, "Admin_Pedidos", "atender",usuarioAccion,
				"Se atenderá el pedido: " + pedido.getNumero());
		
		FX_AtenderPedido fx_modif = new FX_AtenderPedido(this.getDao_Pedido(), pedido, usuarioAccion);
		
		return ejecutarFuncion(fx_modif);
	}
	

	public String atender(String nroPedido, String nroEstudio,
			String usuarioAccion) {
		
		//Recupero el estudio y pedido
		
		return null;
	}

	/**
	 * Registra un nuevo pedido (Estado: En Atencion)
	 * 
	 * @param nroPedido
	 * @param usuarioAccion
	 * @return
	 */
	public RespuestaCorta registrarNuevoPedido(String nroPedido, String usuarioAccion) {
		
		RespuestaCorta resul = null;
		
		DAO_Utils.info(this.log, "Admin_Pedidos",
				"registrarNuevoPedido", usuarioAccion,
				"Se recupera el pedido con el número: " + nroPedido);
		
		// Recupero los datos del Pedido, según el número que llegó
		Pedido_VO vo = this.getDao_Pedido().findById(nroPedido);
		
		// Si se encontró todo OK, me guardo el pedido
		if (!vo.getNumero().equalsIgnoreCase("ERROR")) {
			
//			if(vo.getModalidad()!=null){
//				//Si el pedido tiene modalidad,me fijo si no existe en el sistema,y la agrego
//				List<Modalidad_VO> modalidades = this.getDao_Modalidad().listarTodo();
//				Modalidad_VO modalidad_vo = null;
//				
//				for (int i = 0; (i < modalidades.size()&& modalidad_vo == null); i++) {
//					if(modalidades.get(i).getCodigo().equals(vo.getModalidad())){
//						modalidad_vo=modalidades.get(i);
//					}
//				}
//				if(modalidad_vo==null){
//					modalidad_vo = new Modalidad_VO();
//					modalidad_vo.setCodigo(vo.getModalidad());
//					this.getDao_Modalidad().guardar(modalidad_vo);
//				}
//			}
			
			//Me fijo que la cantidad de estudios establecida en los parametros no supere la cantidad
			Integer cantEstudios = Integer.parseInt(daoParametros.findValueByNombre(CANTIDAD_ESTUDIOS_POR_PEDIDO, E_TipoParametro.INTEGER));

			if (vo.getEstudiosPaciente().size()>cantEstudios){
				
				resul = new RespuestaCorta(false, Admin_Pedidos.MUCHOS_ESTUDIOS);
			
			}else{
				
				this.setearServicioDeHE(vo, usuarioAccion);
				
				vo.setEstudios(this.formatearEstudios(vo));
				
				if (vo.getIdServicio() != null){
					
					//Obtengo de la tabla el formato
					String formatDate = daoParametros.findValueByNombre(FORMATO_FECHA_PEDIDO, E_TipoParametro.STRING);
					
					vo.setDt_fecha(new Date());
					vo.setFecha(new SimpleDateFormat(formatDate).format(vo.getDt_fecha()));
					
					vo.setEstado(Pedido.EN_ATENCION);
					// seteo el campo dt_fecha, que lo voy a necesitar para los filtros de búsqueda de pedidos
					
					//Creo el pedido
					FX_CrearPedido fx_crearPedido = new FX_CrearPedido(
							this.getDao_Pedido(), vo, usuarioAccion);
					JSON_Respuesta jsonResp = fx_crearPedido.ejecutar(getAdminAlertas());
					
					if (jsonResp.getOk()){
						
						DAO_Utils.info(this.log, "Admin_Pedidos" ,
								"registrarNuevoPedido", usuarioAccion,
								"Se creó el pedido en HORUS con el número: " + nroPedido);
						
						/* ANGRA - WORKLIST */
						DAO_Utils.info(this.log, "Admin_Pedidos" ,
								"registrarNuevoPedido", usuarioAccion,
								"Se enviará a Angra-Worklist el pedido: " + nroPedido);
						
						this.enviarPedidoAAngra(vo);//Es asincrono
						
						resul = new RespuestaCorta(true, PEDIDO_RECIBIDO);
						
					}else{
						
						resul = new RespuestaCorta(false, jsonResp.getMensaje());
					}
					
				}else{
					resul = new RespuestaCorta(true, SERVICIO_NO_EXISTE);
				}
			}
			
		}else{
			resul = new RespuestaCorta(false, vo.getPaciente());//En el paciente esta el mensaje
		}
		
		return resul;

	}

	/**
	 * Envia el pedido a Angra
	 * @param vo
	 */
	private void enviarPedidoAAngra(Pedido_VO vo) {
		
		DAO_Paciente daoPac = getDao().getInstance(DAO_Paciente.class);
		
		for (EstudioDePedido_VO ep : vo.getEstudiosPaciente()) {
			
			//Crep la URL
			String url = Angra_Service.crearURLWorklist(vo, ep, daoPac, daoParametros);

			//Calculo el accesion number
			String accesionNumber = Angra_Service.getIDEstudioParaANGRA(ep, daoParametros);
			
			//Creo el estudio pedido angra
			EstudioDePedidoAngra pa = new EstudioDePedidoAngra(vo.getId(), ep.getId(), accesionNumber, url);
			pa = getDao_Pedido().guardarEstudioPedidoAngra(pa);
			
			//TODO Enviar por WebSocket el EPA
			
			//Llamo a para agregar el pedido a la Worklist
			HTTP_Service.get(url, "respuestaEnvioAWorklist", "errorRespuestaEnvioAWorklist", this, pa.getId().toString());
			
		}
		
	}
	
	/**
	 * Metodo que se ejecuta a la vuelta del envio de un pedido a la Worklist
	 * 
	 * @param response un JSON con el codigo y detalle de la transaccion
	 */
	public void respuestaEnvioAWorklist(String response, String... idEstudioPedidoAngra) {
		
		//Actualizo el pedido Angra con la respuesta
		EstudioDePedidoAngra pa = getDao_Pedido().recuperarEstudioPedidoAngra(Long.parseLong(idEstudioPedidoAngra[0]));
		
		pa.setRespuestaWorklist(response);
		pa.setFechaRespuesta(new Date());
		
		getDao_Pedido().guardarEstudioPedidoAngra(pa);
		
		//TODO Enviar por WebSocket el EPA completo
	}
	
	/**
	 * Metodo que se ejecuta si la llamada HTTP no funciona 
	 * 
	 * @param response
	 */
	public void errorRespuestaEnvioAWorklist(String response, String... idEstudioPedidoAngra) {
		
		//Actualizo el pedido Angra con la respuesta
		EstudioDePedidoAngra pa = getDao_Pedido().recuperarEstudioPedidoAngra(Long.parseLong(idEstudioPedidoAngra[0]));
		
		pa.setRespuestaWorklist(response);
		pa.setFechaRespuesta(new Date());
		
		getDao_Pedido().guardarEstudioPedidoAngra(pa);
		
		//TODO Enviar por WebSocket el EPA
	}
	
	
	private void setearServicioDeHE(Pedido_VO vo, String usuarioAccion) {
		
		DAO_Servicio daoSrv = this.getDao_Pedido().getInstance(DAO_Servicio.class);
		
		Servicio_VO srv = (Servicio_VO) daoSrv.buscarPorNombre(vo.getNombreServicio()).get(0);
		
		if ( srv!=null ){
			vo.setIdServicio(srv.getId());
			vo.setUnEstudioPorPedido(srv.getUnEstudioPorPedido());
		}
		
	}

	private String formatearEstudios( Pedido_VO vo) {
		
		String resul = "";
		
//		if (!vo.getUnEstudioPorPedido()){
			
			for (EstudioDePedido_VO itemPedido : vo.getEstudiosPaciente()) {
				resul += itemPedido.getNombreEstudio() + " ; ";
			}
			
//		}

		return (resul.length()>0)?resul.substring(0, resul.length() - 2):"";
	}

	public RespuestaCorta pedidoAtendido(String nroPedido, String usuarioAccion) {
		RespuestaCorta resul = null;
		
		// Recupero los datos del Pedido, según el número que llegó
		Pedido_VO vo = this.getDao_Pedido().findById(nroPedido);
		
		DAO_Utils.info(this.log, "Admin_Pedidos",
				"pedidoAtendido", usuarioAccion,
				"Se recupera el pedido con el numero: " + nroPedido);
		
		FX_AtenderPedido fx_modif = new FX_AtenderPedido(this.getDao_Pedido(), vo, usuarioAccion);
		
		JSON_Respuesta jsonResp = fx_modif.ejecutar(getAdminAlertas());
		
		if (jsonResp.getOk())
			DAO_Utils.info(this.log, "Admin_Pedidos" ,
				"pedidoAtendido", usuarioAccion,
				"Se marcó como atendido el pedido con el numero: " + nroPedido);
		
		if (!jsonResp.getOk()){
			resul = new RespuestaCorta(false, jsonResp.getMensaje());
		}else{
			resul = new RespuestaCorta(true, "Pedido atendido correctamente.");
		}
		
		return resul;
	}
	
	@Override
	public String findById(Long idPedido, String usuarioAccion) {
		
		//Recupero el pedido, sino tiene firma, la intento recuperar
		Pedido_VO vo = (Pedido_VO) DAO_Utils.entityToValueObject(getDao().findById(idPedido));
		
		/*
		 * La firma la tomo del pedido en vez de la asociada al profesional actuante (en HE)
		 * 
		DAO_FirmaProfesional daoFirma = getDao().getInstance(DAO_FirmaProfesional.class);
		FirmaProfesional_VO f = daoFirma.recuperarEntidadDeUsuario(vo.getMatriculaProfesionalActuante());
		if (f!=null)
			vo.setFirmaEfector(f.getTexto());
		else
			vo.setFirmaEfector("El profesional no tiene firma asociada.");
		 */
		
		return this.getGson().toJson(vo);
	}
	
	public String findByIdByEstudio(Long idPedido, Long idEstudio, String usuarioAccion) {
		
		Pedido_VO pedido = findPedido(idPedido, idEstudio);
		
		return this.getGson().toJson(pedido);
	}
	
	/**
	 * 
	 * 	Recuper el pedido y lo bloquea
	 * 
	 * @param idPedido
	 * @param idEstudio
	 * @return
	 */
//	private Pedido_VO findPedidoBloqueante(Long idPedido, Long idEstudio) {
//		
//		//Recupero el pedido
//		Pedido_VO pedido = (Pedido_VO) DAO_Utils.entityToValueObject(getDao().findById(idPedido));
//
//		if (pedido.getUnEstudioPorPedido()){
//			
//			//Me quedo SOLO con el estudio pedido
//			List<EstudioDePedido_VO> ests = new ArrayList<>();
//			for (EstudioDePedido_VO ep : pedido.getEstudiosPaciente()) {
//				
//				if (ep.getId().equals(idEstudio)){
//					
//					ests.add(ep);
//					pedido.setTexto(ep.getTexto());
//					pedido.setEstudiosPaciente(ests);
//					
//					pedido.setBloqueado(ep.getBloqueado());
//					pedido.setUsuarioAccion(ep.getUsuarioAccion());
//					pedido.setDetalleAccion(ep.getDetalleAccion());
//					pedido.setTimestampAccion(ep.getTimestampAccion());
//					
//					break;
//				}
//			}
//			
//		}else{
//			//Si NO es un estudio por pedido, lo bloqueo
//			pedido = (Pedido_VO) this.getDao().bloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Bloqueó el pedido: "+pedido.getNumero()));
//		}
//		
//		return pedido;
//	}
	
	/**
	 * 	Recupera el pedido
	 * 
	 * @param idPedido
	 * @param idEstudio
	 * @return
	 */
	private Pedido_VO findPedido(Long idPedido, Long idEstudio) {
		//Recupero el pedido, sino tiene firma, la intento recuperar
		Pedido_VO pedido = (Pedido_VO) DAO_Utils.entityToValueObject(getDao().findById(idPedido));
		
		if (pedido.getUnEstudioPorPedido()){
			//Me quedo SOLO con el estudio pedido
			List<EstudioDePedido_VO> ests = new ArrayList<>();
			for (EstudioDePedido_VO ep : pedido.getEstudiosPaciente()) {
				
				if (ep.getId().equals(idEstudio)){
					
					ests.add(ep);
					pedido.setTexto(ep.getTexto());
					pedido.setEstudiosPaciente(ests);
					
					break;
				}
			}
		}
		return pedido;
	}

	public byte[] imprimir(Long idPedido, Long idEstudio, String usuarioAccion) {
		
		Pedido_VO pedido = this.findPedido(idPedido, idEstudio);
		
		//Creo un archivo
		File archivoPDF = crearPDFDeEstudio(pedido);
		
		if (archivoPDF!=null){
			
			//Transformo el archivo a bytes
	    	try {
				return Files.readAllBytes(archivoPDF.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	    	
		}else{
			return null;
		}
	}

	public String listarDeServicio(Servicio_VO serv, String usuarioAccion) {

		List<Pedido_VO> pedidos = this.getDao_Pedido().listarDeServicio(serv, usuarioAccion);
		List<Pedido_VO> procesados = new ArrayList<>();
		
		procesarEstudiosDePedido(pedidos, procesados);
		
		JSON_Paginador pag = new JSON_Paginador(procesados);
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}

	public String listarDeServicioDeUsuario(String usuario) {
		
		//Obtengo todos los servicios del usuario 
		DAO_Usuario daoUsr = this.getDao().getInstance(DAO_Usuario.class);
		List<Servicio_VO> servicio = daoUsr.listarServiciosDeUsuario(usuario);

		List<Pedido_VO> todos = new ArrayList<>();
		List<Pedido_VO> temp;
		//Por cada servicio traigo los templates
		for (Servicio_VO servicio_VO : servicio) {
			temp = this.getDao_Pedido().listarDeServicio(servicio_VO, usuario);
			todos.addAll(temp);
		}
		
		List<Pedido_VO> procesados = new ArrayList<>();
		procesarEstudiosDePedido(todos, procesados);
		JSON_Paginador pag = new JSON_Paginador(procesados);
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}

	public String listarPedidosPorFiltro(PedidoFiltrado pedidoFiltrado, String usuarioAccion) {
		
		List<Pedido_VO> pedidos = this.getDao_Pedido().listarPedidosPorFiltro(pedidoFiltrado, usuarioAccion);
		
		List<Pedido_VO> procesados = new ArrayList<>();
		
		procesarEstudiosDePedido(pedidos, procesados);
		
		JSON_Paginador pag = new JSON_Paginador(procesados);
		if (pedidoFiltrado.getPagina()!=null)
			pag.setPaginaActual(pedidoFiltrado.getPagina());
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}

	public String listarDePaciente(Long idPedido, String usuarioAccion) {
		//Recupero el pedido
		Pedido_VO vo = (Pedido_VO) DAO_Utils.entityToValueObject(getDao().findById(idPedido));
		
		List<Pedido_VO> pedidos = this.getDao_Pedido().listarDePaciente(vo.getNroDniPaciente(), vo.getTipoDniPaciente(), usuarioAccion);
		
		List<Pedido_VO> procesados = new ArrayList<>();
		
		procesarEstudiosDePedido(pedidos, procesados);
		
		JSON_Paginador pag = new JSON_Paginador(procesados);
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		resp.setOk(true);
		return new Gson().toJson(resp);
		
	}

	public String getMensajes(int nroPedido, String usuarioAccion) {
		
		R_GetMensajes procesados = getDao_Pedido().getMensajes(nroPedido);
		
		JSON_Respuesta resp = (procesados.getOk())?
				new JSON_Respuesta(new JSON_Paginador(procesados.getMsjs()))
				:new JSON_Respuesta(procesados.getMensaje());
				
		return new Gson().toJson(resp);
	}
	
	public String guardarMensaje(Mensaje_VO mensaje, String usuarioAccion) {
		
		R_GetMensajes procesados = getDao_Pedido().guardarMensaje(mensaje);
		
		JSON_Respuesta resp = (procesados.getOk())?
				new JSON_Respuesta(new JSON_Paginador(procesados.getMsjs()))
				:new JSON_Respuesta(procesados.getMensaje());
				
		return new Gson().toJson(resp);
	}

	/**
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public File crearPDFDeEstudio(Pedido_VO pedido) {
		
		//Obtengo el template del servicio del pedido
		Template_VO template = this.getTemplateDeServicio(pedido.getNombreServicio());
		
		//Armo la lista de items a imprimir
		List<ItemLista> listaAImprimir = new ArrayList<>();
		ItemLista item = null;
		for ( PropiedadTemplate_VO propTemp : template.getPropiedades()) {
			
			//Calculo es estilo
			String estilo = "width:"+ propTemp.getAncho()+"%";
			
			//Obtengo el valor segun la propiedad que haya seleccionado 
			String metodoGet = propTemp.getPropiedad().getAtributo();
			metodoGet = metodoGet.substring(0, 1).toUpperCase() + metodoGet.substring(1);
			Method meotodoContenido = DAO_Utils.getMetodo(pedido, "get"+metodoGet);
			
			//Invoco al metodo get del estudio de la propiedad que figure en el template
			String contenidoSaneado = null;
			try {
				String contenido = (String) meotodoContenido.invoke(pedido);
				
				contenidoSaneado = PdfUtils.sanearHTML((contenido==null)?"":contenido);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			
			//Obtengo el atributo que figura en el template
			item = new ItemLista(estilo, propTemp.getContenido(), contenidoSaneado);
			
			listaAImprimir.add(item); 
		}
		
		//Creo el mail
		Map<String, Object> data = new HashMap<>();
		data.put("listaAImprimir", listaAImprimir);
		data.put("elemento", pedido);

//		Creo el PDF
		return pdfUtils.htmlFileToPdf("/historiaClinica/pedidos/pedido.pdf", data);
	}

	private Template_VO getTemplateDeServicio(String nombreServicio) {
		
		DAO_Templates daoEst = this.getDao().getInstance(DAO_Templates.class);
		
		List<Template_VO> templs = daoEst.buscarPorNombre(getDao().getEntityManager(), nombreServicio);
		
		return templs.get(0); //Solo hay un template por servicio
	}
	
	/******************************************************************************/
	/******************************************************************************/
	/******************************************************************************/
	/**
	 * @throws IOException ****************************************************************************/
	public String findByIdBloqueante(Long idPedido, String usuarioAccion)  {
		
		//Recupero el pedido asi como está
		Pedido_VO vo = (Pedido_VO) this.getDao().bloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Editando un pedido.."));
		
		Map<String, Object> datosAPublicar = new HashMap<>();
		
		//Le agrego el alerta generado
		datosAPublicar.put("pedido", vo);
		
//		boolean ok = WebSocketPedidosServlet.bloquearPedido(datosAPublicar);
	
		return (true)?
					this.getGson().toJson(vo):
					this.getGson().toJson(new RespuestaCorta(false, "Error de concurrencia"));
	}
	
	/**
	 * 		Recuper el estudio y lo bloquea
	 * 
	 * @param idPedido
	 * @param idEstudio
	 * @param usuarioAccion
	 * 
	 * @return
	 */
	public String findByIdByEstudioBloqueante(Long idPedido, Long idEstudio, String usuarioAccion) {

		//Recupero el pedido asi como está
		Pedido_VO pedido = this.findPedido(idPedido, idEstudio);

		//Si el pedido es UN estudio por pedido, me fijo si el estudio esta bloqueado
		if (pedido.getUnEstudioPorPedido()){
			
			DAO_EstudioDePedido daoEst = this.getDao().getInstance(DAO_EstudioDePedido.class);
			if (pedido.getEstudiosPaciente().get(0).getBloqueado()){
				//El estudio esta bloqueado
				
				//Desbloqueo el estudio SOLO si el usuario desbloqueante es el bloqueante
				if (usuarioAccion.equals(pedido.getUsuarioAccion())){
					daoEst.bloquearPorId(idEstudio, new DatosAccion(usuarioAccion, "Editando un estudio de pedido.."));
				}
			}else{
				//Bloqueo el estudio
				EstudioDePedido_VO estudioBloqueado = (EstudioDePedido_VO) daoEst.bloquearPorId(idEstudio, new DatosAccion(usuarioAccion, "Editando un estudio de pedido.."));
				//Reemplazo el estudio anterior por el nuevo bloqueado
				pedido.getEstudiosPaciente().set(0, estudioBloqueado);
			}
			
		}else{
			//Todos los estudios dependen de un pedido, no necesito desbloquear los estudios
			//ya que en este caso solo se bloquea el pedido, no los estudios
			if (usuarioAccion.equals(pedido.getUsuarioAccion())){
				pedido = (Pedido_VO) this.getDao().bloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Editando un pedido.."));
			}
		}
		
		//Si estan todos bloqueados lo bloqueo
		if (this.getDao_Pedido().chequearTodosEstudiosBloqueados(idPedido)){
			pedido = (Pedido_VO) this.getDao().bloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Editando un pedido.."));
		}
		
		Map<String, Object> datosAPublicar = new HashMap<>();
		
		//Le agrego el alerta generado
		datosAPublicar.put("pedido", pedido);
		
//		WebSocketPedidosServlet.bloquearPedido(datosAPublicar);
		
		return this.getGson().toJson(pedido);
	}
	
	/******************************************************************************/
	/******************************************************************************/
	
	/**
	 * 		Desbloquea el pedido
	 */
	public String desbloquear(Long idPedido, String usuarioAccion) {
		
		//Recupero el pedido asi como está
		Pedido_VO pedido = (Pedido_VO) this.getDao().desbloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Desbloqueando un pedido.."));
		
		Map<String, Object> datosAPublicar = new HashMap<>();
		
		//Le agrego el alerta generado
		datosAPublicar.put("pedido", pedido);
		
//		WebSocketPedidosServlet.desbloquearPedido(datosAPublicar);
		
		return this.getGson().toJson(pedido);
	}
	
	/**
	 * 		Desbloquea el estudio del pedido
	 */
	public String desbloquear(Long idPedido, Long idEstudio, String usuarioAccion) {

		//Recupero el pedido asi como está
		Pedido_VO pedido = this.findPedido(idPedido, idEstudio);

		//Si el pedido es UN estudio por pedido, me fijo si el estudio esta bloqueado
		if (pedido.getUnEstudioPorPedido()){
			EstudioDePedido_VO ep = pedido.getEstudiosPaciente().get(0);	
			if (ep.getBloqueado()){
				//El estudio esta bloqueado
				
				DAO_EstudioDePedido daoEst = this.getDao().getInstance(DAO_EstudioDePedido.class);
				
				//Desbloqueo el estudio SOLO si el usuario desbloqueante es el bloqueante
				if (usuarioAccion.equals(pedido.getUsuarioAccion())){
					EstudioDePedido_VO estDesbloqueado = (EstudioDePedido_VO) daoEst.desbloquearPorId(idEstudio, new DatosAccion(usuarioAccion, "Desbloqueó el estudio "+ep.getNumeroItem()+" del pedido: "+pedido.getNumero()));
					pedido.getEstudiosPaciente().set(0, estDesbloqueado);
				}
			}else{
				if (usuarioAccion.equals(pedido.getUsuarioAccion())){
					pedido = (Pedido_VO) this.getDao().desbloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Desbloqueó el pedido: "+pedido.getNumero()));
				}
			}
			
		}else{
			//Todos los estudios dependen de un pedido, no necesito desbloquear los estudios
			//ya que en este caso solo se bloquea el pedido, no los estudios
		}
		
		//Si estan todos los estudios del pedido desbloqueados lo desbloqueo al pedido
		if (this.getDao_Pedido().chequearTodosEstudiosDesbloqueados(idPedido)){
			pedido = (Pedido_VO) this.getDao().desbloquearPorId(idPedido, new DatosAccion(usuarioAccion, "Desbloqueó el pedido: "+pedido.getNumero()));
		}
		
		Map<String, Object> datosAPublicar = new HashMap<>();
		
		//Le agrego el alerta generado
		datosAPublicar.put("pedido", pedido);
		datosAPublicar.put("data", new DatosAccion(usuarioAccion, "Desbloqueó el pedido: "+pedido.getNumero()));
		
//		WebSocketPedidosServlet.desbloquearPedido(datosAPublicar);
		
		return this.getGson().toJson(pedido);
	}
	
	/******************************************************************************/
	/******************************************************************************/

	/**
	 * 	Ultimo pedido de un paciente dado en el que coincida alguno de los estudios(idEstudio) con los estudios actuales
	 * 
	 * @param idEstudios
	 * @param numeroDniPaciente
	 * @param tipoDniPaciente
	 * @param numeroPedido
	 * @param usuarioAccion
	 * @return
	 */
	public String estudioPrevio(List<Long> idEstudios, String numeroDniPaciente,
			String tipoDniPaciente,String numeroPedido, String usuarioAccion) {
		Pedido_VO vo = this.getDao_Pedido().findByIdEstudioAndPatient(idEstudios,numeroDniPaciente,tipoDniPaciente,numeroPedido);

		DAO_Utils.info(this.log, "Admin_Pedidos" ,
				"estudioPrevio", usuarioAccion, "Se recupera el último estudio con idEstudio: "
						+ idEstudios + " del paciente( tipo: "+tipoDniPaciente+", dni: "+numeroDniPaciente+")");

		return getGson().toJson(vo);
	}

	/**
	 * @return the dao_Modalidad
	 */
	public DAO_Modalidad getDao_Modalidad() {
		return dao_Modalidad;
	}

	/**
	 * @param dao_Modalidad the dao_Modalidad to set
	 */
	public void setDao_Modalidad(DAO_Modalidad dao_Modalidad) {
		this.dao_Modalidad = dao_Modalidad;
	}

	public String getPedidosEnCTG(String fecha) {
		
				/*traer numeros de pedidos en ctg*/
		List<Pedido_VO> pedidos = this.getDao_Pedido().listaDePedidosEnCtg(fecha);
		
		JSON_Paginador pag = new JSON_Paginador(pedidos);
				
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		resp.setOk(true);
		return new Gson().toJson(resp);
	}

	public String setEstadoPedidoEnAtencion(String nroPedido,
			String usuarioAccion) {
		RespuestaCorta resul = null;
		
		DAO_Utils.info(this.log, "Admin_Pedidos",
				"set estado pedido:'En Atencion'", usuarioAccion,
				"Pedido con el número: " + nroPedido);

		resul = getDao_Pedido().setEstadoPedidoEnAtencion(nroPedido, usuarioAccion);

		return new Gson().toJson(resul);
	}
}