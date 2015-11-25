package ar.org.hospitalespanol.fx.historiaClinica.pedidos.modalidad;

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
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.DAO_Modalidad;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Modalidad_VO;

public class FX_EliminarModalidad implements I_FX {
	

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarModalidad.class);

	private DAO_Modalidad dao;
	private EntityManager em;
	private Modalidad_VO modalidad;
	private String usuario;

	public FX_EliminarModalidad(DAO dao, Modalidad_VO modalidad_VO,
			String nombreUsuario) {
		this.setDao((DAO_Modalidad) dao);
		this.setModalidad(modalidad_VO);
		this.setEm(getDao().getEntityManager());
		this.setUsuario(nombreUsuario);
		
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		this.logger.debug("executing FX_EliminarModalidad.ejecutar()");
		
		try {

			getDao().eliminar(getModalidad());
//			getModalidad().setBorrado(true);
//			getDao().guardar(getModalidad());

			String detalle = "La modalidad " + getModalidad().getDescripcion()+"("+getModalidad().getCodigo()+")"
						+ " se eliminó correctamente";

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getModalidad()
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getModalidad()));
			this.getRespuesta().setOk(true);
			this.getRespuesta().setMensaje(detalle);
			System.out.println(detalle);
			this.logger.info(detalle);

		} catch (Exception e) {

			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Se produjo un error al intentar eliminar el modalidad");
			e.printStackTrace();
		}

		return getRespuesta();

	}

	public DAO_Modalidad getDao() {
		return dao;
	}

	public void setDao(DAO_Modalidad dao) {
		this.dao = dao;
	}

	public Modalidad_VO getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad_VO modalidad_VO) {
		this.modalidad = modalidad_VO;
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

		resp.put(this.getClass().getCanonicalName(), "La modalidad " + getModalidad().getDescripcion()
				+"("+getModalidad().getCodigo()+")"+ " se eliminó correctamente");

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
