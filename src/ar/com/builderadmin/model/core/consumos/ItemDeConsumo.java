package ar.com.builderadmin.model.core.consumos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.areas.Sucursal;

/**
 * 
 * @author agallego
 * @version 1.0
 * 
 */

@Entity @Table( name = "item_de_consumo")
public class ItemDeConsumo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_item_de_consumo", name = "seq_item_de_consumo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_de_consumo")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 100)
	private String nombre;

	@Column(length = 100)
	private String marca;

	private BigDecimal precio;

	@Column(length = 100)
	private String categoria;

	@Column(length = 200)
	private String presentacion;

	@Column(length = 50)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_sucursal")
	private Sucursal sucursal;

	public ItemDeConsumo() {

	}

	public ItemDeConsumo(Long id, Integer version) {
		setId(id);
		setVersion(version);
	}

	@Override
	public String toString() {
		return "(" + getCodigo() + ") " + getNombre();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
