package ar.com.builderadmin.fx.core.usuarios.perfiles;

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
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ModificarPerfil implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarPerfil.class);

	private DAO_Perfil dao;
	private EntityManager em;
	private Perfil_VO perfil;
	private String usuario;

	private JSON_Respuesta respuesta = new JSON_Respuesta();
	
	public FX_ModificarPerfil(DAO dao, Perfil_VO r,
			String nombreUsuario) {
		setDao((DAO_Perfil) dao);
		setPerfil(r);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarPerfil.ejecutar()");

			try {
				
				getDao().guardar(getPerfil());
				getDao().resetQuery();

				String detalle = "El perfil " + getPerfil().getNombre()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getPerfil()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_ModificarPerfil", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getPerfil()));
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
					"Ya existe un perfil con el mismo nombre o código ");

		}

		return getRespuesta();

	}

	private boolean validar() {
		
		getDao().resetQuery();
		
		String condi = "";

		if (getPerfil().getNombre()!=null){

			condi += " WHERE (lower(" + getDao().getIdClass()+ ".nombre) = :nom) ";
			getDao().getCondiciones().put("nom",
					getPerfil().getNombre().toLowerCase());
		}

		if (getPerfil().getCodigo()!=null){
			condi += (condi.length()>0)?" AND ":" WHERE ";
			
			condi += "(lower(" + getDao().getIdClass()+ ".codigo) = :codigo) ";
			getDao().getCondiciones().put("codigo",
					getPerfil().getCodigo().toLowerCase());
		}
		
		if (getPerfil().getIdRol()!=null){
			condi += (condi.length()>0)?" AND ":" WHERE ";
			
			condi += getDao().getIdClass() + ".rol.id = :idRol ";
			getDao().getCondiciones().put("idRol",
					getPerfil().getIdRol());
		}
		
		if (getPerfil().getIdServicio()!=null){
			condi += (condi.length()>0)?" AND ":" WHERE ";
			
			condi += getDao().getIdClass() + ".servicio.id = :idSrv ";
			getDao().getCondiciones().put("idSrv",
					getPerfil().getIdServicio());
		}
		
		getDao().setQueryCondiciones(condi);
		boolean ok = true;
		
		List<Perfil_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Perfil_VO perfil : elementos) {
				if (!perfil.equals(getPerfil())) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Perfil getDao() {
		return dao;
	}

	public void setDao(DAO_Perfil dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Perfil_VO getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil_VO sucursal) {
		this.perfil = sucursal;
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

		resp.put(this.getClass().getSimpleName(), "El  perfil "
				+ getPerfil().getNombre() + " se modificó correctamente");

		return resp;
	}

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
