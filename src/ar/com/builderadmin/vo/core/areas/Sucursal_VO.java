package ar.com.builderadmin.vo.core.areas;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ar.com.builderadmin.model.core.areas.AreaHorus;
import ar.com.builderadmin.model.core.areas.Sucursal;
import ar.com.builderadmin.vo.I_ValueObject;

public class Sucursal_VO implements I_ValueObject<Sucursal> {

	private Long id;
	private Boolean borrado = false;

	private Integer version;
	private String nombre;
	private String direccion;
	private String telefono;
	private String codigo;
	
	private boolean tieneConsultorios = false;
	private boolean tieneInternacion = false;
	private boolean tieneFarmacia = false;
	
	private List<Area_VO> areas = new ArrayList<Area_VO>();

	// private final List<CirculoDeConfianza_VO> circulosDeConfianza = new
	// ArrayList<CirculoDeConfianza_VO>();

	public Sucursal_VO() {
	}

	public Sucursal_VO(Sucursal sucursal) {
		setObject(sucursal);
	}

	public Sucursal_VO(Sucursal sucursal, int profundidadActual,
			int profundidadDeseada) {
		setObject(sucursal, profundidadActual, profundidadDeseada);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	@Override
	public boolean equals(Object object) {
		if ((object instanceof Sucursal_VO)) {
			Sucursal_VO s = (Sucursal_VO) object;
			return s.getId().equals(getId());
		}
		return false;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isTieneConsultorios() {
		return this.tieneConsultorios;
	}

	public void setTieneConsultorios(boolean tieneConsultorios) {
		this.tieneConsultorios = tieneConsultorios;
	}

	public boolean isTieneInternacion() {
		return this.tieneInternacion;
	}

	public void setTieneInternacion(boolean tieneInternacion) {
		this.tieneInternacion = tieneInternacion;
	}

	public boolean isTieneFarmacia() {
		return this.tieneFarmacia;
	}

	public void setTieneFarmacia(boolean tieneFarmacia) {
		this.tieneFarmacia = tieneFarmacia;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Area_VO> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area_VO> areas) {
		this.areas = areas;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public Sucursal toObject() {
		Sucursal suc = new Sucursal();
		suc.setDireccion(getDireccion());
		suc.setId(getId());
		suc.setNombre(getNombre());
		suc.setTelefono(getTelefono());
		suc.setTieneConsultorios(isTieneConsultorios());
		suc.setTieneFarmacia(isTieneFarmacia());
		suc.setTieneInternacion(isTieneInternacion());
		suc.setVersion(getVersion());
		suc.setCodigo(getCodigo());
		suc.setBorrado(this.getBorrado());
		
		for (Area_VO avo : this.getAreas()) {
			suc.getAreas().add(avo.toObject());
		}

		// for (CirculoDeConfianza_VO cc : this.getCirculosDeConfianza()) {
		// suc.getCirculosDeConfianza().add(
		// cc.toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		// }

		return suc;
	}

	public Sucursal toObject(int profundidadActual, int profundidadDeseada) {
		return this.toObject();
	}

	// /**
	// * @return the circulosDeConfianza
	// */
	// public List<CirculoDeConfianza_VO> getCirculosDeConfianza() {
	// return circulosDeConfianza;
	// }

	@Override
	public void setObject(Sucursal objeto, int profundidadActual,
			int profundidadDeseada) {

		setDireccion(objeto.getDireccion());
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setTelefono(objeto.getTelefono());
		setVersion(objeto.getVersion());
		setTieneConsultorios(objeto.isTieneConsultorios());
		setTieneFarmacia(objeto.isTieneFarmacia());
		setTieneInternacion(objeto.isTieneInternacion());
		setCodigo(objeto.getCodigo());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			this.getAreas().clear();
			for (AreaHorus a : objeto.getAreas()) {

				this.getAreas().add(
						a.toValueObjet(profundidadActual + 1,
								profundidadDeseada));

			}

			// this.getCirculosDeConfianza().clear();
			// for (CirculoDeConfianza cc : objeto.getCirculosDeConfianza()){
			// this.getCirculosDeConfianza().add(cc.toValueObjet(I_ValueObject.PROFUNDIDAD_BASE,
			// 0));
			// }

		}

	}

	@Override
	public void setObject(Sucursal objeto) {

		setDireccion(objeto.getDireccion());
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setTelefono(objeto.getTelefono());
		setVersion(objeto.getVersion());
		setTieneConsultorios(objeto.isTieneConsultorios());
		setTieneFarmacia(objeto.isTieneFarmacia());
		setTieneInternacion(objeto.isTieneInternacion());
		setCodigo(objeto.getCodigo());

		this.getAreas().clear();
		for (AreaHorus a : objeto.getAreas()) {
			this.getAreas().add(
					a.toValueObjet(I_ValueObject.PROFUNDIDAD_BASE, 1));
		}
		this.getAreas().clear();

		// for (CirculoDeConfianza cc : objeto.getCirculosDeConfianza()) {
		// this.getCirculosDeConfianza().add(
		// cc.toValueObjet(I_ValueObject.PROFUNDIDAD_BASE, 0));
		// }
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