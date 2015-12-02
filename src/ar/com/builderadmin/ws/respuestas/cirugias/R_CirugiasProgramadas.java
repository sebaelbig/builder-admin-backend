package ar.com.builderadmin.ws.respuestas.cirugias;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_CirugiasProgramadas extends R_Listador<CirugiaProgramada_VO> {
	
	private Boolean ok = Boolean.valueOf(true);
	private List<CirugiaProgramada_VO> reservas = new ArrayList<CirugiaProgramada_VO>();
	private String fecha;
	private Integer nroSala;
	private String sala;
	
	public Boolean getOk() {
		return this.ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public List<CirugiaProgramada_VO> getLista() {
		return this.getReservas();
	}

	@Override
	public void setLista(List<CirugiaProgramada_VO> paramList) {
		this.setReservas(paramList);
	}

	/**
	 * @return the reservas
	 */
	public List<CirugiaProgramada_VO> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<CirugiaProgramada_VO> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @return the sala
	 */
	public String getSala() {
		return sala;
	}

	/**
	 * @param sala the sala to set
	 */
	public void setSala(String sala) {
		this.sala = sala;
	}

	/**
	 * @return the nroSala
	 */
	public Integer getNroSala() {
		return nroSala;
	}

	/**
	 * @param nroSala the nroSala to set
	 */
	public void setNroSala(Integer nroSala) {
		this.nroSala = nroSala;
	}
	
}