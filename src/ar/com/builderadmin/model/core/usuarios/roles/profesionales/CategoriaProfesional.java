package ar.com.builderadmin.model.core.usuarios.roles.profesionales;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.CategoriaProfesional_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
@Entity @Table( name = "categoria_profesional")
public class CategoriaProfesional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_categoria_profesional", name = "seq_categoria_profesional", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_profesional")
	private Long id;

	@Version
	private Integer version;

	public static final String DIFERENCIAL_A = "Diferenciado A";
	public static final String DIFERENCIAL_B = "Diferenciado B";
	public static final String DIFERENCIAL_C = "Diferenciado C";

	public static final String PARTICULAR = "Particular";

	/**
	 * Nombre de la categoria profesional
	 * 
	 * @Column(length=30)
	 * @Column(name="categoria")
	 */
	private String nombre;

	// Constructores
	public CategoriaProfesional() {

	}

	public CategoriaProfesional(String diferenciado) {
		setNombre(diferenciado);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CategoriaProfesional) {
			CategoriaProfesional o = (CategoriaProfesional) objeto;
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

	public CategoriaProfesional_VO toValueObject() {
		return new CategoriaProfesional_VO(this);
	}

	public CategoriaProfesional_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new CategoriaProfesional_VO(this, profundidadActual,
				profundidadDeseada);
	}

}