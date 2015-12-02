package ar.com.builderadmin.model.alerta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;

import org.pojomatic.Pojomatic;

import ar.com.builderadmin.model.E_Priority;

/**
 * FX Alert
 * 
 * @author segarcia
 *
 */
//@Entity 
@Table
@DiscriminatorValue("alerta_he")
public class AlertaHE extends Alerta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3116901662984319889L;
	
	/**
	 * Entity ID.
	 */
	private String str_id;
	
	public AlertaHE() {}
	
	public AlertaHE(String usuario, Date date, String idObj, Long id, String className, String detalle, E_Priority prioridad) {
		super(usuario, date, id, className, detalle, prioridad);
		
		this.setStr_id(idObj);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return Pojomatic.equals(this, obj);
	}

	/**
	 * @return the str_id
	 */
	public String getStr_id() {
		return str_id;
	}

	/**
	 * @param str_id the str_id to set
	 */
	public void setStr_id(String str_id) {
		this.str_id = str_id;
	}

}
