package ar.com.builderadmin.model.farmacia.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.farmacia.medicamentos.MedicamentoComercial;
import ar.com.builderadmin.vo.farmacia.stock.MedicamentoDeposito_VO;

/**
 * deposito que expide el medicamento
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "medicamento_deposito")
public class MedicamentoDeposito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_medicamento_deposito", name = "seq_medicamento_deposito", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medicamento_deposito")
	private Long id;

	@Version
	private Integer version;

	@Column(name = "cantidad_minima")
	private Integer cantidadMinima;

	@Column(name = "cantidad_maxima")
	private Integer cantidadMaxima;

	@OneToMany
	@JoinColumn(name = "id_lote_medicamento_en_deposito")
	private List<LoteMedicamentoEnDeposito> lotesMedicamentoEnDeposito = new ArrayList<LoteMedicamentoEnDeposito>();

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "id_medicamento_comercial")
	private MedicamentoComercial medicamentoComercial;

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "id_deposito")
	private Deposito deposito;

	@ManyToMany
	private List<Deposito> depositosAnteriores = new ArrayList<Deposito>();

	// Constructores
	public MedicamentoDeposito() {

	}

	public MedicamentoDeposito(Long id2, Integer version2) {
		setId(id2);
		setVersion(version2);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof MedicamentoDeposito) {
			MedicamentoDeposito o = (MedicamentoDeposito) objeto;
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

	public Integer getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(Integer cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public Integer getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(Integer cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public List<Deposito> getDepositosAnteriores() {
		return depositosAnteriores;
	}

	public void setDepositosAnteriores(List<Deposito> depositosAnteriores) {
		this.depositosAnteriores = depositosAnteriores;
	}

	/**
	 * @return the lotesMedicamento
	 */
	public void setLotesMedicamento(List<LoteMedicamentoEnDeposito> lotes) {
		this.lotesMedicamentoEnDeposito = lotes;
	}

	/**
	 * @return the lotesMedicamento
	 */
	public List<LoteMedicamentoEnDeposito> getLotesMedicamento() {
		return lotesMedicamentoEnDeposito;
	}

	/**
	 * @return the medicamentoComercial
	 */
	public MedicamentoComercial getMedicamentoComercial() {
		return medicamentoComercial;
	}

	/**
	 * @param medicamentoComercial
	 *            the medicamentoComercial to set
	 */
	public void setMedicamentoComercial(
			MedicamentoComercial medicamentoComercial) {
		this.medicamentoComercial = medicamentoComercial;
	}

	public MedicamentoDeposito_VO toValueObject() {
		return new MedicamentoDeposito_VO(this);
	}

}