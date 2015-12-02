package ar.com.builderadmin.fx.historiaClinica.pedidos.estudios;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.templates.DAO_Templates;
import ar.com.builderadmin.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.com.builderadmin.utils.Mailer;
import ar.com.builderadmin.utils.PdfUtils;
import ar.com.builderadmin.vo.core.templates.PropiedadTemplate_VO;
import ar.com.builderadmin.vo.core.templates.Template_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;
import ar.com.builderadmin.ws.RespuestaCorta;

public class FX_ConfirmarEstudioDePedido implements I_FX {

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ConfirmarEstudioDePedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_EstudioDePedido dao;
	private EstudioDePedido_VO estudioDePedido;
	private String usuario;

	private PdfUtils pdfUtils;
	private Mailer mailer;
	
	public FX_ConfirmarEstudioDePedido(DAO dao, EstudioDePedido_VO tipo, String usuario, PdfUtils pdfUtils2, Mailer mailer2) {
		setDao((DAO_EstudioDePedido) dao);
		setUsuario(usuario);
		setEstudioDePedido(tipo);
		
		setPdfUtils(pdfUtils2);
		setMailer(mailer2);
	}

	private boolean validar() {
		return getDao().existePedido(getEstudioDePedido().getId());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {
				
				//Refresco porque me daba OptimistckLockException
				EstudioDePedido ep = (EstudioDePedido)getDao().findById(getEstudioDePedido().getId());
				setEstudioDePedido(ep.toValueObject());
				
				getEstudioDePedido().setEstado(EstudioDePedido.INFORMADO);
				
				getDao().guardar(getEstudioDePedido());
				getDao().resetQuery();

				//Indica al HE que se confirmo el informe
				DAO_Utils.info(logger, "FX_ConfirmarEstudioDePedido", "ejecutar", getUsuario(), "Indica al HE que se confirmo el informe");
				RespuestaCorta resp = getDao().informarEstudio(getEstudioDePedido(), getUsuario());
				
				String detalle = "El estudio de pedido " + getEstudioDePedido().getPedido().getNumero()
						+ " se confirmó correctamente";

				/********************************************/
				/*             Envio el mail                */
				/********************************************/
				//Suspendido
//				try{
//					
//					File archivoPDF = crearPDFDeEstudio(getEstudioDePedido().getPedido());
//					
////				Envio el mail
//					Mail_VO mail = new Mail_VO();
//					mail.setAsunto("Hospital Español – Informe de Estudio");
//					mail.getData().put("pedido", getEstudioDePedido().getPedido());
//					mail.setEmailCandidato(getEstudioDePedido().getPedido().getMailSolicitante());
//					
//					Map<String, File> attachs = new HashMap<>();
//					attachs.put("Informe_de_"+getEstudioDePedido().getPedido().getPaciente()+".pdf", archivoPDF);
//					getMailer().enviarMail("/historiaClinica/pedidos/mail_informeConfirmado", mail, attachs);
//					
//				}catch(Exception e){
//					DAO_Utils.info(logger, "FX_ConfirmarEstudioDePedido", "ejecutar", "Informe_de_"+getEstudioDePedido().getPedido().getPaciente()+".pdf --> Enviado");
//				}
				
				// Se genera y persiste el alerta correspondiente a la
				// funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getEstudioDePedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_ConfirmarEstudioDePedido", "ejecutar",getUsuario(),
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
								"Error al guardar el estudioDePedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: "
										+ ""
										+ " )");
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje(
					"El estudio de pedido NO existe.");

		}

		return getRespuesta();
	}

	/**
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public File crearPDFDeEstudio(Pedido_VO pedido) {
		
		//Obtengo el template del servicio del pedido
		Template_VO template = this.getTemplateDeServicio(pedido.getNombreServicio());
		
		//Armo la lista de items a imprimir
		List<ItemLista> listaAImprimir = new ArrayList<>();
		ItemLista item = null;
		for ( PropiedadTemplate_VO propTemp : template.getPropiedades()) {
			
			//Calculo es estilo
			String estilo = "width:"+ propTemp.getAncho()+"%";
			
			//Obtengo el valor segun la propiedad que haya seleccionado 
			String metodoGet = propTemp.getPropiedad().getAtributo();
			metodoGet = metodoGet.substring(0, 1).toUpperCase() + metodoGet.substring(1);
			Method meotodoContenido = DAO_Utils.getMetodo(pedido, "get"+metodoGet);
			
			//Invoco al metodo get del estudio de la propiedad que figure en el template
			String conenido = null;
			try {
				conenido = (String) meotodoContenido.invoke(pedido);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			
			//Obtengo el atributo que figura en el template
			item = new ItemLista(estilo, propTemp.getContenido(), conenido);
			
			listaAImprimir.add(item); 
		}
		
		//Creo el mail
		Map<String, Object> data = new HashMap<>();
		data.put("listaAImprimir", listaAImprimir);
		data.put("elemento", pedido);

//		Creo el PDF
		return pdfUtils.htmlFileToPdf("/historiaClinica/pedidos/pedido_mailSolicitante.pdf", data);
	}

	private Template_VO getTemplateDeServicio(String nombreServicio) {
		
		DAO_Templates daoEst = this.getDao().getInstance(DAO_Templates.class);
		
		List<Template_VO> templs = daoEst.buscarPorNombre(getDao().getEntityManager(), nombreServicio);
		
		return templs.get(0); //Solo hay un template por servicio
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
				+ getEstudioDePedido().toString() + " se confirmó correctamente");

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
