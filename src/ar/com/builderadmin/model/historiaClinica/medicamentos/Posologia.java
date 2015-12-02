package ar.com.builderadmin.model.historiaClinica.medicamentos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Indica como se administra el medicamento
 * 
 * Dosis: 10 Unidad de medida: mg
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table
public class Posologia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_posologia", name = "seq_posologia", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_posologia")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Nombre de la posologia
	 */
	@Column(length = 40)
	private String nombre;

	/**
	 * Descripcion de la posologia
	 */
	@Column(length = 200)
	private String descripcion;

	/**
	 * Nombre que se utiliza para hacer referencia al profesional.
	 * 
	 * Por ejemplo: -Nombre: Ginecologia -Nombre profesional: Ginecologo
	 */
	@Column(length = 40, name = "nombre_profesional")
	private String nombreProfesional;

	// Constructores
	public Posologia() {

	}

	public Posologia(Long id2, Integer version2, String nombre2,
			String descripcion2) {
		setId(id2);
		setVersion(version2);
		setNombre(nombre2);
		setDescripcion(descripcion2);
	}

	public Posologia(String string) {
		setNombre(string);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Posologia) {
			Posologia o = (Posologia) objeto;
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

	public String getNombreProfesional() {
		return nombreProfesional;
	}

	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}

	// public Posologia_VO toValueObjet(){
	// return new Posologia_VO(this);
	// }
	//
	// public Posologia_VO toValueObjet(int profundidadActual, int
	// profundidadDeseada) {
	// return new Posologia_VO(this, profundidadActual, profundidadDeseada);
	// }

}