package ar.com.builderadmin.ws.core.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.core.usuarios.Admin_Usuarios;
import ar.com.builderadmin.dao.core.DAO_Parametro;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.core.usuarios.FX_ListarUsuario;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_GetRolesDeUsuario;
import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.vo.core.usuarios.LinkExterno_VO;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/core/usuarios", produces="application/json;charset=utf-8")
public class WS_Usuarios extends WS_Abstracto{
	
	@Autowired
	private DAO_Parametro daoParametros;
	
	/**
	 * 	Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value="/ping", produces="text/plain;charset=utf-8",  method= RequestMethod.GET)
	public  String ping() {
		return "WS_Usuarios -> ping";
	}
	
	@Autowired
	private Admin_Alertas admin_Alertas;
	
	/****************************************************************************************/
	/*                               Usuarios        										*/
	/****************************************************************************************/
	@Autowired
	private DAO_Usuario dao_Usuario;
	
	@RequestMapping(value = "/seguro/listar/{usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listar(@PathVariable String usuario) {
		System.out.println("WS_Usuarios -> listar("+usuario+")");
		
		FX_ListarUsuario fx = new FX_ListarUsuario(getDao_Usuario(), null, usuario);
		
		JSON_Respuesta respuesta = fx.ejecutar(getAdmin_Alertas());
		
		return new Gson().toJson(respuesta);
	}
	
	/****************************************************************************************/
	/*                                 Roles        										*/
	/****************************************************************************************/
	@Autowired
	private DAO_Rol dao_rol;
	
	@Autowired
	private DAO_Perfil dao_Perfil;
	
	@RequestMapping(value = "/getRolPrioritarioDeUsuario/{usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String getRolPrioritarioDeUsuario(@PathVariable String usuario) {
		System.out.println("WS_Usuarios -> getRolPrioritarioDeUsuario("+usuario+")");
//		
//		Usuario_VO usr = getDao_Usuario().getUsuario(usuario);
		
		FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDao_rol(), usuario, usuario);
		JSON_Respuesta respuesta = fx.ejecutar(getAdmin_Alertas());
		
		Rol_VO resul = null, temp;
		
		for (Object rol : respuesta.getPaginador().getElementos()) {
			
			//Recupero la prioridad
//			temp = this.getRol((Rol) rol, usr.getUsuario());
			temp = (Rol_VO) rol;
			
			if (resul==null || 
					Integer.parseInt(resul.getPrioridad()) > Integer.parseInt(temp.getPrioridad())){
				resul = temp;
			}
		}
		
		return new Gson().toJson(resul);
	}
	
	
	@RequestMapping(value = "/getUsuarioAlfresco", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getUsuarioAlfresco(@RequestBody String host) {
//		alfresco/service/negotiate
		
		// retrieve user name from the session
//		String str_url = "http://"+host+":8080/alfresco/service/negotiate";
//		String str_url = "http://172.20.32.249:8080/alfresco/service/negotiate";

//		HttpURLConnection conn = null;
//
//		// Create a method instance.
//		String usrName = null;
//		
//		try{
//			URL dest = new URL(str_url);
//			
//			conn =  (HttpURLConnection) dest.openConnection();
//			conn.setRequestMethod("GET");
//			conn.connect();
//			
//			BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			usrName = bfr.readLine();
//			conn.disconnect();
//		}catch(IOException ex){
//			System.out.println("No se pudo obtener el usuario logueado en Alfresco de: "+str_url+"/"+host);
//			ex.printStackTrace();
//		}
//
//		return usrName;
		return "guest";
		
	}
	
	/**
	 * Dado un nombre de usuario, le listo los links de las funciones que estan habilitadas para ser accedidas desde 
	 * aplicaciones exteriores
	 * 
	 * @param usuario Nombre de usuario a listar los links
	 * @return Lista de links con los datos: titulo, urlDestino (segun he_fe) e img
	 */
	@RequestMapping(value = "/getLinksExternosDeUsuario/{usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String getLinksExternosDeUsuario(@PathVariable String usuario) {
		
		System.out.println("WS_Usuarios -> getLinksExternosDeUsuario("+usuario+")");
		
		String ipHorus = daoParametros.findValueByNombre(WS_Abstracto.IP_HORUS, E_TipoParametro.STRING);
		
		List<LinkExterno_VO> links = new ArrayList<>();
		
		links.add(new LinkExterno_VO("Consulta de Cirugías Programadas", 
				"http://"+ipHorus+":8090/he_fe/#/?site=/cirugias/reservas&alf_user="+usuario, 
				"cirugias-icon.png"));
		
		links.add(new LinkExterno_VO("Consulta de Agenda Profesional", 
				"http://"+ipHorus+":8090/he_fe/#/?site=/turnos/reservasProfesional&alf_user="+usuario, 
				"agenda-icon.png"));
		
		links.add(new LinkExterno_VO("Historia Clínica Electronica", 
							"http://"+ipHorus+":8090/he_fe/#/?alf_user="+usuario, 
							"hc_min.png"));
		
		//Obtengo los roles para iterar sobre los perfiles
		
		Boolean esDirectorMedico = this.dao_Perfil.usuarioTienePerfil(usuario, "DM") != null;
		if (esDirectorMedico)
			links.add(new LinkExterno_VO("Consulta de internados", 
					"http://"+ipHorus+":8090/he_fe/#/?site=/internacion/controlDias&alf_user="+usuario, 
					"controlDias_min.png"));
		
		return new Gson().toJson(links);
		
	}
	
	@RequestMapping(value = "/getPerfilUsuario/{usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String getPerfilUsuario(@PathVariable String usuario) {
		System.out.println("WS_Usuarios -> getPerfilUsuario("+usuario+")");
		
		
		boolean ok = dao_Perfil.esSecretaria(usuario);
		
		/*** devuelve 'secretaria' si el usuario tiene ese perfil **/		
//		FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDao_rol(), usuario, usuario);
//		JSON_Respuesta respuesta = fx.ejecutar(getAdmin_Alertas());
//		
//		Rol_VO rolUsr;
//		Perfil_VO perUsr;
//		String str = new String("(.*)secretaria(.*)");
//		String result="";
//		
//		for (Object rol : respuesta.getPaginador().getElementos()) {
//			
//			//Casteo el rol de usuario
//			rolUsr = (Rol_VO) rol;
//			for (Object perfil : rolUsr.getPerfiles()) {
//			    perUsr = (Perfil_VO) perfil;
//				if (perUsr.getNombre().toLowerCase().matches("(.*)secretaria(.*)")) {
//					result="secretaria";
//					break;
//				}
//			}
//		}	
		
		return ok?"secretaria":"";
			
	}
		
	@RequestMapping(value = "/getSitioDeRolPrioritarioDeUsuario/{usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String getSitioDeRolPrioritarioDeUsuario(@PathVariable String usuario) {
		System.out.println("WS_Usuarios -> getRolPrioritarioDeUsuario("+usuario+")");
		
		//No anda en produccion, EVITO chequear si el usuario esta en el 
		
//		Usuario_VO usr = getDao_Usuario().getUsuario(usuario);
//		FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDao_rol(), usr.getUsuario(), usr.getUsuario());
		
		FX_GetRolesDeUsuario fx = new FX_GetRolesDeUsuario(getDao_rol(), usuario, usuario);
		JSON_Respuesta respuesta = fx.ejecutar(getAdmin_Alertas());
		
		Rol_VO rolConMasPrioridad = null, rolUsr;
		
		Integer prioridadRol;
		
		for (Object rol : respuesta.getPaginador().getElementos()) {
			
			//Casteo el rol de usuario
			rolUsr = (Rol_VO) rol;
			
			//Obtengo el rol general asignado al usuario
//			rolGeneral = this.getRol(rolUsr, usuario);
//			rolGeneral = this.getRol(rolUsr, usuario);
			
			//Recupero la prioridad del tipo de rol
			prioridadRol = Integer.parseInt(rolUsr.getPrioridad());
			
			if ( 	//Si es el primer rol y SI esta activo EL ROL DEL USUARIO me lo guardo
					( rolConMasPrioridad==null && rolUsr.getEstado().equalsIgnoreCase("activo") ) 
					|| 
					//Si ya tengo un rolConMasPrioridad, el nuevo tiene que tener prioridad mas alta y estar activo
					( 
						rolConMasPrioridad!=null
						&&
						Integer.parseInt(rolConMasPrioridad.getPrioridad()) > prioridadRol
						&&
//						rolConMasPrioridad.getEstado().equalsIgnoreCase("activo")
						rolUsr.getEstado().equalsIgnoreCase("activo")
					)
				)
			{
				rolConMasPrioridad = rolUsr;
			}
		}
		
		return (rolConMasPrioridad!=null)?rolConMasPrioridad.getSitio():"para-todos";
	}
	
//	private Rol_VO getRol( Rol r, String usr ){
//		
//		FX_GetRol fxRol  = new FX_GetRol(getDao_rol(), r.getId_rol() , usr);
//		fxRol.ejecutar(getAdmin_Alertas());
//		
//		if (fxRol.getRespuesta().getPaginador().getElementos().size() > 0){
//			return (Rol_VO) fxRol.getRespuesta().getPaginador().getElementos().get(0);
//		}else{
//			return null;
//		}
//		
//	}
	
	@RequestMapping(value="/pingNuevo", produces="text/plain;charset=utf-8",  method= RequestMethod.GET)
	public  String pingNuevo() {
		return "WS_Usuarios -> pingNuevo";
	}
	
	@Autowired
	private Admin_Usuarios adminUsuarios;
	
	@RequestMapping(value = "/seguro/getUsuarioPorUsername", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getUsuarioPorUsername(@RequestBody String nombreUsuario) {
		System.out.println("WS_Usuarios -> getUsuarioSeguroPorUsername("+nombreUsuario+")");
		
		JSON_Respuesta respuesta = this.adminUsuarios.getUsuarioPorUsername(nombreUsuario, this.getUsuarioAccion());
		
		return new Gson().toJson(respuesta);
	}
	
	@RequestMapping(value = "/seguro/getUsuarioRolesPorUsername", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getUsuarioRolesPorUsername(@RequestBody String nombreUsuario) {
		System.out.println("WS_Usuarios -> getUsuarioSeguroPorUsername("+nombreUsuario+")");
		
		JSON_Respuesta respuesta = this.adminUsuarios.getUsuarioRolesPorUsername(nombreUsuario, this.getUsuarioAccion());
		
		return new Gson().toJson(respuesta);
	}
	
	
//	@RequestMapping(value = "/getUsuarioPorUsername", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
//	public String getUsuarioPorUsername(@RequestBody String nombreUsuario) {
//		return this.getUsuarioSeguroPorUsername(nombreUsuario);
//	}
	
	@RequestMapping(value = "/seguro/setRolesDeUsuario", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String setRolesDeUsuario(@RequestBody String usuario_con_roles) {
		System.out.println("WS_Usuarios -> setRolesDeUsuario("+usuario_con_roles+")");
		
		Usuario_VO usuario = new Gson().fromJson(usuario_con_roles, Usuario_VO.class);

		JSON_Respuesta respuesta = this.adminUsuarios.setRolesDeUsuario(usuario, this.getUsuarioAccion());
			
		return new Gson().toJson(respuesta);
	}
	
	@RequestMapping(value = "/findById/{id_usuario}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String findById(@PathVariable String id_usuario) {
		System.out.println("WS_Usuarios -> findById(" + id_usuario + ")");

		return adminUsuarios.findById(Long.parseLong(id_usuario), getUsuarioAccion());
	}
	
	/**
	 * @return the admin_Alertas
	 */
	public Admin_Alertas getAdmin_Alertas() {
		return admin_Alertas;
	}

	/**
	 * @param admin_Alertas the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
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

	
}