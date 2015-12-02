package ar.com.builderadmin.model.farmacia.medicamentos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.vo.farmacia.medicamentos.Medicamento_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table
public class Medicamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_medicamento", name = "seq_medicamento", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medicamento")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Medicamento
	 */
	@Column(name = "composicion_quimica")
	private String composicionQuimica;

	private String contraindicaciones;

	@Column(name = "conservar_en_frio")
	private Boolean conservarEnFrio;

	private Boolean comercializado = true;

	@Column(name = "cantidad_recomendada")
	private Integer cantidadRecomendada;

	// Alta, Anulada, Denunciada
	private String situacion;

	@Column(name = "uso_terapeutico")
	private String usoTerapeutico;

	@Column(name = "nombre_generico")
	private String nombreGenerico;

	@Column(name = "nombre_quimico")
	private String nombreQuimico;

	@OneToMany
	private List<MedicamentoComercial> comerciales;

	// Constructores
	public Medicamento() {

	}

	public Medicamento(Long id2, Integer version2) {
		this.setId(id2);
		this.setVersion(version2);
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

	public List<MedicamentoComercial> getComerciales() {
		return comerciales;
	}

	public void setComerciales(List<MedicamentoComercial> comerciales) {
		this.comerciales = comerciales;
	}

	@Override
	public String toString() {
		return "";
	}

	public Medicamento_VO toValueObjet() {
		return new Medicamento_VO(this);
	}

	public Medicamento_VO toValueObjet(int profundidadActual,
			int profundidadDeseada) {
		return new Medicamento_VO(this, profundidadActual, profundidadDeseada);
	}

}