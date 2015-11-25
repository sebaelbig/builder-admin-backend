package ar.org.hospitalespanol.model.core.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.org.hospitalespanol.model.I_Entidad;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.model.core.usuarios.roles.TipoDeRol;
import ar.org.hospitalespanol.model.core.usuarios.roles.pacientes.Paciente;
import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.Profesional;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;

/**
 * Usuario que utiliza el sistema
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:39 a.m.
 */
@Entity
@Table
public class Usuario implements Serializable, I_Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;

	@Id
	@SequenceGenerator( sequenceName = "seq_usuario", name = "seq_usuario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Nombre del usuario
	 */
	@Column(length = 30)
	private String nombres;

	/**
	 * Nombre de usuario en el sistema
	 */
	@Column(length = 30, name = "nombre_usuario")
	private String nombreUsuario;

	/**
	 * Contrasena de usuario en el sistema
	 */
	@Column(length = 30)
	private String contrasena;

	/**
	 * Apellido del usuario
	 */
	@Column(length = 20)
	private String apellido;

	/**
	 * Codigo postal de la direccion
	 */
	@Column(length = 30, name = "codigo_postal")
	private String codigoPostal;

	/**
	 * Domicilio del usuario
	 */
	@Column(length = 30)
	private String domicilio;

	/**
	 * E-Mail del usuario
	 */
	@Column(length = 50)
	private String email;

	/**
	 * Fecha de nacimiento
	 */
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	/**
	 * Localidad del domicilio del usuario
	 */
	private String localidad;

	/**
	 * Nro de CUIT
	 * 
	 * XX'-'DD'.'DDD'.'DDD'-'XX
	 */
	@Column(length = 16, name = "nro_cuit")
	private String nroCUIT;

	/**
	 * Nro de documento
	 * 
	 * XX'.'DDD'.'DDD'
	 * 
	 */
	@Column(length = 10, name = "nro_documento")
	private String nroDocumento;

	/**
	 * Tipo de documento
	 */
	@Column(length = 40, name = "tipo_documento")
	private String tipoDocumento;

	/**
	 * roles que tiene el usuario
	 */
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	@JoinFetch(JoinFetchType.OUTER)
	private List<Rol> roles;

	/**
	 * Dias ausente del usuario
	 */
//	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
//	private List<DiaAusente> diasAusente;

	/**
	 * Sexo del usuario
	 */
	@Column(length = 9)
	private String sexo;

	/**
	 * Telefono laboral
	 */
	@Column(name = "otros_telefonos", length = 150)
	private String otrosTelefonos;

	/**
	 * Telefono particular
	 */
	@Column(name = "telefono_particular", length = 15)
	private String telefonoParticular;

	/**
	 * Telefono personal
	 */
	@Column(name = "telefono_celular", length = 15)
	private String telefonoCelular;

	private String nacionalidad;

	/**
	 * Obras Sociales
	 */
//	@OneToMany
//	@JoinTable(name = "usuario_obra_social", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_obra_social"), uniqueConstraints = @UniqueConstraint(columnNames = {
//			"id_usuario", "id_obra_social" }))
//	@JoinColumn(name = "obras_sociales")
//	private List<ObraSocial> obrasSociales;

	/**
	 * Ficha de consumo del usuario
	 */
//	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
//	@JoinColumn(name = "id_ficha_de_consumo")
//	private FichaDeConsumo fichaDeConsumo;

	@Column(columnDefinition = "text")
	private String foto;

	@Column(length = 2147483647, columnDefinition = "bytea")
	@Basic(fetch = FetchType.LAZY)
	private byte[] binario;

	@Column(name = "grupo_sanguineo")
	private String grupoSanguineo;

	@Column(name = "estado_civil")
	private String estadoCivil;

	/*****************************************/
	/********** Datos de LDAP *************/
	/*****************************************/
    private String dn;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String usuario;
    
    private String iniciales;
    
    @Column(name = "member_of")
    private String memberOf;
    
    private String categoria;
	
	/*****************************************/
	/********** Datos de ingreso *************/
	/*****************************************/
	/**
	 * Fecha de ultimo acceso
	 */
	@Column(name = "fecha_ultimo_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaUltimoIngreso = null;

	/**
	 * Cantidad de intentos de ingreso
	 */
	@Column(name = "cantidad_intentos")
	private Integer cantidadIntentos = 0;

	/**
	 * Cantidad de intentos de ingreso
	 */
	@Column(name = "habilitado")
	private Boolean habilitado = true;

	@Column(name = "pregunta_seguridad")
	private String preguntaSeguridad;

	@Column(name = "respuesta_seguridad")
	private String respuestaSeguridad;

	// ------------- CONSTRUCTORES
	public Usuario() {

		roles = new ArrayList<Rol>();

//		this.setFichaDeConsumo(new FichaDeConsumo(this));
	}

	/**
	 * 	this.getId(), this.getVersion(),
		this.getApellido(), this.getCodigoPostal(),
		this.getContrasena(), this.getDomicilio(), this.getEmail(),
		this.getFechaNacimiento(), this.getLocalidad(),
		this.getNombres(), this.getNombreUsuario(), this.getNroCUIT(),
		this.getNroDocumento(), this.getOtrosTelefonos(),
		this.getSexo(), this.getTelefonoCelular(),
		this.getTelefonoParticular(), this.getTipoDocumento(),
		this.getNacionalidad(), this.getFoto(), this.getEstadoCivil(),
		this.getGrupoSanguineo()
				
	 * @param getId
	 * @param version2 getVersion
	 * @param apellido2 getApellido
	 * @param codigoPostal2 getCodigoPostal
	 * @param contrasena2 getContrasena
	 * @param domicilio2 getDomicilio
	 * @param email2 getEmail
	 * @param fechaNacimiento2 getFechaNacimiento
	 * @param localidad2 getLocalidad
	 * @param nombres2 getNombres
	 * @param nombreUsuario2 getNombreUsuario
	 * @param nroCUIT2 getNroCUIT
	 * @param nroDocumento2 getNroDocumento
	 * @param otrosTelefonos2 getOtrosTelefonos
	 * @param sexo2 getSexo
	 * @param telefonoCelular2 getTelefonoCelular
	 * @param telefonoParticular2 getTelefonoParticular
	 * @param tipoDocumento2
	 * @param nac
	 * @param foto
	 * @param estado
	 * @param grupo
	 */
	public Usuario(Long id2, Integer version2, String apellido2,
			String codigoPostal2, String contrasena2, String domicilio2,
			String email2, Date fechaNacimiento2, String localidad2,
			String nombres2, String nombreUsuario2, String nroCUIT2,
			String nroDocumento2, String otrosTelefonos2, String sexo2,
			String telefonoCelular2, String telefonoParticular2,
			String tipoDocumento2, String nac, String foto, String estado,
			String grupo) {
		this.setId(id2);
		this.setVersion(version2);
		this.setApellido(apellido2);
		this.setCodigoPostal(codigoPostal2);
		this.setContrasena(contrasena2);
		this.setDomicilio(domicilio2);
		this.setEmail(email2);
		this.setFechaNacimiento(fechaNacimiento2);
		this.setLocalidad(localidad2);
		this.setNombres(nombres2);
		this.setNombreUsuario(nombreUsuario2);
		this.setNroCUIT(nroCUIT2);
		this.setNroDocumento(nroDocumento2);
		this.setOtrosTelefonos(otrosTelefonos2);
		this.setSexo(sexo2);
		this.setTelefonoCelular(telefonoCelular2);
		this.setTelefonoParticular(telefonoParticular2);
		this.setTipoDocumento(tipoDocumento2);
//		this.setFichaDeConsumo(new FichaDeConsumo(this));

		this.setFoto(foto);
		// this.setBinario(Base64.decode(foto));

		this.setEstadoCivil(estado);
		this.setGrupoSanguineo(grupo);
	}

	// -------------- GETTERS Y SETTERS
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (apellido != null && !apellido.equals("")) {
			this.apellido = apellido;
		} else {
			this.apellido = null;
		}
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		if (codigoPostal != null && !codigoPostal.equals("")) {
			this.codigoPostal = codigoPostal;
		} else {
			this.codigoPostal = null;
		}
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		if (domicilio != null && !domicilio.equals("")) {
			this.domicilio = domicilio;
		} else {
			this.domicilio = null;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null && !email.equals("")) {
			this.email = email;
		} else {
			this.email = null;
		}
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		if (nombres != null && !nombres.equals("")) {
			this.nombres = nombres;
		} else {
			this.nombres = null;
		}
	}

	public String getNroCUIT() {
		return nroCUIT;
	}

	public void setNroCUIT(String nroCUIT) {
		if (nroCUIT != null && !nroCUIT.equals("")) {
			this.nroCUIT = nroCUIT;
		} else {
			this.nroCUIT = null;
		}
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		if (nroDocumento != null && !nroDocumento.equals("")) {
			this.nroDocumento = nroDocumento;
		} else {
			this.nroDocumento = null;
		}
	}

	public List<Rol> getRoles() {
		return roles;
	}

	/*
	 * public String getRol(){ return getRoles(); }
	 */

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		if (sexo != null && !sexo.equals("")) {
			this.sexo = sexo;
		} else {
			this.sexo = null;
		}
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		if (contrasena != null && !contrasena.equals("")) {
			this.contrasena = contrasena;
		} else {
			this.contrasena = null;
		}
	}

	public String getTelefonoParticular() {
		return telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		if (telefonoParticular != null && !telefonoParticular.equals("")) {
			this.telefonoParticular = telefonoParticular;
		} else {
			this.telefonoParticular = null;
		}
	}

//	public List<ObraSocial> getObrasSociales() {
//		return obrasSociales;
//	}
//
//	public void setObrasSociales(List<ObraSocial> obrasSociales) {
//		this.obrasSociales = obrasSociales;
//	}

	public String getOtrosTelefonos() {
		return otrosTelefonos;
	}

	public void setOtrosTelefonos(String otrosTelefonos) {
		if (otrosTelefonos != null && !otrosTelefonos.equals("")) {
			this.otrosTelefonos = otrosTelefonos;
		} else {
			this.otrosTelefonos = null;
		}
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		if (telefonoCelular != null && !telefonoCelular.equals("")) {
			this.telefonoCelular = telefonoCelular;
		} else {
			this.telefonoCelular = null;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Usuario) {
			Usuario usuario = (Usuario) object;
			return usuario.getId().equals(this.getId());

		}
		return false;
	}

	/**
	 * Agrega un rol al usuario siempre y cuando ya no tenga uno del mismo tipo
	 * 
	 * LOS roles no pueden estar repetidos
	 */
	public void agregarRol(Rol rol) {
		getRoles().add(rol);
		rol.setUsuario(this);
	}

	/**
	 * Agrega un rol al usuario siempre y cuando ya no tenga uno del mismo tipo
	 * 
	 * LOS roles no pueden estar repetidos
	 */
	public void agregarRol(TipoDeRol tipo) {
		Rol r = new Rol(tipo);
		r.setUsuario(this);

		getRoles().add(r);
	}

	/**
	 * Quita un rol al usuario dependiendo el codigo del rol
	 * 
	 */
	public Rol quitarRol(String codigoRol) {
		boolean esta = false;
		Iterator<Rol> rols = getRoles().iterator();
		Rol r = null;
		int indice = 0;
		//
		while ((!esta) && (rols.hasNext())) {
			if (rols.next().getCodigo().equals(codigoRol)) {
				esta = true;
			} else {
				indice++;
			}
		}
		if (esta) {
			r = getRoles().remove(indice);
		}
		return r;
	}

	public Profesional getRolProfesional() {
		// return (Profesional) getRol(Rol.PROFESIONAL);
		return null;
	}

	public Paciente getRolPaciente() {
		// return (Paciente) getRol(Rol.PACIENTE);
		return null;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

//	public FichaDeConsumo getFichaDeConsumo() {
//		return fichaDeConsumo;
//	}
//
//	public void setFichaDeConsumo(FichaDeConsumo fichaDeConsumo) {
//		this.fichaDeConsumo = fichaDeConsumo;
//	}

	// ----------------- OPERACIONES
//	public List<DiaAusente> getDiasAusente() {
//		return diasAusente;
//	}
//
//	public void setDiasAusente(List<DiaAusente> diasAusente) {
//		this.diasAusente = diasAusente;
//	}

	@Override
	public String toString() {
		return this.getApellido() + ", " + this.getNombres() + " ("
				+ this.getNombreUsuario() + ")";
	}

	public void copiar(Usuario usr) {
		this.setApellido(usr.getApellido());
		this.setCodigoPostal(usr.getCodigoPostal());
		this.setContrasena(usr.getContrasena());
		this.setDomicilio(usr.getDomicilio());
		this.setEmail(usr.getEmail());
		this.setFechaNacimiento(usr.getFechaNacimiento());
		this.setId(usr.getId());
		this.setLocalidad(usr.getLocalidad());
		this.setNombres(usr.getNombres());
		this.setNombreUsuario(usr.getNombreUsuario());
		this.setNroCUIT(usr.getNroCUIT());
		this.setNroDocumento(usr.getNroDocumento());
		// this.setObrasSociales(obrasSociales);
		this.setOtrosTelefonos(usr.getOtrosTelefonos());
		this.setSexo(usr.getSexo());
		this.setTelefonoCelular(usr.getTelefonoCelular());
		this.setTelefonoParticular(usr.getTelefonoParticular());
		this.setTipoDocumento(usr.getTipoDocumento());
		this.setVersion(usr.getVersion());
		this.setNacionalidad(usr.getNacionalidad());
		this.setFoto(usr.getFoto());
		this.setBinario(usr.getBinario());

	}

	public String getEdad() {

		Integer edad = 0;

		if (getFechaNacimiento() != null) {
			Calendar fechaTemp = Calendar.getInstance(), fechaHoy = Calendar
					.getInstance();
			fechaTemp.setTime(getFechaNacimiento());

			edad = fechaHoy.get(Calendar.YEAR) - fechaTemp.get(Calendar.YEAR)
					- 1;

			if ((fechaTemp.get(Calendar.MONTH) < fechaHoy.get(Calendar.MONTH))
					|| (fechaTemp.get(Calendar.MONTH) == fechaHoy
							.get(Calendar.MONTH) && fechaTemp
							.get(Calendar.DAY_OF_MONTH) <= fechaHoy
							.get(Calendar.DAY_OF_MONTH))) {
				edad++;
			}
		}

		return edad.toString();
	}

	public void agregarDiaAusente(DiaAusente d) {
		d.setUsuario(this);
//		this.getDiasAusente().add(d);

	}

	public Usuario_VO toValueObject() {
		return new Usuario_VO(this);
	}

	public Usuario_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Usuario_VO(this, profundidadActual, profundidadDeseada);
	}

	public boolean tieneRol(Rol r) {
		return this.getRoles().contains(r);
	}

	public boolean tienePerfil(TipoDePerfil tp) {
		boolean resul = false;

		for (Rol r : this.getRoles()) {
			resul = resul || r.tienePerfil(tp);
		}

		return resul;
	}

	public boolean tengoRol(TipoDeRol tr) {
		boolean resul = false;

		for (Rol r : this.getRoles()) {
			resul = resul || r.getTipoRol().equals(tr);
		}

		return resul;
	}

	public Rol obtenerRol(TipoDeRol tr) {
		Rol rol = null;

		for (Rol r : this.getRoles()) {
			if (r.getTipoRol().equals(tr)) {
				rol = r;
			}

		}

		return rol;

	}

	public Profesional obtenerRolProfesionalConPerfil(String nombrePerfil) {

		Profesional prof = null;

		for (Rol r : this.getRoles()) {

			for (Perfil p : r.getPerfiles()) {

				if (p.getNombre().equalsIgnoreCase(nombrePerfil)) {
					prof = (Profesional) r;
				}

			}

		}

		return prof;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public byte[] getBinario() {
		return binario;
	}

	public void setBinario(byte[] binario) {
		this.binario = binario;
	}

	public void pisarDatos(Usuario_VO u) {
		if (u.getId() != null)
			this.setId(u.getId());
		if (u.getVersion() != null)
			this.setVersion(u.getVersion());
		if (u.getApellido() != null)
			this.setApellido(u.getApellido());
		if (u.getCodigoPostal() != null)
			this.setCodigoPostal(u.getCodigoPostal());
		if (u.getContrasena() != null)
			this.setContrasena(u.getContrasena());
		if (u.getDomicilio() != null)
			this.setDomicilio(u.getDomicilio());
		if (u.getEmail() != null)
			this.setEmail(u.getEmail());
		if (u.getFechaNacimiento() != null)
			this.setFechaNacimiento(u.getFechaNacimiento());
		if (u.getLocalidad() != null)
			this.setLocalidad(u.getLocalidad());
		if (u.getNombres() != null)
			this.setNombres(u.getNombres());
		if (u.getNombreUsuario() != null)
			this.setNombreUsuario(u.getNombreUsuario());
		if (u.getNroCUIT() != null)
			this.setNroCUIT(u.getNroCUIT());
		if (u.getNroDocumento() != null)
			this.setNroDocumento(u.getNroDocumento());
		if (u.getOtrosTelefonos() != null)
			this.setOtrosTelefonos(u.getOtrosTelefonos());
		if (u.getSexo() != null)
			this.setSexo(u.getSexo());
		if (u.getTelefonoCelular() != null)
			this.setTelefonoCelular(u.getTelefonoCelular());
		if (u.getTelefonoParticular() != null)
			this.setTelefonoParticular(u.getTelefonoParticular());
		if (u.getTipoDocumento() != null)
			this.setTipoDocumento(u.getTipoDocumento());
		if (u.getFoto() != null)
			this.setFoto(u.getFoto());
		if (u.getEstadoCivil() != null)
			this.setEstadoCivil(u.getEstadoCivil());
		if (u.getGrupoSanguineo() != null)
			this.setGrupoSanguineo(u.getGrupoSanguineo());

	}

	public Date getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public Integer getCantidadIntentos() {
		return cantidadIntentos;
	}

	public void setCantidadIntentos(Integer cantidadIntentos) {
		this.cantidadIntentos = cantidadIntentos;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	public String getRespuestaSeguridad() {
		return respuestaSeguridad;
	}

	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
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
	 * @return the dn
	 */
	public String getDn() {
		return dn;
	}

	/**
	 * @param dn the dn to set
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}

	/**
	 * @param iniciales the iniciales to set
	 */
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	/**
	 * @return the memberOf
	 */
	public String getMemberOf() {
		return memberOf;
	}

	/**
	 * @param memberOf the memberOf to set
	 */
	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
