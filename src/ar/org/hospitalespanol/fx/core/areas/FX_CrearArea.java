package ar.org.hospitalespanol.fx.core.areas;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.areas.DAO_Areas;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.core.areas.AreaHorus;
import ar.org.hospitalespanol.vo.core.areas.Area_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearArea implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_CrearArea.class);

	private DAO_Areas dao;
	private EntityManager em;
	private Area_VO area;
	private String usuario;

	public FX_CrearArea(DAO<Area_VO> dao, Area_VO area, String nombreUsuario) {
		setDao((DAO_Areas) dao);
		setArea(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearArea.ejecutar()");

			try {

				AreaHorus a = (AreaHorus) getDao().guardar(getArea());
				getArea().setId(a.getId());

				getDao().resetQuery();

				String detalle = "El área " + getArea().getNombre()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getArea()
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
					"Ya existe un área con el mismo nombre, código y sucursal.");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".nombre) = :nombre AND lower("
						+ getDao().getIdClass() + ".codigo) = :codigo AND "
						+ getDao().getIdClass() + ".sucursal.id = :sucu) ");

		getDao().getCondiciones().put("nombre",
				getArea().getNombre().toLowerCase());

		getDao().getCondiciones().put("codigo",
				getArea().getCodigo().toLowerCase());

		getDao().getCondiciones().put("sucu", getArea().getSucursal().getId());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getArea().getNombre().length() == 0
				|| getArea().getCodigo().length() == 0
				|| getArea().getSucursal() == null) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Areas getDao() {
		return dao;
	}

	public void setDao(DAO_Areas dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Area_VO getArea() {
		return area;
	}

	public void setArea(Area_VO area) {
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
		return true;
	}

}
