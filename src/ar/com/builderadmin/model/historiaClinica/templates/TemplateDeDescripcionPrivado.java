package ar.com.builderadmin.model.historiaClinica.templates;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ar.com.builderadmin.model.core.usuarios.perfiles.Perfil;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPrivado_VO;

@Entity 
@DiscriminatorValue("template_de_descripcion_privado")
public class TemplateDeDescripcionPrivado extends TemplateDeDescripcion {

	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	
	// nro de matricula
	public TemplateDeDescripcionPrivado() {
	}

	public TemplateDeDescripcionPrivado_VO toValueObject() {
		return new TemplateDeDescripcionPrivado_VO(this);
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	
}