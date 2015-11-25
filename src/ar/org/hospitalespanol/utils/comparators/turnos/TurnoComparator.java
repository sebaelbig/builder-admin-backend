package ar.org.hospitalespanol.utils.comparators.turnos;

import java.util.Comparator;

import ar.org.hospitalespanol.vo.turnos.Turno_VO;

public class TurnoComparator implements Comparator<Turno_VO> {

	@Override
	public int compare(Turno_VO t1, Turno_VO t2) {

		int resul = 0;

		resul = t1.getFecha().getFecha().compareTo(t2.getFecha().getFecha());

		// Si las fechas del turno son iguales, chequeo la hora
		if (resul == 0) {
			resul = t1.getFecha().getHora().compareTo(t2.getFecha().getHora());
		}

		return resul;
	}

}
