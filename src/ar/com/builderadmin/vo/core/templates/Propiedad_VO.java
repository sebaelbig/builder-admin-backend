package ar.com.builderadmin.vo.core.templates;

import ar.com.builderadmin.model.core.templates.Propiedad;
import ar.com.builderadmin.vo.I_ValueObject;

public class Propiedad_VO implements I_ValueObject<Propiedad> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String nombre;
	private String atributo;

	public Propiedad_VO() {
	}

	public Propiedad_VO(Propiedad propiedad) {
		setObject(propiedad);
	}

	@Override
	public void setObject(Propiedad objeto) {
		
		setId(objeto.getId());
		setBorrado(objeto.getBorrado());
		setVersion(objeto.getVersion());
		setNombre(objeto.getNombre());
		setAtributo(objeto.getAtributo());

	}

	@Override
	public Propiedad toObject() {
		
		Propiedad prop = new  Propiedad();
		
		prop.setBorrado(getBorrado());
		prop.setId(getId());
		prop.setNombre(getNombre());
		prop.setVersion(getVersion());
		prop.setAtributo(getAtributo());
		
		return prop;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	@Override
	public void setObject(Propiedad objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	@Override
	public String toString() {
		return getNombre();
	}
	
	@Override
	public boolean equals(Object arg0) {
		try{
			return  this.getId().equals(((Propiedad_VO)arg0).getId());
		}catch (Exception e){return false;}
	}

	/**
	 * @return the atributo
	 */
	public String getAtributo() {
		return atributo;
	}

	/**
	 * @param atributo the atributo to set
	 */
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	
	
}
