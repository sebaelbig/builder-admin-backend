package ar.com.builderadmin.model.core;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.vo.core.Parametro_VO;

/**
 * 
 * @author segarcia
 *
 */

//@Entity
@Table( name="parametro")
public class Parametro implements Serializable, I_Entidad {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7553642112124826241L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "datos_accion_seq")
	@SequenceGenerator( name = "datos_accion_seq", sequenceName = "datos_accion_seq", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;
	
	@Version
	private Integer version;
	
	/**
	 * Parameter's name.
	 */
	private String nombre;
	
	/**
	 * Parameter's value.
	 */
	private String valor;
	
	/**
	 * Parameter's type.
	 */
	@Enumerated(EnumType.STRING)
	private E_TipoParametro type;
	
	/**
	 * Grouop flag.
	 */
	private String grupo;

	
	/*
	 * (non-Javadoc)
	 * @see org.universe.core.model.I_Model#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * @see org.universe.core.model.I_Model#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return Pojomatic.equals(this, obj);
	}

	/**
	 * @return the global
	 */

	/**
	 * @return the borrado
	 */
	@Override
	public Boolean getBorrado() {
		return borrado;
	}

	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @param borrado the borrado to set
	 */
	@Override
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	/**
	 * @return the version
	 */
	@Override
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the type
	 */
	public E_TipoParametro getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(E_TipoParametro type) {
		this.type = type;
	}

	public Parametro_VO toValueObjet() {
		return new Parametro_VO(this);
	}

	
}
