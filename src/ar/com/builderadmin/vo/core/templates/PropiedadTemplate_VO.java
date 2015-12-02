package ar.com.builderadmin.vo.core.templates;

import ar.com.builderadmin.model.core.templates.Propiedad;
import ar.com.builderadmin.model.core.templates.PropiedadTemplate;
import ar.com.builderadmin.model.core.templates.Template;
import ar.com.builderadmin.vo.I_ValueObject;

public class PropiedadTemplate_VO implements I_ValueObject<PropiedadTemplate> {

	private Long idTemplate;
	private Long id;
	private Boolean borrado = false;

	private Integer version;

	private Propiedad propiedad;

	private String contenido;

	private Boolean seleccionada = false;

	private Integer orden;
	private Integer ancho = 100;
	private String margen = "left";

	public PropiedadTemplate_VO() {

	}

	public PropiedadTemplate_VO(PropiedadTemplate prop) {
		setObject(prop);
	}

	public PropiedadTemplate_VO(Propiedad propiedad) {
		setPropiedad(propiedad);
	}

	@Override
	public void setObject(PropiedadTemplate objeto) {
		setContenido(objeto.getContenido());
		setId(objeto.getId());
		setPropiedad(objeto.getPropiedad());
		setMargen(objeto.getMargen());
		setAncho(objeto.getAncho());
		setOrden(objeto.getOrden());
		setVersion(objeto.getVersion());
		setBorrado(objeto.getBorrado());
		
		if (objeto.getTemplate()!=null)
			setIdTemplate(objeto.getTemplate().getId());
	}

	@Override
	public PropiedadTemplate toObject() {
		PropiedadTemplate resul = new PropiedadTemplate();

		resul.setContenido(this.getContenido());
		resul.setId(this.getId());
		resul.setPropiedad(this.getPropiedad());
		resul.setMargen(this.getMargen());
		resul.setAncho(this.getAncho());
		resul.setOrden(this.getOrden());
		resul.setVersion(this.getVersion());
		resul.setBorrado(this.getBorrado());

		if (this.getIdTemplate()!=null){
			Template t = new Template();
			t.setId(getIdTemplate());
			resul.setTemplate(t);
		}
			
		return resul;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Boolean getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(Boolean seleccionada) {
		this.seleccionada = seleccionada;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	@Override
	public void setObject(PropiedadTemplate objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the ancho
	 */
	public Integer getAncho() {
		return ancho;
	}

	/**
	 * @param ancho
	 *            the ancho to set
	 */
	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the margen
	 */
	public String getMargen() {
		return margen;
	}

	/**
	 * @param margen
	 *            the margen to set
	 */
	public void setMargen(String margen) {
		this.margen = margen;
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			return this.getId().equals(((PropiedadTemplate_VO) obj).getId());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @return the idTemplate
	 */
	public Long getIdTemplate() {
		return idTemplate;
	}

	/**
	 * @param idTemplate the idTemplate to set
	 */
	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}
	
	
}
