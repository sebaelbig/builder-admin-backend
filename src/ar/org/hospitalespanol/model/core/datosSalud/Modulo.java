package ar.org.hospitalespanol.model.core.datosSalud;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.core.obrasSociales.ObraSocial;
import ar.org.hospitalespanol.vo.core.datosSalud.Modulo_VO;

@Entity @Table( name = "modulo")
public class Modulo {
	/**
	 * Modulo de Prestaciones de una Obra Social
	 */

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_modulo", name = "seq_modulo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modulo")
	private Long id;

	@Version
	private Integer version;

	/**
	 * codigo del modulo de prestaciones
	 */
	private String codigo;

	/**
	 * descripcion del modulo de prestaciones
	 */
	private String nombre;

	/**
	 * Version de Items que conforman el modulo de prestaciones
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_version_items_modulo_vigente")
	private VersionItemsModulo versionVigente;

	/**
	 * Versiones de Items historicas del modulo de prestaciones
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "modulo_version_items_modulo", joinColumns = @JoinColumn(name = "id_modulo"), inverseJoinColumns = @JoinColumn(name = "id_version_item_modulo"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_modulo", "id_version_item_modulo" }))
	private List<VersionItemsModulo> versiones;

	@ManyToOne
	@JoinColumn(name = "id_obraSocial")
	private ObraSocial obraSocial;

	public Modulo() {
	}

	public Modulo(Long id, Integer version, String codigo, String nombre,
			ObraSocial obraSocial) {

		this.setId(id);
		this.setVersion(version);
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setObraSocial(obraSocial);

		this.setVersionVigente(null);

		this.setVersiones(new ArrayList<VersionItemsModulo>());

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

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public VersionItemsModulo getVersionVigente() {
		return versionVigente;
	}

	public void setVersionVigente(VersionItemsModulo versionVigente) {
		this.versionVigente = versionVigente;
	}

	public List<VersionItemsModulo> getVersiones() {
		return versiones;
	}

	public void setVersiones(List<VersionItemsModulo> versiones) {
		this.versiones = versiones;
	}

	public String prettyPrinting() {
		return this.getCodigo() + " - " + this.getNombre() + " ("
				+ this.getObraSocial().getNombre() + ")";
	}

	public Modulo_VO toValueObject() {
		return new Modulo_VO(this);
	}

	public Modulo_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new Modulo_VO(this, profundidadActual, profundidadDeseada);
	}

}
