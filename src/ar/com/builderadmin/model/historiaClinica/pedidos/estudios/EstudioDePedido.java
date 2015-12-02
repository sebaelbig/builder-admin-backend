package ar.com.builderadmin.model.historiaClinica.pedidos.estudios;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import ar.com.builderadmin.model.I_Bloqueable;
import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.model.core.DatosAccion;
import ar.com.builderadmin.model.core.datosSalud.TipoPrestacionHorus;
import ar.com.builderadmin.model.historiaClinica.pedidos.Pedido;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

//@Entity
@Table( name="estudio_de_pedido")
public class EstudioDePedido implements I_Entidad, I_Bloqueable{

	public static final String EN_ATENCION = "En Atención";
	public static final String ATENDIDO = "Atendido";
	
	public static final String INFORMADO = "Informado";
	public static final String CANCELADO = "Cancelado";
//	
//	public static final String EN_WORKLIST = "En la WorkList";
//	public static final String ANULADO = "Anulado";
//	public static final String REALIZADO = "Realizado";
	
	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudio_de_pedido_seq")
	@SequenceGenerator( name = "estudio_de_pedido_seq", sequenceName = "estudio_de_pedido_seq", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;

	@Version
	private Integer version;

	/**
	 * Numero del pedido
	 */
	@ManyToOne(optional=false)
	@JoinColumn(name = "id_pedido")
	@JoinFetch(JoinFetchType.INNER)
	private Pedido pedido;

	@ManyToOne(optional=false)
	@JoinColumn(name = "id_estudio")
	@JoinFetch(JoinFetchType.INNER)
	private TipoPrestacionHorus estudio;
	
	private String estado = EN_ATENCION;
	
	@Column(length=150)
	private String motivo;
	
	@Column(length=15)
	private String fecha;
	
	@Column(name = "dt_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_fecha;
	
	@Column(columnDefinition = "text")
	private String texto;
	
	@Column(name = "numero_item")
	private Integer numeroItem;
	
	private Boolean bloqueado = false;
	
	@OneToOne
	@JoinColumn(name = "id_datos_accion")
	private DatosAccion data;
	
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
	@Column(name="url_visor", columnDefinition="text")
    private String urlVisor;
	
	public EstudioDePedido() {
		this.setEstado(EN_ATENCION );
		
		Date f = new Date();
		this.setFecha(new SimpleDateFormat("dd/MM/yy HH:mm").format(f));
		this.setDt_fecha(f);
	}

	public EstudioDePedido(Pedido p, TipoPrestacionHorus estudio) {
		this.setPedido(p);
		this.setEstudio(estudio);
		
		this.setEstado(EN_ATENCION);
		
		Date f = new Date();
		this.setFecha(new SimpleDateFormat("dd/MM/yy HH:mm").format(f));
		this.setDt_fecha(f);
		
		this.setTexto(p.getTexto());
		
	}

	public EstudioDePedido_VO toValueObject() {
		return new EstudioDePedido_VO(this);
	}


	public EstudioDePedido_VO toValueObject(int i, int profundidadDeseada) {
		return new EstudioDePedido_VO(this, i, profundidadDeseada);
	}
	
	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param borrado the borrado to set
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
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return the estudio
	 */
	public TipoPrestacionHorus getEstudio() {
		return estudio;
	}

	/**
	 * @param estudio the estudio to set
	 */
	public void setEstudio(TipoPrestacionHorus estudio) {
		this.estudio = estudio;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
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
	 * @param motivo the motivo to set
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
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * @return the data
	 */
	public DatosAccion getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(DatosAccion data) {
		this.data = data;
	}

	@Override
	public DatosAccion getDatosAccion() {
		return getData();
	}

	@Override
	public void setDatosAccion(DatosAccion data) {
		this.setData(data);
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