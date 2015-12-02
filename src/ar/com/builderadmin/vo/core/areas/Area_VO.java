package ar.com.builderadmin.vo.core.areas;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.model.core.areas.AreaHorus;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;

public class Area_VO implements I_ValueObject<AreaHorus> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;
	private String nombre;
	private String codigo;
	private Sucursal_VO sucursal;

	private List<Servicio_VO> servicios = new ArrayList<Servicio_VO>();

	public Area_VO() {
		setServicios(new ArrayList<Servicio_VO>());
	}

	public Area_VO(AreaHorus area) {
		setServicios(new ArrayList<Servicio_VO>());
		setObject(area);
	}

	public Area_VO(AreaHorus areaHorus, int profundidadActual,
			int profundidadDeseada) {
		setObject(areaHorus, profundidadActual, profundidadDeseada);
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object objeto) {
		if ((objeto instanceof Area_VO)) {
			Area_VO o = (Area_VO) objeto;
			return o.getId().equals(getId());
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + getCodigo() + ") " + getNombre();
	}

	public Sucursal_VO getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal_VO sucursal) {
		this.sucursal = sucursal;
	}

	public List<Servicio_VO> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio_VO> servicios) {
		this.servicios = servicios;
	}

	@Override
	public void setObject(AreaHorus area) {
		this.setId(area.getId());
		this.setVersion(area.getVersion());
		this.setNombre(area.getNombre());
		this.setCodigo(area.getCodigo());

		this.setSucursal(area.getSucursal().toValueObject());

		setServicios(new ArrayList<Servicio_VO>());
		if (area.getServicios() != null) {

			for (Servicio s : area.getServicios()) {
				this.getServicios().add(
						s.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

		}
	}

	@Override
	public void setObject(AreaHorus area, int profundidadActual,
			int profundidadDeseada) {

		this.setId(area.getId());
		this.setVersion(area.getVersion());
		this.setNombre(area.getNombre());
		this.setCodigo(area.getCodigo());

		if (profundidadActual < profundidadDeseada) {
			if (this.getSucursal() != null) {
				this.setSucursal(area.getSucursal().toValueObject(
						profundidadActual + 1, profundidadDeseada));
			}

			setServicios(new ArrayList<Servicio_VO>());
			for (Servicio s : area.getServicios()) {
				this.getServicios().add(
						s.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

		}

	}

	public AreaHorus toObjet(int profundidadActual, int profundidadDeseada) {
		return this.toObject();
	}

	@Override
	public AreaHorus toObject() {
		AreaHorus area = new AreaHorus(this.getId(), this.getVersion(),
				this.getNombre(), this.getCodigo());
		area.setBorrado(getBorrado());
		return area;
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}