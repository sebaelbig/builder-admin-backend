package ar.org.hospitalespanol.vo.internacion;


public class Pabellon_VO  {
	
	/**
	 * Representa los pabellones, las camas estan sectorizadas por pabellon
	 * @author carlalu
	 */
	
	int pabellon;
	String denominacion;
	
	public int getPabellon() {
		return pabellon;
	}
	public void setPabellon(int pabellon) {
		this.pabellon = pabellon;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	

}
