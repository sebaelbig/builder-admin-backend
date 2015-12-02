package ar.com.builderadmin.fx.historiaClinica.pedidos;

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
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Pedido;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.historiaClinica.pedidos.Pedido;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;

public class FX_AtenderPedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_AtenderPedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;

	public FX_AtenderPedido(DAO dao, Pedido_VO tipo, String usuario) {
		setDao((DAO_Pedido) dao);
		setUsuario(usuario);
		setPedido(tipo);
	}

	private boolean validar() {
		
		Pedido_VO ped = ((Pedido) getDao().findById(getPedido().getId())).toValueObject();
				
		return ped!=null && !ped.getBorrado();
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {

//				getPedido().setEstado(EstudioDePedido.ATENDIDO);
				
				getDao().guardar(getPedido());
				getDao().resetQuery();

				String detalle = "El Pedido " + getPedido().getNumero()
						+ " se atendió correctamente";

				// Se genera y persiste el alerta correspondiente a la
				// funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getPedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_AtenderPedido", "ejecutar",getUsuario(),
						detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getPedido()));
				this.getRespuesta().setMensaje(detalle);
					this.getRespuesta().setOk(true);
					
			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

				e.printStackTrace();

				this.getRespuesta()
						.setMensaje(
								"Error al guardar el pedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: "
										+ ""
										+ " )");
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje(
					"El pedido NO existe.");

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

		resp.put(this.getClass().getSimpleName(), "El Pedido "
				+ getPedido().getNumero() + " se atendió correctamente");

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
	public DAO_Pedido getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_Pedido dao) {
		this.dao = dao;
	}

	/**
	 * @return the pedido
	 */
	public Pedido_VO getPedido() {
		return pedido;
	}

	/**
	 * @param pedido
	 *            the pedido to set
	 */
	public void setPedido(Pedido_VO pedido) {
		this.pedido = pedido;
	}

	@Override
	public Boolean listar() {
		return true;
	}
	
}
