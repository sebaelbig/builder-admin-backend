package ar.com.builderadmin.vo.core.obrasSociales;

import ar.com.builderadmin.model.core.obrasSociales.TipoCoeficiente;
import ar.com.builderadmin.vo.I_ValueObject;

public class TipoCoeficiente_VO implements I_ValueObject<TipoCoeficiente> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String codigo;

	private String nombre;

	private String descripcion;

	public TipoCoeficiente_VO() {
	}

	public TipoCoeficiente_VO(TipoCoeficiente tipo) {
		setObject(tipo);
	}

	public TipoCoeficiente_VO(TipoCoeficiente tipoCoeficiente, int profActual,
			int profundidadDeseada) {
		setObject(tipoCoeficiente, profActual, profundidadDeseada);
	}

	@Override
	public TipoCoeficiente toObject() {
		return new TipoCoeficiente(getId(), getVersion(), getCodigo(),
				getNombre(), getDescripcion());
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoCoeficiente_VO) {
			TipoCoeficiente_VO o = (TipoCoeficiente_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		return getNombre() + " (" + getCodigo() + ")";
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void setObject(TipoCoeficiente objeto) {
		setCodigo(objeto.getCodigo());
		setDescripcion(objeto.getDescripcion());
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());
	}

	@Override
	public void setObject(TipoCoeficiente objeto, int profundidadActual,
			int profundidadDeseada) {
		setCodigo(objeto.getCodigo());
		setDescripcion(objeto.getDescripcion());
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());
	}
}
