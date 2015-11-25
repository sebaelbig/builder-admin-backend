package ar.org.hospitalespanol.vo.historiaClinica.pedidos;

import ar.org.hospitalespanol.model.historiaClinica.pedidos.Modalidad;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class Modalidad_VO implements I_ValueObject<Modalidad> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String codigo;
	private String descripcion;
	
	public Modalidad_VO() {}
	
	public Modalidad_VO(Modalidad mod) {
		this.setObject(mod);
	}
	
	public Modalidad_VO(Modalidad mod, int profAc, int profDes) {
		this.setObject(mod, profAc, profDes);
	}

	@Override
	public void setObject(Modalidad mod) {
		
		this.setId(mod.getId());
		this.setVersion(mod.getVersion());
		this.setBorrado(mod.getBorrado());
		
		this.setCodigo(mod.getCodigo());
		this.setDescripcion(mod.getDescripcion());
	}


	@Override
	public void setObject(Modalidad mod, int profundidadActual,
			int profundidadDeseada) {
		this.setId(mod.getId());
		this.setVersion(mod.getVersion());
		this.setBorrado(mod.getBorrado());
		
		this.setCodigo(mod.getCodigo());
		this.setDescripcion(mod.getDescripcion());
	}

	@Override
	public Modalidad toObject() {
		Modalidad mod = new Modalidad();

		mod.setId(this.getId());
		mod.setVersion(this.getVersion());
		mod.setBorrado(this.getBorrado());
		
		mod.setCodigo(this.getCodigo());
		mod.setDescripcion(this.getDescripcion());
		
		return mod;
	}

	public Modalidad toObject(int profundidadActual, int profundidadDeseada) {
		Modalidad mod = new Modalidad();

		mod.setId(this.getId());
		mod.setVersion(this.getVersion());
		mod.setBorrado(this.getBorrado());
		
		mod.setCodigo(this.getCodigo());
		mod.setDescripcion(this.getDescripcion());
		
		return mod;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public String toString() {
		return "Modalidad: " + this.getCodigo();
	}


	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Modalidad_VO) {
			Modalidad_VO o = (Modalidad_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}