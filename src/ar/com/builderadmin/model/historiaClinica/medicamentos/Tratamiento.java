package ar.com.builderadmin.model.historiaClinica.medicamentos;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table
public class Tratamiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_tratamiento", name = "seq_tratamiento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tratamiento")
	private Long id;

	@Version
	private Integer version;

	@Enumerated(EnumType.STRING)
	private TipoDeTratamiento tipo = TipoDeTratamiento.CRONICO;

	// Constructores
	public Tratamiento() {

	}

	public Tratamiento(Long id2, Integer version2, String nombre2,
			String descripcion2) {
		setId(id2);
		setVersion(version2);
	}

	public Tratamiento(String string) {
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Tratamiento) {
			Tratamiento o = (Tratamiento) objeto;
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

	@Override
	public String toString() {
		return null; // this.getNombre();
	}

	public TipoDeTratamiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeTratamiento tipo) {
		this.tipo = tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoDeTratamiento.valueOf(tipo);
	}

	// public Tratamiento_VO toValueObjet(){
	// return new Tratamiento_VO(this);
	// }
	//
	// public Tratamiento_VO toValueObjet(int profundidadActual, int
	// profundidadDeseada) {
	// return new Tratamiento_VO(this, profundidadActual, profundidadDeseada);
	// }

}