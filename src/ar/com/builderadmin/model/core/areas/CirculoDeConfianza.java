package ar.com.builderadmin.model.core.areas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.areas.CirculoDeConfianza_VO;

/**
 * @author Sebastian Ariel Garcia
 */
@Entity 
@Table(name="circulo_de_confianza")
@SequenceGenerator(sequenceName = "seq_circulo_de_confianza", name = "seq_circulo_de_confianza", allocationSize=1)
public class CirculoDeConfianza implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_circulo_de_confianza") 
	private Long id;
	
	@Version
	private Integer version;

	@Column(length=100)
	private String nombre;
	
	@Column(length=15)
	private String codigo;
		
	/**
	 *   Especialidades que soporta el division
	 */
	@ManyToMany
	@JoinTable(
			name="circulo_sucursal",
			joinColumns = @JoinColumn(name="id_circulo"),
			inverseJoinColumns = @JoinColumn(name="id_sucursal"),
			uniqueConstraints = @UniqueConstraint(columnNames={"id_circulo","id_sucursal"})
	)
	private List<Sucursal> sucursales = new ArrayList<Sucursal>();

	private Boolean modificar = false;
	
	
	//---------------- CONSTRUCTOR
	public CirculoDeConfianza() {
	}
	
	public CirculoDeConfianza(String nombre) {
		this.nombre = nombre;
	}

	public CirculoDeConfianza(Long id2, Integer version2, String nombre2, String codigo2) {
		this.setId(id2);
		this.setVersion(version2);
		this.setNombre(nombre2);
		this.setCodigo(codigo2);
	}

	//------------------------ OPERACIONES
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof CirculoDeConfianza) {
			CirculoDeConfianza o = (CirculoDeConfianza) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	//---------------- GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public CirculoDeConfianza_VO toValueObjet() {
		return new CirculoDeConfianza_VO(this);
	}
	
	public CirculoDeConfianza_VO toValueObjet(int profundidadActual, int profundidadDeseada) {
		return new CirculoDeConfianza_VO(this, profundidadActual, profundidadDeseada);
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	public Boolean getModificar() {
		return modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}
	
}