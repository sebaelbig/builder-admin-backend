package ar.com.builderadmin.vo.turnos.agenda;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.builderadmin.model.core.usuarios.DiaAusente;
import ar.com.builderadmin.model.turnos.agenda.DiaCalendario;
import ar.com.builderadmin.vo.I_ValueObject;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
public class DiaCalendario_VO implements Serializable,
		I_ValueObject<DiaCalendario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String fecha;
	private String descripcion;

	DateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy");

	public DiaCalendario_VO(DiaCalendario d) {

		this.setId(d.getId());
		this.setVersion(d.getVersion());
		this.setFecha(formatoDia.format(d.getFecha()));
		this.setDescripcion(d.getDescripcion());

	}

	public DiaCalendario_VO(Long id, Integer version, Date fecha2,
			String descripcion2) {

		setId(id);
		setVersion(version);
		setFecha(formatoDia.format(fecha2));
		setDescripcion(descripcion2);
	}

	public DiaCalendario_VO() {
	}

	public DiaCalendario_VO(DiaAusente da) {
		setFecha((new SimpleDateFormat("dd/MM/yyyy").format(da.getFecha())));
		setDescripcion(da.getDescripcion());
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	@Override
	public void setObject(DiaCalendario objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public DiaCalendario toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(DiaCalendario objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

}