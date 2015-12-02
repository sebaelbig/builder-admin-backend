package ar.com.builderadmin.utils.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.utils.Admin_JSONCalendario;

public class JSON_Mes {

	private Integer mes;

	private String nombre;

	private List<JSON_Dia> dias;

	public JSON_Mes(Integer mes) {
		setDias(new ArrayList<JSON_Dia>());
		setMes(mes);
		switch (mes) {
		case 0:
			setNombre("Enero");
			break;
		case 1:
			setNombre("Febrero");
			break;
		case 2:
			setNombre("Marzo");
			break;
		case 3:
			setNombre("Abril");
			break;
		case 4:
			setNombre("Mayo");
			break;
		case 5:
			setNombre("Junio");
			break;
		case 6:
			setNombre("Julio");
			break;
		case 7:
			setNombre("Agosto");
			break;
		case 8:
			setNombre("Septiembre");
			break;
		case 9:
			setNombre("Octubre");
			break;
		case 10:
			setNombre("Noviembre");
			break;
		case 11:
			setNombre("Diciembre");
			break;
		}
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<JSON_Dia> getDias() {
		return dias;
	}

	public void setDias(List<JSON_Dia> dias) {
		this.dias = dias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarItem(List objetos, Date fecha, String estado) {

		Admin_JSONCalendario admin_cal = new Admin_JSONCalendario();

		int mesFecha = admin_cal.mesDeUnaFecha(fecha);

		if (mesFecha == this.getMes()) {

			int dia = admin_cal.diaDeUnaFecha(fecha);

			getDias().get(dia - 1).agregarItem(objetos, fecha, estado);
		}

	}

}
