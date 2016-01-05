package ar.com.builderadmin.model.core.usuarios.funciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.FuncionHorus_VO;

@Entity
@Table( name = "funcion_horus")
public class FuncionHorus {

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_funcion_horus", name = "seq_funcion_horus", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcion_horus")
	private Long id;

	@Version
	private Integer version;

	@Column(name = "nombre_menu", length = 50)
	private String nombreMenu;

	@Column(name = "nombre_sub_menu", length = 50)
	private String nombreSubMenu;

	@Column(name = "url_completa", length = 150)
	private String urlCompleta;

	@Column(name = "nombre_accion")
	private String nombreAccion;

	private String url;

	@Column(name = "nombre_funcion")
	private String nombreFuncion;

	private String descripcion;

	@Column(name = "txt_ayuda")
	private String txtAyuda;

	public FuncionHorus() {
	}

	public FuncionHorus(Long id2, Integer version2, String nombreAccion2,
			String nombreFuncion2, String urlCompleta2, String nombreMenu2,
			String nombreSubMenu2, String url2, String descripcion2,
			String txtHelp) {
		this.setId(id2);
		this.setVersion(version2);
		this.setNombreAccion(nombreAccion2);
		this.setNombreFuncion(nombreFuncion2);
		this.setUrlCompleta(urlCompleta2);
		this.setNombreMenu(nombreMenu2);
		this.setNombreSubMenu(nombreSubMenu2);
		this.setUrl(url2);
		this.setDescripcion(descripcion2);
		this.setTxtAyuda(txtHelp);
	}

	public FuncionHorus(FuncionHorus_VO fx, int i, int profundidadDeseada) {
		this.setId(fx.getId());
		this.setVersion(fx.getVersion());
		this.setNombreAccion(fx.getNombreAccion());
		this.setNombreFuncion(fx.getNombreFuncion());
		this.setUrlCompleta(fx.getUrlCompleta());
		this.setNombreMenu(fx.getNombreMenu());
		this.setNombreSubMenu(fx.getNombreSubMenu());
		this.setUrl(fx.getUrl());
		this.setDescripcion(fx.getDescripcion());
		this.setTxtAyuda(fx.getTxtAyuda());
	}

	public FuncionHorus(FuncionHorus_VO fx) {
		this(fx, 0, 0);
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getNombreSubMenu() {
		return nombreSubMenu;
	}

	public void setNombreSubMenu(String nombreSubMenu) {
		this.nombreSubMenu = nombreSubMenu;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombreFuncion() {
		return nombreFuncion;
	}

	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public FuncionHorus_VO toValueObject() {
		return new FuncionHorus_VO(this);
	}

	public FuncionHorus_VO toValueObject(int i, int j) {
		return new FuncionHorus_VO(this, i, j);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "FX: " + this.getNombreMenu() + " + " + this.getNombreSubMenu()
				+ " -> " + this.getNombreAccion();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof FuncionHorus) {
			FuncionHorus o = (FuncionHorus) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public String getUrlCompleta() {
		return this.urlCompleta;
	}

	public void setUrlCompleta(String urlCompleta) {
		this.urlCompleta = urlCompleta;
	}

	public String getTxtAyuda() {
		return txtAyuda;
	}

	public void setTxtAyuda(String txtAyuda) {
		this.txtAyuda = txtAyuda;
	}

	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}
