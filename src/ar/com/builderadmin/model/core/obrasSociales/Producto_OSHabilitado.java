package ar.com.builderadmin.model.core.obrasSociales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.vo.core.obrasSociales.Producto_OSHabilitado_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity
@DiscriminatorValue("producto_os_habilitado")
public class Producto_OSHabilitado extends EstadoProducto_OS {


	@Column(name="fecha_habilitacion")
	@Temporal(TemporalType.DATE)
	private Date fechaHabilitacion;

	public Producto_OSHabilitado(){
	}
	
	public Producto_OSHabilitado(Long id, Integer version, Date fechaHabilitacion){
		super(id,version);
		setFechaHabilitacion(fechaHabilitacion);
	}
	
	/* Metodos */
	@Override
	public Producto_OSHabilitado_VO toValueObject(){
		return new Producto_OSHabilitado_VO(this);
	}
	
	
	/* Setters y getters */
	public Date getFechaHabilitacion() {
		return fechaHabilitacion;
	}

	public void setFechaHabilitacion(Date fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}

}
