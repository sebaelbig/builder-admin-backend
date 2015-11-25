package ar.org.hospitalespanol.controllers.core.areas.servicios;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.areas.servicios.DAO_Servicio;
import ar.org.hospitalespanol.dao.core.areas.servicios.DAO_Servicio.R_GetServicio;
import ar.org.hospitalespanol.dao.core.usuarios.DAO_Usuario;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.areas.servicios.FX_BuscarServicio;
import ar.org.hospitalespanol.fx.core.areas.servicios.FX_CrearServicio;
import ar.org.hospitalespanol.fx.core.areas.servicios.FX_EliminarServicio;
import ar.org.hospitalespanol.fx.core.areas.servicios.FX_ModificarServicio;
import ar.org.hospitalespanol.model.core.areas.AreaHorus;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;

import com.google.gson.Gson;

/**
 * Componente para manejar Servicios que no tienen Especialidad
 * 
 * @author svalle
 */

@Controller
public class Admin_Servicios extends Admin_Abstracto<Servicio_VO> implements
		Serializable {

	@Autowired
	private Paginador<Servicio_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Servicio dao_Servicio;

	@Autowired
	private Admin_Alertas admin_Alertas;
	
	private Servicio_VO templateDeDescripcionPrivado_VO;

	private JSON_Respuesta json_respuesta;
	
//	private List<String> especialidades;
//	private String especialidad;
//	private List<Sucursal_VO> sucursales = new ArrayList<Sucursal_VO>();

	
//	/**
//	 * Lista los elementos y transforma el paginador en string JSON
//	 * 
//	 * @param pagina
//	 * @param cantidad
//	 * @return
//	 */
//	@Override
//	public String listar(Integer pagina, Integer cantidad) {
//		return super.listar(pagina, cantidad);
//	}

//	public String horarioDeAtencion(Long idServicio){
//		
//		Servicio servicio = null;
//		if (idServicio != null)
//			servicio = this.getEm().find(Servicio.class, idServicio);
//				
//		List<HorarioDeAtencion_VO> horarios = new ArrayList<HorarioDeAtencion_VO>();
//		
//		DAO_DiaDeAtencion dao_dias = new DAO_DiaDeAtencion();
//		
//		List<DiaDeAtencion> dias = dao_dias.diasDeAtencion(getEm());
//		
//		for (DiaDeAtencion dia : dias) {
//			horarios.add(new HorarioDeAtencion_VO(dia));
//		}
//		
//		if (idServicio != null){
//			for (HorarioDeAtencion h : servicio.getHorarios()) {
//				for (HorarioDeAtencion_VO h_vo : horarios) {
//					if (h.getDiaAtencion().getId().equals(h_vo.getDia().getId())){
//						h_vo.setObject(h);
//					}
//				}
//		}
//			
//		}
//		
//		Gson gson = new Gson();
//		
//		return gson.toJson(horarios);
//		
//	}
	
	public String listarTodosLosDelHE(String usuarioAccion) {
		
		R_GetServicio servs = this.getDao_Servicio().getServiciosDeHE();
		
		JSON_Respuesta respuesta = new JSON_Respuesta();
		
		JSON_Paginador pag = new JSON_Paginador();
		pag.setElementos(servs.getServicios());
		pag.setMensaje(respuesta.getMensaje());
		
		respuesta.setPaginador(pag);
		respuesta.setMensaje(respuesta.getMensaje());
		respuesta.setOk(respuesta.getOk());
		
		return this.getGson().toJson(respuesta);
	}
	
	@Override
	protected I_FX getFX_Crear(Servicio_VO servicio, String usr) {

		AreaHorus ar = this.getDao().getEntityManager().find(AreaHorus.class, servicio.getArea().getId());
		
		servicio.setArea(ar.toValueObjet(I_ValueObject.PROFUNDIDAD_BASE, 1));
		
		return new FX_CrearServicio(getDao(), servicio, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Servicio_VO servicio, String usr) {
		return new FX_EliminarServicio(getDao(), servicio, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Servicio_VO servicio, String usr) {
		return new FX_ModificarServicio(getDao(), servicio, usr);
	}

	@Override
	protected I_FX getFX_Buscar(Servicio_VO servicio, String usr) {
		return new FX_BuscarServicio(getDao(), servicio, usr);
	}

	/*********************************************************************************************/

//	public List<Sucursal_VO> getSucursales() {
//		return sucursales;
//	}
//
//	public void setSucursales(List<Sucursal_VO> sucursales) {
//		this.sucursales = sucursales;
//	}

	/**
	 * @return the paginador
	 */
	@Override
	public Paginador<Servicio_VO> getPaginador() {
		return paginador;
	}

	/**
	 * @param paginador the paginador to set
	 */
	public void setPaginador(Paginador<Servicio_VO> paginador) {
		this.paginador = paginador;
	}

	/**
	 * @return the json_paginador
	 */
	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	/**
	 * @param json_paginador the json_paginador to set
	 */
	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	/**
	 * @return the dao_Servicio
	 */
	public DAO_Servicio getDao_Servicio() {
		return dao_Servicio;
	}

	/**
	 * @param dao_Servicio the dao_Servicio to set
	 */
	public void setDao_Servicio(DAO_Servicio dao_Servicio) {
		this.dao_Servicio = dao_Servicio;
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
	 * @return the templateDeDescripcionPrivado_VO
	 */
	public Servicio_VO getServicio_VO() {
		return templateDeDescripcionPrivado_VO;
	}

	/**
	 * @param templateDeDescripcionPrivado_VO the templateDeDescripcionPrivado_VO to set
	 */
	public void setServicio_VO(
			Servicio_VO templateDeDescripcionPrivado_VO) {
		this.templateDeDescripcionPrivado_VO = templateDeDescripcionPrivado_VO;
	}

//	/**
//	 * @return the json_respuesta
//	 */
//	public JSON_Respuesta getJson_respuesta() {
//		return json_respuesta;
//	}
//
//	/**
//	 * @param json_respuesta the json_respuesta to set
//	 */
//	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
//		this.json_respuesta = json_respuesta;
//	}
//
//	/**
//	 * @return the especialidades
//	 */
//	public List<String> getEspecialidades() {
//		return especialidades;
//	}
//
//	/**
//	 * @param especialidades the especialidades to set
//	 */
//	public void setEspecialidades(List<String> especialidades) {
//		this.especialidades = especialidades;
//	}
//
//	/**
//	 * @return the especialidad
//	 */
//	public String getEspecialidad() {
//		return especialidad;
//	}
//
//	/**
//	 * @param especialidad the especialidad to set
//	 */
//	public void setEspecialidad(String especialidad) {
//		this.especialidad = especialidad;
//	}

	@Override
	protected DAO<Servicio_VO> getDao() {
		return this.dao_Servicio;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return admin_Alertas;
	}

	public String listarDeUsuario( String usuario) {
		
		DAO_Usuario daoUsr = this.getDao().getInstance(DAO_Usuario.class);
		
		JSON_Respuesta resp = new JSON_Respuesta();
		resp.setOk(true);
		
		List<Servicio_VO> servicios = daoUsr.listarServiciosDeUsuario(usuario);
		JSON_Paginador pag = new JSON_Paginador(servicios);
		resp.setPaginador(pag);
		
		return new Gson().toJson(resp);
	}

	
}