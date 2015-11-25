package ar.org.hospitalespanol.model.historiaClinica.pedidos;

public class PedidoFiltrado {

    private Long servicio; //idServicio
    private String nombreServicio;
    private String tipoDoc;
    private String nroDoc;
    private String codigo;
    private String accessionNumber;   // Accesion Number cuando busco los de angra
    private String estado;   // descripcion estado
    private String fechaDesde;  // dd/mm/aaaa
    private String fechaHasta;
    private String orden;
    private String modalidad;
    
    private String nombreBusqueda;
    private Integer pagina;
       
    
    public PedidoFiltrado() {
    	
    }
    
	public PedidoFiltrado(Long servicio, String estado, String fechaDesde,
			String fechaHasta, String codigo, String tipoDoc, String nroDoc, String an) {
		this.servicio = servicio;
		this.estado = estado;
		this.codigo= codigo;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.tipoDoc= tipoDoc;
		this.nroDoc = nroDoc;
		this.accessionNumber = an;
	}
	
	public Long getServicio() {
		return servicio;
	}
	public void setServicio(Long servicio) {
		this.servicio = servicio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * @return the nombreBusqueda
	 */
	public String getNombreBusqueda() {
		return nombreBusqueda;
	}

	/**
	 * @param nombreBusqueda the nombreBusqueda to set
	 */
	public void setNombreBusqueda(String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	/**
	 * @return the pagina
	 */
	public Integer getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return the accessionNumber
	 */
	public String getAccessionNumber() {
		return accessionNumber;
	}

	/**
	 * @param accessionNumber the accessionNumber to set
	 */
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
}
