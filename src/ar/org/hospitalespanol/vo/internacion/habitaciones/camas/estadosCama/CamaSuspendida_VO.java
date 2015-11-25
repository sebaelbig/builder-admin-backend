package ar.org.hospitalespanol.vo.internacion.habitaciones.camas.estadosCama;

import java.io.Serializable;
import java.util.Date;

import ar.org.hospitalespanol.model.internacion.habitaciones.camas.estadosCama.CamaSuspendida;
import ar.org.hospitalespanol.model.internacion.habitaciones.camas.estadosCama.EstadoCama;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class CamaSuspendida_VO extends EstadoCama_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fechaSuspendida;

	private String motivo;

	// Constructores
	public CamaSuspendida_VO(String motivo2) {
		super();
		setNombre(EstadoCama.SUSPENDIDO);
		setMotivo(motivo2);
	}

	// Constructores
	public CamaSuspendida_VO(CamaSuspendida objeto) {
		setNombre(EstadoCama.SUSPENDIDO);
		setObject(objeto);
	}

	// Constructores
	public CamaSuspendida_VO(CamaSuspendida objeto, int profundidadActual,
			int profundidadDeseada) {
		super();
		setNombre(EstadoCama.SUSPENDIDO);
		setObject(objeto, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CamaSuspendida_VO) {
			CamaSuspendida_VO o = (CamaSuspendida_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setObject(CamaSuspendida objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setFechaSuspendida(objeto.getFechaSuspendida());
		this.setMotivo(objeto.getMotivo());
	}

	public void setObject(CamaSuspendida objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setFechaSuspendida(objeto.getFechaSuspendida());
		this.setMotivo(objeto.getMotivo());
	}


	@Override
	public CamaSuspendida toObject() {
		CamaSuspendida resul = new CamaSuspendida();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		resul.setFechaSuspendida(this.getFechaSuspendida());
		resul.setMotivo(this.getMotivo());
		return resul;
	}

	public Date getFechaSuspendida() {
		return fechaSuspendida;
	}

	public void setFechaSuspendida(Date fechaSuspendida) {
		this.fechaSuspendida = fechaSuspendida;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}