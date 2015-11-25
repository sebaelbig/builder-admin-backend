package ar.org.hospitalespanol.ws.respuestas.cirugias;

import java.util.Date;

//import org.hospitalespanol.respuestas.datosDelPaciente.R_DatosCirugiaProgramadas;

public class CirugiaProgramada_VO {

	private String dia;
	
	//Vista en la grilla
	private String horaInicio;
	private String horaFin;
	private Date horaInicioDate;
	private Date horaFinDate;
	private String profesional; //Consta de Matricula, apellido y nombres
	private String cirugiaProgramada;
	private String paciente;//Apellido y nombre
	private String habitacion; //Habitacion donde esta/estara internado el paciente
	
	//Descripcion de la cirugia
	private String numero; //Numero de la cirugia
	private String fecha; //Fecha completa
	private Date fechaDate; 
	private String sala;
	private String fechaReservaSala;
	
	//Equipo quirurgico
	private String anestesista;
	private String instrumentista;
	private String patologo;
	private String pediatra; //Solo cesareas
	

	public CirugiaProgramada_VO() {
	}


	public void pisarDatos(CirugiaProgramada_VO u) {
	}

	/**
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * @return the profesional
	 */
	public String getProfesional() {
		return profesional;
	}

	/**
	 * @param profesional the profesional to set
	 */
	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}

	/**
	 * @return the cirugiaProgramada
	 */
	public String getCirugiaProgramada() {
		return cirugiaProgramada;
	}

	/**
	 * @param cirugiaProgramada the cirugiaProgramada to set
	 */
	public void setCirugiaProgramada(String cirugiaProgramada) {
		this.cirugiaProgramada = cirugiaProgramada;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the habitacion
	 */
	public String getHabitacion() {
		return habitacion;
	}

	/**
	 * @param habitacion the habitacion to set
	 */
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @return the anestesista
	 */
	public String getAnestesista() {
		return anestesista;
	}

	/**
	 * @param anestesista the anestesista to set
	 */
	public void setAnestesista(String anestesista) {
		this.anestesista = anestesista;
	}

	/**
	 * @return the instrumentista
	 */
	public String getInstrumentista() {
		return instrumentista;
	}

	/**
	 * @param instrumentista the instrumentista to set
	 */
	public void setInstrumentista(String instrumentista) {
		this.instrumentista = instrumentista;
	}

	/**
	 * @return the patologo
	 */
	public String getPatologo() {
		return patologo;
	}

	/**
	 * @param patologo the patologo to set
	 */
	public void setPatologo(String patologo) {
		this.patologo = patologo;
	}

	/**
	 * @return the pediatra
	 */
	public String getPediatra() {
		return pediatra;
	}

	/**
	 * @param pediatra the pediatra to set
	 */
	public void setPediatra(String pediatra) {
		this.pediatra = pediatra;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the horaInicioDate
	 */
	public Date getHoraInicioDate() {
		return horaInicioDate;
	}

	/**
	 * @param horaInicioDate the horaInicioDate to set
	 */
	public void setHoraInicioDate(Date horaInicioDate) {
		this.horaInicioDate = horaInicioDate;
	}

	/**
	 * @return the horaFinDate
	 */
	public Date getHoraFinDate() {
		return horaFinDate;
	}

	/**
	 * @param horaFinDate the horaFinDate to set
	 */
	public void setHoraFinDate(Date horaFinDate) {
		this.horaFinDate = horaFinDate;
	}

	/**
	 * @return the fechaDate
	 */
	public Date getFechaDate() {
		return fechaDate;
	}

	/**
	 * @param fechaDate the fechaDate to set
	 */
	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}

	/**
	 * @return the fechaReservaSala
	 */
	public String getFechaReservaSala() {
		return fechaReservaSala;
	}

	/**
	 * @param fechaReservaSala the fechaReservaSala to set
	 */
	public void setFechaReservaSala(String fechaReservaSala) {
		this.fechaReservaSala = fechaReservaSala;
	}

}