package ar.org.hospitalespanol.vo.farmacia.stock;

import java.text.SimpleDateFormat;
import java.util.Date;

import ar.org.hospitalespanol.model.farmacia.stock.LoteMedicamento;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.farmacia.medicamentos.MedicamentoComercial_VO;

public class LoteMedicamento_VO implements I_ValueObject<LoteMedicamento> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private Date fechaVencimiento;
	private String str_fechaVencimiento;

	private String lote;
	private MedicamentoComercial_VO medicamentoComercial;

	public LoteMedicamento_VO() {
	}

	public LoteMedicamento_VO(LoteMedicamento loteMedicamento) {
		setObject(loteMedicamento);
	}

	public LoteMedicamento_VO(LoteMedicamento loteMedicamento,
			int profundidadActual, int profundidadDeseada) {
		setObject(loteMedicamento, profundidadActual, profundidadDeseada);
	}

	@Override
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

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof LoteMedicamento_VO) {
			LoteMedicamento_VO o = (LoteMedicamento_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public void setObject(LoteMedicamento loteMedicamento) {
		this.setId(loteMedicamento.getId());
		this.setVersion(loteMedicamento.getVersion());

		this.setFechaVencimiento(loteMedicamento.getFechaVencimiento());
		this.setLote(loteMedicamento.getLote());

		this.setMedicamentoComercial(loteMedicamento.getMedicamentoComercial()
				.toValueObject());
	}

	@Override
	public void setObject(LoteMedicamento loteMedicamento,
			int profundidadActual, int profundidadDeseada) {

		this.setId(loteMedicamento.getId());
		this.setVersion(loteMedicamento.getVersion());

		this.setFechaVencimiento(loteMedicamento.getFechaVencimiento());
		this.setLote(loteMedicamento.getLote());

		if (profundidadActual < profundidadDeseada) {
			this.setMedicamentoComercial(loteMedicamento
					.getMedicamentoComercial().toValueObject(
							profundidadActual + 1, profundidadDeseada));
		}
	}

	@Override
	public LoteMedicamento toObject() {
		LoteMedicamento loteMedicamento = new LoteMedicamento();

		loteMedicamento.setId(this.getId());
		loteMedicamento.setVersion(this.getVersion());

		loteMedicamento.setFechaVencimiento(this.getFechaVencimiento());
		loteMedicamento.setLote(this.getLote());

		loteMedicamento.setMedicamentoComercial(this.getMedicamentoComercial()
				.toObject());

		return loteMedicamento;
	}

	public LoteMedicamento toObject(int profundidadActual,
			int profundidadDeseada) {

		LoteMedicamento loteMedicamento = new LoteMedicamento();

		loteMedicamento.setId(this.getId());
		loteMedicamento.setVersion(this.getVersion());

		loteMedicamento.setFechaVencimiento(this.getFechaVencimiento());
		loteMedicamento.setLote(this.getLote());

		if (profundidadActual < profundidadDeseada) {
			loteMedicamento.setMedicamentoComercial(this
					.getMedicamentoComercial().toObject(profundidadActual + 1,
							profundidadDeseada));
		}

		return loteMedicamento;
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
		if (fechaVencimiento != null)
			this.setStr_fechaVencimiento(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaVencimiento));
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

	/**
	 * @return the medicamentoComercial
	 */
	public MedicamentoComercial_VO getMedicamentoComercial() {
		return medicamentoComercial;
	}

	/**
	 * @param medicamentoComercial
	 *            the medicamentoComercial to set
	 */
	public void setMedicamentoComercial(
			MedicamentoComercial_VO medicamentoComercial) {
		this.medicamentoComercial = medicamentoComercial;
	}

	/**
	 * @return the str_fechaVencimiento
	 */
	public String getStr_fechaVencimiento() {
		return str_fechaVencimiento;
	}

	/**
	 * @param str_fechaVencimiento
	 *            the str_fechaVencimiento to set
	 */
	public void setStr_fechaVencimiento(String str_fechaVencimiento) {
		this.str_fechaVencimiento = str_fechaVencimiento;
	}

}