package ar.com.builderadmin.timers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import ar.com.builderadmin.controllers.historiaClinica.pedidos.Admin_Pedidos;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.DAO_Parametro;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Pedido;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Pedido.PedidosSolicitado;
import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.ws.RespuestaCorta;

/**
 * 
 * @author segarcia
 *
 */
@Component
public class ChronoPedidos implements Runnable, Trigger{

	private static final String CONSULTADOR_LAPSO = "segundos_chrono_consultador_pedidos";
	private static final String CONSULTADOR_ON = "chrono_consultador_pedidos_on";
	private static final String CONSULTADOR_LAPSO_ESTADO = "segundos_chrono_consultador_estado";

	/**
	 * Logger.
	 */
	protected final Logger log = LoggerFactory.getLogger(ChronoPedidos.class);
	
	@Autowired
	private DAO_Parametro daoParametros;
	
	@Autowired
	private DAO_Pedido dao_Pedido;
	
	@Autowired
	private Admin_Pedidos admin_Pedidos;
	
	private final DateFormat formater = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	public void testChrono() {
		// something that should execute periodically
		
		String fecha = formater.format(new Date());
		DAO_Utils.info(log, "ChronoConfig", "testChrono",  "chrono", "Se hace algo a las: "+fecha);
	}
	
	/**
	 * Chequea cada 5 segundos (contando una vez que termina correctamente) 
	 * si existen pedidos nuevos para cargar
	 */
	public void getPedidos() {
		
		//Obtengo los pedidos pendientes de registro en Horus
		DAO_Utils.info(log, "Chrono", "getPedidosNuevos", "chrono", "Se hace algo a las: ");
		List<PedidosSolicitado> pss = this.getDao_Pedido().getPedidosNuevos();
		
		RespuestaCorta resul;
		for (PedidosSolicitado pedidosSolicitado : pss) {
			//Proceso las solicitudes pendientes
			resul = this.getAdmin_Pedidos().registrarNuevoPedido(pedidosSolicitado.getPedido().toString(), "Horus");
			
			//Comunico el resultado del registro
			this.getDao_Pedido().enviarRespuestaARegistroDePedidoIDEAR(resul, pedidosSolicitado.getSolicitud());
		}
	}
	

	/**
	 * @return the dao_Pedido
	 */
	public DAO_Pedido getDao_Pedido() {
		return dao_Pedido;
	}

	/**
	 * @param dao_Pedido the dao_Pedido to set
	 */
	public void setDao_Pedido(DAO_Pedido dao_Pedido) {
		this.dao_Pedido = dao_Pedido;
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
	
	/**************************************************************************************/
	private boolean chornoPedidosON = false;
	
	/**
	 * Tarea que realiza al ejecutarse
	 */
	@Override
	public void run() {
		//Se ejecuta el trigger
		
		//Tomo de la BD si prendieron el consumidor de pedidos
		chornoPedidosON = Boolean.parseBoolean(daoParametros.findValueByNombre(CONSULTADOR_ON, E_TipoParametro.BOOLEAN));
	
		
		//Si se desea consumir los pedidos
		if (chornoPedidosON)
			this.getPedidos();

		//Si se desea mandar mail de 
	}
	
	/**************************************************************************************/
	/**
	 * Indica cada cuanto se ejecuta
	 */
	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {

		//Tomo de la BD si prendieron el consumidor de pedidos
		chornoPedidosON = Boolean.parseBoolean(daoParametros.findValueByNombre(CONSULTADOR_ON, E_TipoParametro.BOOLEAN));
		
		Calendar nextExecutionTime = new GregorianCalendar();
		if (chornoPedidosON){
			//Esta prendido, calculo el nuevo tiempo a ejecutar la tarea
			Date lastActualExecutionTime = triggerContext
					.lastActualExecutionTime();
			
			nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime
					: new Date());
			
			//Tomo el parámetro que indica cada cuanto debo llamarlo
			Integer delay = Integer.parseInt(daoParametros.findValueByNombre(CONSULTADOR_LAPSO, E_TipoParametro.INTEGER));
			
			// you can get the value from wherever you want
			nextExecutionTime.add(Calendar.SECOND, delay); 
			
		}else{
			
			//Vuelve a chequear en una hora si se activo el Chrono
			Integer delayOff = 30;
			
			try{
				delayOff = Integer.parseInt(daoParametros.findValueByNombre(CONSULTADOR_LAPSO_ESTADO, E_TipoParametro.INTEGER));
			}catch(java.lang.NumberFormatException e){}
			
			nextExecutionTime.add(Calendar.SECOND, delayOff);
			
		}
		
		return nextExecutionTime.getTime();
	}
	
	/**************************************************************************************/
}