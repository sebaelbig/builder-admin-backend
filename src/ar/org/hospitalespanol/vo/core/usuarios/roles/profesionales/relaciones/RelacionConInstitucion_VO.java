package ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.relaciones;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.relaciones.RelacionConInstitucion;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class RelacionConInstitucion_VO implements
		I_ValueObject<RelacionConInstitucion> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String nombre;

	public RelacionConInstitucion_VO() {
		this.setNombre(RelacionConInstitucion.OTRO);
	}

	public RelacionConInstitucion_VO(RelacionConInstitucion rela) {
		this.setObject(rela);
	}

	public RelacionConInstitucion_VO(
			RelacionConInstitucion relacionConInstitucion,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(relacionConInstitucion, profundidadActual,
				profundidadDeseada);
	}

	@Override
	public void setObject(RelacionConInstitucion rela) {
		this.setId(rela.getId());
		this.setVersion(rela.getVersion());
		this.setNombre(RelacionConInstitucion.OTRO);

	}

	@Override
	public void setObject(RelacionConInstitucion rela, int profundidadActual,
			int profundidadDeseada) {

		this.setId(rela.getId());
		this.setVersion(rela.getVersion());
		this.setNombre(RelacionConInstitucion.OTRO);

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != 0 && profundidadActual < profundidadDeseada) {
			// profundidadActual+1, profundidadDeseada
		}

	}

	@Override
	public RelacionConInstitucion toObject() {
		RelacionConInstitucion r = new RelacionConInstitucion();

		r.setId(this.getId());
		r.setVersion(this.getVersion());
		r.setNombre(RelacionConInstitucion.OTRO);

		return r;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
