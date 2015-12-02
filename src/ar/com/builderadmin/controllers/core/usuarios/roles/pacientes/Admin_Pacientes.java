package ar.com.builderadmin.controllers.core.usuarios.roles.pacientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.roles.pacientes.DAO_Paciente;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.com.builderadmin.vo.turnos.PacienteTurno_VO;

@Controller
public class Admin_Pacientes extends Admin_Abstracto<Paciente_VO> {
	
	@Autowired
	private DAO_Paciente daoPaciente;
	
	

	public DAO_Paciente getDaoPaciente() {
		return daoPaciente;
	}

	public void setDaoPaciente(DAO_Paciente daoPaciente) {
		this.daoPaciente = daoPaciente;
	}

	@Override
	protected Paginador<Paciente_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DAO<Paciente_VO> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(Paciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Paciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Paciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Paciente_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDatosDePaciente(String tipoDniPaciente, String nroDniPaciente) {
		PacienteTurno_VO paciente=this.getDaoPaciente().datosDePacienteTurno(tipoDniPaciente, nroDniPaciente);
		return new Gson().toJson(paciente);
	}
	
	public String buscarPacientePorNroDocApellido(String apellido,String nombre, String nroDoc){
		List<PacienteTurno_VO> pacientes=null;
		JSON_Respuesta resp = new JSON_Respuesta();
		try {
		
			pacientes=this.getDaoPaciente().buscarPacientePorNroDocApellido(apellido,nombre, nroDoc);
	
			JSON_Paginador pag = new JSON_Paginador(pacientes);
			
			resp.setPaginador(pag);
			resp.setMensaje("Se obtubieron los pacientes correctamente");
			resp.setOk(true);
		} catch (Exception e) {

			resp.setOk(false);
			resp.setMensaje("ERROR al listar todos los: "
					+ this.getClass().getSimpleName());
		}
		
		return new Gson().toJson(resp);
	}

}
