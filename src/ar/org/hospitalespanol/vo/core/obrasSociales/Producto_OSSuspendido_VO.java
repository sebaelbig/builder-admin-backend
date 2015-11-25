package ar.org.hospitalespanol.vo.core.obrasSociales;

import java.util.Date;

import javax.persistence.Column;

import ar.org.hospitalespanol.model.core.obrasSociales.Producto_OSSuspendido;

public class Producto_OSSuspendido_VO extends EstadoProducto_OS_VO {

	private Date fechaSuspension;

	@Column(length = 150)
	private String motivo;

	public Producto_OSSuspendido_VO() {
		this.setFechaSuspension(new Date());
	}

	public Producto_OSSuspendido_VO(Producto_OSSuspendido estadoProducto) {
		this.setObject(estadoProducto);
	}

	public Producto_OSSuspendido_VO(String motivo2) {
		this.setMotivo(motivo2);
		this.setFechaSuspension(new Date());
	}

	public void setObject(Producto_OSSuspendido objeto) {
		setFechaSuspension(objeto.getFechaSuspension());
		setId(objeto.getId());
		setMotivo(objeto.getMotivo());
		setVersion(objeto.getVersion());
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}

	@Override
	public Boolean habilitada() {
		return false;
	}

	@Override
	public Date getFecha() {
		return this.getFechaSuspension();
	}

	@Override
	public Producto_OSSuspendido toObject() {
		return new Producto_OSSuspendido(getId(), getVersion(), getMotivo(),
				getFechaSuspension());
	}

	@Override
	public Producto_OSSuspendido toObject(int profundidadActual,
			int profundidadDeseada) {
		return new Producto_OSSuspendido(getId(), getVersion(), getMotivo(),
				getFechaSuspension());
	}

}
