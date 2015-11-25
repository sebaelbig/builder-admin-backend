package ar.org.hospitalespanol.controllers.historiaClinica.informes;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.historiaClinica.DAO_Informes;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.historiaClinica.informes.FX_BuscarInforme;
import ar.org.hospitalespanol.fx.historiaClinica.informes.FX_CrearInforme;
import ar.org.hospitalespanol.fx.historiaClinica.informes.FX_EliminarInforme;
import ar.org.hospitalespanol.fx.historiaClinica.informes.FX_ModificarInforme;
import ar.org.hospitalespanol.vo.historiaClinica.informes.Informe_VO;

/**
 * Componente para el manejo de las areas
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Informes extends Admin_Abstracto<Informe_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Informe_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Informes dao_Informes;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Informe_VO area;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Informe_VO area, String usr) {
		return new FX_BuscarInforme(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(Informe_VO area, String usr) {
		return new FX_CrearInforme(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Informe_VO area, String usr) {
		return new FX_EliminarInforme(getDao(),  area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Informe_VO area, String usr) {
		return new FX_ModificarInforme(getDao(),  area, usr);
	}

//	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
//		
//		String[] resul = null;
//		
//		if (atributoEntidad.equals("area")){
//			resul = this.buscarInformes(valorABuscar);
//		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
//			resul = this.buscarEspecialidades(valorABuscar);
//		} 
//		return resul;
//	}
//	
//	public String[] buscar(String atributoEntidad, String valorABuscar) {
//		
//		String[] resul = null;
//		
//		if (atributoEntidad.equals("area")){
//			resul = this.buscarInformes(valorABuscar);
//		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
//			resul = this.buscarEspecialidades(valorABuscar);
//		} 
//		return resul;
//	}
//	
//	private String[] buscarEspecialidades(String valorABuscar) {
//
//		DAO_Especialidad dao_esp = new DAO_Especialidad();
//		
//		return armarResultado(dao_esp.buscarEspecialidadPorNombre(valorABuscar),valorABuscar);
//	
//	}


//	private String[] buscarInformes(String valorABuscar) {
//		return armarResultado(getDao_Informes().buscarInformesPorNombreOCodigo(valorABuscar),valorABuscar);
//	}

	/**
	 * @return the dao_Informe
	 */
	public DAO_Informes getDao_Informes() {
		return dao_Informes;
	}

	/**
	 * @param dao_Informe the dao_Informe to set
	 */
	public void setDao_Informes(DAO_Informes dao_Informe) {
		this.dao_Informes = dao_Informe;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Informe_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Informe_VO> getDao() {
		return getDao_Informes();
	}
	
}