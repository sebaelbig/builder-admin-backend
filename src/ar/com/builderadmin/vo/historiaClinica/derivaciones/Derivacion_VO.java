package ar.com.builderadmin.vo.historiaClinica.derivaciones;



/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
public class Derivacion_VO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Long id_especialidad;
	private Integer version;
	
	private String titulo;
	private String tipoInforme;
	private String especialidad;
	
	private String profesional;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id_informe) {
		this.id = id_informe;
	}

	public Long getId_especialidad() {
		return id_especialidad;
	}

	public void setId_especialidad(Long id_especialidad) {
		this.id_especialidad = id_especialidad;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getProfesional() {
		return profesional;
	}

	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}

}