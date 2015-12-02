package ar.com.builderadmin.controllers.panelDeControl.pedidos;

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
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.core.panelDeControl.pedidos.DAO_EstudioDePedidoAngra;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.historiaClinica.pedidos.PedidoFiltrado;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedidoAngra_VO;

/**
 * Componente para el manejo de las pedidos
 * 
 * @author seba garcia
 */
@Controller
public class Admin_EstudioDePedidosAngra extends
		Admin_Abstracto<EstudioDePedidoAngra_VO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<EstudioDePedidoAngra_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_EstudioDePedidoAngra dao_EstudioDePedidoAngra;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private EstudioDePedidoAngra_VO pedido;

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
	protected I_FX getFX_Buscar(EstudioDePedidoAngra_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Crear(EstudioDePedidoAngra_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(EstudioDePedidoAngra_VO pedido, String usr) {
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(EstudioDePedidoAngra_VO pedido, String usr) {
		return null;
	}

	/***************************************************************/

	public String findByAccessionNumber(String an, String usuarioAccion) {

		EstudioDePedidoAngra_VO vo = this.getDao_EstudioDePedidoAngra()
				.getByAccessionNumber(an);

		DAO_Utils.info(this.log, this.getClass().getName(), "findById",
				usuarioAccion, "Se recupera la entidad con an: " + an);

		return getGson().toJson(vo);
	}

	/***************************************************************/

	public String listarPedidosPorFiltro(PedidoFiltrado pedidoFiltrado,
			String usuarioAccion) {

		List<EstudioDePedidoAngra_VO> pedidos = this
				.getDao_EstudioDePedidoAngra().listarPedidosPorFiltro(
						pedidoFiltrado, usuarioAccion);

		JSON_Paginador pag = new JSON_Paginador(pedidos);
		if (pedidoFiltrado.getPagina() != null)
			pag.setPaginaActual(pedidoFiltrado.getPagina());

		return new Gson().toJson(new JSON_Respuesta(pag));
	}

	/***************************************************************/

	/**
	 * @return the dao_EstudioDePedido
	 */
	public DAO_EstudioDePedidoAngra getDao_EstudioDePedidoAngra() {
		return dao_EstudioDePedidoAngra;
	}

	/**
	 * @param dao_EstudioDePedido
	 *            the dao_EstudioDePedido to set
	 */
	public void setDao_EstudioDePedidos(
			DAO_EstudioDePedidoAngra dao_EstudioDePedido) {
		this.dao_EstudioDePedidoAngra = dao_EstudioDePedido;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<EstudioDePedidoAngra_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<EstudioDePedidoAngra_VO> getDao() {
		return getDao_EstudioDePedidoAngra();
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
	public EstudioDePedidoAngra_VO getEstudioDePedido() {
		return pedido;
	}

	/**
	 * @param pedido
	 *            the pedido to set
	 */
	public void setEstudioDePedido(EstudioDePedidoAngra_VO pedido) {
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
	public void setPaginador(Paginador<EstudioDePedidoAngra_VO> paginador) {
		this.paginador = paginador;
	}

}