package ar.com.builderadmin.model.core.usuarios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/**
 * Estudio
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:39 a.m.
 */
//@Entity @Table( name = "tipo_documento")
public class TipoDocumento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String DNI = "D.N.I.";

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_tipo_documento", name = "seq_tipo_documento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_documento")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 35)
	private String nombre;

	@Column(length = 10)
	private String codigo;

	// ---------------- CONSTRUCTOR
	public TipoDocumento() {
		super();
	}

	public TipoDocumento(String nombre) {
		super();
		this.nombre = nombre;
	}

	// ------------------------ OPERACIONES
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoDocumento) {
			TipoDocumento o = (TipoDocumento) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	// ---------------- GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
