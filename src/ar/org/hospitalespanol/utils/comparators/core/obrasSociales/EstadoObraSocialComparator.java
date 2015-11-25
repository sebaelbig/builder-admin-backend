package ar.org.hospitalespanol.utils.comparators.core.obrasSociales;

import java.util.Comparator;

import ar.org.hospitalespanol.vo.core.obrasSociales.EstadoObraSocial_VO;

public class EstadoObraSocialComparator implements
		Comparator<EstadoObraSocial_VO> {

	public int compare(EstadoObraSocial_VO o1, EstadoObraSocial_VO o2) {

		return o1.getFecha().compareTo(o2.getFecha());
	}

}
