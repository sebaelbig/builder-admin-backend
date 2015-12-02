package ar.com.builderadmin.vo.core.templates;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.core.templates.Propiedad;
import ar.com.builderadmin.model.core.templates.PropiedadTemplate;
import ar.com.builderadmin.model.core.templates.Template;
import ar.com.builderadmin.vo.I_ValueObject;

public class Template_VO implements I_ValueObject<Template> {

	private Long id;
	private Boolean borrado = false;

	private Integer version;

	private Long idServicio;
	private String nombreServicio;

	private String tamanioPagina;

	private String orientacion;

	private String tamanioLetra;

	private String tipoFuente;

	private Boolean caratula = false;

	private Boolean pieDePagina = false;

	private String encabezado;
	private String pie;

	private String tipoFuentePie;

	private String tamanioLetraPie;

	private List<PropiedadTemplate_VO> propiedades;

	public Template_VO(Template objeto) {
		setObject(objeto);
	}

	public Template_VO() {
		setPropiedades(new ArrayList<PropiedadTemplate_VO>());
	}

	@Override
	public void setObject(Template objeto) {
		setBorrado(objeto.getBorrado());
		setId(objeto.getId());
		setIdServicio(objeto.getServicio().getId());
		setNombreServicio(objeto.getServicio().getNombre());
		setTamanioPagina(objeto.getTamanioPagina());
		setTamanioLetra(objeto.getTamanioLetra());
		setOrientacion(objeto.getOrientacion());
		setTipoFuente(objeto.getTipoFuente());
		setVersion(objeto.getVersion());
		setCaratula(objeto.getCaratula());
		setPie(objeto.getPie());
		setEncabezado(objeto.getEncabezado());
		setPieDePagina(objeto.getPieDePagina());

		if (objeto.getPieDePagina()) {
			switch (objeto.getTamanioLetraPie()) {
			case 8:
				setTamanioLetraPie(Template.FONT_SIZE_XX_PEQUENIO);
				break;
			case 10:
				setTamanioLetraPie(Template.FONT_SIZE_X_PEQUENIO);
				break;
			case 12:
				setTamanioLetraPie(Template.FONT_SIZE_PEQUENIO);
				break;
			case 14:
				setTamanioLetraPie(Template.FONT_SIZE_MEDIO);
				break;
			case 16:
				setTamanioLetraPie(Template.FONT_SIZE_GRANDE);
				break;
			case 18:
				setTamanioLetraPie(Template.FONT_SIZE_X_GRANDE);
				break;
			case 24:
				setTamanioLetraPie(Template.FONT_SIZE_XX_GRANDE);
				break;

			}

			setPie(objeto.getPie());
			setTipoFuentePie(objeto.getTipoFuentePie());

		}
		setPropiedades(new ArrayList<PropiedadTemplate_VO>());

		for (PropiedadTemplate prop : objeto.getPropiedades()) {
			PropiedadTemplate_VO p = new PropiedadTemplate_VO(prop);
			if (!p.getBorrado()){
				p.setSeleccionada(true);
				getPropiedades().add(p);
			}
		}

	}

	@Override
	public Template toObject() {
		
		Template temp = new Template();
		
		temp.setBorrado(getBorrado());
		temp.setId(getId());
		temp.setTamanioPagina(getTamanioPagina());
		temp.setOrientacion(getOrientacion());
		temp.setTamanioLetra(getTamanioLetra());
		temp.setTipoFuente(getTipoFuente());
		temp.setCaratula(getCaratula());
		temp.setPieDePagina(getPieDePagina());
		temp.setPie(getPie());
		temp.setEncabezado(getEncabezado());
		
		temp.setServicio(new Servicio());
		temp.getServicio().setId(getIdServicio());
		temp.getServicio().setNombre(getNombreServicio());
		
		if (getPieDePagina()) {
			temp.setPie(getPie());

			if (getTamanioLetraPie().equals(Template.FONT_SIZE_XX_PEQUENIO)) {
				temp.setTamanioLetraPie(8);
			}
			if (getTamanioLetraPie().equals(Template.FONT_SIZE_X_PEQUENIO)) {
				temp.setTamanioLetraPie(10);
			}
			if (getTamanioLetraPie().equals(Template.FONT_SIZE_PEQUENIO)) {
				temp.setTamanioLetraPie(12);
			}
			if (getTamanioLetraPie().equals(Template.FONT_SIZE_MEDIO)) {
				temp.setTamanioLetraPie(14);
			}
			if (getTamanioLetraPie().equals(Template.FONT_SIZE_GRANDE)) {
				temp.setTamanioLetraPie(18);
			}
			if (getTamanioLetraPie().equals(Template.FONT_SIZE_X_GRANDE)) {
				temp.setTamanioLetraPie(24);
			}
			if (getTamanioLetraPie().equals(Template.FONT_SIZE_XX_GRANDE)) {
				temp.setTamanioLetraPie(36);
			}

			temp.setTipoFuentePie(getTipoFuentePie());
		}

		temp.setVersion(getVersion());

		for (PropiedadTemplate_VO prop : getPropiedades()) {
			PropiedadTemplate p = prop.toObject();
			p.setTemplate(temp);
			temp.getPropiedades().add(p);
		}

		return temp;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	public String getTamanioPagina() {
		return tamanioPagina;
	}

	public void setTamanioPagina(String tamanioPagina) {
		this.tamanioPagina = tamanioPagina;
	}

	public List<PropiedadTemplate_VO> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<PropiedadTemplate_VO> propiedades_vo) {
		this.propiedades = propiedades_vo;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public boolean tienePropiedad(Propiedad propiedad) {
		for (PropiedadTemplate_VO p : getPropiedades()) {
			if (p.getPropiedad().equals(propiedad)) {
				return true;
			}
		}
		return false;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	public String getTamanioLetra() {
		return tamanioLetra;
	}

	public void setTamanioLetra(String tamanioLetra) {
		this.tamanioLetra = tamanioLetra;
	}

	public String getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public Boolean getCaratula() {
		return caratula;
	}

	public void setCaratula(Boolean caratula) {
		this.caratula = caratula;
	}

	public String getTipoFuentePie() {
		return tipoFuentePie;
	}

	public void setTipoFuentePie(String tipoFuentePie) {
		this.tipoFuentePie = tipoFuentePie;
	}

	public String getTamanioLetraPie() {
		return tamanioLetraPie;
	}

	public void setTamanioLetraPie(String tamanioLetraPie) {
		this.tamanioLetraPie = tamanioLetraPie;
	}

	public Boolean getPieDePagina() {
		return pieDePagina;
	}

	public void setPieDePagina(Boolean pieDePagina) {
		this.pieDePagina = pieDePagina;
	}

	@Override
	public void setObject(Template objeto, int profundidadActual,
			int profundidadDeseada) {
		setObject(objeto);
	}

	@Override
	public String toString() {
		return getNombreServicio();
	}

	@Override
	public boolean equals(Object obj) {
		try {
			return this.getId().equals(((Template_VO) obj).getId());
		} catch (Exception e) {
			return false;
		}
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
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}

	/**
	 * @param encabezado the encabezado to set
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/**
	 * @return the pie
	 */
	public String getPie() {
		return pie;
	}

	/**
	 * @param pie the pie to set
	 */
	public void setPie(String pie) {
		this.pie = pie;
	}
	
}
