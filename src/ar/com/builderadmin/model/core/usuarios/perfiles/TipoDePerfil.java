package ar.com.builderadmin.model.core.usuarios.perfiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;
import ar.com.builderadmin.model.core.usuarios.roles.TipoDeRol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

/**
 * Estudio
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:39 a.m.
 */
@Entity
@Table( name = "tipo_de_perfil")
public class TipoDePerfil implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_tipo_de_perfil", name = "seq_tipo_de_perfil", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_de_perfil")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 35)
	private String nombre;

	@Column(length = 10)
	private String codigo;

	/**
	 * Conjunto de funciones por defecto
	 */
	@ManyToMany
	@JoinTable(
			
		name = "tipo_perfil_fx", 
		joinColumns = @JoinColumn(name = "id_tipo_perfil"), 
		inverseJoinColumns = @JoinColumn(name = "id_fx", unique = false), 
		uniqueConstraints = @UniqueConstraint(columnNames = {"id_tipo_perfil", "id_fx" })
	)
	private List<FuncionHorus> funciones = new ArrayList<FuncionHorus>();

	@Column(name = "id_sucursal")
	private Long idSucursal;

	/**
	 * Tipo de Rol por defecto
	 */
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_tipo_de_rol")
	private TipoDeRol tipoRol;
	
	// ---------------- CONSTRUCTOR
	public TipoDePerfil() {
		super();
	}

	public TipoDePerfil(String nombre) {
		super();
		this.nombre = nombre;
	}

	public TipoDePerfil(Long id2, Integer version2, String codigo2,
			String nombre2, List<FuncionHorus> fxs) {
		this.setId(id2);
		this.setVersion(version2);
		this.setCodigo(codigo2);
		this.setNombre(nombre2);
		this.setFunciones(fxs);
	}

	// ------------------------ OPERACIONES
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoDePerfil) {
			TipoDePerfil o = (TipoDePerfil) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		return "Tipo rol: Codigo = " + this.getCodigo() + ", Nombre = "
				+ this.getNombre();
	}

	// ---------------- GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<FuncionHorus> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<FuncionHorus> funciones) {
		this.funciones = funciones;
	}

	public TipoDePerfil_VO toValueObject() {
		return new TipoDePerfil_VO(this);
	}

	public TipoDePerfil_VO toValueObject(int profundidadActual,
			int profundidadDesada) {
		return new TipoDePerfil_VO(this, profundidadActual, profundidadDesada);
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the tipoRol
	 */
	public TipoDeRol getTipoRol() {
		return tipoRol;
	}

	/**
	 * @param tipoRol the tipoRol to set
	 */
	public void setTipoRol(TipoDeRol tipoRol) {
		this.tipoRol = tipoRol;
	}
	
	
}
