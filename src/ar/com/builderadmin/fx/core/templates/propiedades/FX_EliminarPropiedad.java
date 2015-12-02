package ar.com.builderadmin.fx.core.templates.propiedades;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.templates.propiedades.DAO_Propiedades;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.templates.Propiedad_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarPropiedad implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_CrearPropiedad.class);

	private DAO_Propiedades dao;
	private EntityManager em;
	private Propiedad_VO propieada;
	private String usuario;

	public FX_EliminarPropiedad(DAO<Propiedad_VO> dao, Propiedad_VO area, String nombreUsuario) {
		setDao((DAO_Propiedades) dao);
		setPropiedad(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

			try {

				getPropiedad().setBorrado(true);
				getDao().guardar(getPropiedad());

				String detalle = "La propiedad " + getPropiedad().getNombre()
						+ " se eliminó correctamente";

				// Pasaje de la entidad activa a la entidad historica
				// getEm().persist(area.toHistoricoObject());

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getPropiedad()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta()
						.setPaginador(JSON_Paginador.solo(getPropiedad()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta()
						.setMensaje("No es posible eliminar la propiedad: "+getPropiedad());

			}
		return getRespuesta();

	}

	public DAO_Propiedades getDao() {
		return dao;
	}

	public void setDao(DAO_Propiedades dao) {
		this.dao = dao;
	}

	public Propiedad_VO getPropiedad() {
		return propieada;
	}

	public void setPropiedad(Propiedad_VO area) {
		this.propieada = area;
	}

	public String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
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
		return false;
	}

}
