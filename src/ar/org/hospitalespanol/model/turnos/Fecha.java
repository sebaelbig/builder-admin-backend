package ar.org.hospitalespanol.model.turnos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.org.hospitalespanol.vo.turnos.Fecha_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity @Table
public class Fecha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_fecha", name = "seq_fecha", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fecha")
	private Long id;

	@Version
	private Integer version;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.TIME)
	private Date hora;

	public Fecha() {

	}

	public Fecha(Date fecha) {
		setFecha(fecha);
		setHora(fecha);
	}

	public Fecha(Date fecha, Date hora) {
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
		this.setHora(fecha);
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
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

	public Fecha_VO toValueObject() {
		return new Fecha_VO(this);
	}

}