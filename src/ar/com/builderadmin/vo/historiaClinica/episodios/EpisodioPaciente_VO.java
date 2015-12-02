package ar.com.builderadmin.vo.historiaClinica.episodios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author carlalu
 */
public class EpisodioPaciente_VO {
	
	private int idEpisodio;
	private Date fecha;
	private String fechast;
	private String matriculaProfesional;
	private String nombreProfesional;
	private String especialidadAtencion;
	private String observacion;
	private String tipoEpisodio;
	
	public EpisodioPaciente_VO(){
		
	}
	
	public EpisodioPaciente_VO(int idEpisodio, String fechast,
			String matriculaProfesional, String nombreProfesional,
			String especialidadAtencion, String observacion, String tipoEpisodio) {
		super();
		this.idEpisodio = idEpisodio;
		this.fechast = fechast;
		this.matriculaProfesional = matriculaProfesional;
		this.nombreProfesional = nombreProfesional;
		this.especialidadAtencion = especialidadAtencion;
		this.observacion = observacion;
		this.tipoEpisodio = tipoEpisodio;
	}
	public int getIdEpisodio() {
		return idEpisodio;
	}
	public void setIdEpisodio(int idEpisodio) {
		this.idEpisodio = idEpisodio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getFechast() {
		return fechast;
	}
	public void setFechast(String fechast) {
		this.fechast = fechast;
		this.setFecha(fechast);
	}
	public void setFecha(String fecha){
		this.fecha= new Date(fecha);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = fecha;
	 
		try {
	 
			Date date = formatter.parse(dateInString);
			this.fecha=date;
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getMatriculaProfesional() {
		return matriculaProfesional;
	}
	public void setMatriculaProfesional(String matriculaProfesional) {
		this.matriculaProfesional = matriculaProfesional;
	}
	public String getNombreProfesional() {
		return nombreProfesional;
	}
	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}
	public String getEspecialidadAtencion() {
		return especialidadAtencion;
	}
	public void setEspecialidadAtencion(String especialidadAtencion) {
		this.especialidadAtencion = especialidadAtencion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getTipoEpisodio() {
		return tipoEpisodio;
	}
	public void setTipoEpisodio(String tipoEpisodio) {
		this.tipoEpisodio = tipoEpisodio;
	}
	

	
}
