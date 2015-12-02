package ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama;

import java.io.Serializable;

import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.CamaLibre;
import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.CamaOcupada;
import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.EstadoCama;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class CamaLibre_VO extends EstadoCama_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructores
	public CamaLibre_VO() {
		super();
		setNombre(EstadoCama.LIBRE);
	}

	public CamaLibre_VO(CamaLibre camaLibre) {
		this.setObject(camaLibre);
	}

	public CamaLibre_VO(CamaOcupada camaOcupada) {
		this.setObject(camaOcupada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CamaLibre_VO) {
			CamaLibre_VO o = (CamaLibre_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public void setObject(EstadoCama objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setNombre(objeto.getNombre());
	}

	@Override
	public void setObject(EstadoCama objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setNombre(objeto.getNombre());
	}

	@Override
	public EstadoCama toObject() {
		CamaLibre resul = new CamaLibre();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		resul.setNombre(this.getNombre());
		return resul;
	}

}