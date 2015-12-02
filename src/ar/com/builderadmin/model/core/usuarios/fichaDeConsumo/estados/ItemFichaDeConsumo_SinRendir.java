package ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.estados;

import javax.persistence.DiscriminatorValue;

import ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo_VO;
import ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo.estados.ItemFichaDeConsumo_SinRendir_VO;

//@Entity
@DiscriminatorValue("item_sin_rendir")
public class ItemFichaDeConsumo_SinRendir extends EstadoItemFichaDeConsumo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemFichaDeConsumo_SinRendir() {
	}

	@Override
	public EstadoItemFichaDeConsumo_VO toValueObject() {
		return new ItemFichaDeConsumo_SinRendir_VO(this);
	}
}
