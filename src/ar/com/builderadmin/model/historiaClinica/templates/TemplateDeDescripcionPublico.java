package ar.com.builderadmin.model.historiaClinica.templates;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

@Entity 
@DiscriminatorValue("template_de_descripcion_publico")
public class TemplateDeDescripcionPublico extends TemplateDeDescripcion{

	@OneToOne
	@JoinColumn(name = "id_servicio")
	private Servicio servicio;

	private String nombreServicio;


	public TemplateDeDescripcionPublico_VO toValueObject() {
		return new TemplateDeDescripcionPublico_VO(this);
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	

}