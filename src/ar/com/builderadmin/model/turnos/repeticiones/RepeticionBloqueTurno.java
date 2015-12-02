package ar.com.builderadmin.model.turnos.repeticiones;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.model.turnos.BloqueTurno;


/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
//@Entity @Table(name="repeticion_bloque_turno")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="repeticion_bt",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("repeticion_bloque_turno_base")
public abstract class RepeticionBloqueTurno  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_repeticion_bt", name = "seq_repeticion_bt", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_repeticion_bt") private Long id;
	@Version
	private Integer version;
	
	@OneToOne
	@JoinColumn(name="id_bloque_turno")
	private BloqueTurno bloqueTurno;
	
	
	private Integer frecuencia;
	
	public RepeticionBloqueTurno(){
		//setFechas(new ArrayList<Fecha>());
	}

	/**
	 * Cheque que la fecha pasada como parametro este dentro de la franja horaria  
	 * 
	 * @param time
	 * @return
	 */
	private boolean dentroDeLaFranja(Date time) {
		
		//Si es la misma fecha, chequeo si esta dentro de la hora de atencion
		SimpleDateFormat horaMin = new SimpleDateFormat("HH:mm");
		
		Integer h = Integer.parseInt(horaMin.format(time).split(":")[0]);
		Integer m = Integer.parseInt(horaMin.format(time).split(":")[1]);
		Integer h_i = Integer.parseInt(horaMin.format(getBloqueTurno().getHoraInicio()).split(":")[0]);
		Integer m_i = Integer.parseInt(horaMin.format(getBloqueTurno().getHoraInicio()).split(":")[1]);
		Integer h_f = Integer.parseInt(horaMin.format(getBloqueTurno().getHoraFin()).split(":")[0]);
		Integer m_f = Integer.parseInt(horaMin.format(getBloqueTurno().getHoraFin()).split(":")[1]);
		
		/* Dentro de la franja horaria
		 * 
		 * 1) h_i < h < h_f
		 * 2) h_i = h < h_f && m_i <= m
		 * 3) h_i < h = h_f && m_f > m
		 */
		return 	( h_i<h  && h<h_f) || 
				( h_i==h && h<h_f  && m_i<=m ) || 
				( h_i<h  && h==h_f && m_f>m);
	}
	
	/**
	 * Devuelve la fecha mas cercana hacia al futuro dentro de las del bloque turno
	 * @param bloqueTurno
	 * @param fechaPuntero
	 * @return
	 */
	public Date obtenerFechaSiguiente(Date fechaPuntero) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPuntero);
		
		//Me posiciono en el dia que corresponde al bloque turno 
		while (cal.get(Calendar.DAY_OF_WEEK) != getBloqueTurno().getDia().getNumero_semana()){
			cal.add(Calendar.DAY_OF_WEEK, 1);
		}
		
		Calendar cal_bt = Calendar.getInstance();
		cal_bt.setTime(getBloqueTurno().getHoraFin());
		
		int hora_fechaPunt 	= cal.get(Calendar.HOUR_OF_DAY),
			min_fechaPunt	= cal.get(Calendar.MINUTE),
			hora_f_bt 	= cal_bt.get(Calendar.HOUR_OF_DAY),
			min_f_bt	= cal_bt.get(Calendar.MINUTE);
				
		
		//Si estamos antes del cierre de atencion, se tiene que devolver ese dia
		if ((hora_fechaPunt < hora_f_bt ) ||( (hora_fechaPunt == hora_f_bt ) && (min_fechaPunt < min_f_bt) )){
			//Establezco la hora final para que la proxima vez q se pida el siguiente dia quede fuera
			cal.set(Calendar.HOUR_OF_DAY, hora_f_bt);
			cal.set(Calendar.MINUTE, min_f_bt);
		}else{
			//Ya no hay tiempo de atenderse, si estamos parados en el mismo dia, le sumo la cantidad de dias que tenga la frecuencia
			Calendar cal_Punta = Calendar.getInstance();
			cal_Punta.setTime(fechaPuntero);
			if (cal.get(Calendar.DAY_OF_YEAR) == cal_Punta.get(Calendar.DAY_OF_YEAR)){
				cal.add(Calendar.DAY_OF_WEEK, getFrecuencia());
			}
		}
		
		Date resul = null;
		//Si la nueva fecha es mas futura que el fin del contrato, no se toma
		if (!cal.getTime().after(getBloqueTurno().getDia().getAgenda().getContrato().getFechaHasta())){
			resul = cal.getTime();
		}
		
		return resul;
	}

	/**
	 * Devuelve la fecha mas cercana hacia el pasado dentro de las del bloque turno
	 * @param bloqueTurno
	 * @param fechaPuntero
	 * @return
	 */
	public Date obtenerFechaAnterior(Date fechaPuntero) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaPuntero);
		
		//Me posiciono en el dia que corresponde el bloque turno
		while (cal.get(Calendar.DAY_OF_WEEK) != getBloqueTurno().getDia().getNumero_semana()){
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		//Si la fecha que encontre no es anterior le aplico la frecuencia
		//Si es la misma fecha y esta dentro de la franja no le aplico
		/*
	 		if (!cal.getTime().before(fechaPuntero) ||
				!(cal.getTime().compareTo(fechaPuntero) == 0 && dentroDeLaFranja(cal.getTime()))){
		*/
		if (!cal.getTime().before(fechaPuntero)){
			cal.add(Calendar.DAY_OF_WEEK, -getFrecuencia());
		}
		
		Date resul = null;
		//Si la nueva fecha es mas vieja que el inicio del contrato, no se toma
		if (!cal.getTime().before(getBloqueTurno().getDia().getAgenda().getContrato().getFechaDesde())){
			resul = cal.getTime();
		}
		
		return resul;
	}
	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof RepeticionBloqueTurno) {
			RepeticionBloqueTurno o = (RepeticionBloqueTurno) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BloqueTurno getBloqueTurno() {
		return bloqueTurno;
	}

	public void setBloqueTurno(BloqueTurno bloqueTurno) {
		this.bloqueTurno = bloqueTurno;
	}

	public Integer getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	public abstract String getNombre();

}