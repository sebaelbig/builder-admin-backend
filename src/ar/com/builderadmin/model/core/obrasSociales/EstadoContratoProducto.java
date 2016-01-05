package ar.com.builderadmin.model.core.obrasSociales;

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

import ar.com.builderadmin.vo.core.obrasSociales.EstadoContratoProducto_VO;

/**
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity @Table( name="estado_contrato_producto")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="tipoestadocontratoproducto",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("estado_contrato_producto_base")
public class EstadoContratoProducto {

	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estadocontratoproducto") 
	@SequenceGenerator(sequenceName = "seq_estadocontratoproducto", name = "seq_estadocontratoproducto", allocationSize=1)
	private Long id;
	
	@Version
	private Integer version;
	
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name="id_contrato")
	private ContratoDeProducto contrato;

	public EstadoContratoProducto(){
		
	}
	
	public EstadoContratoProducto(Long id, Integer version,String motivo){
		setId(id);
		setMotivo(motivo);
		setVersion(version);
	}
	
	
	/**
	 * Setters y Getters
	 * 
	 */
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public ContratoDeProducto getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDeProducto contrato) {
		this.contrato = contrato;
	}
	
	
	/**
	 * Metodos
	 */
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoContratoProducto) {
			EstadoContratoProducto o = (EstadoContratoProducto) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public EstadoContratoProducto_VO toValueObject(){
		return new EstadoContratoProducto_VO(this);
	}
}
