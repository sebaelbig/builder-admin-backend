package ar.com.builderadmin.controllers.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.com.builderadmin.vo.FuncionHorus_VO;

public class HorusMenuItem {

	private List<HorusSubMenuItem> subMenuItems;

	private String label;

	private Long idFx;
	
	public HorusMenuItem(FuncionHorus_VO fun) {
		this.setLabel(fun.getNombreMenu());
		this.setIdFx(fun.getId());
		this.setSubMenuItems(new ArrayList<HorusSubMenuItem>());
	}

	public void agregarSubMenu(FuncionHorus_VO funcion) {
		HorusSubMenuItem smi = new HorusSubMenuItem(funcion.getNombreSubMenu());
		int index_smi = this.getSubMenuItems().indexOf(smi);

		if (index_smi >= 0) {
			// Si ya existe el submenuitem, le cargo la accion
			smi = this.getSubMenuItems().get(index_smi);
			
		} else {
			// Si no esta el submenuitem en la coleccion de submenues lo creo y lo agrego
			this.getSubMenuItems().add(smi);
		}
		
		smi.agregarAccion(funcion);
		
		Collections.sort(this.getSubMenuItems(),new HorusMenuSubItemComparator());
	}
	
	private class HorusMenuSubItemComparator implements Comparator<HorusSubMenuItem> {
		
		@Override
		public int compare(HorusSubMenuItem mp1, HorusSubMenuItem mp2) {
			return mp1.getLabel().compareToIgnoreCase(mp2.getLabel()) ;
		}
		
	}
	
	@Override
	public boolean equals(Object objeto) {
	     return ((HorusMenuItem)objeto).getLabel().equalsIgnoreCase(this.getLabel());
	}
	
	public List<HorusSubMenuItem> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<HorusSubMenuItem> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getIdFx() {
		return idFx;
	}

	public void setIdFx(Long idFx) {
		this.idFx = idFx;
	}
	
	
}
