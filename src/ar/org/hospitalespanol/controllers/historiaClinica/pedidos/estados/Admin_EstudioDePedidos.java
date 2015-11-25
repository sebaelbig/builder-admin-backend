package ar.org.hospitalespanol.controllers.historiaClinica.pedidos.estados;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.org.hospitalespanol.controllers.Admin_Abstracto;
import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.Paginador;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_AtenderEstudioDePedido;
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_CancelarEstudioDePedido;
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_InformarEstudioDePedido;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;
import ar.org.hospitalespanol.ws.RespuestaCorta;

import com.google.gson.Gson;

/**
 * Componente para el manejo de las pedidos
 * 
 * @author seba garcia
 */
@Controller
public class Admin_EstudioDePedidos extends Admin_Abstracto<EstudioDePedido_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<EstudioDePedido_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_EstudioDePedido dao_EstudioDePedido;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private EstudioDePedido_VO pedido;

	private JSON_Respuesta json_respuesta;

	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	/*******************************************************
	 * Por el momento no se pueden crear/elimnar/modificar
	 */
	@Override
	protected I_FX getFX_Buscar(EstudioDePedido_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Crear(EstudioDePedido_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(EstudioDePedido_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(EstudioDePedido_VO pedido, String usr) {
		return null;
	}

	/***************************************************************/

	public String findByNro(String nroEstudioDePedido, String usuarioAccion) {
		
		EstudioDePedido_VO vo = (EstudioDePedido_VO) this.getDao_EstudioDePedido().findById(Long.parseLong(nroEstudioDePedido));

		DAO_Utils.info(this.log, this.getClass().getName() ,
				"findById", usuarioAccion, "Se recupera la entidad con id: "
						+ nroEstudioDePedido);

		return getGson().toJson(vo);
	}

	/***************************************************************/

	/**
	 * @return the dao_EstudioDePedido
	 */
	public DAO_EstudioDePedido getDao_EstudioDePedido() {
		return dao_EstudioDePedido;
	}

	/**
	 * @param dao_EstudioDePedido
	 *            the dao_EstudioDePedido to set
	 */
	public void setDao_EstudioDePedidos(DAO_EstudioDePedido dao_EstudioDePedido) {
		this.dao_EstudioDePedido = dao_EstudioDePedido;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<EstudioDePedido_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<EstudioDePedido_VO> getDao() {
		return getDao_EstudioDePedido();
	}

	/**
	 * @return the admin_Alertas
	 */
	public Admin_Alertas getAdmin_Alertas() {
		return admin_Alertas;
	}

	/**
	 * @param admin_Alertas
	 *            the admin_Alertas to set
	 */
	public void setAdmin_Alertas(Admin_Alertas admin_Alertas) {
		this.admin_Alertas = admin_Alertas;
	}

	/**
	 * @return the pedido
	 */
	public EstudioDePedido_VO getEstudioDePedido() {
		return pedido;
	}

	/**
	 * @param pedido
	 *            the pedido to set
	 */
	public void setEstudioDePedido(EstudioDePedido_VO pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return the json_respuesta
	 */
	public JSON_Respuesta getJson_respuesta() {
		return json_respuesta;
	}

	/**
	 * @param json_respuesta
	 *            the json_respuesta to set
	 */
	public void setJson_respuesta(JSON_Respuesta json_respuesta) {
		this.json_respuesta = json_respuesta;
	}

	/**
	 * @param paginador
	 *            the paginador to set
	 */
	public void setPaginador(Paginador<EstudioDePedido_VO> paginador) {
		this.paginador = paginador;
	}
	
	/**
	 * Cancela un pedido  (Estado: Informado)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String informar(EstudioDePedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "informar",usuarioAccion,
				"Se informará: " + this.getClass().getSimpleName());
		
		FX_InformarEstudioDePedido fx_modif = new FX_InformarEstudioDePedido(this.getDao_EstudioDePedido(), pedido, usuarioAccion);
		
		return ejecutarFuncion(fx_modif);
	}
	
	/**
	 * Cancela un pedido  (Estado: Cancelado)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String cancelar(EstudioDePedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "cancelar",usuarioAccion,
				"Se cancelará: " + this.getClass().getSimpleName());
		
		FX_CancelarEstudioDePedido fx_modif = new FX_CancelarEstudioDePedido(this.getDao_EstudioDePedido(), pedido, usuarioAccion);
		
		return ejecutarFuncion(fx_modif);
	}
	
	/**
	 * Atiende un pedido (Estado: Atendido)
	 * 
	 * @param pedido
	 * @param usuarioAccion
	 * @return
	 */
	public String atender(EstudioDePedido_VO pedido, String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "atender",usuarioAccion,
				"Se atenderá: " + this.getClass().getSimpleName());
		
		FX_AtenderEstudioDePedido fx_modif = new FX_AtenderEstudioDePedido(this.getDao_EstudioDePedido(), pedido, usuarioAccion);
		
		return ejecutarFuncion(fx_modif);
	}
	
	/**
	 * Atiende un pedido enviado por el PACS
	 * 
	 * @param an
	 * @param status
	 * @param usuarioAccion
	 * @return
	 */
	public String atender(String an, String status,
			String usuarioAccion) {
		
		DAO_Utils.info(log, getClass().getSimpleName(), "atender",usuarioAccion,
				"Se atenderá: " + an +" que llego con un status: "+status);
		/*********************/
		
		EstudioDePedido_VO estudio = getDao_EstudioDePedido().getEstudioPorAccessionNumber(an);
		
		FX_AtenderEstudioDePedido fx_atender = new FX_AtenderEstudioDePedido(this.getDao_EstudioDePedido(), estudio, usuarioAccion);
		
		ejecutarFuncion(fx_atender);
		
		return new Gson().toJson( new RespuestaCorta(true, "recibido"));
	}

}