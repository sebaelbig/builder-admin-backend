package ar.org.hospitalespanol.vo.core.obrasSociales;

import java.util.Date;

import ar.org.hospitalespanol.model.core.obrasSociales.ContratoProductoSuspendido;

public class ContratoProductoSuspendido_VO extends EstadoContratoProducto_VO {

	private Date fechaSuspension;

	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}

	public ContratoProductoSuspendido_VO(
			ContratoProductoSuspendido estadoContratoProducto) {
		setObject(estadoContratoProducto);
	}

	public void setObject(ContratoProductoSuspendido objeto) {
		setFechaSuspension(objeto.getFechaSuspension());
		setId(objeto.getId());
		setMotivo(objeto.getMotivo());
		setVersion(objeto.getVersion());
	}

	@Override
	public ContratoProductoSuspendido toObject() {
		return new ContratoProductoSuspendido(getId(), getVersion(),
				getMotivo(), getFechaSuspension());
	}

}