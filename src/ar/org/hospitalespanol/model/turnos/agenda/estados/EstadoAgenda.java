package ar.org.hospitalespanol.model.turnos.agenda.estados;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table(name="estado_agenda")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="estado_agenda",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("estado_agenda_base")
public abstract class EstadoAgenda {

	/**
	 * 
	 */
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_estado_agenda", name = "seq_estado_agenda",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estado_agenda") private Long id;

	@Version
	private Integer version;
	
	//Constructores
	public EstadoAgenda(){

	}

	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoAgenda) {
			EstadoAgenda o = (EstadoAgenda) objeto;
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

}