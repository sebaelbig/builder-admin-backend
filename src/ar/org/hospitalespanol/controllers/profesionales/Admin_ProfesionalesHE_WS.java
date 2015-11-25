package ar.org.hospitalespanol.controllers.profesionales;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.core.usuarios.roles.profesionales.DAO_ProfesionalHE;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.profesionales.FX_BuscarProfesionalHE;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.org.hospitalespanol.ws.respuestas.datosDelPaciente.R_ProfesionalesHE;

import com.google.gson.Gson;

@Controller
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class Admin_ProfesionalesHE_WS extends Admin_Abstracto<ProfesionalHE_VO>
		implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<ProfesionalHE_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_ProfesionalHE dao;

	@Override
	protected DAO_ProfesionalHE getDao() {
		return this.dao;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		return this.json_paginador;
	}

	@Override
	protected Paginador<ProfesionalHE_VO> getPaginador() {
		return this.paginador;
	}


	@Override
	protected I_FX getFX_Crear(ProfesionalHE_VO profesionalHE, String usuarioAccion) {
//		return new FX_CrearProfesionalHE(getDao(), profesionalHE, usuarioAccion);
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(ProfesionalHE_VO profesionalHE, String usuarioAccion) {
//		return new FX_ModificarProfesionalHE(getDao(), profesionalHE, usuarioAccion);
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(ProfesionalHE_VO profesionalHE, String usuarioAccion) {
//		return new FX_EliminarProfesionalHE(getDao(), profesionalHE, usuarioAccion);
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(ProfesionalHE_VO profesionalHE, String usuarioAccion) {
		return new FX_BuscarProfesionalHE(getDao(), profesionalHE, usuarioAccion);
	}
	
	@Override
	public String buscar(Integer pagina, Integer cantidad, ProfesionalHE_VO vo, String usr) {
		FX_BuscarProfesionalHE fx = new FX_BuscarProfesionalHE(getDao(), vo, usr);
		
		return ejecutarFuncion(fx);
	}
	
	public void setPaginador(Paginador<ProfesionalHE_VO> paginador) {
		this.paginador = paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	public void setDao(DAO_ProfesionalHE dao) {
		this.dao = dao;
	}

	public String getProfesionalesPor(String parametro){
		String nombre="";
		int mat;
		try{
			mat=Integer.parseInt(parametro);
		}catch(Exception e){
			mat=-1;
			nombre=parametro;
		}
		R_ProfesionalesHE resp=this.getDao().getProfesionalesPor(nombre,mat);
		
		return new Gson().toJson(resp);
	}
	
	public String getMatricula(String usuarioProfesional) {

		DAO_ProfesionalHE.R_Matricula r_mat = this.getDao()
				.getMatriculaDeUsuario(usuarioProfesional);

		return new Gson().toJson(r_mat);
	}

	/**
	 * Dado un nro de matricula, devuelve el profesional
	 * 
	 * @param nroMatricula
	 * @return
	 */
	public ProfesionalHE_VO getProfesionalConMatricula(Integer nroMatricula) {

		// Cargo el filtro
		ProfesionalHE_VO filtro = new ProfesionalHE_VO();
		if (nroMatricula != null) {
			filtro.setNroMatricula(nroMatricula);
		}
		JSON_Respuesta resp = new FX_BuscarProfesionalHE(getDao(), filtro, "webService").ejecutar(getAdminAlertas());

//		List<ProfesionalHE_VO> profes = this.getDao()
//				.buscar("profesional", 1, 1).getLista();

		// Ejecuto la busqueda
		return (ProfesionalHE_VO) ((resp.getPaginador().getCantidadElementos()>0) ? resp.getPaginador().getElementos().get(0) : null);
	}
	
	
	@Autowired
	private Admin_Alertas admin_Alertas;

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}
	
//	public String listar(String usuarioAccion) {
//		FX_ListarProfesionaes fx = new FX_ListarProfesionaes(getDao(), usuarioAccion);
//		
//		return ejecutarFuncion(fx);
//	}
}