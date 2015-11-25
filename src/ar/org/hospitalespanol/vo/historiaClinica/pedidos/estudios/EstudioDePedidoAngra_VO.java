package ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios;

import java.util.Date;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.EstudioDePedidoAngra;
import ar.org.hospitalespanol.utils.Angra_Service;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class EstudioDePedidoAngra_VO implements I_ValueObject<EstudioDePedidoAngra> {

	/**
	 * Entity ID.
	 */
	private Long id;
	private Boolean borrado = false;
	private Integer version;

	/*********************************************/
	/*                 DATOS DEL ENVIO 			 */
	/**
	 * fecha en la que fue generado
	 */
	private Date fecha;
	private String str_fecha;
	
	/**
	 * fecha en la que fue generado
	 */
	private Date fechaRespuesta;
	private String str_fechaRespuesta;
	
	/**
	 * Datos de la respuesta
	 */
	private String codigo;
	private String detalle;
	
    /**
     * 	Respuesta recibida al invocar a la worklist
     */
    private String respuestaWorklist;
    
	/*********************************************/
    private Long idPedido;
    
    private String numeroPedido;
    private Long idEstudio;
    
	/**
	 *	URL que se armo para pasar al pedido a la Work-list 
	 */
    private String urlWorklist;
    
    /**
     * Codigo unico para acceder al pedido en la worklist
     */
    private String accesionNumber;

	public EstudioDePedidoAngra_VO(EstudioDePedidoAngra ep) {
		setObject(ep);
	}
	
	public EstudioDePedidoAngra_VO(EstudioDePedidoAngra ep, int profAct, int profDes) {
		setObject(ep, profAct, profDes);
	}

	public EstudioDePedidoAngra_VO() {
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the borrado
	 */
	@Override
	public Boolean getBorrado() {
		return borrado;
	}

	/**
	 * @param borrado
	 *            the borrado to set
	 */
	@Override
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	/**
	 * @return the version
	 */
	@Override
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}


	/**
	 * @return the idEstudio
	 */
	public Long getIdEstudio() {
		return idEstudio;
	}

	/**
	 * @param idEstudio
	 *            the idEstudio to set
	 */
	public void setIdEstudio(Long idEstudio) {
		this.idEstudio = idEstudio;
	}

	@Override
	public EstudioDePedidoAngra toObject() {
		EstudioDePedidoAngra ep = new EstudioDePedidoAngra();
		
		ep.setId(getId());
		ep.setVersion(getVersion());
		ep.setBorrado(getBorrado());
		
		ep.setAccesionNumber(getAccesionNumber());
		ep.setFecha(getFecha());
		ep.setIdEstudio(getIdEstudio());
		ep.setIdPedido(getIdPedido());
		ep.setUrlWorklist(getUrlWorklist());

		ep.setCodigo(getCodigo());
		ep.setDetalle(getDetalle());
		ep.setFechaRespuesta(getFechaRespuesta());
		ep.setRespuestaWorklist(getRespuestaWorklist());
		
		return ep;
	}

	@Override
	public void setObject(EstudioDePedidoAngra ep) {
		this.setId(ep.getId());
		this.setVersion(ep.getVersion());
		this.setBorrado(ep.getBorrado());
		
		this.setAccesionNumber(ep.getAccesionNumber());
		this.setFecha(ep.getFecha());
		this.setIdEstudio(ep.getIdEstudio());
		this.setIdPedido(ep.getIdPedido());
		this.setUrlWorklist(ep.getUrlWorklist());

		this.setCodigo(ep.getCodigo());
		this.setDetalle(ep.getDetalle());
		this.setFechaRespuesta(ep.getFechaRespuesta());
		this.setRespuestaWorklist(ep.getRespuestaWorklist());
	}

	@Override
	public void setObject(EstudioDePedidoAngra ep, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(ep);
	}

	@Override
	public String toString() {
		return "Estudio de pedido Angra: "+this.getAccesionNumber() ;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
		if (fecha!=null)
			this.str_fecha = DAO_Utils.formatDateHour(fecha);
	}

	/**
	 * @return the str_fecha
	 */
	public String getStr_fecha() {
		return str_fecha;
	}

	/**
	 * @param str_fecha the str_fecha to set
	 */
	public void setStr_fecha(String str_fecha) {
		this.str_fecha = str_fecha;
	}

	/**
	 * @return the fechaRespuesta
	 */
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	/**
	 * @param fechaRespuesta2 the fechaRespuesta to set
	 */
	public void setFechaRespuesta(Date fechaRespuesta2) {
		this.fechaRespuesta = fechaRespuesta2;
		if (fechaRespuesta2!=null)
			this.str_fechaRespuesta = DAO_Utils.formatDateHour(fechaRespuesta2);
	}

	/**
	 * @return the str_fechaRespuesta
	 */
	public String getStr_fechaRespuesta() {
		return str_fechaRespuesta;
	}

	/**
	 * @param str_fechaRespuesta the str_fechaRespuesta to set
	 */
	public void setStr_fechaRespuesta(String str_fechaRespuesta) {
		this.str_fechaRespuesta = str_fechaRespuesta;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the respuestaWorklist
	 */
	public String getRespuestaWorklist() {
		return respuestaWorklist;
	}

	/**
	 * @param respuestaWorklist the respuestaWorklist to set
	 */
	public void setRespuestaWorklist(String respuestaWorklist) {
		this.respuestaWorklist = respuestaWorklist;
	}

	/**
	 * @return the idPedido
	 */
	public Long getIdPedido() {
		return idPedido;
	}

	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * @return the urlWorklist
	 */
	public String getUrlWorklist() {
		return urlWorklist;
	}

	/**
	 * @param urlWorklist the urlWorklist to set
	 */
	public void setUrlWorklist(String urlWorklist) {
		this.urlWorklist = urlWorklist;
	}

	/**
	 * @return the accesionNumber
	 */
	public String getAccesionNumber() {
		return accesionNumber;
	}

	/**
	 * @param accesionNumber the accesionNumber to set
	 */
	public void setAccesionNumber(String accesionNumber) {
		this.accesionNumber = accesionNumber;
		
		this.numeroPedido = Angra_Service.getIDEstudioParaANGRA(accesionNumber); 
	}

	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}

	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	

}