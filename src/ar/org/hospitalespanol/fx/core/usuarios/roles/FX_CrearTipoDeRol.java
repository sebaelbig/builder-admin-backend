package ar.org.hospitalespanol.fx.core.usuarios.roles;

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
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE.R_CrearRol;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;

public class FX_CrearTipoDeRol implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearTipoDeRol.class);

	private DAO_TipoDeRol dao;
	private EntityManager em;
	private TipoDeRol_VO tipoRol;
	private String usuario;

	public FX_CrearTipoDeRol(DAO<TipoDeRol_VO> dao, TipoDeRol_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDeRol) dao);
		setTipoDeRol(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearTipoDeRol.ejecutar()");

			try {

				DAO_RolHE daoRolHE = new DAO_RolHE();
				R_CrearRol resul = daoRolHE.guardar(getEm(), getTipoDeRol());
				
				if (resul.getOk()){
					
					getDao().guardar(getTipoDeRol());
					getDao().resetQuery();
					
					String detalle = "El tipo de rol" + getTipoDeRol().getNombre()
							+ " se creo correctamente";
					
					// Se genera y persiste el alerta correspondiente a la funcion
					// FX
					Alerta al = new Alerta(getUsuario(), new Date(), getTipoDeRol()
							.getId(), this.getClass().getCanonicalName(), detalle,
							E_Priority.BAJA);
					
					adminAlertas.guardarAlerta(getEm(), al, this);
					
					DAO_Utils
					.info(logger, "FX_CrearTipoDeRol", "ejecutar", getUsuario(), detalle);
					
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
						"Ocurrió un error en la grabación");

			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un tipo de rol con el mismo nombre o código ");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".nombre) = :nombre AND lower("
						+ getDao().getIdClass() + ".codigo) = :codigo ) ");

		getDao().getCondiciones().put("nombre",
				getTipoDeRol().getNombre().toLowerCase());

		if (getTipoDeRol().getCodigo()!=null)
			getDao().getCondiciones().put("codigo",
					getTipoDeRol().getCodigo().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getTipoDeRol().getNombre().length() == 0
				|| getTipoDeRol().getCodigo().length() == 0) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_TipoDeRol getDao() {
		return dao;
	}

	public void setDao(DAO_TipoDeRol dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public TipoDeRol_VO getTipoDeRol() {
		return tipoRol;
	}

	public void setTipoDeRol(TipoDeRol_VO sucursal) {
		this.tipoRol = sucursal;
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

		resp.put(this.getClass().getSimpleName(), "El tipo de rol "
				+ getTipoDeRol().getNombre() + " se creó correctamente");

		return resp;
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
