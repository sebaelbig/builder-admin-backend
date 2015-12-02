package ar.com.builderadmin.model.internacion.habitaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ar.com.builderadmin.model.core.areas.servicios.Division;
import ar.com.builderadmin.model.internacion.habitaciones.camas.Cama;
import ar.com.builderadmin.vo.internacion.habitaciones.Habitacion_VO;

//@Entity 
@DiscriminatorValue("division_habitacion")
public class Habitacion extends Division implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Camas de la habitacion
	 */
	@OneToMany(mappedBy = "habitacion")
	private List<Cama> camas;

	@ManyToOne
	@JoinColumn(name = "id_tipo_categoria_habitacion")
	private TipoCategoriaHabitacion tipoCategoriaHabitacion;

	// Constructores
	public Habitacion() {
		setCamas(new ArrayList<Cama>());
	}

	// Gettters & Setters
	public List<Cama> getCamas() {
		return camas;
	}

	public void setCamas(List<Cama> camass) {
		this.camas = camass;
	}

	public TipoCategoriaHabitacion getTipoCategoriaHabitacion() {
		return tipoCategoriaHabitacion;
	}

	public void setTipoCategoriaHabitacion(
			TipoCategoriaHabitacion tipoCategoriaHabitacion) {
		this.tipoCategoriaHabitacion = tipoCategoriaHabitacion;
	}

	// Funciones
	@Override
	public String toString() {
		return "Habitaci�n: N�mero: " + getNumero() + ", Piso: " + getPiso()
				+ ", Ubicaci�n: " + getUbicacion();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Habitacion) {
			Habitacion o = (Habitacion) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Habitacion_VO toValueObject() {
		return new Habitacion_VO(this);
	}

	public Habitacion_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Habitacion_VO(this, profundidadActual, profundidadDeseada);
	}
}
