package ar.com.builderadmin.vo.core.obrasSociales;

import java.util.Date;

import ar.com.builderadmin.model.core.obrasSociales.Producto_OSHabilitado;

public class Producto_OSHabilitado_VO extends EstadoProducto_OS_VO {

	private Date fechaHabilitacion;

	public Producto_OSHabilitado_VO() {
		this.setFechaHabilitacion(new Date());
	}

	public Producto_OSHabilitado_VO(Producto_OSHabilitado estadoProducto) {
		setObject(estadoProducto);
	}

	public void setObject(Producto_OSHabilitado estadoProducto) {
		setFechaHabilitacion(estadoProducto.getFechaHabilitacion());
		setId(estadoProducto.getId());
		setVersion(estadoProducto.getVersion());
	}

	public Date getFechaHabilitacion() {
		return fechaHabilitacion;
	}

	public void setFechaHabilitacion(Date fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}

	@Override
	public Date getFecha() {
		return this.getFechaHabilitacion();
	}

	@Override
	public Boolean habilitada() {
		return true;
	}

	@Override
	public String getTipo() {
		return this.getClass().getCanonicalName();
	}

	@Override
	public Producto_OSHabilitado toObject() {
		return new Producto_OSHabilitado(getId(), getVersion(),
				getFechaHabilitacion());
	}

	@Override
	public Producto_OSHabilitado toObject(int profundidadActual,
			int profundidadDeseada) {
		return new Producto_OSHabilitado(getId(), getVersion(),
				getFechaHabilitacion());
	}
}
