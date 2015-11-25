package ar.org.hospitalespanol.model.turnos.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.ContratoProfesional;
import ar.org.hospitalespanol.vo.turnos.agenda.Agenda_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:09 a.m.
 */
@Entity @Table
public class Agenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_agenda", name = "seq_agenda", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agenda")
	private Long id;

	@Version
	private Integer version;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "agenda")
	private List<Dia> dias;

	@OneToOne
	@JoinColumn(name = "id_contrato")
	private ContratoProfesional contrato;

	@Column(name = "fecha_inicio_turnos_creados")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicioTurnosCreados;

	@Column(name = "fecha_fin_turnos_creados")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFinTurnosCreados;

	// /**
	// * Estado de la agenda
	// */
	// @OneToOne(cascade=CascadeType.ALL)
	// @JoinColumn(name="id_estado")
	// private EstadoAgenda estado;

	// Constructores
	public Agenda() {
		// setEstado(new AgendaNueva());
		setDias(new ArrayList<Dia>());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Agenda) {
			Agenda o = (Agenda) objeto;
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

	public List<Dia> getDias() {
		return dias;
	}

	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}

	// public EstadoAgenda getEstado() {
	// return estado;
	// }
	//
	// public void setEstado(EstadoAgenda estado) {
	// this.estado = estado;
	// }

	public void guardarDia(Dia dia) {
		if (!contieneDia(dia)) {
			getDias().add(dia);
			dia.setAgenda(this);
		}
	}

	public void quitarDia(Dia dia) {
		// Si esta persistido
		if (dia.getId() != null) {
			getDias().remove(dia);
		} else {
			// Si no esta persistido
			int index = 0;
			for (Dia d : getDias()) {
				if (!d.getNombre().equals(dia.getNombre())) {
					index++;
				}
			}
			getDias().remove(index);
		}
	}

	public Boolean contieneDia(Dia dia) {
		boolean esta = false;
		Iterator<Dia> diass = getDias().iterator();

		while ((!esta) && (diass.hasNext())) {
			if (dia.getNombre().equals(diass.next().getNombre())) {
				esta = true;
			}
		}
		return esta;
	}

	public Dia obtenerDia(Dia dia) {
		boolean esta = false;
		Iterator<Dia> diass = getDias().iterator();
		Dia day = null;

		while ((!esta) && (diass.hasNext())) {
			day = diass.next();
			if (dia.getNombre().equals(day.getNombre())) {
				esta = true;
			}
		}

		return (esta) ? day : null;
	}

	public void eliminar() {
		getDias().clear();
	}

	public ContratoProfesional getContrato() {
		return contrato;
	}

	public void setContrato(ContratoProfesional contrato) {
		this.contrato = contrato;
	}

	@Override
	public String toString() {
		return "Agenda de :"
				+ this.getContrato().getProfesional().getUsuario()
						.getNombreUsuario();
	}

	public void agregarDia(Dia dia) {
		this.getDias().add(dia);

	}

	public Date getFechaFinTurnosCreados() {
		return fechaFinTurnosCreados;
	}

	public void setFechaFinTurnosCreados(Date fechaFinTurnosCreados) {
		this.fechaFinTurnosCreados = fechaFinTurnosCreados;
	}

	public Date getFechaInicioTurnosCreados() {
		return fechaInicioTurnosCreados;
	}

	public void setFechaInicioTurnosCreados(Date fechaInicioTurnosCreados) {
		this.fechaInicioTurnosCreados = fechaInicioTurnosCreados;
	}

	public Agenda_VO toValueObject() {
		return new Agenda_VO(this);
	}

	public Agenda_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new Agenda_VO(this, profundidadActual, profundidadDeseada);
	}

}