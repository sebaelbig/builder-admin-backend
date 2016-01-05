package ar.com.builderadmin.fx.core.usuarios;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearUsuario implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearUsuario.class);

	private DAO_Usuario dao;
	private EntityManager em;
	private Usuario_VO usuario;
	private String usuarioAccion;

	public FX_CrearUsuario(DAO<Usuario_VO> dao, Usuario_VO user,
			String nombreUsuario) {
		setDao((DAO_Usuario) dao);
		setUsuario(user);
		setUsuarioAccion(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas){
		if (validar()) {

			this.logger.debug("executing FX_CrearUsuario.ejecutar()");

			try {

//				if (getUsuario().getNombreUsuario()==null){
//					getUsuario().setNombreUsuario(getDao().obtenerNombreDeUsuario(this.getUsuario()));
//				}
				
				getDao().guardar(this.usuario);
				getDao().resetQuery();

				String detalle = "El usuario " + this.usuario.getNombres()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuarioAccion(), new Date(), this.usuario
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_CrearUsuario", "ejecutar", getUsuarioAccion(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(this.usuario));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un usuario con el mismo tipo y nro de documento ");

		}

		return getRespuesta();

	}

	private boolean validar() {
		return getDao().buscarUsuarioPorDocumento(this.usuario.getTipoDocumento(), this.usuario.getNroDocumento()) == null;
	}

	public DAO_Usuario getDao() {
		return dao;
	}

	public void setDao(DAO_Usuario dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setUsuario(Usuario_VO sucursal) {
		this.usuario = sucursal;
	}

	public String getUsuarioAccion() {
		return usuarioAccion;
	}

	public void setUsuarioAccion(String usuario) {
		this.usuarioAccion = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El usuario "
				+ this.usuario.getNombres() + " se creó correctamente");

		return resp;
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public Boolean listar() {
		return true;
	}
	
	@Override
	public String getUsuario() {
		return usuarioAccion;
	}
}
