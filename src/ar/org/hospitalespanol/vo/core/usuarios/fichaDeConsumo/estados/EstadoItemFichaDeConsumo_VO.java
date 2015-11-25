package ar.org.hospitalespanol.vo.core.usuarios.fichaDeConsumo.estados;

import ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class EstadoItemFichaDeConsumo_VO implements
		I_ValueObject<EstadoItemFichaDeConsumo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String nombre;

	public EstadoItemFichaDeConsumo_VO() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public void setObject(EstadoItemFichaDeConsumo objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstadoItemFichaDeConsumo toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(EstadoItemFichaDeConsumo objeto,
			int profundidadActual, int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	public EstadoItemFichaDeConsumo toObject(int profundidadActual,
			int profundidadDeseada) {
		return this.toObject();
	}

}
