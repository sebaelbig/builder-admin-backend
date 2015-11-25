package ar.org.hospitalespanol.fx.core.usuarios;

import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_TipoIDHE;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_TipoIDHE.R_CrearTipoDeID;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.TipoIDHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_EliminarTipoIDHE implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(FX_EliminarTipoIDHE.class);

	private DAO_TipoIDHE dao;
	private EntityManager em;
	private TipoIDHE_VO propieada;
	private String usuario;

	public FX_EliminarTipoIDHE(DAO dao, TipoIDHE_VO area, String nombreUsuario) {
		setDao((DAO_TipoIDHE) dao);
		setTipoIDHE(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

			try {
				getTipoIDHE().setEstado(TipoIDHE_VO.INACTIVO);
				R_CrearTipoDeID resul = getDao().guardar(getTipoIDHE());
				
				if (resul.getOk()){
					getTipoIDHE().setBorrado(true);
					getDao().guardar(getTipoIDHE());
	
					String detalle = "El tipo de id " + getTipoIDHE().getTipoID()
							+ " se eliminó correctamente";
	
					// Pasaje de la entidad activa a la entidad historica
					// getEm().persist(area.toHistoricoObject());
	
					// Se genera y persiste el alerta correspondiente a la funcion
					// FX
					Alerta al = new Alerta(getUsuario(), new Date(), getTipoIDHE()
							.getId(), this.getClass().getCanonicalName(), detalle,
							E_Priority.BAJA);
	
					adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
							this);
	
					System.out.println(detalle);
					this.logger.info(detalle);
	
					this.getRespuesta()
							.setPaginador(JSON_Paginador.solo(getTipoIDHE()));
					this.getRespuesta().setMensaje(detalle);
					this.getRespuesta().setOk(true);
				}else{
					this.getRespuesta().setMensaje(resul.getMensaje());
					this.getRespuesta().setOk(false);
				}
				
			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta()
						.setMensaje("No es posible eliminar el tipo de id: "+getTipoIDHE());

			}
		return getRespuesta();

	}

	public DAO_TipoIDHE getDao() {
		return dao;
	}

	public void setDao(DAO_TipoIDHE dao) {
		this.dao = dao;
	}

	public TipoIDHE_VO getTipoIDHE() {
		return propieada;
	}

	public void setTipoIDHE(TipoIDHE_VO area) {
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
