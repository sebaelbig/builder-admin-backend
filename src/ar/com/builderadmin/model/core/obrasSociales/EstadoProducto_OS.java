package ar.com.builderadmin.model.core.obrasSociales;

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

import ar.com.builderadmin.vo.core.obrasSociales.EstadoProducto_OS_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table( name="estado_producto_obra_social")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="estado_producto_obra_social",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("estado_producto_obra_social_base")
public class EstadoProducto_OS {
	
	public static final String SUSPENDIDA = "Suspendida";
	public static final String VIGENTE = "Vigente";
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_estado_producto_obra_social", name = "seq_estado_producto_obra_social",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estado_producto_obra_social") 
	private Long id;

	@Version
	private Integer version;
	
	@ManyToOne
	@JoinColumn(name="id_producto_obra_social")
	private Producto_OS producto;
	
	//Constructores
	public EstadoProducto_OS(){

	}
	
	public EstadoProducto_OS(Long id, Integer version){
		setId(id);
		setVersion(version);
	}
	
	/* Metodos */
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoProducto_OS) {
			EstadoProducto_OS o = (EstadoProducto_OS) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public EstadoProducto_OS_VO toValueObject(){
		return null;
	};
	
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

	public Producto_OS getProducto() {
		return producto;
	}

	public void setProducto(Producto_OS producto) {
		this.producto = producto;
	}


}