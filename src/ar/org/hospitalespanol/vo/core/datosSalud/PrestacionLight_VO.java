package ar.org.hospitalespanol.vo.core.datosSalud;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.datosSalud.Prestacion;

public class PrestacionLight_VO implements Serializable {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String codigoUnico;
	private String codigoInterno;
	private String nombre;
	private TipoPrestacionHorus_VO tipoPrestacion;
	private NomencladorHorus_VO nomenclador;
	// private Float derechos;
	// private Float costoDerechos;
	// private Float honorarios;
	// private Float costoHonorarios;
	private String indicacion_preparacion;
	private String contraindicaciones;
	private String restricciones;

	// private float utilidadInterna;

	public PrestacionLight_VO() {

	}

	public PrestacionLight_VO(Prestacion prestacion) {
		setObject(prestacion);
	}

	public PrestacionLight_VO(Prestacion prestacion, int profundidadActual,
			int profundidadBase) {
		setObject(prestacion, profundidadActual, profundidadBase);
	}

	public void setObject(Prestacion prestacion) {
		// this.setCodigoInterno(prestacion.getCodigoInterno());
		// this.setCostoDerechos(prestacion.getCostoDerechos());
		// this.setCostoHonorarios(prestacion.getCostoHonorarios());
		// this.setDerechos(prestacion.getDerechos());
		// this.setHonorarios(prestacion.getHonorarios());
		this.setId(prestacion.getId());
		this.setCodigoUnico(prestacion.getCodigoUnico());
		this.setContraindicaciones(prestacion.getContraindicaciones());
		this.setNombre(prestacion.getNombre());
		this.setIndicacion_preparacion(prestacion.getIndicacion_preparacion());
		this.setRestricciones(prestacion.getRestricciones());
		this.setVersion(prestacion.getVersion());

		this.setNomenclador(prestacion.getNomenclador().toValueObject());
		this.setTipoPrestacion(prestacion.getTipoPrestacion().toValueObject());
	}

	public void setObject(Prestacion prestacion, int profundidadActual,
			int profundidadDeseada) {
		this.setId(prestacion.getId());
		this.setCodigoUnico(prestacion.getCodigoUnico());
		this.setContraindicaciones(prestacion.getContraindicaciones());
		this.setNombre(prestacion.getNombre());
		this.setIndicacion_preparacion(prestacion.getIndicacion_preparacion());
		this.setRestricciones(prestacion.getRestricciones());
		this.setVersion(prestacion.getVersion());

		if (profundidadActual < profundidadDeseada) {
			this.setNomenclador(prestacion.getNomenclador().toValueObject(
					profundidadActual + 1, profundidadDeseada));
			this.setTipoPrestacion(prestacion.getTipoPrestacion()
					.toValueObject(profundidadActual + 1, profundidadDeseada));
		}
	}

	public Prestacion toObject() {
		return new Prestacion(this.getId(), this.getVersion(),
				this.getCodigoUnico(),
				/* this.getCodigoInterno(), */
				this.getNombre(), this.getTipoPrestacion().toObject(), this
						.getNomenclador().toObject(),
				// this.getDerechos(), this.getHonorarios(),//
				// this.getCostoDerechos(), this.getCostoHonorarios(),
				this.getIndicacion_preparacion(), this.getRestricciones(),
				this.getContraindicaciones());

	}

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoPrestacionHorus_VO getTipoPrestacion() {
		return tipoPrestacion;
	}

	public void setTipoPrestacion(TipoPrestacionHorus_VO tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	public NomencladorHorus_VO getNomenclador() {
		return nomenclador;
	}

	public void setNomenclador(NomencladorHorus_VO nomenclador) {
		this.nomenclador = nomenclador;
	}

	/*
	 * public Float getDerechos() { return derechos; }
	 * 
	 * public void setDerechos(Float derechos) { this.derechos = derechos; }
	 * 
	 * public Float getCostoDerechos() { return costoDerechos; }
	 * 
	 * public void setCostoDerechos(Float costoDerechos) { this.costoDerechos =
	 * costoDerechos; }
	 * 
	 * public Float getHonorarios() { return honorarios; }
	 * 
	 * public void setHonorarios(Float honorarios) { this.honorarios =
	 * honorarios; }
	 * 
	 * public Float getCostoHonorarios() { return costoHonorarios; }
	 * 
	 * public void setCostoHonorarios(Float costoHonorarios) {
	 * this.costoHonorarios = costoHonorarios; }
	 */
	public String getIndicacion_preparacion() {
		return indicacion_preparacion;
	}

	public void setIndicacion_preparacion(String indicacion_preparacion) {
		this.indicacion_preparacion = indicacion_preparacion;
	}

	public String getContraindicaciones() {
		return contraindicaciones;
	}

	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	public String getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(String restricciones) {
		this.restricciones = restricciones;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "(" + this.getCodigoUnico() + ") " + this.getNombre();
	}

	public Integer getCantidad() {
		return 0;
	}

	public String getClaseConsumo() {
		return Prestacion.class.getCanonicalName();
	}

	public String getDescripcion() {
		return this.toString();
	}

	public Date getFechaPrecio() {
		// TODO Auto-generated method stub
		return new Date();
	}

	public Float getIVA() {
		// TODO Auto-generated method stub
		return new Float(0);
	}

	public Long getIdConsumo() {
		return this.id;
	}

	public BigDecimal getPrecioUnitario() {
		// TODO Auto-generated method stub
		return new BigDecimal(0);
	}

	public void setCantidad(Integer cantidadNueva) {
		// TODO Auto-generated method stub

	}

	public void setDescripcion(String descripcionNueva) {
		// TODO Auto-generated method stub

	}

	public void setFechaPrecio(Date nuevaFechaPrecio) {
		// TODO Auto-generated method stub

	}

	public void setIVA(Float nuevoIVA) {
		// TODO Auto-generated method stub

	}

	public void setPrecioUnitario(BigDecimal precioNuevo) {
		// TODO Auto-generated method stub

	}

	public Servicio getServicio() {
		return null;
	}

	public void setServicio(Servicio Servicio) {
		// TODO Auto-generated method stub

	}

}
