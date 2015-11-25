package ar.org.hospitalespanol.utils.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.utils.Admin_JSONCalendario;

@SuppressWarnings("unchecked")
public class JSON_Dia {

	public static final String NO_LABORABLE = "NO LABORABLE";

	public static final String LABORABLE = "LABORABLE";

	public static final String TURNO_LIBRE = "TURNO LIBRE";

	public static final String BLOQUE_TURNO_OCUPADO = "BLOQUE TURNO OCUPADO";

	private Date fecha;
	private String str_fecha;

	private Integer numero;

	private Integer numero_dia_semana;

	private boolean esHoy;

	private String descripcion;

	private List items;

	private String estado;

	public JSON_Dia() {
		setItems(new ArrayList());
		setEstado(JSON_Dia.LABORABLE);
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public boolean isEsHoy() {
		return esHoy;
	}

	public void setEsHoy(boolean esHoy) {
		this.esHoy = esHoy;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fechaDia) {
		this.fecha = fechaDia;
		if (fechaDia != null) {
			this.setStr_fecha(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaDia));
		}
	}

	public Integer getNumero_dia_semana() {
		return numero_dia_semana;
	}

	public void setNumero_dia_semana(Integer numero_dia_semana) {

		switch (numero_dia_semana) {
		case 1:
			this.numero_dia_semana = 7;
			break;
		default:
			this.numero_dia_semana = numero_dia_semana - 1;
			break;
		}

	}

	public void agregarItem(List objetos, Date fecha2, String estado2) {

		Admin_JSONCalendario admin_cal = new Admin_JSONCalendario();

		if (admin_cal.fechasSonIguales(this.getFecha(), fecha2)) {

			this.setItems(objetos);

			setEstado(estado2);

		}

	}

	public void cambiarEstado(Date fecha2, String estado2, String descripcion2) {
		Admin_JSONCalendario admin_cal = new Admin_JSONCalendario();

		if (admin_cal.fechasSonIguales(this.getFecha(), fecha2)) {

			setEstado(estado2);
			setDescripcion(descripcion2);
		}

	}

	public String getStr_fecha() {
		return str_fecha;
	}

	public void setStr_fecha(String str_fecha) {
		this.str_fecha = str_fecha;
	}

}
