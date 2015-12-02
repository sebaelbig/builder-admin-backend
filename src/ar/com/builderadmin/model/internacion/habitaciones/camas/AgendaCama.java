package ar.com.builderadmin.model.internacion.habitaciones.camas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.model.internacion.Reserva;
import ar.com.builderadmin.vo.internacion.habitaciones.camas.AgendaCama_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:09 a.m.
 */
//@Entity @Table( name = "agenda_cama")
public class AgendaCama implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_agenda_cama", name = "seq_agenda_cama", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agenda_cama")
	private Long id;

	@Version
	private Integer version;

	@OneToOne
	@JoinColumn(name = "id_cama")
	private Cama cama;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "agendaCama")
	private List<Reserva> reservas;

	// Constructores
	public AgendaCama() {
		setReservas(new ArrayList<Reserva>());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof AgendaCama) {
			AgendaCama o = (AgendaCama) objeto;
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

	public void eliminar() {
		getReservas().clear();
	}

	@Override
	public String toString() {
		return "Agenda de cama: " + this.getCama().getNumero();
	}

	public void agregarDia(Reserva dia) {
		this.getReservas().add(dia);
	}

	public AgendaCama_VO toValueObject() {
		return new AgendaCama_VO(this);
	}

	public AgendaCama_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new AgendaCama_VO(this, profundidadActual, profundidadDeseada);
	}

	public Cama getCama() {
		return cama;
	}

	public void setCama(Cama cama) {
		this.cama = cama;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}