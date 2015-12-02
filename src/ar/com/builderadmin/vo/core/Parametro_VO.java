package ar.com.builderadmin.vo.core;

import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.model.core.Parametro;
import ar.com.builderadmin.vo.I_ValueObject;

public class Parametro_VO implements I_ValueObject<Parametro> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String nombre;
	private String valor;
	private E_TipoParametro type;
	private String grupo;

	public Parametro_VO() {
	}

	public Parametro_VO(Parametro propiedad) {
		setObject(propiedad);
	}

	@Override
	public void setObject(Parametro o) {
		
		setId(o.getId());
		setBorrado(o.getBorrado());
		setVersion(o.getVersion());
		
		setNombre(o.getNombre());
		setValor(o.getValor());
		setType(o.getType());
		setGrupo(o.getGrupo());
	}

	@Override
	public Parametro toObject() {
		
		Parametro prop = new  Parametro();
		
		prop.setBorrado(getBorrado());
		prop.setId(getId());
		prop.setVersion(getVersion());
		
		prop.setNombre(getNombre());
		prop.setValor(getValor());
		prop.setType(getType());
		prop.setGrupo(getGrupo());
		
		return prop;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
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
			return  this.getId().equals(((Parametro_VO)arg0).getId());
		}catch (Exception e){return false;}
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the type
	 */
	public E_TipoParametro getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(E_TipoParametro type) {
		this.type = type;
	}

	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public void setObject(Parametro objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(objeto);
	}
	
}
