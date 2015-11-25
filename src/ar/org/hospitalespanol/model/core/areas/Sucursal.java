package ar.org.hospitalespanol.model.core.areas;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.vo.core.areas.Sucursal_VO;

@Entity
@Table
public class Sucursal implements I_Entidad {

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_sucursal", name = "seq_sucursal", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sucursal")
	private Long id;

	@Version
	private Integer version;

	private String nombre;

	private String direccion;

	private String telefono;

	private String codigo;

	@Column(name = "tiene_consultorios")
	private boolean tieneConsultorios;

	@Column(name = "tiene_internacion")
	private boolean tieneInternacion;

	@Column(name = "tiene_farmacia")
	private boolean tieneFarmacia;

	@OneToMany(mappedBy = "sucursal")
	@JoinFetch(JoinFetchType.INNER)
	private List<AreaHorus> areas;

//	@ManyToMany
//	@JoinTable( name = "sucursal_circulo", joinColumns = @JoinColumn(name = "id_sucursal"), inverseJoinColumns = @JoinColumn(name = "id_circulo"), uniqueConstraints = @UniqueConstraint(columnNames = {
//			"id_sucursal", "id_circulo" }))
//	private List<CirculoDeConfianza> circulosDeConfianza;

	public Sucursal() {
		setAreas(new ArrayList<AreaHorus>());
//		setCirculosDeConfianza(new ArrayList<CirculoDeConfianza>());
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isTieneConsultorios() {
		return tieneConsultorios;
	}

	public void setTieneConsultorios(boolean tieneConsultorios) {
		this.tieneConsultorios = tieneConsultorios;
	}

	public boolean isTieneInternacion() {
		return tieneInternacion;
	}

	public void setTieneInternacion(boolean tieneInternacion) {
		this.tieneInternacion = tieneInternacion;
	}

	public boolean isTieneFarmacia() {
		return tieneFarmacia;
	}

	public void setTieneFarmacia(boolean tieneFarmacia) {
		this.tieneFarmacia = tieneFarmacia;
	}

	public Sucursal_VO toValueObject() {
		return new Sucursal_VO(this);
	}

	public Sucursal_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Sucursal_VO(this, profundidadActual, profundidadDeseada);
	}

	@Override
	public boolean equals(Object object) {

		if (object instanceof Sucursal) {
			Sucursal s = (Sucursal) object;
			return s.getId().equals(this.getId());

		} else if (object instanceof Sucursal_VO) {
			Sucursal_VO s = (Sucursal_VO) object;
			return s.getId().equals(this.getId());
		}

		return false;
	}

	public List<AreaHorus> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaHorus> areas) {
		this.areas = areas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
//
//	public List<CirculoDeConfianza> getCirculosDeConfianza() {
//		return circulosDeConfianza;
//	}
//
//	public void setCirculosDeConfianza(
//			List<CirculoDeConfianza> circulosDeConfianza) {
//		this.circulosDeConfianza = circulosDeConfianza;
//	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}
