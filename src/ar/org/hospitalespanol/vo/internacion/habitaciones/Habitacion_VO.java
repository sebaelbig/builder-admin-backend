package ar.org.hospitalespanol.vo.internacion.habitaciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.internacion.habitaciones.Habitacion;
import ar.org.hospitalespanol.model.internacion.habitaciones.camas.Cama;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.Cama_VO;

public class Habitacion_VO implements I_ValueObject<Habitacion> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private Integer numero;

	private String ubicacion;

	private String piso;

	private String descripcion;

	private BigDecimal costo;

	private BigDecimal costoAccionista;

	private boolean fueraDeServicio = false;

	private String motivo;

	private Servicio_VO servicio;

	/**
	 * Camas de la habitacion
	 */
	private List<Cama_VO> camas = new ArrayList<Cama_VO>();

	private TipoCategoriaHabitacion_VO tipoCategoriaHabitacion;

	// Constructores
	public Habitacion_VO() {
	}

	public Habitacion_VO(Habitacion hab) {
		this.setObject(hab);
	}

	public Habitacion_VO(Habitacion cama, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(cama, profundidadActual, profundidadDeseada);
	}

	// Funciones
	@Override
	public void setObject(Habitacion objeto) {
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
		setCamas(new ArrayList<Cama_VO>());
		setTipoCategoriaHabitacion(objeto.getTipoCategoriaHabitacion()
				.toValueObject());
//		if (objeto.getCamas() != null) {
//			for (Cama cama : objeto.getCamas()) {
//				getCamas().add(cama.toValueObject());
//			}
//		}
		setServicio(objeto.getServicio().toValueObject(0, 0));
	}

	@Override
	public Habitacion toObject() {
		Habitacion hab = new Habitacion();
		hab.setCosto(getCosto());
		hab.setCostoAccionista(getCostoAccionista());
		hab.setDescricpion(getDescripcion());
		hab.setId(getId());
		hab.setNumero(getNumero());
		hab.setPiso(getPiso());
		hab.setUbicacion(getUbicacion());
		hab.setVersion(getVersion());
		hab.setFueraDeServicio(isFueraDeServicio());
		hab.setMotivo(getMotivo());
		hab.setTipoCategoriaHabitacion(getTipoCategoriaHabitacion().toObject());
//		if (this.getCamas() != null) {
//			for (Cama_VO c : this.getCamas()) {
//				hab.getCamas().add(c.toObject());
//			}
//		}

		return hab;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Habitacion_VO) {
			Habitacion_VO o = (Habitacion_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		return "N�mero: " + getNumero() + ", Ubicaci�n: " + getUbicacion()
				+ ", Piso: " + getPiso();
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

	public BigDecimal getCostoAccionista() {
		return costoAccionista;
	}

	public void setCostoAccionista(BigDecimal costoAccionista) {
		this.costoAccionista = costoAccionista;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public void setObject(Habitacion objeto, int profundidadActual,
			int profundidadDeseada) {
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
		setServicio(objeto.getServicio().toValueObject());

		setCamas(new ArrayList<Cama_VO>());

		// Se chequea que no se halla llegado a la profundidad deseada
//		if (profundidadActual < profundidadDeseada) {
//			for (Cama c : objeto.getCamas()) {
//				getCamas().add(c.toValueObject());
//			}
//		}

	}

	public Habitacion toObject(int profundidadActual, int profundidadDeseada) {

		Habitacion hab = new Habitacion();
		hab.setCosto(getCosto());
		hab.setCostoAccionista(getCostoAccionista());
		hab.setDescricpion(getDescripcion());
		hab.setId(getId());
		hab.setNumero(getNumero());
		hab.setPiso(getPiso());
		hab.setUbicacion(getUbicacion());
		hab.setVersion(getVersion());
		hab.setFueraDeServicio(isFueraDeServicio());
		hab.setMotivo(getMotivo());
		hab.setServicio(getServicio().toObject());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

//			for (Cama_VO e : getCamas()) {
//				hab.getCamas().add(e.toObject());
//			}

		}
		return hab;
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio) {
		this.servicio = servicio;
	}

	public List<Cama_VO> getCamas() {
		return camas;
	}

	public void setCamas(List<Cama_VO> camas) {
		this.camas = camas;
	}

	public TipoCategoriaHabitacion_VO getTipoCategoriaHabitacion() {
		return tipoCategoriaHabitacion;
	}

	public void setTipoCategoriaHabitacion(
			TipoCategoriaHabitacion_VO tipoCategoriaHabitacion) {
		this.tipoCategoriaHabitacion = tipoCategoriaHabitacion;
	}

	public void reemplazarCama(Cama_VO c) {
		// Remuevo de la coleccion la cama con ese id
		this.getCamas().remove(c);
		// Pongo la cama con el estado nuevo
		this.getCamas().add(c);

	}

}
