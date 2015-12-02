package ar.com.builderadmin.model;

/**
 * General Model Interface.
 *
 * @author fgonzalez
 *
 */
public interface I_Entidad {

	/**
	 * Entity ID getter.
	 * 
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Entity ID setter.
	 * 
	 * @param id to set
	 */
	public void setId(Long id);
	
	/**
	 * Entity Version getter.
	 * 
	 * @return version
	 */
	public Integer getVersion();
	
	/**
	 * Entity Version setter.
	 * 
	 * @param version to set
	 */
	public void setVersion(Integer id);
	
	/**
	 * Entity Borrado getter.
	 * 
	 * @return version
	 */
	public Boolean getBorrado();
	
	/**
	 * Entity Version setter.
	 * 
	 * @param version to set
	 */
	public void setBorrado(Boolean b);
	
}
