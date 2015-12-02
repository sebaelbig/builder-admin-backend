package ar.com.builderadmin.model.core.datosSalud.nomencladores;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;


/**
 * @author svalle
 */


//@Entity @Table( name="tipo_prestacion")
public class TipoPrestacion  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_tipo_prestacion", name = "seq_tipo_prestacion", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_tipo_prestacion") 
	private Long id;
	
	@Version
	private Integer version;
	
	/**
	 * codigo asociado al tipo de prestacion
	 */
	@Column(name="codigo_prestacion")
	private String codigoPrestacion;
	
	
	/**
	 * Descripcion del tipo de prestacion
	 */
	private String descripcion;
	
	public TipoPrestacion(){
		
	}
	
	public TipoPrestacion(String codigoPrestacion, String descripcion){
		this.setCodigoPrestacion(codigoPrestacion);
		this.setDescripcion(descripcion);
	}

	public TipoPrestacion(Long id, Integer version, String codigo,	String descripcion) {
		this.setId(id);
		this.setVersion(version);
		this.setCodigoPrestacion(codigo);
		this.setDescripcion(descripcion);
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

	public String getCodigoPrestacion() {
		return codigoPrestacion;
	}

	public void setCodigoPrestacion(String codigoPrestacion) {
		this.codigoPrestacion = codigoPrestacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoPrestacion) {
			TipoPrestacion o = (TipoPrestacion) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
		
}
