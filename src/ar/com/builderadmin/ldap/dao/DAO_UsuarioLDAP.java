package ar.com.builderadmin.ldap.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.AD_Service;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_GetPerfilesDeRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_GetRolesDeUsuario;
import ar.com.builderadmin.ldap.modelo.UsuarioLDAP;
import ar.com.builderadmin.ldap.vo.UsuarioLDAP_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

/**
 * Data Access Object interface for the Person entity.
 * 
 * @author Mattias Hellborg Arthursson
 * @author Ulrik Sandberg
 */
@Service
public class DAO_UsuarioLDAP extends DAO<UsuarioLDAP>{

	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private DAO_Rol dao_Rol;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	protected final Logger logger = LoggerFactory
			.getLogger(DAO_UsuarioLDAP.class);

	@SuppressWarnings("unchecked")
	public UsuarioLDAP_VO getUsuarioLDAP(String usuario) {
		
		UsuarioLDAP_VO usrLDAP = AD_Service.findAccountByAccountName(usuario);
		
		if (usrLDAP == null) {
			throw new RuntimeException("Usuario no encontrado o no es unico.");
		}else{
			
			//Obtengo los roles del usuario y los guardo en caso de tener alguno
			FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(dao_Rol, usuario, usuario);
			JSON_Respuesta json = fx.ejecutar(admin_Alertas);
			
			if (json.getOk() && json.getPaginador().getElementos().size()>0){
				
				usrLDAP.setRoles(json.getPaginador().getElementos());
				DAO_Perfil daoPerfil = dao_Rol.getInstance(DAO_Perfil.class);
				//Recupero los perfiles con sus funciones
				for (Rol_VO rol : usrLDAP.getRoles()) {

					json = new FX_GetPerfilesDeRol(daoPerfil, rol.getId(), usuario).ejecutar(admin_Alertas);
					if (json.getOk() && json.getPaginador().getElementos().size()>0){
						rol.setPerfiles(json.getPaginador().getElementos());
					}
					
				}
				
			}
			
		}
			
		return usrLDAP;
	}

	public R_GetUsuariosLDAP getUsuariosLDAP() {

		R_GetUsuariosLDAP resul = new R_GetUsuariosLDAP();
		resul.setOk(false);
		
		try{
			
			resul.setOk(true);
			//Recupero los usuario de LDAP
			List<UsuarioLDAP> users = ldapTemplate.findAll(UsuarioLDAP.class);
//			
			for (UsuarioLDAP usuarioLDAP : users) {
				//Limpio los que no son miembros de nada
				if (usuarioLDAP.getMemberOf()!=null)
					resul.getUsuarios().add(new UsuarioLDAP_VO(usuarioLDAP));
			}
//			resul.setUsuarios( AD_Service.listarTodasLasPersonas() );
			
		} catch (Exception e) {
	
			// Context creation failed - authentication did not succeed
			DAO_Utils.error(logger, "AutenticadorLDAP", "getUsuarioDeLDAP", "interno", "error al obtener los usuarios del LDAP");
			
			//Error al autenticar, devuelvo el error
			e.printStackTrace();
			
		}
		
		return resul;
	}

	public List<UsuarioLDAP_VO> listar(Integer paramInteger1, Integer paramInteger2) {
		return getUsuariosLDAP().getUsuarios();
	}

	@Override
	protected Class getClazz() {
		return UsuarioLDAP.class;
	}

	@Override
	protected String getIdClass() {
		return "usrLDAP";
	}

	public class R_GetUsuariosLDAP{
		
		private String mensaje;
		private Boolean ok;
		
		private List<UsuarioLDAP_VO> usuarios = new ArrayList<>();
		
		public R_GetUsuariosLDAP(){}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

		/**
		 * @return the usuarios
		 */
		public List<UsuarioLDAP_VO> getUsuarios() {
			return usuarios;
		}

		/**
		 * @param usuarios the usuarios to set
		 */
		public void setUsuarios(List<UsuarioLDAP_VO> usuarios) {
			this.usuarios = usuarios;
		}

		
	}

}
