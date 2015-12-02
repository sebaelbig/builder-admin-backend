package ar.com.builderadmin.dao.historiaClinica.pedidos;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.controllers.historiaClinica.pedidos.Admin_Pedidos;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.historiaClinica.pedidos.EstudioDePedidoAngra;
import ar.com.builderadmin.model.historiaClinica.pedidos.Pedido;
import ar.com.builderadmin.model.historiaClinica.pedidos.PedidoFiltrado;
import ar.com.builderadmin.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.com.builderadmin.utils.adapters.historiaClinica.pedidos.MensajePedidoSimpleAdapter;
import ar.com.builderadmin.utils.adapters.historiaClinica.pedidos.PedidoBDSimpleAdapter;
import ar.com.builderadmin.utils.adapters.historiaClinica.pedidos.PedidoSimpleAdapter;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.usuarios.Mensaje_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;
import ar.com.builderadmin.ws.RespuestaCorta;

@Service
public class DAO_Pedido extends DAO<Pedido_VO> {
	
	public DAO_Pedido() {
		
		this.resetQuery();
		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".numero ");
		
	}
	
	@Override
	public void resetQuery() {
		super.resetQuery();
		this.setQueryEncabezado("SELECT new "
				+ Pedido_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

	}
	
	public DAO_Pedido(Long idSuc) {
		this();
	}

	@Override
	public Class getClazz() {
		return Pedido.class;
	}

	@Override
	public String getIdClass() {
		return "pedidos";
	}
	
	@Override
	protected Object getObjeto(I_ValueObject valueObject) {

		Object o = null;

		// Si estoy persistiendo un rol en particular, el usuario no se
		// convertira cuando le haga toObject
		if (valueObject instanceof Pedido_VO) {

			Pedido_VO p_vo = (Pedido_VO) valueObject;
			o = p_vo.toObject();
			Pedido p = (Pedido) o;

			Servicio srv = this.getEntityManager().find(Servicio.class,
					p_vo.getIdServicio());
			p.setServicio(srv);

			List<EstudioDePedido> fxs = new ArrayList<>();
			EstudioDePedido estudioTemp = null;
			for (EstudioDePedido_VO ep : p_vo.getEstudiosPaciente()){
				try{
					estudioTemp = this.getEntityManager().find(EstudioDePedido.class, ep.getId());
					fxs.add(estudioTemp);
				}catch(Exception e){}
			}
			p.setEstudiosPaciente(fxs);
			
			
		} else {
			o = valueObject.toObject();
		}

		return o;

	}
	
	public Pedido_VO getPedido(String nroPedido) {
		Pedido_VO resul = null;
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_detallepedido(?,?)}");

				pstmt.setString(1, nroPedido);

				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_detallepedido(" + nroPedido
								+ ")", new Object[0]);
				pstmt.execute();
				String sp_horus_get_detallepedido = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_Pedido", "getPedido", getUsuarioAccion(),"Se llama a sp_horus_get_detallepedido y se obtiene: "+sp_horus_get_detallepedido);

				resul = new GsonBuilder()
						.registerTypeAdapter(Pedido_VO.class, new PedidoSimpleAdapter())
						.create()
						.fromJson(sp_horus_get_detallepedido, Pedido_VO.class);
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}

		return resul;
	}
	
	public Pedido_VO findById(String nroPedido) {
		Pedido_VO resul = new Pedido_VO();

		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_detallepedido(?,?)}");

				pstmt.setInt(1, Integer.parseInt(nroPedido));

				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				String detalle = "Llamando: 1.3.1	sp_horus_get_detallepedido(" + nroPedido + ")";
				DAO_Utils.info(log, "DAO_Pedido", "findById",getUsuarioAccion(), detalle);
				pstmt.execute();
				String sp_horus_get_detallepedido = pstmt.getString(2);
				
				DAO_Utils.info(log, "DAO_Pedido", "findById", getUsuarioAccion(),"Resultado obtenido: " + sp_horus_get_detallepedido);
				
				try{
					
					resul = new GsonBuilder()
							.registerTypeAdapter(Pedido_VO.class,
									new PedidoSimpleAdapter()).create()
							.fromJson(sp_horus_get_detallepedido, Pedido_VO.class);
					
					if (resul.getTipoDniPaciente()==null){
						//No me trajo lo correcto
//						RespuestaCorta resp = new Gson().fromJson(sp_horus_get_detallepedido, RespuestaCorta.class);
						
						resul.setNumero("ERROR");
//						resul.setPaciente("El sp: sp_horus_get_detallepedido("+nroPedido+") responde que No existe un pedido con el nro ingresado: "+nroPedido);
						resul.setPaciente(Admin_Pedidos.PEDIDO_INEXISTENTE);
						
					}else{
						//Todo bien
						resul.setNumero(nroPedido);
					}
					
				}catch(Exception e ){
					cerrarConexion(con);
					
					resul.setNumero("ERROR");
					resul.setPaciente(Admin_Pedidos.RESULTADO_INCORRECTO);
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
			
			resul.setNumero("ERROR");
			resul.setPaciente(Admin_Pedidos.FALLO_EN_CONEXION_A_BD);
		} finally {
			cerrarConexion(con);
		}

		return resul;
	}

	public Boolean chequearTodosEstudiosConEstado(Pedido_VO pedido, String estadoDeseado) {
		
		Pedido ped = (Pedido) this.findById(pedido.getId());
		
		Boolean todos = true;
		for (EstudioDePedido estPed : ped.getEstudiosPaciente()) {
			todos = todos && estPed.getEstado().equalsIgnoreCase(estadoDeseado);
		}
		
		return todos;
	}
	
	/**
	 * Chequea si todos los estudios de un pedido estan desbloqueados
	 * 
	 * @param pedido
	 * @param estadoDeseado
	 * @return
	 */
	public Boolean chequearTodosEstudiosDesbloqueados(Long idPedido) {
		
		Pedido ped = (Pedido) this.findById(idPedido);
		
		Boolean todos = true;
		for (EstudioDePedido estPed : ped.getEstudiosPaciente()) {
			todos = todos && !estPed.getBloqueado();
		}
		
		return todos;
	}
	
	/**
	 * Chequea si todos los estudios de un pedido estan bloqueados
	 * 
	 * @param pedido
	 * @param estadoDeseado
	 * @return
	 */
	public Boolean chequearTodosEstudiosBloqueados(Long idPedido) {
		
		Pedido ped = (Pedido) this.findById(idPedido);
		
		Boolean todos = true;
		for (EstudioDePedido estPed : ped.getEstudiosPaciente()) {
			todos = todos && estPed.getBloqueado();
		}
		
		return todos;
	}
	
	/**
	 * Chequea si los estudios estan TODOS finalizados
	 * 
	 * @param pedido
	 * @return
	 */
	public Boolean chequearParaFinalizar(Pedido_VO pedido) {
		
		Pedido ped = (Pedido) this.findById(pedido.getId());
		
		Boolean todos = true;
		for (EstudioDePedido estPed : ped.getEstudiosPaciente()) {
			todos = todos && estPed.estaFinalizado();
		}
		
		return todos;
	}
	
	public boolean existePedido(Long id) {
		
		Pedido ped = (Pedido) this.findById(id);
		
		return ped!=null && !ped.getBorrado();
	}

	public List<Pedido_VO> listarDeServicio(Servicio_VO serv,
			String usuarioAccion) {

		this.resetQuery();
		
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".servicio.id = :idSrv ");
		this.getCondiciones().put("idSrv", serv.getId());
		
		List<Pedido_VO> pedidos = this.listarTodo();
		
		this.resetQuery();
		return pedidos;
	}
	

	public RespuestaCorta setEstadoPedido(String nroPedido, String usuario, String estado, String motivo) {
		
		RespuestaCorta resul = null;
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_estadopedido(?,?,?,?,?,?)}");

				pstmt.setInt(1, Integer.parseInt(nroPedido));
				pstmt.setString(2, estado);
				pstmt.setString(3, motivo);
				pstmt.setString(4, usuario);
				String fecha = DAO_Utils.getStrDate();
				pstmt.setString(5, fecha);

				pstmt.registerOutParameter(6, -1);
				pstmt.setString(6, "");

				DAO_Utils.info(log, "DAO_Pedido", "setEstadoPedido", getUsuarioAccion(),"Llamando: 1.3.3	sp_horus_set_estadopedido(" + nroPedido
						+", "+usuario+", "+estado+", "+motivo+", "+fecha+")");
				
				pstmt.execute();
				String sp_horus_set_estadoPedido = pstmt.getString(6);

				DAO_Utils.info(log, "DAO_Pedido", "setEstadoPedido",getUsuarioAccion(), "Se llama a sp_horus_set_estadopedido y se obtiene: "+sp_horus_set_estadoPedido);

				resul = new Gson().fromJson(sp_horus_set_estadoPedido, RespuestaCorta.class);
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}

		return resul;
//		return new RespuestaCorta(true, "Se llamo al setEstadoPedido()");
		
	}

	public Pedido_VO refreshEstudios(Pedido_VO pedido) {
		Pedido ped = (Pedido) this.findById(pedido.getId());
		return ped.toValueObject();
	}
	

	@SuppressWarnings("unchecked")
	public List<Pedido_VO> listarPedidosPorFiltro(PedidoFiltrado pedidoFiltrado, String usuarioAccion) {

//		this.resetQuery();
//        String where = "WHERE ";
//        
//		if (pedidoFiltrado.getTipoDoc() !=null ) {
//			params += this.getIdClass()+".tipoDniPaciente = :tipoDni AND ";
//			this.getCondiciones().put("tipoDni",pedidoFiltrado.getTipoDoc());
//		}
//		if (pedidoFiltrado.getNroDoc()!=null ) {
//			params += this.getIdClass()+".nroDniPaciente = :nroDni AND ";
//			this.getCondiciones().put("nroDni", pedidoFiltrado.getNroDoc());
//		}
//		
//		if (pedidoFiltrado.getServicio() !=null ) {
//			params += this.getIdClass()+".servicio.codigo = :idSrv AND ";
//			this.getCondiciones().put("idSrv",pedidoFiltrado.getServicio().toString());
//		}
//		if (pedidoFiltrado.getModalidad() !=null ) {
//			params += this.getIdClass()+".modalidad = :codMod AND ";
//			this.getCondiciones().put("codMod",pedidoFiltrado.getModalidad());
//		}
//		if (pedidoFiltrado.getEstado()!=null ) {
//			/*if (pedidoFiltrado.getEstado().equals("paciente") ) {
//			 * muestro solo informado y cancelado
//				params = params +"("+ this.getIdClass()+".estado = :inf OR ";
//				this.getCondiciones().put("inf", "Informado");
//				params = params + this.getIdClass()+".estado = :can ) AND ";
//				this.getCondiciones().put("can", "Cancelado");
//			}else{*/
//				params = params + this.getIdClass()+".estado = :estado AND ";
//				this.getCondiciones().put("estado", pedidoFiltrado.getEstado());
//			//}
//		}
//		if (params != null) {
//			where = where + params;
//		}
//		if(pedidoFiltrado.getOrden() != null){
//			/*PedidioDePacienteCtrl  lo usa*/
//			this.setQueryFinal(" ORDER BY " + this.getIdClass() + "."+pedidoFiltrado.getOrden() + " DESC");
//		}
//		System.out.print(params);
//		this.setQueryCondiciones(where + this.getIdClass()+".dt_fecha >= :fechaDesde AND "  
//	                                   + this.getIdClass()+".dt_fecha <= :fechaHasta");				
//		this.getCondiciones().put("fechaDesde", DAO_Utils.parseDateHour(pedidoFiltrado.getFechaDesde() + " 00:00:00"));
//		this.getCondiciones().put("fechaHasta", DAO_Utils.parseDateHour(pedidoFiltrado.getFechaHasta() + " 23:59:00"));
//		
//		List<Pedido_VO> pedidos = this.listarTodo();
		
		//estado_pedido character varying, 
        //fecha_desde timestamp without time zone, 
        //fecha_hasta timestamp without time zone, 
        //modalidad_pedido character varying, 
        //codigo_servicio character varying, 
        //nro_dni character varying, 
        //tipo_dni character varying)
		
//		this.resetQuery();
		
		
		Object[] params = {
				(pedidoFiltrado.getEstado()==null)?null:"'"+pedidoFiltrado.getEstado()+"'",
				"'"+DAO_Utils.formatPostgresDate( DAO_Utils.parseDateHour(pedidoFiltrado.getFechaDesde() + " 00:00:00"))+"'",
				"'"+DAO_Utils.formatPostgresDate( DAO_Utils.parseDateHour(pedidoFiltrado.getFechaHasta() + " 23:59:00"))+"'",
				(pedidoFiltrado.getModalidad()==null)?null:"'"+pedidoFiltrado.getModalidad()+"'",
				(pedidoFiltrado.getServicio()==null)?null:"'"+pedidoFiltrado.getServicio().toString()+"'",
				(pedidoFiltrado.getNroDoc()==null)?null:"'"+pedidoFiltrado.getNroDoc()+"'",
				(pedidoFiltrado.getTipoDoc()==null)?null:"'"+pedidoFiltrado.getTipoDoc()+"'"
        };
		  
		String query = "SELECT fx_buscar_pedidos("+
				params[0]+","+params[1]+","+params[2]+","+params[3]+","+params[4]+","+params[5]+","+params[6]
				+")";
		
		DAO_Utils.info(log, "DAO_Pedido", "listarPedidosPorFiltro", getUsuarioAccion(), "Ejecutando la query: \n"+query);
		Object fx_response = getEntityManager().createNativeQuery(query).getSingleResult();
		
		if (fx_response==null) 
			fx_response = "[]";
		
		String json_pedidos = fx_response.toString();
		DAO_Utils.info(log, "DAO_Pedido", "listarPedidosPorFiltro", getUsuarioAccion(), "Respondió: \n"+json_pedidos);
		

		List<Pedido_VO> pedidos = 
				(List<Pedido_VO>) 
				new GsonBuilder()
					.registerTypeAdapter(Pedido_VO.class, new PedidoBDSimpleAdapter())
					.create()
					.fromJson(json_pedidos, new TypeToken<List<Pedido_VO>>() {}.getType());
		
		return pedidos;
	}


	public List<Pedido_VO> listarDePaciente(String nroDniPaciente, String tipoDniPaciente, String usuarioAccion) {
		this.resetQuery();

		this.setQueryCondiciones("WHERE " + this.getIdClass()+".nroDniPaciente = :nroDniPaciente AND "  
                + this.getIdClass()+".tipoDniPaciente = :tipoDniPaciente ");	
		this.getCondiciones().put("nroDniPaciente", nroDniPaciente);
		this.getCondiciones().put("tipoDniPaciente", tipoDniPaciente);
		
		List<Pedido_VO> pedidos = this.listarTodo();
		
		this.resetQuery();
		return pedidos;
	}
	
	public Pedido_VO findByIdEstudioAndPatient(List<Long> idEstudios,
			String numeroDniPaciente, String tipoDniPaciente,String numeroPedido) {
//		String encabezadoOriginal=this.getQueryEncabezado();
		
		this.resetQuery();
		
		this.setQueryEncabezado(getQueryEncabezado()+" INNER JOIN "+this.getIdClass()+".estudiosPaciente as estudio ");
		
		this.setQueryCondiciones("WHERE "+this.getIdClass()+".nroDniPaciente = :numeroDniPaciente AND "
				+this.getIdClass()+".tipoDniPaciente = :tipoDniPaciente AND "+this.getIdClass()+".estado = :estado AND " 
				+" estudio.estudio.id in :idEstudios AND "+this.getIdClass()+".numero != :numeroPedido");
		
		this.setQueryFinal(" ORDER BY "+this.getIdClass()+".id DESC ");
		
		this.getCondiciones().put("numeroDniPaciente",numeroDniPaciente);
		this.getCondiciones().put("tipoDniPaciente",tipoDniPaciente);
		this.getCondiciones().put("idEstudios",idEstudios);
		this.getCondiciones().put("numeroPedido",numeroPedido);
		this.getCondiciones().put("estado","Informado");
		
		List<Pedido_VO> pedidos = this.listar(0,1);
		
		if(pedidos.size()>0){
			return pedidos.get(0);
		}else{
			return null;
		}
	}

	public R_GetMensajes guardarMensaje(Mensaje_VO mensaje) {
		
		R_GetMensajes resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_msjpedido(?,?,?,?,?)}");
				
				pstmt.setString(1, mensaje.getUsuario());
				pstmt.setInt(2, mensaje.getNroPedido());
				pstmt.setString(3, mensaje.getTexto());
				String fecha = DAO_Utils.getStrDate();
				pstmt.setString(4, fecha);
				
				pstmt.registerOutParameter(5, -1);
				pstmt.setString(5, "");
				
				DAO_Utils.info(log, "DAO_Pedido", "guardarMensaje", getUsuarioAccion(), "Llamando: 1.3.6	sp_horus_set_msjpedido("+mensaje.getUsuario() +", "+ mensaje.getNroPedido()+", "
											+ mensaje.getTexto()+", "+ fecha +")");
				
				pstmt.execute();
				String sp_horus_set_msjpedido = pstmt.getString(5);
				
				DAO_Utils.info(log, "DAO_Pedido", "guardarMensaje", getUsuarioAccion(), "Se llama a sp_horus_set_msjpedido y se obtiene: "+sp_horus_set_msjpedido);
				
				resul = this.getMensajes(mensaje.getNroPedido());
			}
			
		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}
		
		return resul;
	}
	
	public R_GetMensajes getMensajes(int nroPedido) {
		
		R_GetMensajes resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_msjspedido(?,?)}");

				pstmt.setInt(1, nroPedido);

				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				DAO_Utils.info(log, "DAO_Pedido", "getMensajes", getUsuarioAccion(),"Llamando: 1.3.5	sp_horus_get_msjsPedido(" +nroPedido+")");
				
				pstmt.execute();
				String sp_horus_get_msjsPedido = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_Pedido", "getMensajes", getUsuarioAccion(),"Se llama a sp_horus_get_msjsPedido y se obtiene: "+sp_horus_get_msjsPedido);

				resul = new GsonBuilder()
					.registerTypeAdapter(Mensaje_VO.class, new MensajePedidoSimpleAdapter())
					.create()
					.fromJson(sp_horus_get_msjsPedido, R_GetMensajes.class);
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}

		return resul;
	}
	
	public class R_GetMensajes {

		private String mensaje;
		private Boolean ok;

		private List<Mensaje_VO> msjs = new ArrayList<>();

		public R_GetMensajes() {
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje
		 *            the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok
		 *            the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

		public List<Mensaje_VO> getMsjs() {
			return msjs;
		}

		public void setMsjs(List<Mensaje_VO> msjs) {
			this.msjs = msjs;
		}

	}
	
	/*********************************/
	/**
	 * 
	 * sp_pedidos_solicitados_a_horus: 
	 * 
	 * Devuelve un json con una lista de {solicitud, pedido}, los cuales estÃ¡n pendientes de transferir.
	 * El primero devuelve la lista de pedidos pendientes de transferir,  
	 * cada uno con un Nro. de solicitud asociado.
	 * 
	 * sp_pedido_a_horus_respuesta (solicitud, respuesta): 
	 * 
	 * Actualiza las tablas para registrar que el pedido se transfiriÃ³.
	 * El segundo SP lo tiene que llamar despuÃ©s de que transfieren cada pedido, 
	 * indicando el Nro. de solicitud correspondiente y, en el mensaje, el resultado de la 
	 * transferencia.
	 * Si todo anduvo ben, poner en el mensaje â€œEl pedido se transfiriÃ³ correctamenteâ€�
	 * Si algo anduvo mal, describir el problema
	 */
	
	/**
	 * @param nroPedido
	 * @return Devuelve una lista de pedidos que tienen solicitud pendiente
	 */
	public List<PedidosSolicitado> getPedidosNuevos() {
		
		List<PedidosSolicitado> solicitudes = new ArrayList<>();
		
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		
		try {
			if (con != null) {
				
				//Obtengo los pedidos a solicitar
				CallableStatement pstmt = con
						.prepareCall("{call sp_pedidos_solicitados_a_horus(?)}");

				pstmt.registerOutParameter(1, -1);
				pstmt.setString(1, "");
				
				DAO_Utils.info(log, "DAO_Pedido", "getPedidosNuevos", getUsuarioAccion(),"Llamando: 1.3.5	sp_pedidos_solicitados_a_horus()");
				
				pstmt.execute();
				String sp_pedidos_solicitados_a_horus = pstmt.getString(1);

				DAO_Utils.info(log, "DAO_Pedido", "getPedidosNuevos", getUsuarioAccion(),"Se llama a sp_pedidos_solicitados_a_horus y se obtiene: "+sp_pedidos_solicitados_a_horus);
				
				Type listType = new TypeToken<List<PedidosSolicitado>>() {}.getType();

				//Parseo la lista de solicitud/pedido
				solicitudes = new GsonBuilder()
								.create()
								.fromJson(sp_pedidos_solicitados_a_horus, listType);
				
				if (solicitudes.isEmpty())
					DAO_Utils.info(log, "DAO_Pedido", "getPedidosNuevos", getUsuarioAccion(),"Actualmente no hay solicitudes de pedidos pendientes...");
				
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}
		
		return solicitudes;
	}
	
	/**
	 * Se notifica a IDEAR como fue el resultado de procesar el registro del pedido
	 * 
	 * @param resul
	 * @param solicitud
	 */
	public void enviarRespuestaARegistroDePedidoIDEAR(RespuestaCorta resul, Integer solicitud) {
		
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		
		try {
			if (con != null) {
				
				//Obtengo los pedidos a solicitar
				CallableStatement pstmt = con
						.prepareCall("{call sp_pedido_a_horus_respuesta(?,?)}");

				pstmt.setInt(1, solicitud);
				pstmt.setString(2, (resul.getOk())?"El pedido se transfirió correctamente”":resul.getMensaje());
				
				DAO_Utils.info(log, "DAO_Pedido", "enviarRespuestaARegistroDePedidoIDEAR", getUsuarioAccion(),"Se llamo a sp_pedido_a_horus_respuesta("+solicitud+")");
				
				pstmt.execute();
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}
		
	}
	
	
	public RespuestaCorta setEstadoPedidoEnAtencion(String nroPedido, String usuario) {
			
			RespuestaCorta resul = null;
			Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
			try {
				if (con != null) {
					CallableStatement pstmt = con
							.prepareCall("{call sp_horus_set_pedidoena(?,?,?)}");
	
					pstmt.setInt(1, Integer.parseInt(nroPedido));
					pstmt.setString(2, usuario);
	
					pstmt.registerOutParameter(3, -1);
					pstmt.setString(3, "");
	
					DAO_Utils.info(log, "DAO_Pedido", "setEstadoPedidoEnAtencion", getUsuarioAccion(),"Llamando: 1.3.3	sp_horus_set_pedidoena(" + nroPedido
							+", "+usuario+")" );
					
					pstmt.execute();
					String sp_horus_set_pedidoena = pstmt.getString(3);
	
					DAO_Utils.info(log, "DAO_Pedido", "setEstadoPedido", getUsuarioAccion(), "Se llama a sp_horus_set_pedidoena y se obtiene: "+sp_horus_set_pedidoena);
	
					resul = new Gson().fromJson(sp_horus_set_pedidoena, RespuestaCorta.class);
				}
	
			} catch (NoResultException e) {
				e.printStackTrace();
				cerrarConexion(con);
			} catch (Exception e1) {
				e1.printStackTrace();
				cerrarConexion(con);
			} finally {
				cerrarConexion(con);
			}
	
			return resul;
		
	}
	
	
	/*Lista de numeros de pedidos que no pasaron */
	public List<Pedido_VO> listaDePedidosEnCtg(String fecha){
		List<Pedido_VO> resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_pedidosctg(?,?)}");
				
				pstmt.setString(1, fecha);
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_pedidosctg("+fecha+")", new Object[1]);
				pstmt.execute();
				String sp_horus_get_pedidosctg = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_Pedidos","listadoDePedidosEnCtg", getUsuarioAccion(), "Se llama a sp_horus_get_pedidosctg("+fecha+") y se obtiene: "+sp_horus_get_pedidosctg);

				Type listType = new TypeToken<List<PedidoPendiente>>() {}.getType();

				//Parseo la lista de solicitud/pedido
				resul = new GsonBuilder()
								.create()
								.fromJson(sp_horus_get_pedidosctg, listType);
			
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}
		
		return resul;
	}
	
	/*Clase para pedidos que no pasaron con el consumidor de horus*/
	public class PedidoPendiente {
		private Integer id;
		private String paciente;
		private String efector;
		private String fecha;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getPaciente() {
			return paciente;
		}
		public void setPaciente(String paciente) {
			this.paciente = paciente;
		}
		public String getEfector() {
			return efector;
		}
		public void setEfector(String efector) {
			this.efector = efector;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
				
	}
	
	
	public class PedidosSolicitado {
		
		private Integer solicitud;
		private Integer pedido;
		/**
		 * @return the solicitud
		 */
		public Integer getSolicitud() {
			return solicitud;
		}
		/**
		 * @param solicitud the solicitud to set
		 */
		public void setSolicitud(Integer solicitud) {
			this.solicitud = solicitud;
		}
		/**
		 * @return the pedido
		 */
		public Integer getPedido() {
			return pedido;
		}
		/**
		 * @param pedido the pedido to set
		 */
		public void setPedido(Integer pedido) {
			this.pedido = pedido;
		}
	}

	/**
	 * Guarda un pedido angra
	 * 
	 * @param pa
	 */
	@Transactional
	public EstudioDePedidoAngra guardarEstudioPedidoAngra(EstudioDePedidoAngra pa) {
		pa = getEntityManager().merge(pa);
		getEntityManager().flush();
		
		return pa;
	}

	/**
	 * Dado un accesion number, se recupera el pedido angra
	 * @param pa
	 * @return
	 */
	public EstudioDePedidoAngra recuperarEstudioPedidoAngra(Long an) {
		return getEntityManager().find(EstudioDePedidoAngra.class, an);
	}

}
