package ar.com.builderadmin.model.core.obrasSociales;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ar.com.builderadmin.vo.core.obrasSociales.Epo_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

//@Entity
public class Epo extends Subproducto_OS {

	@ManyToOne
	@JoinColumn(name = "id_localidad")
	private Localidad localidad;

	public Epo() {

	}

	public Epo(Long id, Integer version, String codigo, String nombre,
			Localidad localidad) {
		super(id, version, nombre, codigo);
		setLocalidad(localidad);
	}

	/**
	 * Metodos
	 */

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Epo) {
			Epo o = (Epo) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public Epo_VO toValueObject() {
		return new Epo_VO(this);
	}

	/**
	 * 
	 * Setters y Getters
	 */

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
}
