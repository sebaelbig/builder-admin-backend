package ar.com.builderadmin.fx.core.panelDeControl;

import java.util.Map;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_VerAlertas implements I_FX {

	public FX_VerAlertas() {
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	@Override
	public Map<String, Object> armarDatosPublicacionComet(EntityManager em) {
		return new java.util.HashMap<String, Object>();
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {
		return null;
	}

	@Override
	public Boolean listar() {
		return true;
	}
	
	@Override
	public String getUsuario() {
		return "";
	}
}