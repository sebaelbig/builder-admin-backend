package ar.org.hospitalespanol.model.core.datosSalud;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;

/**
 * @author svalle
 */

@Entity
@Table( name = "tipo_prestacion_horus")
public class TipoPrestacionHorus implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_tipoPrestacionHorus", name = "seq_tipoPrestacionHorus", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipoPrestacionHorus")
	private Long id;

	@Version
	private Integer version;

	private String codigo;

	private String nombre;

	private String descripcion;

	public TipoPrestacionHorus() {
	}
	
	public TipoPrestacionHorus(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public TipoPrestacionHorus(Long id, Integer version, String nombre,
			String codigo, String descripcion) {
		this.id = id;
		this.version = version;
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;

	}

	public TipoPrestacionHorus(Long id, String codi,
			String nom) {
		this.setId(id);
		this.setCodigo(codi);
		this.setNombre(nom);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoPrestacionHorus) {
			TipoPrestacionHorus o = (TipoPrestacionHorus) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public TipoPrestacionHorus_VO toValueObject() {
		return new TipoPrestacionHorus_VO(this);
	}

	public TipoPrestacionHorus_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new TipoPrestacionHorus_VO(this, profundidadActual,
				profundidadDeseada);
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
