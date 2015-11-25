package ar.org.hospitalespanol.model.historiaClinica.pedidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

import ar.org.hospitalespanol.model.I_Bloqueable;
import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.DatosAccion;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;

@Entity
@Table
public class Pedido implements I_Entidad, I_Bloqueable{

	public static final String EN_ATENCION = "En Atención";
	public static final String ATENDIDO = "Atendido";
	public static final String INFORMADO = "Informado";
	public static final String CANCELADO = "Cancelado";

	/**
	 * Entity ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
	@SequenceGenerator( name = "pedido_seq", sequenceName = "pedido_seq", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;
	
	@Version
	private Integer version;

	/**
	 * Numero del pedido
	 */
	private String numero;

	@Column(name = "tipo_dni_paciente")
	private String tipoDniPaciente;

	@Column(name = "nro_dni_paciente")
	private String nroDniPaciente;

	@Column(name = "nro_carpeta")
	private String nroCarpeta;

	@Column(name = "fecha_pedida")
	private String fechaPedida; // dd/MM/yyyy

	@Column(name = "dt_fecha_pedida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_fechaPedida;
	
	@Column(name = "dt_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dt_fecha;

	@Column(name = "matricula_profesional_solicitante")
	private String matriculaProfesionalSolicitante;
	
	@Column(name = "mail_solicitante")
	private String mailSolicitante;

	@Column(name = "matricula_profesional_actuante")
	private String matriculaProfesionalActuante;

	private String usuario;
	private String estado;
	private String modalidad;
	private String equipo;

	@OneToMany(mappedBy="pedido", fetch=FetchType.EAGER)
	@JoinFetch(JoinFetchType.OUTER)
	private List<EstudioDePedido> estudiosPaciente;
	
	private String estudios;

	@OneToOne(optional=false)
	@JoinColumn(name = "id_servicio")
	@JoinFetch(JoinFetchType.INNER)
	private Servicio servicio;
	
	private String paciente;
	private String actuante;
	
	@Column(name="firma_efector", columnDefinition="text")
	private String firmaEfector;
	
	private String solicitante;
	private String fecha;

	@Column(columnDefinition = "text")
	private String texto;
	
	private Boolean urgente;
	
	/**
	 * Informacion de estado
	 */
	private Boolean bloqueado = false;
	
	@OneToOne
	@JoinColumn(name = "id_datos_accion")
	@JoinFetch(JoinFetchType.OUTER)
	private DatosAccion data;
	
	public Pedido() {
		this.setEstado(EN_ATENCION);
		
		this.setEstudiosPaciente(new ArrayList<EstudioDePedido>());
	}

	public Pedido_VO toValueObject() {
		return new Pedido_VO(this);
	}
	public Pedido_VO toValueObject(int profAc, int profDes) {
		return new Pedido_VO(this, profAc, profDes);
	}

	/**
	 * @return the tipoDniPaciente
	 */
	public String getTipoDniPaciente() {
		return tipoDniPaciente;
	}

	/**
	 * @param tipoDniPaciente
	 *            the tipoDniPaciente to set
	 */
	public void setTipoDniPaciente(String tipoDniPaciente) {
		this.tipoDniPaciente = tipoDniPaciente;
	}

	/**
	 * @return the nroDniPaciente
	 */
	public String getNroDniPaciente() {
		return nroDniPaciente;
	}

	/**
	 * @param nroDniPaciente
	 *            the nroDniPaciente to set
	 */
	public void setNroDniPaciente(String nroDniPaciente) {
		this.nroDniPaciente = nroDniPaciente;
	}

	/**
	 * @return the nroCarpeta
	 */
	public String getNroCarpeta() {
		return nroCarpeta;
	}

	/**
	 * @param nroCarpeta
	 *            the nroCarpeta to set
	 */
	public void setNroCarpeta(String nroCarpeta) {
		this.nroCarpeta = nroCarpeta;
	}

	/**
	 * @return the fechaPedida
	 */
	public String getFechaPedida() {
		return fechaPedida;
	}

	/**
	 * @param fechaPedida
	 *            the fechaPedida to set
	 */
	public void setFechaPedida(String fechaPedida) {
		this.fechaPedida = fechaPedida;
		
	}

	/**
	 * @return the dt_fechaPedida
	 */
	public Date getDt_fechaPedida() {
		return dt_fechaPedida;
	}

	/**
	 * @param dt_fechaPedida
	 *            the dt_fechaPedida to set
	 */
	public void setDt_fechaPedida(Date dt_fechaPedida) {
		this.dt_fechaPedida = dt_fechaPedida;
	}

	/**
	 * @return the matriculaProfesionalSolicitante
	 */
	public String getMatriculaProfesionalSolicitante() {
		return matriculaProfesionalSolicitante;
	}

	/**
	 * @param matriculaProfesionalSolicitante
	 *            the matriculaProfesionalSolicitante to set
	 */
	public void setMatriculaProfesionalSolicitante(
			String matriculaProfesionalSolicitante) {
		this.matriculaProfesionalSolicitante = matriculaProfesionalSolicitante;
	}

	/**
	 * @return the matriculaProfesionalActuante
	 */
	public String getMatriculaProfesionalActuante() {
		return matriculaProfesionalActuante;
	}

	/**
	 * @param matriculaProfesionalActuante
	 *            the matriculaProfesionalActuante to set
	 */
	public void setMatriculaProfesionalActuante(
			String matriculaProfesionalActuante) {
		this.matriculaProfesionalActuante = matriculaProfesionalActuante;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad
	 *            the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo
	 *            the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the estudiosPaciente
	 */
	public List<EstudioDePedido> getEstudiosPaciente() {
		return estudiosPaciente;
	}

	/**
	 * @param estudiosPaciente
	 *            the estudiosPaciente to set
	 */
	public void setEstudiosPaciente(List<EstudioDePedido> estudiosPaciente) {
		this.estudiosPaciente = estudiosPaciente;
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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante
	 *            the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
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
	 * @return the estudios
	 */
	public String getEstudios() {
		return estudios;
	}

	/**
	 * @param estudios
	 *            the estudios to set
	 */
	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

	/**
	 * @return the servicio
	 */
	public Servicio getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the actuante
	 */
	public String getActuante() {
		return actuante;
	}

	/**
	 * @param actuante the actuante to set
	 */
	public void setActuante(String actuante) {
		this.actuante = actuante;
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
	 * @return the firmaEfector
	 */
	public String getFirmaEfector() {
		return firmaEfector;
	}

	/**
	 * @param firmaEfector the firmaEfector to set
	 */
	public void setFirmaEfector(String firmaEfector) {
		this.firmaEfector = firmaEfector;
	}

	public Date getDt_fecha() {
		return dt_fecha;
	}

	public void setDt_fecha(Date dt_fecha) {
		this.dt_fecha = dt_fecha;
	}

	/**
	 * @return the urgente
	 */
	public Boolean getUrgente() {
		return urgente;
	}

	/**
	 * @param urgente the urgente to set
	 */
	public void setUrgente(Boolean urgente) {
		this.urgente = urgente;
	}

	public String getMailSolicitante() {
		return mailSolicitante;
	}

	public void setMailSolicitante(String mailSolicitante) {
		this.mailSolicitante = mailSolicitante;
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
	
}