package ar.org.hospitalespanol.controllers.core.usuarios.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.usuarios.roles.DAO_RolHE;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_BuscarRolesHE;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_CrearRolHE;
import ar.org.hospitalespanol.fx.core.usuarios.roles.FX_ModificarRolHE;
import ar.org.hospitalespanol.vo.core.usuarios.roles.RolHE_VO;

@Controller
public class Admin_RolesHE extends Admin_Abstracto<RolHE_VO> {

	@Autowired
	private Paginador<RolHE_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_RolHE dao_Rol;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private RolHE_VO rolHE_VO;

	private JSON_Respuesta json_respuesta;

	public String crearNuevo() {
		setJson_respuesta(null);
		return "crear_template_privado";
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	public void setDao(DAO_RolHE dao) {
		this.dao_Rol = dao;
	}

	@Override
	protected DAO getDao() {
		return dao_Rol;
	}

	@Override
	protected Paginador<RolHE_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<RolHE_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	/**
	 * @return the dao_Rol
	 */
	public DAO_RolHE getDao_Rol() {
		return dao_Rol;
	}

	/**
	 * @param dao_Rol the dao_Rol to set
	 */
	public void setDao_Rol(DAO_RolHE dao_Rol) {
		this.dao_Rol = dao_Rol;
	}

	/**
	 * @return the rolHE_VO
	 */
	public RolHE_VO getRolHE_VO() {
		return rolHE_VO;
	}

	/**
	 * @param rolHE_VO the rolHE_VO to set
	 */
	public void setRolHE_VO(RolHE_VO rolHE_VO) {
		this.rolHE_VO = rolHE_VO;
	}

	@Override
	protected I_FX getFX_Crear(RolHE_VO rol, String usr) {
		
		return new FX_CrearRolHE(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Modificar(RolHE_VO rol, String usr) {
		
		return  new FX_ModificarRolHE(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(RolHE_VO rol, String usr) {
		
		rol.setEstado(RolHE_VO.INACTIVO);
		
		return new FX_ModificarRolHE(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Buscar(RolHE_VO rol, String usr) {
		
		return new FX_BuscarRolesHE(getDao(), rol, usr);
	}
	
}