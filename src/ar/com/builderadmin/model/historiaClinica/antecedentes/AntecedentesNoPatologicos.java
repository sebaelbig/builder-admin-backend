package ar.com.builderadmin.model.historiaClinica.antecedentes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.historiaClinica.antecedentes.AntecedentesNoPatologicos_VO;

/**
 * Los Antecedentes son una parte muy importante dentro de la ficha cl�nica, ya
 * que proporcionan informaci�n acerca de factores de riesgo o factores que
 * agraven la situaci�n de la paciente. Entre ellos se incluye cualquier
 * enfermedad que padezcan de manera cr�nica, procedimientos quir�rgicos, alg�n
 * traumatismo, alguna predisposici�n gen�tica por antecedentes familiares y
 * alergias que la paciente pueda padecer. No es posible olvidarse de los
 * antecedentes pues estos son determinantes en las patolog�as que aquejan a las
 * pacientes que nos consultan.
 * 
 * Estos se dividen en antecedentes patol�gicos y No patologicos: Patol�gicos:
 * antecedentes familiares, m�dicos, quir�rugicos, traum�ticos, al�rgicos,
 * gineco-obst�tricos vicios y man�as
 * 
 * No patol�gicos: atenci�n prenatal, natal, posnatal, crecimiento y desarrollo,
 * inmunizaciones, alimentaci�n, h�bitos .
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table(name = "antecedente_no_patologico")
public class AntecedentesNoPatologicos {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_antecedentes_no_patologico", name = "seq_antecedentes_no_patologico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_antecedentes_no_patologico")
	private Long id;
	@Version
	private Integer version;

	private String procedencia;

	private String residencia;

	@Column(name = "ocupacion_actual")
	private String ocupacionActual;

	@Column(name = "ocupacion_anterior")
	private String ocupacionAnterior;

	private String escolaridad;

	private Boolean lee = false;

	private Boolean escribe = false;

	// VIVIENDA
	private Boolean luz = false;

	private Boolean agua = false;

	private Boolean cloacas = false;

	/**
	 * No de persona con las que vive
	 */
	private Integer nroConvivientes;

	/**
	 * No de habitaciones
	 */
	private Integer nroHabitaciones;

	// ALIMENTACION
	private String calidad;

	/**
	 * (Carencial, mon�tona, abundante, Excesiva.)
	 */
	@Column(name = "cantidad_alimentacion")
	private String cantidadAlimentacion;

	@Column(name = "frecuencia_alimentacion")
	private String frecuenciaAlimentacion;

	@Column(name = "predominio_alimentacion")
	private String predominioAlimentacion;

	private String intolerancia;

	// HABITOS SOCIALES
	/**
	 * Consumo de alcohol
	 */
	@Column(name = "consume_alcohol")
	private Boolean consumeAlcohol = false;

	@Column(name = "cantidad_alcohol")
	private String cantidadAlcohol;

	@Column(name = "frecuencia_alcohol")
	private String frecuenciaAlcohol;

	@Column(name = "predominio_alcohol")
	private String predominioAlcohol;

	/**
	 * Consumo de tabaco
	 */
	@Column(name = "consumo_tabaco")
	private Boolean consumoTabaco = false;

	@Column(name = "cantidad_tabaco")
	private String cantidadTabaco;

	/**
	 * Desde que edad fuma
	 */
	@Column(name = "edad_fuma")
	private String edadFuma;

	@Column(name = "frecuenciaFuma")
	private String frecuenciaFuma;

	private String drogas;

	private String coca;

	private String sueno;

	private String horas;

	private String deportes;

	@Column(name = "frecuencia_deportes")
	private String frecuenciaDeportes;

	private String emuntorios;

	private String catarsis;

	private String diuresis;

	private String religion;

	/**
	 * (1)BCG Tuberculosis, (2)HB hepatitis B, (3)DPT-HB-Hib (Pentavalente)
	 * Diferia, Tetanos, pertussis, Hep B, Haemophilus influenzae b, (4)DPT-Hib:
	 * (Cuadruple) diferia, tetanos, pertussis, Haemophilus influenza b.,
	 * (5)OPV: (Sabin): vacuna antipoliomielitica oral, (6)SRP: (Triple viral):
	 * Sarampion, rubeola, parotiditis. (7)HA (Hepatitis A) (8)DPT: (Triple
	 * bacteriana): difteria, tetanos, pertussis (9)dTap: (Triple bacteriana
	 * Acelular) (10)dT (Doble bacteriana): difteria, tetanos (11)SR.:(Doble
	 * viral): Sarampion, rubeola (12)FA: Fiebre Amarilla, una dosis para
	 * residentes o viajeros a zonas de riesgo (13)FHA: fiebre hemorragica
	 * argentina. Una dosis para residentes o viajeros a zonas de riesgo
	 * 
	 */
	private String vacunas;

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

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getOcupacionActual() {
		return ocupacionActual;
	}

	public void setOcupacionActual(String ocupacionActual) {
		this.ocupacionActual = ocupacionActual;
	}

	public String getOcupacionAnterior() {
		return ocupacionAnterior;
	}

	public void setOcupacionAnterior(String ocupacionAnterior) {
		this.ocupacionAnterior = ocupacionAnterior;
	}

	public String getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public Boolean getLee() {
		return lee;
	}

	public void setLee(Boolean lee) {
		this.lee = lee;
	}

	public Boolean getLuz() {
		return luz;
	}

	public void setLuz(Boolean luz) {
		this.luz = luz;
	}

	public Boolean getAgua() {
		return agua;
	}

	public void setAgua(Boolean agua) {
		this.agua = agua;
	}

	public Boolean getCloacas() {
		return cloacas;
	}

	public void setCloacas(Boolean cloacas) {
		this.cloacas = cloacas;
	}

	public Integer getNroConvivientes() {
		return nroConvivientes;
	}

	public void setNroConvivientes(Integer nroConvivientes) {
		this.nroConvivientes = nroConvivientes;
	}

	public Integer getNroHabitaciones() {
		return nroHabitaciones;
	}

	public void setNroHabitaciones(Integer nroHabitaciones) {
		this.nroHabitaciones = nroHabitaciones;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public String getCantidadAlimentacion() {
		return cantidadAlimentacion;
	}

	public void setCantidadAlimentacion(String cantidadAlimentacion) {
		this.cantidadAlimentacion = cantidadAlimentacion;
	}

	public String getFrecuenciaAlimentacion() {
		return frecuenciaAlimentacion;
	}

	public void setFrecuenciaAlimentacion(String frecuenciaAlimentacion) {
		this.frecuenciaAlimentacion = frecuenciaAlimentacion;
	}

	public String getPredominioAlimentacion() {
		return predominioAlimentacion;
	}

	public void setPredominioAlimentacion(String predominioAlimentacion) {
		this.predominioAlimentacion = predominioAlimentacion;
	}

	public String getIntolerancia() {
		return intolerancia;
	}

	public void setIntolerancia(String intolerancia) {
		this.intolerancia = intolerancia;
	}

	public Boolean getConsumeAlcohol() {
		return consumeAlcohol;
	}

	public void setConsumeAlcohol(Boolean consumeAlcohol) {
		this.consumeAlcohol = consumeAlcohol;
	}

	public String getCantidadAlcohol() {
		return cantidadAlcohol;
	}

	public void setCantidadAlcohol(String cantidadAlcohol) {
		this.cantidadAlcohol = cantidadAlcohol;
	}

	public String getFrecuenciaAlcohol() {
		return frecuenciaAlcohol;
	}

	public void setFrecuenciaAlcohol(String frecuenciaAlcohol) {
		this.frecuenciaAlcohol = frecuenciaAlcohol;
	}

	public String getPredominioAlcohol() {
		return predominioAlcohol;
	}

	public void setPredominioAlcohol(String predominioAlcohol) {
		this.predominioAlcohol = predominioAlcohol;
	}

	public Boolean getConsumoTabaco() {
		return consumoTabaco;
	}

	public void setConsumoTabaco(Boolean consumoTabaco) {
		this.consumoTabaco = consumoTabaco;
	}

	public String getCantidadTabaco() {
		return cantidadTabaco;
	}

	public void setCantidadTabaco(String cantidadTabaco) {
		this.cantidadTabaco = cantidadTabaco;
	}

	public String getEdadFuma() {
		return edadFuma;
	}

	public void setEdadFuma(String edadFuma) {
		this.edadFuma = edadFuma;
	}

	public String getFrecuenciaFuma() {
		return frecuenciaFuma;
	}

	public void setFrecuenciaFuma(String frecuenciaFuma) {
		this.frecuenciaFuma = frecuenciaFuma;
	}

	public String getDrogas() {
		return drogas;
	}

	public void setDrogas(String drogas) {
		this.drogas = drogas;
	}

	public String getCoca() {
		return coca;
	}

	public void setCoca(String coca) {
		this.coca = coca;
	}

	public String getSueno() {
		return sueno;
	}

	public void setSueno(String sueno) {
		this.sueno = sueno;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String getDeportes() {
		return deportes;
	}

	public void setDeportes(String deportes) {
		this.deportes = deportes;
	}

	public String getFrecuenciaDeportes() {
		return frecuenciaDeportes;
	}

	public void setFrecuenciaDeportes(String frecuenciaDeportes) {
		this.frecuenciaDeportes = frecuenciaDeportes;
	}

	public String getEmuntorios() {
		return emuntorios;
	}

	public void setEmuntorios(String emuntorios) {
		this.emuntorios = emuntorios;
	}

	public String getCatarsis() {
		return catarsis;
	}

	public void setCatarsis(String catarsis) {
		this.catarsis = catarsis;
	}

	public String getDiuresis() {
		return diuresis;
	}

	public void setDiuresis(String diuresis) {
		this.diuresis = diuresis;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getVacunas() {
		return vacunas;
	}

	public void setVacunas(String vacunas) {
		this.vacunas = vacunas;
	}

	public AntecedentesNoPatologicos_VO toValueObject() {
		return new AntecedentesNoPatologicos_VO(this);
	}

	public AntecedentesNoPatologicos_VO toValueObject(int profAct,
			int profDeseada) {
		return new AntecedentesNoPatologicos_VO(this, profAct, profDeseada);
	}

	public Boolean getEscribe() {
		return escribe;
	}

	public void setEscribe(Boolean escribe) {
		this.escribe = escribe;
	}

}