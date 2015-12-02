package ar.com.builderadmin.ws.respuestas.reservaTurnos;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.turnos.BloqueTurno_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_BloqueTurnos extends R_Listador<BloqueTurno_VO> {
	
	private String especialidad;
	private Boolean aceptaListaDeEspera = Boolean.valueOf(false);
//	List<BloqueTurno_VO> profes = new ArrayList<BloqueTurno_VO>();
	List<BloqueTurno_VO> profesionales = new ArrayList<BloqueTurno_VO>();

	public List<BloqueTurno_VO> getBloques() {
		return this.profesionales;
	}

	public void setBloques(List<BloqueTurno_VO> bloques) {
		this.profesionales = bloques;
	}

	@Override
	public List<BloqueTurno_VO> getLista() {
		return getBloques();
	}

	@Override
	public void setLista(List<BloqueTurno_VO> profes) {
		setBloques(profes);
	}

	public String getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Boolean getAceptaListaDeEspera() {
		return this.aceptaListaDeEspera;
	}

	public void setAceptaListaDeEspera(Boolean aceptaListaDeEspera) {
		this.aceptaListaDeEspera = aceptaListaDeEspera;
	}

	/**
	 * @return the profes
	 */
	public List<BloqueTurno_VO> getProfes() {
		return profesionales;
	}

	/**
	 * @param profes the profes to set
	 */
	public void setProfes(List<BloqueTurno_VO> profes) {
		this.profesionales = profes;
	}
	
}