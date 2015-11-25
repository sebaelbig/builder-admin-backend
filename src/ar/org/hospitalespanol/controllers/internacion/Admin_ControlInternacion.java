package ar.org.hospitalespanol.controllers.internacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.internacion.DAO_ControlInternacion;
import ar.org.hospitalespanol.dao.internacion.DAO_Pabellon;
import ar.org.hospitalespanol.dao.internacion.epicrisis.DAO_Epicrisis;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.model.internacion.Carpeta;
import ar.org.hospitalespanol.vo.internacion.Carpeta_VO;
import ar.org.hospitalespanol.vo.internacion.Pabellon_VO;
import ar.org.hospitalespanol.vo.internacion.epicrisis.Epicrisis_VO;

import com.google.gson.Gson;

@Controller
public class Admin_ControlInternacion extends Admin_Abstracto<Carpeta_VO> {

	@Autowired
	private Paginador<Carpeta_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_ControlInternacion dao_controlinter;

	@Autowired
	private DAO_Pabellon dao_pabellon;

	@Autowired
	private DAO_Epicrisis dao_epicrisis;

	public Admin_ControlInternacion() {

	}

	public String getListadoInernadosHoy(String usuariosAction) {
		List<Pabellon_VO> pabellones = this.getDao_pabellon().getPabellones();
		// System.out.print(pabellones);
		List<Carpeta_VO> internados = null;
		for (Pabellon_VO p : pabellones) {
			if (internados == null) {
				internados = this.getDao_controlinter().listadoDeInternadosHoy(
						p);
			} else {
				internados.addAll(this.getDao_controlinter()
						.listadoDeInternadosHoy(p));
			}
		}

		JSON_Paginador pag = new JSON_Paginador(internados);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);

		return new Gson().toJson(resp);
	}

	public String getDatosIntPaciente(String nroCarpeta) {
		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {
			Carpeta_VO pac_vo = this.getDao_controlinter().getDatosIntPaciente(
					nroCarpeta);
			// return getGson().toJson(pac_vo);
			List<Carpeta_VO> lista = new ArrayList();
			lista.add(pac_vo);
			JSON_Paginador pag = new JSON_Paginador();
			pag.setElementos(lista);
			pag.setHayPaginaAnterior(false);
			pag.setHayPaginaSiguiente(false);
			pag.setMensaje("Se obtubos los datos de la Carpeta  correctamente");
			pag.setPaginaActual(1);
			pag.setTotalPaginas(1);
			// pag.setCantPorPagina(pedidos.size());

			jsonResp.setPaginador(pag);
			jsonResp.setMensaje("Se obtubos los datos de Carpeta correctamente");
			jsonResp.setOk(true);
		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}

	public String getCapetasPorFiltro(String usuariosAction, Carpeta carpeta) {
		JSON_Respuesta resp = new JSON_Respuesta();
		try {
			List<Carpeta_VO> internados = null;
			internados = this.getDao_controlinter()
					.getCapetasPorFiltro(carpeta);

			/** Me fijo si esta guardada la epicrisis **/
			try {
				Epicrisis_VO epicrisis;
				for (Carpeta_VO carp : internados) {
					epicrisis = this.getDao_epicrisis().getDatosEpicrisis(
							Integer.toString(carp.getCarpeta()));
					if (epicrisis != null) {
						//System.out.print(epicrisis.getCerrado());
						carp.setEpicrisisGuardada(epicrisis.getCerrado());
					} else {
						carp.setEpicrisisGuardada(false);
					}
				}
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			/*****************/

			JSON_Paginador pag = new JSON_Paginador(internados);

			resp.setPaginador(pag);
			resp.setMensaje("Se obtubieron las carpetas correctamente");
			resp.setOk(true);
		} catch (Exception e) {

			resp.setOk(false);
			resp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(resp);
	}

	public String setCarpetaAltaMedica(String usuariosAction, Carpeta carpeta) {

		JSON_Respuesta jsonResp = new JSON_Respuesta();
		try {

			carpeta.setUsuarioAltaMedica(usuariosAction);
			this.dao_controlinter.setAltaMedica(carpeta);

			jsonResp.setMensaje("Se Actualizo la carpeta correctamente");
			jsonResp.setOk(true);

		} catch (Exception e) {

			jsonResp.setOk(false);
			jsonResp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		return new Gson().toJson(jsonResp);
	}
	
	public String getListadoInernadosPorProfesional(String matricula,
			String usuarioAccion) {
		
		List<Carpeta_VO> internados =  this.getDao_controlinter().listadoDeInternadosPorProfesional(matricula);
		

		JSON_Paginador pag = new JSON_Paginador(internados);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);

		return new Gson().toJson(resp);
	}

	public Paginador<Carpeta_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<Carpeta_VO> paginador) {
		this.paginador = paginador;
	}

	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	public DAO_ControlInternacion getDao_controlinter() {
		return dao_controlinter;
	}

	public void setDao_controlinter(DAO_ControlInternacion dao_controlinter) {
		this.dao_controlinter = dao_controlinter;
	}

	public DAO_Pabellon getDao_pabellon() {
		return dao_pabellon;
	}

	public void setDao_pabellon(DAO_Pabellon dao_pabellon) {
		this.dao_pabellon = dao_pabellon;
	}

	public DAO_Epicrisis getDao_epicrisis() {
		return dao_epicrisis;
	}

	public void setDao_epicrisis(DAO_Epicrisis dao_epicrisis) {
		this.dao_epicrisis = dao_epicrisis;
	}

	@Override
	protected DAO<Carpeta_VO> getDao() {
		// TODO Auto-generated method stub
		return this.dao_controlinter;
	}

	@Override
	protected I_FX getFX_Crear(Carpeta_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Carpeta_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Carpeta_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Carpeta_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}


}
