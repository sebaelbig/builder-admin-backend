package ar.com.builderadmin.model.core.datosSalud;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.datosSalud.NomencladorHorus_VO;

@Entity @Table( name = "nomenclador_horus")
public class NomencladorHorus {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_nomenclador", name = "seq_nomenclador", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nomenclador")
	private Long id;

	@Version
	private Integer version;

	/**
	 * codigo del nomenclador de prestaciones
	 */
	private String codigo;

	/**
	 * descripcion del nomenclador de prestaciones
	 */
	private String nombre;

	public NomencladorHorus() {
	}

	public NomencladorHorus(Long id, Integer version, String codigo,
			String nombre) {
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

	public NomencladorHorus_VO toValueObject() {
		return new NomencladorHorus_VO(this);
	}

	public NomencladorHorus_VO toValueObject(int i, int profundidadDeseada) {
		return new NomencladorHorus_VO(this, i, profundidadDeseada);
	}

	
}
