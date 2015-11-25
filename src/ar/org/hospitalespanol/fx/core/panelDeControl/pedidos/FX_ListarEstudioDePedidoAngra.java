package ar.org.hospitalespanol.fx.core.panelDeControl.pedidos;

import java.util.Map;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_ListarEstudioDePedidoAngra implements I_FX {
	

	public FX_ListarEstudioDePedidoAngra() {
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
