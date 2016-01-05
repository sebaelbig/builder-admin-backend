package ar.com.builderadmin.model.historiaClinica.templates;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.pojomatic.annotations.AutoProperty;
import org.pojomatic.annotations.DefaultPojomaticPolicy;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import ar.com.builderadmin.model.I_Entidad;

@Entity
@DiscriminatorColumn(name = "template_de_descripcion", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("template_de_descripcion_base")
@Table( name = "template_de_descripcion")
@AutoProperty(policy = DefaultPojomaticPolicy.TO_STRING)
public class TemplateDeDescripcion implements I_Entidad {

	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templateDeDescripcion_seq")
	@SequenceGenerator( name = "templateDeDescripcion_seq", sequenceName = "templateDeDescripcion_seq")
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;

	private String titulo;
    
	@Column(columnDefinition="text")
    private String texto;
	

	@Version
	private Integer version;

	public TemplateDeDescripcion() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
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

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}