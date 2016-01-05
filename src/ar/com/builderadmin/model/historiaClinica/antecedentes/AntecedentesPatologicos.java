package ar.com.builderadmin.model.historiaClinica.antecedentes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.historiaClinica.antecedentes.AntecedentesPatologicos_VO;

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
@Entity @Table( name = "antecedente_patologico")
public class AntecedentesPatologicos {

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_antecedentes_patologico", name = "seq_antecedentes_patologico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_antecedentes_patologico")
	private Long id;
	@Version
	private Integer version;

	/**
	 * Enfermedades de la infancia
	 */
	@Column(name = "enfermedades_de_infancia")
	private String enfermedadesDeInfancia;

	/**
	 * Enfermedades no quir�rgicas
	 */
	@Column(name = "enfermedades_no_quirurgicas")
	private String enfermedadesNoQuirurgicas;

	/**
	 * Enfermedades quir�rgicas
	 */
	@Column(name = "enfermedades_quirurgicas")
	private String enfermedadesQuirurgicas;

	private String accidentes;

	private String traumatismos;

	private String fracturas;

	/**
	 * Transfusiones de sangre
	 */
	private String transfuciones;

	private String alergias;

	private String incapacidades;

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

	public String getEnfermedadesDeInfancia() {
		return enfermedadesDeInfancia;
	}

	public void setEnfermedadesDeInfancia(String enfermedadesDeInfancia) {
		this.enfermedadesDeInfancia = enfermedadesDeInfancia;
	}

	public String getEnfermedadesNoQuirurgicas() {
		return enfermedadesNoQuirurgicas;
	}

	public void setEnfermedadesNoQuirurgicas(String enfermedadesNoQuirurgicas) {
		this.enfermedadesNoQuirurgicas = enfermedadesNoQuirurgicas;
	}

	public String getEnfermedadesQuirurgicas() {
		return enfermedadesQuirurgicas;
	}

	public void setEnfermedadesQuirurgicas(String enfermedadesQuirurgicas) {
		this.enfermedadesQuirurgicas = enfermedadesQuirurgicas;
	}

	public String getAccidentes() {
		return accidentes;
	}

	public void setAccidentes(String accidentes) {
		this.accidentes = accidentes;
	}

	public String getTraumatismos() {
		return traumatismos;
	}

	public void setTraumatismos(String traumatismos) {
		this.traumatismos = traumatismos;
	}

	public String getFracturas() {
		return fracturas;
	}

	public void setFracturas(String fracturas) {
		this.fracturas = fracturas;
	}

	public String getTransfuciones() {
		return transfuciones;
	}

	public void setTransfuciones(String transfuciones) {
		this.transfuciones = transfuciones;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getIncapacidades() {
		return incapacidades;
	}

	public void setIncapacidades(String incapacidades) {
		this.incapacidades = incapacidades;
	}

	public AntecedentesPatologicos_VO toValueObject() {
		return new AntecedentesPatologicos_VO(this);
	}

	public AntecedentesPatologicos_VO toValueObject(int profAct, int profDeseada) {
		return new AntecedentesPatologicos_VO(this, profAct, profDeseada);
	}

}