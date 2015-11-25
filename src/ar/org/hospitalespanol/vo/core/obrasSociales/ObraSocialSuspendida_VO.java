package ar.org.hospitalespanol.vo.core.obrasSociales;

import java.util.Date;

import ar.org.hospitalespanol.model.core.obrasSociales.EstadoObraSocial;
import ar.org.hospitalespanol.model.core.obrasSociales.ObraSocialSuspendida;

public class ObraSocialSuspendida_VO extends EstadoObraSocial_VO {

	private Date fechaSuspension;

	private String motivo;

	public ObraSocialSuspendida_VO(ObraSocialSuspendida estadoObraSocial) {
		setObject(estadoObraSocial);
	}

	public ObraSocialSuspendida_VO(ObraSocialSuspendida estadoObraSocial,
			int profundidadActual, int profundidadDeseada) {
		setObject(estadoObraSocial, profundidadActual, profundidadDeseada);
	}

	public ObraSocialSuspendida_VO(String motivo) {
		setFechaSuspension(new Date());
		setMotivo(motivo);
		setNombre(EstadoObraSocial.SUSPENDIDA);
	}

	public void setObject(ObraSocialSuspendida objeto) {
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());
		setFechaSuspension(objeto.getFechaSuspension());
		setMotivo(objeto.getMotivo());
	}

	public void setObject(ObraSocialSuspendida estadoObraSocial,
			int profundidadActual, int profundidadDeseada) {
		setObject(estadoObraSocial);
	}

	@Override
	public ObraSocialSuspendida toObject() {
		return new ObraSocialSuspendida(getId(), getVersion(), getNombre(),
				getMotivo(), getFechaSuspension());
	}

	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}

	@Override
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public Date getFecha() {
		return this.fechaSuspension;
	}

	@Override
	public Boolean habilitada() {
		return false;
	}
}
