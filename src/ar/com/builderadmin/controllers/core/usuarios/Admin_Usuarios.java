package ar.com.builderadmin.controllers.core.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.AD_Service;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.dao.core.usuarios.roles.profesionales.DAO_FirmaProfesional;
import ar.com.builderadmin.dao.core.usuarios.roles.profesionales.DAO_ProfesionalHE;
import ar.com.builderadmin.dao.historiaClinica.DAO_Firma;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.FX_BuscarUsuario;
import ar.com.builderadmin.fx.core.usuarios.FX_CrearUsuario;
import ar.com.builderadmin.fx.core.usuarios.FX_ModificarUsuario;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_CrearRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_GetRolesDeUsuario;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_GetSoloRolesDeUsuario;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_ModificarRol;
import ar.com.builderadmin.fx.core.usuarios.roles.profesionales.FX_CrearFirmaProfesional;
import ar.com.builderadmin.fx.historiaClinica.FX_ModificarFirmaProfesional;
import ar.com.builderadmin.ldap.vo.UsuarioLDAP_VO;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.FirmaProfesional_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;

@Controller
public class Admin_Usuarios extends Admin_Abstracto<Usuario_VO> {

	@Autowired
	private Paginador<Usuario_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Usuario dao_Usuario;

	@Autowired
	private DAO_Rol dao_rol;
	
	@Autowired
	private DAO_Firma dao_firma;
	
	@Autowired
	private Admin_Alertas admin_Alertas;

	private Usuario_VO usuario_VO;

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

	public void setDao(DAO_Usuario dao) {
		this.dao_Usuario = dao;
	}

	@Override
	protected DAO getDao() {
		return dao_Usuario;
	}

	@Override
	protected Paginador<Usuario_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<Usuario_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	/**
	 * @return the dao_Usuario
	 */
	public DAO_Usuario getDao_Usuario() {
		return dao_Usuario;
	}

	/**
	 * @param dao_Usuario the dao_Usuario to set
	 */
	public void setDao_Usuario(DAO_Usuario dao_Usuario) {
		this.dao_Usuario = dao_Usuario;
	}

	/**
	 * @return the usuario_VO
	 */
	public Usuario_VO getUsuario_VO() {
		return usuario_VO;
	}

	/**
	 * @param usuario_VO the usuario_VO to set
	 */
	public void setUsuario_VO(Usuario_VO usuario_VO) {
		this.usuario_VO = usuario_VO;
	}

	@Override
	protected I_FX getFX_Crear(Usuario_VO usuario, String usr) {
		
		return new FX_CrearUsuario(getDao(), usuario, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Usuario_VO usuario, String usr) {
		
		return  new FX_ModificarUsuario(getDao(), usuario, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Usuario_VO usuario, String usr) {
		
		return new FX_ModificarUsuario(getDao(), usuario, usr);
	}

	@Override
	protected I_FX getFX_Buscar(Usuario_VO usuario, String usr) {
		
		return new FX_BuscarUsuario(getDao(), usuario, usr);
	}

	public JSON_Respuesta setRolesDeUsuario(Usuario_VO usuario, String usuarioAccion) {
		
		JSON_Respuesta resp = new JSON_Respuesta();
		List<Rol_VO> rolesPersistidos = new ArrayList<>();
		
		JSON_Respuesta temp;
		
		DAO_Rol daoRol = (DAO_Rol) getDao().getInstance(DAO_Rol.class);
		for (Rol_VO rol : usuario.getRoles()) {
			
			//Me fijo si el rol ya existe
			if (rol.getId()==null){
				//El rol no está persistido, lo preparo para tal fin
				
				rol.refreshValues();
				rol.setUsuario(usuario);
				
				FX_CrearRol fx_crearRol = new FX_CrearRol(daoRol, rol, usuarioAccion);
				temp = fx_crearRol.ejecutar(getAdminAlertas());
				
				if (temp.getOk()){
					rolesPersistidos.add(fx_crearRol.getRol());
					resp.setMensaje(temp.getMensaje());
				}else{
					resp.setOk(false);
					resp.setMensaje(temp.getMensaje());
				}
				
			}else{
//				rol.refreshValues();
//				rol.setUsuario(usuario);
				
				FX_ModificarRol fx_modificarRol = new FX_ModificarRol(daoRol, rol, usuarioAccion);
				temp = fx_modificarRol.ejecutar(getAdminAlertas());
				
				if (temp.getOk()){
					rolesPersistidos.add(fx_modificarRol.getRol());
					resp.setMensaje(temp.getMensaje());
				}else{
					resp.setOk(false);
					resp.setMensaje(temp.getMensaje());
				}
			}
			
		}
		
		if (resp.getOk()){
			
			//Acoto los roles para que no queden relaciones circulares
			for (Rol_VO rol : rolesPersistidos) {
				rol.prepareToJson();
			}
			
			resp.setPaginador(new JSON_Paginador(rolesPersistidos));
		}
		
		return resp;
	}
	
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@SuppressWarnings("unchecked")
	public JSON_Respuesta getUsuarioPorUsername(String nombreUsuario, String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "getUsuarioPorUsername", usuarioAccion, "Se busca el usuario en Horus con el username: "+nombreUsuario);
		
		Usuario_VO usr = this.getDao_Usuario().buscarPorNombreUsuario(nombreUsuario);
		
		JSON_Respuesta respuesta = null;
		if (usr==null){
			//No existe en nuestra BD, lo creo
			respuesta = this.crearUsuarioDeLDAP(nombreUsuario, usuarioAccion);
		}else{
			//Ya existe, traigo sus roles
			
			//Obtengo los roles del usuario y los guardo en caso de tener alguno
			FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDao_rol(), nombreUsuario, usuarioAccion);
			JSON_Respuesta json = fx.ejecutar(admin_Alertas);
			
			if (json.getOk() && json.getPaginador().getElementos().size()>0){
				usr.setRoles(json.getPaginador().getElementos());
			}else{
				usr.getRoles().clear();
			}
			
			respuesta = new JSON_Respuesta(JSON_Paginador.solo(usr));
		}
		
		return respuesta;
	}
	
	@SuppressWarnings("unchecked")
	public JSON_Respuesta getUsuarioRolesPorUsername(String nombreUsuario, String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "getUsuarioPorUsername", usuarioAccion, "Se busca el usuario en Horus con el username: "+nombreUsuario);
		
		Usuario_VO usr = this.getDao_Usuario().buscarPorNombreUsuario(nombreUsuario);
		
		JSON_Respuesta respuesta = null;
		if (usr==null){
			//No existe en nuestra BD, lo creo
			respuesta = this.crearUsuarioDeLDAP(nombreUsuario, usuarioAccion);
		}else{
			//Ya existe, traigo sus roles
			
			//Obtengo los roles del usuario y los guardo en caso de tener alguno
			FX_GetSoloRolesDeUsuario fx = new FX_GetSoloRolesDeUsuario(getDao_rol(), nombreUsuario, usuarioAccion);
			JSON_Respuesta json = fx.ejecutar(admin_Alertas);
			
			if (json.getOk() && json.getPaginador().getElementos().size()>0){
				usr.setRoles(json.getPaginador().getElementos());
			}else{
				usr.getRoles().clear();
			}
			
			respuesta = new JSON_Respuesta(JSON_Paginador.solo(usr));
		}
		
		return respuesta;
	}

	/**
	 * Crea un usuario en base a uno del LDAP
	 * 
	 * @param nombreUsuario
	 * @param usuarioAccion
	 * 
	 * @return respeusta de la accion
	 */
	public JSON_Respuesta crearUsuarioDeLDAP(String nombreUsuario, String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "crearUsuarioDeLDAP",usuarioAccion, "Se crea el usuario de LDAP en Postgres: "+nombreUsuario);
		
		//Lo traigo del AD para absorber los datos
//		UsuarioLDAP user = ldapTemplate.findOne(query().where("sAMAccountName").is(nombreUsuario), UsuarioLDAP.class);
//		Usuario_VO usr = new Usuario_VO(user);
		UsuarioLDAP_VO user = AD_Service.findAccountByAccountName(nombreUsuario);
		
		if (user!=null){
			//El usuario existe
			Usuario_VO usr = new Usuario_VO(user);
			
			return new FX_CrearUsuario(getDao(), usr, usuarioAccion).ejecutar(admin_Alertas);
		}else{
			return new JSON_Respuesta( "El usuario "+nombreUsuario+ " NO existe como usuario válido en el hospital.");
		}
	}

	
	public String crearFirma(FirmaProfesional_VO firma, String usuarioAccion) {
		@SuppressWarnings("unchecked")
		DAO_FirmaProfesional daoFirma = (DAO_FirmaProfesional) getDao().getInstance(DAO_FirmaProfesional.class);
		
		FX_CrearFirmaProfesional fx_crearFirma = new FX_CrearFirmaProfesional(daoFirma, firma, usuarioAccion);
		
		return ejecutarFuncion(fx_crearFirma);
	}
	
	public String modificarFirma(FirmaProfesional_VO firma,Long idRol, String usuarioAccion) {
		
		firma.setNroMatricula(((Rol)this.getDao_rol().findById(idRol)).getValorTipoID());
		
		FX_ModificarFirmaProfesional fx_crearFirma = new FX_ModificarFirmaProfesional(this.getDao_firma(), firma, usuarioAccion);
		
		return ejecutarFuncion(fx_crearFirma);
	}

	
	public String recuperarFirmaDeUsuario(String usuario, String usuarioAccion) {

		JSON_Respuesta respuesta = getUsuarioPorUsername(usuario, usuarioAccion);
		
		if (respuesta.getOk()){
			Usuario_VO usr = (Usuario_VO)respuesta.getPaginador().getElementoUnico();
			
			//Me fijo si el usuario es medico
			Rol_VO medico = usr.getRolMedico();
			
			if (medico == null){
				//El usuario no es medico
				respuesta.setOk(false);
				respuesta.setMensaje("El usuario "+usuario+" NO es un médico del hospital, o no tiene el Rol correspondiente asociado.");
			
			}else{
				//Recupero la firma del medico
				DAO_FirmaProfesional daoFirma = (DAO_FirmaProfesional) getDao().getInstance(DAO_FirmaProfesional.class);
				FirmaProfesional_VO f = daoFirma.recuperarEntidadDeUsuario(medico.getValorTipoID());
				
				if (f==null){
					//El medico No tenia una firma
					f = new FirmaProfesional_VO();
					f.setUsuario(usuario);
					f.setNroMatricula(medico.getValorTipoID());
				}
				
				respuesta.setPaginador(JSON_Paginador.solo(f));
				respuesta.setOk(true);
			}
		
		}else{
			//El usuario no es medico
			respuesta.setOk(false);
			respuesta.setMensaje(respuesta.getMensaje());
		
		}
		
		return new Gson().toJson(respuesta);
	}
	
	public String recuperarFirmaPorIdRol(String idRol, String usuarioAccion) {

		JSON_Respuesta respuesta = new JSON_Respuesta(); 
		try{
			Rol rol = (Rol)this.getDao_rol().findById(Long.parseLong(idRol));
			
			List<FirmaProfesional_VO> firmasProfesional =	this.getDao_firma().buscarPorMatricula(rol.getValorTipoID(), usuarioAccion);
			
			//TODO Si no existe, crearla
			
			if(firmasProfesional.size()==0){
				DAO_ProfesionalHE daoProfeHE = (DAO_ProfesionalHE) getDao().getInstance(DAO_ProfesionalHE.class);
				ProfesionalHE_VO profeActuanteHE = daoProfeHE.recuperarEntidad(Integer.parseInt(rol.getValorTipoID()));
				FirmaProfesional_VO firmaProfesional = new FirmaProfesional_VO(profeActuanteHE,usuarioAccion);
				this.crearFirma(firmaProfesional, usuarioAccion);
				firmasProfesional.add(firmaProfesional);
			}
			
			respuesta.setPaginador(new JSON_Paginador(firmasProfesional));
						
		}catch(Exception ex){
			respuesta.setOk(false);
			respuesta.setMensaje("No se pudo recuperar la firma del profesional");
		}
		
		return new Gson().toJson(respuesta);
	}

	public String getDatosDePacienteWeb(String usuario){
		/*Traigo tipo y num de Doc del paciente web*/
		
		String datos= getDao_Usuario().getDatosPacienteWeb(usuario);
		return getGson().toJson(datos); 

		
	}
	/**
	 * @return the dao_rol
	 */
	public DAO_Rol getDao_rol() {
		return dao_rol;
	}

	/**
	 * @param dao_rol the dao_rol to set
	 */
	public void setDao_rol(DAO_Rol dao_rol) {
		this.dao_rol = dao_rol;
	}

	/**
	 * @return the dao_firma
	 */
	public DAO_Firma getDao_firma() {
		return dao_firma;
	}

	/**
	 * @param dao_firma the dao_firma to set
	 */
	public void setDao_firma(DAO_Firma dao_firma) {
		this.dao_firma = dao_firma;
	}
	
	
	
}