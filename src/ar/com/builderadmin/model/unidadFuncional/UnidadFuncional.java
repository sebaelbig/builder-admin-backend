package ar.com.builderadmin.model.unidadFuncional;

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
import ar.com.builderadmin.vo.unidadFuncional.UnidadFuncional_VO;

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
	
	@OneToOne(optional=false)
	@JoinColumn(name = "tipounidad_id")
	@JoinFetch(JoinFetchType.INNER)
	private TipoUnidadFuncional tipoDeUnidad;
	
	public UnidadFuncional(){
		
	}
	
	public UnidadFuncional_VO toValueObject() {
		return new UnidadFuncional_VO();
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
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	public TipoUnidadFuncional getTipoDeUnidad() {
		return tipoDeUnidad;
	}
	public void setTipoDeUnidad(TipoUnidadFuncional tipoDeUnidad) {
		this.tipoDeUnidad = tipoDeUnidad;
	}
	
	@Override
	public void setBorrado(Boolean b) {
		// TODO Auto-generated method stub
		this.borrado=b;
	}
	@Override
	public Boolean getBorrado() {
		// TODO Auto-generated method stub
		return this.borrado;
	}
	
	
	

}
