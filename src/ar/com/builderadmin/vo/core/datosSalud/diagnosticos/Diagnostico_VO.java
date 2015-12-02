package ar.com.builderadmin.vo.core.datosSalud.diagnosticos;

import java.io.Serializable;

import ar.com.builderadmin.model.core.datosSalud.diagnosticos.Diagnostico;
import ar.com.builderadmin.vo.I_ValueObject;

/**
 * @author svalle
 */

public class Diagnostico_VO implements Serializable, I_ValueObject<Diagnostico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	/**
	 * Codigo unico del diagnostico
	 */
	private String codigoUnico;

	/******************************************************************************/
	/*********************** Correspondiente al standar CIAP **********************/
	/******************************************************************************/
	private Boolean ciap_2 = false;

	public static final String CIAP = "Clasificaci�n Internacional de la Atenci�n Primaria";

	public static final String SIGNOS_Y_SiNTOMAS = "Signos y s�ntomas";
	public static final String INFECCIONES = "Infecciones";
	public static final String NEOPLASIAS = "Neoplasias";
	public static final String LESIONES = "Lesiones";
	public static final String ANOMALIAS_CONGENITAS = "Anomal�as cong�nitas";
	public static final String OTROS_DIAGNOSTICOS = "Otros diagn�sticos";

	/**
	 * El primer d�gito es una letra que representa un aparato o sistema
	 * org�nico, y constituyen los 17 "cap�tulos" de esta clasificaci�n.
	 */
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

	private String codigoCIAP;

	private String descripcionCIAP;

	private String resumenCIAP;

	private String terminosIncluidos;

	private String terminosExcluidos;

	private String criteriosDeInclusion;

	private String consideraciones;

	private String notaCIAP;

	private String icpc2;

	private String correspondencia_CIAP_CIE;

	private String componente;

	private String nroComponente;

	/******************************************************************************/
	/*********************** Correspondiente al standar CIE ***********************/
	/******************************************************************************/
	private Boolean cie_10 = false;

	public static final String CIE = "Clasificaci�n estad�stica internacional de enfermedades y otros problemas de salud";

	/**
	 * ada condici�n de salud puede ser asignada a una categor�a y recibir un
	 * c�digo de hasta seis caracteres de longitud (en formato de X00.00).
	 */
	private String codigoCIE;

	/**
	 * Expresado en nros romanos
	 */
	private String capituloCIE;

	private String nroGrupoPrincipal;

	private String nroGrupoMenor;

	private String descripcionCIE;

	/******************************************************************************/
	/******************************************************************************/
	public Diagnostico_VO() {
	}

	public Diagnostico_VO(Diagnostico diag) {
		this.setObject(diag);
	}

	public Diagnostico_VO(Diagnostico diag, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(diag, profundidadActual, profundidadDeseada);
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

	@Override
	public String toString() {
		String resul = "(" + this.getCodigoUnico() + ") ";

		if (this.getCiap_2()) {
			resul += this.getDescripcionCIAP();
		} else {
			resul += this.getDescripcionCIE();
		}

		return resul;
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

	public Boolean getCIE_10() {
		return cie_10;
	}

	public void setCIE_10(Boolean cie_10) {
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

	@Override
	public void setObject(Diagnostico diag) {
		this.setId(diag.getId());
		this.setVersion(diag.getVersion());

		this.setCodigoUnico(diag.getCodigoUnico());
		if (diag.getCiap_2()) {
			// Es un diagnostico de CIAP-2
			this.setCiap_2(true);

			this.setAparatoSistemaOrganico(diag.getAparatoSistemaOrganico());
			this.setCodigoCIAP(diag.getCodigoCIAP());
			this.setComponentes(diag.getComponentes());
			this.setComponente(diag.getComponente());
			this.setCorrespondencia_CIAP_CIE(diag.getCorrespondencia_CIAP_CIE());
			this.setCriteriosDeInclusion(diag.getCriteriosDeInclusion());
			this.setDescripcionCIAP(diag.getDescripcionCIAP());
			this.setIcpc2(diag.getIcpc2());
			this.setNotaCIAP(diag.getNotaCIAP());
			this.setNroComponente(diag.getNroComponente());
			this.setResumenCIAP(diag.getResumenCIAP());
			this.setTerminosIncluidos(diag.getTerminosIncluidos());
			this.setTerminosExcluidos(diag.getTerminosExcluidos());

		} else {
			// Es un diagnostico de CIE-10
			this.setCIE_10(true);

			this.setCapituloCIE(diag.getCapituloCIE());
			this.setCodigoCIE(diag.getCodigoCIE());
			this.setDescripcionCIE(diag.getDescripcionCIE());
			this.setNroGrupoMenor(diag.getNroGrupoMenor());
			this.setNroGrupoPrincipal(diag.getNroGrupoPrincipal());

		}

	}

	@Override
	public void setObject(Diagnostico objeto, int profundidadActual,
			int profundidadDeseada) {
		setObject(objeto);
	}

	@Override
	public Diagnostico toObject() {
		Diagnostico resul = new Diagnostico();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setCodigoUnico(this.getCodigoUnico());
		if (this.getCiap_2()) {
			// Es un diagnostico de CIAP-2
			resul.setCiap_2(true);

			resul.setAparatoSistemaOrganico(this.getAparatoSistemaOrganico());
			resul.setCodigoCIAP(this.getCodigoCIAP());
			resul.setComponentes(this.getComponentes());
			resul.setComponente(this.getComponente());
			resul.setCorrespondencia_CIAP_CIE(this
					.getCorrespondencia_CIAP_CIE());
			resul.setCriteriosDeInclusion(this.getCriteriosDeInclusion());
			resul.setDescripcionCIAP(this.getDescripcionCIAP());
			resul.setIcpc2(this.getIcpc2());
			resul.setNotaCIAP(this.getNotaCIAP());
			resul.setNroComponente(this.getNroComponente());
			resul.setResumenCIAP(this.getResumenCIAP());
			resul.setTerminosIncluidos(this.getTerminosIncluidos());
			resul.setTerminosExcluidos(this.getTerminosExcluidos());

		} else {
			// Es un diagnostico de CIE-10
			resul.setCie_10(true);

			resul.setCapituloCIE(this.getCapituloCIE());
			resul.setCodigoCIE(this.getCodigoCIE());
			resul.setDescripcionCIE(this.getDescripcionCIE());
			resul.setNroGrupoMenor(this.getNroGrupoMenor());
			resul.setNroGrupoPrincipal(this.getNroGrupoPrincipal());

		}

		return resul;
	}

	public Diagnostico toObject(int profundidadActual, int profundidadDeseada) {
		return toObject();
	}

}
