package ar.com.builderadmin.fx.core.datosSalud.prestaciones;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.datosSalud.prestaciones.DAO_TipoPrestacionHorus;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.datosSalud.TipoPrestacionHorus_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarTipoPrestacionHorus implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_EliminarTipoPrestacionHorus.class);

	private DAO_TipoPrestacionHorus dao;
	private EntityManager em;
	private TipoPrestacionHorus_VO propieada;
	private String usuario;

	public FX_EliminarTipoPrestacionHorus(DAO dao, TipoPrestacionHorus_VO area, String nombreUsuario) {
		setDao((DAO_TipoPrestacionHorus) dao);
		setTipoPrestacionHorus(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

			try {

				getTipoPrestacionHorus().setBorrado(true);
				getDao().guardar(getTipoPrestacionHorus());

				String detalle = "El estuduio " + getTipoPrestacionHorus().getNombre()
						+ " se eliminó correctamente";

				// Pasaje de la entidad activa a la entidad historica
				// getEm().persist(area.toHistoricoObject());

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTipoPrestacionHorus()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta()
						.setPaginador(JSON_Paginador.solo(getTipoPrestacionHorus()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta()
						.setMensaje("No es posible eliminar el tipo de id: "+getTipoPrestacionHorus());

			}
		return getRespuesta();

	}

	public DAO_TipoPrestacionHorus getDao() {
		return dao;
	}

	public void setDao(DAO_TipoPrestacionHorus dao) {
		this.dao = dao;
	}

	public TipoPrestacionHorus_VO getTipoPrestacionHorus() {
		return propieada;
	}

	public void setTipoPrestacionHorus(TipoPrestacionHorus_VO area) {
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
