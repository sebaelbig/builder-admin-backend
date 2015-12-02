package ar.com.builderadmin.vo.historiaClinica.antecedentes;

import ar.com.builderadmin.model.historiaClinica.antecedentes.AntecedentesNoPatologicos;

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
public class AntecedentesNoPatologicos_VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String procedencia;

	private String residencia;

	private String ocupacionActual;

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
	private String cantidadAlimentacion;

	private String frecuenciaAlimentacion;

	private String predominioAlimentacion;

	private String intolerancia;

	// HABITOS SOCIALES
	/**
	 * Consumo de alcohol
	 */
	private Boolean consumeAlcohol = false;

	private String cantidadAlcohol;

	private String frecuenciaAlcohol;

	private String predominioAlcohol;

	/**
	 * Consumo de tabaco
	 */
	private Boolean consumoTabaco = false;

	private String cantidadTabaco;

	/**
	 * Desde que edad fuma
	 */
	private String edadFuma;

	private String frecuenciaFuma;

	private String drogas;

	private String coca;

	private String sueno;

	private String horas;

	private String deportes;

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

	public AntecedentesNoPatologicos_VO(AntecedentesNoPatologicos ante) {
		this.setObject(ante);
	}

	public AntecedentesNoPatologicos_VO(AntecedentesNoPatologicos ante, int i,
			int f) {
		this.setObject(ante);
	}

	public AntecedentesNoPatologicos_VO() {
	}

	private void setObject(AntecedentesNoPatologicos a) {
		this.setId(a.getId());
		this.setVersion(a.getVersion());
		this.setAgua(a.getAgua());
		this.setCalidad(a.getCalidad());
		this.setCantidadAlcohol(a.getCantidadAlcohol());
		this.setCantidadAlimentacion(a.getCantidadAlimentacion());
		this.setCantidadTabaco(a.getCantidadTabaco());
		this.setCatarsis(a.getCatarsis());
		this.setCloacas(a.getCloacas());
		this.setCoca(a.getCoca());
		this.setConsumeAlcohol(a.getConsumeAlcohol());
		this.setConsumoTabaco(a.getConsumoTabaco());
		this.setDeportes(a.getDeportes());
		this.setDiuresis(a.getDiuresis());
		this.setDrogas(a.getDrogas());
		this.setEdadFuma(a.getEdadFuma());
		this.setEmuntorios(a.getEmuntorios());
		this.setEscolaridad(a.getEscolaridad());
		this.setEscribe(a.getEscribe());
		this.setFrecuenciaAlcohol(a.getFrecuenciaAlcohol());
		this.setFrecuenciaAlimentacion(a.getFrecuenciaAlimentacion());
		this.setFrecuenciaDeportes(a.getFrecuenciaDeportes());
		this.setFrecuenciaFuma(a.getFrecuenciaFuma());
		this.setHoras(a.getHoras());
		this.setIntolerancia(a.getIntolerancia());
		this.setLee(a.getLee());
		this.setLuz(a.getLuz());
		this.setNroConvivientes(a.getNroConvivientes());
		this.setNroHabitaciones(a.getNroHabitaciones());
		this.setOcupacionActual(a.getOcupacionActual());
		this.setOcupacionAnterior(a.getOcupacionAnterior());
		this.setPredominioAlcohol(a.getPredominioAlcohol());
		this.setPredominioAlimentacion(a.getPredominioAlimentacion());
		this.setProcedencia(a.getProcedencia());
		this.setReligion(a.getReligion());
		this.setResidencia(a.getResidencia());
		this.setSueno(a.getSueno());
		this.setVacunas(a.getVacunas());
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

	public Boolean getEscribe() {
		return escribe;
	}

	public void setEscribe(Boolean escribe) {
		this.escribe = escribe;
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

	public AntecedentesNoPatologicos toObject() {
		AntecedentesNoPatologicos resul = new AntecedentesNoPatologicos();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setAgua(this.getAgua());
		resul.setCalidad(this.getCalidad());
		resul.setCantidadAlcohol(this.getCantidadAlcohol());
		resul.setCantidadAlimentacion(this.getCantidadAlimentacion());
		resul.setCantidadTabaco(this.getCantidadTabaco());
		resul.setCatarsis(this.getCatarsis());
		resul.setCloacas(this.getCloacas());
		resul.setCoca(this.getCoca());
		resul.setConsumeAlcohol(this.getConsumeAlcohol());
		resul.setConsumoTabaco(this.getConsumoTabaco());
		resul.setDeportes(this.getDeportes());
		resul.setDiuresis(this.getDiuresis());
		resul.setDrogas(this.getDrogas());
		resul.setEdadFuma(this.getEdadFuma());
		resul.setEmuntorios(this.getEmuntorios());
		resul.setEscolaridad(this.getEscolaridad());
		resul.setEscribe(this.getEscribe());
		resul.setFrecuenciaAlcohol(this.getFrecuenciaAlcohol());
		resul.setFrecuenciaAlimentacion(this.getFrecuenciaAlimentacion());
		resul.setFrecuenciaDeportes(this.getFrecuenciaDeportes());
		resul.setFrecuenciaFuma(this.getFrecuenciaFuma());
		resul.setHoras(this.getHoras());
		resul.setIntolerancia(this.getIntolerancia());
		resul.setLee(this.getLee());
		resul.setLuz(this.getLuz());
		resul.setNroConvivientes(this.getNroConvivientes());
		resul.setNroHabitaciones(this.getNroHabitaciones());
		resul.setOcupacionActual(this.getOcupacionActual());
		resul.setOcupacionAnterior(this.getOcupacionAnterior());
		resul.setPredominioAlcohol(this.getPredominioAlcohol());
		resul.setPredominioAlimentacion(this.getPredominioAlimentacion());
		resul.setProcedencia(this.getProcedencia());
		resul.setReligion(this.getReligion());
		resul.setResidencia(this.getResidencia());
		resul.setSueno(this.getSueno());
		resul.setVacunas(this.getVacunas());

		return resul;
	}

	public AntecedentesNoPatologicos toObject(int i, int profundidadDeseada) {
		return toObject();
	}

}