package ar.com.builderadmin.model.core.templates;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.templates.Propiedad_VO;

@Entity
@Table( name = "propiedad")
public class Propiedad implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_propiedad", name = "seq_propiedad", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_propiedad")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 100)
	private String nombre;
	
	@Column(length = 100)
	private String atributo;

	public Propiedad () {		
	}
	
	public Propiedad(Long id, Integer version, String nombre, String att) {
		setId(id);
		setVersion(version);
		setNombre(nombre);
		setAtributo(att);
	} 
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Propiedad) {
			Propiedad o = (Propiedad) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	
	public Propiedad_VO toValueObjet() {
		return new Propiedad_VO(this);
	}
	
	public Propiedad_VO toValueObjet(int profundidadActual, int profundidadDeseada) {
		return new Propiedad_VO(this);
	}

	/**
	 * @return the atributo
	 */
	public String getAtributo() {
		return atributo;
	}

	/**
	 * @param atributo the atributo to set
	 */
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	
}
