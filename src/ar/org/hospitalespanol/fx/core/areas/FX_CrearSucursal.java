package ar.org.hospitalespanol.fx.core.areas;

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
import ar.org.hospitalespanol.dao.core.areas.DAO_Sucursal;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.core.areas.Sucursal;
import ar.org.hospitalespanol.vo.core.areas.Sucursal_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearSucursal implements I_FX { @Override public Boolean listar(){return true;}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_CrearSucursal.class);

	private DAO_Sucursal dao;
	private EntityManager em;
	private Sucursal_VO sucursal;
	private String usuario;

	public FX_CrearSucursal(DAO<Sucursal_VO> dao, Sucursal_VO sucursal, String nombreUsuario) {
		setDao((DAO_Sucursal) dao);
		setSucursal(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {
			
			this.logger
				.debug("executing FX_CrearSucursal.ejecutar()");

			try {

				Sucursal a = (Sucursal)getDao().guardar(getSucursal());
				getSucursal().setId(a.getId());
				
				getDao().resetQuery();

				String detalle = "La sucursal "
						+ getSucursal().getNombre()
						+ " se creo correctamente";
				
				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getSucursal().getId(),
						getSucursal().getNombre(), detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				System.out.println(detalle);
				this.logger.info(detalle);
				
				this.getRespuesta().setPaginador(JSON_Paginador.solo(getSucursal()));   
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
					"Ya existe una sucursal con el mismo nombre o código ");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower("+getDao().getIdClass()+".nombre) = :nombre AND lower("+getDao().getIdClass()+".codigo) = :codigo ) ");

		getDao().getCondiciones().put("nombre",
				getSucursal().getNombre().toLowerCase());

		getDao().getCondiciones().put("codigo",
				getSucursal().getCodigo().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getSucursal().getNombre().length() == 0
				|| getSucursal().getCodigo().length() == 0) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Sucursal getDao() {
		return dao;
	}

	public void setDao(DAO_Sucursal dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Sucursal_VO getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal_VO sucursal) {
		this.sucursal = sucursal;
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

		resp.put(this.getClass().getSimpleName(), "La sucursal " + getSucursal().getNombre()
				+ " se creó correctamente");

		return resp;
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

}
