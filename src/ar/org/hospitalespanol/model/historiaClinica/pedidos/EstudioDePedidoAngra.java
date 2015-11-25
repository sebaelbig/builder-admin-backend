package ar.org.hospitalespanol.model.historiaClinica.pedidos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

@Entity
@Table(name="estudio_pedido_angra")
public class EstudioDePedidoAngra {

	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_angra_seq")
	@SequenceGenerator( name = "pedido_angra_seq", sequenceName = "pedido_angra_seq", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;
	
	@Version
	private Integer version;
	
	/*********************************************/
	/*                 DATOS DEL ENVIO 			 */
	/**
	 * fecha en la que fue generado
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	/**
	 * fecha en la que fue generado
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_respuesta")
	private Date fechaRespuesta;
	
	/**
	 * Datos de la respuesta
	 */
	private String codigo;
	private String detalle;
	
    /**
     * 	Respuesta recibida al invocar a la worklist
     */
	@Column(name="respuesta_worklist")
    private String respuestaWorklist;
    
	/*********************************************/

	/**
	 * id pedido enviado a Angra
	 */
	@Column(name="id_pedido")
    private Long idPedido; 

    /**
     * id del estudio
     */
	@Column(name="id_estudio")
    private Long idEstudio;
    
	/**
	 *	URL que se armo para pasar al pedido a la Work-list 
	 */
	@Column(name="url_worklist", columnDefinition="text")
    private String urlWorklist;
    
    /**
     * Codigo unico para acceder al pedido en la worklist
     */
	@Column(name="accesion_number")
    private String accesionNumber;
    
    public EstudioDePedidoAngra() {    }

    public EstudioDePedidoAngra(Long idPedido, Long idEstudioPedido, String an, String url) {
    	
    	this.setFecha(new Date());
    	
    	this.setIdPedido(idPedido);
    	this.setIdEstudio(idEstudioPedido);
    	
    	this.setAccesionNumber(an);
    	this.setUrlWorklist(url);
    }


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the borrado
	 */
	public Boolean getBorrado() {
		return borrado;
	}


	/**
	 * @param borrado the borrado to set
	 */
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}


	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
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
	 * @return the idEstudio
	 */
	public Long getIdEstudio() {
		return idEstudio;
	}

	/**
	 * @param idEstudio the idEstudio to set
	 */
	public void setIdEstudio(Long idEstudio) {
		this.idEstudio = idEstudio;
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
	 * @return the fechaRespuesta
	 */
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	/**
	 * @param fechaRespuesta the fechaRespuesta to set
	 */
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}


	
}
