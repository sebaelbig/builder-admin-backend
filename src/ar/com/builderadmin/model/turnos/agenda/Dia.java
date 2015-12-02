package ar.com.builderadmin.model.turnos.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.turnos.BloqueTurno;
import ar.com.builderadmin.vo.turnos.agenda.Dia_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
//@Entity @Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_dia", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dia")
public class Dia {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_dia", name = "seq_dia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dia")
	private Long id;

	@Version
	private Integer version;

	public static final String LUNES = "Lunes";
	public static final String MARTES = "Martes";
	public static final String MIERCOLES = "Mi�rcoles";
	public static final String JUEVES = "Jueves";
	public static final String VIERNES = "Viernes";
	public static final String SABADO = "S�bado";
	public static final String DOMINGO = "Domingo";

	// public static final int DOMINGO_c=1;
	// public static final int LUNES_c=2;
	// public static final int MARTES_c=3;
	// public static final int MIERCOLES_c=4;
	// public static final int JUEVES_c=5;
	// public static final int VIERNES_c=6;
	// public static final int SABADO_c=7;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dia")
	@JoinColumn(name = "bloques_turnos")
	private List<BloqueTurno> bloquesTurnos;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_agenda")
	private Agenda agenda;

	@Column(length = 10)
	private String nombre;

	private int numero_semana;

	// Constructores
	public Dia() {
		setBloquesTurnos(new ArrayList<BloqueTurno>());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Dia) {
			Dia o = (Dia) objeto;
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

	public List<BloqueTurno> getBloquesTurnos() {
		return bloquesTurnos;
	}

	public void setBloquesTurnos(List<BloqueTurno> bloquesTurnos) {
		this.bloquesTurnos = bloquesTurnos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public int getNumero_semana() {
		return numero_semana;
	}

	public void setNumero_semana(int numero_semana) {
		this.numero_semana = numero_semana;
	}

	public void quitarBloqueTurno(BloqueTurno bloqueTurno) {
		getBloquesTurnos().remove(bloqueTurno);
		if (!tieneBloques()) {
			getAgenda().quitarDia(this);
		}
	}

	public void quitarBloqueTurno(Integer indiceBloqueTurno) {
		getBloquesTurnos().remove(indiceBloqueTurno.intValue());
		if (!tieneBloques()) {
			getAgenda().quitarDia(this);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Agrega el bloque turno en caso de que no este en el dia
	 * 
	 * @param bloqueTurno
	 */
	public void agregarBloqueTurno(BloqueTurno bloqueTurno) {
		getBloquesTurnos().add(bloqueTurno);
	}

	public boolean estaBloqueTurno(BloqueTurno bloqueTurno) {
		boolean esta = false;
		Iterator<BloqueTurno> bts = getBloquesTurnos().iterator();

		while ((bts.hasNext()) && (!esta)) {
			esta = bts.next().igualA(bloqueTurno);
		}
		return esta;
	}

	public boolean tieneBloques() {
		return getBloquesTurnos().iterator().hasNext();
	}

	public boolean horarioDisponible(BloqueTurno bloqueTurno) {
		boolean disponible = true;

		Iterator<BloqueTurno> bts = getBloquesTurnos().iterator();
		while ((bts.hasNext()) && (disponible)) {
			disponible = disponible && !bts.next().intersectaCon(bloqueTurno);
		}

		return disponible;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public Dia_VO toValueObject() {
		return new Dia_VO(this);
	}

	public Dia_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new Dia_VO(this, profundidadActual, profundidadDeseada);
	}

}