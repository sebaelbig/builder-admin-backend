package ar.com.builderadmin.vo.core.usuarios;

/**
 * Clase que hace referencia a los links que se devuelven para que aplicaciones de terceros puedan ingresar
 * 
 * @author segarcia
 */
public class LinkExterno_VO {

	//	 titulo: "Historia ClÃ­nica Electronica", 
	private String titulo;

	//     urlDestino:"http://172.20.32.249:8090/he_fe/#/?alf_user="+usuarioLogueado, 
	private String urlDestino;

	//     img: "hc_min.png"
	private String icono;

	
	public LinkExterno_VO(String titulo, String urlDestino, String icono) {
		super();
		this.titulo = titulo;
		this.urlDestino = urlDestino;
		this.icono = icono;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the urlDestino
	 */
	public String getUrlDestino() {
		return urlDestino;
	}

	/**
	 * @param urlDestino the urlDestino to set
	 */
	public void setUrlDestino(String urlDestino) {
		this.urlDestino = urlDestino;
	}

	/**
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
	
}
