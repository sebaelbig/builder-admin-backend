package ar.com.builderadmin.model.core.areas;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.areas.DiaDeAtencion_VO;

//@Entity
@Table( name = "dia_de_atencion")
public class DiaDeAtencion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_dia_atencion", name = "seq_dia_atencion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dia_atencion")
	private Long id;

	@Version
	private Integer version;

	private String nombre;

	private Integer numero;

	public DiaDeAtencion() {
	}

	public DiaDeAtencion(Long id, Integer version, String nombre, Integer numero) {
		super();
		this.id = id;
		this.version = version;
		this.nombre = nombre;
		this.numero = numero;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public DiaDeAtencion_VO toValueObject() {
		return new DiaDeAtencion_VO(this.getId(), getVersion(), getNombre(),
				getNumero());
	}
	
	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

}
