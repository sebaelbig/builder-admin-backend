package ar.com.builderadmin.controllers.core.templates.propiedades;

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
import ar.com.builderadmin.dao.core.templates.propiedades.DAO_Propiedades;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.templates.propiedades.FX_BuscarPropiedad;
import ar.com.builderadmin.fx.core.templates.propiedades.FX_CrearPropiedad;
import ar.com.builderadmin.fx.core.templates.propiedades.FX_EliminarPropiedad;
import ar.com.builderadmin.fx.core.templates.propiedades.FX_ModificarPropiedad;
import ar.com.builderadmin.vo.core.templates.Propiedad_VO;

/**
 * Componente para el manejo de las areas
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Propiedades extends Admin_Abstracto<Propiedad_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Propiedad_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Propiedades dao_Propiedades;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Propiedad_VO propiedad;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Propiedad_VO area, String usr) {
		return new FX_BuscarPropiedad(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(Propiedad_VO area, String usr) {
		return new FX_CrearPropiedad(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Propiedad_VO area, String usr) {
		return new FX_EliminarPropiedad(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Propiedad_VO area, String usr) {
		return new FX_ModificarPropiedad(getDao(),  area, usr);
	}

	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("area")){
			resul = this.buscarPropiedads(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	public String[] buscar(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("area")){
			resul = this.buscarPropiedads(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarPorNombre(valorABuscar),valorABuscar);
	
	}


	private String[] buscarPropiedads(String valorABuscar) {
		return armarResultado(getDao_Propiedades().buscarPropiedadesPorNombreOCodigo(valorABuscar),valorABuscar);
	}


	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Propiedad_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Propiedad_VO> getDao() {
		return getDao_Propiedades();
	}

	/**
	 * @return the dao_Propiedades
	 */
	public DAO_Propiedades getDao_Propiedades() {
		return dao_Propiedades;
	}

	/**
	 * @param dao_Propiedades the dao_Propiedades to set
	 */
	public void setDao_Propiedades(DAO_Propiedades dao_Propiedades) {
		this.dao_Propiedades = dao_Propiedades;
	}
	
	
	
	

}