package ar.com.builderadmin.fx.profesionales;

import java.util.Map;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.core.usuarios.roles.profesionales.DAO_ProfesionalHE;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;

public class FX_BuscarProfesionalHE implements I_FX {
	@Override
	public Boolean listar() {
		return false;
	}

	private DAO_ProfesionalHE dao;
	private ProfesionalHE_VO profe;
	private String usuario;

	public FX_BuscarProfesionalHE() {
	}

	public FX_BuscarProfesionalHE(DAO_ProfesionalHE dao,
			ProfesionalHE_VO profe, String nombreUsuario) {
		setDao(dao);
		setUsuario(nombreUsuario);
		setProfe(profe);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {
		JSON_Respuesta respuesta = new JSON_Respuesta();

		if (getProfe() != null) {
			getDao().getCondiciones().put("profesional", getProfe());
		
			try {
	
				ProfesionalHE_VO resul = getDao().recuperarEntidad(getProfe().getNroMatricula());
	
				JSON_Paginador pag = JSON_Paginador.solo(resul);
	
				respuesta.setPaginador(pag);
				respuesta.setOk(true);
	
			} catch (Exception e) {
				respuesta.setOk(false);
	
				respuesta.setMensaje(
						"Ocurrió un error en la busqueda");
				e.printStackTrace();
			}
		}else{
			getDao().buscar(null, 1, 1);
		}
		return respuesta;
	}

	public String getNombreAccion() {
		return "Buscar";
	}

	public String getNombreMenu() {
		return "Administración";
	}

	public String getNombreSubMenu() {
		return "ProfesionalHEes";
	}

	public String getUrl() {
		return "buscar_especialidad";
	}

	public DAO_ProfesionalHE getDao() {
		return this.dao;
	}

	public void setDao(DAO_ProfesionalHE dao) {
		this.dao = dao;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	public ProfesionalHE_VO getProfe() {
		return this.profe;
	}

	public void setProfe(ProfesionalHE_VO profe) {
		this.profe = profe;
	}

	@Override
	public Map<String, Object> armarDatosPublicacionComet(EntityManager em) {
		return new java.util.HashMap<String, Object>();
	}
}