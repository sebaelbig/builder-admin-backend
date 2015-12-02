package ar.com.builderadmin.model.core.datosSalud;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

//@Entity @Table(name = "grupo")
public class Grupo {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_grupo", name = "seq_grupo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo")
	private Long id;

	@Version
	private Integer version;

	/**
	 * codigo del grupo de prestaciones
	 */
	private String codigo;

	/**
	 * descripcion del grupo de prestaciones
	 */
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "grupo_prestaciones", joinColumns = @JoinColumn(name = "id_grupo"), inverseJoinColumns = @JoinColumn(name = "id_prestacion"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_grupo", "id_prestacion" }))
	private List<Prestacion> prestaciones;

	public Grupo() {
	}

	public Grupo(Long id, Integer version, String codigo, String nombre) {
		this.setId(id);
		this.setVersion(version);
		this.setCodigo(codigo);
		this.setNombre(nombre);
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

	public List<Prestacion> getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(List<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}

}
