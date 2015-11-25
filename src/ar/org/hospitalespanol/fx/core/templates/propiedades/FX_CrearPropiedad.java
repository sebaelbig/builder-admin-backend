package ar.org.hospitalespanol.fx.core.templates.propiedades;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.templates.propiedades.DAO_Propiedades;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.core.templates.Propiedad;
import ar.org.hospitalespanol.vo.core.templates.Propiedad_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearPropiedad implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_CrearPropiedad.class);

	private DAO_Propiedades dao;
	private EntityManager em;
	private Propiedad_VO area;
	private String usuario;

	public FX_CrearPropiedad(DAO<Propiedad_VO> dao, Propiedad_VO area, String nombreUsuario) {
		setDao((DAO_Propiedades) dao);
		setPropiedad(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearPropiedad.ejecutar()");

			try {

				Propiedad a = (Propiedad) getDao().guardar(getPropiedad());
				getPropiedad().setId(a.getId());

				getDao().resetQuery();

				String detalle = "La propiedad " + getPropiedad().getNombre()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getPropiedad()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(a.toValueObjet()));
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
					"Ya existe una propiedad con el nombre: "+this.getPropiedad().getNombre());

		}

		return getRespuesta();

	}

	private boolean validar() {

		this.getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass()
						+ ".nombre) = :nombre ");

		getDao().getCondiciones().put("nombre",
				getPropiedad().getNombre().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getPropiedad().getNombre().length() == 0 ) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Propiedades getDao() {
		return dao;
	}

	public void setDao(DAO_Propiedades dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Propiedad_VO getPropiedad() {
		return area;
	}

	public void setPropiedad(Propiedad_VO area) {
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
