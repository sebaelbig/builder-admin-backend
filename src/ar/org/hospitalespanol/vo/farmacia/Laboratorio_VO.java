package ar.org.hospitalespanol.vo.farmacia;

import ar.org.hospitalespanol.model.farmacia.Laboratorio;
import ar.org.hospitalespanol.vo.I_ValueObject;

import com.google.gson.Gson;

public class Laboratorio_VO implements I_ValueObject<Laboratorio>{


	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String nombre;
	private String descripcion;

	public Laboratorio_VO() {
	}

	public Laboratorio_VO(Laboratorio labo) {
		setObject(labo);
	}
	
	public Laboratorio_VO(Laboratorio labo, int profundidadActual, int profundidadDeseada) {
		setObject(labo, profundidadActual, profundidadDeseada);
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
	public Laboratorio toObject() {
		return new Laboratorio(this.getId(), this.getVersion(), this
				.getNombre(), this.getDescripcion());
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Laboratorio_VO) {
			Laboratorio_VO o = (Laboratorio_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public String toString(){
		return getNombre();
	}

	@Override
	public void setObject(Laboratorio labo) {
		this.setId(labo.getId());
		this.setVersion(labo.getVersion());
		this.setNombre(labo.getNombre());
		this.setDescripcion(labo.getDescripcion());
	}
	
	@Override
	public void setObject(Laboratorio labo, int profundidadActual, int profundidadDeseada) {
		this.setId(labo.getId());
		this.setVersion(labo.getVersion());
		this.setNombre(labo.getNombre());
		this.setDescripcion(labo.getDescripcion());
	}

	public Laboratorio toObject(int profundidadActual, int profundidadDeseada) {
		Laboratorio labo = new Laboratorio();
		
		labo.setId(this.getId());
		labo.setVersion(this.getVersion());
		labo.setDescripcion(this.getDescripcion());
		labo.setNombre(this.getNombre());
		
		return labo; 
	}
	
	public String toJson(){
		return new Gson().toJson(this);
	}
}
