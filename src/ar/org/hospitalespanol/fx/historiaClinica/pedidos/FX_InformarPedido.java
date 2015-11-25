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
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_InformarEstudioDePedido;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_InformarPedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_InformarPedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;
	private Long idEstudio;

	public FX_InformarPedido(DAO dao, Pedido_VO tipo, String usuario) {
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
				DAO_Utils.info(logger, "FX_InformarPedido", "ejecutar", getUsuario(), "Se recuperan los estudios del pedido nro:"+getPedido().getNumero());
				
				DAO_EstudioDePedido daoEst = this.getDao().getInstance(DAO_EstudioDePedido.class);
				List<EstudioDePedido_VO> estudiosDelPedido = daoEst.getEstudiosDePedido(getPedido(), getUsuario());
				
//				Debo reabrir todos los estudios del pedido
//				Marco como informado todos los estudios
				if (getPedido().getUnEstudioPorPedido() 
						&& this.getIdEstudio()!=null)
				{
					//Debo informar SOLO un estudio, el que viene en el pedido
					DAO_Utils.info(logger, "FX_InformarPedido", "ejecutar", getUsuario(), "Debo informar SOLO un estudio, el que viene en el pedido");
//					for (EstudioDePedido_VO ep : estudiosDelPedido) {
					EstudioDePedido_VO ep = getPedido().getEstudiosPaciente().get(0);
					
					if (ep.getId().equals(this.getIdEstudio())){
						//Le pongo el texto del pedido a todos los estudios, 
						//si el pedido es de un estudio por servicio me fijo si me especificaron el estuduio
						ep.setTexto(getPedido().getTexto());
						
						FX_InformarEstudioDePedido fx_informarEstudio = new FX_InformarEstudioDePedido(daoEst, ep, getUsuario());
						fx_informarEstudio.ejecutar(adminAlertas);
						
						getPedido().getEstudiosPaciente().set(0, fx_informarEstudio.getEstudioDePedido());
//							break;
					}
//					}
					
				}else{
					
					DAO_Utils.info(logger, "FX_InformarPedido", "ejecutar", getUsuario(), "Itero sobre los estudios marcandolos como informados");
					//Informo a todos los estudios por igual
					for (EstudioDePedido_VO ep : estudiosDelPedido) {
						ep.setTexto(getPedido().getTexto());
						
						FX_InformarEstudioDePedido fx_informarEstudio = new FX_InformarEstudioDePedido(daoEst, ep, getUsuario());
						fx_informarEstudio.ejecutar(adminAlertas);
					}
					
					getPedido().setEstudiosPaciente(estudiosDelPedido);
				}
						
				
				getDao().guardar(getPedido());
				getDao().resetQuery();

				String detalle = "El Pedido " + getPedido().getNumero()
						+ " se informó correctamente";

				// Se genera y persiste el alerta correspondiente a la
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

	/**
	 * @return the idEstudio
	 */
	public Long getIdEstudio() {
		return idEstudio;
	}

	/**
	 * @param idEstudio the idEstudio to set
	 */
	public void setIdEstudio(Long idEstudio) {
		this.idEstudio = idEstudio;
	}
	
}