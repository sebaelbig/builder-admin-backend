package ar.org.hospitalespanol.timers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.DAO_Estadisticas;
import ar.org.hospitalespanol.dao.core.DAO_Parametro;
import ar.org.hospitalespanol.model.core.E_TipoParametro;

/**
 * 
 * @author segarcia
 *
 */
@Component
public class ChronoMailer implements Runnable, Trigger{

	//Parametros del mailer
	public final String CRONO_MAILS_ON = "mails.on";
	public final String CRONO_MAILS_DIA = "mails.dia";
	public final String CRONO_MAILS_HORA = "mails.hora";
	public final String CRONO_MAILS_MINUTOS = "mails.minuto";
	public final String CRONO_MAILS_DESTINO = "mails.destino";
	
	//Funciones que se registran
	public final String CANTIDAD_LOGINS = "FX_DoLogin";
	public final String CANTIDAD_MAILS_A_SOPORTE = "FX_MailASoporte";

	/**
	 * Logger.
	 */
	protected final Logger log = LoggerFactory.getLogger(ChronoMailer.class);
	
	@Autowired
	private DAO_Parametro daoParametros;
	
	@Autowired
	private DAO_Estadisticas daoEst;
	
	private final DateFormat formater = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	/**
	 * Chequea cada 1 dia segundos (contando una vez que termina correctamente) 
	 * si existen Mailer nuevos para cargar
	 */
	public void enviarMail() {
		
	}
	

	/**************************************************************************************/
	private boolean chornoMailerON = false;
	
	/**
	 * Tarea que realiza al ejecutarse
	 */
	@Override
    public void run() {
    	
		System.out.println("[SendMailTimer] Se ejecuta el enviador de estadisticas por mail");
		
		//Calculo con el mes anterior al actual
    	Calendar c = Calendar.getInstance();
    	
		c.add(Calendar.MONTH, -1);
		Integer mesAnterior = c.get(Calendar.MONTH);//0 es Enero
		String nombreMes = DAO_Utils.getNombreMes(mesAnterior);
		
		Integer anioDelMesAnterior = c.get(Calendar.YEAR);
		System.out.println("[SendMailTimer] Se calculan las estadísticas del mes de "+nombreMes+"/"+anioDelMesAnterior);
		
		//Cantidad de logins
		Integer cantLogins = daoEst.getEstadisticaDeFuncion(mesAnterior, anioDelMesAnterior, CANTIDAD_LOGINS );
		String str_cantLogins = String.format("%05d", cantLogins);
		
		//Cantidad de mails a soporte
		Integer cantMails = daoEst.getEstadisticaDeFuncion(mesAnterior, anioDelMesAnterior, CANTIDAD_MAILS_A_SOPORTE);
		String str_cantMails = String.format("%05d", cantMails);
		

		//----------- Armo el mail
		String asunto = "Hospital Español - Estadísticas del mes de "+nombreMes+"/"+anioDelMesAnterior;
		
		String emailDestino = daoParametros.findValueByNombre(CRONO_MAILS_DESTINO, E_TipoParametro.STRING);
		String cuerpo = "Estimados,  "
				+ "\n"
				+ " Durante el mes de "+nombreMes+" de "+anioDelMesAnterior+" se registró la siguiente actividad en el sitio de pacientes: \n"
				+ "\n"
				+ "╔════════════════════╦═══════╗\n"
				+ "║ Ingresos al sitio  ║ "+ str_cantLogins+" ║\n"
				+ "╠════════════════════╬═══════╣\n"
				+ "║ Mails a soporte    ║ "+ str_cantMails+" ║\n"
				+ "╚════════════════════╩═══════╝\n"
				+ "\n"
				+ "\n"
				+ "Saludamos a Usted muy atentamente.\n"
				+ "\n"
				+ "Hospital Español de La Plata\n"
				+ "www.hospitalespanol.org.ar\n"
				+ "\n"
				+ "Por favor no responda este mail ya que se trata de un remitente automático.";
		
		System.out.println("[SendMailTimer] Se envia el mail a: "+emailDestino);
		
		daoEst.enviarMail(asunto, emailDestino, cuerpo);
    }
	
	/**************************************************************************************/
	/**
	 * Indica cada cuanto se ejecuta
	 */
	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {

		//Tomo de la BD si prendieron el consumidor de Mailer
		chornoMailerON = Boolean.parseBoolean(daoParametros.findValueByNombre(CRONO_MAILS_ON, E_TipoParametro.BOOLEAN));

		Calendar diaDeEjecucion = Calendar.getInstance();
		
		System.out.println("[ChronoMailer] Esta prendido: "+chornoMailerON);
		if (chornoMailerON){
			
			//Pasó un nuevo día, me fijo si es el dia que esta configurado
			//Tomo los parametros del archivo de configuracion
			Integer dia = Integer.parseInt(daoParametros.findValueByNombre(CRONO_MAILS_DIA, E_TipoParametro.INTEGER));
	
			
			//Es el dia de mandar el mail,
			System.out.println("[ChronoMailer] Se fija si el dia configurado ("+dia+") es el dia de hoy ("+diaDeEjecucion.get(Calendar.DAY_OF_MONTH)+")");
			if (dia==diaDeEjecucion.get(Calendar.DAY_OF_MONTH)){
				
				System.out.println("[ChronoMailer] Hoy es el día! Mando mail");
				
				//configuro una tarea para que se ejecute a la hora parametrizada
				Integer hora = Integer.parseInt(daoParametros.findValueByNombre(CRONO_MAILS_HORA, E_TipoParametro.INTEGER));
				Integer minutos = Integer.parseInt(daoParametros.findValueByNombre(CRONO_MAILS_MINUTOS, E_TipoParametro.INTEGER));
				
				//Se tiene que ejecutar hoy (date tomado del calendar)
				//Resta configurar la hora y minuto que enviará el mail
				diaDeEjecucion.set(Calendar.HOUR_OF_DAY, hora);
				diaDeEjecucion.set(Calendar.MINUTE, minutos);
				diaDeEjecucion.set(Calendar.SECOND, 0);
				diaDeEjecucion.set(Calendar.MILLISECOND, 0);
				
				System.out.println("[ChronoMailer] Es el día configurado! se planea el timer para la hora configurada ("+hora+":"+minutos+")");
				
			}else{
				
				//Sino es el dia, entonces vuelvo a consultar mañana
				diaDeEjecucion.add(Calendar.DAY_OF_YEAR, 1);
				
			}
			
		}else{
			//Sino está prendido, pregunto mañana
			diaDeEjecucion.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return diaDeEjecucion.getTime();
	}
	
	/**************************************************************************************/
}