package ar.com.builderadmin.fx.core.areas;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.areas.DAO_Areas;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.areas.Area_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarArea implements I_FX { @Override public Boolean listar(){return true;}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_ModificarArea.class);
	
	private DAO_Areas dao;
	private EntityManager em;
	private Area_VO area;
	private String usuario;

	public FX_ModificarArea(DAO<Area_VO> dao, Area_VO area,String nombreUsuario) {
		setDao((DAO_Areas) dao);
		setArea(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger
				.debug("executing FX_ModificarArea.ejecutar()");
			
			try {

				getDao().guardar(getArea());
				getDao().resetQuery();

				String detalle = "El área "
						+ getArea().getNombre()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getArea().getId(),
						getArea().getNombre(), detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				System.out.println(detalle);
				this.logger.info(detalle);
				
				this.getRespuesta().setPaginador(JSON_Paginador.solo(getArea()));   
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
					"Ya existe un área con el mismo nombre o código o sucursal");

		}
		return getRespuesta();
	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower("+getDao().getIdClass()+".nombre) = :nombre AND lower("+getDao().getIdClass()+".codigo) = :codigo AND "+getDao().getIdClass()+".sucursal.id = :idSucu) ");

		getDao().getCondiciones().put("nombre",
				getArea().getNombre().toLowerCase());

		getDao().getCondiciones().put("codigo",
				getArea().getCodigo().toLowerCase());
		
		getDao().getCondiciones().put("idSucu",
				getArea().getSucursal().getId());

		boolean ok = true;

		List<Area_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Area_VO area : elementos) {
				if (!area.equals(getArea())) {
					ok = false;
				}
			}
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

	public Area_VO getArea() {
		return area;
	}

	public void setArea(Area_VO area) {
		this.area = area;
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

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	
	
	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(EntityManager em) {
		return new java.util.HashMap<String, Object>();
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
