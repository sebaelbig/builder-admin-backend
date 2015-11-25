package ar.org.hospitalespanol.utils.comparators.core.areas;

import java.util.Comparator;

import ar.org.hospitalespanol.model.core.areas.DiaDeAtencion;

public class DiaDeAtencionComparator implements Comparator<DiaDeAtencion>{

	public int compare(DiaDeAtencion d1, DiaDeAtencion d2) {
		
		return d1.getNumero().compareTo(d2.getNumero());
	}

}
