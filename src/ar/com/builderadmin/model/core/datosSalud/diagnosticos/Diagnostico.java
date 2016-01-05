package ar.com.builderadmin.model.core.datosSalud.diagnosticos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.datosSalud.diagnosticos.Diagnostico_VO;

/**
 * @author svalle
 */

@Entity @Table( name = "diagnostico")
public class Diagnostico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_diagnostico", name = "seq_diagnostico", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_diagnostico")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Codigo unico del diagnostico
	 */
	@Column(name = "codigo_unico", length = 12)
	private String codigoUnico;

	/******************************************************************************
	 * La Clasificaci�n Internacional de Atenci�n Primaria es una taxonom�a de
	 * los t�rminos y expresiones utilizadas habitualmente en medicina general.
	 * Recoge los motivos (o razones) de consulta, los problemas de salud y el
	 * proceso de atenci�n. Es un tipo de clasificaci�n de terminolog�a m�dica
	 * de �mbito internacional
	 */
	/*********************** Correspondiente al standar CIAP **********************/
	/******************************************************************************/
	private Boolean ciap_2 = false;

	public static final String CIAP = "Clasificaci�n Internacional de la Atenci�n Primaria";

	public static final String SIGNOS_Y_SINTOMAS = "Signos y s�ntomas";
	public static final String INFECCIONES = "Infecciones";
	public static final String NEOPLASIAS = "Neoplasias";
	public static final String LESIONES = "Lesiones";
	public static final String ANOMALIAS_CONGENITAS = "Anomal�as cong�nitas";
	public static final String OTROS_DIAGNOSTICOS = "Otros diagn�sticos";

	/**
	 * El primer d�gito es una letra que representa un aparato o sistema
	 * org�nico, y constituyen los 17 "cap�tulos" de esta clasificaci�n.
	 */
	@Column(name = "aparato_sist_organico")
	private String aparatoSistemaOrganico;

	/**
	 * El segundo y tercer d�gitos los forman n�meros, denominados
	 * "componentes", que se relacionan espec�fica o inespec�ficamente con:
	 * signos o s�ntomas; procedimientos administrativos, diagn�sticos,
	 * preventivos o terap�uticos; resultados de pruebas complementarias;
	 * derivaciones, seguimiento y otras razones de consulta; o enfermedades y
	 * problemas de salud.
	 */
	private String componentes;
	@Column(name = "codigo_ciap", length = 3)
	private String codigoCIAP;

	@Column(name = "descripcion_ciap")
	private String descripcionCIAP;

	@Column(name = "resumen_ciap")
	private String resumenCIAP;

	@Column(name = "terminos_incluidos")
	private String terminosIncluidos;

	@Column(name = "terminos_excluidos")
	private String terminosExcluidos;

	@Column(name = "criterios_de_inclusion")
	private String criteriosDeInclusion;

	private String consideraciones;

	@Column(name = "nota_ciap")
	private String notaCIAP;

	@Column(length = 10)
	private String icpc2 = "verdadero";

	@Column(name = "correspondencia_ciap_cie")
	private String correspondencia_CIAP_CIE;

	private String componente;

	@Column(name = "nro_componente")
	private String nroComponente;

	/******************************************************************************
	 * La CIE-10 es el acr�nimo de la Clasificaci�n internacional de
	 * enfermedades, d�cima versi�n correspondiente a la versi�n en espa�ol de
	 * la (en ingl�s) ICD, siglas de International Statistical Classification of
	 * Diseases and Related Health Problems) y determina la clasificaci�n y
	 * codificaci�n de las enfermedades y una amplia variedad de signos,
	 * s�ntomas, hallazgos anormales, denuncias, circunstancias sociales y
	 * causas externas de da�os y/o enfermedad. 1
	 */
	/*********************** Correspondiente al standar CIE ***********************/
	/******************************************************************************/
	private Boolean cie_10 = false;

	public static final String CIE = "Clasificaci�n estad�stica internacional de enfermedades y otros problemas de salud";

	/**
	 * ada condici�n de salud puede ser asignada a una categor�a y recibir un
	 * c�digo de hasta seis caracteres de longitud (en formato de X00.00).
	 */
	@Column(name = "codigo_cie", length = 6)
	private String codigoCIE;

	/**
	 * Expresado en nros romanos
	 */
	private String capituloCIE;

	@Column(name = "nro_grupo_principal")
	private String nroGrupoPrincipal;

	@Column(name = "nro_grupo_menor")
	private String nroGrupoMenor;

	@Column(name = "descripcion_cie")
	private String descripcionCIE;

	public Diagnostico() {
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

	@Override
	public String toString() {
		return "(";
	}

	public Diagnostico_VO toValueObject() {
		return new Diagnostico_VO(this);
	}

	public Diagnostico_VO toValueObject(int profundidadActual,
			int profundidadBase) {
		return new Diagnostico_VO(this, profundidadActual, profundidadBase);
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}

	public Boolean getCiap_2() {
		return ciap_2;
	}

	public void setCiap_2(Boolean ciap_2) {
		this.ciap_2 = ciap_2;
	}

	public String getAparatoSistemaOrganico() {
		return aparatoSistemaOrganico;
	}

	public void setAparatoSistemaOrganico(String aparatoSistemaOrganico) {
		this.aparatoSistemaOrganico = aparatoSistemaOrganico;
	}

	public String getComponentes() {
		return componentes;
	}

	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}

	public String getCodigoCIAP() {
		return codigoCIAP;
	}

	public void setCodigoCIAP(String codigoCIAP) {
		this.codigoCIAP = codigoCIAP;
	}

	public String getDescripcionCIAP() {
		return descripcionCIAP;
	}

	public void setDescripcionCIAP(String descripcionCIAP) {
		this.descripcionCIAP = descripcionCIAP;
	}

	public String getResumenCIAP() {
		return resumenCIAP;
	}

	public void setResumenCIAP(String resumenCIAP) {
		this.resumenCIAP = resumenCIAP;
	}

	public String getTerminosIncluidos() {
		return terminosIncluidos;
	}

	public void setTerminosIncluidos(String terminosIncluidos) {
		this.terminosIncluidos = terminosIncluidos;
	}

	public String getTerminosExcluidos() {
		return terminosExcluidos;
	}

	public void setTerminosExcluidos(String terminosExcluidos) {
		this.terminosExcluidos = terminosExcluidos;
	}

	public String getCriteriosDeInclusion() {
		return criteriosDeInclusion;
	}

	public void setCriteriosDeInclusion(String criteriosDeInclusion) {
		this.criteriosDeInclusion = criteriosDeInclusion;
	}

	public String getConsideraciones() {
		return consideraciones;
	}

	public void setConsideraciones(String consideraciones) {
		this.consideraciones = consideraciones;
	}

	public String getNotaCIAP() {
		return notaCIAP;
	}

	public void setNotaCIAP(String notaCIAP) {
		this.notaCIAP = notaCIAP;
	}

	public String getIcpc2() {
		return icpc2;
	}

	public void setIcpc2(String icpc2) {
		this.icpc2 = icpc2;
	}

	public String getCorrespondencia_CIAP_CIE() {
		return correspondencia_CIAP_CIE;
	}

	public void setCorrespondencia_CIAP_CIE(String correspondencia_CIAP_CIE) {
		this.correspondencia_CIAP_CIE = correspondencia_CIAP_CIE;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getNroComponente() {
		return nroComponente;
	}

	public void setNroComponente(String nroComponente) {
		this.nroComponente = nroComponente;
	}

	public Boolean getCie_10() {
		return cie_10;
	}

	public void setCie_10(Boolean cie_10) {
		this.cie_10 = cie_10;
	}

	public String getCodigoCIE() {
		return codigoCIE;
	}

	public void setCodigoCIE(String codigoCIE) {
		this.codigoCIE = codigoCIE;
	}

	public String getCapituloCIE() {
		return capituloCIE;
	}

	public void setCapituloCIE(String capituloCIE) {
		this.capituloCIE = capituloCIE;
	}

	public String getNroGrupoPrincipal() {
		return nroGrupoPrincipal;
	}

	public void setNroGrupoPrincipal(String nroGrupoPrincipal) {
		this.nroGrupoPrincipal = nroGrupoPrincipal;
	}

	public String getNroGrupoMenor() {
		return nroGrupoMenor;
	}

	public void setNroGrupoMenor(String nroGrupoMenor) {
		this.nroGrupoMenor = nroGrupoMenor;
	}

	public String getDescripcionCIE() {
		return descripcionCIE;
	}

	public void setDescripcionCIE(String descripcionCIE) {
		this.descripcionCIE = descripcionCIE;
	}

}
