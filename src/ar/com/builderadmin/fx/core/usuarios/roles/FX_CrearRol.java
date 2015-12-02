package ar.com.builderadmin.fx.core.usuarios.roles;

import java.util.ArrayList;
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
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_Rol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_CrearPerfil;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class FX_CrearRol implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearRol.class);

	private DAO_Rol dao;
	private EntityManager em;
	private Rol_VO rol;
	private String usuario;

	private JSON_Respuesta respuesta = new JSON_Respuesta();
	
	public FX_CrearRol(DAO dao, Rol_VO r,
			String nombreUsuario) {
		setDao((DAO_Rol) dao);
		setRol(r);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearRol.ejecutar()");

			try {
				
				/**
				 * Me fijo si los perfiles NO estan en la BD
				 */
				JSON_Respuesta resp = new JSON_Respuesta();

				//Si el rol no tiene ID entonces, primero persisto el rol, para tener su ID
				DAO_Perfil daoPerfil = getDao().getInstance(DAO_Perfil.class);
				List<Perfil_VO> perfiles = getRol().getPerfiles();
				getRol().setPerfiles(new ArrayList<Perfil_VO>());
				
				getDao().guardar(getRol());
				getDao().resetQuery();
				
				//Una vez que tengo el ID del rol, se los asigno a los perfiles
				for (Perfil_VO per : perfiles) {
					
					//No esta persistida, La persisto
					per.refreshValues();
					per.setIdRol(getRol().getId());
					
					FX_CrearPerfil fx_crearPerfil = new FX_CrearPerfil(daoPerfil, per, getUsuario());
					resp.setOk( fx_crearPerfil.ejecutar(adminAlertas).getOk() );
					
					//Voy agregando los perfiles al rol
					getRol().getPerfiles().add(per);
					
				}
				
				String detalle = "El rol " + getRol().getNombre()
						+ " se creo correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getRol()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_CrearRol", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getRol()));
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
					"Ya existe un rol con el mismo nombre o código ");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()+ ".tipoID.id_tipoId) = :idTipoId " +
						" AND lower("+ getDao().getIdClass() + ".codigo) = :codigo ) " +
						" AND "+ getDao().getIdClass() + ".usuario.id = :idUsr");

		getDao().getCondiciones().put("idTipoId",
				getRol().getTipoId().getId_tipoId());

		getDao().getCondiciones().put("codigo",
					getRol().getCodigo().toLowerCase());
		
		getDao().getCondiciones().put("idUsr",
					getRol().getUsuario().getId());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getRol().getNombre().length() == 0
				|| getRol().getCodigo().length() == 0) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Rol getDao() {
		return dao;
	}

	public void setDao(DAO_Rol dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Rol_VO getRol() {
		return rol;
	}

	public void setRol(Rol_VO sucursal) {
		this.rol = sucursal;
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

		resp.put(this.getClass().getSimpleName(), "El  rol "
				+ getRol().getNombre() + " se creó correctamente");

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
		return false;
	}
}
