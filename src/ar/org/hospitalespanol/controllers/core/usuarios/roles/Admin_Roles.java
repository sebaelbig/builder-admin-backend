package ar.org.hospitalespanol.controllers.core.usuarios.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_Rol;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE.R_SetRolDeUsuario;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_BuscarRol;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_CrearRol;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_EliminarRol;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_ModificarRol;
import ar.org.hospitalespanol.ldap.modelo.RolDeUsuarioHE;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

@Controller
public class Admin_Roles extends Admin_Abstracto<Rol_VO> {

	@Autowired
	private Paginador<Rol_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Rol dao_Rol;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private Rol_VO rol_VO;

	private JSON_Respuesta json_respuesta;

	@Override
	protected JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	public void setDao(DAO_Rol dao) {
		this.dao_Rol = dao;
	}

	@Override
	protected DAO_Rol getDao() {
		return dao_Rol;
	}

	@Override
	protected Paginador<Rol_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<Rol_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	/**
	 * @return the dao_Rol
	 */
	public DAO_Rol getDao_Rol() {
		return dao_Rol;
	}

	/**
	 * @param dao_Rol the dao_Rol to set
	 */
	public void setDao_Rol(DAO_Rol dao_Rol) {
		this.dao_Rol = dao_Rol;
	}

	/**
	 * @return the rol_VO
	 */
	public Rol_VO getRol_VO() {
		return rol_VO;
	}

	/**
	 * @param rol_VO the rol_VO to set
	 */
	public void setRol_VO(Rol_VO rol_VO) {
		this.rol_VO = rol_VO;
	}

	@Override
	protected I_FX getFX_Crear(Rol_VO rol, String usr) {
		
		return new FX_CrearRol(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Rol_VO rol, String usr) {
		
		return  new FX_ModificarRol(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Rol_VO rol, String usr) {
		
		return new FX_EliminarRol(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Buscar(Rol_VO rol, String usr) {
		
		return new FX_BuscarRol(getDao(), rol, usr);
	}

	@Override
	public String crear(Rol_VO rol, String usr) {
		
		rol.setLogin(usr);
		
		RolDeUsuarioHE rolUsr = new RolDeUsuarioHE(rol);
		
		R_SetRolDeUsuario resul = new DAO_RolHE().setRolDeUsuario(rolUsr);
		
		if (resul.getOk()){
			
			I_FX funcion =  new FX_CrearRol(getDao(), rol, usr);
			
			return ejecutarFuncion(funcion);
		}else{
			JSON_Respuesta res =  new JSON_Respuesta(resul.getMensaje());
			
			return getGson().toJson(res);
		}
	}
	
	@Override
	public String modificar(Rol_VO rol, String usr) {
		rol.setLogin(usr);
		
		RolDeUsuarioHE rolUsr = new RolDeUsuarioHE(rol);
		
		R_SetRolDeUsuario resul = new DAO_RolHE().setRolDeUsuario(rolUsr);
		
		if (resul.getOk()){
			
			I_FX funcion =  new FX_ModificarRol(getDao(), rol, usr);
			
			return ejecutarFuncion(funcion);
		}else{
			JSON_Respuesta res =  new JSON_Respuesta(resul.getMensaje());
			
			return getGson().toJson(res);
		}
	}
	
	@Override
	public String eliminar(Rol_VO rol, String usr) {
		rol.setLogin(usr);
		
		RolDeUsuarioHE rolUsr = new RolDeUsuarioHE(rol);
		rolUsr.setEstado("INACTIVO");
		
		R_SetRolDeUsuario resul = new DAO_RolHE().setRolDeUsuario(rolUsr);
		
		if (resul.getOk()){
			
			I_FX funcion =  new FX_EliminarRol(getDao(), rol, usr);
			
			return ejecutarFuncion(funcion);
		}else{
			JSON_Respuesta res =  new JSON_Respuesta(resul.getMensaje());
			
			return getGson().toJson(res);
		}
	}
	
	@Override
	@Transactional
	public String findById(Long id, String usuarioAccion) {
		
		Rol obj = (Rol) this.getDao().findById(id);
		Rol_VO rol = obj.toValueObject();
		
		//Actualizo la lista de perfiles
		DAO_Perfil daoPerfil = (DAO_Perfil) getDao().getInstance(DAO_Perfil.class);
		List<Perfil_VO> perfilesRefreshed = daoPerfil.getPerfilesDeRol(id);
		rol.setPerfiles(perfilesRefreshed);
		
		DAO_Utils.info(this.log, this.getClass().getName(), "findById", usuarioAccion, "Se recupera el rol con id: "+id);
		
		return getGson().toJson(rol);
	}
	
}