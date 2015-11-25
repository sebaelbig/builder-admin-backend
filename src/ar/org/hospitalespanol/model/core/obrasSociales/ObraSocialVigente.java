package ar.org.hospitalespanol.model.core.obrasSociales;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.org.hospitalespanol.vo.core.obrasSociales.ObraSocialVigente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
@Entity 
@DiscriminatorValue("os_vigente")
public class ObraSocialVigente extends EstadoObraSocial implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Column(name="fecha_vigencia")
	@Temporal(TemporalType.DATE)
	private Date fechaVigencia;

	//Constructores
	public ObraSocialVigente(){
		setNombre(EstadoObraSocial.VIGENTE);
	}

	public ObraSocialVigente(Long id, Integer version, String nombre,  Date fechaVigencia){
		super(id, version, nombre);
		setFechaVigencia(fechaVigencia);
		
	}
	
	
	/* Metodos */
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ObraSocialVigente) {
			ObraSocialVigente o = (ObraSocialVigente) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public ObraSocialVigente_VO toValueObject(){
		return new ObraSocialVigente_VO(this);
	}
	

	
	//Getters & Setters
	
	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getMotivo(){
		return "";
	}

}