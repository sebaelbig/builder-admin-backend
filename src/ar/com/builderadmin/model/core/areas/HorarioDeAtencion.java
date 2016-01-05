package ar.com.builderadmin.model.core.areas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.core.areas.HorarioDeAtencion_VO;

@Entity @Table( name="horario_de_atencion")
public class HorarioDeAtencion {


	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_horario_atencion", name = "seq_horario_atencion",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_horario_atencion") 
	private Long id;
		
	@Version
	private Integer version;
	
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;
	
	/**
	 * Desde la hora 0 a la hora 23
	 */
	@Column(name="horario_inicio")
	@Temporal(TemporalType.TIME)
	private Date horarioInicio;
	
	/**
	 * Desde la hora 0 a la hora 23
	 */
	@Column(name="horario_fin")
	@Temporal(TemporalType.TIME)
	private Date horarioFin;
	
	@ManyToOne
	@JoinColumn(name="id_dia_atencion")
	private DiaDeAtencion diaDeAtencion;

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
	
	public Date getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Date getHorarioFin() {
		return horarioFin;
	}

	public void setHorarioFin(Date horarioFin) {
		this.horarioFin = horarioFin;
	}

	public DiaDeAtencion getDiaDeAtencion() {
		return diaDeAtencion;
	}

	public void setDiaDeAtencion(DiaDeAtencion diaAtencion) {
		this.diaDeAtencion = diaAtencion;
	}

	public HorarioDeAtencion_VO toValueObject() {
		return new HorarioDeAtencion_VO(this);
	}

	public HorarioDeAtencion_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new HorarioDeAtencion_VO(this);
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}
