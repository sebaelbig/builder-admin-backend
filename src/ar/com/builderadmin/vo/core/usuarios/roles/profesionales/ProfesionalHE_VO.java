package ar.com.builderadmin.vo.core.usuarios.roles.profesionales;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.core.areas.DiaDeAtencion_VO;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;
import ar.com.builderadmin.vo.core.obrasSociales.PlanNoAceptado_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class ProfesionalHE_VO extends Rol_VO {

	private Integer nroMatricula;
	private String apellido;
	private String tipoMatricula;
	private String especialidadFirma;
	private String especialidad_renglon;
	private List<Especialidad_VO> especialidades = new ArrayList<Especialidad_VO>();
	private List<PlanNoAceptado_VO> mutualesNoAceptadas = new ArrayList<PlanNoAceptado_VO>();
	private String firma;
	private String categoria;
	private String mensajeMutuales;
	private List<DiaDeAtencion_VO> diasAtencion = new ArrayList<DiaDeAtencion_VO>();
	

	public Integer getNroMatricula() {
		return this.nroMatricula;
	}

	public void setNroMatricula(Integer nroMatriculaProvincial) {
		this.nroMatricula = nroMatriculaProvincial;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Especialidad_VO> getEspecialidades() {
		return this.especialidades;
	}

	public void setEspecialidades(List<Especialidad_VO> especialidades) {
		this.especialidades = especialidades;
	}

	public List<DiaDeAtencion_VO> getDiasAtencion() {
		return this.diasAtencion;
	}

	public void setDiasAtencion(List<DiaDeAtencion_VO> diasAtencion) {
		this.diasAtencion = diasAtencion;
	}

	@Override
	public String toString() {
		return getApellido() + " (Mat. " + getNroMatricula() + ")";
	}

	public String getMensajeMutuales() {
		return this.mensajeMutuales;
	}

	public void setMensajeMutuales(String mensajeMutuales) {
		this.mensajeMutuales = mensajeMutuales;
	}

	public List<PlanNoAceptado_VO> getMutualesNoAceptadas() {
		return this.mutualesNoAceptadas;
	}

	public void setMutualesNoAceptadas(
			List<PlanNoAceptado_VO> mutualesNoAceptadas) {
		this.mutualesNoAceptadas = mutualesNoAceptadas;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	public String getTipoMatricula() {
		return tipoMatricula;
	}
	public void setTipoMatricula(String tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
	}
	public String getEspecialidadFirma() {
		return especialidadFirma;
	}
	public void setEspecialidadFirma(String especialidadFirma) {
		this.especialidadFirma = especialidadFirma;
	}

	public String getEspecialidad_renglon() {
		return especialidad_renglon;
	}

	public void setEspecialidad_renglon(String especialidad_renglon) {
		this.especialidad_renglon = especialidad_renglon;
	}	

}