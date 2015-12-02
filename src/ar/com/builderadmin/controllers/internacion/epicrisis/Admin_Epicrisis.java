package ar.com.builderadmin.controllers.internacion.epicrisis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.internacion.DAO_ControlInternacion;
import ar.com.builderadmin.dao.internacion.epicrisis.DAO_Epicrisis;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.utils.PdfUtils;
import ar.com.builderadmin.vo.internacion.Carpeta_VO;
import ar.com.builderadmin.vo.internacion.epicrisis.Epicrisis_VO;

@Controller
public class Admin_Epicrisis extends Admin_Abstracto<Epicrisis_VO> {

	@Autowired
	private DAO_Epicrisis daoEpicrisis;

	@Autowired
	private DAO_ControlInternacion daoInternacion;

	@Autowired
	private PdfUtils pdfUtils;

	@Autowired
	private Paginador<Epicrisis_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	private JSON_Respuesta json_respuesta;

	public Admin_Epicrisis() {

	}

	public DAO_Epicrisis getDaoEpicrisis() {
		return daoEpicrisis;
	}

	public void setDaoEpicrisis(DAO_Epicrisis daoEpicrisis) {
		this.daoEpicrisis = daoEpicrisis;
	}

	public String guardarEpicrisis(Epicrisis_VO epicrisis, String usuarioAction) {

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {

			epicrisis.setUsuario(usuarioAction);
			Date hoy = new Date();
			epicrisis.setFechaModificacion(new SimpleDateFormat("dd/MM/yyyy")
					.format(hoy));
			this.getDaoEpicrisis().guardar(epicrisis);

			jsonResp.setMensaje("Se Guardo Epicrisis correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	public String getDatosEpicrisis(String idCarpeta, String usuario) {
		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			Epicrisis_VO epicrisis = this.daoEpicrisis
					.getDatosEpicrisis(idCarpeta);
			// return getGson().toJson(epicrisis);
			List<Epicrisis_VO> lista = new ArrayList();
			if (epicrisis != null) {
				lista.add(epicrisis);
				JSON_Paginador pag = new JSON_Paginador();
				pag.setElementos(lista);
				pag.setHayPaginaAnterior(false);
				pag.setHayPaginaSiguiente(false);
				pag.setMensaje("Se obtubos los datos de Epicrisis  correctamente");
				pag.setPaginaActual(1);
				pag.setTotalPaginas(1);
				// pag.setCantPorPagina(pedidos.size());

				jsonResp.setPaginador(pag);
				jsonResp.setMensaje("Se obtubos los datos de Epicrisis correctamente");
				jsonResp.setOk(true);
			} else {
				jsonResp.setOk(false);
				jsonResp.setMensaje("No esta cargada la Epicrisis en la HCE ");

			}
		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	/**
	 * 
	 */
	public String getEpicrisisCerrada(String idCarpeta, String usuario) {
		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			Epicrisis_VO epicrisis = this.daoEpicrisis
					.getDatosEpicrisis(idCarpeta);
			Carpeta_VO carpeta = daoInternacion.getDatosIntPaciente(idCarpeta);

			/** Fecha de hoy **/
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -3);
			/* tiene la fecha de hoy -3 dias */
			Date fechafinal = cal.getTime();
			/****/

			// return getGson().toJson(epicrisis);
			List<Epicrisis_VO> lista = new ArrayList();
			if (epicrisis != null) {
				/* Verificar que este cerrada o hayan pasado 72hs */
				if ((epicrisis.getCerrado() == true)
						|| (fechafinal.after(carpeta.getDt_fechaEgreso()))) {
					lista.add(epicrisis);
					JSON_Paginador pag = new JSON_Paginador();
					pag.setElementos(lista);
					pag.setHayPaginaAnterior(false);
					pag.setHayPaginaSiguiente(false);
					pag.setMensaje("Se obtubos los datos de Epicrisis  correctamente");
					pag.setPaginaActual(1);
					pag.setTotalPaginas(1);
					// pag.setCantPorPagina(pedidos.size());

					jsonResp.setPaginador(pag);
					jsonResp.setMensaje("Se obtubos los datos de Epicrisis correctamente");
					jsonResp.setOk(true);
				} else {
					jsonResp.setOk(false);
					jsonResp.setMensaje("La Epicrisis no esta cerrada");
				}
			} else {
				jsonResp.setOk(false);
				jsonResp.setMensaje("No esta cargada la Epicrisis en la HCE ");

			}
		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	/** Cerrar **/
	public String cerrarEpicrisis(String idCarpeta, String usuarioAction) {

		Epicrisis_VO epicrisis = this.getDaoEpicrisis().getDatosEpicrisis(
				idCarpeta);

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		if (epicrisis != null) {
			epicrisis.setCerrado(true);
			epicrisis.setUsuario(usuarioAction);
			Date hoy = new Date();
			epicrisis.setFechaModificacion(new SimpleDateFormat("dd/MM/yyyy")
					.format(hoy));
			try {
				epicrisis.setCerrado(true);
				this.getDaoEpicrisis().guardar(epicrisis);

				jsonResp.setMensaje("Se Cerro Epicrisis correctamente");
				jsonResp.setOk(true);

			} catch (Exception e) {

				jsonResp.setOk(false);
				jsonResp.setMensaje("ERROR al listar todos los: "
						+ this.getClass().getSimpleName());
			}
		} else {
			jsonResp.setOk(false);
			jsonResp.setMensaje("La epicrisis no esta cargada en la HCE, primero debe GUARDAR ");
		}
		return new Gson().toJson(jsonResp);
	}

	public byte[] imprimir(Long idCarpeta, Long idEpicrisis,
			String usuarioAccion) {

		Epicrisis_VO epicrisis = this.findEpicrisis(idEpicrisis);
		Carpeta_VO carpeta = daoInternacion.getDatosIntPaciente(idCarpeta
				.toString());

		// Creo un archivo
		File archivoPDF = crearPDFDeEpicrisis(epicrisis, carpeta);

		if (archivoPDF != null) {

			// Transformo el archivo a bytes
			try {
				return Files.readAllBytes(archivoPDF.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return null;
		}
	}

	private File crearPDFDeEpicrisis(Epicrisis_VO epicrisis, Carpeta_VO carpeta) {

		Map<String, Object> data = new HashMap<>();
		data.put("carpeta", carpeta);
		data.put("epicrisis", epicrisis);

		// Creo el PDF
		return pdfUtils.htmlFileToPdf("/internacion/epicrisis.pdf", data);
	}

	/**
	 * Recupera el pedido
	 * 
	 * @param idPedido
	 * @param idEstudio
	 * @return
	 */
	private Epicrisis_VO findEpicrisis(Long idEpicrisis) {
		// Recupero el pedido, sino tiene firma, la intento recuperar
		Epicrisis_VO epicrisis = (Epicrisis_VO) DAO_Utils
				.entityToValueObject(getDaoEpicrisis().findById(idEpicrisis));

		return epicrisis;
	}

	public byte[] getHCDigital(String idCarpeta, String usuarioAccion) {
		String filesep = System.getProperty("file.separator");
		Path pathArchivo=Paths.get( filesep +"home"+filesep+"HCDigital"+filesep + idCarpeta + ".pdf");
		System.out.println("Se Busca el archivo " + pathArchivo);
		byte[] array = null;
		try {
			array = Files.readAllBytes(pathArchivo);
		} catch (IOException e) {
			//e.printStackTrace();
			try {
				System.out.println("No se encontro el archivo");
				pathArchivo=Paths.get(filesep +"home"+filesep  + "noDisponible.pdf");
				System.out.println("Se Busca el archivo " + pathArchivo);
				array = Files.readAllBytes(pathArchivo);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return array;

	}

	public void setDao(DAO_Epicrisis dao) {
		this.daoEpicrisis = dao;
	}

	@Override
	protected DAO_Epicrisis getDao() {
		return daoEpicrisis;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public void setPaginador(Paginador<Epicrisis_VO> paginador) {
		this.paginador = paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Crear(Epicrisis_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Epicrisis_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Epicrisis_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Epicrisis_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

	public PdfUtils getPdfUtils() {
		return pdfUtils;
	}

	public void setPdfUtils(PdfUtils pdfUtils) {
		this.pdfUtils = pdfUtils;
	}

	public DAO_ControlInternacion getDaoInternacion() {
		return daoInternacion;
	}

	public void setDaoInternacion(DAO_ControlInternacion daoInternacion) {
		this.daoInternacion = daoInternacion;
	}

	@Override
	protected Paginador<Epicrisis_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

}
