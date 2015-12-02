package ar.com.builderadmin.model.core.usuarios.perfiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

/**
 * Representa los perfiles que puede haber en el sistema
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:38 a.m.
 */
//@Entity
@Table
public class Perfil implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_perfil", name = "seq_perfil", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil")
	private Long id;

	@Version()
	private Integer version;

	/**
	 * Nombre del perfil
	 */
	@Column(length = 200)
	private String nombre;

	/**
	 * Codigo del eprfil
	 */
	@Column(length = 10)
	private String codigo;

	/**
	 * Rol del perfil
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "id_rol")
	private Rol rol;

	/**
	 * Tipo de perfil
	 */
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_perfil")
	@JoinFetch(JoinFetchType.INNER)
	private TipoDePerfil tipoPerfil;

	/**
	 * Conjunto de funciones por defecto
	 */
	@ManyToMany
	@JoinTable(name = "perfil_fx", 
		joinColumns = @JoinColumn(name = "id_perfil"), 
		inverseJoinColumns = @JoinColumn(name = "id_fx", unique = false), 
		uniqueConstraints = @UniqueConstraint(columnNames = {"id_perfil", "id_fx" })
	)
	@JoinFetch(JoinFetchType.OUTER)
	private List<FuncionHorus> funciones = new ArrayList<FuncionHorus>();

	/**
	 * Servicio
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_servicio")
	@JoinFetch(JoinFetchType.INNER)
	private Servicio servicio;

	// ------------- CONSTRUCTORES
	public Perfil() {

	}

	public Perfil(Long id2, Integer version2, TipoDePerfil tipo) {
		this.setId(id2);
		this.setVersion(version2);
		this.setTipoPerfil(tipo);

	}

	public Perfil(Long id1, Integer version, TipoDePerfil tipo, Servicio servicio2) {
		this.setTipoPerfil(tipo);
		this.setId(id1);
		this.setVersion(version);
		this.setTipoPerfil(tipo);
		this.setServicio(servicio2);
	}

	public Perfil(Long id2, Integer version2) {
		this.setId(id2);
		this.setVersion(version2);
	}

	public Perfil(TipoDePerfil tipo) {
		this.setTipoPerfil(tipo);
	}

	public Perfil_VO toValueObject() {
		return new Perfil_VO(this);
	}

	public Perfil_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new Perfil_VO(this, profundidadActual, profundidadDeseada);
	}

	// -------------- GETTERS Y SETTERS
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Perfil) {
			Perfil rol = (Perfil) object;
			return rol.getId().equals(this.getId());

		}
		return false;
	}

	@Override
	public String toString() {
		return getTipoPerfil().toString();
	}

	public TipoDePerfil getTipoPerfil() {
		return tipoPerfil;
	}

	public void setTipoPerfil(TipoDePerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;

//		if (tipoPerfil != null) {
//			this.setNombre(tipoPerfil.getNombre());
//			this.setCodigo(tipoPerfil.getCodigo());
//			// this.cargarFunciones(tipoPerfil);
//		}
	}

	public boolean tieneServicio() {
		return false;
	}

	public List<FuncionHorus> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<FuncionHorus> funciones) {
		this.funciones = funciones;
	}

	public boolean tieneFuncion(FuncionHorus fx) {
		return this.getFunciones().contains(fx);
	}

	public void agregarFuncion(FuncionHorus fx) {
		this.getFunciones().add(fx);
	}

	public void quitarFuncion(FuncionHorus fx) {
		this.getFunciones().remove(fx);
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Servicio getServicio() {
		return this.servicio;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}