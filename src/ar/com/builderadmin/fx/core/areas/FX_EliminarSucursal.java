package ar.com.builderadmin.fx.core.areas;

import java.util.Date;

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

public class FX_EliminarSucursal implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearSucursal.class);

	private DAO_Sucursal dao;
	private EntityManager em;
	private Sucursal_VO sucursal;
	private String usuario;

	public FX_EliminarSucursal(DAO<Sucursal_VO> dao, Sucursal_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_Sucursal) dao);
		setSucursal(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		try {
			
			getSucursal().setBorrado(true);
			getDao().guardar(getSucursal());

			String detalle = "La sucursal " + getSucursal().getNombre()
					+ " se eliminó correctamente";

			// Pasaje de la entidad activa a la entidad historica
			// getEm().persist(sucursal.toHistoricoObject());

			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getSucursal()
					.getId(), this.getClass().getCanonicalName(), detalle,
					E_Priority.BAJA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
					this);

			System.out.println(detalle);
			this.logger.info(detalle);

			this.getRespuesta().setPaginador(
					JSON_Paginador.solo(getSucursal()));
			this.getRespuesta().setMensaje(detalle);
			this.getRespuesta().setOk(true);

		} catch (Exception e) {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"No es posible eliminar el Sucursal");

		}
		
		return getRespuesta();

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
