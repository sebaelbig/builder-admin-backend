package ar.com.builderadmin.controllers.core.usuarios.perfiles;

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
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_TipoDePerfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_ActualizarFuncionesTipoPerfil;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_BuscarTipoDePerfil;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_CrearTipoDePerfil;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_EliminarTipoDePerfil;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_ModificarTipoDePerfil;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

/**
 * Componente para el manejo de las tipoDePerfils
 * 
 * @author seba garcia
 */
@Controller
public class Admin_TipoDePerfil extends Admin_Abstracto<TipoDePerfil_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<TipoDePerfil_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TipoDePerfil dao_TipoDePerfil;
	
	@Autowired
	private DAO_Perfil dao_Perfil;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private TipoDePerfil_VO tipoDePerfil;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(TipoDePerfil_VO tipoDePerfil, String usr) {
		return new FX_BuscarTipoDePerfil(getDao(), tipoDePerfil, usr);
	}

	@Override
	protected I_FX getFX_Crear(TipoDePerfil_VO tipoDePerfil, String usr) {
		return new FX_CrearTipoDePerfil(getDao(),  tipoDePerfil, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(TipoDePerfil_VO tipoDePerfil, String usr) {
		return new FX_EliminarTipoDePerfil(getDao(),  tipoDePerfil, usr);
	}

	@Override
	protected I_FX getFX_Modificar(TipoDePerfil_VO tipoDePerfil, String usr) {
		return new FX_ModificarTipoDePerfil(getDao(), getDao_Perfil(), tipoDePerfil, usr);
	}

	@Override
	public String modificar(TipoDePerfil_VO vo, String usr) {
		
		I_FX funcion = getFX_Modificar(vo, usr);

		String resul =  ejecutarFuncion(funcion);
		
		if (vo.getAplicarActuales())
			new FX_ActualizarFuncionesTipoPerfil(getDao_Perfil(), vo, usr).ejecutar(getAdminAlertas());
		
		return resul;
	}
	
	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("tipoDePerfil")){
			resul = this.buscarTipoDePerfils(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	public String[] buscar(String atributoEntidad, String valorABuscar) {
		
		String[] resul = null;
		
		if (atributoEntidad.equals("tipoDePerfil")){
			resul = this.buscarTipoDePerfils(valorABuscar);
		}else if (atributoEntidad.equalsIgnoreCase("especialidad")){
			resul = this.buscarEspecialidades(valorABuscar);
		} 
		return resul;
	}
	
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarEspecialidadPorNombre(valorABuscar),valorABuscar);
	
	}

	private String[] buscarTipoDePerfils(String valorABuscar) {
		return armarResultado(getDao_TipoDePerfils().buscarTipoDePerfilsPorNombreOCodigo(valorABuscar),valorABuscar);
	}

	/**
	 * @return the dao_TipoDePerfil
	 */
	public DAO_TipoDePerfil getDao_TipoDePerfils() {
		return dao_TipoDePerfil;
	}

	/**
	 * @param dao_TipoDePerfil the dao_TipoDePerfil to set
	 */
	public void setDao_TipoDePerfils(DAO_TipoDePerfil dao_TipoDePerfil) {
		this.dao_TipoDePerfil = dao_TipoDePerfil;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<TipoDePerfil_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<TipoDePerfil_VO> getDao() {
		return getDao_TipoDePerfils();
	}

	/**
	 * @return the dao_TipoDePerfil
	 */
	public DAO_TipoDePerfil getDao_TipoDePerfil() {
		return dao_TipoDePerfil;
	}

	/**
	 * @param dao_TipoDePerfil the dao_TipoDePerfil to set
	 */
	public void setDao_TipoDePerfil(DAO_TipoDePerfil dao_TipoDePerfil) {
		this.dao_TipoDePerfil = dao_TipoDePerfil;
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
	 * @return the tipoDePerfil
	 */
	public TipoDePerfil_VO getTipoDePerfil() {
		return tipoDePerfil;
	}

	/**
	 * @param tipoDePerfil the tipoDePerfil to set
	 */
	public void setTipoDePerfil(TipoDePerfil_VO tipoDePerfil) {
		this.tipoDePerfil = tipoDePerfil;
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
	public void setPaginador(Paginador<TipoDePerfil_VO> paginador) {
		this.paginador = paginador;
	}

	/**
	 * @return the dao_Perfil
	 */
	public DAO_Perfil getDao_Perfil() {
		return dao_Perfil;
	}

	/**
	 * @param dao_Perfil the dao_Perfil to set
	 */
	public void setDao_Perfil(DAO_Perfil dao_Perfil) {
		this.dao_Perfil = dao_Perfil;
	}

	
	
}