package ar.com.builderadmin.vo.farmacia.stock;

import ar.com.builderadmin.model.farmacia.stock.LoteMedicamentoEnDeposito;
import ar.com.builderadmin.vo.I_ValueObject;

public class LoteMedicamentoEnDeposito_VO implements
		I_ValueObject<LoteMedicamentoEnDeposito> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private Integer cantidad = 0;
	private LoteMedicamento_VO loteMedicamento;

	public LoteMedicamentoEnDeposito_VO() {
	}

	public LoteMedicamentoEnDeposito_VO(
			LoteMedicamentoEnDeposito loteMedicamentoEnDeposito) {
		setObject(loteMedicamentoEnDeposito);
	}

	public LoteMedicamentoEnDeposito_VO(
			LoteMedicamentoEnDeposito loteMedicamentoEnDeposito,
			int profundidadActual, int profundidadDeseada) {
		setObject(loteMedicamentoEnDeposito, profundidadActual,
				profundidadDeseada);
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
		if (objeto instanceof LoteMedicamentoEnDeposito_VO) {
			LoteMedicamentoEnDeposito_VO o = (LoteMedicamentoEnDeposito_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public void setObject(LoteMedicamentoEnDeposito loteMedicamentoEnDeposito) {
		this.setId(loteMedicamentoEnDeposito.getId());
		this.setVersion(loteMedicamentoEnDeposito.getVersion());

		this.setCantidad(loteMedicamentoEnDeposito.getCantidad());
		this.setLoteMedicamento(loteMedicamentoEnDeposito.getLoteMedicamento()
				.toValueObjet());
	}

	@Override
	public void setObject(LoteMedicamentoEnDeposito loteMedicamentoEnDeposito,
			int profundidadActual, int profundidadDeseada) {

		this.setId(loteMedicamentoEnDeposito.getId());
		this.setVersion(loteMedicamentoEnDeposito.getVersion());

		this.setCantidad(loteMedicamentoEnDeposito.getCantidad());

		if (profundidadActual < profundidadDeseada) {
			this.setLoteMedicamento(loteMedicamentoEnDeposito
					.getLoteMedicamento().toValueObjet(profundidadActual + 1,
							profundidadDeseada));
		}
	}

	@Override
	public LoteMedicamentoEnDeposito toObject() {
		LoteMedicamentoEnDeposito loteMedicamentoEnDeposito = new LoteMedicamentoEnDeposito();

		loteMedicamentoEnDeposito.setId(this.getId());
		loteMedicamentoEnDeposito.setVersion(this.getVersion());

		loteMedicamentoEnDeposito.setCantidad(this.getCantidad());
		loteMedicamentoEnDeposito.setLoteMedicamento(this.getLoteMedicamento()
				.toObject());

		return loteMedicamentoEnDeposito;
	}

	public LoteMedicamentoEnDeposito toObject(int profundidadActual,
			int profundidadDeseada) {

		LoteMedicamentoEnDeposito loteMedicamentoEnDeposito = new LoteMedicamentoEnDeposito();

		loteMedicamentoEnDeposito.setId(this.getId());
		loteMedicamentoEnDeposito.setVersion(this.getVersion());

		loteMedicamentoEnDeposito.setCantidad(this.getCantidad());

		if (profundidadActual < profundidadDeseada) {
			loteMedicamentoEnDeposito.setLoteMedicamento(this
					.getLoteMedicamento().toObject(profundidadActual + 1,
							profundidadDeseada));
		}

		return loteMedicamentoEnDeposito;
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
	public LoteMedicamento_VO getLoteMedicamento() {
		return loteMedicamento;
	}

	/**
	 * @param loteMedicamento
	 *            the loteMedicamento to set
	 */
	public void setLoteMedicamento(LoteMedicamento_VO loteMedicamento) {
		this.loteMedicamento = loteMedicamento;
	}

}