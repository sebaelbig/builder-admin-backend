package ar.org.hospitalespanol.vo.core.datosSalud;

import ar.org.hospitalespanol.model.core.datosSalud.ItemModulo;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class ItemModulo_VO implements I_ValueObject<ItemModulo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private Integer cantidad;
	private Prestacion_VO prestacion;
	private Long idPrestacion;

	public ItemModulo_VO() {

	}

	public ItemModulo_VO(ItemModulo itemModulo) {
		setObject(itemModulo);
	}

	public ItemModulo_VO(ItemModulo itemModulo, int profundidadActual,
			int profundidadDeseada) {
		setObject(itemModulo, profundidadActual, profundidadDeseada);
	}

	public Long getIdPrestacion() {
		return idPrestacion;
	}

	public void setIdPrestacion(Long idPrestacion) {
		this.idPrestacion = idPrestacion;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(ItemModulo itemModulo) {
		this.setId(itemModulo.getId());
		this.setVersion(itemModulo.getVersion());
		this.setCantidad(itemModulo.getCantidad());

		this.setIdPrestacion(itemModulo.getPrestacion().getId());
		this.setPrestacion(itemModulo.getPrestacion().toValueObject());

	}

	@Override
	public void setObject(ItemModulo itemModulo, int profundidadActual,
			int profundidadDeseada) {
		this.setId(itemModulo.getId());
		this.setVersion(itemModulo.getVersion());
		this.setCantidad(itemModulo.getCantidad());

		this.setIdPrestacion(itemModulo.getPrestacion().getId());

		if (profundidadActual < profundidadDeseada) {

			this.setPrestacion(itemModulo.getPrestacion().toValueObject(
					profundidadActual + 1, profundidadDeseada));
		}

	}

	@Override
	public ItemModulo toObject() {
		return new ItemModulo(this.getId(), this.getVersion(),
				this.getCantidad(), this.getPrestacion().toObject());

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Prestacion_VO getPrestacion() {
		return prestacion;
	}

	public void setObraSocial(Prestacion_VO prestacion) {
		this.prestacion = prestacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setPrestacion(Prestacion_VO prestacion) {
		this.prestacion = prestacion;
	}

}
