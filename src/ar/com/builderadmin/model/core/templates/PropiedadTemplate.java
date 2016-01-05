package ar.com.builderadmin.model.core.templates;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.vo.core.templates.PropiedadTemplate_VO;

@Entity
@Table( name = "propiedad_template")
public class PropiedadTemplate implements Serializable, I_Entidad {

	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_propiedad_template", name = "seq_propiedad_template", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_propiedad_template")
	private Long id;

	@Version
	private Integer version;

	@ManyToOne
	@JoinColumn(name = "id_propiedad")
	private Propiedad propiedad;

	@Column(columnDefinition = "text")
	private String contenido;

	@ManyToOne
	@JoinColumn(name = "id_template")
	private Template template;

	
	/**
	 * Artibutos para la posición dentro del documento
	 * 
	 * ancho 100 (Toda la pagina)
	 * ancho  50 (Mitad  de pagina)
	 * 
	 * 
	 * orden
	 * 
	 */
	private Integer orden;
	private Integer ancho = 100;
	private String margen = "left";
	
	
	public PropiedadTemplate() {
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PropiedadTemplate) {
			PropiedadTemplate o = (PropiedadTemplate) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the ancho
	 */
	public Integer getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the margen
	 */
	public String getMargen() {
		return margen;
	}

	/**
	 * @param margen the margen to set
	 */
	public void setMargen(String margen) {
		this.margen = margen;
	}

	public PropiedadTemplate_VO toValueObject() {
		return new PropiedadTemplate_VO(this);
	}
	

}
