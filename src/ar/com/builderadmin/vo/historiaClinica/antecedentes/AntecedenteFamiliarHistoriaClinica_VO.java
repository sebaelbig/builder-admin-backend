package ar.com.builderadmin.vo.historiaClinica.antecedentes;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.model.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinica;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.datosSalud.diagnosticos.Diagnostico_VO;

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
public class AntecedenteFamiliarHistoriaClinica_VO implements
		I_ValueObject<AntecedenteFamiliarHistoriaClinica> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;@Override
	public Boolean getBorrado(){return this.borrado;}@Override
	public void setBorrado(Boolean b){this.borrado=b;}

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
	private Boolean vive = false;

	/**
	 * Razon de fallecimiento
	 */
	private String razonFallecimiento;

	private String edad;

	/**
	 * Descripcion del antecedente
	 */
	private String descripcion;

	/**
	 * Diagnosticos del familiar
	 */
	private List<Diagnostico_VO> diagnosticos = new ArrayList<Diagnostico_VO>();

	public AntecedenteFamiliarHistoriaClinica_VO(
			AntecedenteFamiliarHistoriaClinica ante) {
		this.setObject(ante);
	}

	public AntecedenteFamiliarHistoriaClinica_VO(
			AntecedenteFamiliarHistoriaClinica ante, int a, int d) {
		this.setObject(ante, a, d);
	}

	public AntecedenteFamiliarHistoriaClinica_VO() {
	}

	@Override
	public void setObject(AntecedenteFamiliarHistoriaClinica a) {
		this.setId(a.getId());
		this.setVersion(a.getVersion());

		this.setApellido(a.getApellido());
		this.setDescripcion(a.getDescripcion());
		this.setEdad(a.getEdad());
		this.setNombre(a.getNombre());
		this.setParentezco(a.getParentezco());
		this.setRazonFallecimiento(a.getRazonFallecimiento());
		this.setVive(a.getVive());

//		this.setDiagnosticos(new ArrayList<Diagnostico_VO>());
//		for (Diagnostico diag : a.getDiagnosticos()) {
//			this.getDiagnosticos().add(diag.toValueObject());
//		}
	}

	@Override
	public void setObject(AntecedenteFamiliarHistoriaClinica a,
			int profundidadActual, int profundidadDeseada) {
		this.setId(a.getId());
		this.setVersion(a.getVersion());

		this.setApellido(a.getApellido());
		this.setDescripcion(a.getDescripcion());
		this.setEdad(a.getEdad());
		this.setNombre(a.getNombre());
		this.setParentezco(a.getParentezco());
		this.setRazonFallecimiento(a.getRazonFallecimiento());
		this.setVive(a.getVive());

		this.setDiagnosticos(new ArrayList<Diagnostico_VO>());
//		if (profundidadActual < profundidadDeseada) {
//			for (Diagnostico diag : a.getDiagnosticos()) {
//				this.getDiagnosticos().add(diag.toValueObject());
//			}
//		}

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
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

	public List<Diagnostico_VO> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico_VO> diagnostico) {
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

	@Override
	public AntecedenteFamiliarHistoriaClinica toObject() {
		AntecedenteFamiliarHistoriaClinica resul = new AntecedenteFamiliarHistoriaClinica();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setApellido(this.getApellido());
		resul.setDescripcion(this.getDescripcion());
		resul.setEdad(this.getEdad());
		resul.setNombre(this.getNombre());
		resul.setParentezco(this.getParentezco());
		resul.setRazonFallecimiento(this.getRazonFallecimiento());
		resul.setVive(this.getVive());

//		resul.setDiagnosticos(new ArrayList<Diagnostico>());
//		for (Diagnostico_VO diag : this.getDiagnosticos()) {
//			resul.getDiagnosticos().add(diag.toObject());
//		}

		return resul;
	}

	public AntecedenteFamiliarHistoriaClinica toObject(int i,
			int profundidadDeseada) {
		return toObject();
	}

}