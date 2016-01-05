package ar.com.builderadmin.model.farmacia.stock;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.farmacia.stock.LoteMedicamentoEnDeposito_VO;

/**
 * deposito que expide el medicamento
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "lote_medicamento_en_deposito")
public class LoteMedicamentoEnDeposito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_lote_medicamento_en_deposito", name = "seq_lote_medicamento_en_deposito", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lote_medicamento_en_deposito")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Cantidad
	 */
	private Integer cantidad;

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "id_lote_medicamento")
	private LoteMedicamento loteMedicamento;

	// Constructores
	public LoteMedicamentoEnDeposito() {

	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof LoteMedicamentoEnDeposito) {
			LoteMedicamentoEnDeposito o = (LoteMedicamentoEnDeposito) objeto;
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

	public LoteMedicamentoEnDeposito_VO toValueObject() {
		return new LoteMedicamentoEnDeposito_VO(this);
	}

	public LoteMedicamentoEnDeposito_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new LoteMedicamentoEnDeposito_VO(this, profundidadActual,
				profundidadDeseada);
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the loteMedicamento
	 */
	public LoteMedicamento getLoteMedicamento() {
		return loteMedicamento;
	}

	/**
	 * @param loteMedicamento
	 *            the loteMedicamento to set
	 */
	public void setLoteMedicamento(LoteMedicamento loteMedicamento) {
		this.loteMedicamento = loteMedicamento;
	}

}