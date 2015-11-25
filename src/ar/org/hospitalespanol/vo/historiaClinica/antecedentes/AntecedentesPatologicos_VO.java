package ar.org.hospitalespanol.vo.historiaClinica.antecedentes;

import ar.org.hospitalespanol.model.historiaClinica.antecedentes.AntecedentesPatologicos;
import ar.org.hospitalespanol.vo.I_ValueObject;

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
public class AntecedentesPatologicos_VO implements
		I_ValueObject<AntecedentesPatologicos> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	/**
	 * Enfermedades de la infancia
	 */
	private String enfermedadesDeInfancia;

	/**
	 * Enfermedades no quir�rgicas
	 */
	private String enfermedadesNoQuirurgicas;

	/**
	 * Enfermedades quir�rgicas
	 */
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

	public AntecedentesPatologicos_VO(AntecedentesPatologicos ante) {
		this.setObject(ante);
	}

	public AntecedentesPatologicos_VO(AntecedentesPatologicos ante, int i, int f) {
		this.setObject(ante);
	}

	public AntecedentesPatologicos_VO() {
	}

	@Override
	public void setObject(AntecedentesPatologicos ante) {
		this.setId(ante.getId());
		this.setVersion(ante.getVersion());
		this.setAccidentes(ante.getAccidentes());
		this.setAlergias(ante.getAlergias());
		this.setEnfermedadesDeInfancia(ante.getEnfermedadesDeInfancia());
		this.setEnfermedadesNoQuirurgicas(ante.getEnfermedadesNoQuirurgicas());
		this.setEnfermedadesQuirurgicas(ante.getEnfermedadesQuirurgicas());
		this.setFracturas(ante.getFracturas());
		this.setIncapacidades(ante.getIncapacidades());
		this.setTransfuciones(ante.getTransfuciones());
		this.setTraumatismos(ante.getTraumatismos());

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

	@Override
	public AntecedentesPatologicos toObject() {
		AntecedentesPatologicos resul = new AntecedentesPatologicos();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setAccidentes(this.getAccidentes());
		resul.setAlergias(this.getAlergias());
		resul.setEnfermedadesDeInfancia(this.getEnfermedadesDeInfancia());
		resul.setEnfermedadesNoQuirurgicas(this.getEnfermedadesNoQuirurgicas());
		resul.setEnfermedadesQuirurgicas(this.getEnfermedadesQuirurgicas());
		resul.setFracturas(this.getFracturas());
		resul.setIncapacidades(this.getIncapacidades());
		resul.setTransfuciones(this.getTransfuciones());
		resul.setTraumatismos(this.getTraumatismos());

		return resul;
	}

	public AntecedentesPatologicos toObject(int i, int profundidadDeseada) {
		return toObject();
	}

	@Override
	public void setObject(AntecedentesPatologicos paramT,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(paramT);
	}

}