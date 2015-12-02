package ar.com.builderadmin.controllers.menu;

import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;
import ar.com.builderadmin.vo.FuncionHorus_VO;

public class HorusAccion {

	private String label;

	private String url;

	private Long id;

	private String nombre;

	private String descripcion;

	private String txtAyuda;

	public HorusAccion(String label, String url, Long id, String nombreAccion,
			String txtHelp) {
		this.setUrl(url);
		this.setId(id);
		this.setLabel(label);
		this.setNombre(nombreAccion);
		this.setTxtAyuda(txtHelp);
	}

	public HorusAccion(FuncionHorus fx) {
		this.setUrl(fx.getUrl());
		this.setId(fx.getId());
		this.setLabel(fx.getNombreAccion());
		this.setNombre(fx.getNombreFuncion());
		this.setDescripcion(fx.getDescripcion());
		this.setTxtAyuda(fx.getTxtAyuda());
	}

	public HorusAccion(FuncionHorus_VO fx) {
		this.setUrl(fx.getUrl());
		this.setId(fx.getId());
		this.setLabel(fx.getNombreAccion());
		this.setNombre(fx.getNombreFuncion());
		this.setDescripcion(fx.getDescripcion());
		this.setTxtAyuda(fx.getTxtAyuda());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object objeto) {
		return ((HorusAccion) objeto).getNombre().equalsIgnoreCase(
				this.getNombre());
	}

	public String getTxtAyuda() {
		return txtAyuda;
	}

	public void setTxtAyuda(String txtAyuda) {
		this.txtAyuda = txtAyuda;
	}
}
