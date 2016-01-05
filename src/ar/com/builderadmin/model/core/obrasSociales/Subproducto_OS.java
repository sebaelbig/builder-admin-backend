package ar.com.builderadmin.model.core.obrasSociales;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.obrasSociales.Subproducto_OS_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity @Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="subproducto_os",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("subproducto_os")
public class Subproducto_OS {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_subproducto_os", name = "seq_subproducto_os",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_subproducto_os")
	private Long id;
	
	@Version
	private Integer version;
	
	@ManyToOne
	@JoinColumn(name="id_obra_social")
	private ObraSocial obraSocial;
	
	private String nombre;
	
	@Column(length=50)
	private String codigo;

	public Subproducto_OS(){
		
	}

	public Subproducto_OS(Long id, Integer version, String nombre, String codigo){
		setNombre(nombre);
		setId(id);
		setVersion(version);
		setCodigo(codigo);
	}
	
	/* Metodo */
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Subproducto_OS) {
			Subproducto_OS o = (Subproducto_OS) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public Subproducto_OS_VO toValueObject(){
		return new Subproducto_OS_VO(this);
	}
	
	/* Setters y Getters */
	
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

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
