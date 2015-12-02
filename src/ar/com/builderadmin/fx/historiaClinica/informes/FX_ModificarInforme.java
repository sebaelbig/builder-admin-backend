package ar.com.builderadmin.fx.historiaClinica.informes;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.historiaClinica.DAO_Informes;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.informes.Informe_VO;

public class FX_ModificarInforme implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ModificarInforme.class);

	private DAO_Informes dao;
	private EntityManager em;
	private Informe_VO informe;
	private String usuario;

	public FX_ModificarInforme(DAO<Informe_VO> dao, Informe_VO area,
			String nombreUsuario) {
		setDao((DAO_Informes) dao);
		setInforme(area);
		setUsuario(nombreUsuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			this.logger.debug("executing FX_ModificarInforme.ejecutar()");

			try {

				getDao().guardar(getInforme());
				getDao().resetQuery();

				String detalle = "El informe "
						+ getInforme().getNumero()
						+ " se modificó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(), getInforme()
						.getId(), this.getClass().getCanonicalName(), detalle,
						E_Priority.BAJA);

				adminAlertas.guardarAlerta(getEm(), al, this);

				System.out.println(detalle);
				this.logger.info(detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getInforme()));
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
					"Ya existe un informe de servicio con el mismo nombre: "
							+ getInforme());

		}
		return getRespuesta();
	}

	private boolean validar() {

		getDao().setQueryCondiciones(
				" WHERE (lower(" + getDao().getIdClass()
						+ ".numero) = :nombre ) ");

		getDao().getCondiciones().put("nombre",
				getInforme().getNumero().toLowerCase());

		boolean ok = true;

		List<Informe_VO> elementos = getDao().listarTodo();

		if (elementos.size() > 0) {
			for (Informe_VO templ : elementos) {
				if (!templ.equals(getInforme())) {
					ok = false;
				}
			}
		}

		getDao().resetQuery();

		return ok;
	}

	public DAO_Informes getDao() {
		return dao;
	}

	public void setDao(DAO_Informes dao) {
		this.dao = dao;
	}

	public Informe_VO getInforme() {
		return informe;
	}

	public void setInforme(Informe_VO area) {
		this.informe = area;
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
		return new java.util.HashMap<String, Object>();
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

}
