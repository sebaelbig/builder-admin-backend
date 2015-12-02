package ar.com.builderadmin.model.core.especialidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.google.gson.Gson;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;


/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table
public class Especialidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_especialidad", name = "seq_especialidad", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_especialidad")
	private Long id;

	@Version
	private Integer version;
	
	/**
	 *  Nombre de la especialidad
	 */
	@Column(length=40)
	private String nombre;
	
	/**
	 *  Descripcion de la especialidad
	 */
	@Column(length=200)
	private String descripcion;
			
	/**
	 *  Nombre que se utiliza para hacer referencia al profesional.
	 *  
	 *  Por ejemplo: 
	 *   -Nombre: Ginecologia
	 *   -Nombre profesional: Ginecologo
	 */
	@Column(length=40, name="nombre_profesional")
	private String nombreProfesional;
	
	/**
	 * Especialidad 
	 */
	@ManyToMany
	private List<Prestacion> prestaciones = new ArrayList<Prestacion>();
	
	//Constructores
	public Especialidad(){

	}
	
	public Especialidad(Long id2, Integer version2, String nombre2, String descripcion2) {
		this.setId(id2);
		this.setVersion(version2);
		this.setNombre(nombre2);
		this.setDescripcion(descripcion2);
	}

	public Especialidad(String string) {
		this.setNombre(string);
	}

	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Especialidad) {
			Especialidad o = (Especialidad) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString(){
		return this.getNombre();
	}
	
	

	public List<Prestacion> getPrestaciones() {
		return this.prestaciones;
	}

	public void setPrestaciones(List<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}
	
	public void agregarPrestacion(Prestacion prestacionNueva){
		
		if (this.getPrestaciones().isEmpty()){
			//No hay prestaciones
			this.getPrestaciones().add(prestacionNueva);
		}else{
			if (!this.getPrestaciones().contains(prestacionNueva)){
				this.getPrestaciones().add(prestacionNueva);
			}
		}
	}
	
	public void quitarPrestacion(Prestacion prestacionVieja){
		
		if (this.getPrestaciones().isEmpty()){
			//No hace nada, no hay prestaciones
		}else{
			if (this.getPrestaciones().contains(prestacionVieja)){
				this.getPrestaciones().remove(prestacionVieja);
			}
		}
	}

	public String getNombreProfesional() {
		return this.nombreProfesional;
	}

	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}

	public Especialidad_VO toValueObjet(){
		return new Especialidad_VO(this);
	}
	
	public Especialidad_VO toValueObjet(int profundidadActual, int profundidadDeseada) {
		return new Especialidad_VO(this, profundidadActual, profundidadDeseada);
	}
	
	public String toJson(){
		return new Gson().toJson(this);
	}
}