package ar.com.builderadmin.controllers.core.usuarios.perfiles;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.especialidades.DAO_Especialidad;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_CrearPerfil;
import ar.com.builderadmin.fx.core.usuarios.perfiles.FX_ModificarPerfil;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.core.usuarios.perfiles.PerfilServicio_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

/**
 * Componente para el manejo de las tipoDePerfils
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Perfil extends Admin_Abstracto<Perfil_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Perfil_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Perfil dao_Perfil;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Perfil_VO tipoDePerfil;

	private JSON_Respuesta json_respuesta;
	
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Perfil_VO tipoDePerfil, String usr) {
		return null;//new FX_BuscarPerfil(getDao(), tipoDePerfil, usr);
	}

	@Override
	protected I_FX getFX_Crear(Perfil_VO tipoDePerfil, String usr) {
		return new FX_CrearPerfil(getDao(),  tipoDePerfil, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Perfil_VO tipoDePerfil, String usr) {
	//	return new FX_EliminarPerfil(getDao(),  tipoDePerfil, usr);
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Perfil_VO tipoDePerfil, String usr) {
		return new FX_ModificarPerfil(getDao(),  tipoDePerfil, usr);
	}

/*	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {
		
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
	*/
	private String[] buscarEspecialidades(String valorABuscar) {

		DAO_Especialidad dao_esp = new DAO_Especialidad();
		
		return armarResultado(dao_esp.buscarEspecialidadPorNombre(valorABuscar),valorABuscar);
	
	}

/*	private String[] buscarTipoDePerfils(String valorABuscar) {
		return armarResultado(getDAO_Perfils().buscarTipoDePerfilsPorNombreOCodigo(valorABuscar),valorABuscar);
	}
*/
	/**
	 * @return the DAO_Perfil
	 */
	public DAO_Perfil getDAO_Perfils() {
		return dao_Perfil;
	}

	/**
	 * @param DAO_Perfil the dao_TipoDePerfil to set
	 */
	public void setDAO_Perfils(DAO_Perfil dao_TipoDePerfil) {
		this.dao_Perfil = dao_TipoDePerfil;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Perfil_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Perfil_VO> getDao() {
		return getDAO_Perfils();
	}

	/**
	 * @return the dao_TipoDePerfil
	 */
	public DAO_Perfil getDAO_Perfil() {
		return dao_Perfil;
	}

	/**
	 * @param DAO_Perfil the dao_TipoDePerfil to set
	 */
	public void setDao_TipoDePerfil(DAO_Perfil dao_TipoDePerfil) {
		this.dao_Perfil = dao_TipoDePerfil;
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
	public Perfil_VO getTipoDePerfil() {
		return tipoDePerfil;
	}

	/**
	 * @param tipoDePerfil the tipoDePerfil to set
	 */
	public void setTipoDePerfil(Perfil_VO tipoDePerfil) {
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
	public void setPaginador(Paginador<Perfil_VO> paginador) {
		this.paginador = paginador;
	}

	public String getPerfilPorServicio (Servicio srv) {
		setJson_respuesta(new JSON_Respuesta());
		getJson_respuesta().setPaginador(new JSON_Paginador(getDAO_Perfils().getPerfilPorServicio(srv)));
		getJson_respuesta().setOk(true);
		return new Gson().toJson(this.getJson_respuesta());
	}
	
	/**
	 * Busca los perfiles de un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public String buscarMedicosDelServicioDelUsuario (String usuario) {
		setJson_respuesta(new JSON_Respuesta());
		
		List<PerfilServicio_VO> perfilesRecuperados = getDAO_Perfils().buscarMedicosDelServicioDelUsuario(usuario);
		
		getJson_respuesta().setPaginador(new JSON_Paginador(perfilesRecuperados));
		getJson_respuesta().setOk(true);
		
		return new Gson().toJson(this.getJson_respuesta());
	}

}