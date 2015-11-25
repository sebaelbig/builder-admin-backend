package ar.org.hospitalespanol.controllers.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.dao.core.usuarios.DAO_Usuario;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.vo.core.areas.Sucursal_VO;
import ar.org.hospitalespanol.vo.core.usuarios.InfoIngresoUsuario;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

//@Scope(ScopeType.SESSION)
public class MenuManager {

	List<String> funciones = new ArrayList<String>();

	// @RequestParameter
	public String url = "paginaPrincipal";;

	public String htmlMenu;

	private HorusMenuPerfil perfilActual;
	private HorusMenuRol rolActual;

	private Set<Sucursal_VO> sucursalesDeUsuario = new HashSet<Sucursal_VO>();
	private Sucursal_VO sucursalActual;

	private boolean poseeUnRol = false;
	private boolean poseeUnaSucursal = false;

	private boolean poseeMail = false;
	private boolean usuario = false;

	// @Out(value="conAyuda")
	private Boolean conAyuda = false;

	public MenuManager() {
	}

	/**
	 * Carga el menu para el rol y la sucursal pasada como parametro
	 * 
	 * @param rol
	 * @param sucursalMenu
	 */
	public void cargarMenu(Rol_VO rol, Sucursal_VO sucursalMenu) {

		this.cargarRol(rol);

		this.getRolActual().armarMenu(sucursalMenu);

		this.cargarFunciones();

	}

	/**
	 * Dado una sucursal, arma el menu
	 * 
	 * @param r
	 */
	public void armarMenuParaSucursal(Usuario usr, EntityManager em,
			Sucursal_VO sucursalMenu) {

		this.setPoseeUnaSucursal(true);

		this.getRolActual().armarMenu(sucursalMenu);

		this.cargarFunciones();

	}

	public void cargarRol(Rol_VO rol) {

		HorusMenuRol mr = new HorusMenuRol(rol);

		this.setRolActual(mr);

		this.setPoseeUnRol(true);

	}

	private void cargarFunciones() {

		// Recupero las funciones
		for (HorusMenuPerfil mp : this.getRolActual().getMenuPerfiles()) {

			this.getFunciones().addAll(mp.getFunciones());

		}

	}

	/**
	 * Dado un rol, arma el menu
	 * 
	 * @param r
	 */
	public void armarMenuParaRol(Usuario usuario, EntityManager em, Rol_VO r) {

		this.cargarRol(r);

		// Obtengo las sucursales distintas que tenga el rol en sus perfiles
		Set<Sucursal_VO> sucus = new DAO_Usuario()
				.buscarSucursalesDePerfiles(em, r);

		if (sucus.size() == 1) {
			// Tiene una sola sucursal, y tiene un solo rol que no es paciente
			// => HOME
			Sucursal_VO s = sucus.iterator().next();

			this.setPoseeUnaSucursal(true);

			this.setSucursalActual(s);

			this.armarMenuParaSucursal(usuario, em, s);

		} else {

			this.setPoseeUnaSucursal(false);

			this.setSucursalActual(null);

			this.setSucursalesDeUsuario(sucus);

		}

	}

	public Boolean tieneFuncion(String nombreFuncion) {
		return this.getFunciones().contains(nombreFuncion);
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String redirect() {
		return this.getUrl();
	}

	public String redirect(int posPerfil, String urlDestino) {

		this.setPerfilActual(this.getRolActual().getMenuPerfiles()
				.get(posPerfil));
		this.setUrl(urlDestino);
		return urlDestino;
	}

	@Override
	public String toString() {
		if (this.getRolActual() != null) {
			return this.getRolActual().getLabel();
		} else {
			return "error";
		}
	}

	public List<String> getFunciones() {
		return this.funciones;
	}

	public void setFunciones(List<String> funciones) {
		this.funciones = funciones;
	}

	public void limpiar() {
		this.getFunciones().clear();
	}

	public String getHtmlMenu() {
		return this.htmlMenu;
	}

	public void setHtmlMenu(String htmlMenu) {
		this.htmlMenu = htmlMenu;
	}

	public HorusMenuPerfil getPerfilActual() {
		return this.perfilActual;
	}

	public void setPerfilActual(HorusMenuPerfil perfilActual) {
		this.perfilActual = perfilActual;
	}

	public HorusMenuRol getRolActual() {
		return this.rolActual;
	}

	public void setRolActual(HorusMenuRol rolActual) {
		this.rolActual = rolActual;
	}

	public String salir() {
//		((Identity) Component.getInstance(Identity.class)).logout();
		return "logOut";
	}

	public String home() {
		return "paginaPrincipal";
	}

	public Sucursal_VO getSucursalActual() {
		return this.sucursalActual;
	}

	public void setSucursalActual(Sucursal_VO sucursalActual) {
		this.sucursalActual = sucursalActual;
	}

	public Set<Sucursal_VO> getSucursalesDeUsuario() {
		return this.sucursalesDeUsuario;
	}

	public void setSucursalesDeUsuario(Set<Sucursal_VO> sucursalesDeUsuario) {
		this.sucursalesDeUsuario = sucursalesDeUsuario;
	}

	public boolean isPoseeUnRol() {
		return this.poseeUnRol;
	}

	public void setPoseeUnRol(boolean poseeUnRol) {
		this.poseeUnRol = poseeUnRol;
	}

	public boolean isPoseeUnaSucursal() {
		return this.poseeUnaSucursal;
	}

	public void setPoseeUnaSucursal(boolean poseeUnaSucursal) {
		this.poseeUnaSucursal = poseeUnaSucursal;
	}

	public void limpiarSucursal() {

		this.setPoseeUnaSucursal(false);

		this.getFunciones().clear();

	}

	public void limpiarRol() {
		this.setPoseeUnRol(false);

		this.limpiarSucursal();
	}

	public boolean isPoseeMail() {
		return this.poseeMail;
	}

	public void setPoseeMail(boolean poseeMail) {
		this.poseeMail = poseeMail;
	}

	public boolean isUsuario() {
		return this.usuario;
	}

	public void setUsuario(boolean usuario) {
		this.usuario = usuario;
	}

	// @In(scope=ScopeType.SESSION, value="infoIngresoUsuario")
	// @Out
	private InfoIngresoUsuario infoIngresoUsuario;

	public Boolean getConAyuda() {
		return this.conAyuda;
	}

	public void setConAyuda(Boolean conAyuda) {
		this.conAyuda = conAyuda;
	}

	// @WebRemote
	public void navegacionConAyuda(Boolean conAyuda) {
		this.setConAyuda(conAyuda);
	}

	/*******************************************************************************************/
	/*********************** Recuperar contrasena con respuesta ********************************/
	/*******************************************************************************************/
	private Boolean respuestaIncorrecta = false;
	private String respuestaSeguridad = "Ingrese una respuesta";
	private String preguntaSeguridad;
	private String username;

	public String recuperarContrasena() {
		this.setUsuario(false);

		return "cambiarContrasena";
	}

	public void motrarPreguntaRespuesta(EntityManager em) {
		DAO_Usuario dao = new DAO_Usuario();

		this.setPreguntaSeguridad(dao.recuperarPreguntaDeSeguridad(em,
				this.getUsername()));
		this.setUsuario(true);
		this.setRespuestaSeguridad("Ingrese una respuesta");
	}

	public String guardarContrasenaConRespuesta(EntityManager em) {
		DAO_Usuario dao = new DAO_Usuario();

		Boolean cambioCorrecto = dao.guardarContrasenaConRespuesta(em,
				this.getUsername(), this.getContrasenaNueva(),
				this.getRespuestaSeguridad());

		this.setRespuestaIncorrecta(!cambioCorrecto);
		if (!cambioCorrecto) {
			return "recuperarContrasena";
		} else {
			return "exito";
		}

	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRespuestaSeguridad() {
		return this.respuestaSeguridad;
	}

	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
	}

	public String getPreguntaSeguridad() {
		return this.preguntaSeguridad;
	}

	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	public Boolean getRespuestaIncorrecta() {
		return this.respuestaIncorrecta;
	}

	public void setRespuestaIncorrecta(Boolean respuestaIncorrecta) {
		this.respuestaIncorrecta = respuestaIncorrecta;
	}

	/*******************************************************************************************/
	/******************************** Guardar primer logueo *****************************************/
	/*******************************************************************************************/
	public void guardarDatosPrimerLogueo(EntityManager em) {
		// Recupero usuario
//		Usuario usr = (Usuario) Constants.getSessionContext().get(Usuario.class);
		
		Usuario usr = new Usuario(); //TODO
				
		// Primero se guarda nuevo password
		Boolean resul = this.cambiarPassword(em, usr);

		// Despues se guarda la pregunta y la respuesta
		if (resul) {
			DAO_Usuario dao = new DAO_Usuario();

			resul = dao.guardarNuevaPreguntaYRespuesta(em,
					usr.getNombreUsuario(), usr.getContrasena(),
					usr.getPreguntaSeguridad(), usr.getRespuestaSeguridad());

			if (resul) {
				// Guardo mail
				resul = dao.setNuevoEmail(em, usr.getNombreUsuario(),
						usr.getContrasena(), usr.getEmail());

				if (resul) {
					// Si todo salio bien, actualizo la respuesta dvalidar
					// usuario
					this.infoIngresoUsuario = dao.autenticarUsuario(em,
							usr.getNombreUsuario(), this.getContrasenaNueva());
				}
				this.infoIngresoUsuario.setEmail(usr.getEmail());
			}
		}
	}

	public InfoIngresoUsuario getInfoIngresoUsuario() {
		return this.infoIngresoUsuario;
	}

	public void setInfoIngresoUsuario(InfoIngresoUsuario infoIngresoUsuario) {
		this.infoIngresoUsuario = infoIngresoUsuario;
	}

	/*******************************************************************************************/
	/******************************** Cambiar password *****************************************/
	/*******************************************************************************************/
	public String contrasena;

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String contrasenaNueva;

	public String getContrasenaNueva() {
		return this.contrasenaNueva;
	}

	public void setContrasenaNueva(String contrasenaNueva) {
		this.contrasenaNueva = contrasenaNueva;
	}

	public Boolean cambiarPassword(EntityManager em, Usuario usr) {

		DAO_Usuario dao = new DAO_Usuario();

		Boolean cambioCorrecto = dao.cambiarPassword(em,
				usr.getNombreUsuario(), this.getContrasena(),
				this.getContrasenaNueva());

		if (cambioCorrecto) {
			// La nueva contrasena quedo como la actual.
			this.setContrasena(this.getContrasenaNueva());
			usr.setContrasena(this.getContrasenaNueva());
		}

		return cambioCorrecto;
	}

}