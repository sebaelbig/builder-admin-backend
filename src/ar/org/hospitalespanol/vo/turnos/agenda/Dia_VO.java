package ar.org.hospitalespanol.vo.turnos.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.turnos.BloqueTurno;
import ar.org.hospitalespanol.model.turnos.agenda.Dia;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.DiaDeAtencion_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.EspecialidadProfesional_VO;
import ar.org.hospitalespanol.vo.turnos.BloqueTurno_VO;

public class Dia_VO implements I_ValueObject<Dia>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private List<BloqueTurno_VO> bloquesTurnos;

	private Date fecha;

	private Agenda_VO agenda;

	@Column(length = 10)
	private String nombre;

	private int numero_semana;

	public Dia_VO() {
	}

	public Dia_VO(Dia dia) {
		this.setObject(dia);
	}

	public Dia_VO(Dia dia, int profundidadActual, int profundidadDeseada) {
		this.setObject(dia, profundidadActual, profundidadDeseada);
	}

	public Dia_VO(DiaDeAtencion_VO d, EspecialidadProfesional_VO espe) {
		setNumero_semana(DAO_Utils.calcularNroDia(d.getNombre()));
		setNombre(d.getNombre());
		
		BloqueTurno_VO bt = new BloqueTurno_VO();
		bt.setHoraFin(d.getHoraFinAtencion());
		bt.setHoraInicio(d.getHoraInicioAtencion());
		bt.setServicio(d.getServicio());
		bt.setEspecialidadPrestada(espe);
		
		this.setBloquesTurnos(new ArrayList<BloqueTurno_VO>());
		this.getBloquesTurnos().add(bt);
	}

	@Override
	public void setObject(Dia d) {

		this.setId(d.getId());
		this.setVersion(d.getVersion());
		this.setFecha(d.getFecha());
		this.setNombre(d.getNombre());
		this.setNumero_semana(d.getNumero_semana());

		// Las referencias hacia arriba son planas
		if (d.getAgenda() != null)
			this.setAgenda(d.getAgenda().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));

		this.setBloquesTurnos(new ArrayList<BloqueTurno_VO>());
		for (BloqueTurno bt : d.getBloquesTurnos()) {

			this.getBloquesTurnos().add(bt.toValueObject());

		}

	}

	@Override
	public void setObject(Dia d, int profundidadActual, int profundidadDeseada) {

		this.setId(d.getId());
		this.setVersion(d.getVersion());
		this.setFecha(d.getFecha());
		this.setNombre(d.getNombre());
		this.setNumero_semana(d.getNumero_semana());

		this.setBloquesTurnos(new ArrayList<BloqueTurno_VO>());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			if (d.getAgenda() != null)
				this.setAgenda(d.getAgenda().toValueObject(
						profundidadActual + 1, profundidadDeseada));

			for (BloqueTurno bt : d.getBloquesTurnos()) {

				this.getBloquesTurnos().add(
						bt.toValueObject(profundidadActual + 1,
								profundidadDeseada));

			}

		}

	}

	@Override
	public Dia toObject() {
		Dia d = new Dia();

		d.setId(this.getId());
		d.setVersion(this.getVersion());
		d.setFecha(this.getFecha());
		d.setNumero_semana(this.getNumero_semana());
		d.setNombre(this.getNombre());

		if (this.getAgenda() != null)
			d.setAgenda(this.getAgenda().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));

		d.setBloquesTurnos(new ArrayList<BloqueTurno>());
		for (BloqueTurno_VO bt : this.getBloquesTurnos()) {

			d.getBloquesTurnos().add(bt.toObject());

		}

		return d;
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

	public List<BloqueTurno_VO> getBloquesTurnos() {
		return bloquesTurnos;
	}

	public void setBloquesTurnos(List<BloqueTurno_VO> bloquesTurnos) {
		this.bloquesTurnos = bloquesTurnos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Agenda_VO getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda_VO agenda) {
		this.agenda = agenda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero_semana() {
		return numero_semana;
	}

	public void setNumero_semana(int numero_semana) {
		this.numero_semana = numero_semana;
	}

	public Dia toObject(int profundidadActual, int profundidadDeseada) {
		Dia d = new Dia();

		d.setId(this.getId());
		d.setVersion(this.getVersion());
		d.setFecha(this.getFecha());
		d.setNumero_semana(this.getNumero_semana());
		d.setNombre(this.getNombre());

		d.setBloquesTurnos(new ArrayList<BloqueTurno>());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			for (BloqueTurno_VO bt : this.getBloquesTurnos()) {

				d.getBloquesTurnos().add(
						bt.toObject(profundidadActual + 1, profundidadDeseada));

			}

			if (this.getAgenda() != null)
				d.setAgenda(this.getAgenda().toObject(profundidadActual + 1,
						profundidadDeseada));
		}

		return d;

	}

	public Dia_VO acortar(int profundidadActual, int profundidadDeseada) {
		Dia_VO d = new Dia_VO();

		d.setId(this.getId());
		d.setVersion(this.getVersion());
		d.setFecha(this.getFecha());
		d.setNumero_semana(this.getNumero_semana());
		d.setNombre(this.getNombre());

		d.setBloquesTurnos(new ArrayList<BloqueTurno_VO>());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			for (BloqueTurno_VO bt : this.getBloquesTurnos()) {

				d.getBloquesTurnos().add(
						bt.acortar(profundidadActual + 1, profundidadDeseada));

			}
		}

		d.setAgenda(this.getAgenda());

		return d;
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
