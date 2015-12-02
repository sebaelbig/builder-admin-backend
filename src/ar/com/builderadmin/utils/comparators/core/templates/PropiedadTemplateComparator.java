package ar.com.builderadmin.utils.comparators.core.templates;

import java.util.Comparator;

import ar.com.builderadmin.model.core.templates.PropiedadTemplate;

public class PropiedadTemplateComparator implements
		Comparator<PropiedadTemplate> {

	@Override
	public int compare(PropiedadTemplate p1, PropiedadTemplate p2) {

		return p1.getPropiedad().getNombre()
				.compareTo(p2.getPropiedad().getNombre());
	}

}
