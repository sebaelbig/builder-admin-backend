package ar.com.builderadmin.controllers.core.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.DAO_TipoIDHE;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.usuarios.FX_BuscarTipoIDHE;
import ar.com.builderadmin.fx.core.usuarios.FX_CrearTipoIDHE;
import ar.com.builderadmin.fx.core.usuarios.FX_EliminarTipoIDHE;
import ar.com.builderadmin.fx.core.usuarios.FX_ModificarTipoIDHE;
import ar.com.builderadmin.vo.core.usuarios.TipoIDHE_VO;

@Controller
public class Admin_TipoIDHE extends Admin_Abstracto<TipoIDHE_VO> {

	@Autowired
	private Paginador<TipoIDHE_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_TipoIDHE dao_Rol;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private TipoIDHE_VO rolHE_VO;

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

	public void setDao(DAO_TipoIDHE dao) {
		this.dao_Rol = dao;
	}

	@Override
	protected DAO getDao() {
		return dao_Rol;
	}

	@Override
	protected Paginador<TipoIDHE_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<TipoIDHE_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	/**
	 * @return the dao_Rol
	 */
	public DAO_TipoIDHE getDao_Rol() {
		return dao_Rol;
	}

	/**
	 * @param dao_Rol the dao_Rol to set
	 */
	public void setDao_Rol(DAO_TipoIDHE dao_Rol) {
		this.dao_Rol = dao_Rol;
	}

	/**
	 * @return the rolHE_VO
	 */
	public TipoIDHE_VO getTipoIDHE_VO() {
		return rolHE_VO;
	}

	/**
	 * @param rolHE_VO the rolHE_VO to set
	 */
	public void setTipoIDHE_VO(TipoIDHE_VO rolHE_VO) {
		this.rolHE_VO = rolHE_VO;
	}

	@Override
	protected I_FX getFX_Crear(TipoIDHE_VO tipo, String usr) {
		
		return new FX_CrearTipoIDHE(getDao(), tipo, usr);
	}

	@Override
	protected I_FX getFX_Modificar(TipoIDHE_VO tipo, String usr) {
		
		return new FX_ModificarTipoIDHE(getDao(), tipo, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(TipoIDHE_VO tipo, String usr) {
		
		tipo.setEstado(TipoIDHE_VO.INACTIVO);
		
		return new FX_EliminarTipoIDHE(getDao(), tipo, usr);
	}

	@Override
	protected I_FX getFX_Buscar(TipoIDHE_VO tipo, String usr) {
		
		return new FX_BuscarTipoIDHE(getDao(), tipo, usr);
	}
	
}