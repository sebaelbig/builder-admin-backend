package ar.com.builderadmin.fx.core.usuarios.roles;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_RolHE;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_RolHE.R_CrearRol;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.core.usuarios.roles.TipoDeRol;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

public class FX_EliminarTipoDeRol implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_EliminarTipoDeRol.class);

	private DAO_TipoDeRol dao;
	private EntityManager em;
	private TipoDeRol_VO sucursal;
	private String usuario;

	public FX_EliminarTipoDeRol(DAO<TipoDeRol_VO> dao, TipoDeRol_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDeRol) dao);
		setTipoDeRol(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		try {
			
			DAO_RolHE daoRolHE = new DAO_RolHE();
			getTipoDeRol().setEstado(TipoDeRol.INACTIVO);
			R_CrearRol resul = daoRolHE.guardar(getEm(), getTipoDeRol());
			
			if (resul.getOk()){
				
				getTipoDeRol().setBorrado(true);
				getDao().guardar(getTipoDeRol());
	
				String detalle = "El tipo de rol " + getTipoDeRol().getNombre()
						+ " se eliminó correctamente";
	
				// Pasaje de la entidad activa a la entidad historica
				// getEm().persist(sucursal.toHistoricoObject());
	
				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getTipoDeRol()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);
	
				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);
	
				DAO_Utils.info(logger, "FX_ModificarTipoDeRol", "ejecutar", getUsuario(), detalle);
	
				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getTipoDeRol()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);
			}else{
				this.getRespuesta().setMensaje(resul.getMensaje());
				this.getRespuesta().setOk(false);
			}

		} catch (Exception e) {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"No es posible eliminar el tipo de rol");

		}
		
		return getRespuesta();

	}

	public DAO_TipoDeRol getDao() {
		return dao;
	}

	public void setDao(DAO_TipoDeRol dao) {
		this.dao = dao;
	}

	public TipoDeRol_VO getTipoDeRol() {
		return sucursal;
	}

	public void setTipoDeRol(TipoDeRol_VO sucursal) {
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
