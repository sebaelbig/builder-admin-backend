package ar.com.builderadmin.model.designacion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;

@Entity
@Table(name = "unidad_de_medida")
public class UnidadDeMedida implements I_Entidad, Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Boolean borrado = false;

	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidad_de_medida_seq")
	@SequenceGenerator( name = "unidad_de_medida_seq", sequenceName = "unidad_de_medida_seq", allocationSize = 1)
	private Long id;
	
	@Version
	private Integer version;
	private String nombre;
	private String descripcion;
	private boolean granel;

	public UnidadDeMedida() {
		
	}
	
	
	public Long getId() {
		return id;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getGranel() {
		return granel;
	}

	public void setGranel(boolean granel) {
		this.granel = granel;
	}



}
