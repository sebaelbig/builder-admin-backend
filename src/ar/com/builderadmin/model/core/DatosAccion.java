package ar.com.builderadmin.model.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import ar.com.builderadmin.model.I_Entidad;

//@Entity
@Table( name="datos_accion")
public class DatosAccion implements I_Entidad{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "datos_accion_seq")
	@SequenceGenerator( name = "datos_accion_seq", sequenceName = "datos_accion_seq", allocationSize = 1)
	@Property(policy = PojomaticPolicy.EQUALS)
	private Long id;

	private Boolean borrado = false;
	
	@Version
	private Integer version;
	
	private String timestamp;
	private String usuario;
	private String detalle;

	public DatosAccion() {}
	
	public DatosAccion(String usuario, String detalle) {
		this.setTimestamp(new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()));
		this.usuario = usuario;
		this.detalle = detalle;
	}

	public DatosAccion(String timestampAccion, String usuarioAccion,
			String detalleAccion) {
		this.timestamp = timestampAccion;
		this.usuario = usuarioAccion;
		this.detalle = detalleAccion;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	@Override
	public String toString() {
		return "["+this.getTimestamp()+"]["+this.getUsuario()+"] "+getDetalle();
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

	
}
