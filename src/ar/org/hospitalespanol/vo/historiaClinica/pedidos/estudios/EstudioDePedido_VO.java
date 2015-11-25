package ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios;

import java.util.Date;

import ar.org.hospitalespanol.model.I_Bloqueable;
import ar.org.hospitalespanol.model.core.DatosAccion;
import ar.org.hospitalespanol.model.core.datosSalud.TipoPrestacionHorus;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.Pedido;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;

public class EstudioDePedido_VO implements I_ValueObject<EstudioDePedido>, I_Bloqueable {

	/**
	 * Entity ID.
	 */
	private Long id;
	private Boolean borrado = false;
	private Integer version;

	/**
	 * Datos del pedido
	 */
	private Pedido_VO pedido;

	/**
	 * Estudio
	 */
	private Long idEstudio;
	private String codigoEstudio;
	private String nombreEstudio;

	private Integer numeroItem;
	private String estado;
	private String motivo;
	private String fecha;
	
	private Date dt_fecha;
	private String texto;

	/*********************************************/
	/*  *  */
	private Boolean bloqueado = false;
	
	private String timestampAccion;
	private String usuarioAccion;
	private String detalleAccion;
	
	
	/*********************************************/
	
	/**	
     * URL para invocar al Visor ClearCanvas
     * http://172.20.43.50/Pages/WebViewer/Launch.aspx?
     * 	username=xx
     * &password=yy
     * &
			&WebViewerInitParams=application=epr,aetitle=PACS_HELP,accession=9017050,liststudies=false
			
			IP	IP del PACS actualmente 172.20.43.50
			username	Nombre de un usuario con permiso para visualizar imagenes
			Password	Contraseña de username
			aetitle	aetitle del PACS acualmente PACS_HELP
			accession	ID del estudio, dato univoco
     */
    private String urlVisor;
	
	public EstudioDePedido_VO(EstudioDePedido ep) {
		setObject(ep);
	}
	
	public EstudioDePedido_VO(EstudioDePedido ep, int profAct, int profDes) {
		setObject(ep, profAct, profDes);
	}

	public EstudioDePedido_VO() {
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the pedido
	 */
	public Pedido_VO getPedido() {
		return pedido;
	}

	/**
	 * @param pedido
	 *            the pedido to set
	 */
	public void setPedido(Pedido_VO pedido) {
		this.pedido = pedido;
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

	/**
	 * @return the codigoEstudio
	 */
	public String getCodigoEstudio() {
		return codigoEstudio;
	}

	/**
	 * @param codigoEstudio
	 *            the codigoEstudio to set
	 */
	public void setCodigoEstudio(String codigoEstudio) {
		this.codigoEstudio = codigoEstudio;
	}

	/**
	 * @return the nombreEstudio
	 */
	public String getNombreEstudio() {
		return nombreEstudio;
	}

	/**
	 * @param nombreEstudio
	 *            the nombreEstudio to set
	 */
	public void setNombreEstudio(String nombreEstudio) {
		this.nombreEstudio = nombreEstudio;
	}

	@Override
	public EstudioDePedido toObject() {
		EstudioDePedido resul;

		Pedido p = this.getPedido().toObject(PROFUNDIDAD_BASE, 0);
		TipoPrestacionHorus estudio = new TipoPrestacionHorus(
				this.getIdEstudio(), this.getCodigoEstudio(),
				this.getNombreEstudio());
		
		resul = new EstudioDePedido(p, estudio);
		
		resul.setId(getId());
		resul.setVersion(getVersion());
		resul.setBorrado(getBorrado());
		
		resul.setEstado(getEstado());
		resul.setFecha(getFecha());
		resul.setMotivo(getMotivo());
		resul.setDt_fecha(this.getDt_fecha()); 
		resul.setTexto(this.getTexto());
		resul.setNumeroItem(getNumeroItem());

		resul.setBloqueado(getBloqueado());
		if (this.getTimestampAccion()!=null){
			resul.setData(new DatosAccion(this.getTimestampAccion(), this.getUsuarioAccion(), this.getDetalleAccion()));
		}
		
		resul.setUrlVisor(getUrlVisor());
		return resul;
	}

	public EstudioDePedido toObject(int profundidadActual, int profundidadDeseada) {
		return toObject();
	}
	
	@Override
	public void setObject(EstudioDePedido ep) {
		
		this.setPedido(ep.getPedido().toValueObject(PROFUNDIDAD_BASE, 0));
		
		if (ep.getEstudio()!=null){
			this.setIdEstudio(ep.getEstudio().getId());
			this.setCodigoEstudio(ep.getEstudio().getCodigo());
			this.setNombreEstudio(ep.getEstudio().getNombre());
		}
		
		this.setId(ep.getId());
		this.setVersion(ep.getVersion());
		this.setBorrado(ep.getBorrado());
		
		this.setEstado(ep.getEstado());
		this.setFecha(ep.getFecha());
		this.setMotivo(ep.getMotivo());
		this.setDt_fecha(ep.getDt_fecha()); 
		this.setTexto(ep.getTexto());
		this.setNumeroItem(ep.getNumeroItem());

		this.setBloqueado(ep.getBloqueado());
		if (ep.getData()!=null){
			this.setTimestampAccion(ep.getData().getTimestamp());
			this.setUsuarioAccion(ep.getData().getUsuario());
			this.setDetalleAccion(ep.getData().getDetalle());
		}
		
		this.setUrlVisor(ep.getUrlVisor());
	}

	@Override
	public void setObject(EstudioDePedido ep, int profundidadActual,
			int profundidadDeseada) {
		
		this.setPedido(ep.getPedido().toValueObject(PROFUNDIDAD_BASE, 0));

		if (profundidadActual<profundidadDeseada){
				
			if (ep.getEstudio()!=null){
				this.setIdEstudio(ep.getEstudio().getId());
				this.setCodigoEstudio(ep.getEstudio().getCodigo());
				this.setNombreEstudio(ep.getEstudio().getNombre());
			}
			
		}
		
		this.setId(ep.getId());
		this.setVersion(ep.getVersion());
		this.setBorrado(ep.getBorrado());
		
		this.setEstado(ep.getEstado());
		this.setFecha(ep.getFecha());
		this.setMotivo(ep.getMotivo());
		
		this.setDt_fecha(ep.getDt_fecha()); 
		this.setTexto(ep.getTexto());
		this.setNumeroItem(ep.getNumeroItem());
		
		this.setBloqueado(ep.getBloqueado());
		if (ep.getData()!=null){
			this.setTimestampAccion(ep.getData().getTimestamp());
			this.setUsuarioAccion(ep.getData().getUsuario());
			this.setDetalleAccion(ep.getData().getDetalle());
		}
		
		this.setUrlVisor(ep.getUrlVisor());
	}

	@Override
	public String toString() {
		return "Pedido: "+this.getPedido().getNumero()+" ("+this.getCodigoEstudio()+") "+this.getNombreEstudio();
	}

	/**
	 * @return the dt_fecha
	 */
	public Date getDt_fecha() {
		return dt_fecha;
	}

	/**
	 * @param dt_fecha the dt_fecha to set
	 */
	public void setDt_fecha(Date dt_fecha) {
		this.dt_fecha = dt_fecha;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public boolean estaCancelado() {
		return this.getEstado().equalsIgnoreCase(EstudioDePedido.CANCELADO);
	}

	public boolean estaInformado() {
		return this.getEstado().equalsIgnoreCase(EstudioDePedido.INFORMADO);
	}

	public boolean estaFinalizado() {
		return this.getEstado().equalsIgnoreCase(EstudioDePedido.CANCELADO) || this.getEstado().equalsIgnoreCase(EstudioDePedido.INFORMADO);
	}

	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}

	/**
	 * @return the bloqueado
	 */
	@Override
	public Boolean getBloqueado() {
		return bloqueado;
	}

	/**
	 * @param bloqueado the bloqueado to set
	 */
	@Override
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @return the timestampAccion
	 */
	public String getTimestampAccion() {
		return timestampAccion;
	}

	/**
	 * @param timestampAccion the timestampAccion to set
	 */
	public void setTimestampAccion(String timestampAccion) {
		this.timestampAccion = timestampAccion;
	}

	/**
	 * @return the usuarioAccion
	 */
	public String getUsuarioAccion() {
		return usuarioAccion;
	}

	/**
	 * @param usuarioAccion the usuarioAccion to set
	 */
	public void setUsuarioAccion(String usuarioAccion) {
		this.usuarioAccion = usuarioAccion;
	}

	/**
	 * @return the detalleAccion
	 */
	public String getDetalleAccion() {
		return detalleAccion;
	}

	/**
	 * @param detalleAccion the detalleAccion to set
	 */
	public void setDetalleAccion(String detalleAccion) {
		this.detalleAccion = detalleAccion;
	}

	@Override
	public DatosAccion getDatosAccion() {
		return new DatosAccion(this.getTimestampAccion(), this.getUsuarioAccion(), this.getDetalleAccion());
	}

	@Override
	public void setDatosAccion(DatosAccion data) {
		this.setTimestampAccion(data.getTimestamp());
		this.setUsuarioAccion(data.getUsuario());
		this.setDetalleAccion(data.getDetalle());
	}

	/**
	 * @return the urlVisor
	 */
	public String getUrlVisor() {
		return urlVisor;
	}

	/**
	 * @param urlVisor the urlVisor to set
	 */
	public void setUrlVisor(String urlVisor) {
		this.urlVisor = urlVisor;
	}
	
	
	
}