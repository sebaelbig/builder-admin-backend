package ar.org.hospitalespanol.vo.core.usuarios;


public class Mensaje_VO  {

	private String fecha;
	private String usuario;
	private String texto;
	private Integer nroPedido;
	private Integer id;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(Integer nroPedido) {
		this.nroPedido = nroPedido;
	}
	
}
