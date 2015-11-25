package ar.org.hospitalespanol.vo.historiaClinica.templates;

import ar.org.hospitalespanol.model.historiaClinica.templates.TemplateDeDescripcion;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class TemplateDeDescripcion_VO implements
		I_ValueObject<TemplateDeDescripcion> {

	private Long id;
	private Boolean borrado = false;

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	private String titulo;

	private String texto;

	private Integer version;
	

	public TemplateDeDescripcion_VO() {

	}

	public TemplateDeDescripcion_VO(TemplateDeDescripcion template) {
		setObject(template);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void setObject(TemplateDeDescripcion objeto) {
		setId(objeto.getId());
		setTitulo(objeto.getTitulo());
		setTexto(objeto.getTexto());
		setVersion(objeto.getVersion());
	}

	@Override
	public TemplateDeDescripcion toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(TemplateDeDescripcion objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
