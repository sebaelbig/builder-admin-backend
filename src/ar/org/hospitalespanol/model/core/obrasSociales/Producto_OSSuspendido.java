package ar.org.hospitalespanol.model.core.obrasSociales;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.org.hospitalespanol.vo.core.obrasSociales.Producto_OSSuspendido_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity 
@DiscriminatorValue("producto_os_suspendido")
public class Producto_OSSuspendido extends EstadoProducto_OS {

	@Column(name="fecha_suspension")
	@Temporal(TemporalType.DATE)
	private Date fechaSuspension;

	@Column(length = 150)
	private String motivo;
	
	public Producto_OSSuspendido() {

	}

	public Producto_OSSuspendido(Long id, Integer version, String motivo,
			Date fechaSuspension) {
		super(id, version);
		setMotivo(motivo);
		setFechaSuspension(fechaSuspension);
	}

	/* Metodos */
	@Override
	public Producto_OSSuspendido_VO toValueObject() {
		return new Producto_OSSuspendido_VO(this);
	}

	/* Setters y Getters */

	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
