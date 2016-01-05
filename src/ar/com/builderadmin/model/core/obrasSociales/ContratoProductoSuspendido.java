package ar.com.builderadmin.model.core.obrasSociales;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.builderadmin.vo.core.obrasSociales.ContratoProductoSuspendido_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity
@DiscriminatorValue("contrato_producto_suspendido")
public class ContratoProductoSuspendido extends EstadoContratoProducto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="fecha_suspension")
	@Temporal(TemporalType.DATE)
	private Date fechaSuspension;

	public ContratoProductoSuspendido() {

	}

	public ContratoProductoSuspendido(Long id, Integer version, String motivo,
			Date fechaSuspension) {
		super(id, version, motivo);
		setFechaSuspension(fechaSuspension);
	}

	/* Metodos */

	@Override
	public ContratoProductoSuspendido_VO toValueObject() {
		return new ContratoProductoSuspendido_VO(this);
	}

	/* Setters y Getters */

	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}
}
