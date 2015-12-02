package ar.com.builderadmin.vo.core.usuarios.roles.profesionales.relaciones;

import ar.com.builderadmin.model.core.usuarios.roles.profesionales.relaciones.Accionista;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.relaciones.RelacionConInstitucion;

public class Accionista_VO extends RelacionConInstitucion_VO {

	public Accionista_VO() {
		this.setNombre(RelacionConInstitucion.ACCIONISTA);
	}

	public Accionista_VO(RelacionConInstitucion relacionConInstitucion) {
		super(relacionConInstitucion);
		this.setNombre(RelacionConInstitucion.ACCIONISTA);
	}

	public Accionista_VO(RelacionConInstitucion relacionConInstitucion,
			int profundidadActual, int profundidadDeseada) {
		super(relacionConInstitucion, profundidadActual, profundidadDeseada);
		this.setNombre(RelacionConInstitucion.ACCIONISTA);
	}

	public void setObject(Accionista rela) {
		this.setId(rela.getId());
		this.setVersion(rela.getVersion());
		this.setNombre(RelacionConInstitucion.ACCIONISTA);
	}
	@Override
	public RelacionConInstitucion toObject() {
		Accionista a = new Accionista();

		a.setId(this.getId());
		a.setVersion(this.getVersion());
		a.setNombre(RelacionConInstitucion.ACCIONISTA);

		return a;
	}

}
