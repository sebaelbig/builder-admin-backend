package ar.com.builderadmin.vo.core.obrasSociales;

import java.util.Date;

import ar.com.builderadmin.model.core.obrasSociales.EstadoProducto_OS;
import ar.com.builderadmin.vo.I_ValueObject;

public class EstadoProducto_OS_VO implements I_ValueObject<EstadoProducto_OS> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	public String tipo;

	public EstadoProducto_OS_VO() {
	}

	public EstadoProducto_OS_VO(EstadoProducto_OS estadoProducto) {
		setObject(estadoProducto);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(EstadoProducto_OS estadoProducto) {
	};

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTipo() {
		return null;
	};

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return null;
	};

	public Boolean habilitada() {
		return false;
	}

	@Override
	public EstadoProducto_OS toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(EstadoProducto_OS objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	public EstadoProducto_OS toObject(int profundidadActual,
			int profundidadDeseada) {
		return null;
	}

}