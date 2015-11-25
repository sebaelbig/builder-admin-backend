package ar.org.hospitalespanol.utils.comparators.core.templates;

import java.util.Comparator;

import ar.org.hospitalespanol.vo.core.templates.PropiedadTemplate_VO;

public class PropiedadTemplateVOComparator implements
		Comparator<PropiedadTemplate_VO> {

	@Override
	public int compare(PropiedadTemplate_VO p1, PropiedadTemplate_VO p2) {

		return p1.getPropiedad().getNombre()
				.compareTo(p2.getPropiedad().getNombre());
	}

}
