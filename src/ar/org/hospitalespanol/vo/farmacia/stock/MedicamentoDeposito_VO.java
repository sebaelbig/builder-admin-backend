package ar.org.hospitalespanol.vo.farmacia.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.farmacia.stock.Deposito;
import ar.org.hospitalespanol.model.farmacia.stock.LoteMedicamentoEnDeposito;
import ar.org.hospitalespanol.model.farmacia.stock.MedicamentoDeposito;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.farmacia.medicamentos.MedicamentoComercial_VO;

/**
 * deposito que expide el medicamento
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class MedicamentoDeposito_VO implements Serializable,
		I_ValueObject<MedicamentoDeposito> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private MedicamentoComercial_VO medicamentoComercial;

	private final List<LoteMedicamentoEnDeposito_VO> lotesMedicamentoEnDeposito = new ArrayList<LoteMedicamentoEnDeposito_VO>();

	private Integer cantidadMinima = 0;
	private Integer cantidadActual = 0;
	private Integer cantidadMaxima = 0;

	private Deposito_VO deposito;
	private List<Deposito_VO> depositosAnteriores = new ArrayList<Deposito_VO>();

	// Constructores
	public MedicamentoDeposito_VO() {

	}

	public MedicamentoDeposito_VO(MedicamentoDeposito med) {
		this.setObject(med);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof MedicamentoDeposito_VO) {
			MedicamentoDeposito_VO o = (MedicamentoDeposito_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
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

	public MedicamentoComercial_VO getMedicamentoComercial() {
		return medicamentoComercial;
	}

	public void setMedicamentoComercial(
			MedicamentoComercial_VO medicamentoComercial) {
		this.medicamentoComercial = medicamentoComercial;
	}

	public Integer getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(Integer cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public Integer getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Integer cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public Integer getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(Integer cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public Deposito_VO getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito_VO deposito) {
		this.deposito = deposito;
	}

	public List<Deposito_VO> getDepositosAnteriores() {
		return depositosAnteriores;
	}

	public void setDepositosAnteriores(List<Deposito_VO> depositosAnteriores) {
		this.depositosAnteriores = depositosAnteriores;
	}

	@Override
	public MedicamentoDeposito toObject() {
		MedicamentoDeposito med = new MedicamentoDeposito(this.getId(),
				this.getVersion());

		med.setCantidadMinima(getCantidadMinima());
		med.setCantidadMaxima(getCantidadMaxima());

		med.setDeposito(getDeposito().toObject());
		for (Deposito_VO depoAnt : getDepositosAnteriores()) {
			med.getDepositosAnteriores().add(depoAnt.toObject());
		}

		med.setMedicamentoComercial(getMedicamentoComercial().toObject());

		for (LoteMedicamentoEnDeposito_VO lote : getLotesMedicamentoEnDeposito()) {
			med.getLotesMedicamento().add(lote.toObject());
		}

		return med;
	}

	public MedicamentoDeposito toObject(int profundidadActual,
			int profundidadDeseada) {
		MedicamentoDeposito med = new MedicamentoDeposito(this.getId(),
				this.getVersion());

		med.setCantidadMinima(getCantidadMinima());
		med.setCantidadMaxima(getCantidadMaxima());

		if (profundidadActual < profundidadDeseada) {

			med.setDeposito(getDeposito().toObject(profundidadActual + 1,
					profundidadDeseada));

			for (Deposito_VO depoAnt : getDepositosAnteriores()) {
				med.getDepositosAnteriores().add(
						depoAnt.toObject(profundidadActual + 1,
								profundidadDeseada));
			}

			med.setMedicamentoComercial(getMedicamentoComercial().toObject(
					profundidadActual + 1, profundidadDeseada));

			for (LoteMedicamentoEnDeposito_VO lote : getLotesMedicamentoEnDeposito()) {
				med.getLotesMedicamento()
						.add(lote.toObject(profundidadActual + 1,
								profundidadDeseada));
			}
		}

		return med;
	}

	@Override
	public void setObject(MedicamentoDeposito objeto) {

		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setDeposito(objeto.getDeposito().toValueObject());
		this.setMedicamentoComercial(objeto.getMedicamentoComercial()
				.toValueObject());

		for (Deposito depoAnt : objeto.getDepositosAnteriores()) {
			this.getDepositosAnteriores().add(depoAnt.toValueObject());
		}

		for (LoteMedicamentoEnDeposito lote : objeto.getLotesMedicamento()) {
			this.getLotesMedicamentoEnDeposito().add(lote.toValueObject());
		}

		this.setCantidadActual(objeto.getCantidadMinima());
		this.setCantidadMinima(objeto.getCantidadMaxima());
		this.actualizarStock();
	}

	@Override
	public void setObject(MedicamentoDeposito objeto, int profundidadActual,
			int profundidadDeseada) {

		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		this.setCantidadMaxima(objeto.getCantidadMaxima());
		this.setCantidadMinima(objeto.getCantidadMinima());

		if (profundidadActual < profundidadDeseada) {

			this.setMedicamentoComercial(objeto.getMedicamentoComercial()
					.toValueObject(profundidadActual + 1, profundidadDeseada));

			this.setDeposito(objeto.getDeposito().toValueObject(
					profundidadActual + 1, profundidadDeseada));

			for (Deposito depoAnt : objeto.getDepositosAnteriores()) {
				this.getDepositosAnteriores().add(
						depoAnt.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

		}

		for (LoteMedicamentoEnDeposito lote : objeto.getLotesMedicamento()) {
			this.getLotesMedicamentoEnDeposito().add(
					lote.toValueObject(profundidadActual, profundidadDeseada));
		}
		this.actualizarStock();

	}

	@Override
	public String toString() {
		return this.getMedicamentoComercial().getNombre() + " en "
				+ this.getDeposito().getNombre();
	}

	/**
	 * @return the lotesMedicamentoEnDeposito
	 */
	public List<LoteMedicamentoEnDeposito_VO> getLotesMedicamentoEnDeposito() {
		return lotesMedicamentoEnDeposito;
	}

	/**
	 * Agrega el lote en el medicamento, si estaba lo reemplazo, sino lo agrego
	 * 
	 * @param loteEnDepo
	 */
	public void agregarLote(LoteMedicamentoEnDeposito_VO loteEnDepo) {
		int indiceLote = this.getLotesMedicamentoEnDeposito().indexOf(
				loteEnDepo);

		if (indiceLote > 0) {
			// Si esta lo reemplazo
			getLotesMedicamentoEnDeposito().set(indiceLote, loteEnDepo);
		} else {
			// Sino estaba lo agrego sin mas
			getLotesMedicamentoEnDeposito().add(loteEnDepo);
		}
	}

	public void actualizarStock() {
		int total = 0;
		for (LoteMedicamentoEnDeposito_VO lote : getLotesMedicamentoEnDeposito()) {
			total += lote.getCantidad();
		}
		this.setCantidadActual(total);
	}

	public boolean stockMinimo() {
		return this.getCantidadActual() <= getCantidadMinima();
	}

	public boolean stockMaximo() {
		return this.getCantidadActual() >= getCantidadMaxima();
	}

}