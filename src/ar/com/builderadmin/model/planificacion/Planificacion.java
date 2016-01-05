package ar.com.builderadmin.model.planificacion;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.model.unidadFuncional.UnidadFuncional;

@Table
public class Planificacion  implements I_Entidad{
	
	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planificacion_seq")
	@SequenceGenerator( name = "planificacion_seq", sequenceName = "planificacion_seq", allocationSize = 1)
	private Long id;
	
	@Version
	private Integer version;
	private boolean borrado;
	private String nombre;
	private String descripcion;
	private List<UnidadFuncional> unidades;
	
	
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
	public boolean isBorrado() {
		return borrado;
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
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	public List<UnidadFuncional> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<UnidadFuncional> unidades) {
		this.unidades = unidades;
	}
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}
	@Override
	public void setBorrado(Boolean b) {
		this.borrado=b;
	}

}
