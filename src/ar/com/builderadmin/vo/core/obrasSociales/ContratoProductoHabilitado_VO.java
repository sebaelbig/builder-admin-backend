package ar.com.builderadmin.vo.core.obrasSociales;

import java.util.Date;

import ar.com.builderadmin.model.core.obrasSociales.ContratoProductoHabilitado;

public class ContratoProductoHabilitado_VO extends EstadoContratoProducto_VO {

	private Date fechaHabilitacion;

	public ContratoProductoHabilitado_VO(
			ContratoProductoHabilitado estadoContratoProducto) {
		setObject(estadoContratoProducto);
	}

	public void setObject(ContratoProductoHabilitado objeto) {

		setFechaHabilitacion(objeto.getFechaHabilitacion());
		setId(objeto.getId());
		setMotivo(objeto.getMotivo());
		setVersion(objeto.getVersion());
	}

	@Override
	public ContratoProductoHabilitado toObject() {
		return new ContratoProductoHabilitado(getId(), getVersion(),
				getMotivo(), getFechaHabilitacion());

	}

	public Date getFechaHabilitacion() {
		return fechaHabilitacion;
	}

	public void setFechaHabilitacion(Date fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}

}
