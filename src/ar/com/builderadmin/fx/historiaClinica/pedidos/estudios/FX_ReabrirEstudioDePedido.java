package ar.com.builderadmin.fx.historiaClinica.pedidos.estudios;

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
import ar.com.builderadmin.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_ReabrirEstudioDePedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ReabrirEstudioDePedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_EstudioDePedido dao;
	private EstudioDePedido_VO estudioDePedido;
	private String usuario;

	public FX_ReabrirEstudioDePedido(DAO dao, EstudioDePedido_VO tipo, String usuario) {
		setDao((DAO_EstudioDePedido) dao);
		setUsuario(usuario);
		setEstudioDePedido(tipo);
	}

	private boolean validar() {
		return getDao().existePedido(getEstudioDePedido().getId());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {
				
				getEstudioDePedido().setEstado(EstudioDePedido.EN_ATENCION);
				
				getDao().guardar(getEstudioDePedido());
				getDao().resetQuery();

				String detalle = "El estudio de pedido " + getEstudioDePedido().getPedido().getNumero()
						+ " se reabrió correctamente";

				// Se genera y persiste el alerta correspondiente a la
				// funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getEstudioDePedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_AtenderEstudioDePedido", "ejecutar",getUsuario(),
						detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getEstudioDePedido()));
				this.getRespuesta().setMensaje(detalle);
					this.getRespuesta().setOk(true);
					
			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

				e.printStackTrace();

				this.getRespuesta()
						.setMensaje(
								"Error al guardar el estudioDePedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248)");
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje(
					"El estudioDePedido NO existe.");

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

		resp.put(this.getClass().getSimpleName(), "El EstudioDePedido "
				+ getEstudioDePedido().toString() + " se atendió correctamente");

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
	public DAO_EstudioDePedido getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_EstudioDePedido dao) {
		this.dao = dao;
	}

	/**
	 * @return the estudioDePedido
	 */
	public EstudioDePedido_VO getEstudioDePedido() {
		return estudioDePedido;
	}

	/**
	 * @param estudioDePedido
	 *            the estudioDePedido to set
	 */
	public void setEstudioDePedido(EstudioDePedido_VO estudioDePedido) {
		this.estudioDePedido = estudioDePedido;
	}

	@Override
	public Boolean listar() {
		return false;
	}
	
}
