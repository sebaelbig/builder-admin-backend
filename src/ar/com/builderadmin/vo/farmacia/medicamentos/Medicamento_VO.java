package ar.com.builderadmin.vo.farmacia.medicamentos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.model.farmacia.medicamentos.Medicamento;
import ar.com.builderadmin.model.farmacia.medicamentos.MedicamentoComercial;
import ar.com.builderadmin.vo.I_ValueObject;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class Medicamento_VO implements Serializable, I_ValueObject<Medicamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private Date fechaVencimiento;

	/**
	 * Medicamento
	 */
	private String composicionQuimica;

	private String contraindicaciones;

	private Boolean conservarEnFrio;

	private Boolean comercializado;

	private Integer cantidadRecomendada;

	// Alta, Anulada, Denunciada
	private String situacion;

	private String usoTerapeutico;

	private String nombreGenerico;

	private String nombreQuimico;

	private List<MedicamentoComercial_VO> comerciales;

	// Constructores
	public Medicamento_VO() {

	}

	public Medicamento_VO(Medicamento o) {
		this.setObject(o);
	}

	public Medicamento_VO(Medicamento o, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(o, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Medicamento) {
			Medicamento o = (Medicamento) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Boolean getConservarEnFrio() {
		return conservarEnFrio;
	}

	public void setConservarEnFrio(Boolean conservarEnFrio) {
		this.conservarEnFrio = conservarEnFrio;
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

	public Boolean getComercializado() {
		return comercializado;
	}

	public void setComercializado(Boolean comercializado) {
		this.comercializado = comercializado;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public Integer getCantidadRecomendada() {
		return cantidadRecomendada;
	}

	public void setCantidadRecomendada(Integer cantidadRecomendada) {
		this.cantidadRecomendada = cantidadRecomendada;
	}

	public String getComposicionQuimica() {
		return composicionQuimica;
	}

	public void setComposicionQuimica(String composicionQuimica) {
		this.composicionQuimica = composicionQuimica;
	}

	public String getContraindicaciones() {
		return contraindicaciones;
	}

	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	public String getUsoTerapeutico() {
		return usoTerapeutico;
	}

	public void setUsoTerapeutico(String usoTerapeutico) {
		this.usoTerapeutico = usoTerapeutico;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public String getNombreQuimico() {
		return nombreQuimico;
	}

	public void setNombreQuimico(String nombreQuimico) {
		this.nombreQuimico = nombreQuimico;
	}

	public List<MedicamentoComercial_VO> getComerciales() {
		return comerciales;
	}

	public void setComerciales(List<MedicamentoComercial_VO> comerciales) {
		this.comerciales = comerciales;
	}

	@Override
	public String toString() {
		return "";
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public Medicamento toObject() {
		Medicamento med = new Medicamento(this.getId(), this.getVersion());

		med.setCantidadRecomendada(getCantidadRecomendada());
		med.setComercializado(getComercializado());
		med.setComposicionQuimica(getComposicionQuimica());
		med.setConservarEnFrio(getConservarEnFrio());
		med.setContraindicaciones(getContraindicaciones());
		med.setNombreGenerico(getNombreGenerico());
		med.setNombreQuimico(getNombreQuimico());
		med.setSituacion(getSituacion());
		med.setUsoTerapeutico(getUsoTerapeutico());

		for (MedicamentoComercial_VO comercial : getComerciales()) {
			med.getComerciales().add(comercial.toObject());
		}

		return med;
	}

	@Override
	public void setObject(Medicamento o) {
		this.setId(o.getId());
		this.setVersion(o.getVersion());

		this.setCantidadRecomendada(getCantidadRecomendada());
		this.setComercializado(getComercializado());
		this.setComposicionQuimica(getComposicionQuimica());
		this.setConservarEnFrio(getConservarEnFrio());
		this.setContraindicaciones(getContraindicaciones());
		this.setNombreGenerico(getNombreGenerico());
		this.setNombreQuimico(getNombreQuimico());
		this.setSituacion(getSituacion());
		this.setUsoTerapeutico(getUsoTerapeutico());

		for (MedicamentoComercial comercial : o.getComerciales()) {
			this.getComerciales().add(comercial.toValueObject());
		}

	}

	@Override
	public void setObject(Medicamento o, int profundidadActual,
			int profundidadDeseada) {
		this.setId(o.getId());
		this.setVersion(o.getVersion());

		this.setCantidadRecomendada(getCantidadRecomendada());
		this.setComercializado(getComercializado());
		this.setComposicionQuimica(getComposicionQuimica());
		this.setConservarEnFrio(getConservarEnFrio());
		this.setContraindicaciones(getContraindicaciones());
		this.setNombreGenerico(getNombreGenerico());
		this.setNombreQuimico(getNombreQuimico());
		this.setSituacion(getSituacion());
		this.setUsoTerapeutico(getUsoTerapeutico());

		if (profundidadActual < profundidadDeseada) {

			for (MedicamentoComercial comercial : o.getComerciales()) {
				this.getComerciales().add(
						comercial.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}
		}

	}

}