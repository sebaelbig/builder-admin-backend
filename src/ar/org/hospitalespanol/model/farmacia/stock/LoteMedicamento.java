package ar.org.hospitalespanol.model.farmacia.stock;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.farmacia.medicamentos.MedicamentoComercial;
import ar.org.hospitalespanol.vo.farmacia.stock.LoteMedicamento_VO;

/**
 * deposito que expide el medicamento
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table
public class LoteMedicamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_lote_medicamento", name = "seq_lote_medicamento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lote_medicamento")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Fecha de vencimiento
	 */
	@Column(name = "fecha_vencimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "id_medicamento_comercial")
	private MedicamentoComercial medicamentoComercial;

	/**
	 * Lote
	 */
	@Column(name = "lote")
	private String lote;

	// Constructores
	public LoteMedicamento() {

	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof LoteMedicamento) {
			LoteMedicamento o = (LoteMedicamento) objeto;
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

	public LoteMedicamento_VO toValueObjet() {
		return new LoteMedicamento_VO(this);
	}

	public LoteMedicamento_VO toValueObjet(int profundidadActual,
			int profundidadDeseada) {
		return new LoteMedicamento_VO(this, profundidadActual,
				profundidadDeseada);
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	/**
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * @param lote
	 *            the lote to set
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

}