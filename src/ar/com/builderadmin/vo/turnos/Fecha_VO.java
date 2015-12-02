package ar.com.builderadmin.vo.turnos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ar.com.builderadmin.model.turnos.Fecha;
import ar.com.builderadmin.vo.I_ValueObject;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class Fecha_VO implements Serializable, I_ValueObject<Fecha> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private Date fecha;
	private String str_fecha;

	private Date hora;
	private String str_hora;

	public Fecha_VO() {
	}

	public Fecha_VO(Fecha fecha) {
		setObject(fecha);
	}

	@Override
	public void setObject(Fecha fecha2) {
		this.setId(fecha2.getId());
		this.setVersion(fecha2.getVersion());

		this.setFecha(fecha2.getFecha());
		this.setHora(fecha2.getHora());
	}

	public Fecha_VO(Date fecha) {
		setFecha(fecha);
		setHora(fecha);
	}

	public Fecha_VO(Date fecha, Date hora) {
		setFecha(fecha);
		setHora(hora);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Fecha) {
			Fecha o = (Fecha) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		if (fecha != null) {
			this.setStr_fecha(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		}
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
		if (hora != null) {
			this.setStr_hora(new SimpleDateFormat("HH:mm").format(hora));
		}
	}

	public boolean mismoDia(Fecha fechaActual) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getFecha());

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fechaActual.getFecha());

		Integer dia = cal.get(Calendar.DAY_OF_MONTH);
		Integer mes = cal.get(Calendar.MONTH);
		Integer anio = cal.get(Calendar.YEAR);

		return dia.equals(cal2.get(Calendar.DAY_OF_MONTH))
				&& mes.equals(cal2.get(Calendar.MONTH))
				&& anio.equals(cal2.get(Calendar.YEAR));
	}

	@Override
	public String toString() {
		DateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy"), formatoHora = new SimpleDateFormat(
				"HH:mm");

		return formatoDia.format(getFecha()) + " - "
				+ formatoHora.format(getHora());
	}

	@Override
	public void setObject(Fecha objeto, int profundidadActual,
			int profundidadDeseada) {

		this.setObject(objeto);

	}

	@Override
	public Fecha toObject() {
		Fecha resul = new Fecha();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setFecha(this.getFecha());
		resul.setHora(this.getHora());

		return resul;
	}

	public String getStr_fecha() {
		return str_fecha;
	}

	public void setStr_fecha(String str_fecha) {
		this.str_fecha = str_fecha;
	}

	public String getStr_hora() {
		return str_hora;
	}

	public void setStr_hora(String str_hora) {
		this.str_hora = str_hora;
	}

}