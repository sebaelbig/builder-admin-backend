package ar.org.hospitalespanol.fx.core.usuarios;

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
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_TipoIDHE;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_TipoIDHE.R_CrearTipoDeID;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.TipoIDHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_CrearTipoIDHE implements I_FX {
	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearTipoIDHE.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_TipoIDHE dao;
	private EntityManager em;
	private TipoIDHE_VO tipo;
	private String usuario;

	public FX_CrearTipoIDHE(DAO dao, TipoIDHE_VO tipo, String usuario) {
		setDao((DAO_TipoIDHE) dao);
		setUsuario(usuario);
		setTipo(tipo);
		setEm(getDao().getEntityManager());
	}

	private boolean validar() {
		
		getDao().setQueryCondiciones(
				" WHERE lower(" + getDao().getIdClass()
						+ ".tipoID) = :nombre ");
		
		if (getTipo().getTipoID()!=null)
			getDao().getCondiciones().put("nombre",
					getTipo().getTipoID().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getTipo().getTipoID() == null
				|| getTipo().getTipoID().length() == 0) {
			ok = false;
		}

		getDao().resetQuery();

		return ok;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {

				R_CrearTipoDeID resul = getDao().guardar(getTipo());
				
				if (resul.getOk()){
					getDao().guardar(getTipo());
					getDao().resetQuery();

					String detalle = "El tipo de ID " + getTipo().getTipoID()
							+ " se creó correctamente";
					
					// Se genera y persiste el alerta correspondiente a la funcion
					// FX
					Alerta al = new Alerta(getUsuario(), new Date(), getTipo()
							.getId(), this.getClass().getCanonicalName(), detalle,
							E_Priority.BAJA);
					
					adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
							this);
					
					DAO_Utils.info(logger, "FX_CrearTipoIDHE", "ejecutar", getUsuario(), detalle);
					
					this.getRespuesta().setPaginador(
							JSON_Paginador.solo(getTipo()));
					this.getRespuesta().setMensaje(detalle);
					this.getRespuesta().setOk(true);
				
				}else{
					this.getRespuesta().setMensaje(resul.getMensaje());
					this.getRespuesta().setOk(false);
				}


			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

				e.printStackTrace();
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje("Debe ingresar un tipo de ID o el tipo de id ya existe.");

		}

		return getRespuesta();
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO paramPerfil_VO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El tipo de ID "
				+ getTipo().getTipoID() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the tipo
	 */
	public TipoIDHE_VO getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoIDHE_VO tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the dao
	 */
	public DAO_TipoIDHE getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_TipoIDHE dao) {
		this.dao = dao;
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
