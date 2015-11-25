package ar.org.hospitalespanol.fx.core.usuarios.perfiles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearPerfil implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearPerfil.class);

	private DAO_Perfil dao;
	private EntityManager em;
	private Perfil_VO perfil;
	private String usuario;

	private JSON_Respuesta respuesta = new JSON_Respuesta();
	
	public FX_CrearPerfil(DAO dao, Perfil_VO r,
			String nombreUsuario) {
		setDao((DAO_Perfil) dao);
		setPerfil(r);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearPerfil.ejecutar()");

			try {
				
				getDao().guardar(getPerfil());
				getDao().resetQuery();

				String detalle = "El perfil " + getPerfil().getNombre()
						+ " se creo correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getPerfil()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_CrearPerfil", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getPerfil()));
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
					"Ya existe un perfil con el mismo nombre o código ");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE lower("
						+ getDao().getIdClass() + ".codigo) = :codigo "
						+" AND "+ getDao().getIdClass() + ".rol.id = :idRol"
						+" AND "+ getDao().getIdClass() + ".servicio.id = :idSrv");

		getDao().getCondiciones().put("codigo",
				getPerfil().getCodigo().toLowerCase());
	
		getDao().getCondiciones().put("idRol",
				getPerfil().getIdRol());
	
		getDao().getCondiciones().put("idSrv",
				getPerfil().getIdServicio());
		
		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getPerfil().getNombre().length() == 0
				|| getPerfil().getCodigo().length() == 0) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Perfil getDao() {
		return dao;
	}

	public void setDao(DAO_Perfil dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Perfil_VO getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil_VO sucursal) {
		this.perfil = sucursal;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El  perfil "
				+ getPerfil().getNombre() + " se creó correctamente");

		return resp;
	}

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
}
