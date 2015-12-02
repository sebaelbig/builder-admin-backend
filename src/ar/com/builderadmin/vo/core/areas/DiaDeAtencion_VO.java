package ar.com.builderadmin.vo.core.areas;

import java.io.Serializable;
import java.util.Date;

import ar.com.builderadmin.model.core.areas.DiaDeAtencion;
import ar.com.builderadmin.utils.DateUtils;
import ar.com.builderadmin.vo.I_ValueObject;

public class DiaDeAtencion_VO implements Serializable,
		I_ValueObject<DiaDeAtencion> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String nombre; //Dia
	private Integer numero; //Dia

	private Date horaInicioAtencion; //BT
	private String str_horaInicioAtencion; //BT

	private Date horaFinAtencion; //BT
	private String str_horaFinAtencion; //BT

	private String servicio; //BT

	public DiaDeAtencion_VO() {
	}

	public DiaDeAtencion_VO(Long id, Integer version, String nombre,
			Integer numero) {
		super();
		this.id = id;
		this.version = version;
		this.nombre = nombre;
		this.numero = numero;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	@Override
	public DiaDeAtencion toObject() {
		return new DiaDeAtencion(getId(), getVersion(), getNombre(),
				getNumero());
	}

	@Override
	public void setObject(DiaDeAtencion paramT) {
		this.id = paramT.getId();
		this.version = paramT.getVersion();
		this.nombre = paramT.getNombre();
		this.numero = paramT.getNumero();
	}

	@Override
	public void setObject(DiaDeAtencion paramT, int paramInt1, int paramInt2) {
		this.setObject(paramT);

	}

	/**
	 * @return the horaInicioAtencion
	 */
	public Date getHoraInicioAtencion() {
		return horaInicioAtencion;
	}

	/**
	 * @param horaInicioAtencion
	 *            the horaInicioAtencion to set
	 */
	public void setHoraInicioAtencion(Date horaInicioAtencion) {
		this.horaInicioAtencion = horaInicioAtencion;
		if (horaInicioAtencion != null) {
			this.setStr_horaInicioAtencion(DateUtils
					.formatHour(horaInicioAtencion));
		}
	}

	/**
	 * @return the str_horaInicioAtencion
	 */
	public String getStr_horaInicioAtencion() {
		return str_horaInicioAtencion;
	}

	/**
	 * @param str_horaInicioAtencion
	 *            the str_horaInicioAtencion to set
	 */
	private void setStr_horaInicioAtencion(String str_horaInicioAtencion) {
		this.str_horaInicioAtencion = str_horaInicioAtencion;
	}

	/**
	 * @return the horaFinAtencion
	 */
	public Date getHoraFinAtencion() {
		return horaFinAtencion;
	}

	/**
	 * @param horaFinAtencion
	 *            the horaFinAtencion to set
	 */
	public void setHoraFinAtencion(Date horaFinAtencion) {
		this.horaFinAtencion = horaFinAtencion;
		if (horaFinAtencion != null) {
			this.setStr_horaFinAtencion(DateUtils.formatHour(horaFinAtencion));
		}
	}

	/**
	 * @return the str_horaFinAtencion
	 */
	public String getStr_horaFinAtencion() {
		return str_horaFinAtencion;
	}

	/**
	 * @param str_horaFinAtencion
	 *            the str_horaFinAtencion to set
	 */
	private void setStr_horaFinAtencion(String str_horaFinAtencion) {
		this.str_horaFinAtencion = str_horaFinAtencion;
	}

	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * @param servicio
	 *            the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
	
}