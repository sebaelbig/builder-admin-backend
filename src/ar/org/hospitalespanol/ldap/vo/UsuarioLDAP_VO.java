package ar.org.hospitalespanol.ldap.vo;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import ar.org.hospitalespanol.ldap.modelo.UsuarioLDAP;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

public class UsuarioLDAP_VO implements I_ValueObject<UsuarioLDAP> {

	private Long id;
	private Integer version;
	private Boolean borrado;
	
    private String dn;

    private String nombreCompleto;

    private String apellido;

    private String usuario;
    
    private String email;
    
    private String iniciales;
    
    private String memberOf;
    
    private String categoria;
	
//    private List<RolDeUsuarioHE> roles = new ArrayList<>();
    private List<Rol_VO> roles = new ArrayList<>();
    
    public UsuarioLDAP_VO(){}
    
    public UsuarioLDAP_VO(Attributes atts) {
    	try {
    		
    		try{ this.setDn( (String) atts.get("distinguishedName").get() );} catch (NullPointerException nulle) {}
    		try{ this.setNombreCompleto( (String) atts.get("cn").get() );} catch (NullPointerException nulle) {}
    		try{ this.setUsuario( (String) atts.get("sAMAccountName").get() );} catch (NullPointerException nulle) {}
    		try{ this.setCategoria( (String) atts.get("objectCategory").get() );} catch (NullPointerException nulle) {}
    		try{ this.setMemberOf( (String) atts.get("memberOf").get() );} catch (NullPointerException nulle) {}
    		try{ this.setApellido( (String) atts.get("sn").get() );} catch (NullPointerException nulle) {}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
    
    public UsuarioLDAP_VO(UsuarioLDAP usrLDAP) {
    	setObject(usrLDAP);
    }
    
    public UsuarioLDAP_VO(List<Rol_VO> roles) {
    	setRoles(roles);
    }
    
	public UsuarioLDAP_VO(UsuarioLDAP usrLDAP, List<Rol_VO> roles) {
		setObject(usrLDAP);
		setRoles(roles);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public UsuarioLDAP toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(UsuarioLDAP usr) {
		 
		this.setApellido(usr.getApellido());
		this.setCategoria(usr.getCategoria());
//		this.setDn(usr.getDn().toString());
		this.setEmail(usr.getEmail());
		this.setIniciales(usr.getIniciales());
		this.setMemberOf(usr.getMemberOf());
		this.setNombreCompleto(usr.getNombreCompleto());
		this.setUsuario(usr.getUsuario());
	}

	@Override
	public void setObject(UsuarioLDAP paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean deleted) {}

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

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	
}
