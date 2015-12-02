package ar.com.builderadmin.controllers.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.com.builderadmin.vo.core.areas.Sucursal_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class HorusMenuRol {

	private List<HorusMenuPerfil> menuPerfiles;

	private Rol_VO rol;

	private String label;

	private Long idRol;

	public HorusMenuRol(Rol_VO r) {

		this.setLabel(r.getNombre());
		this.setIdRol(r.getId());

		this.setMenuPerfiles(new ArrayList<HorusMenuPerfil>());

		this.setRol(r);

	}

	/**
	 * Daada una sucursal, arma el menu
	 * 
	 * @param sucursalMenu
	 */
	public void armarMenu(Sucursal_VO sucursalMenu) {

//		for (Perfil_VO p : this.getRol().getPerfiles()) {

			// Si viene una sucursal valida o el perfil se aplica en alguna de
			// sus sucursales, se agrega
//			if (sucursalMenu != null) {
//
//				if (p.tieneSucursal(sucursalMenu)) {
//					this.agregarPerfil(p);
//				}
//
//			}

//		}

		Collections.sort(this.getMenuPerfiles(),
				new HorusMenuPerfilComparator());
	}

	public void agregarPerfil(Perfil_VO p) {

		HorusMenuPerfil mp = new HorusMenuPerfil(p);
		int index_mp = this.getMenuPerfiles().indexOf(mp);

		if (index_mp < 0) {

			// Si no esta el menuPerfil en la coleccion de menues lo agrego
			this.getMenuPerfiles().add(mp);

		}

		Collections.sort(getMenuPerfiles(), new HorusMenuPerfilComparator());

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean equals(Object objeto) {
		return ((HorusMenuRol) objeto).getLabel().equalsIgnoreCase(
				this.getLabel());
	}

	public Rol_VO getRol() {
		return rol;
	}

	public void setRol(Rol_VO rol) {
		this.rol = rol;
	}

	public List<HorusMenuPerfil> getMenuPerfiles() {
		return menuPerfiles;
	}

	public void setMenuPerfiles(List<HorusMenuPerfil> menuPerfiles) {
		this.menuPerfiles = menuPerfiles;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long id) {
		this.idRol = id;
	}

	private class HorusMenuPerfilComparator implements
			Comparator<HorusMenuPerfil> {

		@Override
		public int compare(HorusMenuPerfil mp1, HorusMenuPerfil mp2) {
			return mp1.getLabel().compareToIgnoreCase(mp2.getLabel());
		}

	}

}
