package ar.org.hospitalespanol.fx.core.usuarios.roles;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_Rol;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_CrearPerfil;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_EliminarPerfil;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_ModificarPerfil;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

public class FX_ModificarRol implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarRol.class);

	private DAO_Rol dao;
	private EntityManager em;
	private Rol_VO rol;
	private String usuario;

	private JSON_Respuesta respuesta = new JSON_Respuesta();
	
	public FX_ModificarRol(DAO dao, Rol_VO r,
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

				//Si el rol tiene ID entonces, lo guardo dierectamente
				DAO_Perfil daoPerfil = getDao().getInstance(DAO_Perfil.class);
//				List<Perfil_VO> perfiles = getRol().getPerfiles();
//				getRol().setPerfiles(new ArrayList<Perfil_VO>());
				
				//Guardo los perfiles
				for (Perfil_VO per : getRol().getPerfiles()) {
					
					per.refreshValues();
					per.setIdRol(getRol().getId());
					
					if (per.getId()==null){
						//No esta persistida, La persisto
						FX_CrearPerfil fx_crearPerfil = new FX_CrearPerfil(daoPerfil, per, getUsuario());
						resp.setOk( fx_crearPerfil.ejecutar(adminAlertas).getOk() );
					
					}else{
						
						if (per.getBorrado()){
							//Esta persistida, la modifico
							FX_EliminarPerfil fx_eliminarPerfil = new FX_EliminarPerfil(daoPerfil, per, getUsuario());
							resp.setOk( fx_eliminarPerfil.ejecutar(adminAlertas).getOk() );
						}else{
							//Esta persistida, la modifico
							FX_ModificarPerfil fx_modificarPerfil = new FX_ModificarPerfil(daoPerfil, per, getUsuario());
							resp.setOk( fx_modificarPerfil.ejecutar(adminAlertas).getOk() );
						}
						
					}
					
					//Voy agregando los perfiles al rol
//					getRol().getPerfiles().add(per);
				}
				
				getDao().guardar(getRol());
				getDao().resetQuery();
				
				//Actualizo la lista de perfiles
				List<Perfil_VO> perfilesRefreshed = daoPerfil.getPerfilesDeRol(getRol().getId());
				getRol().setPerfiles(perfilesRefreshed);
				
				String detalle = "El rol " + getRol().getNombre()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getRol()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_CrearRol", "ejecutar",getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getRol()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {
				e.printStackTrace();
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

		List<Rol_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Rol_VO rol : elementos) {
				if (!rol.equals(getRol())) {
					ok = false;
				}
			}
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

	/**
	 * SI modifico un rol NO listo todos los roles al ejecutar la funcion
	 */
	@Override
	public Boolean listar() {
		return false;
	}
}
