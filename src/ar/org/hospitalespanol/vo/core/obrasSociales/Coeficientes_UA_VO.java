package ar.org.hospitalespanol.vo.core.obrasSociales;

import ar.org.hospitalespanol.model.core.obrasSociales.Coeficientes_UA;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class Coeficientes_UA_VO implements I_ValueObject<Coeficientes_UA> {
	
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	
	private Integer version;
	
	private Float porcentaje;
	
	private Long idContrato;
	
	private TipoCoeficiente_VO tipoCoeficiente_VO;
	

	public Coeficientes_UA_VO(){}
	
	public Coeficientes_UA_VO(Coeficientes_UA coeficientes_UA){
		setObject(coeficientes_UA);
	}
	
	public Coeficientes_UA_VO(TipoCoeficiente_VO tipo) {
		this.setTipoCoeficiente_VO(tipo);
		this.setPorcentaje(new Float(0));
	}

	public Coeficientes_UA_VO(Coeficientes_UA_VO c) {
		setPorcentaje(c.getPorcentaje());
		setTipoCoeficiente_VO(c.getTipoCoeficiente_VO());
	}

	public Coeficientes_UA_VO(Coeficientes_UA coef, int profActual, int profundidadDeseada) {
		setObject(coef, profActual, profundidadDeseada);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(Coeficientes_UA objeto) {
		setId(objeto.getId());
		setPorcentaje(objeto.getPorcentaje());
		setVersion(objeto.getVersion());
		setIdContrato(objeto.getId());
		setTipoCoeficiente_VO(objeto.getTipoCoeficiente().toValueObject());
	}

	@Override
	public Coeficientes_UA toObject() {
		return new Coeficientes_UA(getId(),getVersion(),getPorcentaje(),getTipoCoeficiente_VO().toObject());
	}

	@Override
	public String toString() {
		return getTipoCoeficiente_VO().getCodigo()+" - "+getPorcentaje();
	}
	
	
	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public TipoCoeficiente_VO getTipoCoeficiente_VO() {
		return tipoCoeficiente_VO;
	}

	public void setTipoCoeficiente_VO(TipoCoeficiente_VO tipoCoeficiente_VO) {
		this.tipoCoeficiente_VO = tipoCoeficiente_VO;
	}

	@Override
	public void setObject(Coeficientes_UA objeto, int profundidadActual, int profundidadDeseada) {
		setId(objeto.getId());
		setPorcentaje(objeto.getPorcentaje());
		setVersion(objeto.getVersion());
		setIdContrato(objeto.getId());
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE && profundidadActual < profundidadDeseada  ){
			setTipoCoeficiente_VO(objeto.getTipoCoeficiente().toValueObject(profundidadActual+1, profundidadDeseada));
		}	
	}

	

	

	
}
