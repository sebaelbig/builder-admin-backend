package ar.com.builderadmin.model.alerta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.pojomatic.annotations.DefaultPojomaticPolicy;

import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.model.E_Priority;

/**
 * FX Alert
 * 
 * @author fgonzalez
 * 
 */
//@Entity
@Table
@AutoProperty(policy = DefaultPojomaticPolicy.TO_STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_alerta", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("alerta_horus")
public class Alerta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3116901662984319889L;

	/**
	 * Entity ID.
	 */
	private Boolean borrado = false;

	@Id
	@SequenceGenerator( 
						name = "seq_alert", 
						sequenceName = "seq_alert", 
						allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alert")
	private Long id;

	/**
	 * Alert priority
	 */
	@Enumerated(EnumType.STRING)
	private E_Priority priority;

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
	 * Involved Object ID
	 */
	private Long objectId;

	/**
	 * Involved Object Class Name
	 */
	private String objectClassName;

	/**
	 * User that generated the alert
	 */
	private String usuario;

	public Alerta() {
	}

	public Alerta(String usuario, Date date, Long idObj, String className,
			String detalle, E_Priority prioridad) {
		
		this.setObjectId(idObj);
		this.setObjectClassName(className);
		
		this.setDescription(detalle);
		this.setUsuario(usuario);
		this.setTimestamp(date);
		this.setPriority(prioridad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return Pojomatic.equals(this, obj);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the priority
	 */
	public E_Priority getPriority() {
		return this.priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(E_Priority priority) {
		this.priority = priority;
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
		if (time!=null){
			this.setStr_timestamp(DAO_Utils.formatDateHour(time));
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
	 * @return the objectId
	 */
	public Long getObjectId() {
		return this.objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the objectClassName
	 */
	public String getObjectClassName() {
		return this.objectClassName;
	}

	/**
	 * @param objectClassName
	 *            the objectClassName to set
	 */
	public void setObjectClassName(String objectClassName) {
		this.objectClassName = objectClassName;
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

	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the str_timestamp
	 */
	public String getStr_timestamp() {
		return str_timestamp;
	}

	/**
	 * @param str_timestamp the str_timestamp to set
	 */
	public void setStr_timestamp(String str_timestamp) {
		this.str_timestamp = str_timestamp;
	}
	
}
