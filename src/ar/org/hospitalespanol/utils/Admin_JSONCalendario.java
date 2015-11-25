package ar.org.hospitalespanol.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ar.org.hospitalespanol.utils.json.JSON_Anio;
import ar.org.hospitalespanol.utils.json.JSON_Anio_VA;
import ar.org.hospitalespanol.utils.json.JSON_Dia;
import ar.org.hospitalespanol.utils.json.JSON_Mes;
import ar.org.hospitalespanol.utils.json.JSON_Semana;

public class Admin_JSONCalendario {

	private Calendar calendario;

	public Admin_JSONCalendario() {

		setCalendario(Calendar.getInstance());
	}

	/**
	 * 
	 * @param mes
	 *            Enero 0 - Diciembre 11
	 * @param anio
	 * @return
	 */
	public JSON_Anio semanasParaUnMesDeUnAnio(Integer mes, Integer anio) {
		JSON_Anio c = new JSON_Anio(anio);

		c.setMesActual(mes);

		this.getCalendario().set(Calendar.MONTH, mes);
		this.getCalendario().set(Calendar.DAY_OF_MONTH, 1);
		this.getCalendario().set(Calendar.YEAR, anio);
		this.getCalendario().set(Calendar.HOUR, 0);
		this.getCalendario().set(Calendar.MINUTE, 0);
		this.getCalendario().set(Calendar.SECOND, 0);

		int dia_semana = this.getCalendario().get(Calendar.DAY_OF_WEEK);

		int restar = 0;

		switch (dia_semana) {
		case 1:
			restar = 6;
			break;
		case 2:
			restar = 0;
			break;
		case 3:
			restar = 1;
			break;
		case 4:
			restar = 2;
			break;
		case 5:
			restar = 3;
			break;
		case 6:
			restar = 4;
			break;
		case 7:
			restar = 5;
			break;
		}

		this.getCalendario().get(Calendar.DAY_OF_YEAR);
		this.getCalendario().getTime();

		if (restar != 0)
			this.getCalendario().add(Calendar.DAY_OF_YEAR, -restar);

		JSON_Semana sem_x;
		JSON_Dia dia_x;

		for (int i = 1; i < 7; i++) {
			sem_x = new JSON_Semana(getCalendario().get(Calendar.WEEK_OF_YEAR));
			for (int j = 1; j < 8; j++) {
				dia_x = new JSON_Dia();
				dia_x.setFecha(getCalendario().getTime());
				dia_x.setEsHoy(esHoy(dia_x.getFecha()));
				dia_x.setNumero(getCalendario().get(Calendar.DAY_OF_MONTH));
				dia_x.setNumero_dia_semana(getCalendario().get(
						Calendar.DAY_OF_WEEK));
				sem_x.getDias().add(dia_x);
				getCalendario().add(Calendar.DAY_OF_YEAR, 1);
			}
			c.getSemanas().add(sem_x);
		}

		c.setLabelMes(mes);

		return c;
	}

	public JSON_Anio_VA generarAnio(Integer anio) {

		JSON_Anio_VA json_anio = new JSON_Anio_VA(anio);

		this.getCalendario().set(Calendar.MONTH, 0);
		this.getCalendario().set(Calendar.DAY_OF_MONTH, 1);
		this.getCalendario().set(Calendar.YEAR, anio);
		this.getCalendario().set(Calendar.HOUR_OF_DAY, 0);
		this.getCalendario().set(Calendar.MINUTE, 0);
		this.getCalendario().set(Calendar.SECOND, 0);
		this.getCalendario().set(Calendar.MILLISECOND, 0);

		Integer mesActual = 0;

		JSON_Mes mes;

		JSON_Dia dia;
		while (anio == this.getCalendario().get(Calendar.YEAR)) {

			mes = new JSON_Mes(mesActual);

			while (mesActual == this.getCalendario().get(Calendar.MONTH)) {
				dia = new JSON_Dia();
				dia.setFecha(this.getCalendario().getTime());
				dia.setEsHoy(esHoy(dia.getFecha()));
				dia.setNumero(getCalendario().get(Calendar.DAY_OF_MONTH));
				dia.setNumero_dia_semana(getCalendario().get(
						Calendar.DAY_OF_WEEK));
				mes.getDias().add(dia);
				getCalendario().add(Calendar.DAY_OF_YEAR, 1);
			}

			json_anio.getMeses().add(mes);

			mesActual = this.getCalendario().get(Calendar.MONTH);
		}

		return json_anio;
	}

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	public boolean esHoy(Date fecha) {

		Calendar c1 = Calendar.getInstance();

		Calendar c2 = Calendar.getInstance();

		c2.setTime(fecha);

		return (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}

	public int anioActual() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public int anioDeUnaFecha(Date fecha) {

		Calendar c = Calendar.getInstance();

		c.setTime(fecha);

		return c.get(Calendar.YEAR);
	}

	public int mesDeUnaFecha(Date fecha) {

		Calendar c = Calendar.getInstance();

		c.setTime(fecha);

		return c.get(Calendar.MONTH);
	}

	public int diaDeUnaFecha(Date fecha) {

		Calendar c = Calendar.getInstance();

		c.setTime(fecha);

		return c.get(Calendar.DAY_OF_MONTH);
	}

	public boolean fechasSonIguales(Date fecha1, Date fecha2) {

		Calendar c1 = Calendar.getInstance();

		c1.setTime(fecha1);

		Calendar c2 = Calendar.getInstance();

		c2.setTime(fecha2);

		return (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);

	}

	public int semanaDeUnaFecha(Date fecha) {
		Calendar c = Calendar.getInstance();

		c.setTime(fecha);

		return c.get(Calendar.WEEK_OF_YEAR);

	}

	public Date sumarDuracionAHora(Date hora, int duracion) {
		Calendar c = Calendar.getInstance();
		c.setTime(hora);
		c.add(Calendar.MINUTE, duracion);
		SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
		try {
			return sfd.parse(sfd.format(c.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Calendar getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendar calendario) {
		this.calendario = calendario;
	}

}
