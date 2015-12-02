package ar.com.builderadmin.vo.farmacia.stock;

import com.google.gson.Gson;

import ar.com.builderadmin.model.farmacia.stock.Deposito;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.farmacia.Farmacia_VO;

public class Deposito_VO implements I_ValueObject<Deposito> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String nombre;
	private String descripcion;

	private Farmacia_VO farmacia;

	public Deposito_VO() {
	}

	public Deposito_VO(Deposito labo) {
		setObject(labo);
	}

	public Deposito_VO(Deposito de, int profundidadActual,
			int profundidadDeseada) {
		setObject(de, profundidadActual, profundidadDeseada);
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
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Deposito toObject() {
		return new Deposito(this.getId(), this.getVersion(), this.getNombre(),
				this.getDescripcion());
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Deposito_VO) {
			Deposito_VO o = (Deposito_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	@Override
	public void setObject(Deposito labo) {
		this.setId(labo.getId());
		this.setVersion(labo.getVersion());
		this.setNombre(labo.getNombre());
		this.setDescripcion(labo.getDescripcion());

		this.setFarmacia(labo.getFarmacia().toValueObject());
	}

	@Override
	public void setObject(Deposito labo, int profundidadActual,
			int profundidadDeseada) {
		this.setId(labo.getId());
		this.setVersion(labo.getVersion());
		this.setNombre(labo.getNombre());
		this.setDescripcion(labo.getDescripcion());

		if (profundidadActual < profundidadDeseada) {
			this.setFarmacia(labo.getFarmacia().toValueObject(
					profundidadActual + 1, profundidadDeseada));
		}
	}

	public Deposito toObject(int profundidadActual, int profundidadDeseada) {
		Deposito depo = new Deposito();

		depo.setId(this.getId());
		depo.setVersion(this.getVersion());
		depo.setDescripcion(this.getDescripcion());
		depo.setNombre(this.getNombre());

		if (profundidadActual < profundidadDeseada) {
			depo.setFarmacia(this.getFarmacia().toObject(profundidadActual + 1,
					profundidadDeseada));
		}

		return depo;
	}

	/**
	 * @return the farmacia
	 */
	public Farmacia_VO getFarmacia() {
		return farmacia;
	}

	/**
	 * @param farmacia
	 *            the farmacia to set
	 */
	public void setFarmacia(Farmacia_VO farmacia) {
		this.farmacia = farmacia;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

}
