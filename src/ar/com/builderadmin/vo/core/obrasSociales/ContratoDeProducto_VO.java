package ar.com.builderadmin.vo.core.obrasSociales;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.model.core.obrasSociales.Coeficientes_UA;
import ar.com.builderadmin.model.core.obrasSociales.ContratoDeProducto;
import ar.com.builderadmin.vo.I_ValueObject;

public class ContratoDeProducto_VO implements I_ValueObject<ContratoDeProducto> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String condicionIVA;

	private Integer descartableAmbulatorio;

	private Integer descartableInternacion;

	private Float diferenciadoA;

	private Float diferenciadoB;

	private Float diferenciadoC;

	private Boolean emiteNotaCredito;

	private Integer extraAmbulatorio;

	private Integer extraInternacion;

	private String fechaDesde;

	private String fechaHasta;

	private Integer gastosBioquimicosAmbulatorio;

	private Integer gastosBioquimicosInternacion;

	private Integer gastosQuirurgicosAmbulatorio;

	private Integer gastosQuirurgicosInternacion;

	private Integer gastosRadiologicosAmbulatorio;

	private Integer gastosRadiologicosInternacion;

	private Integer hematologiaHemoterapiaAmbulatorio;

	private Integer hematologiaHemoterapiaInternacion;

	private Integer honorarioAmbulatorio;

	private Integer honorarioInternacion;

	private Integer honorarioQuirurgicoAmbulatorio;

	private Integer honorarioQuirurgicoInternacion;

	private Integer honorariosBioquimicosAmbulatorio;

	private Integer honorariosBioquimicosInternacion;

	private Integer medicamentosAmbulatorio;

	private Integer medicamentosInternacion;

	private Integer pensionAmbulatorio;

	private Integer pensionInternacion;

	private Integer practicasModulosConsultasAmbulatorio;

	private Integer practicasModulosConsultasInternacion;

	private Float valorIVA;

	private List<Coeficientes_UA_VO> coeficientes_vo;

	private Long idProducto_OS;

	private String nombreProducto_OS;

	private String nombreObraSocial;

	public ContratoDeProducto_VO() {

	}

	public ContratoDeProducto_VO(ContratoDeProducto contratoDeProducto) {
		setObject(contratoDeProducto);
	}

	public ContratoDeProducto_VO(ContratoDeProducto contratoDeProducto,
			int profundidadActual, int profundidadDeseada) {
		setObject(contratoDeProducto, profundidadActual, profundidadDeseada);
	}

	public ContratoDeProducto_VO(List<TipoCoeficiente_VO> tiposCoeficientes) {
		setCoeficientes_vo(new ArrayList<Coeficientes_UA_VO>());
		for (TipoCoeficiente_VO tipo : tiposCoeficientes) {
			getCoeficientes_vo().add(new Coeficientes_UA_VO(tipo));

		}
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(ContratoDeProducto objeto) {
		setCondicionIVA(objeto.getCondicionIVA());
		setDescartableAmbulatorio(objeto.getDescartableAmbulatorio());
		setDescartableInternacion(objeto.getDescartableInternacion());
		setDiferenciadoA(objeto.getDiferenciadoA());
		setDiferenciadoB(objeto.getDiferenciadoB());
		setDiferenciadoC(objeto.getDiferenciadoC());
		setEmiteNotaCredito(objeto.getEmiteNotaCredito());
		setExtraAmbulatorio(objeto.getExtraAmbulatorio());
		setExtraInternacion(objeto.getExtraInternacion());
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		if (objeto.getFechaDesde() != null)
			setFechaDesde(f.format(objeto.getFechaDesde()));
		if (objeto.getFechaHasta() != null)
			setFechaHasta(f.format(objeto.getFechaHasta()));
		setGastosBioquimicosAmbulatorio(objeto
				.getGastosBioquimicosAmbulatorio());
		setGastosBioquimicosInternacion(objeto
				.getGastosBioquimicosInternacion());
		setGastosQuirurgicosAmbulatorio(objeto
				.getGastosQuirurgicosAmbulatorio());
		setGastosQuirurgicosInternacion(objeto
				.getGastosQuirurgicosInternacion());
		setGastosRadiologicosAmbulatorio(objeto
				.getGastosRadiologicosAmbulatorio());
		setGastosRadiologicosInternacion(objeto
				.getGastosRadiologicosInternacion());
		setHematologiaHemoterapiaAmbulatorio(objeto
				.getHematologiaHemoterapiaAmbulatorio());
		setHematologiaHemoterapiaInternacion(objeto
				.getHematologiaHemoterapiaInternacion());
		setHonorarioAmbulatorio(objeto.getHonorarioAmbulatorio());
		setHonorarioInternacion(objeto.getHonorarioInternacion());
		setHonorarioQuirurgicoAmbulatorio(objeto
				.getHonorarioQuirurgicoAmbulatorio());
		setHonorarioQuirurgicoInternacion(objeto
				.getHonorarioQuirurgicoInternacion());
		setHonorariosBioquimicosAmbulatorio(objeto
				.getHonorariosBioquimicosAmbulatorio());
		setHonorariosBioquimicosInternacion(objeto
				.getHonorariosBioquimicosInternacion());
		setId(objeto.getId());
		setMedicamentosAmbulatorio(objeto.getMedicamentosAmbulatorio());
		setMedicamentosInternacion(objeto.getMedicamentosInternacion());
		setPensionAmbulatorio(objeto.getPensionAmbulatorio());
		setPensionInternacion(objeto.getPensionInternacion());
		setPracticasModulosConsultasAmbulatorio(objeto
				.getPracticasModulosConsultasAmbulatorio());
		setPracticasModulosConsultasInternacion(objeto
				.getPracticasModulosConsultasInternacion());
		setValorIVA(objeto.getValorIVA());
		setVersion(objeto.getVersion());

		setCoeficientes_vo(new ArrayList<Coeficientes_UA_VO>());

		for (Coeficientes_UA c : objeto.getCoeficientes()) {
			getCoeficientes_vo().add(new Coeficientes_UA_VO(c));

		}

		setIdProducto_OS(objeto.getProducto().getId());
		setNombreObraSocial(objeto.getProducto().getObraSocial().getNombre());
		setNombreProducto_OS(objeto.getProducto().getNombre());

	}

	@Override
	public ContratoDeProducto toObject() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

		Date fd = null;
		try {
			fd = f.parse(fechaDesde);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date fh = null;

		if (fechaHasta != null)
			try {
				fh = f.parse(fechaHasta);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		ContratoDeProducto contratoDeProducto = new ContratoDeProducto(id,
				version, condicionIVA, descartableAmbulatorio,
				descartableInternacion, diferenciadoA, diferenciadoB,
				diferenciadoC, emiteNotaCredito, extraAmbulatorio,
				extraInternacion, fd, fh, gastosBioquimicosAmbulatorio,
				gastosBioquimicosInternacion, gastosQuirurgicosAmbulatorio,
				gastosQuirurgicosInternacion, gastosRadiologicosAmbulatorio,
				gastosRadiologicosInternacion,
				hematologiaHemoterapiaAmbulatorio,
				hematologiaHemoterapiaInternacion, honorarioAmbulatorio,
				honorarioInternacion, honorarioQuirurgicoAmbulatorio,
				honorarioQuirurgicoInternacion,
				honorariosBioquimicosAmbulatorio,
				honorariosBioquimicosInternacion, medicamentosAmbulatorio,
				medicamentosInternacion, pensionAmbulatorio,
				pensionInternacion, practicasModulosConsultasAmbulatorio,
				practicasModulosConsultasInternacion, valorIVA);

		contratoDeProducto.setCoeficientes(new ArrayList<Coeficientes_UA>());

		for (Coeficientes_UA_VO coeficiente : getCoeficientes_vo()) {
			Coeficientes_UA c = coeficiente.toObject();
			c.setContrato(contratoDeProducto);
			contratoDeProducto.getCoeficientes().add(c);
		}

		return contratoDeProducto;
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

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Coeficientes_UA_VO> getCoeficientes_vo() {
		return coeficientes_vo;
	}

	public void setCoeficientes_vo(List<Coeficientes_UA_VO> coeficientes_vo) {
		this.coeficientes_vo = coeficientes_vo;
	}

	public Long getIdProducto_OS() {
		return idProducto_OS;
	}

	public void setIdProducto_OS(Long idProducto_OS) {
		this.idProducto_OS = idProducto_OS;
	}

	public String getNombreObraSocial() {
		return nombreObraSocial;
	}

	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}

	public String getNombreProducto_OS() {
		return nombreProducto_OS;
	}

	public void setNombreProducto_OS(String nombreProducto_OS) {
		this.nombreProducto_OS = nombreProducto_OS;
	}

	@Override
	public void setObject(ContratoDeProducto objeto, int profundidadActual,
			int profundidadDeseada) {
		setCondicionIVA(objeto.getCondicionIVA());
		setDescartableAmbulatorio(objeto.getDescartableAmbulatorio());
		setDescartableInternacion(objeto.getDescartableInternacion());
		setDiferenciadoA(objeto.getDiferenciadoA());
		setDiferenciadoB(objeto.getDiferenciadoB());
		setDiferenciadoC(objeto.getDiferenciadoC());
		setEmiteNotaCredito(objeto.getEmiteNotaCredito());
		setExtraAmbulatorio(objeto.getExtraAmbulatorio());
		setExtraInternacion(objeto.getExtraInternacion());
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		if (objeto.getFechaDesde() != null)
			setFechaDesde(f.format(objeto.getFechaDesde()));
		if (objeto.getFechaHasta() != null)
			setFechaHasta(f.format(objeto.getFechaHasta()));
		setGastosBioquimicosAmbulatorio(objeto
				.getGastosBioquimicosAmbulatorio());
		setGastosBioquimicosInternacion(objeto
				.getGastosBioquimicosInternacion());
		setGastosQuirurgicosAmbulatorio(objeto
				.getGastosQuirurgicosAmbulatorio());
		setGastosQuirurgicosInternacion(objeto
				.getGastosQuirurgicosInternacion());
		setGastosRadiologicosAmbulatorio(objeto
				.getGastosRadiologicosAmbulatorio());
		setGastosRadiologicosInternacion(objeto
				.getGastosRadiologicosInternacion());
		setHematologiaHemoterapiaAmbulatorio(objeto
				.getHematologiaHemoterapiaAmbulatorio());
		setHematologiaHemoterapiaInternacion(objeto
				.getHematologiaHemoterapiaInternacion());
		setHonorarioAmbulatorio(objeto.getHonorarioAmbulatorio());
		setHonorarioInternacion(objeto.getHonorarioInternacion());
		setHonorarioQuirurgicoAmbulatorio(objeto
				.getHonorarioQuirurgicoAmbulatorio());
		setHonorarioQuirurgicoInternacion(objeto
				.getHonorarioQuirurgicoInternacion());
		setHonorariosBioquimicosAmbulatorio(objeto
				.getHonorariosBioquimicosAmbulatorio());
		setHonorariosBioquimicosInternacion(objeto
				.getHonorariosBioquimicosInternacion());
		setId(objeto.getId());
		setMedicamentosAmbulatorio(objeto.getMedicamentosAmbulatorio());
		setMedicamentosInternacion(objeto.getMedicamentosInternacion());
		setPensionAmbulatorio(objeto.getPensionAmbulatorio());
		setPensionInternacion(objeto.getPensionInternacion());
		setPracticasModulosConsultasAmbulatorio(objeto
				.getPracticasModulosConsultasAmbulatorio());
		setPracticasModulosConsultasInternacion(objeto
				.getPracticasModulosConsultasInternacion());
		setValorIVA(objeto.getValorIVA());
		setVersion(objeto.getVersion());

		setCoeficientes_vo(new ArrayList<Coeficientes_UA_VO>());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			for (Coeficientes_UA c : objeto.getCoeficientes()) {
				getCoeficientes_vo().add(
						c.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

			setIdProducto_OS(objeto.getProducto().getId());
			setNombreObraSocial(objeto.getProducto().getObraSocial()
					.getNombre());
			setNombreProducto_OS(objeto.getProducto().getNombre());
		}

	}

	public ContratoDeProducto_VO(ContratoDeProducto_VO objeto) {
		setCondicionIVA(objeto.getCondicionIVA());
		setDescartableAmbulatorio(objeto.getDescartableAmbulatorio());
		setDescartableInternacion(objeto.getDescartableInternacion());
		setDiferenciadoA(objeto.getDiferenciadoA());
		setDiferenciadoB(objeto.getDiferenciadoB());
		setDiferenciadoC(objeto.getDiferenciadoC());
		setEmiteNotaCredito(objeto.getEmiteNotaCredito());
		setExtraAmbulatorio(objeto.getExtraAmbulatorio());
		setExtraInternacion(objeto.getExtraInternacion());
		setFechaDesde(objeto.getFechaDesde());
		setFechaHasta(objeto.getFechaHasta());
		setGastosBioquimicosAmbulatorio(objeto
				.getGastosBioquimicosAmbulatorio());
		setGastosBioquimicosInternacion(objeto
				.getGastosBioquimicosInternacion());
		setGastosQuirurgicosAmbulatorio(objeto
				.getGastosQuirurgicosAmbulatorio());
		setGastosQuirurgicosInternacion(objeto
				.getGastosQuirurgicosInternacion());
		setGastosRadiologicosAmbulatorio(objeto
				.getGastosRadiologicosAmbulatorio());
		setGastosRadiologicosInternacion(objeto
				.getGastosRadiologicosInternacion());
		setHematologiaHemoterapiaAmbulatorio(objeto
				.getHematologiaHemoterapiaAmbulatorio());
		setHematologiaHemoterapiaInternacion(objeto
				.getHematologiaHemoterapiaInternacion());
		setHonorarioAmbulatorio(objeto.getHonorarioAmbulatorio());
		setHonorarioInternacion(objeto.getHonorarioInternacion());
		setHonorarioQuirurgicoAmbulatorio(objeto
				.getHonorarioQuirurgicoAmbulatorio());
		setHonorarioQuirurgicoInternacion(objeto
				.getHonorarioQuirurgicoInternacion());
		setHonorariosBioquimicosAmbulatorio(objeto
				.getHonorariosBioquimicosAmbulatorio());
		setHonorariosBioquimicosInternacion(objeto
				.getHonorariosBioquimicosInternacion());
		setMedicamentosAmbulatorio(objeto.getMedicamentosAmbulatorio());
		setMedicamentosInternacion(objeto.getMedicamentosInternacion());
		setPensionAmbulatorio(objeto.getPensionAmbulatorio());
		setPensionInternacion(objeto.getPensionInternacion());
		setPracticasModulosConsultasAmbulatorio(objeto
				.getPracticasModulosConsultasAmbulatorio());
		setPracticasModulosConsultasInternacion(objeto
				.getPracticasModulosConsultasInternacion());
		setValorIVA(objeto.getValorIVA());
		setVersion(objeto.getVersion());
		setIdProducto_OS(objeto.getIdProducto_OS());
		setNombreProducto_OS(objeto.getNombreProducto_OS());
		setNombreObraSocial(objeto.getNombreObraSocial());
		/*
		 * setCoeficientes_vo(new ArrayList<Coeficientes_UA_VO>());
		 * 
		 * for (Coeficientes_UA_VO c : objeto.getCoeficientes_vo()) {
		 * getCoeficientes_vo().add(new Coeficientes_UA_VO(c));
		 * 
		 * }
		 */

	}

	public ContratoDeProducto toObject(int profundidadActual,
			int profundidadDeseada) {
		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

		}

		return this.toObject();
	}

}
