package ar.org.hospitalespanol.vo.farmacia.medicamentos;

import java.io.Serializable;

import ar.org.hospitalespanol.model.farmacia.medicamentos.PrincipioActivo;
import ar.org.hospitalespanol.vo.I_ValueObject;

import com.google.gson.Gson;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class PrincipioActivo_VO implements Serializable,
		I_ValueObject<PrincipioActivo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	/**
	 * Nombre de la medicamento
	 */
	private String nombre;

	/**
	 * Descripcion de la medicamento
	 */
	private String descripcion;

	// Constructores
	public PrincipioActivo_VO() {

	}

	public PrincipioActivo_VO(PrincipioActivo pm) {
		setObject(pm);
	}

	public PrincipioActivo_VO(PrincipioActivo pm, int profundidadActual,
			int profundidadDeseada) {
		setObject(pm, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof PrincipioActivo) {
			PrincipioActivo o = (PrincipioActivo) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
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

	@Override
	public String toString() {
		return this.getNombre();
	}

	@Override
	public PrincipioActivo toObject() {
		return new PrincipioActivo(this.getId(), getVersion(), getNombre(),
				getDescripcion());
	}

	public PrincipioActivo toObject(int profundidadActual,
			int profundidadDeseada) {
		return new PrincipioActivo(this.getId(), getVersion(), getNombre(),
				getDescripcion());
	}

	@Override
	public void setObject(PrincipioActivo o) {
		this.setId(o.getId());
		this.setVersion(o.getVersion());
		this.setNombre(o.getNombre());
		this.setDescripcion(o.getDescripcion());
	}

	@Override
	public void setObject(PrincipioActivo o, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(o);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}