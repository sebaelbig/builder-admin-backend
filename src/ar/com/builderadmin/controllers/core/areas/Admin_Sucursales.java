package ar.com.builderadmin.controllers.core.areas;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.areas.DAO_Sucursal;
import ar.com.builderadmin.dao.core.especialidades.DAO_Especialidad;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.areas.FX_BuscarSucursal;
import ar.com.builderadmin.fx.core.areas.FX_CrearSucursal;
import ar.com.builderadmin.fx.core.areas.FX_EliminarSucursal;
import ar.com.builderadmin.fx.core.areas.FX_ModificarSucursal;
import ar.com.builderadmin.vo.core.areas.Sucursal_VO;

/**
 * Componente para el manejo de las sucursals
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Sucursales extends Admin_Abstracto<Sucursal_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Sucursal_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Sucursal dao_Sucursal;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Sucursal_VO sucursal;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Sucursal_VO sucursal, String usr) {
		return new FX_BuscarSucursal(getDao(), sucursal, usr);
	}

	@Override
	protected I_FX getFX_Crear(Sucursal_VO sucursal, String usr) {
		return new FX_CrearSucursal(getDao(),  sucursal, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Sucursal_VO sucursal, String usr) {
		return new FX_EliminarSucursal(getDao(),  sucursal, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Sucursal_VO sucursal, String usr) {
		return new FX_ModificarSucursal(getDao(),  sucursal, usr);
	}

	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("sucursal")){
			resul = this.buscarSucursals(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	public String[] buscar(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("sucursal")){
			resul = this.buscarSucursals(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarPorNombre(valorABuscar),valorABuscar);
	
	}

	private String[] buscarSucursals(String valorABuscar) {
		return armarResultado(getDao_Sucursals().buscarSucursalsPorNombreOCodigo(valorABuscar),valorABuscar);
	}

	/**
	 * @return the dao_Sucursal
	 */
	public DAO_Sucursal getDao_Sucursals() {
		return dao_Sucursal;
	}

	/**
	 * @param dao_Sucursal the dao_Sucursal to set
	 */
	public void setDao_Sucursals(DAO_Sucursal dao_Sucursal) {
		this.dao_Sucursal = dao_Sucursal;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Sucursal_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Sucursal_VO> getDao() {
		return getDao_Sucursals();
	}

	/**
	 * @return the dao_Sucursal
	 */
	public DAO_Sucursal getDao_Sucursal() {
		return dao_Sucursal;
	}

	/**
	 * @param dao_Sucursal the dao_Sucursal to set
	 */
	public void setDao_Sucursal(DAO_Sucursal dao_Sucursal) {
		this.dao_Sucursal = dao_Sucursal;
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
	 * @return the sucursal
	 */
	public Sucursal_VO getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal_VO sucursal) {
		this.sucursal = sucursal;
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
	public void setPaginador(Paginador<Sucursal_VO> paginador) {
		this.paginador = paginador;
	}

	
}