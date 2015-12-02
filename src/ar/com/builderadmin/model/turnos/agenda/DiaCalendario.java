package ar.com.builderadmin.model.turnos.agenda;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.DiscriminatorValue;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
//@Entity @Table
@DiscriminatorValue("dia_calendario")
public class DiaCalendario extends Dia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String descripcion; 
	
	public DiaCalendario(Long id, Integer version, Date fecha2, String descripcion2) {
		setId(id);
		setVersion(version);
		setFecha(fecha2);
		setDescripcion(descripcion2);
	}

	public DiaCalendario() {
	}

	public DiaCalendario(Date fecha2, String descripcion2) {
		setFecha(fecha2);
		setDescripcion(descripcion2);
	}

	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof DiaCalendario) {
			Dia o = (DiaCalendario) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString(){
		DateFormat f= new SimpleDateFormat("dd/MM/yyyy");
		return f.format(getFecha());
	}
}