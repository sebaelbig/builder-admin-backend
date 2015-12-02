package ar.com.builderadmin.ws.respuestas.reservaTurnos;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_GetTurnosDesde extends R_Listador<TurnoReservado_VO> {

	private Boolean ok = Boolean.valueOf(true);
	
	private List<TurnoReservado_VO> turnos = new ArrayList<TurnoReservado_VO>();
	
	private String fechaTurnos;
	
	private Integer frecuencia;

	public List<TurnoReservado_VO> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<TurnoReservado_VO> profesionales) {
		this.turnos = profesionales;
	}

	@Override
	public List<TurnoReservado_VO> getLista() {
		return getTurnos();
	}

	@Override
	public void setLista(List<TurnoReservado_VO> profes) {
		setTurnos(profes);
	}

	public Boolean getOk() {
		return this.ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public String getFechaTurnos() {
		return this.fechaTurnos;
	}

	public void setFechaTurnos(String fechaTurnos) {
		this.fechaTurnos = fechaTurnos;
	}

	public Integer getFrecuencia() {
		return this.frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}
}

/*
 * Location: D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * 
 * Qualified Name:
 * WEB-INF.classes.org.hospitalespanol.respuestas.reservaTurnos.R_GetTurnosDesde
 * 
 * JD-Core Version: 0.7.0.1
 */