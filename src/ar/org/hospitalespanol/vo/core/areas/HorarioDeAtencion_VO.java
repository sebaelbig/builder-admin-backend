package ar.org.hospitalespanol.vo.core.areas;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.model.core.areas.HorarioDeAtencion;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class HorarioDeAtencion_VO implements I_ValueObject<HorarioDeAtencion> {

	public static final String HORA_NEUTRA = "00:00:00";
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Long idArea;
	private Integer version;
	private String horarioInicio;
	private Integer cantHoraInicio;
	private Integer cantHoraFin;
	private String horarioFin;
	private DiaDeAtencion_VO diaDeAtencion;
	private boolean seleccionado = false;

	public HorarioDeAtencion_VO(HorarioDeAtencion h) {
		setObject(h);
	}

	public Long getIdArea() {
		return this.idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getHorarioInicio() {
		return this.horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
		setCantHoraInicio(convertirHoraEnCantHoras(horarioInicio));
	}

	public String getHorarioFin() {
		return this.horarioFin;
	}

	public void setHorarioFin(String horarioFin) {
		this.horarioFin = horarioFin;
		setCantHoraFin(convertirHoraEnCantHoras(horarioFin));
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public boolean isSeleccionado() {
		return this.seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	@Override
	public String toString() {
		String resul = "Horario de atencion: " + getDiaDeAtencion().getNombre();
		if (getHorarioInicio() != null) {
			resul = resul + " de: " + getHorarioInicio();
		}
		if (getHorarioFin() != null) {
			resul = resul + " a " + getHorarioFin();
		}
		return resul;
	}

	public Integer getCantHoraInicio() {
		return this.cantHoraInicio;
	}

	public void setCantHoraInicio(Integer cantHoraInicio) {
		this.cantHoraInicio = cantHoraInicio;
	}

	public Integer getCantHoraFin() {
		return this.cantHoraFin;
	}

	public void setCantHoraFin(Integer cantHoraFin) {
		this.cantHoraFin = cantHoraFin;
	}

	private Integer convertirHoraEnCantHoras(String hora) {
		String horaLimpia = hora.split(":")[0].concat(hora.split(":")[1]);

		Integer cantHora = Integer.valueOf(Integer.parseInt(horaLimpia));

		return Integer.valueOf(cantHora.intValue() / 50);
	}

	/*********************************************************/
	// I_ValueObject
	@Override
	public void setObject(HorarioDeAtencion objeto) {
		setId(objeto.getId());
		setSeleccionado(true);
		setVersion(objeto.getVersion());

		setDiaDeAtencion(objeto.getDiaDeAtencion().toValueObject());

		SimpleDateFormat s = new SimpleDateFormat("HH:mm");
		if (objeto.getHorarioFin() != null)
			setHorarioFin(s.format(objeto.getHorarioFin()));

		if (objeto.getHorarioInicio() != null)
			setHorarioInicio(s.format(objeto.getHorarioInicio()));
	}

	@Override
	public void setObject(HorarioDeAtencion objeto, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(objeto);
	}

	@Override
	public HorarioDeAtencion toObject() {
		SimpleDateFormat s = new SimpleDateFormat("HH:mm");
		HorarioDeAtencion h = new HorarioDeAtencion();

		h.setDiaDeAtencion(getDiaDeAtencion().toObject());
		try {
			h.setHorarioFin(s.parse(getHorarioFin()));
			h.setHorarioInicio(s.parse(getHorarioInicio()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		h.setId(getId());
		h.setVersion(getVersion());
		return h;
	}

	/**
	 * @return the diaDeAtencion
	 */
	public DiaDeAtencion_VO getDiaDeAtencion() {
		return diaDeAtencion;
	}

	/**
	 * @param diaDeAtencion the diaDeAtencion to set
	 */
	public void setDiaDeAtencion(DiaDeAtencion_VO diaDeAtencion) {
		this.diaDeAtencion = diaDeAtencion;
	}
	
	
}
