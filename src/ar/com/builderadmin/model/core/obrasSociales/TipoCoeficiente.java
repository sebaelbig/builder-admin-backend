package ar.com.builderadmin.model.core.obrasSociales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.obrasSociales.TipoCoeficiente_VO;


@Entity @Table( name="tipo_coeficiente")
public class TipoCoeficiente {
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_tipo_coeficiente", name = "seq_tipo_coeficiente",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_tipo_coeficiente")
	private Long id;
	
	@Version
	private Integer version;
	
	@Column(length=50)
	private String codigo;
	
	private String nombre;
	
	private String descripcion;
	
	
	public TipoCoeficiente(){
		
		
	}
	
	public TipoCoeficiente(Long id, Integer version,String codigo, String nombre, String descripcion){
		setCodigo(codigo);
		setDescripcion(descripcion);
		setId(id);
		setNombre(nombre);
		setVersion(version);
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
	
	public TipoCoeficiente_VO toValueObject(){
		return new TipoCoeficiente_VO(this);
	}

	public TipoCoeficiente_VO toValueObject(int profActual, int profundidadDeseada) {
		return new TipoCoeficiente_VO(this, profActual, profundidadDeseada);
	}

}
