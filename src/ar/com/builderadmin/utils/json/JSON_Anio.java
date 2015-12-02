package ar.com.builderadmin.utils.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.utils.Admin_JSONCalendario;

public class JSON_Anio {

	private List<JSON_Semana> semanas;

	private Integer anio;

	private Integer mesActual;

	private String label;

	public JSON_Anio(int anio) {

		setAnio(anio);

		setSemanas(new ArrayList<JSON_Semana>());

	}

	public void setLabelMes(Integer mes) {
		switch (mes) {
		case 0:
			setLabel("Enero, " + anio);
			break;
		case 1:
			setLabel("Febrero, " + anio);
			break;
		case 2:
			setLabel("Marzo, " + anio);
			break;
		case 3:
			setLabel("Abril, " + anio);
			break;
		case 4:
			setLabel("Mayo, " + anio);
			break;
		case 5:
			setLabel("Junio, " + anio);
			break;
		case 6:
			setLabel("Julio, " + anio);
			break;
		case 7:
			setLabel("Agosto, " + anio);
			break;
		case 8:
			setLabel("Septiembre, " + anio);
			break;
		case 9:
			setLabel("Octubre, " + anio);
			break;
		case 10:
			setLabel("Noviembre, " + anio);
			break;
		case 11:
			setLabel("Diciembre, " + anio);
			break;
		}

	}

	public void cambiarEstado(Date fecha, String estado, String descripcion) {

		Admin_JSONCalendario admin_cal = new Admin_JSONCalendario();

		int anioFecha = admin_cal.anioDeUnaFecha(fecha);

		if (anioFecha == anio) {

			for (JSON_Semana sem : getSemanas()) {
				sem.cambiarEstado(fecha, estado, descripcion);
			}

		}

	}

	public List<JSON_Semana> getSemanas() {
		return semanas;
	}

	public void setSemanas(List<JSON_Semana> semanas) {
		this.semanas = semanas;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMesActual() {
		return mesActual;
	}

	public void setMesActual(Integer mesActual) {
		this.mesActual = mesActual;
	}

}
