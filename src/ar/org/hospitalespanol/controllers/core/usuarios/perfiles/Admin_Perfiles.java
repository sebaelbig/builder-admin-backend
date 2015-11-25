package ar.org.hospitalespanol.controllers.core.usuarios.perfiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_CrearPerfil;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_EliminarPerfil;
import ar.org.hospitalespanol.fx.core.usuarios.perfiles.FX_ModificarPerfil;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

@Controller
public class Admin_Perfiles extends Admin_Abstracto<Perfil_VO> {

	@Autowired
	private Paginador<Perfil_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Perfil dao_Perfil;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private Perfil_VO rol_VO;

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

	public void setDao(DAO_Perfil dao) {
		this.dao_Perfil = dao;
	}

	@Override
	protected DAO getDao() {
		return dao_Perfil;
	}

	@Override
	protected Paginador<Perfil_VO> getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador<Perfil_VO> paginador) {
		this.paginador = paginador;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
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

	/**
	 * @return the rol_VO
	 */
	public Perfil_VO getPerfil_VO() {
		return rol_VO;
	}

	/**
	 * @param rol_VO the rol_VO to set
	 */
	public void setPerfil_VO(Perfil_VO rol_VO) {
		this.rol_VO = rol_VO;
	}

	@Override
	protected I_FX getFX_Crear(Perfil_VO rol, String usr) {
		
		return new FX_CrearPerfil(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Perfil_VO rol, String usr) {
		
		return  new FX_ModificarPerfil(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Perfil_VO rol, String usr) {
		
		return new FX_EliminarPerfil(getDao(), rol, usr);
	}

	@Override
	protected I_FX getFX_Buscar(Perfil_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

}