package ar.com.builderadmin.vo.internacion.habitaciones;

import ar.com.builderadmin.model.internacion.habitaciones.TipoCategoriaHabitacion;
import ar.com.builderadmin.vo.I_ValueObject;

public class TipoCategoriaHabitacion_VO implements
		I_ValueObject<TipoCategoriaHabitacion> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private String nombre;
	private String codigo;

	public TipoCategoriaHabitacion_VO() {
	}

	public TipoCategoriaHabitacion_VO(TipoCategoriaHabitacion tipoRol) {
		setObject(tipoRol);
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(TipoCategoriaHabitacion tr) {
		if (tr != null) {
			this.setId(tr.getId());
			this.setVersion(tr.getVersion());
			this.setCodigo(tr.getCodigo());
			this.setNombre(tr.getNombre());
		}
	}

	@Override
	public TipoCategoriaHabitacion toObject() {
		return new TipoCategoriaHabitacion(this.getId(), this.getVersion(),
				this.getCodigo(), this.getNombre());
	}

	@Override
	public String toString() {
		return "(" + this.getCodigo() + ")" + this.getNombre();
	}

	@Override
	public void setObject(TipoCategoriaHabitacion objeto,
			int profundidadActual, int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	public TipoCategoriaHabitacion toObject(int profundidadActual,
			int profundidadDeseada) {
		return this.toObject();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoCategoriaHabitacion_VO) {
			TipoCategoriaHabitacion_VO o = (TipoCategoriaHabitacion_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

}
