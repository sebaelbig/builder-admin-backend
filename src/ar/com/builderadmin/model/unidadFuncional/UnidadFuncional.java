package ar.com.builderadmin.model.unidadFuncional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;

@Entity @Table( name = "unidad_funcional")
public class UnidadFuncional implements I_Entidad {
	
	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidad_funcional_seq")
	@SequenceGenerator( name = "unidad_funcional_seq", sequenceName = "unidad_funcional_seq", allocationSize = 1)
	private Long id;
	
	@Version
	private Integer version;
	private boolean borrado;
	private String nombre;
	private String descripcion; 
	private TipoDeUnidadFuncional tipoDeUnidad;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setVersion(Integer id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean getBorrado() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setBorrado(Boolean b) {
		// TODO Auto-generated method stub
		
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	public TipoDeUnidadFuncional getTipoDeUnidad() {
		return tipoDeUnidad;
	}
	public void setTipoDeUnidad(TipoDeUnidadFuncional tipoDeUnidad) {
		this.tipoDeUnidad = tipoDeUnidad;
	}
	
	

}
