package ar.com.builderadmin.vo.core.obrasSociales;

import ar.com.builderadmin.model.core.obrasSociales.EstadoContratoProducto;
import ar.com.builderadmin.vo.I_ValueObject;

public class EstadoContratoProducto_VO implements
		I_ValueObject<EstadoContratoProducto> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String motivo;

	public EstadoContratoProducto_VO() {
	}

	public EstadoContratoProducto_VO(
			EstadoContratoProducto estadoContratoProducto) {
		setObject(estadoContratoProducto);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(EstadoContratoProducto objeto) {
		setId(objeto.getId());
		setMotivo(objeto.getMotivo());
		setVersion(objeto.getVersion());
	}

	@Override
	public EstadoContratoProducto toObject() {
		return new EstadoContratoProducto(getId(), getVersion(), getMotivo());
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void setObject(EstadoContratoProducto objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

}
