package ar.org.hospitalespanol.fx.core.usuarios.roles.profesionales;

import java.util.ArrayList;
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
import ar.org.hospitalespanol.dao.core.usuarios.DAO_TipoIDHE;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_Usuario;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.org.hospitalespanol.dao.core.usuarios.roles.profesionales.DAO_Profesional;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.usuarios.FX_CrearUsuario;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_CrearPerfil;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.TipoIDHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.Profesional_VO;

public class FX_CrearProfesional implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearProfesional.class);

	private DAO_Profesional dao;
	private EntityManager em;
	private Profesional_VO profesional;
	private String usuario;

	private JSON_Respuesta respuesta = new JSON_Respuesta();
	
	public FX_CrearProfesional(DAO dao, Profesional_VO r,
			String nombreUsuario) {
		setDao((DAO_Profesional) dao);
		setProfesional(r);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	public FX_CrearProfesional(DAO daoProfe,
			ProfesionalHE_VO profeActuanteHE, String usuario2) {

		setDao((DAO_Profesional) daoProfe);
		setUsuario(usuario2);
		setEm(getDao().getEntityManager());

		//Obtengo el tipo de ID
		DAO_TipoIDHE daoTipoID = getDao().getInstance(DAO_TipoIDHE.class);
		TipoIDHE_VO tipoId = daoTipoID.recuperarEntidadPorCodigo("MAT");
		profeActuanteHE.setTipoId(tipoId);
		
		setProfesional( new Profesional_VO(profeActuanteHE));

		//No tiene usuario
		getProfesional().setUsuario(new Usuario_VO());
		getProfesional().getUsuario().setApellido(profeActuanteHE.getApellido());
		
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearProfesional.ejecutar()");

			try {
				
				/**
				 * Me fijo si los perfiles NO estan en la BD
				 */
				JSON_Respuesta resp = new JSON_Respuesta();

				//Me fijo si el usuario existe, sino lo creo con los datos del LDAP
				if (getProfesional().getUsuario().getId()==null){
					
					// Me fijo si el usuario existe, sino lo creo con los datos
					DAO_Usuario daoUsuario = getDao()
							.getInstance(DAO_Usuario.class);
					
					// No existe en nuestra BD, lo creo
					resp = new FX_CrearUsuario(daoUsuario, getProfesional().getUsuario(), getUsuario()).ejecutar(adminAlertas);
				}

				
				//Se supone que no tiene tipo de rol asociado, asi que busco el rol MHE, tipo de rol del medico del HE
				DAO_TipoDeRol daoTipoDeRol = getDao().getInstance(DAO_TipoDeRol.class);
				TipoDeRol_VO tr = daoTipoDeRol.buscarTipoDeRolsPorNombreOCodigo("MHE").get(0);
				this.getProfesional().setTipoRol(tr);
				
				//Si el rol no tiene ID entonces, primero persisto el rol, para tener su ID
				DAO_Perfil daoPerfil = getDao().getInstance(DAO_Perfil.class);
				List<Perfil_VO> perfiles = getProfesional().getPerfiles();
				getProfesional().setPerfiles(new ArrayList<Perfil_VO>());
				
				getDao().guardar(getProfesional());
				getDao().resetQuery();
				
				//Una vez que tengo el ID del rol, se los asigno a los perfiles
				for (Perfil_VO per : perfiles) {
					
					//No esta persistida, La persisto
					per.refreshValues();
					per.setIdRol(getProfesional().getId());
					
					FX_CrearPerfil fx_crearPerfil = new FX_CrearPerfil(daoPerfil, per, getUsuario());
					resp.setOk( fx_crearPerfil.ejecutar(adminAlertas).getOk() );
					
					//Voy agregando los perfiles al rol
					getProfesional().getPerfiles().add(per);
					
				}
				
				String detalle = "El rol " + getProfesional().getNombre()
						+ " se creo correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getProfesional()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				DAO_Utils
						.info(logger, "FX_CrearProfesional", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getProfesional()));
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
					"Ya existe un profesional con ese numero de matricula");

		}

		return getRespuesta();

	}

	private boolean validar() {

		getDao().resetQuery();
		
		getDao().setQueryCondiciones(" WHERE "+getDao().getIdClass()+".nroMatriculaNacional = :nroMatNac " +
				" OR "+getDao().getIdClass()+".nroMatriculaProvincial = :nroMatProv ");
		
		getDao().getCondiciones().put("nroMatNac", getProfesional().getNroMatriculaNacional());
		getDao().getCondiciones().put("nroMatProv", getProfesional().getNroMatriculaProvincial());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0) {

			ok = false;

		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Profesional getDao() {
		return dao;
	}

	public void setDao(DAO_Profesional dao) {
		this.dao = dao;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
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
				+ getProfesional().getNombre() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	
	/**
	 * @return the profesional
	 */
	public Profesional_VO getProfesional() {
		return profesional;
	}

	/**
	 * @param profesional the profesional to set
	 */
	public void setProfesional(Profesional_VO profesional) {
		this.profesional = profesional;
	}

	@Override
	public Boolean listar() {
		return true;
	}
}
