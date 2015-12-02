package ar.com.builderadmin.vo;

import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;

public class FuncionHorus_VO implements I_ValueObject<FuncionHorus>{
	
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String nombreMenu;
	private String nombreSubMenu;
	private String nombreAccion;
	private String url;
	private String urlCompleta;
	private String nombreFuncion;
	private String descripcion;
	private String txtAyuda;
	
	public FuncionHorus_VO(){
	}
	
	public FuncionHorus_VO(FuncionHorus fx){
		setObject(fx);
	}
	
	public FuncionHorus_VO(FuncionHorus fx, int i, int j) {
		setObject(fx, i, j);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public String getNombreMenu() {
		return this.nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getNombreSubMenu() {
		return this.nombreSubMenu;
	}

	public void setNombreSubMenu(String nombreSubMenu) {
		this.nombreSubMenu = nombreSubMenu;
	}

	public String getNombreAccion() {
		return this.nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombreFuncion() {
		return this.nombreFuncion;
	}

	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return getNombreMenu() + "+" + getNombreSubMenu() + ">"
				+ getNombreFuncion();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object obj) {
		return ((FuncionHorus_VO) obj).getNombreFuncion().equals(
				getNombreFuncion());
	}

	public String getUrlCompleta() {
		return this.urlCompleta;
	}

	public void setUrlCompleta(String urlCompleta) {
		this.urlCompleta = urlCompleta;
	}
	
	/**
	 * @return the txtAyuda
	 */
	public String getTxtAyuda() {
		return txtAyuda;
	}

	/**
	 * @param txtAyuda the txtAyuda to set
	 */
	public void setTxtAyuda(String txtAyuda) {
		this.txtAyuda = txtAyuda;
	}

	@Override
	public void setObject(FuncionHorus fx) {
		this.setId(fx.getId());
		this.setDescripcion(fx.getDescripcion());
		this.setNombreAccion(fx.getNombreAccion());
		this.setNombreFuncion(fx.getNombreFuncion());
		this.setUrlCompleta(fx.getUrlCompleta());
		this.setNombreMenu(fx.getNombreMenu());
		this.setNombreSubMenu(fx.getNombreSubMenu());
		this.setUrl(fx.getUrl());
		this.setVersion(fx.getVersion());
		this.setTxtAyuda(fx.getTxtAyuda());
	}

	@Override
	public void setObject(FuncionHorus fx, int i, int j) {
		
		this.setId(fx.getId());
		this.setDescripcion(fx.getDescripcion());
		this.setNombreAccion(fx.getNombreAccion());
		this.setNombreFuncion(fx.getNombreFuncion());
		this.setUrlCompleta(fx.getUrlCompleta());
		this.setNombreMenu(fx.getNombreMenu());
		this.setNombreSubMenu(fx.getNombreSubMenu());
		this.setUrl(fx.getUrl());
		this.setVersion(fx.getVersion());
		this.setTxtAyuda(fx.getTxtAyuda());
	}
	
	@Override
	public FuncionHorus toObject() {
		return new FuncionHorus(this.getId(), this.getVersion(), this.getNombreAccion(), this.getNombreFuncion(), 
				this.getUrlCompleta(), this.getNombreMenu(), this.getNombreSubMenu(), this.getUrl(), this.getDescripcion(), this.getTxtAyuda());
	}

	public FuncionHorus toObject(int i, int profDeseado) {
		return toObject();
	}
	
}