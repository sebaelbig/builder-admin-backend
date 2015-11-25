package ar.org.hospitalespanol.vo.core.areas.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.core.areas.servicios.Consultorio;
import ar.org.hospitalespanol.model.core.especialidades.Especialidad;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.especialidades.Especialidad_VO;

public class Consultorio_VO implements I_ValueObject<Consultorio>{

	
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	
	private Integer version;
	
	private Integer numero;
	
	private String ubicacion;
	
	private String piso;
	
	private String descripcion;
	
	private BigDecimal costo;
	
	private BigDecimal costoAccionista;
	
	private List<Especialidad_VO> especialidadesSoportadas;
	
	private boolean fueraDeServicio = false;

	private String motivo;

	private Servicio_VO servicio;

	public Consultorio_VO(){
		setEspecialidadesSoportadas(new ArrayList<Especialidad_VO>());
	}
	
	@Override
	public void setObject(Consultorio objeto) {
		setCosto(objeto.getCosto());
		setCostoAccionista(objeto.getCostoAccionista());
		setDescricpion(objeto.getDescricpion());
		setId(objeto.getId());
		setNumero(objeto.getNumero());
		setPiso(objeto.getPiso());
		setUbicacion(objeto.getUbicacion());
		setVersion(objeto.getVersion());
		setFueraDeServicio(objeto.isFueraDeServicio());
		setMotivo(objeto.getMotivo());
		setEspecialidadesSoportadas(new ArrayList<Especialidad_VO>());
		
		if (objeto.getEspecialidadesSoportadas() != null)
			for (Especialidad esp : objeto.getEspecialidadesSoportadas()) {
				getEspecialidadesSoportadas().add(esp.toValueObjet());
			}
				
		setServicio(objeto.getServicio().toValueObject());
	}

	@Override
	public Consultorio toObject() {
		Consultorio consul = new Consultorio();
		consul.setCosto(getCosto());
		consul.setCostoAccionista(getCostoAccionista());
		consul.setDescricpion(getDescripcion());
		consul.setId(getId());
		consul.setNumero(getNumero());
		consul.setPiso(getPiso());
		consul.setUbicacion(getUbicacion());
		consul.setVersion(getVersion());
		consul.setFueraDeServicio(isFueraDeServicio());
		consul.setMotivo(getMotivo());
		
		if (this.getEspecialidadesSoportadas() != null){
			for (Especialidad_VO esp : this.getEspecialidadesSoportadas()) {
				consul.getEspecialidadesSoportadas().add(esp.toObject());
			}
		}
		
		if (this.getServicio() != null){
			consul.setServicio(getServicio().toObject());
		}
		
		return consul;
	}
	
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Consultorio_VO) {
			Consultorio_VO o = (Consultorio_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "N�mero: "+getNumero()+", Ubicaci�n: "+getUbicacion()+", Piso: "+getPiso();
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescricpion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public List<Especialidad_VO> getEspecialidadesSoportadas() {
		return especialidadesSoportadas;
	}

	public void setEspecialidadesSoportadas(
			List<Especialidad_VO> especialidadesSoportadas) {
		this.especialidadesSoportadas = especialidadesSoportadas;
	}

	public BigDecimal getCostoAccionista() {
		return costoAccionista;
	}

	public void setCostoAccionista(BigDecimal costoAccionista) {
		this.costoAccionista = costoAccionista;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Consultorio_VO(Consultorio objeto){
		setObject(objeto);
	}
	
	public Consultorio_VO(Consultorio consu, int profundidadActual, int profundidadDeseada) {
		setObject(consu, profundidadActual, profundidadDeseada);
	}

	
	
	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	
	public boolean isFueraDeServicio() {
		return fueraDeServicio;
	}

	public void setFueraDeServicio(boolean fueraDeServicio) {
		this.fueraDeServicio = fueraDeServicio;
	}

	

	@Override
	public void setObject(Consultorio objeto, int profundidadActual, int profundidadDeseada) {
		setCosto(objeto.getCosto());
		setCostoAccionista(objeto.getCostoAccionista());
		setDescricpion(objeto.getDescricpion());
		setId(objeto.getId());
		setNumero(objeto.getNumero());
		setPiso(objeto.getPiso());
		setUbicacion(objeto.getUbicacion());
		setFueraDeServicio(objeto.isFueraDeServicio());
		setMotivo(objeto.getMotivo());
		setVersion(objeto.getVersion());
		
		setEspecialidadesSoportadas(new ArrayList<Especialidad_VO>());
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada  ){			
			for (Especialidad esp : objeto.getEspecialidadesSoportadas()) {
				getEspecialidadesSoportadas().add(esp.toValueObjet());
			}	
			
			setServicio(objeto.getServicio().toValueObject(profundidadActual+1, profundidadDeseada));
		}
		
		
	}

	public Consultorio toObject(int profundidadActual, int profundidadDeseada) {

		Consultorio cons = new Consultorio();
		cons.setCosto(getCosto());
		cons.setCostoAccionista(getCostoAccionista());
		cons.setDescricpion(getDescripcion());
		cons.setId(getId());
		cons.setNumero(getNumero());
		cons.setPiso(getPiso());
		cons.setUbicacion(getUbicacion());
		cons.setVersion(getVersion());
		cons.setFueraDeServicio(isFueraDeServicio());
		cons.setMotivo(getMotivo());
		
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada  ){
			
			for (Especialidad_VO e : getEspecialidadesSoportadas()) {
				cons.getEspecialidadesSoportadas().add(e.toObject());
			}
			
			cons.setServicio(getServicio().toObject(profundidadActual+1, profundidadDeseada));
			
		}
		
		return cons;
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio) {
		this.servicio = servicio;
	}
}
