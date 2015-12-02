package ar.com.builderadmin.model.core.obrasSociales;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.obrasSociales.Localidad_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

//@Entity @Table
public class Localidad {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_localidad", name = "seq_localidad",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_localidad")
	private Long id;
	
	@Version
	private Integer version;
	
	private String codigo;
	
	@Column(name="codigo_postal")
	private String codigoPostal;
	
	private String nombre;
	
	private String provincia;

	public Localidad(){
		
	}
	
	public Localidad(Long id, Integer version, String codigo, String nombre, String provincia, String codigoPostal){
		this.setCodigo(codigo);
		this.setCodigoPostal(codigoPostal);
		this.setId(id);
		this.setNombre(nombre);
		this.setProvincia(provincia);
		this.setVersion(version);
	}
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Localidad) {
			Localidad o = (Localidad) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	
	public Localidad_VO toValueObject(){
		return new Localidad_VO(this);
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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
}
