package ar.com.builderadmin.model.designacion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.vo.designacion.Designacion_VO;

@Entity
@Table(name = "designacion")
public class Designacion implements I_Entidad, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	/**
	 * Entity ID.
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designacion_seq")
	@Id
	@SequenceGenerator( name = "designacion_seq", sequenceName = "designacion_seq", allocationSize = 1)
	private Long id;

	@Version
	private Integer version;
	private boolean borrado;
	private String nombre;
	private String descripcion;

	@OneToOne(optional=false)
	@JoinColumn(name = "unidadpordefault_id")
	@JoinFetch(JoinFetchType.INNER)
	private UnidadDeMedida unidadPorDefault;
	
	public Designacion(){
		
	}
	
	@Override
	public Long getId() {
		return this.id;
	}
	@Override
	public void setId(Long id) {
		this.id=id;
		
	}
	@Override
	public Integer getVersion() {
		return this.version;
	}
	@Override
	public void setVersion(Integer id) {
		this.version=id;
		
	}
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}
	@Override
	public void setBorrado(Boolean b) {
		this.borrado=b;
		
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
	public UnidadDeMedida getUnidadPorDefault() {
		return unidadPorDefault;
	}
	public void setUnidadPorDefault(UnidadDeMedida unidadPorDefault) {
		this.unidadPorDefault = unidadPorDefault;
	}
	
	public Designacion_VO toValueObject() {
		return new Designacion_VO();
	}
	

}
