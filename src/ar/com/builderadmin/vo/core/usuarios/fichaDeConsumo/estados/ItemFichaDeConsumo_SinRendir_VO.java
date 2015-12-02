package ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo.estados;

import ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo;
import ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.estados.ItemFichaDeConsumo_SinRendir;

public class ItemFichaDeConsumo_SinRendir_VO extends
		EstadoItemFichaDeConsumo_VO {

	public ItemFichaDeConsumo_SinRendir_VO() {
	}

	public ItemFichaDeConsumo_SinRendir_VO(
			ItemFichaDeConsumo_SinRendir itemFichaDeConsumo_SinRendir) {
		setObject(itemFichaDeConsumo_SinRendir);
	}

	@Override
	public void setObject(EstadoItemFichaDeConsumo objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());
		this.setNombre("Sin Rendir");
	}

	@Override
	public EstadoItemFichaDeConsumo toObject() {
		ItemFichaDeConsumo_SinRendir resul = new ItemFichaDeConsumo_SinRendir();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		return resul;
	}

}
