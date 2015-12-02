package ar.com.builderadmin.controllers.core;

import java.io.Serializable;
import java.util.ArrayList;
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
import ar.com.builderadmin.dao.core.DAO_Parametro;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.core.FX_BuscarParametro;
import ar.com.builderadmin.fx.core.FX_CrearParametro;
import ar.com.builderadmin.fx.core.FX_EliminarParametro;
import ar.com.builderadmin.fx.core.FX_ModificarParametro;
import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.vo.core.Parametro_VO;
import ar.com.builderadmin.vo.core.TipoParametro_VO;

/**
 * Componente para el manejo de las areas
 * 
 * @author seba garcia
 */
@Controller
public class Admin_Parametros extends Admin_Abstracto<Parametro_VO> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Parametro_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Parametro dao_Parametro;

	@Autowired
	private Admin_Alertas admin_Alertas;

	private Parametro_VO parametro;

	private JSON_Respuesta json_respuesta;

	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Parametro_VO area, String usr) {
		return new FX_BuscarParametro(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(Parametro_VO area, String usr) {
		return new FX_CrearParametro(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Parametro_VO area, String usr) {
		return new FX_EliminarParametro(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Parametro_VO area, String usr) {
		return new FX_ModificarParametro(getDao(), area, usr);
	}

	public String[] buscarPorPerfil(String atributoEntidad, String valorABuscar) {

		String[] resul = null;
		resul = this.buscarParametros(valorABuscar);
		return resul;
	}

	public String[] buscar(String atributoEntidad, String valorABuscar) {

		String[] resul = null;
		resul = this.buscarParametros(valorABuscar);
		return resul;
	}

	private String[] buscarParametros(String valorABuscar) {
		return armarResultado(getDao_Parametro()
				.buscarParametrosPorNombreOCodigo(valorABuscar), valorABuscar);
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return this.admin_Alertas;
	}

	@Override
	protected Paginador<Parametro_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Parametro_VO> getDao() {
		return getDao_Parametro();
	}

	/**
	 * @return the dao_Parametros
	 */
	public DAO_Parametro getDao_Parametro() {
		return dao_Parametro;
	}

	/**
	 * @param dao_Parametros
	 *            the dao_Parametros to set
	 */
	public void setDao_Parametro(DAO_Parametro dao_Parametros) {
		this.dao_Parametro = dao_Parametros;
	}

	public String listarTipos(String usuarioAccion) {
		List<TipoParametro_VO> tipos = new ArrayList<>() ;
		for (E_TipoParametro tipo : E_TipoParametro.values()) {
			tipos.add(new TipoParametro_VO(tipo));
		}
		return new Gson().toJson(tipos);
	}

	
}