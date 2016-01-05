package ar.com.builderadmin.model.designacion;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;

@Table
public class Designacion implements I_Entidad{
	
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
	private UnidadDeMedida unidadPorDefault;
	
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
	

}
