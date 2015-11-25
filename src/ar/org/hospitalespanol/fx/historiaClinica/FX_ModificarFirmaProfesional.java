package ar.org.hospitalespanol.fx.historiaClinica;

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
import ar.org.hospitalespanol.dao.historiaClinica.DAO_Firma;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.FirmaProfesional_VO;

public class FX_ModificarFirmaProfesional implements I_FX {
	
	@Override
	public Boolean listar() {
		return false;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarFirmaProfesional.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Firma dao;
	private EntityManager em;
	private FirmaProfesional_VO firma;
	private String usuario;

	public FX_ModificarFirmaProfesional(DAO dao, FirmaProfesional_VO tipo, String usuario) {
		setDao((DAO_Firma) dao);
		setUsuario(usuario);
		setFirma(tipo);
		setEm(getDao().getEntityManager());
	}

	private boolean validar() {
		
		getDao().resetQuery();
		getDao().setQueryCondiciones(
				" WHERE " + getDao().getIdClass()
						+ ".nroMatricula = :idUsr ");
		
		if (getFirma().getNroMatricula()!=null)
			getDao().getCondiciones().put("idUsr",
					getFirma().getNroMatricula());

		boolean ok = true;

		List<FirmaProfesional_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (FirmaProfesional_VO firma : elementos) {
				if (!firma.equals(getFirma())) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {

				getDao().guardar(getFirma());
				getDao().resetQuery();

				String detalle = "La firma del profesional  " + getFirma().getNroMatricula()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getFirma()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_ModificarFirmaProfesional", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getFirma()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);
				
				DAO_Utils.info(logger, "FX_ModificarFirmaProfesional", "ejecutar", getUsuario(),
						"Avisando a IDEAR el cambio de la firma");
				
				//Se manda la firma a HE
				getDao().guardar(getEm(), getFirma());

			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

				e.printStackTrace();
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje("Debe ingresar un usuario que ya existe.");

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

		resp.put(this.getClass().getSimpleName(), "La firma del profesional  "
				+ getFirma().getNroMatricula() + " se modificó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the dao
	 */
	public DAO_Firma getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_Firma dao) {
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

	/**
	 * @return the firma
	 */
	public FirmaProfesional_VO getFirma() {
		return firma;
	}

	/**
	 * @param firma the firma to set
	 */
	public void setFirma(FirmaProfesional_VO firma) {
		this.firma = firma;
	}


	
}
