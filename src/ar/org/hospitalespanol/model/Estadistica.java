package ar.org.hospitalespanol.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.org.hospitalespanol.dao.DAO_Utils;

import com.google.gson.Gson;

/**
 * Estadistica
 * 
 */
@Entity
public class Estadistica {

	@Id
	@SequenceGenerator(name = "seq_estadistica", sequenceName = "seq_estadistica", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estadistica")
	private Long id;

	/**
	 * Alert priority
	 */
	private Integer prioridad;

	/**
	 * Date time of the alert.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	private String str_timestamp;

	/**
	 * Description.
	 */
	private String description;

	/**
	 * User that generated the alert
	 */
	private String usuario;

	/**
	 * User that generated the alert
	 */
	private String funcion;

	private Integer anio;
	private Integer mes;
	private Integer dia;
	private Integer hora;
	private Integer minuto;
	private Integer segundo;

	public Estadistica() {
	}

	/**
	 * 
	 * @param usuario
	 * @param clase
	 * @param detalle
	 */
	public Estadistica(String usuario, String detalle, String fx) {
		this.setDescription(detalle);
		this.setUsuario(usuario);
		this.setTimestamp(new Date());
		this.setPrioridad(0);
		this.setFuncion(fx);
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return this.timestamp;
	}

	/**
	 * @param time
	 *            the timestamp to set
	 */
	public void setTimestamp(Date time) {
		this.timestamp = time;

		if (time != null) {

			this.setStr_timestamp(DAO_Utils.formatDateHour(time));

			Calendar cal = Calendar.getInstance();
			cal.setTime(time);

			this.setAnio(cal.get(Calendar.YEAR));
			this.setMes(cal.get(Calendar.MONTH));
			this.setDia(cal.get(Calendar.DAY_OF_MONTH));

			this.setHora(cal.get(Calendar.HOUR_OF_DAY));
			this.setMinuto(cal.get(Calendar.MINUTE));
			this.setSegundo(cal.get(Calendar.SECOND));

		}
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the str_timestamp
	 */
	public String getStr_timestamp() {
		return str_timestamp;
	}

	/**
	 * @param str_timestamp
	 *            the str_timestamp to set
	 */
	public void setStr_timestamp(String str_timestamp) {
		this.str_timestamp = str_timestamp;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	/**
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad
	 *            the prioridad to set
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * @param funcion
	 *            the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}

	/**
	 * @param dia
	 *            the dia to set
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}

	/**
	 * @return the hora
	 */
	public Integer getHora() {
		return hora;
	}

	/**
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(Integer hora) {
		this.hora = hora;
	}

	/**
	 * @return the minuto
	 */
	public Integer getMinuto() {
		return minuto;
	}

	/**
	 * @param minuto
	 *            the minuto to set
	 */
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	/**
	 * @return the segundo
	 */
	public Integer getSegundo() {
		return segundo;
	}

	/**
	 * @param segundo
	 *            the segundo to set
	 */
	public void setSegundo(Integer segundo) {
		this.segundo = segundo;
	}

}
