package ar.com.builderadmin.vo.core.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class Usuario_VO implements I_ValueObject<Usuario> {

	private Long id;
	private Integer version;
	private Boolean borrado = false;
	
	private String usuario;
	private String apellido;
	private String nombres;
	private String codigoPostal;
	private String domicilio;
	private String email;
	private Date fechaNacimiento;
	private String localidad;
	private String nroDocumento;
	private String tipoDocumento;

	private List<Rol_VO> roles = new ArrayList<Rol_VO>();
	private Boolean masculino = Boolean.valueOf(true);
	private String telefonos;
	private Boolean borrar = Boolean.valueOf(false);
	private byte[] binario;
	private String foto;
	private String edad;

	private Date fechaUltimoIngreso = null;
	private String respuestaSeguridad;
	private String preguntaSeguridad;
	private Integer cantidadIntentos = 0;
	
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


	public Usuario_VO() {
	}

	public Usuario_VO(Usuario u) {
		setObject(u);
	}

	public Usuario_VO(Usuario u, int profundidadActual, int profundidadDeseada) {
		setObject(u, profundidadActual, profundidadDeseada);
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

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
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

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		if (u.getTelefonos() != null) {
			setTelefonos(u.getTelefonos());
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
	}

	public String getNombreUsuario() {
		return getUsuario();
	}

	public void setNombreUsuario(String nuevoNombre) {
		setUsuario(nuevoNombre);
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
		this.setTelefonos(u.getTelefonos());
		this.setTipoDocumento(u.getTipoDocumento());
		this.setEdad(u.getEdad());
		this.setFoto(u.getFoto());

		List<Rol_VO> roles = new ArrayList<Rol_VO>();
		for (Rol r : u.getRoles()) {

			roles.add(r.toValueObject());
		}

		this.setRoles(roles);
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
		this.setTelefonos(u.getTelefonos());
		this.setTipoDocumento(u.getTipoDocumento());
		this.setEdad(u.getEdad());
		this.setFoto(u.getFoto());
		this.setBinario(u.getBinario());
		
		List<Rol_VO> roles = new ArrayList<Rol_VO>();
		this.setRoles(roles);

		// Se chequea que no se halla llegado a la profundidad deseada
		if ( profundidadActual < profundidadDeseada) {

			for (Rol r : u.getRoles()) {

				this.getRoles().add(
						r.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

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
				this.getNroDocumento(), 
				this.getTelefonos(), this.getTipoDocumento(),
				this.getFoto()
				);

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

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	public String getNombreCompleto() {
		return getApellido()+", "+getNombres();
	}

	public Date getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public String getRespuestaSeguridad() {
		return respuestaSeguridad;
	}

	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
	}

	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	public Integer getCantidadIntentos() {
		return cantidadIntentos;
	}

	public void setCantidadIntentos(Integer cantidadIntentos) {
		this.cantidadIntentos = cantidadIntentos;
	}
	
}