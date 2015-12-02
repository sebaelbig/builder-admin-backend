package ar.com.builderadmin.vo;

import java.io.Serializable;

import org.pojomatic.Pojomatic;

/**
 * Abstract Value Object.
 * 
 * @author fgonzalez
 *
 */
public abstract class AbstractVo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7965505608380560059L;
	
	/**
	 * VO id.
	 */
	private Long id;
	
	/**
	 * Validates this VO.
	 * 
	 * @param wch the web context holder.
	 * 
	 * @return string iif is not valid
	 */
//	public String validate(WebContextHolder wch) {
//		return null;
//	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return Pojomatic.equals(this, obj);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}
	
}
