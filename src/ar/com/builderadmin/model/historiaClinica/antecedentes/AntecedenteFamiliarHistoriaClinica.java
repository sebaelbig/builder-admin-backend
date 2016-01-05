package ar.com.builderadmin.model.historiaClinica.antecedentes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.datosSalud.diagnosticos.Diagnostico;
import ar.com.builderadmin.vo.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinica_VO;

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
@Entity
@Table( name = "antecedente_familiar_historia_clinica")
@SequenceGenerator( sequenceName = "seq_antecedente_familiar_historia_clinica", name = "seq_antecedente_familiar_historia_clinica", allocationSize = 1)
public class AntecedenteFamiliarHistoriaClinica {

	private Boolean borrado = false;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_antecedente_familiar_historia_clinica")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Parentezco: Abuelo Paterno, Abuelo Materno, Abuela Paterno, Abuela
	 * Materno, Padre, Madre, Hermano/a
	 */
	private String parentezco;

	/**
	 * Apellido del parentezco
	 */
	private String apellido;

	/**
	 * Nombre del parentezco
	 */
	private String nombre;

	/**
	 * Variable que indica si esta vivo o no el familiar
	 */
	private Boolean vive;

	/**
	 * Razon de fallecimiento
	 */
	@Column(name = "razon_fallecimiento")
	private String razonFallecimiento;

	private String edad;

	/**
	 * Descripcion del antecedente
	 */
	@Column(columnDefinition = "text")
	private String descripcion;

	/**
	 * Diagnosticos del familiar
	 */
	@ManyToMany
	@JoinTable(name = "antecedente_familiar_historia_clinica_diagnostico", joinColumns = @JoinColumn(name = "id_antecedente"), inverseJoinColumns = @JoinColumn(name = "id_diagnostico", unique = false), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_antecedente", "id_diagnostico" }))
	private List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();

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

	public String getParentezco() {
		return parentezco;
	}

	public void setParentezco(String parentezco) {
		this.parentezco = parentezco;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico> diagnostico) {
		this.diagnosticos = diagnostico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getVive() {
		return vive;
	}

	public void setVive(Boolean vive) {
		this.vive = vive;
	}

	public String getRazonFallecimiento() {
		return razonFallecimiento;
	}

	public void setRazonFallecimiento(String razonFallecimiento) {
		this.razonFallecimiento = razonFallecimiento;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public AntecedenteFamiliarHistoriaClinica_VO toValueObject() {
		return new AntecedenteFamiliarHistoriaClinica_VO(this);
	}

	public AntecedenteFamiliarHistoriaClinica_VO toValueObject(int profAct,
			int profDeseada) {
		return new AntecedenteFamiliarHistoriaClinica_VO(this, profAct,
				profDeseada);
	}
	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}