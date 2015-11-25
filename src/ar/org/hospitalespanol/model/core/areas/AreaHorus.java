package ar.org.hospitalespanol.model.core.areas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.vo.core.areas.Area_VO;

/**
 * @author Sebastian Ariel Garcia
 */
@Entity
@Table( name = "area_horus")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "area", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("area_base")
public class AreaHorus implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_area_horus", name = "seq_area_horus", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_area_horus")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 100)
	private String nombre;

	@Column(length = 15)
	private String codigo;

	@OneToMany(mappedBy = "area")
	@JoinFetch(JoinFetchType.OUTER)
	private List<Servicio> servicios;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sucursal")
	@JoinFetch(JoinFetchType.INNER)
	private Sucursal sucursal;

	// ---------------- CONSTRUCTOR
	public AreaHorus() {
		setServicios(new ArrayList<Servicio>());
	}

	public AreaHorus(String nombre) {
		setServicios(new ArrayList<Servicio>());
		this.nombre = nombre;
	}

	public AreaHorus(Long id2, Integer version2, String nombre2, String codigo2) {
		this.setId(id2);
		this.setVersion(version2);
		this.setNombre(nombre2);
		this.setCodigo(codigo2);
	}

	// ------------------------ OPERACIONES
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof AreaHorus) {
			AreaHorus o = (AreaHorus) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	// ---------------- GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Area_VO toValueObjet() {
		return new Area_VO(this);
	}

	public Area_VO toValueObjet(int profundidadActual, int profundidadDeseada) {
		return new Area_VO(this, profundidadActual, profundidadDeseada);
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

}