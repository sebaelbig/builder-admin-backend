package ar.org.hospitalespanol.vo.core.especialidades;

import ar.org.hospitalespanol.model.core.especialidades.Especialidad;
import ar.org.hospitalespanol.vo.I_ValueObject;

import com.google.gson.Gson;

public class Especialidad_VO implements I_ValueObject<Especialidad> {

	private Long id;
	private Integer version;
	private Boolean borrado = false;

	private Integer codigo;
	private String nombre;
	private String nombreEspecialidad;
	private String descripcion;

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object objeto) {
		if ((objeto instanceof Especialidad_VO)) {
			Especialidad_VO o = (Especialidad_VO) objeto;
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public String getNombreEspecialidad() {
		return this.nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
		this.nombre = nombreEspecialidad;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Especialidad_VO(Especialidad espe) {
		this.setObject(espe);
	}

	public Especialidad_VO(Especialidad espe, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(espe, profundidadActual, profundidadDeseada);
	}

	public Especialidad_VO() {
		// TODO Auto-generated constructor stub
	}

	public Especialidad_VO(String nombre) {
		setNombre(nombre);
	}

	@Override
	public void setObject(Especialidad espe) {
		this.setId(espe.getId());
		this.setNombre(espe.getNombre());
		this.setDescripcion(espe.getDescripcion());
	}

	@Override
	public void setObject(Especialidad espe, int profundidadActual,
			int profundidadDeseada) {
		this.setId(espe.getId());
		this.setNombre(espe.getNombre());
		this.setDescripcion(espe.getDescripcion());
	}

	public Especialidad toObject(int profundidadActual, int profundidadDeseada) {
		return toObject();
	}

	@Override
	public Especialidad toObject() {
		Especialidad espe = new Especialidad();

		espe.setId(this.getId());
		espe.setDescripcion(this.getDescripcion());
		espe.setNombre(this.getNombre());

		return espe;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	/**
	 * @return the version
	 */
	@Override
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the borrado
	 */
	@Override
	public Boolean getBorrado() {
		return borrado;
	}

	/**
	 * @param borrado the borrado to set
	 */
	@Override
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}
	
	
}