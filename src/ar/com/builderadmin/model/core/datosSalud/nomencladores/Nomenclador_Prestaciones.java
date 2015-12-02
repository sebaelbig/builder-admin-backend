package ar.com.builderadmin.model.core.datosSalud.nomencladores;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;

/**
 * @author svalle
 */
//@Entity @Table
public class Nomenclador_Prestaciones {
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_nomenclador_prestaciones", name = "seq_nomenclador_prestaciones", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_nomenclador_prestaciones") 
	private Long id;
	
	@Version
	private Integer version;
	
	/**
	 * codigo del nomenclador de prestaciones
	 */
	private String codigo;
	
	/**
	 * descripcion del nomenclador de prestaciones
	 */
	private String descripcion;
	
	/**
	 * prestaciones asociadas al nomenclador
	 */
	@OneToMany
	@JoinTable(
			name="nomenclador_Prestaciones_Prestacion",
			joinColumns = @JoinColumn(name="id_nomenclador_prestaciones"),
			inverseJoinColumns = @JoinColumn(name="id_prestacion"),
			uniqueConstraints = @UniqueConstraint(columnNames={"id_nomenclador_prestaciones","id_prestacion"}))
	private List<Prestacion> prestaciones;

	public Nomenclador_Prestaciones(){
		
	}
	
	public Nomenclador_Prestaciones(Long id, Integer version, String codigo,
			String descripcion) {
		setId(id);
		setVersion(version);
		setCodigo(codigo);
		setDescripcion(descripcion);
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void agregarPrestacion(Prestacion prestacion) {
		this.getPrestaciones().add(prestacion);
	}

	public List<Prestacion> getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(List<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}

	public void quitarPrestacion(Prestacion prestacion) {
		this.getPrestaciones().remove(prestacion);
		
	}
	
}
