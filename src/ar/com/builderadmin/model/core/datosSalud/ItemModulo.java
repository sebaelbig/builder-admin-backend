package ar.com.builderadmin.model.core.datosSalud;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.datosSalud.ItemModulo_VO;

@Entity @Table( name = "item_modulo")
public class ItemModulo {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_itemModulo", name = "seq_itemModulo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_itemModulo")
	private Long id;

	@Version
	private Integer version;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_prestacion")
	private Prestacion prestacion;

	private Integer cantidad;

	public ItemModulo() {

	}

	public ItemModulo(Long id, Integer version, Integer cantidad,
			Prestacion prestacion) {

		this.id = id;
		this.version = version;
		this.cantidad = cantidad;
		this.prestacion = prestacion;

	}

	public Prestacion getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(Prestacion prestacion) {
		this.prestacion = prestacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public ItemModulo_VO toValueObject() {
		return new ItemModulo_VO(this);
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ItemModulo) {
			ItemModulo o = (ItemModulo) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public ItemModulo_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new ItemModulo_VO(this, profundidadActual, profundidadDeseada);
	}

}