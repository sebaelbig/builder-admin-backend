package ar.com.builderadmin.vo.internacion.habitaciones.camas.estadosCama;

import java.util.Date;

import ar.com.builderadmin.model.internacion.Internacion;
import ar.com.builderadmin.model.internacion.habitaciones.camas.estadosCama.EstadoCama;
import ar.com.builderadmin.vo.I_ValueObject;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class EstadoCama_VO implements I_ValueObject<EstadoCama> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	
	private Integer version;
	
	public static final String LIBRE = "Libre";
	public static final String OCUPADO = "Ocupado";
	public static final String SUSPENDIDO = "Suspendido";
	
	private String nombre;
	
	/**
	 * Fecha en la cual se ha establecido el estado actual
	 */
	private Date fechaEstablecido;
	
	
	
	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof EstadoCama_VO) {
			EstadoCama_VO o = (EstadoCama_VO) objeto;
			return o.getNombre().equals(this.getNombre());
		}
		return false;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaEstablecido() {
		return fechaEstablecido;
	}

	public void setFechaEstablecido(Date fechaEstablecido) {
		this.fechaEstablecido = fechaEstablecido;
	}

	public  EstadoCama_VO(){
		setFechaEstablecido(new Date());
	}
	
	public boolean estaLibre(){return false;}
	public boolean estaOcupado(){return false;}
	public boolean estaSuspendido(){return false;}

	@Override
	public void setObject(EstadoCama objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObject(EstadoCama objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EstadoCama toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public EstadoCama_VO ocupar(Internacion i) {
		return null;
	}
	
}