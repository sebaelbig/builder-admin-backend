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
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_ConfirmarEstudioDePedido;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.Pedido;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.org.hospitalespanol.utils.Mailer;
import ar.org.hospitalespanol.utils.PdfUtils;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_ConfirmarPedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ConfirmarPedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;

	private PdfUtils pdfUtils;
	private Mailer mailer;
	
	public FX_ConfirmarPedido(DAO dao, Pedido_VO tipo, String usuario, PdfUtils pdfUtil, Mailer mailer2) {
		setDao((DAO_Pedido) dao);
		setUsuario(usuario);
		setPedido(tipo);
		
		setPdfUtils(pdfUtil);
		setMailer(mailer2);
	}

	private boolean validar() {
		
		Pedido_VO ped = ((Pedido) getDao().findById(getPedido().getId())).toValueObject();
				
		return ped!=null && !ped.getBorrado();
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {
			
			try {
				DAO_EstudioDePedido daoEst = this.getDao().getInstance(DAO_EstudioDePedido.class);
				FX_ConfirmarEstudioDePedido fx_confirmEstudio = null;
				
				Boolean finalizarPedido = !getPedido().getUnEstudioPorPedido();
				String detalle="";
				
				if (getPedido().getUnEstudioPorPedido()){
//					Si el pedido es de un servicio que es de un estudio por informe, 
//					entonces solo marca informado el estudio
					DAO_Utils.info(logger, "FX_ConfirmarPedido", "ejecutar", getUsuario(), "Itero sobre los estudios marcandolos como confirmados");
					fx_confirmEstudio = new FX_ConfirmarEstudioDePedido(daoEst, getPedido().getEstudiosPaciente().get(0), getUsuario(), getPdfUtils(), getMailer());
					fx_confirmEstudio.ejecutar(adminAlertas);
					
					detalle = fx_confirmEstudio.getRespuesta().getMensaje();
					
//					Una vez que cambio el estudio, me fijo si ya todos los estudios de este pedido fueron confirmados
//					confirmarPedido = this.getDao().chequearTodosEstudiosConEstado(getPedido(), EstudioDePedido.INFORMADO);
					DAO_Utils.info(logger, "FX_ConfirmarPedido", "ejecutar", getUsuario(), "Una vez que cambio el estudio, me fijo si ya todos los estudios de este pedido fueron confirmados.");
					finalizarPedido = this.getDao().chequearParaFinalizar(getPedido());
					
				}else{
					
//					Si el pedido es de un servicio que es un informe por TODOS los estudios, 
//					marco todos los estudios como informados
					DAO_Utils.info(logger, "FX_ConfirmarPedido", "ejecutar", getUsuario(), "Itero sobre los estudios marcandolos como confirmados");
					for (EstudioDePedido_VO ep : getPedido().getEstudiosPaciente()) {
						
						fx_confirmEstudio = new FX_ConfirmarEstudioDePedido(daoEst, ep, getUsuario(), getPdfUtils(), getMailer());
						fx_confirmEstudio.ejecutar(adminAlertas).getOk();
						
					}
					
				}

				if (finalizarPedido){
					//Ya estan todos los estudios finalizados, y si estamos confirmando al menos uno, 
					//Ya sabemos que es condicion para informar el pedido entero
					DAO_Utils.info(logger, "FX_ConfirmarPedido", "ejecutar", getUsuario(), "Guardo el estudio.");
					setPedido(getDao().refreshEstudios(getPedido()));
					getPedido().setEstado(EstudioDePedido.INFORMADO);
					
					getDao().guardar(getPedido());
					getDao().resetQuery();
					
					//Indico al HE el cambio de estado
					getDao().setEstadoPedido(getPedido().getNumero(), getUsuario(), "INF","Todos los estudios del pedido están en estado final y al menos uno está informado.");
					
					detalle = "El Pedido " + getPedido().getNumero()
							+ " se confirmó correctamente";
				}
				

				// Se genera y persiste el alerta correspondiente a la
				// funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getPedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_ConfirmarPedido", "ejecutar",getUsuario(),
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
				+ getPedido().getNumero() + " se confirmó correctamente");

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

	public PdfUtils getPdfUtils() {
		return pdfUtils;
	}

	public void setPdfUtils(PdfUtils pdfUtils) {
		this.pdfUtils = pdfUtils;
	}

	public Mailer getMailer() {
		return mailer;
	}

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	
}
