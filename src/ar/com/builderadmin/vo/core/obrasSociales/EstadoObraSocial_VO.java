package ar.com.builderadmin.vo.core.obrasSociales;

import java.util.Date;

import ar.com.builderadmin.model.core.obrasSociales.EstadoObraSocial;
import ar.com.builderadmin.vo.I_ValueObject;

public class EstadoObraSocial_VO implements I_ValueObject<EstadoObraSocial> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String nombre;

	public EstadoObraSocial_VO() {

	}

	public EstadoObraSocial_VO(EstadoObraSocial estadoObraSocial) {
		setObject(estadoObraSocial);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(EstadoObraSocial objeto) {
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());
	}

	@Override
	public EstadoObraSocial toObject() {
		return new EstadoObraSocial(getId(), getVersion(), getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getFecha() {
		return null;
	}

	public Boolean habilitada() {
		return true;
	}

	public String getMotivo() {
		return "";
	}

	@Override
	public void setObject(EstadoObraSocial objeto, int profundidadActual,
			int profundidadDeseada) {
		// Las subclases lo implementan
	}

	public EstadoObraSocial toObject(int profundidadActual,
			int profundidadDeseada) {
		// Las subclases lo implementan
		return null;
	}

}
