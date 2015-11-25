package ar.org.hospitalespanol.fx.core.usuarios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_Usuario;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarUsuario implements I_FX {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarUsuario.class);

	private DAO_Usuario dao;
	private EntityManager em;
	private Usuario_VO usuario;
	private String usuarioAccion;

	public FX_ModificarUsuario(DAO<Usuario_VO> dao, Usuario_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_Usuario) dao);
		setUsuario(sucursal);
		setUsuarioAccion(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarUsuario.ejecutar()");

			try {

				getDao().guardar(this.usuario);
				getDao().resetQuery();

				String detalle = "El usuario " + this.usuario.getNombres()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuarioAccion(), new Date(), this.usuario
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_ModificarUsuario", "ejecutar",getUsuarioAccion(),
						detalle);

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
					"Ya existe un tipo de rol con el mismo nombre o código");

		}
		return getRespuesta();
	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".nombreUsuario) = :nombre ");

		getDao().getCondiciones().put("nombre",
				this.usuario.getNombreUsuario().toLowerCase());


		boolean ok = true;

		List<Usuario_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Usuario_VO tipoRol : elementos) {
				if (!tipoRol.equals(this.usuario)) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Usuario getDao() {
		return dao;
	}

	public void setDao(DAO_Usuario dao) {
		this.dao = dao;
	}

	public void setUsuario(Usuario_VO sucursal) {
		this.usuario = sucursal;
	}

	private String getUsuarioAccion() {
		return usuarioAccion;
	}

	private void setUsuarioAccion(String usuario) {
		this.usuarioAccion = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El tipo de rol "
				+ this.usuario.getNombres() + " se modificó correctamente");

		return resp;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
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
