package ar.org.hospitalespanol.model.core.obrasSociales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.vo.core.obrasSociales.Coeficientes_UA_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity @Table( name="coeficientes_ua")
public class Coeficientes_UA {

	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_coeficientes_ua", name = "seq_coeficientes_ua",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_coeficientes_ua")
	private Long id;
	
	@Version
	private Integer version;
		
	/**
	 * Porcentaje
	 */
	private Float porcentaje;

	
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private ContratoDeProducto contrato;
	
	
	@ManyToOne
	@JoinColumn(name="id_tipo_coeficiente")
	private TipoCoeficiente tipoCoeficiente;
	
	
	public Coeficientes_UA(Long id, Integer version, Float porcentaje, TipoCoeficiente tipoCoeficiente){
		this.setId(id);
		this.setPorcentaje(porcentaje);
		this.setVersion(version);
		this.setTipoCoeficiente(tipoCoeficiente);
	}
	
	
	public Coeficientes_UA(){}
	
	/**
	 * Setter y Getters 
	 * 
	 */
	

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
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


	
	/**
	 * Metodos
	 */
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Coeficientes_UA) {
			Coeficientes_UA o = (Coeficientes_UA) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public ContratoDeProducto getContrato() {
		return contrato;
	}


	public void setContrato(ContratoDeProducto contrato) {
		this.contrato = contrato;
	}


	public TipoCoeficiente getTipoCoeficiente() {
		return tipoCoeficiente;
	}

	public void setTipoCoeficiente(TipoCoeficiente tipoCoeficiente) {
		this.tipoCoeficiente = tipoCoeficiente;
	}


	public Coeficientes_UA_VO toValueObject(int profActual, int profundidadDeseada) {
		return new Coeficientes_UA_VO(this, profActual, profundidadDeseada);
	}
	
	public Coeficientes_UA_VO toValueObject(){
		return new Coeficientes_UA_VO(this);
	}
	
}
