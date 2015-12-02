package ar.com.builderadmin.model.core.obrasSociales;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.obrasSociales.EstadoObraSocial_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table( name="estado_obra_social")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="estado_obra_social",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("estado_obra_social_base")
public class EstadoObraSocial {

	public static final String SUSPENDIDA = "Suspendida";
	public static final String VIGENTE = "Vigente";
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_estado_obra_social", name = "seq_estado_obra_social",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estado_obra_social") 
	private Long id;

	@Version
	private Integer version;
	
	@Column(length=30)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_obra_social")
	private ObraSocial obraSocial;
	
	//Constructores
	public EstadoObraSocial(){
	}
	
	public EstadoObraSocial(Long id, Integer version, String nombre){
		setId(id);
		setNombre(nombre);
		setVersion(version);
	}

	public EstadoObraSocial_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		// Las subclases
		return null;
	}
	
	/* Metodos */
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoObraSocial) {
			EstadoObraSocial o = (EstadoObraSocial) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public EstadoObraSocial_VO toValueObject(){
		return new EstadoObraSocial_VO(this);
	}
	
	//Getters & Setters
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

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

}