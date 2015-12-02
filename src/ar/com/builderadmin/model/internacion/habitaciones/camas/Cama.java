package ar.com.builderadmin.model.internacion.habitaciones.camas;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.model.internacion.Reserva;
import ar.com.builderadmin.model.internacion.habitaciones.Habitacion;
import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.CamaLibre;
import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.EstadoCama;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
//@Entity @Table
public class Cama implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_cama", name = "seq_cama", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cama")
	private Long id;
	@Version
	private Integer version;

	/**
	 * Estado del cama
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estado")
	private EstadoCama estado;

	/**
	 * Numero del cama
	 */
	private String numero;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_agenda")
	private AgendaCama agendaCama;

	@OneToOne
	@JoinColumn(name = "id_habitacion")
	private Habitacion habitacion;

	// Constructores
	public Cama() {
		setEstado(new CamaLibre());
		setAgendaCama(new AgendaCama());
	}

	public Cama(String nro, Integer duracion) {
		setEstado(new CamaLibre());
		setAgendaCama(new AgendaCama());
		setNumero(nro);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Cama) {
			Cama o = (Cama) objeto;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public EstadoCama getEstado() {
		return estado;
	}

	public void setEstado(EstadoCama estado) {
		this.estado = estado;
	}

	public AgendaCama getAgendaCama() {
		return agendaCama;
	}

	public void setAgendaCama(AgendaCama agenda) {
		this.agendaCama = agenda;
	}

//	public Cama_VO toValueObject() {
//		return new Cama_VO(this);
//	}
//
//	public Cama_VO toValueObject(int profundidadActual, int profundidadDeseada) {
//		return new Cama_VO(this, profundidadActual, profundidadDeseada);
//	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public void ocupar(Reserva r) {
		this.setEstado(this.getEstado().ocupar(r));

	}

}