package ar.com.builderadmin.model.core.areas.servicios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ar.com.builderadmin.model.core.especialidades.Especialidad;
import ar.com.builderadmin.vo.core.areas.servicios.Consultorio_VO;

@Entity 
@Table
@DiscriminatorValue("division_consultorio")
public class Consultorio extends Division implements Serializable{

	private static final long serialVersionUID = 1L; 

	/**
	 *   Especialidades que soporta el division
	 */
	@ManyToMany
	@JoinTable(
			name="division_especialidad",
			joinColumns = @JoinColumn(name="id_division"),
			inverseJoinColumns = @JoinColumn(name="id_especialidad"),
			uniqueConstraints = @UniqueConstraint(columnNames={"id_division","id_especialidad"})
	)
	private List<Especialidad> especialidadesSoportadas;
	
	//Constructores
	public Consultorio() {
		setEspecialidadesSoportadas(new ArrayList<Especialidad>());
	}
	
	//Gettters & Setters
	public List<Especialidad> getEspecialidadesSoportadas() {
		return especialidadesSoportadas;
	}

	public void setEspecialidadesSoportadas(
			List<Especialidad> especialidadesSoportadas) {
		this.especialidadesSoportadas = especialidadesSoportadas;
	}
	
	//Funciones
	@Override
	public String toString() {
		return "Consultorio: N�mero: "+getNumero()+", Piso: "+getPiso()+", Ubicaci�n: "+getUbicacion();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Consultorio) {
			Consultorio o = (Consultorio) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public Consultorio_VO toValueObject(){
		return new Consultorio_VO(this);
	}
	
	public Consultorio_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new Consultorio_VO(this, profundidadActual, profundidadDeseada);
	}
}
