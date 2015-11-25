package ar.org.hospitalespanol.model.core.datosSalud.nomencladores;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity @Table
public class Nomenclador_EnfermedadesOMS {
	
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_nomenclador_enfermedadesOMS", name = "seq_nomenclador_enfermedadesOMS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_nomenclador_enfermedadesOMS") 
	private Long id;
	
	@Version
	private Integer version;
	
	/**
	 * codigo del nomenclador de enfermedades
	 */
	private String codigo;
	
	/**
	 * descripcion del nomenclador de enfermedades
	 */
	private String descripcion;
	
	/**
	 * prestaciones asociadas al nomenclador
	 */
	private List<EnfermedadOMS> enfermedades;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EnfermedadOMS> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<EnfermedadOMS> enfermedades) {
		this.enfermedades = enfermedades;
	}
}
