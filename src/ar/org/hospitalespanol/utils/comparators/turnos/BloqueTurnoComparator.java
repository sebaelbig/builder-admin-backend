package ar.org.hospitalespanol.utils.comparators.turnos;

import java.util.Comparator;

import ar.org.hospitalespanol.vo.turnos.BloqueTurno_VO;

public class BloqueTurnoComparator implements Comparator<BloqueTurno_VO> {
	
	@Override
	public int compare(BloqueTurno_VO bt1, BloqueTurno_VO bt2) {
		int resul = 0;
		
		if (bt1.getDiaProximoTurno() == null) {
			if (bt2.getDiaProximoTurno() == null) {
				resul = 0;
			} else {
				resul = -1;
			}
			
		} else if (bt2.getDiaProximoTurno() == null) {
			resul = 1;
			
		} else {
			resul = bt1.getDiaProximoTurno().compareTo(
					bt2.getDiaProximoTurno());
			
			//Si los dias son iguales, entonces comparo por las horas
			if (resul == 0) {
				resul = bt1.getHoraInicio().compareTo(
						bt2.getHoraInicio());
			}
			
		}
		
		return resul;
	}
	
}