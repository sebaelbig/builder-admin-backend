package ar.com.builderadmin.controllers.core.usuarios.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_BuscarRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_CrearRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_EliminarRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_ModificarRol;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

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
		
		I_FX funcion =  new FX_CrearRol(getDao(), rol, usr);
		
		return ejecutarFuncion(funcion);
	}
	
	@Override
	public String modificar(Rol_VO rol, String usr) {
		rol.setLogin(usr);
		
		I_FX funcion =  new FX_ModificarRol(getDao(), rol, usr);
			
		return ejecutarFuncion(funcion);
	}
	
	@Override
	public String eliminar(Rol_VO rol, String usr) {
		rol.setLogin(usr);
		
		I_FX funcion =  new FX_EliminarRol(getDao(), rol, usr);
			
		return ejecutarFuncion(funcion);
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