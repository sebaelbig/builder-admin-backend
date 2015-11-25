package ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.I_Consumo;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo;
import ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo.estados.ItemFichaDeConsumo_SinRendir;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.vo.core.usuarios.fichaDeConsumo.ItemFichaDeConsumo_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "item_ficha_de_consumo")
public class ItemFichaDeConsumo implements Serializable, I_Consumo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_item_ficha_de_consumo", name = "seq_item_ficha_de_consumo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_ficha_de_consumo")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Fecha en la que se realizo el consumo
	 */
	@Temporal(TemporalType.DATE)
	private Date fecha;

	/**
	 * Id de la clase que implementa I_Consumo
	 */
	@Column(name = "id_consumo")
	private Long idConsumo;

	/**
	 * Canonical Name de la clase que implementa I_Consumo
	 */
	@Column(name = "clase_consumo")
	private String claseConsumo;

	/**
	 * Nombre del consumo
	 */
	private String nombre;

	/**
	 * Descripcion del consumo
	 */
	private String descripcion;

	/**
	 * Rol que posee la FX_Crear/ModificarItemFichaDeConsumo y que registro este
	 * consumo
	 */
	@ManyToOne
	@JoinColumn(name = "id_rol_registro")
	private Rol rolRegistro;

	/**
	 * Estado en el que se encuentra este item
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado")
	private EstadoItemFichaDeConsumo estado;

	/**
	 * Ficha a la cual pertenece este item
	 */
	@ManyToOne
	@JoinColumn(name = "id_ficha_de_consumo")
	private FichaDeConsumo fichaDeConsumo;

	/**
	 * Fecha en la cual era actual el precio unitario
	 */
	@Column(name = "fecha_precio")
	@Temporal(TemporalType.DATE)
	private Date fechaPrecio;

	/**
	 * Precio unitario del item
	 */
	@Column(name = "precio_unitario")
	private BigDecimal precioUnitario;

	/**
	 * Cantidad del item
	 */
	private Integer cantidad;

	/**
	 * El iva que se le debe aplica a este consumo
	 */
	private Float iva;

	@ManyToOne
	@JoinColumn(name = "id_servicio")
	private Servicio servicio;

	public ItemFichaDeConsumo() {
		this.setEstado(new ItemFichaDeConsumo_SinRendir());
	}

	public ItemFichaDeConsumo(I_Consumo consumo, Integer cantidad,
			BigDecimal precio, Date fechaPrecio) {
		this.setIdConsumo(consumo.getIdConsumo());
		this.setClaseConsumo(consumo.getClaseConsumo());
		this.setNombre(consumo.getNombre());
		this.setDescripcion(consumo.getDescripcion());
		this.setServicio(consumo.getServicio());
		this.setFecha(new Date());

		this.setCantidad(cantidad);
		this.setFechaPrecio(fechaPrecio);
		this.setPrecioUnitario(precio);

		this.setEstado(new ItemFichaDeConsumo_SinRendir());

	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ItemFichaDeConsumo) {
			ItemFichaDeConsumo o = (ItemFichaDeConsumo) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public Long getIdConsumo() {
		return idConsumo;
	}

	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
	}

	@Override
	public String getClaseConsumo() {
		return claseConsumo;
	}

	public void setClaseConsumo(String claseConsumo) {
		this.claseConsumo = claseConsumo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRolRegistro() {
		return rolRegistro;
	}

	public void setRolRegistro(Rol rolRegistro) {
		this.rolRegistro = rolRegistro;
	}

	public EstadoItemFichaDeConsumo getEstado() {
		return estado;
	}

	public void setEstado(EstadoItemFichaDeConsumo estado) {
		this.estado = estado;
	}

	public FichaDeConsumo getFichaDeConsumo() {
		return fichaDeConsumo;
	}

	public void setFichaDeConsumo(FichaDeConsumo fichaDeConsumo) {
		this.fichaDeConsumo = fichaDeConsumo;
	}

	@Override
	public Date getFechaPrecio() {
		return fechaPrecio;
	}

	@Override
	public void setFechaPrecio(Date fechaPrecio) {
		this.fechaPrecio = fechaPrecio;
	}

	@Override
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	@Override
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Override
	public Integer getCantidad() {
		return cantidad;
	}

	@Override
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Float getIVA() {
		return iva;
	}

	@Override
	public void setIVA(Float iva) {
		this.iva = iva;
	}

	public ItemFichaDeConsumo_VO toValueObject() {
		return new ItemFichaDeConsumo_VO(this);
	}

	public ItemFichaDeConsumo_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new ItemFichaDeConsumo_VO(this, profundidadActual,
				profundidadDeseada);
	}

	@Override
	public Servicio getServicio() {
		return servicio;
	}

	@Override
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}
