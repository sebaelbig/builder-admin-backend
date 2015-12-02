package ar.com.builderadmin.fx.core.usuarios.roles;

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
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_RolHE;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_RolHE.R_CrearRol;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

public class FX_ModificarTipoDeRol implements I_FX {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarTipoDeRol.class);

	private DAO_TipoDeRol dao;
	private EntityManager em;
	private TipoDeRol_VO sucursal;
	private String usuario;

	public FX_ModificarTipoDeRol(DAO<TipoDeRol_VO> dao, TipoDeRol_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDeRol) dao);
		setTipoDeRol(sucursal);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarTipoDeRol.ejecutar()");

			try {

				DAO_RolHE daoRolHE = new DAO_RolHE();
				R_CrearRol resul = daoRolHE.guardar(getEm(), getTipoDeRol());
				
				if (resul.getOk()){
				
					getDao().guardar(getTipoDeRol());
					getDao().resetQuery();
	
					String detalle = "El tipo de rol " + getTipoDeRol().getNombre()
							+ " se modificó correctamente";
	
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
						"Ocurrió un error en la grabación");

			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un tipo de rol con el mismo nombre o código");

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

		getDao().getCondiciones().put("codigo",
				getTipoDeRol().getCodigo().toLowerCase());

		boolean ok = true;

		List<TipoDeRol_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (TipoDeRol_VO tipoRol : elementos) {
				if (!tipoRol.equals(getTipoDeRol())) {
					ok = false;
				}
			}
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

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El tipo de rol "
				+ getTipoDeRol().getNombre() + " se modificó correctamente");

		return resp;
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
		return true;
	}
}
