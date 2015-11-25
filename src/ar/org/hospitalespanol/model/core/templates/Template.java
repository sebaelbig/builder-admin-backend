package ar.org.hospitalespanol.model.core.templates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.vo.core.templates.Template_VO;

@Entity
@Table( name = "template")
public class Template implements Serializable, I_Entidad {

	/**
	 * 
	 * El template tendrá por defecto un encabezado, el cual contiene: Logo -
	 * Nombre de la Institición - Nombre del Servicio
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/* Tamños de pagina */

	public static final String A5 = "A5";

	public static final String A4 = "A4";

	public static final String LEGAL = "LEGAL";

	public static final String LETTER = "LETTER";

	/* orientacion de la paigna */

	public static final String HORIZONTAL = "Horizontal";

	public static final String VERTICAL = "Vertical";

	/* Tipos de fuente */

	public static final String FONT_COURIER = "Courier";

	public static final String FONT_HELVETICA = "Helvetica";

	public static final String FONT_TIMES_ROMAN = "Times New Roman";

	/* Tamño de la fuente */

	public static final String FONT_SIZE_XX_PEQUENIO = "xx-pequeño";

	public static final String FONT_SIZE_X_PEQUENIO = "x-pequeño";

	public static final String FONT_SIZE_PEQUENIO = "pequeño";

	public static final String FONT_SIZE_MEDIO = "medio";

	public static final String FONT_SIZE_GRANDE = "grande";

	public static final String FONT_SIZE_X_GRANDE = "x-grande";

	public static final String FONT_SIZE_XX_GRANDE = "xx-grande";

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_template", name = "seq_template", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_template")
	private Long id;

	@Version
	private Integer version;

	@OneToOne
	@JoinColumn(name = "id_servicio")
	private Servicio servicio;

	@Column(name = "tamanio_pagina")
	private String tamanioPagina;

	private String orientacion;

	@Column(name = "tamanio_letra")
	private String tamanioLetra;

	@Column(name = "tipo_fuente")
	private String tipoFuente;

	private Boolean caratula;

	@Column(name = "pie_de_pagina")
	private Boolean pieDePagina;

	@Column(name = "pie")
	private String pie;
	
	private String encabezado;

	@Column(name = "tipo_fuente_pie")
	private String tipoFuentePie;

	@Column(name = "tamanio_letra_pie")
	private Integer tamanioLetraPie;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "template")
	private List<PropiedadTemplate> propiedades;

	public Template() {
		setPropiedades(new ArrayList<PropiedadTemplate>());
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getTamanioPagina() {
		return tamanioPagina;
	}

	public void setTamanioPagina(String tamanioPagina) {
		this.tamanioPagina = tamanioPagina;
	}

	public List<PropiedadTemplate> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<PropiedadTemplate> propiedades) {
		this.propiedades = propiedades;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Template) {
			Template o = (Template) objeto;
			return (o.getId().equals(this.getId()));
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

	public Integer getTamanioLetraPie() {
		return tamanioLetraPie;
	}

	public void setTamanioLetraPie(Integer tamanioLetraPie) {
		this.tamanioLetraPie = tamanioLetraPie;
	}

	public Boolean getPieDePagina() {
		return pieDePagina;
	}

	public void setPieDePagina(Boolean pieDePagina) {
		this.pieDePagina = pieDePagina;
	}

	public Template_VO toValueObject() {
		return new Template_VO(this);
	}

	/**
	 * @return the borrado
	 */
	@Override
	public Boolean getBorrado() {
		return borrado;
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
	 * @param borrado the borrado to set
	 */
	@Override
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	
}
