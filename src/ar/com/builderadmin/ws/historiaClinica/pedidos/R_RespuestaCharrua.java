package ar.com.builderadmin.ws.historiaClinica.pedidos;

public class R_RespuestaCharrua {

	private String codigo;
	private String detalle;
	
	public R_RespuestaCharrua() {
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
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	@Override
	public String toString() {
		return "Codigo: "+this.getCodigo() + " - Detalle: "+this.getDetalle();
	}
}
