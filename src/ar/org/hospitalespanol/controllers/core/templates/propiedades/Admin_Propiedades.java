package ar.org.hospitalespanol.controllers.core.templates.propiedades;

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
import ar.org.hospitalespanol.dao.core.templates.propiedades.DAO_Propiedades;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.templates.propiedades.FX_BuscarPropiedad;
import ar.org.hospitalespanol.fx.core.templates.propiedades.FX_CrearPropiedad;
import ar.org.hospitalespanol.fx.core.templates.propiedades.FX_EliminarPropiedad;
import ar.org.hospitalespanol.fx.core.templates.propiedades.FX_ModificarPropiedad;
import ar.org.hospitalespanol.vo.core.templates.Propiedad_VO;

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
		
		return armarResultado(dao_esp.buscarEspecialidadPorNombre(valorABuscar),valorABuscar);
	
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