package ar.com.builderadmin.fx.core.areas;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.areas.DAO_Sucursal;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.areas.Sucursal_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarSucursal implements I_FX { @Override public Boolean listar(){return true;}

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_ModificarSucursal.class);
	
	private DAO_Sucursal dao;
	private EntityManager em;
	private Sucursal_VO sucursal;
	private String usuario;

	public FX_ModificarSucursal(DAO<Sucursal_VO> dao, Sucursal_VO sucursal,String nombreUsuario) {
		setDao((DAO_Sucursal) dao);
		setSucursal(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {
			
			this.logger
				.debug("executing FX_ModificarSucursal.ejecutar()");
			
			try {

				getDao().guardar(getSucursal());
				getDao().resetQuery();

				String detalle = "La sucursal "
						+ getSucursal().getNombre()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getSucursal().getId(),
						getSucursal().getNombre(), detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al, this);

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
					"Ya existe una sucursal con el mismo nombre o código");

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

		List<Sucursal_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Sucursal_VO sucu : elementos) {
				if (!sucu.equals(getSucursal())) {
					ok = false;
				}
			}
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

	public Sucursal_VO getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal_VO sucursal) {
		this.sucursal = sucursal;
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
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "La sucursal " + getSucursal().getNombre()
				+ " se modificó correctamente");

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
