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
import ar.com.builderadmin.dao.core.areas.DAO_Areas;
import ar.com.builderadmin.dao.core.especialidades.DAO_Especialidad;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.areas.FX_BuscarArea;
import ar.com.builderadmin.fx.core.areas.FX_CrearArea;
import ar.com.builderadmin.fx.core.areas.FX_EliminarArea;
import ar.com.builderadmin.fx.core.areas.FX_ModificarArea;
import ar.com.builderadmin.vo.core.areas.Area_VO;

/**
 * Componente para el manejo de las areas
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Areas extends Admin_Abstracto<Area_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Area_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Areas dao_Areas;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Area_VO area;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Area_VO area, String usr) {
		return new FX_BuscarArea(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(Area_VO area, String usr) {
		return new FX_CrearArea(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Area_VO area, String usr) {
		return new FX_EliminarArea(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Area_VO area, String usr) {
		return new FX_ModificarArea(getDao(),  area, usr);
	}

	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("area")){
			resul = this.buscarAreas(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	public String[] buscar(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("area")){
			resul = this.buscarAreas(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarPorNombre(valorABuscar),valorABuscar);
	
	}


	private String[] buscarAreas(String valorABuscar) {
		return armarResultado(getDao_Areas().buscarAreasPorNombreOCodigo(valorABuscar),valorABuscar);
	}

	/**
	 * @return the dao_Area
	 */
	public DAO_Areas getDao_Areas() {
		return dao_Areas;
	}

	/**
	 * @param dao_Area the dao_Area to set
	 */
	public void setDao_Areas(DAO_Areas dao_Area) {
		this.dao_Areas = dao_Area;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Area_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Area_VO> getDao() {
		return getDao_Areas();
	}
	
}