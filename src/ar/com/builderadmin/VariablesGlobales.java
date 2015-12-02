package ar.com.builderadmin;

import java.util.Calendar;

public class VariablesGlobales {

	public static final Integer NRO_LUNES = 1;
	public static final Integer NRO_MARTES = 2;
	public static final Integer NRO_MIERCOLES = 3;
	public static final Integer NRO_JUEVES = 4;
	public static final Integer NRO_VIERNES = 5;
	public static final Integer NRO_SABADO = 6;
	public static final Integer NRO_DOMINGO = 7;

	public static int diaHorusToDiaCalendario(int diaHorus){
		int resul = 0;
		
		switch (diaHorus){
			case 1: resul = Calendar.MONDAY;break;
			case 2: resul = Calendar.TUESDAY;break;
			case 3: resul = Calendar.WEDNESDAY;break;
			case 4: resul = Calendar.THURSDAY;break;
			case 5: resul = Calendar.FRIDAY;break;
			case 6: resul = Calendar.SATURDAY;break;
			case 7: resul = Calendar.SUNDAY;break;
		}
		
		return resul;
	}
	
	
	public static int offsetEntreDiasDeSemana(int dia_desde, int dia_hasta){
		if (dia_desde <= dia_hasta){
			return dia_hasta - dia_desde;
		}else{
			return dia_hasta + (7 - dia_desde);
		}
		
	}
	
	
	
}
