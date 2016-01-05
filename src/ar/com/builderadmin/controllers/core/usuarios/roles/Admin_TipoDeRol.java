package ar.com.builderadmin.controllers.core.usuarios.roles;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.especialidades.DAO_Especialidad;
import ar.com.builderadmin.dao.core.usuarios.roles.DAO_TipoDeRol;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_BuscarTipoDeRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_CrearTipoDeRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_EliminarTipoDeRol;
import ar.com.builderadmin.fx.core.usuarios.roles.FX_ModificarTipoDeRol;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

/**
 * Componente para el manejo de las tipoDeRols
 * 
 * @author seba garcia
 */
@Controller
public class Admin_TipoDeRol extends Admin_Abstracto<TipoDeRol_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<TipoDeRol_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TipoDeRol dao_TipoDeRol;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private TipoDeRol_VO tipoDeRol;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(TipoDeRol_VO tipoDeRol, String usr) {
		return new FX_BuscarTipoDeRol(getDao(), tipoDeRol, usr);
	}

	@Override
	protected I_FX getFX_Crear(TipoDeRol_VO tipoDeRol, String usr) {
		return new FX_CrearTipoDeRol(getDao(),  tipoDeRol, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(TipoDeRol_VO tipoDeRol, String usr) {
		return new FX_EliminarTipoDeRol(getDao(),  tipoDeRol, usr);
	}

	@Override
	protected I_FX getFX_Modificar(TipoDeRol_VO tipoDeRol, String usr) {
		return new FX_ModificarTipoDeRol(getDao(),  tipoDeRol, usr);
	}

	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("tipoDeRol")){
			resul = this.buscarTipoDeRols(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	public String[] buscar(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("tipoDeRol")){
			resul = this.buscarTipoDeRols(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarPorNombre(valorABuscar),valorABuscar);
	
	}

	private String[] buscarTipoDeRols(String valorABuscar) {
		return armarResultado(getDao_TipoDeRols().buscarTipoDeRolsPorNombreOCodigo(valorABuscar),valorABuscar);
	}

	/**
	 * @return the dao_TipoDeRol
	 */
	public DAO_TipoDeRol getDao_TipoDeRols() {
		return dao_TipoDeRol;
	}

	/**
	 * @param dao_TipoDeRol the dao_TipoDeRol to set
	 */
	public void setDao_TipoDeRols(DAO_TipoDeRol dao_TipoDeRol) {
		this.dao_TipoDeRol = dao_TipoDeRol;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<TipoDeRol_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<TipoDeRol_VO> getDao() {
		return getDao_TipoDeRols();
	}

	/**
	 * @return the dao_TipoDeRol
	 */
	public DAO_TipoDeRol getDao_TipoDeRol() {
		return dao_TipoDeRol;
	}

	/**
	 * @param dao_TipoDeRol the dao_TipoDeRol to set
	 */
	public void setDao_TipoDeRol(DAO_TipoDeRol dao_TipoDeRol) {
		this.dao_TipoDeRol = dao_TipoDeRol;
	}

	/**
	 * @return the admin_Alertas
	 */
	public Admin_Alertas getAdmin_Alertas() {
		return admin_Alertas;
	}

	/**
	 * @param admin_Alertas the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
	}

	/**
	 * @return the tipoDeRol
	 */
	public TipoDeRol_VO getTipoDeRol() {
		return tipoDeRol;
	}

	/**
	 * @param tipoDeRol the tipoDeRol to set
	 */
	public void setTipoDeRol(TipoDeRol_VO tipoDeRol) {
		this.tipoDeRol = tipoDeRol;
	}

	/**
	 * @return the json_respuesta
	 */
	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	/**
	 * @param json_respuesta the json_respuesta to set
	 */
	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	/**
	 * @param paginador the paginador to set
	 */
	public void setPaginador(Paginador<TipoDeRol_VO> paginador) {
		this.paginador = paginador;
	}

	
}