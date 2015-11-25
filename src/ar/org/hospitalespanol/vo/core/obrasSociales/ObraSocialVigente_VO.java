package ar.org.hospitalespanol.vo.core.obrasSociales;

import java.util.Date;

import javax.persistence.Column;

import ar.org.hospitalespanol.model.core.obrasSociales.EstadoObraSocial;
import ar.org.hospitalespanol.model.core.obrasSociales.ObraSocialVigente;

public class ObraSocialVigente_VO extends EstadoObraSocial_VO {

	@Column(name = "fecha_vigencia")
	private Date fechaVigencia;

	public ObraSocialVigente_VO() {
		setFechaVigencia(new Date());
		setNombre(EstadoObraSocial.VIGENTE);
	}

	public ObraSocialVigente_VO(ObraSocialVigente estadoObraSocial) {
		setObject(estadoObraSocial);
	}

	public ObraSocialVigente_VO(ObraSocialVigente estadoObraSocial,
			int profundidadActual, int profundidadDeseada) {
		setObject(estadoObraSocial, profundidadActual, profundidadDeseada);
	}

	public void setObject(ObraSocialVigente objeto) {
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());
		setFechaVigencia(objeto.getFechaVigencia());
	}

	@Override
	public ObraSocialVigente toObject() {
		return new ObraSocialVigente(getId(), getVersion(), getNombre(),
				getFechaVigencia());
	}

	@Override
	public Boolean habilitada() {
		return true;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	@Override
	public Date getFecha() {
		return this.fechaVigencia;
	}
}
