package ar.org.hospitalespanol.fx.core;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.DAO_Parametro;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.Parametro_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearParametro implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_CrearParametro.class);

	private DAO_Parametro dao;
	private EntityManager em;
	private Parametro_VO area;
	private String usuario;

	public FX_CrearParametro(DAO<Parametro_VO> dao, Parametro_VO area, String nombreUsuario) {
		setDao((DAO_Parametro) dao);
		setParametro(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearParametro.ejecutar()");

			try {

				getDao().guardar(getParametro());

				getDao().resetQuery();

				String detalle = "La propiedad " + getParametro().getNombre()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getParametro()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getParametro()));
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
					"Ya existe una propiedad con el nombre: "+this.getParametro().getNombre());

		}

		return getRespuesta();

	}

	private boolean validar() {

		this.getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass()
						+ ".nombre) = :nombre ");

		getDao().getCondiciones().put("nombre",
				getParametro().getNombre().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getParametro().getNombre().length() == 0 ) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Parametro getDao() {
		return dao;
	}

	public void setDao(DAO_Parametro dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Parametro_VO getParametro() {
		return area;
	}

	public void setParametro(Parametro_VO area) {
		this.area = area;
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
		return new java.util.HashMap<String, Object>();
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
		return false;
	}

}
