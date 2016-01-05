package ar.com.builderadmin.model.core.obrasSociales;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.vo.core.obrasSociales.ContratoDeProducto_VO;

/**
 * 
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 */

@Entity
@Table( name = "contrato_de_producto")
public class ContratoDeProducto implements I_Entidad {

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_contratodeproducto", name = "seq_contratodeproducto", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contratodeproducto")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 50, name = "condicion_iva")
	private String condicionIVA;

	@Column(name = "descartable_ambulatorio")
	private Integer descartableAmbulatorio;

	@Column(name = "descartable_internacion")
	private Integer descartableInternacion;

	@Column(name = "diferenciado_a")
	private Float diferenciadoA;

	@Column(name = "diferenciado_b")
	private Float diferenciadoB;

	@Column(name = "diferenciado_c")
	private Float diferenciadoC;

	@Column(name = "emite_nota_credito")
	private Boolean emiteNotaCredito;

	@Column(name = "extra_ambulatorio")
	private Integer extraAmbulatorio;

	@Column(name = "extra_internacion")
	private Integer extraInternacion;

	@Column(name = "fecha_desde")
	@Temporal(TemporalType.DATE)
	private Date fechaDesde;

	@Column(name = "fecha_hasta")
	@Temporal(TemporalType.DATE)
	private Date fechaHasta;

	@Column(name = "gastos_bioquimicos_ambulatorio")
	private Integer gastosBioquimicosAmbulatorio;

	@Column(name = "gastos_bioquimicos_internacion")
	private Integer gastosBioquimicosInternacion;

	@Column(name = "gastos_quirurgicos_ambulatorio")
	private Integer gastosQuirurgicosAmbulatorio;

	@Column(name = "gastos_quirurgicos_internacion")
	private Integer gastosQuirurgicosInternacion;

	@Column(name = "gastos_radiologicos_ambulatorio")
	private Integer gastosRadiologicosAmbulatorio;

	@Column(name = "gastos_radiologicos_internacion")
	private Integer gastosRadiologicosInternacion;

	@Column(name = "hematologia_hemoterapia_ambulatorio")
	private Integer hematologiaHemoterapiaAmbulatorio;

	@Column(name = "hematologia_hemoterapia_internacion")
	private Integer hematologiaHemoterapiaInternacion;

	@Column(name = "honorario_Ambulatorio")
	private Integer honorarioAmbulatorio;

	@Column(name = "honorario_internacion")
	private Integer honorarioInternacion;

	@Column(name = "honorario_quirurgico_ambulatorio")
	private Integer honorarioQuirurgicoAmbulatorio;

	@Column(name = "honorario_quirurgico_internacion")
	private Integer honorarioQuirurgicoInternacion;

	@Column(name = "honorarios_bioquimicos_ambulatorio")
	private Integer honorariosBioquimicosAmbulatorio;

	@Column(name = "honorarios_bioquimicos_internacion")
	private Integer honorariosBioquimicosInternacion;

	@Column(name = "medicamentos_ambulatorio")
	private Integer medicamentosAmbulatorio;

	@Column(name = "medicamentos_internacion")
	private Integer medicamentosInternacion;

	@Column(name = "pension_ambulatorio")
	private Integer pensionAmbulatorio;

	@Column(name = "pension_internacion")
	private Integer pensionInternacion;

	@Column(name = "practicas_modulos_consultas_ambulatorio")
	private Integer practicasModulosConsultasAmbulatorio;

	@Column(name = "practicas_modulos_consultas_internacion")
	private Integer practicasModulosConsultasInternacion;

	@Column(name = "valor_iva")
	private Float valorIVA;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto_OS producto;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contrato")
	private List<Coeficientes_UA> coeficientes;

	public ContratoDeProducto(Long id, Integer version, String condicionIVA,
			Integer descartableAmbulatorio, Integer descartableInternacion,
			Float diferenciadoA, Float diferenciadoB, Float diferenciadoC,
			Boolean emiteNotaCredito, Integer extraAmbulatorio,
			Integer extraInternacion, Date fechaDesde, Date fechaHasta,
			Integer gastosBioquimicosAmbulatorio,
			Integer gastosBioquimicosInternacion,
			Integer gastosQuirurgicosAmbulatorio,
			Integer gastosQuirurgicosInternacion,
			Integer gastosRadiologicosAmbulatorio,
			Integer gastosRadiologicosInternacion,
			Integer hematologiaHemoterapiaAmbulatorio,
			Integer hematologiaHemoterapiaInternacion,
			Integer honorarioAmbulatorio, Integer honorarioInternacion,
			Integer honorarioQuirurgicoAmbulatorio,
			Integer honorarioQuirurgicoInternacion,
			Integer honorariosBioquimicosAmbulatorio,
			Integer honorariosBioquimicosInternacion,
			Integer medicamentosAmbulatorio, Integer medicamentosInternacion,
			Integer pensionAmbulatorio, Integer pensionInternacion,
			Integer practicasModulosConsultasAmbulatorio,
			Integer practicasModulosConsultasInternacion, Float valorIVA) {

		setCondicionIVA(condicionIVA);
		setDescartableAmbulatorio(descartableAmbulatorio);
		setDescartableInternacion(descartableInternacion);
		setDiferenciadoA(diferenciadoA);
		setDiferenciadoB(diferenciadoB);
		setDiferenciadoC(diferenciadoC);
		setEmiteNotaCredito(emiteNotaCredito);
		setExtraAmbulatorio(extraAmbulatorio);
		setExtraInternacion(extraInternacion);
		setFechaDesde(fechaDesde);
		setFechaHasta(fechaHasta);
		setGastosBioquimicosAmbulatorio(gastosBioquimicosAmbulatorio);
		setGastosBioquimicosInternacion(gastosBioquimicosInternacion);
		setGastosQuirurgicosAmbulatorio(gastosQuirurgicosAmbulatorio);
		setGastosQuirurgicosInternacion(gastosQuirurgicosInternacion);
		setGastosRadiologicosAmbulatorio(gastosRadiologicosAmbulatorio);
		setGastosRadiologicosInternacion(gastosRadiologicosInternacion);
		setHematologiaHemoterapiaAmbulatorio(hematologiaHemoterapiaAmbulatorio);
		setHematologiaHemoterapiaInternacion(hematologiaHemoterapiaInternacion);
		setHonorarioAmbulatorio(honorarioAmbulatorio);
		setHonorarioInternacion(honorarioInternacion);
		setHonorarioQuirurgicoAmbulatorio(honorarioQuirurgicoAmbulatorio);
		setHonorarioQuirurgicoInternacion(honorarioQuirurgicoInternacion);
		setHonorariosBioquimicosAmbulatorio(honorariosBioquimicosAmbulatorio);
		setHonorariosBioquimicosInternacion(honorariosBioquimicosInternacion);
		setId(id);
		setMedicamentosAmbulatorio(medicamentosAmbulatorio);
		setMedicamentosInternacion(medicamentosInternacion);
		setPensionAmbulatorio(pensionAmbulatorio);
		setPensionInternacion(pensionInternacion);
		setPracticasModulosConsultasAmbulatorio(practicasModulosConsultasAmbulatorio);
		setPracticasModulosConsultasInternacion(practicasModulosConsultasInternacion);
		setValorIVA(valorIVA);
		setVersion(version);

	}

	public ContratoDeProducto() {

	}

	/**
	 * Metodos
	 */

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ContratoDeProducto) {
			ContratoDeProducto o = (ContratoDeProducto) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	/**
	 * Setters y getters
	 * 
	 */

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

	public String getCondicionIVA() {
		return condicionIVA;
	}

	public void setCondicionIVA(String condicionIVA) {
		this.condicionIVA = condicionIVA;
	}

	public Integer getDescartableAmbulatorio() {
		return descartableAmbulatorio;
	}

	public void setDescartableAmbulatorio(Integer descartableAmbulatorio) {
		this.descartableAmbulatorio = descartableAmbulatorio;
	}

	public Integer getDescartableInternacion() {
		return descartableInternacion;
	}

	public void setDescartableInternacion(Integer descartableInternacion) {
		this.descartableInternacion = descartableInternacion;
	}

	public Float getDiferenciadoA() {
		return diferenciadoA;
	}

	public void setDiferenciadoA(Float diferenciadoA) {
		this.diferenciadoA = diferenciadoA;
	}

	public Float getDiferenciadoB() {
		return diferenciadoB;
	}

	public void setDiferenciadoB(Float diferenciadoB) {
		this.diferenciadoB = diferenciadoB;
	}

	public Float getDiferenciadoC() {
		return diferenciadoC;
	}

	public void setDiferenciadoC(Float diferenciadoC) {
		this.diferenciadoC = diferenciadoC;
	}

	public Boolean getEmiteNotaCredito() {
		return emiteNotaCredito;
	}

	public void setEmiteNotaCredito(Boolean emiteNotaCredito) {
		this.emiteNotaCredito = emiteNotaCredito;
	}

	public Integer getExtraAmbulatorio() {
		return extraAmbulatorio;
	}

	public void setExtraAmbulatorio(Integer extraAmbulatorio) {
		this.extraAmbulatorio = extraAmbulatorio;
	}

	public Integer getExtraInternacion() {
		return extraInternacion;
	}

	public void setExtraInternacion(Integer extraInternacion) {
		this.extraInternacion = extraInternacion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getGastosBioquimicosAmbulatorio() {
		return gastosBioquimicosAmbulatorio;
	}

	public void setGastosBioquimicosAmbulatorio(
			Integer gastosBioquimicosAmbulatorio) {
		this.gastosBioquimicosAmbulatorio = gastosBioquimicosAmbulatorio;
	}

	public Integer getGastosBioquimicosInternacion() {
		return gastosBioquimicosInternacion;
	}

	public void setGastosBioquimicosInternacion(
			Integer gastosBioquimicosInternacion) {
		this.gastosBioquimicosInternacion = gastosBioquimicosInternacion;
	}

	public Integer getGastosQuirurgicosAmbulatorio() {
		return gastosQuirurgicosAmbulatorio;
	}

	public void setGastosQuirurgicosAmbulatorio(
			Integer gastosQuirurgicosAmbulatorio) {
		this.gastosQuirurgicosAmbulatorio = gastosQuirurgicosAmbulatorio;
	}

	public Integer getGastosQuirurgicosInternacion() {
		return gastosQuirurgicosInternacion;
	}

	public void setGastosQuirurgicosInternacion(
			Integer gastosQuirurgicosInternacion) {
		this.gastosQuirurgicosInternacion = gastosQuirurgicosInternacion;
	}

	public Integer getGastosRadiologicosAmbulatorio() {
		return gastosRadiologicosAmbulatorio;
	}

	public void setGastosRadiologicosAmbulatorio(
			Integer gastosRadiologicosAmbulatorio) {
		this.gastosRadiologicosAmbulatorio = gastosRadiologicosAmbulatorio;
	}

	public Integer getGastosRadiologicosInternacion() {
		return gastosRadiologicosInternacion;
	}

	public void setGastosRadiologicosInternacion(
			Integer gastosRadiologicosInternacion) {
		this.gastosRadiologicosInternacion = gastosRadiologicosInternacion;
	}

	public Integer getHematologiaHemoterapiaAmbulatorio() {
		return hematologiaHemoterapiaAmbulatorio;
	}

	public void setHematologiaHemoterapiaAmbulatorio(
			Integer hematologiaHemoterapiaAmbulatorio) {
		this.hematologiaHemoterapiaAmbulatorio = hematologiaHemoterapiaAmbulatorio;
	}

	public Integer getHematologiaHemoterapiaInternacion() {
		return hematologiaHemoterapiaInternacion;
	}

	public void setHematologiaHemoterapiaInternacion(
			Integer hematologiaHemoterapiaInternacion) {
		this.hematologiaHemoterapiaInternacion = hematologiaHemoterapiaInternacion;
	}

	public Integer getHonorarioAmbulatorio() {
		return honorarioAmbulatorio;
	}

	public void setHonorarioAmbulatorio(Integer honorarioAmbulatorio) {
		this.honorarioAmbulatorio = honorarioAmbulatorio;
	}

	public Integer getHonorarioInternacion() {
		return honorarioInternacion;
	}

	public void setHonorarioInternacion(Integer honorarioInternacion) {
		this.honorarioInternacion = honorarioInternacion;
	}

	public Integer getHonorarioQuirurgicoAmbulatorio() {
		return honorarioQuirurgicoAmbulatorio;
	}

	public void setHonorarioQuirurgicoAmbulatorio(
			Integer honorarioQuirurgicoAmbulatorio) {
		this.honorarioQuirurgicoAmbulatorio = honorarioQuirurgicoAmbulatorio;
	}

	public Integer getHonorarioQuirurgicoInternacion() {
		return honorarioQuirurgicoInternacion;
	}

	public void setHonorarioQuirurgicoInternacion(
			Integer honorarioQuirurgicoInternacion) {
		this.honorarioQuirurgicoInternacion = honorarioQuirurgicoInternacion;
	}

	public Integer getHonorariosBioquimicosAmbulatorio() {
		return honorariosBioquimicosAmbulatorio;
	}

	public void setHonorariosBioquimicosAmbulatorio(
			Integer honorariosBioquimicosAmbulatorio) {
		this.honorariosBioquimicosAmbulatorio = honorariosBioquimicosAmbulatorio;
	}

	public Integer getHonorariosBioquimicosInternacion() {
		return honorariosBioquimicosInternacion;
	}

	public void setHonorariosBioquimicosInternacion(
			Integer honorariosBioquimicosInternacion) {
		this.honorariosBioquimicosInternacion = honorariosBioquimicosInternacion;
	}

	public Integer getMedicamentosAmbulatorio() {
		return medicamentosAmbulatorio;
	}

	public void setMedicamentosAmbulatorio(Integer medicamentosAmbulatorio) {
		this.medicamentosAmbulatorio = medicamentosAmbulatorio;
	}

	public Integer getMedicamentosInternacion() {
		return medicamentosInternacion;
	}

	public void setMedicamentosInternacion(Integer medicamentosInternacion) {
		this.medicamentosInternacion = medicamentosInternacion;
	}

	public Integer getPensionAmbulatorio() {
		return pensionAmbulatorio;
	}

	public void setPensionAmbulatorio(Integer pensionAmbulatorio) {
		this.pensionAmbulatorio = pensionAmbulatorio;
	}

	public Integer getPensionInternacion() {
		return pensionInternacion;
	}

	public void setPensionInternacion(Integer pensionInternacion) {
		this.pensionInternacion = pensionInternacion;
	}

	public Integer getPracticasModulosConsultasAmbulatorio() {
		return practicasModulosConsultasAmbulatorio;
	}

	public void setPracticasModulosConsultasAmbulatorio(
			Integer practicasModulosConsultasAmbulatorio) {
		this.practicasModulosConsultasAmbulatorio = practicasModulosConsultasAmbulatorio;
	}

	public Integer getPracticasModulosConsultasInternacion() {
		return practicasModulosConsultasInternacion;
	}

	public void setPracticasModulosConsultasInternacion(
			Integer practicasModulosConsultasInternacion) {
		this.practicasModulosConsultasInternacion = practicasModulosConsultasInternacion;
	}

	public Float getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(Float valorIVA) {
		this.valorIVA = valorIVA;
	}

	public Producto_OS getProducto() {
		return producto;
	}

	public void setProducto(Producto_OS producto) {
		this.producto = producto;
	}

	public List<Coeficientes_UA> getCoeficientes() {
		return coeficientes;
	}

	public void setCoeficientes(List<Coeficientes_UA> coeficientes) {
		this.coeficientes = coeficientes;
	}

	public ContratoDeProducto_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new ContratoDeProducto_VO(this, profundidadActual,
				profundidadDeseada);
	}

	public ContratoDeProducto_VO toValueObject() {
		return new ContratoDeProducto_VO(this);
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}
