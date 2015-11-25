package ar.org.hospitalespanol.utils.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.utils.Admin_JSONCalendario;

public class JSON_Anio_VA {

	private Integer anio;

	private List<JSON_Mes> meses;

	public JSON_Anio_VA(Integer anio) {
		setMeses(new ArrayList<JSON_Mes>());
		setAnio(anio);
	}

	public void agregarItem(List objetos, Date fecha, String estado) {

		Admin_JSONCalendario admin_cal = new Admin_JSONCalendario();

		int anioFecha = admin_cal.anioDeUnaFecha(fecha);

		if (anioFecha == anio) {

			int mes = admin_cal.mesDeUnaFecha(fecha);

			this.getMeses().get(mes).agregarItem(objetos, fecha, estado);

		}

	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public List<JSON_Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<JSON_Mes> meses) {
		this.meses = meses;
	}

}
