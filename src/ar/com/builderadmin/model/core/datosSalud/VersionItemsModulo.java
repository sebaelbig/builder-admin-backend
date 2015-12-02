package ar.com.builderadmin.model.core.datosSalud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.datosSalud.VersionItemsModulo_VO;

//@Entity @Table( name = "version_items_modulo")
public class VersionItemsModulo {

	/**
	 * Version de un set de items de un modulo de prestaciones
	 */

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_versionItemsModulo", name = "seq_versionItemsModulo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_versionItemsModulo")
	private Long id;

	@Version
	private Integer version;

	@Temporal(TemporalType.DATE)
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	private Date fechaHasta;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "version_items_modulo_items_modulos", joinColumns = @JoinColumn(name = "id_version_items_modulos"), inverseJoinColumns = @JoinColumn(name = "id_item_modulo"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_version_items_modulos", "id_item_modulo" }))
	private List<ItemModulo> items = new ArrayList<ItemModulo>();

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<ItemModulo> getItems() {
		return items;
	}

	public void setItems(List<ItemModulo> items) {
		this.items = items;
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

	public VersionItemsModulo_VO toValueObject() {
		return new VersionItemsModulo_VO(this);
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof VersionItemsModulo) {
			VersionItemsModulo o = (VersionItemsModulo) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		return "Version del m�dulo habilitada desde: "
				+ f.format(this.getFechaDesde()) + "hasta: "
				+ f.format(this.getFechaHasta());
	}

}
