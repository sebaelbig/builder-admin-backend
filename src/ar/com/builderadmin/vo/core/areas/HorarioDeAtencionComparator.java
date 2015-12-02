package ar.com.builderadmin.vo.core.areas;

import java.util.Comparator;

public class HorarioDeAtencionComparator implements
		Comparator<HorarioDeAtencion_VO> {
	@Override
	public int compare(HorarioDeAtencion_VO h1, HorarioDeAtencion_VO h2) {
		int resul = 0;

		resul = h1.getDiaDeAtencion().getNumero()
				.compareTo(h2.getDiaDeAtencion().getNumero());
		if (resul == 0) {
			resul = h1.getHorarioInicio().compareTo(h2.getHorarioInicio());
			if (resul == 0) {
				resul = h1.getHorarioFin().compareTo(h2.getHorarioFin());
			}
		}
		return resul;
	}
}