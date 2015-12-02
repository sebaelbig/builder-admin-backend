package ar.com.builderadmin.model.farmacia.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.model.farmacia.Farmacia;
import ar.com.builderadmin.vo.farmacia.stock.Deposito_VO;

/**
 * deposito que expide el medicamento
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table
public class Deposito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_deposito", name = "seq_deposito", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_deposito")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Nombre de deposito
	 */
	@Column(length = 200)
	private String nombre;

	/**
	 * Descripcion de deposito
	 */
	@Column(length = 200)
	private String descripcion;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_farmacia")
	private Farmacia farmacia;

	// Constructores
	public Deposito() {

	}

	public Deposito(Long id2, Integer version2, String nombre2,
			String descripcion2) {
		setId(id2);
		setVersion(version2);
		setNombre(nombre2);
		setDescripcion(descripcion2);
	}

	public Deposito(String string) {
		setNombre(string);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Deposito) {
			Deposito o = (Deposito) objeto;
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

	@Override
	public String toString() {
		return this.getNombre();
	}

	public Deposito_VO toValueObject() {
		return new Deposito_VO(this);
	}

	public Deposito_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Deposito_VO(this, profundidadActual, profundidadDeseada);
	}

	/**
	 * @return the farmacia
	 */
	public Farmacia getFarmacia() {
		return farmacia;
	}

	/**
	 * @param farmacia
	 *            the farmacia to set
	 */
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

}