package ar.org.hospitalespanol.vo.farmacia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.farmacia.Farmacia;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.farmacia.stock.Deposito_VO;

import com.google.gson.Gson;

/**
 * farmacia que expide el medicamento
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class Farmacia_VO implements Serializable, I_ValueObject<Farmacia> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	/**
	 * Nombre de farmacia
	 */
	private String nombre;

	/**
	 * Descripcion de farmacia
	 */
	private String descripcion;

	private List<Deposito_VO> depositos = new ArrayList<Deposito_VO>();

	// Constructores
	public Farmacia_VO() {
	}

	public Farmacia_VO(Farmacia f) {
		this.setObject(f);
	}

	public Farmacia_VO(Farmacia labo, int profundidadActual,
			int profundidadDeseada) {
		setObject(labo, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Farmacia) {
			Farmacia o = (Farmacia) objeto;
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

	public List<Deposito_VO> getDepositos() {
		return depositos;
	}

	public void setDepositos(List<Deposito_VO> depositos) {
		this.depositos = depositos;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public Farmacia toObject() {
		Farmacia resul = new Farmacia();

		resul.setId(getId());
		resul.setVersion(getVersion());
		resul.setDescripcion(getDescripcion());
		resul.setNombre(getNombre());

		return resul;
	}

	public Farmacia toObject(int profundidadActual, int profundidadDeseada) {
		Farmacia resul = new Farmacia();

		resul.setId(getId());
		resul.setVersion(getVersion());
		resul.setDescripcion(getDescripcion());
		resul.setNombre(getNombre());

		return resul;
	}

	@Override
	public void setObject(Farmacia o) {
		this.setId(o.getId());
		this.setVersion(o.getVersion());

		this.setDescripcion(o.getDescripcion());
		this.setNombre(o.getNombre());

	}

	@Override
	public void setObject(Farmacia o, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(o);
	}

}