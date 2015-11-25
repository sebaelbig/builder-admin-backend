package ar.org.hospitalespanol.controllers.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.org.hospitalespanol.vo.FuncionHorus_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class HorusMenuPerfil {

	private List<HorusMenuItem> menuItems;

	private String label;

	private Long idPerfil;

	private Long idServicio;
	private String nombreServicio;

	public HorusMenuPerfil() {
		this.setMenuItems(new ArrayList<HorusMenuItem>());
	}

	public HorusMenuPerfil(Perfil_VO p) {

		this.setLabel(p.getNombre());
		this.setIdPerfil(p.getId());
		
		this.setIdServicio(p.getIdServicio());
		this.setNombreServicio(p.getNombreServicio());

		this.setMenuItems(new ArrayList<HorusMenuItem>());

		for (FuncionHorus_VO fx : p.getFunciones()) {

			this.agregarFX(fx);

		}

		Collections.sort(this.getMenuItems(), new HorusMenuItemComparator());

	}

	private class HorusMenuItemComparator implements Comparator<HorusMenuItem> {

		@Override
		public int compare(HorusMenuItem mp1, HorusMenuItem mp2) {
			return mp1.getLabel().compareToIgnoreCase(mp2.getLabel());
		}

	}

	public void agregarFX(FuncionHorus_VO funcion) {

		HorusMenuItem mi = new HorusMenuItem(funcion);
		int index_mi = this.getMenuItems().indexOf(mi);

		if (index_mi >= 0) {

			// Si ya existe el menuitem, le cargo el submenu
			mi = this.getMenuItems().get(index_mi);

		} else {
			// Si no esta el menuitem en la coleccion de menues lo creo y lo
			// agrego
			this.getMenuItems().add(mi);

		}

		mi.agregarSubMenu(funcion);
		this.agregarFuncion(funcion);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean equals(Object objeto) {
		return ((HorusMenuPerfil) objeto).getLabel().equalsIgnoreCase(
				this.getLabel());
	}

	public List<HorusMenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<HorusMenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public void limpiar() {
		this.getMenuItems().clear();
	}

	List<String> funciones = new ArrayList<String>();

	public Boolean tieneFuncion(String nombreFuncion) {
		return this.getFunciones().contains(nombreFuncion);
	}

	public void agregarFuncion(FuncionHorus_VO funcion) {
		this.getFunciones().add(funcion.getNombreFuncion());
	}

	public List<String> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<String> funciones) {
		this.funciones = funciones;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
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

	private class HorusMenuPerfilComparator implements
			Comparator<HorusMenuPerfil> {

		@Override
		public int compare(HorusMenuPerfil mp1, HorusMenuPerfil mp2) {
			return mp1.getLabel().compareToIgnoreCase(mp2.getLabel());
		}

	}
}
