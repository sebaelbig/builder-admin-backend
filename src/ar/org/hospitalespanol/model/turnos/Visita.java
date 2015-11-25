package ar.org.hospitalespanol.model.turnos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.Profesional;
import ar.org.hospitalespanol.model.turnos.estadosTurno.TurnoReservado;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
@Entity @Table
public class Visita implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_visita", name = "seq_visita", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_visita") private Long id;
	@Version
	private Integer version;
	
	@Column(length=300)
	private String detalle;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@OneToOne
	@JoinColumn(name="id_turno_tomado")
	private TurnoReservado turno;
	
	@ManyToOne
	@JoinColumn(name="id_profesional")
	private Profesional profesional;
	
	//Constructores
	public Visita(){

	}

	public Visita(Date fecha, TurnoReservado turno) {
		setFecha(fecha);
		setTurno(turno);
	}

	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Visita) {
			Visita o = (Visita) objeto;
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TurnoReservado getTurno() {
		return turno;
	}

	public void setTurno(TurnoReservado turno) {
		this.turno = turno;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

}