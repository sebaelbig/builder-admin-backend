package ar.org.hospitalespanol.model.core.usuarios.roles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

/**
 * Representa los roles que puede haber en el sistema
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:38 a.m.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_rol", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("rol_base")
public class Rol implements Serializable, I_Entidad{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_rol", name = "seq_rol", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rol")
	private Long id;

	@Version()
	private Integer version;

	public static final String DIRECTIVO = "Directivo";
	public static final String PACIENTE = "Paciente";
	public static final String PROFESIONAL = "Profesional";
	public static final String SECRETARIA = "Secretaria";
	public static final String PERSONAL_CONTABLE = "Personal contable";
	public static final String ADMINISTRADOR_CONSULTORIOS = "Administrador de consultorios";
	public static final String SOLICITANTE = "Solicitante";
	public static final String ADMINISTRADOR_HORUS = "Administrador Horus";

	/**
	 * Nombre del rol el cual se autentica
	 */
	@Column(length = 200)
	private String nombre;

	/**
	 * Codigo del rol el cual se autentica
	 */
	@Column(length = 10)
	private String codigo;

	/**
	 * Usuario del rol
	 */
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	@JoinFetch(JoinFetchType.INNER)
	private Usuario usuario;

	/**
	 * perfiles que tiene el usuario
	 */
	@OneToMany(mappedBy = "rol", fetch=FetchType.EAGER)
	private List<Perfil> perfiles = new ArrayList<Perfil>();

	/**
	 * Tipo de rol
	 */
	@OneToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_rol")
	@JoinFetch(value=JoinFetchType.INNER)
	private TipoDeRol tipoRol;

	
	/**
	 * Tipo de ID por defecto
	 */
	@ManyToOne
	@JoinColumn(nullable = true, name = "tipo_de_id")
	@JoinFetch(JoinFetchType.INNER)
	private TipoIDHE tipoID;
	
	/**
	 * Nombre del rol el cual se autentica
	 */
	@Column(length = 200, name="valor_tipo_id")
	private String valorTipoID;
	
	/***********************************************************/
	//ESTAN EN EL TIPO
//	public static final String ACTIVO = "ACTIVO";
//	public static final String INACTIVO = "INACTIVO";
	
//	private String id_rol; Ahora es el Codigo
//	private String rol; Ahora es el Nombre
//	private String estado;
//	private String sitio;
//	private String prioridad;
//	private String login;
//	
//	@Column(name="fecha_modifico")
//	private String fechaModifico;
	
	
	// ------------- CONSTRUCTOR
	public Rol() {
	}

	public Rol(Long id2, Integer version2, TipoDeRol tipo) {
		this.setId(id2);
		this.setVersion(version2);
		this.setTipoRol(tipo);
	}

	public Rol(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rol(Long id2, Integer version2, Boolean boolean1) {
		this.setId(id2);
		this.setVersion(version2);
		this.setBorrado(boolean1);
	}

	public Rol(TipoDeRol tipo) {
		this.setTipoRol(tipo);
	}

	public Rol(TipoDePerfil tipo) {
		Perfil p = new Perfil(tipo);

		p.setRol(this);

		this.agregarPerfil(p);
	}

	public void agregarPerfil(Perfil p) {
		this.getPerfiles().add(p);
	}

	public void quitarPerfil(Perfil p) {
		this.getPerfiles().remove(p);
	}

	// -------------- GETTERS Y SETTERS
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@Override
	public boolean equals(Object object) {
		if (object instanceof Rol) {
			Rol rol = (Rol) object;
			return rol.getId().equals(this.getId());

		}
		return false;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public boolean tienePerfil(TipoDePerfil tp) {
		boolean resul = false;

		for (Perfil p : this.getPerfiles()) {
			resul = resul || p.getTipoPerfil().equals(tp);
		}

		return resul;
	}

	public void marcarParaEliminarPerfil(TipoDePerfil tr) {

		for (Perfil p : this.getPerfiles()) {

			if (p.getTipoPerfil().equals(tr)) {
				p.setRol(null);
				p.setTipoPerfil(null);
			}
		}

	}

	public Perfil recuperarPerfil(TipoDePerfil tr) {
		Perfil resul = null;
		for (Perfil p : this.getPerfiles()) {

			if (p.getTipoPerfil().equals(tr)) {
				resul = p;
			}
		}
		return resul;
	}

	public TipoDeRol getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(TipoDeRol tr) {
		this.tipoRol = tr;

		if (tr != null) {
			setNombre(tr.getNombre());
			setCodigo(tr.getCodigo());
		}
	}

	public Rol_VO toValueObject() {
		return new Rol_VO(this);
	}

	public Rol_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new Rol_VO(this, profundidadActual, profundidadDeseada);
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
	 * @return the valorTipoID
	 */
	public String getValorTipoID() {
		return valorTipoID;
	}

	/**
	 * @param valorTipoID the valorTipoID to set
	 */
	public void setValorTipoID(String valorTipoID) {
		this.valorTipoID = valorTipoID;
	}

	/**
	 * @return the tipoID
	 */
	public TipoIDHE getTipoID() {
		return tipoID;
	}

	/**
	 * @param tipoID the tipoID to set
	 */
	public void setTipoID(TipoIDHE tipoID) {
		this.tipoID = tipoID;
	}

	
}