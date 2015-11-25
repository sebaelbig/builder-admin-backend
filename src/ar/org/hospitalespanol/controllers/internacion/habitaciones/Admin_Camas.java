package ar.org.hospitalespanol.controllers.internacion.habitaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.internacion.DAO_Pabellon;
import ar.org.hospitalespanol.dao.internacion.habitaciones.DAO_Cama;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.R_CrearEntidad;
import ar.org.hospitalespanol.vo.internacion.Pabellon_VO;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.Cama_VO;

import com.google.gson.Gson;

@Controller
public class Admin_Camas extends Admin_Abstracto<Cama_VO>{
	
	@Autowired
	private Paginador<Cama_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Cama dao_cama;
	
	@Autowired
	private DAO_Pabellon dao_pabellon;

	
	public String getCamas(){
		
		
		List<Pabellon_VO> pabellones = this.getDao_pabellon().getPabellones();
		// System.out.print(pabellones);
		List<Cama_VO> camas = null;
		for (Pabellon_VO p : pabellones) {
			if (camas == null) {
				camas = this.getDao_cama().getCamasPorPabellon(p);
			} else {
				camas.addAll(this.getDao_cama().getCamasPorPabellon(p));
			}
		}
		
		JSON_Paginador pag = new JSON_Paginador(camas);

		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setPaginador(pag);

		return new Gson().toJson(resp);
	}
	
	public String setCriterioACama(Cama_VO obj) {
		R_CrearEntidad resp= this.getDao_cama().setCriterioACama(obj);

		return new Gson().toJson(resp);
	}
	
	
	
	
	public DAO_Pabellon getDao_pabellon() {
		return dao_pabellon;
	}

	public void setDao_pabellon(DAO_Pabellon dao_pabellon) {
		this.dao_pabellon = dao_pabellon;
	}

	@Override
	public Paginador<Cama_VO> getPaginador() {
		return paginador;
	}
	
	public void setPaginador(Paginador<Cama_VO> paginador) {
		this.paginador = paginador;
	}

	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}
	


	public DAO_Cama getDao_cama() {
		return dao_cama;
	}

	public void setDao_cama(DAO_Cama dao_cama) {
		this.dao_cama = dao_cama;
	}

	@Override
	protected DAO<Cama_VO> getDao() {
		return dao_cama;
	}

	@Override
	protected I_FX getFX_Crear(Cama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Cama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Cama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Cama_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}


}
