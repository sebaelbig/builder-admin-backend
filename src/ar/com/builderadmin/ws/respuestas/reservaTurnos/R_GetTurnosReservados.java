package ar.com.builderadmin.ws.respuestas.reservaTurnos;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_GetTurnosReservados extends R_Listador<TurnoReservado_VO> {
	private List<TurnoReservado_VO> turnos = new ArrayList();
	private String mensajeMutual;
	private String mensajeTurnos;

	public List<TurnoReservado_VO> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<TurnoReservado_VO> turnos) {
		this.turnos = turnos;
	}

	@Override
	public List<TurnoReservado_VO> getLista() {
		return getTurnos();
	}

	@Override
	public void setLista(List<TurnoReservado_VO> turnos) {
		setTurnos(turnos);
	}

	public String getMensajeMutual() {
		return this.mensajeMutual;
	}

	public void setMensajeMutual(String mensajeMutual) {
		this.mensajeMutual = mensajeMutual;
	}

	public String getMensajeTurnos() {
		return this.mensajeTurnos;
	}

	public void setMensajeTurnos(String mensajeTurnos) {
		this.mensajeTurnos = mensajeTurnos;
	}
}

/*
 * Location: D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * 
 * Qualified Name: WEB-INF.classes.org.hospitalespanol.respuestas.reservaTurnos.
 * R_GetTurnosReservados
 * 
 * JD-Core Version: 0.7.0.1
 */