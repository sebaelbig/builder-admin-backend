package ar.org.hospitalespanol.vo.historiaClinica.episodios;

import java.io.Serializable;

public class EpisodioLight_VO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Long sucursal;
	private String nroEpisodio;
	private String apellido;
	private String nombres;
	private String nroDocumento;
	private String sexo;
	private String prestacion;

	public EpisodioLight_VO(Long id, Long sucursal, String nroEpisodio,
			String apellido, String nombres, String nroDocumento, String sexo,
			String prestacionNombre, String prestacionCodigo) {
		setId(id);
		setSucursal(sucursal);
		setNroEpisodio(nroEpisodio);
		setApellido(apellido);
		setNombres(nombres);
		setNroDocumento(nroDocumento);
		setSexo(sexo);
		setPrestacion("(" + prestacionCodigo + ") " + prestacionNombre);
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return getApellido() + ", " + getNombres() + " (" + getNroDocumento()
				+ ")";
	}

	public Integer getVersion() {
		return null;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNroDocumento() {
		return this.nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getNroEpisodio() {
		return this.nroEpisodio;
	}

	public void setNroEpisodio(String nroEpisodio) {
		this.nroEpisodio = nroEpisodio;
	}

	public String getPrestacion() {
		return this.prestacion;
	}

	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}
}

/*
 * Location: D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * 
 * Qualified Name:
 * WEB-INF.classes.org.hospitalespanol.historiaClinica.episodios.
 * EpisodioLight_VO
 * 
 * JD-Core Version: 0.7.0.1
 */