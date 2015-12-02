package ar.com.builderadmin.fx.core.usuarios;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.DAO_TipoIDHE;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.TipoIDHE_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarTipoIDHE implements I_FX {
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_BuscarTipoIDHE.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_TipoIDHE dao;
	private TipoIDHE_VO rol;
	private String usuario;

	public FX_BuscarTipoIDHE(DAO dao, TipoIDHE_VO rol, String usuario) {
		setDao((DAO_TipoIDHE) dao);
		setTipoID(rol);
		setUsuario(usuario);
	}

	private boolean validar() {
		return true;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		if (validar()) {

			try {

//				R_GetTipoDeID tipos = getDao().getTipoDeIDes(getTipoID());
//
//				JSON_Paginador pag = new JSON_Paginador();
//				pag.setElementos(tipos.getTipoDeIDs());
//				pag.setHayPaginaAnterior(false);
//				pag.setHayPaginaSiguiente(false);
//				pag.setMensaje(tipos.getMensaje());
//				pag.setPaginaActual(1);
//				pag.setTotalPaginas(1);
//				pag.setCantPorPagina(tipos.getTipoDeIDs().size());
//
//				getRespuesta().setPaginador(pag);
//				getRespuesta().setMensaje(tipos.getMensaje());
//				getRespuesta().setOk(tipos.getOk());
				

			} catch (Exception e) {
				this.getRespuesta().setOk(false);

				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");
				e.printStackTrace();
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje("Error al obtener los Tipo de IDs.");

		}

		return getRespuesta();
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

	/**
	 * @return the rol
	 */
	public TipoIDHE_VO getTipoID() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setTipoID(TipoIDHE_VO rol) {
		this.rol = rol;
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

		resp.put(this.getClass().getSimpleName(), "El tipo ID "
				+ getTipoID().getTipoID() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

}
