package ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo.estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.org.hospitalespanol.vo.core.usuarios.fichaDeConsumo.estados.ItemFichaDeConsumo_Rendida_VO;

@Entity 
@DiscriminatorValue("item_rendida")
public class ItemFichaDeConsumo_Rendida extends EstadoItemFichaDeConsumo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ItemFichaDeConsumo_Rendida_VO toValueObject() {
		return new ItemFichaDeConsumo_Rendida_VO(this);
	}

}
