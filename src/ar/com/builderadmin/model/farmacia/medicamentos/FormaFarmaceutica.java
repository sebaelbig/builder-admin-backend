package ar.com.builderadmin.model.farmacia.medicamentos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.farmacia.medicamentos.FormaFarmaceutica_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "forma_farmaceutica")
public class FormaFarmaceutica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_forma_farmaceutica", name = "seq_forma_farmaceutica", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_forma_farmaceutica")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Nombre de la medicamento
	 */
	@Column(length = 200)
	private String nombre;

	/**
	 * Descripcion de la medicamento
	 */
	@Column(length = 200)
	private String descripcion;

	// Constructores
	public FormaFarmaceutica() {

	}

	public FormaFarmaceutica(Long id2, Integer version2, String nombre2,
			String descripcion2) {
		setId(id2);
		setVersion(version2);
		setNombre(nombre2);
		setDescripcion(descripcion2);
	}

	public FormaFarmaceutica(String string) {
		setNombre(string);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof FormaFarmaceutica) {
			FormaFarmaceutica o = (FormaFarmaceutica) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public FormaFarmaceutica_VO toValueObject() {
		return new FormaFarmaceutica_VO(this);
	}

	public FormaFarmaceutica_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new FormaFarmaceutica_VO(this, profundidadActual,
				profundidadDeseada);
	}

}