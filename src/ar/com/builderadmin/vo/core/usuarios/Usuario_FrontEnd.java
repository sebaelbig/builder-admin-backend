package ar.com.builderadmin.vo.core.usuarios;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.ldap.modelo.UsuarioLDAP;
import ar.com.builderadmin.ldap.vo.UsuarioLDAP_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;


public class Usuario_FrontEnd {

	private String token;
	private String usuario;
	private String nombre;
	private boolean admin = false;
	
	private List<Rol_VO> roles = new ArrayList<>();
	
	public Usuario_FrontEnd(UsuarioLDAP_VO usr, List<Rol_VO> rs) {
		this.setUsuario(usr.getUsuario());
		this.setNombre(usr.getNombreCompleto());
		this.setRoles( rs);
	}
	
	public Usuario_FrontEnd(UsuarioLDAP_VO usr) {
		this.setUsuario(usr.getUsuario());
		this.setNombre(usr.getNombreCompleto());
     	this.setRoles( usr.getRoles());
	}
	
	public Usuario_FrontEnd(UsuarioLDAP usr) {
		this.setUsuario(usr.getUsuario());
		this.setNombre(usr.getNombreCompleto());
	}
	/*Para los pacientes*/
	public Usuario_FrontEnd(String nombre){
		this.setNombre(nombre);
		this.setUsuario(nombre);
	}
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
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
		
		if (usuario!=null){
			this.setAdmin(this.esAdmin(usuario));
		}
	}

	private boolean esAdmin(String usr) {
		List<String> users = new ArrayList<>();
		
		users.add("sebastianga");
		users.add("fabiona");
		users.add("pablona");
		users.add("mariaco");
		users.add("danilamo");
		users.add("carlalu");
		users.add("reemplazo");
		
		return users.contains(usr);
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	
	/**
	 * @param nombre the nombre to set
	 */
	public void setAdmin(boolean b) {
		this.admin = b;
	}

	/**
	 * @return the roles
	 */
	public List<Rol_VO> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Rol_VO> roles) {
		this.roles = roles;
	}

	
}