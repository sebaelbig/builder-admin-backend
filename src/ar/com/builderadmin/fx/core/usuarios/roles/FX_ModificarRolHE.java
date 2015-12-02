package ar.com.builderadmin.fx.core.usuarios.roles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_RolHE;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.AlertaHE;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.RolHE_VO;

public class FX_ModificarRolHE implements I_FX {
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarRolHE.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_RolHE dao;
	private RolHE_VO rol;
	private String usuario;

	public FX_ModificarRolHE(DAO dao, RolHE_VO rol, String usuario) {
		setDao((DAO_RolHE) dao);
		setRol(rol);
		setUsuario(usuario);
	}

	private boolean validar() {

		// R_GetRol resp = getDao().getUnoPorRol(getDao(), getRol());

		// Que este todo bien y que no exista un rol con ese nombre
		// return resp.getOk() && resp.getRoles().size() == 0;
		return true;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {

			try {

//				R_CrearRol resul = getDao().guardar(
//						this.getDao().getEntityManager(), getRol());
				getDao().resetQuery();

				String detalle = "El rol " + getRol().getRol()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				// Genero un ID de tipo Long
				Long id = Admin_Abstracto.getNextAlertaID(getDao()
						.getEntityManager());

				AlertaHE al = new AlertaHE(getUsuario(), new Date(), getRol()
						.getId_rol(), id, getRol().getRol(), detalle,
						E_Priority.BAJA);

				adminAlerta
						.guardarAlerta(getDao().getEntityManager(), al, this);

				this.getRespuesta().setPaginador(JSON_Paginador.solo(getRol()));
//				this.getRespuesta().setMensaje(resul.getMensaje());
				this.getRespuesta().setOk(true);
				
			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");
				e.printStackTrace();
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje("El Rol ya existe");

		}

		return getRespuesta();
	}

	/**
	 * @return the dao
	 */
	public DAO_RolHE getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_RolHE dao) {
		this.dao = dao;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the rol
	 */
	public RolHE_VO getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(RolHE_VO rol) {
		this.rol = rol;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO paramPerfil_VO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		
		Map<String, Object> resp = new HashMap<String, Object>();

		String key = this.getClass().getSimpleName();
		String descripcion = getRol().getLogin()+" modificó el rol -"+getRol().getRol()+"- correctamente.";
		
		resp.put(key, descripcion);

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

}
