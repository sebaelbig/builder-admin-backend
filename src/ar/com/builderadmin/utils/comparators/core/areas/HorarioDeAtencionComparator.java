package ar.com.builderadmin.utils.comparators.core.areas;

import java.util.Comparator;

import ar.com.builderadmin.vo.core.areas.HorarioDeAtencion_VO;

public class HorarioDeAtencionComparator implements
		Comparator<HorarioDeAtencion_VO> {

	@Override
	public int compare(HorarioDeAtencion_VO h1, HorarioDeAtencion_VO h2) {

		int resul = 0;

		resul = h1.getDiaDeAtencion().getNumero().compareTo(h2.getDiaDeAtencion().getNumero());
		if (resul == 0) {
			// Si estamos en el mismo dia, nos fijamos la hora

			resul = h1.getHorarioInicio().compareTo(h2.getHorarioInicio());
			if (resul == 0) {
				// Si los dos empiezan el mismo dia, a la misma hora
				resul = h1.getHorarioFin().compareTo(h2.getHorarioFin());
			}

		}

		return resul;
	}

}
