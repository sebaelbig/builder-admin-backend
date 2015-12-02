package ar.com.builderadmin.utils.comparators.turnos;

import java.util.Comparator;

import ar.com.builderadmin.vo.turnos.ProfesionalBloqueTurnoPrimerTurno_VO;

public class ProfesionalBloqueTurnoPrimerTurnoComparator implements
		Comparator<ProfesionalBloqueTurnoPrimerTurno_VO> {
	@Override
	public int compare(ProfesionalBloqueTurnoPrimerTurno_VO bt1,
			ProfesionalBloqueTurnoPrimerTurno_VO bt2) {

		int resul = 0;

		resul = bt1.getFechaTurno().compareTo(bt2.getFechaTurno());

		// Si las fechas del turno son iguales, chequeo la hora
		if (resul == 0) {
			resul = bt1.getHoraTurno().compareTo(bt2.getHoraTurno());
		}

		return resul;
	}

}
