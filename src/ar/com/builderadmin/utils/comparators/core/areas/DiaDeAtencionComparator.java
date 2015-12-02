package ar.com.builderadmin.utils.comparators.core.areas;

import java.util.Comparator;

import ar.com.builderadmin.model.core.areas.DiaDeAtencion;

public class DiaDeAtencionComparator implements Comparator<DiaDeAtencion>{

	public int compare(DiaDeAtencion d1, DiaDeAtencion d2) {
		
		return d1.getNumero().compareTo(d2.getNumero());
	}

}
