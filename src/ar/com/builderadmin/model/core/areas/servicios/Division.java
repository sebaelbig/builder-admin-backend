package ar.com.builderadmin.model.core.areas.servicios;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
//@Entity 
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(
		name="tipo_division",
		discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("division_base")
public class Division implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_division", name = "seq_division")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_division") 
	private Long id;
	
	@Version
	private Integer version;
	
	private boolean fueraDeServicio;
	
	private String motivo;
	
	/**
	 * Numero de division
	 */
	private Integer numero;
	
	/**
	 * Ubicacion de division
	 */
	private String ubicacion;
	
	/**
	 *  Piso de division
	 */
	private String piso;
	
	/**
	 *  Descripcion de division
	 */
	@Column(length=300)
	private String descricpion;
	
	/**
	 *  costo de alquiler de un division
	 */
	private BigDecimal costo;
	
	/**
	 *  costo de alquiler de un division para un accionista
	 */
	@Column(name="costo_accionista")
	private BigDecimal costoAccionista;
	
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;
	
	//Constructores
	public Division(){
		setFueraDeServicio(false);
	}
	
	//Gettters & Setters
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

	public String getDescricpion() {
		return descricpion;
	}

	public void setDescricpion(String descricpion) {
		this.descricpion = descricpion;
	}

	
	@Override
	public String toString() {
		return "N�mero: "+getNumero()+", Piso: "+getPiso()+", Ubicaci�n: "+getUbicacion();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Division) {
			Division o = (Division) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

//	public Division_VO toValueObject(){
//		return new Division_VO(this);
//	}
//	
//	public Division_VO toValueObject(int profundidadActual, int profundidadDeseada) {
//		return new Division_VO(this, profundidadActual, profundidadDeseada);
//	}

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

	public boolean isFueraDeServicio() {
		return fueraDeServicio;
	}

	public void setFueraDeServicio(boolean fueraDeServicio) {
		this.fueraDeServicio = fueraDeServicio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	
}