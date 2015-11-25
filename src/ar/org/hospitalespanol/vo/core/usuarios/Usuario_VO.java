package ar.org.hospitalespanol.vo.core.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.ldap.modelo.UsuarioLDAP;
import ar.org.hospitalespanol.ldap.vo.UsuarioLDAP_VO;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.fichaDeConsumo.FichaDeConsumo_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;
import ar.org.hospitalespanol.ws.respuestas.datosDelPaciente.R_DatosPersonalesUsuario;

public class Usuario_VO implements I_ValueObject<Usuario> {

	private Long id;
	private Integer version;
	private Boolean borrado = false;
	
	private String usuario;
	private String socioNro;
	private String apellido;
	private String nombres;
	private String sexo;
	private String codigoPostal;
	private String domicilio;
	private String email;
	private Date fechaNacimiento;
	private String localidad;
	private String nroDocumento;
	private String tipoDocumento;
	private String permisos;

	private List<Rol_VO> roles = new ArrayList<Rol_VO>();
	private Boolean masculino = Boolean.valueOf(true);
	private String telefonoParticular;
	private Boolean borrar = Boolean.valueOf(false);
	private byte[] binario;
	private String foto;
	private String edad;
	private String nacionalidad;
	private String grupoSanguineo;
	private String estadoCivil;

	/*****************************************/
	/********** Datos de LDAP *************/
	/*****************************************/
    private String dn;

    private String nombreCompleto;

    private String iniciales;
    
    private String memberOf;
    
    private String categoria;
    /*****************************************/
    
	/**
	 * Contrasena de usuario en el sistema
	 */
	private String contrasena;

	/**
	 * Nro de CUIT
	 * 
	 * NN'-'NN'.'NNN'.'NNN'-'NN
	 */
	private String nroCUIT;

	/**
	 * Telefono laboral
	 */
	private String otrosTelefonos;

	/**
	 * Telefono personal
	 */
	private String telefonoCelular;

	/**
	 * Ficha de consumo del paciente
	 */
	private FichaDeConsumo_VO fichaDeConsumo;

	public Usuario_VO(R_DatosPersonalesUsuario resp) {
		setApellido(resp.getApellido());
		setCodigoPostal(resp.getCodigoPostal());
		setDomicilio(resp.getDomicilio());
		setEmail(resp.getEmail());
		setFechaNacimiento(resp.getFechaNacimiento());
		setLocalidad(resp.getLocalidad());
		setMasculino(resp.getMasculino());
		setNombreUsuario(resp.getUsuario());
		setNroDocumento(resp.getNroDocumento());
		setTipoDocumento(resp.getTipoDocumento());
		setUsuario(resp.getUsuario());
		setSocioNro(resp.getSocioNro());
		setTelefonoParticular(resp.getTelefonoParticular());
		setPermisos(resp.getPermisos());
	}

	public Usuario_VO() {
		this.setFichaDeConsumo(new FichaDeConsumo_VO());
	}

	public Usuario_VO(Usuario u) {
		setObject(u);
	}

	public Usuario_VO(Usuario u, int profundidadActual, int profundidadDeseada) {
		setObject(u, profundidadActual, profundidadDeseada);
	}

	public Usuario_VO(UsuarioLDAP usr) {
		this.setApellido(usr.getApellido());
		this.setCategoria(usr.getCategoria());
		this.setEmail(usr.getEmail());
		this.setIniciales(usr.getIniciales());
		this.setMemberOf(usr.getMemberOf());
		this.setNombreCompleto(usr.getNombreCompleto());
		this.setUsuario(usr.getUsuario());
		this.setNombreUsuario(usr.getUsuario());
	}

	public Usuario_VO(UsuarioLDAP_VO usr) {
		try{ this.setApellido(usr.getApellido());} catch (NullPointerException nulle) {}
		try{ this.setCategoria(usr.getCategoria());} catch (NullPointerException nulle) {}
		try{ this.setEmail(usr.getEmail());} catch (NullPointerException nulle) {}
		try{ this.setIniciales(usr.getIniciales());} catch (NullPointerException nulle) {}
		try{ this.setMemberOf(usr.getMemberOf());} catch (NullPointerException nulle) {}
		try{ this.setNombreCompleto(usr.getNombreCompleto());} catch (NullPointerException nulle) {}
		try{ this.setUsuario(usr.getUsuario());} catch (NullPointerException nulle) {}
		try{ this.setNombreUsuario(usr.getUsuario());} catch (NullPointerException nulle) {}
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNroDocumento() {
		return this.nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Rol_VO> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Rol_VO> roles) {
		this.roles = roles;
	}

	public String getTelefonoParticular() {
		return this.telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	// public boolean tengoRol(TipoDePerfil_VO tipo) {
	// boolean resul = false;
	// for (Rol_VO r : getRoles()) {
	// resul = (resul) || (r.getCodigo().equals(tipo.getCodigo()));
	// }
	// return resul;
	// }

	// public boolean tengoRol(String nombreRol) {
	// boolean resul = false;
	// for (Rol_VO r : getRoles()) {
	// resul = (resul) || (r.getNombre().equals(nombreRol));
	// }
	// return resul;
	// }

	// public void agregarRol(Rol_VO r) {
	// if (!getRoles().contains(r)) {
	// getRoles().add(r);
	// }
	// }
	//
	// public void quitarRol(Rol_VO r) {
	// if (getRoles().contains(r)) {
	// getRoles().remove(r);
	// }
	// }
	//
	// public void quitarRol_VO(TipoDeRol_VO tr) {
	// Rol_VO resul = null;
	// for (Rol_VO r : getRoles()) {
	// if (r.getTipoRol().getCodigo().equals(tr.getCodigo())) {
	// resul = r;
	// }
	// }
	// getRoles().remove(resul);
	// marcarParaEliminar(resul);
	// }

	// public void marcarParaEliminar(Rol_VO rol) {
	// rol.setUsuario(null);
	// rol.marcarParaEliminar();
	// }

	// public void quitarRol_VO(Rol_VO r) {
	// getRoles().remove(r);
	// }
	//
	// public void quitarRol(TipoDePerfil_VO tr) {
	// if (getRoles().size() > 0) {
	// int pos = -1;
	// for (int i = 0; i < getRoles().size(); i++) {
	// if (getRoles().get(i).getCodigo().equals(tr.getCodigo())) {
	// pos = i;
	// }
	// }
	// Rol_VO r = getRoles().get(pos);
	// r.setUsuario(null);
	// getRoles().remove(r);
	// }
	// }
	//
	// public boolean tengoRol(Rol_VO elem) {
	// boolean resul = false;
	// for (Rol_VO r : getRoles()) {
	// resul = (resul)
	// || ((r.getCodigo().equals(elem.getCodigo())) && (r
	// .getNombre().equals(elem.getNombre())));
	// }
	// return resul;
	// }

	public Boolean getBorrar() {
		return this.borrar;
	}

	public void setBorrar(Boolean borrar) {
		this.borrar = borrar;
	}

	@Override
	public String toString() {
		return getApellido() + "(" + getUsuario() + ") - " + getNroDocumento();
	}

	// public boolean tengoRol(TipoDeRol_VO tr) {
	// boolean resul = false;
	// for (Rol_VO r : getRoles()) {
	// resul = (resul)
	// || (r.getTipoRol().getCodigo().equals(tr.getCodigo()));
	// }
	// return resul;
	// }
	//
	// public Rol_VO recuperarRol(TipoDeRol_VO tr) {
	// Rol_VO resul = null;
	// for (Rol_VO r : getRoles()) {
	// if (r.getTipoRol().getCodigo().equals(tr.getCodigo())) {
	// resul = r;
	// }
	// }
	// return resul;
	// }

	public String getEdad() {
		return this.edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getGrupoSanguineo() {
		return this.grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public byte[] getBinario() {
		return this.binario;
	}

	public void setBinario(byte[] binario) {
		this.binario = binario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getMasculino() {
		return this.masculino;
	}

	public void setMasculino(Boolean masculino) {
		this.masculino = masculino;
	}

	public void pisarDatos(Usuario_VO u) {
		if (u.getId() != null) {
			setId(u.getId());
		}
		if (u.getVersion() != null) {
			setVersion(u.getVersion());
		}
		if (u.getApellido() != null) {
			setApellido(u.getApellido());
		}
		if (u.getCodigoPostal() != null) {
			setCodigoPostal(u.getCodigoPostal());
		}
		if (u.getDomicilio() != null) {
			setDomicilio(u.getDomicilio());
		}
		if (u.getEmail() != null) {
			setEmail(u.getEmail());
		}
		if (u.getFechaNacimiento() != null) {
			setFechaNacimiento(u.getFechaNacimiento());
		}
		if (u.getLocalidad() != null) {
			setLocalidad(u.getLocalidad());
		}
		if (u.getUsuario() != null) {
			setUsuario(u.getUsuario());
		}
		if (u.getNroDocumento() != null) {
			setNroDocumento(u.getNroDocumento());
		}
		if (u.getTelefonoParticular() != null) {
			setTelefonoParticular(u.getTelefonoParticular());
		}
		if (u.getTipoDocumento() != null) {
			setTipoDocumento(u.getTipoDocumento());
		}
		if (u.getEdad() != null) {
			setEdad(u.getEdad());
		}
		if (u.getFoto() != null) {
			setFoto(u.getFoto());
		}
		if (u.getEstadoCivil() != null) {
			setEstadoCivil(u.getEstadoCivil());
		}
		if (u.getGrupoSanguineo() != null) {
			setGrupoSanguineo(u.getGrupoSanguineo());
		}
	}

	public String getNombreUsuario() {
		return getUsuario();
	}

	public void setNombreUsuario(String nuevoNombre) {
		setUsuario(nuevoNombre);
	}

	/**
	 * @return the permisos
	 */
	public String getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos
	 *            the permisos to set
	 */
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
		this.masculino = (sexo!=null)?sexo.toLowerCase().startsWith("m")?true:false:false;
	}

	@Override
	public void setObject(Usuario u) {

		this.setId(u.getId());
		this.setVersion(u.getVersion());
		this.setApellido(u.getApellido());
		this.setCodigoPostal(u.getCodigoPostal());
		this.setContrasena(u.getContrasena());
		this.setDomicilio(u.getDomicilio());
		this.setEmail(u.getEmail());
		this.setFechaNacimiento(u.getFechaNacimiento());
		this.setLocalidad(u.getLocalidad());
		this.setNombres(u.getNombres());
		this.setNombreUsuario(u.getNombreUsuario());
		this.setNroCUIT(u.getNroCUIT());
		this.setNroDocumento(u.getNroDocumento());
		this.setOtrosTelefonos(u.getOtrosTelefonos());
		this.setSexo(u.getSexo());
		this.setTelefonoCelular(u.getTelefonoCelular());
		this.setTelefonoParticular(u.getTelefonoParticular());
		this.setTipoDocumento(u.getTipoDocumento());
		this.setEdad(u.getEdad());

		this.setFoto(u.getFoto());
		this.setEstadoCivil(u.getEstadoCivil());
		this.setGrupoSanguineo(u.getGrupoSanguineo());

		this.setDn(u.getDn());
		this.setNombreCompleto(u.getNombreCompleto());
		this.setUsuario(u.getUsuario());
		this.setIniciales(u.getIniciales());
		this.setMemberOf(u.getMemberOf());
		this.setCategoria(u.getCategoria());
		
		List<Rol_VO> roles = new ArrayList<Rol_VO>();
		for (Rol r : u.getRoles()) {

			roles.add(r.toValueObject());
		}

		this.setRoles(roles);

		// this.setCantidadRolesInicales(this.getRoles().size());
//		if (u.getFichaDeConsumo() != null) {
//			this.setFichaDeConsumo(u.getFichaDeConsumo().toValueObject());
//		}

	}

	@Override
	public void setObject(Usuario u, int profundidadActual,
			int profundidadDeseada) {

		this.setId(u.getId());
		this.setVersion(u.getVersion());
		this.setApellido(u.getApellido());
		this.setCodigoPostal(u.getCodigoPostal());
		this.setContrasena(u.getContrasena());
		this.setDomicilio(u.getDomicilio());
		this.setEmail(u.getEmail());
		this.setFechaNacimiento(u.getFechaNacimiento());
		this.setLocalidad(u.getLocalidad());
		this.setNombres(u.getNombres());
		this.setNombreUsuario(u.getNombreUsuario());
		this.setNroCUIT(u.getNroCUIT());
		this.setNroDocumento(u.getNroDocumento());
		this.setOtrosTelefonos(u.getOtrosTelefonos());
		this.setSexo(u.getSexo());
		this.setTelefonoCelular(u.getTelefonoCelular());
		this.setTelefonoParticular(u.getTelefonoParticular());
		this.setTipoDocumento(u.getTipoDocumento());
		this.setEdad(u.getEdad());
		this.setEstadoCivil(u.getEstadoCivil());
		this.setGrupoSanguineo(u.getGrupoSanguineo());
		this.setFoto(u.getFoto());
		this.setBinario(u.getBinario());
		
		this.setDn(u.getDn());
		this.setNombreCompleto(u.getNombreCompleto());
		this.setUsuario(u.getUsuario());
		this.setIniciales(u.getIniciales());
		this.setMemberOf(u.getMemberOf());
		this.setCategoria(u.getCategoria());

		List<Rol_VO> roles = new ArrayList<Rol_VO>();
		this.setRoles(roles);

		// Se chequea que no se halla llegado a la profundidad deseada
		if ( profundidadActual < profundidadDeseada) {

			for (Rol r : u.getRoles()) {

				this.getRoles().add(
						r.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

//			if (u.getFichaDeConsumo() != null) {
//				this.setFichaDeConsumo(u.getFichaDeConsumo().toValueObject(
//						profundidadActual + 1, profundidadDeseada));
//			}

		}

	}

	public Usuario toObject(int i, int g) {
		return this.toObject();
	}

	@Override
	public Usuario toObject() {

		Usuario u = new Usuario(this.getId(), this.getVersion(),
				this.getApellido(), this.getCodigoPostal(),
				this.getContrasena(), this.getDomicilio(), this.getEmail(),
				this.getFechaNacimiento(), this.getLocalidad(),
				this.getNombres(), this.getNombreUsuario(), this.getNroCUIT(),
				this.getNroDocumento(), this.getOtrosTelefonos(),
				this.getSexo(), this.getTelefonoCelular(),
				this.getTelefonoParticular(), this.getTipoDocumento(),
				this.getNacionalidad(), this.getFoto(), this.getEstadoCivil(),
				this.getGrupoSanguineo());

		u.setDn(this.getDn());
		u.setNombreCompleto(this.getNombreCompleto());
		u.setUsuario(this.getUsuario());
		u.setIniciales(this.getIniciales());
		u.setMemberOf(this.getMemberOf());
		u.setCategoria(this.getCategoria());
		
		List<Rol> roles = new ArrayList<Rol>();
		Rol rol = null;
		for (Rol_VO r : this.getRoles()) {
			rol = r.toObject(I_ValueObject.PROFUNDIDAD_BASE, 0);
			roles.add(rol);
			rol.setUsuario(u);
		}
		u.setRoles(roles);

//		// Si tiene una ficha de consumo ya creada
//		if (this.getFichaDeConsumo() != null) {
//			u.setFichaDeConsumo(this.getFichaDeConsumo().toObject());
//			u.getFichaDeConsumo().setUsuario(u);
//		}

		return u;
	}

	@Override
	public boolean equals(Object usr) {
		String nombreUsr = "";

		if (usr instanceof Usuario) {
			nombreUsr = ((Usuario) usr).getNombreUsuario();
		} else {
			nombreUsr = ((Usuario_VO) usr).getNombreUsuario();
		}

		return this.getNombreUsuario().equalsIgnoreCase(nombreUsr);
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena
	 *            the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the nroCUIT
	 */
	public String getNroCUIT() {
		return nroCUIT;
	}

	/**
	 * @param nroCUIT
	 *            the nroCUIT to set
	 */
	public void setNroCUIT(String nroCUIT) {
		this.nroCUIT = nroCUIT;
	}

	/**
	 * @return the otrosTelefonos
	 */
	public String getOtrosTelefonos() {
		return otrosTelefonos;
	}

	/**
	 * @param otrosTelefonos
	 *            the otrosTelefonos to set
	 */
	public void setOtrosTelefonos(String otrosTelefonos) {
		this.otrosTelefonos = otrosTelefonos;
	}

	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @param telefonoCelular
	 *            the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return the fichaDeConsumo
	 */
	public FichaDeConsumo_VO getFichaDeConsumo() {
		return fichaDeConsumo;
	}

	/**
	 * @param fichaDeConsumo
	 *            the fichaDeConsumo to set
	 */
	public void setFichaDeConsumo(FichaDeConsumo_VO fichaDeConsumo) {
		this.fichaDeConsumo = fichaDeConsumo;
	}


	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
	
	public String getSocioNro() {
		return this.socioNro;
	}

	public void setSocioNro(String socioNro) {
		this.socioNro = socioNro;
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

	public Rol_VO getRolMedico() {
		
		for (Rol_VO r : getRoles()) {
			if (r.getCodigo().equalsIgnoreCase("MHE")){
				return r;
			}
		}
		
		return null;
	}
	
	
}