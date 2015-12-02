package ar.com.builderadmin.model.core.datosSalud;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.datosSalud.Prestacion_VO;

/**
 * @author svalle
 */

//@Entity
@Table( name = "prestacion")
public class Prestacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_prestacion", name = "seq_prestacion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prestacion")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Codigo unico de la prestacion
	 */
	@Column(name = "codigo_unico", length = 12)
	private String codigoUnico;

	/**
	 * Codigo interno de la aplicacion
	 */
	/*
	 * @Column(name="codigo_interno", length=12) private String codigoInterno;
	 */
	/**
	 * Nombre de la prestacion
	 */
	private String nombre;

	/**
	 * Tipo de prestacion de la prestacion
	 */
	@ManyToOne
	@JoinColumn(name = "id_tipo_prestacion")
	private TipoPrestacionHorus tipoPrestacion;

	@ManyToOne
	@JoinColumn(name = "id_nomenclador")
	private NomencladorHorus nomenclador;

	public Prestacion() {
	}

	/*
	 * derechos de prestacion
	 */
	private Float derechos;

	@Column(name = "costo_derechos")
	private Float costoDerechos;

	/*
	 * honorarios de prestacion
	 */
	private Float honorarios;

	@Column(name = "costo_honorarios")
	private Float costoHonorarios;

	/*
	 * Indicaciones o preparacion que requiere la prestacion
	 */
	private String indicacion_preparacion;

	/*
	 * contraindicaciones de la prestacion
	 */
	private String contraindicaciones;

	/*
	 * restricciones de la prestacion
	 */
	private String restricciones;

	// private float utilidadInterna;

	public Prestacion(Long id, Integer version, String codigoPrestacion,
	/* String codigoInterno, */
	String nombre, TipoPrestacionHorus tipoPrestacion,
			NomencladorHorus nomenclador,
			/*
			 * Float derechos, Float honorarios, Float costoDerechos, Float
			 * costoHonorarios,
			 */
			String indicacion_preparacion, String restricciones,
			String contraindicaciones) {
		this.id = id;
		this.version = version;
		this.codigoUnico = codigoPrestacion;
		// this.codigoInterno = codigoInterno;
		this.nombre = nombre;
		this.tipoPrestacion = tipoPrestacion;
		this.nomenclador = nomenclador;
		// this.derechos = derechos;
		// this.honorarios = honorarios;
		// this.costoDerechos = costoDerechos;
		// this.costoHonorarios = costoHonorarios;
		this.indicacion_preparacion = indicacion_preparacion;
		this.restricciones = restricciones;
		this.contraindicaciones = contraindicaciones;

	}

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

	/*
	 * public String getCodigoInterno() { return codigoInterno; }
	 * 
	 * public void setCodigoInterno(String codigoInterno) { this.codigoInterno =
	 * codigoInterno; }
	 */

	public TipoPrestacionHorus getTipoPrestacion() {
		return tipoPrestacion;
	}

	public void setTipoPrestacion(TipoPrestacionHorus tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	public NomencladorHorus getNomenclador() {
		return nomenclador;
	}

	public void setNomenclador(NomencladorHorus nomenclador) {
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

	/*
	 * public float getUtilidadInterna() { return utilidadInterna; }
	 * 
	 * public void setUtilidadInterna(float utilidadInterna) {
	 * this.utilidadInterna = utilidadInterna; }
	 */

	@Override
	public String toString() {
		return "(" + this.getCodigoUnico() + ") " + this.getNombre();
	}

	public Prestacion_VO toValueObject() {
		return new Prestacion_VO(this);
	}

	public Prestacion_VO toValueObject(int profundidadActual,
			int profundidadBase) {
		return new Prestacion_VO(this, profundidadActual, profundidadBase);
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getDerechos() {
		return derechos;
	}

	public void setDerechos(Float derechos) {
		this.derechos = derechos;
	}

	public Float getCostoDerechos() {
		return costoDerechos;
	}

	public void setCostoDerechos(Float costoDerechos) {
		this.costoDerechos = costoDerechos;
	}

	public Float getHonorarios() {
		return honorarios;
	}

	public void setHonorarios(Float honorarios) {
		this.honorarios = honorarios;
	}

	public Float getCostoHonorarios() {
		return costoHonorarios;
	}

	public void setCostoHonorarios(Float costoHonorarios) {
		this.costoHonorarios = costoHonorarios;
	}

	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

}
