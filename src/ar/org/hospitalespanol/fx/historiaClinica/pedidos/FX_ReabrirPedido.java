package ar.org.hospitalespanol.fx.historiaClinica.pedidos;

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
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.DAO_Pedido;
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_ReabrirEstudioDePedido;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.Pedido;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_ReabrirPedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ReabrirPedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;

	public FX_ReabrirPedido(DAO dao, Pedido_VO tipo, String usuario) {
		setDao((DAO_Pedido) dao);
		setUsuario(usuario);
		setPedido(tipo);
	}

	private boolean validar() {
		return getDao().existePedido(getPedido().getId());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {
				
				DAO_EstudioDePedido daoEst = this.getDao().getInstance(DAO_EstudioDePedido.class);
				
				List<EstudioDePedido_VO> estudiosDelPedido = daoEst.getEstudiosDePedido(getPedido(), getUsuario());
				
//				Debo reabrir todos los estudios del pedido
//				Marco como informado todos los estudios
				for (EstudioDePedido_VO ep : estudiosDelPedido) {
					FX_ReabrirEstudioDePedido fx_reabrirEstudio = new FX_ReabrirEstudioDePedido(daoEst, ep, getUsuario());
					fx_reabrirEstudio.ejecutar(adminAlertas);
				}
				
				//Cuando se gravo el estudio, se modifico el pedido, hay que refrescarlo
				Pedido p = (Pedido) getDao().findById(getPedido().getId());
				setPedido(p.toValueObject());
				getPedido().setEstado(Pedido.EN_ATENCION);
				getDao().guardar(getPedido());
				getDao().resetQuery();

				String detalle = "El Pedido " + getPedido().getNumero()
						+ " se reabrió correctamente";

				// Se genera y persiste el alerta correspondiente a la
				// funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getPedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_InformarPedido", "ejecutar",getUsuario(),
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
				+ getPedido().getNumero() + " se informó correctamente");

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
		return false;
	}
	
}
