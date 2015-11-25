package ar.org.hospitalespanol.vo.historiaClinica.templates;

import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.historiaClinica.templates.TemplateDeDescripcionPublico;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;

public class TemplateDeDescripcionPublico_VO extends TemplateDeDescripcion_VO {

	private Long idServicio;
	private String nombreServicio;	
	private Servicio_VO servicio;
	

	public TemplateDeDescripcionPublico_VO() {

	}

	public TemplateDeDescripcionPublico_VO(TemplateDeDescripcionPublico template) {
		super.setObject(template);
		setObject(template);
	}

	public void setObject(TemplateDeDescripcionPublico objeto) {
		setIdServicio(objeto.getServicio().getId());
		setNombreServicio(objeto.getServicio().getNombre());
	}

	public TemplateDeDescripcionPublico_VO(Servicio servicio) {
		setIdServicio(servicio.getId());
		setNombreServicio(servicio.getNombre());
	}

	@Override
	public TemplateDeDescripcionPublico toObject() {
		TemplateDeDescripcionPublico template = new TemplateDeDescripcionPublico();
		
		template.setBorrado(this.getBorrado());
		template.setId(this.getId());
		template.setTitulo(this.getTitulo());
		template.setTexto(this.getTexto());
		template.setVersion(this.getVersion());

		template.setServicio(new Servicio());
		template.getServicio().setId(getIdServicio());
		template.getServicio().setNombre(getNombreServicio());
		
		return template;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public String getTituloServicio() {
		return nombreServicio;
	}

	public void setTituloServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * @param nombreServicio the nombreServicio to set
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * @return the servicio
	 */
	public Servicio_VO getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Servicio_VO servicio) {
		this.servicio = servicio;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return this.getId().equals(((TemplateDeDescripcionPublico_VO) obj).getId());
		} catch (Exception e) {
			return false;
		}
	}

}