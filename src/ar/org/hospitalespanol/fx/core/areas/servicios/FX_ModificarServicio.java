package ar.org.hospitalespanol.fx.core.areas.servicios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.areas.servicios.DAO_Servicio;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarServicio implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarServicio.class);

	private DAO_Servicio dao;
	private EntityManager em;
	private Servicio_VO servicio;
	private String usuario;

	public FX_ModificarServicio(DAO dao, Servicio_VO servicio_VO,
			String nombreUsuario) {
		this.setDao((DAO_Servicio) dao);
		setServicio(servicio_VO);
		setUsuario(nombreUsuario);
		this.setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarServicio.ejecutar()");
				
			try {
	
				getDao().guardar(getServicio());
				getDao().resetQuery();
	
				String detalle = "El servicio " + getServicio().getNombre()
						+ " se modificó correctamente";
	
				this.getRespuesta().setMensaje(detalle);
				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getServicio()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);
	
				adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);
	
			} catch (Exception e) {
				this.getRespuesta().setOk(false);
	
				this.getRespuesta().setMensaje("Ocurrió un error en la grabación");
			}
		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un servicio con el mismo nombre, código y área");

		}
		
		return getRespuesta();
	}

	private boolean validar() {

		
		getDao().setQueryCondiciones(
				" WHERE lower("+getDao().getIdClass()+".nombre) = :nombre " +
						"AND lower("+getDao().getIdClass()+".codigo) = :codigo " +
						"AND "+getDao().getIdClass()+".area.id = :idArea ");

		getDao().getCondiciones().put("nombre",
				getServicio().getNombre().toLowerCase());

		getDao().getCondiciones().put("codigo",
				getServicio().getCodigo().toLowerCase());
		
		getDao().getCondiciones().put("idArea",
				getServicio().getArea().getId());

		boolean ok = true;

		List<Servicio_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Servicio_VO srv : elementos) {
				if (!srv.equals(getServicio())) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
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
		return true;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getCanonicalName(), "El servicio "
				+ getServicio().getNombre() + " se modificó correctamente");

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
