package ar.com.builderadmin.model.core.areas.servicios;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.model.core.areas.AreaHorus;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;

/**
 * @author Sebastian Valle
 */

//@Entity
@Table
public class Servicio implements Serializable, I_Entidad {

	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( 
						sequenceName = "seq_servicio", 
						name = "seq_servicio", 
						allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servicio")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 15)
	private String codigo;

	@Column(length = 150)
	private String nombre;
	
	@Column(name="un_estudio_por_pedido")
	private Boolean unEstudioPorPedido= false;

//	@ManyToMany
//	@JoinTable(name = "servicio_prestacion", joinColumns = @JoinColumn(name = "id_servicio"), inverseJoinColumns = @JoinColumn(name = "id_prestacion"), uniqueConstraints = @UniqueConstraint(columnNames = {
//			"id_servicio", "id_prestacion" }))
//	private List<Prestacion> prestaciones = new ArrayList<Prestacion>();

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_area")
	@JoinFetch(JoinFetchType.INNER)
	private AreaHorus area;

//	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
//	private List<Division> divisiones = new ArrayList<Division>();

//	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
//	private List<HorarioDeAtencion> horarios = new ArrayList<HorarioDeAtencion>();

	public Servicio() {
//		setPrestaciones(new ArrayList<Prestacion>());
	}

	public Servicio(Long id, Integer version, String nombre, String codigo) {

		this.setId(id);
		this.setVersion(version);
		this.setNombre(nombre);
		this.setCodigo(codigo);
//		setHorarios(new ArrayList<HorarioDeAtencion>());
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

	@Override
	public String toString() {
		return this.getCodigo();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Servicio) {
			Servicio o = (Servicio) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

//	public List<Prestacion> getPrestaciones() {
//		return prestaciones;
//	}
//
//	public void setPrestaciones(List<Prestacion> prestaciones) {
//		this.prestaciones = prestaciones;
//	}

	public AreaHorus getArea() {
		return area;
	}

	public void setArea(AreaHorus area) {
		this.area = area;
	}

	public Servicio_VO toValueObject() {
		return new Servicio_VO(this);
	}

	public Servicio_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Servicio_VO(this, profundidadActual, profundidadDeseada);
	}

//	public List<Division> getDivisiones() {
//		return divisiones;
//	}
//
//	public void setDivisiones(List<Division> divisiones) {
//		this.divisiones = divisiones;
//	}
//
//	public List<HorarioDeAtencion> getHorarios() {
//		return horarios;
//	}
//
//	public void setHorarios(List<HorarioDeAtencion> horarios) {
//		this.horarios = horarios;
//	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the unEstudioPorPedido
	 */
	public Boolean getUnEstudioPorPedido() {
		return unEstudioPorPedido;
	}

	/**
	 * @param unEstudioPorPedido the unEstudioPorPedido to set
	 */
	public void setUnEstudioPorPedido(Boolean unEstudioPorPedido) {
		this.unEstudioPorPedido = unEstudioPorPedido;
	}

	
}
