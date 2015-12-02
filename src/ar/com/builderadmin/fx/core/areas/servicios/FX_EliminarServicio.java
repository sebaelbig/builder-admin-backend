package ar.com.builderadmin.fx.core.areas.servicios;

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
import ar.com.builderadmin.dao.core.areas.servicios.DAO_Servicio;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarServicio implements I_FX {
	

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarServicio.class);

	private DAO_Servicio dao;
	private EntityManager em;
	private Servicio_VO servicio;
	private String usuario;

	public FX_EliminarServicio(DAO dao, Servicio_VO servicio_VO,
			String nombreUsuario) {
		this.setDao((DAO_Servicio) dao);
		this.setServicio(servicio_VO);
		this.setEm(getDao().getEntityManager());
		this.setUsuario(nombreUsuario);
		
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		this.logger.debug("executing FX_EliminarServicio.ejecutar()");
		
		try {

			getDao().eliminar(getServicio());
//			getServicio().setBorrado(true);
//			getDao().guardar(getServicio());

			String detalle = "El servicio " + getServicio().getNombre()
					+ " se eliminó correctamente";

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getServicio()
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getServicio()));
			this.getRespuesta().setOk(true);
			this.getRespuesta().setMensaje(detalle);
			System.out.println(detalle);
			this.logger.info(detalle);

		} catch (Exception e) {

			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Se produjo un error al intentar eliminar el servicio");
			e.printStackTrace();
		}

		return getRespuesta();

	}

	public DAO_Servicio getDao() {
		return dao;
	}

	public void setDao(DAO_Servicio dao) {
		this.dao = dao;
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio_VO) {
		this.servicio = servicio_VO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return perfil.tieneServicio();
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

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getCanonicalName(), "El servicio "
				+ getServicio().getNombre() + " se eliminó correctamente");

		return resp;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}
