package ar.com.builderadmin.controllers.core.usuarios.roles.profesionales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.roles.profesionales.DAO_FirmaProfesional;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.roles.profesionales.FX_CrearFirmaProfesional;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.FirmaProfesional_VO;

@Controller
public class Admin_FirmaProfesional extends Admin_Abstracto<FirmaProfesional_VO> {

	@Autowired
	private Paginador<FirmaProfesional_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_FirmaProfesional dao_Rol;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private FirmaProfesional_VO rolHE_VO;

	private JSON_Respuesta json_respuesta;

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

	public void setDao(DAO_FirmaProfesional dao) {
		this.dao_Rol = dao;
	}

	@Override
	protected DAO getDao() {
		return dao_Rol;
	}

	@Override
	protected Paginador<FirmaProfesional_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<FirmaProfesional_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	/**
	 * @return the dao_Rol
	 */
	public DAO_FirmaProfesional getDao_Rol() {
		return dao_Rol;
	}

	/**
	 * @param dao_Rol the dao_Rol to set
	 */
	public void setDao_Rol(DAO_FirmaProfesional dao_Rol) {
		this.dao_Rol = dao_Rol;
	}

	/**
	 * @return the rolHE_VO
	 */
	public FirmaProfesional_VO getFirmaProfesional_VO() {
		return rolHE_VO;
	}

	/**
	 * @param rolHE_VO the rolHE_VO to set
	 */
	public void setFirmaProfesional_VO(FirmaProfesional_VO rolHE_VO) {
		this.rolHE_VO = rolHE_VO;
	}

	@Override
	protected I_FX getFX_Crear(FirmaProfesional_VO tipo, String usr) {
		
		return new FX_CrearFirmaProfesional(getDao(), tipo, usr);
	}

	@Override
	protected I_FX getFX_Modificar(FirmaProfesional_VO tipo, String usr) {
		
//		return new FX_ModificarFirmaProfesional_old(getDao(), tipo, usr);
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(FirmaProfesional_VO tipo, String usr) {
		
//		return new FX_EliminarFirmaProfesional_old(getDao(), tipo, usr);
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(FirmaProfesional_VO tipo, String usr) {
		
		return null;
	}
	
}