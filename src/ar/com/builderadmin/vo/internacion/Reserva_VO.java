package ar.com.builderadmin.vo.internacion;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.builderadmin.model.internacion.Reserva;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.internacion.habitaciones.camas.AgendaCama_VO;

public class Reserva_VO implements Serializable, I_ValueObject<Reserva> {

	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private Date fechaAsignacion;
	private String str_fechaAsignacion;

	private Date fechaInicio;
	private Date fechaFin;
	private String str_fechaInicio;
	private String str_fechaFin;

	private Date horaInicio;
	private Date horaFin;
	private String str_horaInicio;
	private String str_horaFin;

	private Integer cantHoraInicio;
	private Integer cantHoraFin;

	private AgendaCama_VO agendaCama;

	private Internacion_VO internacion;

	private Boolean activo = true;

	public Reserva_VO(Reserva bt) {
		this.setObject(bt);
	}

	public Reserva_VO(Reserva bt, int profundidadActual, int profundidadDeseada) {
		this.setObject(bt, profundidadActual, profundidadDeseada);
	}

	public Reserva_VO() {
	}

	@Override
	public void setObject(Reserva r) {
		this.setId(r.getId());
		this.setVersion(r.getVersion());
		this.setActivo(r.getActivo());
		this.setCantHoraFin(r.getCantHoraFin());
		this.setCantHoraInicio(r.getCantHoraInicio());
		this.setFechaFin(r.getFechaFin());
		this.setFechaInicio(r.getFechaInicio());
		this.setHoraFin(r.getHoraFin());
		this.setHoraInicio(r.getHoraInicio());
		this.setAgendaCama(r.getAgendaCama().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setInternacion(r.getInternacion().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setFechaAsignacion(r.getFechaAsignacion());
	}

	@Override
	public void setObject(Reserva r, int profundidadActual,
			int profundidadDeseada) {
		this.setId(r.getId());
		this.setVersion(r.getVersion());
		this.setActivo(r.getActivo());
		this.setCantHoraFin(r.getCantHoraFin());
		this.setCantHoraInicio(r.getCantHoraInicio());
		this.setFechaFin(r.getFechaFin());
		this.setFechaInicio(r.getFechaInicio());
		this.setHoraFin(r.getHoraFin());
		this.setHoraInicio(r.getHoraInicio());
		this.setFechaAsignacion(r.getFechaAsignacion());

		if (profundidadActual < profundidadDeseada) {
			this.setAgendaCama(r.getAgendaCama().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
			this.setInternacion(r.getInternacion().toValueObject(
					profundidadActual + 1, profundidadDeseada));
		}

	}

	@Override
	public Reserva toObject() {
		Reserva r = new Reserva();

		r.setId(this.getId());
		r.setVersion(this.getVersion());
		r.setActivo(this.getActivo());
		r.setCantHoraFin(this.getCantHoraFin());
		r.setCantHoraInicio(this.getCantHoraInicio());
		r.setFechaAsignacion(this.getFechaAsignacion());

		try {

			if (this.getStr_fechaInicio() != null) {
				r.setFechaInicio(new SimpleDateFormat("dd/MM/yyyy").parse(this
						.getStr_fechaInicio()));
			}
			if (this.getStr_fechaFin() != null) {
				r.setFechaFin(new SimpleDateFormat("dd/MM/yyyy").parse(this
						.getStr_fechaFin()));
			}
			if (this.getStr_horaInicio() != null) {
				r.setHoraInicio(new SimpleDateFormat("HH:mm").parse(this
						.getStr_horaInicio()));
			}
			if (this.getStr_horaFin() != null) {
				r.setHoraFin(new SimpleDateFormat("HH:mm").parse(this
						.getStr_horaFin()));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		r.setAgendaCama(this.getAgendaCama().toObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));

		if (this.getInternacion() != null) {
			r.setInternacion(this.getInternacion().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 1));
		}

		return r;
	}

	public Reserva toObject(int profundidadActual, int profundidadDeseada) {
		Reserva r = new Reserva();

		r.setId(this.getId());
		r.setVersion(this.getVersion());
		r.setActivo(this.getActivo());
		r.setCantHoraFin(this.getCantHoraFin());
		r.setCantHoraInicio(this.getCantHoraInicio());
		r.setFechaFin(this.getFechaFin());
		r.setFechaInicio(this.getFechaInicio());
		r.setHoraFin(this.getHoraFin());
		r.setHoraInicio(this.getHoraInicio());
		r.setFechaAsignacion(this.getFechaAsignacion());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
			r.setAgendaCama(this.getAgendaCama().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
			r.setInternacion(this.getInternacion().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}

		return r;
	}

	public int compareTo(Reserva_VO bt) {
		return 0;
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

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
		if (horaFin != null) {
			this.setStr_horaFin(new SimpleDateFormat("HH:mm").format(horaFin));
		}
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
		if (horaInicio != null) {
			this.setStr_horaInicio(new SimpleDateFormat("HH:mm")
					.format(horaInicio));
		}
	}

	public Integer getCantHoraInicio() {
		return cantHoraInicio;
	}

	public void setCantHoraInicio(Integer cantHoraInicio) {
		this.cantHoraInicio = cantHoraInicio;
	}

	public Integer getCantHoraFin() {
		return cantHoraFin;
	}

	public void setCantHoraFin(Integer cantHoraFin) {
		this.cantHoraFin = cantHoraFin;
	}

	public Reserva_VO acortar(int profundidadActual, int profundidadDeseada) {
		Reserva_VO r = new Reserva_VO();

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
		}

		return r;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
		if (fechaInicio != null) {
			this.setStr_fechaInicio(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaInicio));
		}
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
		if (fechaFin != null) {
			this.setStr_fechaFin(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaFin));
		}
	}

	public String getStr_fechaInicio() {
		return str_fechaInicio;
	}

	public void setStr_fechaInicio(String str_fechaInicio) {
		this.str_fechaInicio = str_fechaInicio;
	}

	public String getStr_fechaFin() {
		return str_fechaFin;
	}

	public void setStr_fechaFin(String str_fechaFin) {
		this.str_fechaFin = str_fechaFin;
	}

	public String getStr_horaInicio() {
		return str_horaInicio;
	}

	public void setStr_horaInicio(String str_horaInicio) {
		this.str_horaInicio = str_horaInicio;
	}

	public String getStr_horaFin() {
		return str_horaFin;
	}

	public void setStr_horaFin(String str_horaFin) {
		this.str_horaFin = str_horaFin;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Reserva_VO) {
			Reserva_VO o = (Reserva_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Internacion_VO getInternacion() {
		return internacion;
	}

	public void setInternacion(Internacion_VO internacion) {
		this.internacion = internacion;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public AgendaCama_VO getAgendaCama() {
		return agendaCama;
	}

	public void setAgendaCama(AgendaCama_VO agendaCama) {
		this.agendaCama = agendaCama;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
		if (fechaAsignacion != null) {
			this.setStr_fechaAsignacion(new SimpleDateFormat("dd/MM/yyyy HH:mm")
					.format(fechaAsignacion));
		}
	}

	public String getStr_fechaAsignacion() {
		return str_fechaAsignacion;
	}

	public void setStr_fechaAsignacion(String str_fechaAsignacion) {
		this.str_fechaAsignacion = str_fechaAsignacion;
	}

}
