package ar.org.hospitalespanol.fx.historiaClinica.pedidos;

import java.util.Date;
import java.util.HashMap;
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
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_CancelarEstudioDePedido;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.Pedido;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_CancelarPedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CancelarPedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;

	public FX_CancelarPedido(DAO dao, Pedido_VO tipo, String usuario) {
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

			String motivo = getPedido().getMotivo();
			
			try {

				DAO_EstudioDePedido daoEst = this.getDao().getInstance(DAO_EstudioDePedido.class);
				FX_CancelarEstudioDePedido fx_estudio = null;
				
				Boolean finalizarPedido = !getPedido().getUnEstudioPorPedido();
				
				String detalle = "";
				
				if (getPedido().getUnEstudioPorPedido()){
//					Si el pedido es de un servicio que es de un estudio por informe, 
//					entonces solo marca el estudio
					
					getPedido().getEstudiosPaciente().get(0).setMotivo(motivo);
					fx_estudio = new FX_CancelarEstudioDePedido(daoEst, getPedido().getEstudiosPaciente().get(0), getUsuario());
					fx_estudio.ejecutar(adminAlertas);
				
					detalle = fx_estudio.getRespuesta().getMensaje();
					
//					Una vez que cambio el estudio, me fijo si ya todos los estudios de este pedido fueron confirmados
//					cancelarPedido = this.getDao().chequearTodosEstudiosConEstado(getPedido(), EstudioDePedido.CANCELADO);
					finalizarPedido = this.getDao().chequearParaFinalizar(getPedido());
					
				}else{
					
//					Si el pedido es de un servicio que es un informe por TODOS los estudios, 
//					marco todos los estudios 
					
					for (EstudioDePedido_VO ep : getPedido().getEstudiosPaciente()) {
						
						ep.setMotivo(motivo);
						
						fx_estudio = new FX_CancelarEstudioDePedido(daoEst, ep, getUsuario());
						fx_estudio.ejecutar(adminAlertas).getOk();
						
					}
					
				}
				
				if (finalizarPedido){
					
					setPedido(getDao().refreshEstudios(getPedido()));
					
					if (getPedido().chequearParaCancelarPedido()){
						getPedido().setEstado(EstudioDePedido.CANCELADO);
						getDao().setEstadoPedido(getPedido().getNumero(), getUsuario(), "CAN", motivo);
					}else{
						getPedido().setEstado(EstudioDePedido.INFORMADO);
						getDao().setEstadoPedido(getPedido().getNumero(), getUsuario(), "INF", "motivo");
					}
					
					getDao().guardar(getPedido());
					getDao().resetQuery();
					
					detalle = "El Pedido " + getPedido().getNumero()
							+ " se canceló correctamente";
					
				}


				// Se genera y persiste el alerta correspondiente a la
				// funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getPedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_CancelarPedido", "ejecutar",getUsuario(),
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
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El Pedido "
				+ getPedido().getNumero() + " se canceló correctamente");

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
