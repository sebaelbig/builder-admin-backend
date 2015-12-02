package ar.com.builderadmin.fx.core.usuarios.roles.pacientes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.usuarios.DAO_Usuario;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.com.builderadmin.dao.core.usuarios.roles.pacientes.DAO_Paciente;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.FX_CrearUsuario;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.Paciente_VO;

public class FX_CrearPaciente implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearPaciente.class);

	private DAO_Paciente dao;
	private EntityManager em;
	private Paciente_VO paciente;
	private String usuario;

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public FX_CrearPaciente(DAO dao, Paciente_VO r, String nombreUsuario) {
		setDao((DAO_Paciente) dao);
		setPaciente(r);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_CrearPaciente.ejecutar()");

			try {

				/**
				 * Me fijo si los perfiles NO estan en la BD
				 */
				JSON_Respuesta resp = new JSON_Respuesta();
				// Me fijo si el usuario existe, sino lo creo con los datos
				DAO_Usuario daoUsuario = getDao().getInstance(DAO_Usuario.class);
				
				if (getPaciente().getUsuario() == null || 
					getPaciente().getUsuario().getId() == null) {

					// No existe en nuestra BD, lo creo
					resp = new FX_CrearUsuario(daoUsuario, getPaciente().getUsuario(), getUsuario()).ejecutar(adminAlertas);

					if (!resp.getOk()){
						//Si no lo pudo crear entonces el Usuario existe
						getPaciente().setUsuario( daoUsuario.buscarUsuarioPorDocumento(getPaciente().getUsuario().getTipoDocumento(), getPaciente().getUsuario().getNroDocumento()));
					}
					
				}else{
					getPaciente().setUsuario(daoUsuario.buscarUsuarioPorDocumento(getPaciente().getUsuario().getTipoDocumento(), getPaciente().getUsuario().getNroDocumento()));
				}

				// Se supone que no tiene tipo de rol asociado, asi que busco el
				// rol MHE, tipo de rol del medico del HE
				DAO_TipoDeRol daoTipoDeRol = getDao().getInstance(
						DAO_TipoDeRol.class);
				
				TipoDeRol_VO tr = null;
				try {
					
					tr = daoTipoDeRol.buscarTipoDeRolsPorNombreOCodigo("PAC").get(0);
					
					this.getPaciente().setTipoRol(tr);

					getDao().guardar(getPaciente());
					getDao().resetQuery();

					String detalle = "El paciente " + getPaciente().getNombre()
							+ " se creó correctamente";
					resp.setMensaje(detalle);
					resp.setOk(true);

					// Se genera y persiste el alerta correspondiente a la funcion
					// FX
					Alerta al = new Alerta(getUsuario(), new Date(), getPaciente()
							.getId(), this.getClass().getCanonicalName(), detalle,
							E_Priority.BAJA);

					adminAlertas.guardarAlerta(getEm(), al, this);

					DAO_Utils.info(logger, "FX_CrearPaciente", "ejecutar", getUsuario(), detalle);

					this.getRespuesta().setPaginador(
							JSON_Paginador.solo(getPaciente()));
					this.getRespuesta().setMensaje(detalle);
					this.getRespuesta().setOk(true);
					
				}catch(NullPointerException e){
					this.getRespuesta().setOk(false);
					this.getRespuesta().setMensaje("El Tipo de rol Paciente NO existe");
				}

			} catch (Exception e) {
				this.getRespuesta().setOk(false);
				e.printStackTrace();
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");
			}

		} else {
			this.getRespuesta().setOk(false);

			this.getRespuesta().setMensaje(
					"Ya existe un paciente con ese numero de matricula");

		}

		return getRespuesta();

	}

	private boolean validar() {

		Paciente_VO pac = getDao().recuperarEntidad(
				getPaciente().getUsuario().getTipoDocumento(),
				getPaciente().getUsuario().getNroDocumento());

		return pac == null;
	}

	public DAO_Paciente getDao() {
		return dao;
	}

	public void setDao(DAO_Paciente dao) {
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
				+ getPaciente().getNombre() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the paciente
	 */
	public Paciente_VO getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(Paciente_VO paciente) {
		this.paciente = paciente;
	}

	@Override
	public Boolean listar() {
		return true;
	}
}
