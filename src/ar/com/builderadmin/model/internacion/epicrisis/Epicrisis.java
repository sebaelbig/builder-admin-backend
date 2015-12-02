package ar.com.builderadmin.model.internacion.epicrisis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.vo.internacion.epicrisis.Epicrisis_VO;

/**
 * 
 * @author carlalu
 * 
 */
//@Entity
public class Epicrisis implements I_Entidad, Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	public Boolean getBorrado() {
		return this.borrado;
	}

	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	@Id
	@SequenceGenerator( sequenceName = "epicrisis_sec", name = "epicrisis_sec", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epicrisis_sec")
	private Long id;

	@Version
	private Integer version;

	private Integer carpeta;
	private String evolucion;
	@Column(name = "estudios_complementarios")
	private String estudiosComplementarios;
	@Column(name = "enfermedad_actual")
	private String enfermedadActual;
	private String tratamiento;
	@Column(name = "indicaciones_alta")
	private String indicacionesAlta;
	@Column(name = "pendientes")
	private String estudiosPendientes;
	@Column(name = "proximo_control")
	private String proximoControl;
	@Column(name = "contacto")
	private String telefonoContacto;
	private String usuario;
	@Column(name = "fecha_modificacion")
	private String fechaModificacion;
	@Column(name = "tipo_documento")
	private int tipoDocumento;
	@Column(name = "numero_documento")
	private int numeroDocumento;
	private boolean cerrado;

	public Epicrisis() {

	}
	
	public String getEnfermedadActual() {
		return enfermedadActual;
	}

	public void setEnfermedadActual(String enfermedadActual) {
		this.enfermedadActual = enfermedadActual;
	}

	public Epicrisis_VO toValueObject() {
		return new Epicrisis_VO(this);
	}
	
	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

	public String getEstudiosComplementarios() {
		return estudiosComplementarios;
	}

	public void setEstudiosComplementarios(String estudiosComplementarios) {
		this.estudiosComplementarios = estudiosComplementarios;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getIndicacionesAlta() {
		return indicacionesAlta;
	}

	public void setIndicacionesAlta(String indicacionesAlta) {
		this.indicacionesAlta = indicacionesAlta;
	}

	public String getEstudiosPendientes() {
		return estudiosPendientes;
	}

	public void setEstudiosPendientes(String estudiosPendientes) {
		this.estudiosPendientes = estudiosPendientes;
	}

	public String getProximoControl() {
		return proximoControl;
	}

	public void setProximoControl(String proximoControl) {
		this.proximoControl = proximoControl;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public void setCarpeta(Integer carpeta) {
		this.carpeta = carpeta;
	}

	public Integer getCarpeta() {
		return carpeta;
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
		
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public boolean getCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}
	
	
}
