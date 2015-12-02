package ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.estados;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table( name = "estado_item_ficha_de_consumo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "estado_ficha_de_consumo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("estado_base")
public abstract class EstadoItemFichaDeConsumo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_estado_ficha_de_consumo", name = "seq_estado_ficha_de_consumo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado_ficha_de_consumo")
	private Long id;

	@Version
	private Integer version;

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

	public abstract EstadoItemFichaDeConsumo_VO toValueObject();

}
