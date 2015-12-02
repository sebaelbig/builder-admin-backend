package ar.com.builderadmin.vo.core.obrasSociales;

import ar.com.builderadmin.model.core.obrasSociales.Epo;

public class Epo_VO extends Subproducto_OS_VO {

	private Localidad_VO localidad;

	public Epo_VO(Epo epo) {
		setObject(epo);
	}

	public Epo_VO() {

	}

	public void setObject(Epo objeto) {
		super.setObject(objeto);
		setLocalidad(objeto.getLocalidad().toValueObject());

	}

	@Override
	public Epo toObject() {
		return new Epo(getId(), getVersion(), getCodigo(), getNombre(),
				getLocalidad().toObject());
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Epo_VO) {
			Epo_VO o = (Epo_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Localidad_VO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad_VO localidad_VO) {
		this.localidad = localidad_VO;
	}

	@Override
	public String toString() {
		return getNombre();
	}

}
