package ar.com.builderadmin.controllers.cirugia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.cirugias.DAO_CirugiaProgramada;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.ws.respuestas.cirugias.CirugiaProgramadaSimpleAdapter;
import ar.com.builderadmin.ws.respuestas.cirugias.CirugiaProgramada_VO;
import ar.com.builderadmin.ws.respuestas.cirugias.R_CirugiasProgramadas;

@Controller
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class Admin_CirugiaProgramada extends Admin_Abstracto<CirugiaProgramada_VO> {

	@Autowired
	private DAO_CirugiaProgramada daoCirugiaProgramada;

	@Autowired
	private Paginador<CirugiaProgramada_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private Admin_Alertas adminAlertas;
	
	@Override
	protected Gson getGson() {
		Gson gsb = new GsonBuilder()
				.setDateFormat("dd/MM/yyyy HH:mm")
				.registerTypeAdapter(CirugiaProgramada_VO.class,
						new CirugiaProgramadaSimpleAdapter()).create();

		return gsb;
	}

	public String obtenerCirugiasDeQuirofanoParaUnaFecha(String str_fecha,
			Integer nroSala) {

		DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");

		Gson g = this.getGson();

		Date fecha;
		try {
			fecha = dia.parse(str_fecha);
		} catch (ParseException e) {
			e.printStackTrace();
			fecha = new Date();
		}

		R_CirugiasProgramadas resp_diasQuirofano = getDao()
				.getCirugiasProgramadasDeFecha(fecha, nroSala);

		if (resp_diasQuirofano != null) {

			resp_diasQuirofano.setFecha(str_fecha);

		} else {

			resp_diasQuirofano = new R_CirugiasProgramadas();

			resp_diasQuirofano.setOk(false);
			resp_diasQuirofano.setFecha(str_fecha);
			resp_diasQuirofano.setNroSala(nroSala);
			resp_diasQuirofano.setLista(new ArrayList<CirugiaProgramada_VO>());
		}

		return g.toJson(resp_diasQuirofano);
	}

	/******************************************************************************/
	@Override
	protected I_FX getFX_Crear(CirugiaProgramada_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(CirugiaProgramada_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(CirugiaProgramada_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(CirugiaProgramada_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}
	/******************************************************************************/
	
	/**
	 * @return the daoCirugiaProgramada
	 */
	@Override
	public DAO_CirugiaProgramada getDao() {
		return daoCirugiaProgramada;
	}
	
	/**
	 * @return the daoCirugiaProgramada
	 */
	public DAO_CirugiaProgramada getDaoCirugiaProgramada() {
		return daoCirugiaProgramada;
	}

	/**
	 * @param daoCirugiaProgramada the daoCirugiaProgramada to set
	 */
	public void setDaoCirugiaProgramada(DAO_CirugiaProgramada daoCirugiaProgramada) {
		this.daoCirugiaProgramada = daoCirugiaProgramada;
	}

	/**
	 * @return the paginador
	 */
	@Override
	public Paginador<CirugiaProgramada_VO> getPaginador() {
		return paginador;
	}

	/**
	 * @param paginador the paginador to set
	 */
	public void setPaginador(Paginador<CirugiaProgramada_VO> paginador) {
		this.paginador = paginador;
	}

	/**
	 * @return the json_paginador
	 */
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	/**
	 * @param json_paginador the json_paginador to set
	 */
	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	/**
	 * @param admin_Alertas the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.adminAlertas = admin_Alertas;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return adminAlertas;
	}

}