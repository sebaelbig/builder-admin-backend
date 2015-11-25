package ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.DAO_Parametro;
import ar.org.hospitalespanol.dao.core.datosSalud.prestaciones.DAO_TipoPrestacionHorus;
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.datosSalud.prestaciones.FX_CrearTipoPrestacionHorus;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.org.hospitalespanol.utils.Angra_Service;
import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_CrearEstudioDePedido implements I_FX {
	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearEstudioDePedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_EstudioDePedido dao;
	private EstudioDePedido_VO estudioDePedido;
	private String usuario;

	public FX_CrearEstudioDePedido(DAO dao, EstudioDePedido_VO tipo,
			String usuario) {
		setDao((DAO_EstudioDePedido) dao);
		setUsuario(usuario);
		setEstudioDePedido(tipo);
	}

	private boolean validar() {
		
		getDao().resetQuery();

		if (getEstudioDePedido().getIdEstudio() != null) {
			getDao().setQueryCondiciones(
					" WHERE " + getDao().getIdClass()
							+ ".estudio.id = :idEstudio ");

			getDao().getCondiciones().put("idEstudio",
					getEstudioDePedido().getIdEstudio());
		}

		if (getEstudioDePedido().getPedido() != null
				&& getEstudioDePedido().getPedido().getId() != null) {
			getDao().setQueryCondiciones( getDao().getQueryCondiciones()+
					" AND " + getDao().getIdClass()
							+ ".pedido.id = :idPedido ");

			getDao().getCondiciones().put("idPedido",
					getEstudioDePedido().getPedido().getId());
		}
		
		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getEstudioDePedido().getPedido() == null) {
			ok = false;
		}

		getDao().resetQuery();

		return ok;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		//Me fijo si existe el estudio que se quiere hacer, sino lo creo
		DAO_TipoPrestacionHorus daoEstudio = getDao().getInstance(DAO_TipoPrestacionHorus.class);
		List<TipoPrestacionHorus_VO> estudios = daoEstudio.buscarPorNombre(getEstudioDePedido().getNombreEstudio());
		if (estudios.isEmpty()){
			//No esta persistido el estudio, lo creo
			TipoPrestacionHorus_VO est = new TipoPrestacionHorus_VO(null, getEstudioDePedido().getCodigoEstudio(), getEstudioDePedido().getNombreEstudio());
			FX_CrearTipoPrestacionHorus fx_crearTipoPrestacion = new FX_CrearTipoPrestacionHorus(daoEstudio, est, getUsuario());
			fx_crearTipoPrestacion.ejecutar(adminAlertas);
			
			getEstudioDePedido().setIdEstudio(est.getId());
		}else{
			getEstudioDePedido().setIdEstudio(estudios.get(0).getId());
		}

		if (validar()) {

			try {

				getEstudioDePedido().setEstado(EstudioDePedido.EN_ATENCION);
				
				//Una vez que se creó correctamente el estudio y el pedido, se arma la URL del Visor
				DAO_Parametro daoParam = getDao().getInstance(DAO_Parametro.class);
				getEstudioDePedido().setUrlVisor( Angra_Service.crearUrlVisor(getEstudioDePedido(), daoParam) );
				
				// Si el estudio de pedido no tiene ID entonces
				getDao().guardar(getEstudioDePedido());
				getDao().resetQuery();

				String detalle = "El Estudio de pedido "
						+ getEstudioDePedido().getPedido().getNumero() + " - "
						+ getEstudioDePedido().getNombreEstudio()
						+ " se creó correctamente";

				// Se genera y persiste el alerta correspondiente a la funcion
				// FX
				Alerta al = new Alerta(getUsuario(), new Date(),
						getEstudioDePedido().getId(), this.getClass().getCanonicalName(),
						detalle, E_Priority.BAJA);

				adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
						this);

				DAO_Utils.info(logger, "FX_CrearEstudioDePedido", "ejecutar", getUsuario(), detalle);

				this.getRespuesta().setPaginador(
						JSON_Paginador.solo(getEstudioDePedido()));
				this.getRespuesta().setMensaje(detalle);
				this.getRespuesta().setOk(true);

			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje(
						"Ocurrió un error en la grabación");

				e.printStackTrace();

				this.getRespuesta()
						.setMensaje(
								"Error al guardar el pedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: "
										+ ""
										+ " )");
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje(
					"Ya existe un estudio para ese pedido y ese estudio.");

		}

		return getRespuesta();
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO paramPerfil_VO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El estudio de pedido "
				+ getEstudioDePedido().getPedido().getNumero() + " - "
				+ getEstudioDePedido().getNombreEstudio() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the dao
	 */
	public DAO_EstudioDePedido getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_EstudioDePedido dao) {
		this.dao = dao;
	}

	/**
	 * @return the estudioDePedido
	 */
	public EstudioDePedido_VO getEstudioDePedido() {
		return estudioDePedido;
	}

	/**
	 * @param estudioDePedido
	 *            the estudioDePedido to set
	 */
	public void setEstudioDePedido(EstudioDePedido_VO estudioDePedido) {
		this.estudioDePedido = estudioDePedido;
	}

}
