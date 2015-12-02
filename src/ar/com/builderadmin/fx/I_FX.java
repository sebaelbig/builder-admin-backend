package ar.com.builderadmin.fx;

import java.util.Map;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public abstract interface I_FX {
	
//	public abstract JSON_Respuesta ejecutar();
	public abstract JSON_Respuesta ejecutar(Admin_Alertas adminAlerta);

	public abstract boolean cumpleRestricciones(Perfil_VO paramPerfil_VO);
	
	/**
	 * Devuelve el usuario en accion
	 * 
	 * @return Nombre de usuario que ejecuta la funcion
	 */
	public String getUsuario();
	
	/**
	 * Executes the FX
	 * 
	 * @return a JsonResponse
	public JSON_Respuesta execute();
	 */
	
	/**
	 * Sets the Entity Manager.
	 * 
	 * @param em
	public void setEm(EntityManager em);
	 */
	
	/**
	 * Sets the Value Object.
	 * 
	 * @param em
	 */
	public Boolean listar();
	
	/**
	 * Cada funcion devolvera los datos a pasar en el publish
	 * @param em 
	 * 
	 * @return
	 */
	public Map<String, Object> armarDatosPublicacionComet(EntityManager em);

	
}