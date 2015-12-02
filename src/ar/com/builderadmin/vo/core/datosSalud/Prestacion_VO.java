package ar.com.builderadmin.vo.core.datosSalud;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.builderadmin.model.core.datosSalud.Prestacion;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;

public class Prestacion_VO implements I_ValueObject<Prestacion> {
	
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String codigoUnico;
	private String codigoInterno;
	private String nombre;
	private TipoPrestacionHorus_VO tipoPrestacion;
	private NomencladorHorus_VO nomenclador;
	private String indicacion_preparacion;
	private String contraindicaciones;
	private String restricciones;

	public Prestacion_VO() {

	}

	public Prestacion_VO(Prestacion prestacion) {
		setObject(prestacion);
	}

	public Prestacion_VO(Prestacion prestacion, int profundidadActual, int profundidadBase) {
		setObject(prestacion, profundidadActual, profundidadBase);
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public String getCodigoInterno() {
		return this.codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoPrestacionHorus_VO getTipoPrestacion() {
		return this.tipoPrestacion;
	}

	public void setTipoPrestacion(TipoPrestacionHorus_VO tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	public NomencladorHorus_VO getNomenclador() {
		return this.nomenclador;
	}

	public void setNomenclador(NomencladorHorus_VO nomenclador) {
		this.nomenclador = nomenclador;
	}

	public String getIndicacion_preparacion() {
		return this.indicacion_preparacion;
	}

	public void setIndicacion_preparacion(String indicacion_preparacion) {
		this.indicacion_preparacion = indicacion_preparacion;
	}

	public String getContraindicaciones() {
		return this.contraindicaciones;
	}

	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	public String getRestricciones() {
		return this.restricciones;
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
		return this.codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "(" + getCodigoUnico() + ") " + getNombre();
	}

	public Integer getCantidad() {
		return Integer.valueOf(0);
	}

	public String getClaseConsumo() {
		return Prestacion_VO.class.getCanonicalName();
	}

	public String getDescripcion() {
		return toString();
	}

	public Date getFechaPrecio() {
		return new Date();
	}

	public Float getIVA() {
		return new Float(0.0F);
	}

	public Long getIdConsumo() {
		return this.id;
	}

	public BigDecimal getPrecioUnitario() {
		return new BigDecimal(0);
	}

	public void setCantidad(Integer cantidadNueva) {
	}

	public void setDescripcion(String descripcionNueva) {
	}

	public void setFechaPrecio(Date nuevaFechaPrecio) {
	}

	public void setIVA(Float nuevoIVA) {
	}

	public void setPrecioUnitario(BigDecimal precioNuevo) {
	}

	public Servicio_VO getServicio() {
		return null;
	}

	public void setServicio(Servicio_VO Servicio) {
	}
	
	@Override
	public Prestacion toObject() {
		return new Prestacion(
			this.getId(), 
			this.getVersion(), 
			this.getCodigoUnico(), 
			/*this.getCodigoInterno(),*/ 
			this.getNombre(),
			this.getTipoPrestacion().toObject(), 
			this.getNomenclador().toObject(), 
			// this.getDerechos(), this.getHonorarios(),// this.getCostoDerechos(), this.getCostoHonorarios(),
			this.getIndicacion_preparacion(), 
			this.getRestricciones(), 
			this.getContraindicaciones());

	}
	
	@Override
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

		this.setNomenclador( prestacion.getNomenclador().toValueObject() );
		this.setTipoPrestacion(prestacion.getTipoPrestacion().toValueObject());
	}

	@Override
	public void setObject(Prestacion prestacion, int profundidadActual, int profundidadDeseada) {
		this.setId(prestacion.getId());
		this.setCodigoUnico(prestacion.getCodigoUnico());
		this.setContraindicaciones(prestacion.getContraindicaciones());
		this.setNombre(prestacion.getNombre());
		this.setIndicacion_preparacion(prestacion.getIndicacion_preparacion());
		this.setRestricciones(prestacion.getRestricciones());
		this.setVersion(prestacion.getVersion());

		if (profundidadActual<profundidadDeseada){
			this.setNomenclador( prestacion.getNomenclador().toValueObject(profundidadActual+1, profundidadDeseada) );
			this.setTipoPrestacion(prestacion.getTipoPrestacion().toValueObject(profundidadActual+1, profundidadDeseada));
		}
	}
	
}