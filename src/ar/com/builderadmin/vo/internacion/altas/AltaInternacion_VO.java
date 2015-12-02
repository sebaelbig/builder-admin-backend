package ar.com.builderadmin.vo.internacion.altas;

import java.util.Date;

import ar.com.builderadmin.model.internacion.altas.AltaInternacion;
import ar.com.builderadmin.vo.I_ValueObject;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class AltaInternacion_VO implements I_ValueObject<AltaInternacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	
	private Integer version;
	
	private String nombre;
	
	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	private Date fechaEstablecido;
	
	
	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof AltaInternacion_VO) {
			AltaInternacion_VO o = (AltaInternacion_VO) objeto;
			return o.getNombre().equals(this.getNombre());
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaEstablecido() {
		return fechaEstablecido;
	}

	public void setFechaEstablecido(Date fechaEstablecido) {
		this.fechaEstablecido = fechaEstablecido;
	}

	public  AltaInternacion_VO(){
		setFechaEstablecido(new Date());
	}
	

	public void setObject(AltaInternacion objeto) {
		// TODO Auto-generated method stub
		
	}

	public void setObject(AltaInternacion objeto, int profundidadActual, int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}

	public AltaInternacion toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public AltaInternacion toObject(int profundidadActual, int profundidadDeseada) {
		// TODO Auto-generated method stub
		return null;
	}
	
}