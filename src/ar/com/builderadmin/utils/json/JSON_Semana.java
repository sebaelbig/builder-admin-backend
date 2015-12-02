package ar.com.builderadmin.utils.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.utils.Admin_JSONCalendario;

public class JSON_Semana {

	private Integer numero;

	private List<JSON_Dia> dias;

	public JSON_Semana(int i) {
		setNumero(i);
		setDias(new ArrayList<JSON_Dia>());
	}

	public List<JSON_Dia> getDias() {
		return dias;
	}

	public void setDias(List<JSON_Dia> dias) {
		this.dias = dias;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void cambiarEstado(Date fecha, String estado, String descripcion) {
		Admin_JSONCalendario admin_cal = new Admin_JSONCalendario();

		int semana = admin_cal.semanaDeUnaFecha(fecha);

		if (semana == this.getNumero()) {

			for (JSON_Dia jd : getDias()) {
				jd.cambiarEstado(fecha, estado, descripcion);
			}
		}
	}

}
