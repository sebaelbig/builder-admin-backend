package ar.com.builderadmin.controllers.core.usuarios.funciones;

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
import ar.com.builderadmin.dao.core.usuarios.DAO_Funciones;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.FuncionHorus_VO;

/**
 * Componente para el manejo de las areas
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Funciones extends Admin_Abstracto<FuncionHorus_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<FuncionHorus_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Funciones dao_Funciones;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private FuncionHorus_VO funcion;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(FuncionHorus_VO area, String usr) {
		return null;//new FX_BuscarFuncione(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(FuncionHorus_VO area, String usr) {
		return null;//new FX_CrearFuncione(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(FuncionHorus_VO area, String usr) {
		return null;//new FX_EliminarFuncione(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(FuncionHorus_VO area, String usr) {
		return null;//new FX_ModificarFuncione(getDao(),  area, usr);
	}

//	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
//		
//		String[] resul = null;
//		
//		if (atributoEntidad.equals("area")){
//			resul = this.buscarFunciones(valorABuscar);
//		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
//			resul = this.buscarEspecialidades(valorABuscar);
//		} 
//		return resul;
//	}

	//	public String[] buscar(String atributoEntidad, String valorABuscar) {
//		
//		String[] resul = null;
//		
//		if (atributoEntidad.equals("area")){
//			resul = this.buscarFunciones(valorABuscar);
//		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
//			resul = this.buscarEspecialidades(valorABuscar);
//		} 
//		return resul;
//	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarPorNombre(valorABuscar),valorABuscar);
	
	}

//	private String[] buscarFunciones(String valorABuscar) {
//		return armarResultado(getDao_Funciones().buscarFuncionesPorNombreOCodigo(valorABuscar),valorABuscar);
//	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<FuncionHorus_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<FuncionHorus_VO> getDao() {
		return getDao_Funciones();
	}

	/**
	 * @return the dao_Funciones
	 */
	public DAO_Funciones getDao_Funciones() {
		return dao_Funciones;
	}

	/**
	 * @param dao_Funciones the dao_Funciones to set
	 */
	public void setDao_Funciones(DAO_Funciones dao_Funciones) {
		this.dao_Funciones = dao_Funciones;
	}

}