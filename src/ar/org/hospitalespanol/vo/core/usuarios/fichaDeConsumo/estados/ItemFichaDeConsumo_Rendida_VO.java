package ar.org.hospitalespanol.vo.core.usuarios.fichaDeConsumo.estados;

import ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo;
import ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo.estados.ItemFichaDeConsumo_Rendida;

public class ItemFichaDeConsumo_Rendida_VO extends EstadoItemFichaDeConsumo_VO {

	public ItemFichaDeConsumo_Rendida_VO(
			ItemFichaDeConsumo_Rendida fichaDeConsumo_Rendida) {
		this.setObject(fichaDeConsumo_Rendida);
	}

	@Override
	public void setObject(EstadoItemFichaDeConsumo objeto) {
		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());
		this.setNombre("Rendido");
	}

	@Override
	public ItemFichaDeConsumo_Rendida toObject() {
		ItemFichaDeConsumo_Rendida resul = new ItemFichaDeConsumo_Rendida();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		return resul;
	}

}
