package ar.org.hospitalespanol.controllers.core.usuarios.funciones;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.especialidades.DAO_Especialidad;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_Funciones;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.FuncionHorus_VO;

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
		
		return armarResultado(dao_esp.buscarEspecialidadPorNombre(valorABuscar),valorABuscar);
	
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