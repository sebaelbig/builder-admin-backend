package ar.com.builderadmin.controllers.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.com.builderadmin.vo.FuncionHorus_VO;

public class HorusSubMenuItem {

	private List<HorusAccion> acciones;

	private String label;

	public HorusSubMenuItem(String label) {
		this.setLabel(label);
		this.setAcciones(new ArrayList<HorusAccion>());
	};

	public void agregarAccion(FuncionHorus_VO fx) {
		HorusAccion ha = new HorusAccion(fx);
		int index_smi = this.getAcciones().indexOf(ha);

		if (index_smi == -1) {
			// Si ya existe la accion, no la agrego
			this.getAcciones().add(ha);
		}
		
		Collections.sort(this.getAcciones(),new HorusAccionesMenuComparator());

	}
	
	private class HorusAccionesMenuComparator implements Comparator<HorusAccion> {
		
		@Override
		public int compare(HorusAccion mp1, HorusAccion mp2) {
			return mp1.getLabel().compareToIgnoreCase(mp2.getLabel()) ;
		}
		
	}
	
	@Override
	public boolean equals(Object objeto) {
	     return ((HorusSubMenuItem)objeto).getLabel().equalsIgnoreCase(this.getLabel());
	}
	
	public List<HorusAccion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<HorusAccion> acciones) {
		this.acciones = acciones;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
