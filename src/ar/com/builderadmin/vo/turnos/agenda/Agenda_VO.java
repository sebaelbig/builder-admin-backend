package ar.com.builderadmin.vo.turnos.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.model.turnos.agenda.Agenda;
import ar.com.builderadmin.model.turnos.agenda.Dia;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ContratoProfesional_VO;

public class Agenda_VO implements I_ValueObject<Agenda>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	
	private List<Dia_VO> dias = new ArrayList<Dia_VO>();
	
	private ContratoProfesional_VO contrato;
	
	private Date fechaInicioTurnosCreados;
	
	private Date fechaFinTurnosCreados;
	
	public Agenda_VO(Agenda agenda) {
		this.setObject(agenda);
	}

	public Agenda_VO(Agenda agenda, int profundidadActual, int profundidadDeseada) {
		this.setObject(agenda, profundidadActual, profundidadDeseada);
	}

	public Agenda_VO() {}

	@Override
	public void setObject(Agenda ag){
		
		this.setId(ag.getId());
		this.setVersion(ag.getVersion());
		this.setFechaInicioTurnosCreados(ag.getFechaInicioTurnosCreados());
		this.setFechaFinTurnosCreados(ag.getFechaFinTurnosCreados());
		
		this.setDias(new ArrayList<Dia_VO>());
		for (Dia d 	: ag.getDias()) {
			this.getDias().add(d.toValueObject());
		}
		
		//Atras
		this.setContrato(ag.getContrato().toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		
	}
	
	@Override
	public void setObject(Agenda ag, int profundidadActual, int profundidadDeseada) {
		
		this.setId(ag.getId());
		this.setVersion(ag.getVersion());
		this.setFechaInicioTurnosCreados(ag.getFechaInicioTurnosCreados());
		this.setFechaFinTurnosCreados(ag.getFechaFinTurnosCreados());
		
		this.setDias(new ArrayList<Dia_VO>());
		this.setContrato(null);
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE && profundidadActual < profundidadDeseada  ){
			
			for (Dia d 	: ag.getDias()) {
				this.getDias().add(d.toValueObject(profundidadActual+1, profundidadDeseada));
			}
		
			//Atras
			this.setContrato(ag.getContrato().toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
	}

	@Override
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

	public List<Dia_VO> getDias() {
		return dias;
	}

	public void setDias(List<Dia_VO> dias) {
		this.dias = dias;
	}

	public ContratoProfesional_VO getContrato() {
		return contrato;
	}

	public void setContrato(ContratoProfesional_VO contrato) {
		this.contrato = contrato;
	}

	public Date getFechaInicioTurnosCreados() {
		return fechaInicioTurnosCreados;
	}

	public void setFechaInicioTurnosCreados(Date fechaInicioTurnosCreados) {
		this.fechaInicioTurnosCreados = fechaInicioTurnosCreados;
	}

	public Date getFechaFinTurnosCreados() {
		return fechaFinTurnosCreados;
	}

	public void setFechaFinTurnosCreados(Date fechaFinTurnosCreados) {
		this.fechaFinTurnosCreados = fechaFinTurnosCreados;
	}

	public Agenda toObject(int profundidadActual, int profundidadDeseada) {
		Agenda resul = new Agenda();
		
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setFechaInicioTurnosCreados(this.getFechaInicioTurnosCreados());
		resul.setFechaFinTurnosCreados(this.getFechaFinTurnosCreados());
		
		resul.setDias(new ArrayList<Dia>());
		//Se chequea que no se halla llegado a la profundidad deseada
		if ( profundidadActual < profundidadDeseada  ){
			
			for (Dia_VO d 	: this.getDias()) {
				resul.getDias().add(d.toObject(profundidadActual+1, profundidadDeseada));
			}
			
			resul.setContrato(this.getContrato().toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
		
		return resul;
	}

	public Agenda_VO acortar(int profundidadActual, int profundidadDeseada) {
		Agenda_VO resul = new Agenda_VO();
		
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setFechaInicioTurnosCreados(this.getFechaInicioTurnosCreados());
		resul.setFechaFinTurnosCreados(this.getFechaFinTurnosCreados());
		
		resul.setDias(new ArrayList<Dia_VO>());
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada  ){
			
			for (Dia_VO d 	: this.getDias()) {
				resul.getDias().add(d.acortar(profundidadActual+1, profundidadDeseada));
			}
		}
		
		return resul;
	}
	@Override
	public Agenda toObject() {
		Agenda resul = new Agenda();
		
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setFechaInicioTurnosCreados(this.getFechaInicioTurnosCreados());
		resul.setFechaFinTurnosCreados(this.getFechaFinTurnosCreados());
		
		resul.setDias(new ArrayList<Dia>());
		for (Dia_VO d 	: this.getDias()) {
			resul.getDias().add(d.toObject());
		}
		
		return resul;
	}

}
