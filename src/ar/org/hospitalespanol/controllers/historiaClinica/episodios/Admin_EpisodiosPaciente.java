package ar.org.hospitalespanol.controllers.historiaClinica.episodios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.historiaClinica.episodios.DAO_EpisodioPaciente;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.EpisodioPaciente_VO;

import com.google.gson.Gson;

@Controller
public class Admin_EpisodiosPaciente extends Admin_Abstracto<EpisodioPaciente_VO>{

	@Autowired
	private Paginador<EpisodioPaciente_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_EpisodioPaciente daoEpisodioPaciente;
	
	public Admin_EpisodiosPaciente(){
		
	}
	
	public String getEpisodiosDePaciente(int tipoDoc, int nroDoc){
		
		List<EpisodioPaciente_VO> episodios=null;
		
		/*Traigo los turnos*/
		episodios= getDaoEpisodioPaciente().getTurnosDePaciente(tipoDoc,nroDoc);
		/*Carpetas*/
		if(episodios == null){
			episodios=getDaoEpisodioPaciente().getCarpetasDePaciente(tipoDoc,nroDoc);
		}else{
			episodios.addAll(getDaoEpisodioPaciente().getCarpetasDePaciente(tipoDoc,nroDoc));
		}
		/*Pedidos*/
		episodios.addAll(getDaoEpisodioPaciente().getPedidosDePaciente(tipoDoc,nroDoc));
		
		
		JSON_Paginador pag = new JSON_Paginador(episodios);
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}

	public DAO_EpisodioPaciente getDaoEpisodioPaciente() {
		return daoEpisodioPaciente;
	}

	public void setDaoEpisodioPaciente(DAO_EpisodioPaciente daoEpisodioPaciente) {
		this.daoEpisodioPaciente = daoEpisodioPaciente;
	}

	@Override
	protected Paginador<EpisodioPaciente_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DAO<EpisodioPaciente_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(EpisodioPaciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(EpisodioPaciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(EpisodioPaciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(EpisodioPaciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
