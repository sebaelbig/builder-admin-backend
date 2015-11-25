package ar.org.hospitalespanol.model.turnos.reportes.lineasCustomizadas;

import ar.org.hospitalespanol.model.core.usuarios.Usuario;

public class LineaListadoItemCantidadDeTurnos {

	/*
	 */
	private String item;
	private String profesional;
	// private String fechaDesde;
	// private String fechaHasta;
	private String cantTurnosDados;
	private String cantTurnosPresente;

	public LineaListadoItemCantidadDeTurnos(Object[] linea) {
	}

	/**
	 * Recibe una linea: linea[0] Nombre especialidad (String) linea[1] Apellido
	 * profesional (String) linea[2] Nombres profesional (String) linea[3]
	 * Nombre usuario (String) linea[4] Cantidad de turnos (Long)
	 * 
	 * @param linea
	 */
	public LineaListadoItemCantidadDeTurnos(String item, String ape,
			String nom, String nomUsr, Long cantTurnos) {
		setItem(item);
		setProfesional(ape + ", " + nom + " (" + nomUsr + ")");
		setCantTurnosDados(cantTurnos.toString());
	}

	public LineaListadoItemCantidadDeTurnos(String item, Usuario profesional,
			Long cantTurnos) {
		setItem(item);
		setProfesional(profesional.toString());
		setCantTurnosDados(cantTurnos.toString());
	}

	/*
	 * Getters y Setters
	 */

	public String getProfesional() {
		return profesional;
	}

	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}

	// public String getFechaDesde() {
	// return fechaDesde;
	// }
	//
	// public void setFechaDesde(String fechaDesde) {
	// this.fechaDesde = fechaDesde;
	// }
	//
	// public String getFechaHasta() {
	// return fechaHasta;
	// }
	//
	// public void setFechaHasta(String fechaHasta) {
	// this.fechaHasta = fechaHasta;
	// }

	public String getCantTurnosDados() {
		return cantTurnosDados;
	}

	public void setCantTurnosDados(String cantTurnosDados) {
		this.cantTurnosDados = cantTurnosDados;
	}

	public String getCantTurnosPresente() {
		return cantTurnosPresente;
	}

	public void setCantTurnosPresente(String cantTurnosPresente) {
		this.cantTurnosPresente = cantTurnosPresente;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
