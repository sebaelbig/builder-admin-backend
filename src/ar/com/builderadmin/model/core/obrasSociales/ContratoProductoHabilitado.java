package ar.com.builderadmin.model.core.obrasSociales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.vo.core.obrasSociales.ContratoProductoHabilitado_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity 
@DiscriminatorValue("contrato_producto_habilitado")
public class ContratoProductoHabilitado extends EstadoContratoProducto {



	private static final long serialVersionUID = 1L;

	@Column(name="fecha_habilitacion")
	@Temporal(TemporalType.DATE)
	private Date fechaHabilitacion;

	public ContratoProductoHabilitado(){
		
	}
	
	public ContratoProductoHabilitado(Long id, Integer version, String motivo, Date fechaHabilitacion){
		super(id,version,motivo);
		setFechaHabilitacion(fechaHabilitacion);
	}
	
	/* Metodos */
	
	@Override
	public ContratoProductoHabilitado_VO toValueObject(){
		return new ContratoProductoHabilitado_VO(this);
	}
	
	
	
	
	/* Setters y getters */

	public Date getFechaHabilitacion() {
		return fechaHabilitacion;
	}

	public void setFechaHabilitacion(Date fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}

}
