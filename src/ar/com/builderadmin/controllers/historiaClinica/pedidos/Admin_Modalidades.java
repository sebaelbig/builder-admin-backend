package ar.com.builderadmin.controllers.historiaClinica.pedidos;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Modalidad;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.fx.historiaClinica.pedidos.modalidad.FX_BuscarModalidad;
import ar.com.builderadmin.fx.historiaClinica.pedidos.modalidad.FX_CrearModalidad;
import ar.com.builderadmin.fx.historiaClinica.pedidos.modalidad.FX_EliminarModalidad;
import ar.com.builderadmin.fx.historiaClinica.pedidos.modalidad.FX_ModificarModalidad;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Modalidad_VO;

/**
 * Componente para el manejo de las modalidades
 * 
 * @author Axel Collard Bovy
 */
@Controller
public class Admin_Modalidades extends Admin_Abstracto<Modalidad_VO> implements
		Serializable {

	private final Logger log = LoggerFactory.getLogger(Admin_Modalidades.class);
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private Paginador<Modalidad_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private DAO_Modalidad dao_Modalidad;
	
	@Autowired
	private Admin_Alertas admin_Alertas;

	private Modalidad_VO modalidad;

	private JSON_Respuesta json_respuesta;

	@Override
	public JSON_Paginador getJson_paginador() {
		return json_paginador;
	}

	public void setJson_paginador(JSON_Paginador json_paginador) {
		this.json_paginador = json_paginador;
	}

	@Override
	protected I_FX getFX_Buscar(Modalidad_VO area, String usr) {
		return new FX_BuscarModalidad(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Crear(Modalidad_VO area, String usr) {
		return new FX_CrearModalidad(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Eliminar(Modalidad_VO area, String usr) {
		return new FX_EliminarModalidad(getDao(), area, usr);
	}

	@Override
	protected I_FX getFX_Modificar(Modalidad_VO area, String usr) {
		return new FX_ModificarModalidad(getDao(), area, usr);
	}

	/***************************************************************/

	public String findByCodigo(String codigo, String usuarioAccion) {
		Modalidad_VO vo = this.getDao_Modalidad().findByCodigo(codigo);

		DAO_Utils.info(this.log, "Admin_Modalidades",
				"findByCodigo", usuarioAccion, "Se recupera la entidad con codigo: "
						+ codigo);

		return getGson().toJson(vo);
	}
	
	public String findById(EntityManager em,Long id) {

		Modalidad_VO vo = (Modalidad_VO) DAO_Utils.entityToValueObject(getDao().findById(id));
		
		return this.getGson().toJson(vo);
	}

	/***************************************************************/

	/**
	 * @return the dao_Modalidad
	 */
	public DAO_Modalidad getDao_Modalidad() {
		return dao_Modalidad;
	}

	/**
	 * @param dao_Modalidad
	 *            the dao_Modalidad to set
	 */
	public void setDao_Modalidads(DAO_Modalidad dao_Modalidad) {
		this.dao_Modalidad = dao_Modalidad;
	}

	@Override
	protected Paginador<Modalidad_VO> getPaginador() {
		return this.paginador;
	}

	@Override
	protected DAO<Modalidad_VO> getDao() {
		return getDao_Modalidad();
	}

	/**
	 * @return the modalidad
	 */
	public Modalidad_VO getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad
	 *            the modalidad to set
	 */
	public void setModalidad(Modalidad_VO modalidad) {
		this.modalidad = modalidad;
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
	public void setPaginador(Paginador<Modalidad_VO> paginador) {
		this.paginador = paginador;
	}
	
	@Override
	public String findById(Long idModalidad, String usuarioAccion) {
		
		//Recupero el modalidad, sino tiene firma, la intento recuperar
		Modalidad_VO vo = (Modalidad_VO) DAO_Utils.entityToValueObject(getDao().findById(idModalidad));
		
		return this.getGson().toJson(vo);
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		return admin_Alertas;
	}
	
	/**
	 * 	Recupera el modalidad
	 * 
	 * @param idModalidad
	 * @param idEstudio
	 * @return
	 */
//	private Modalidad_VO findModalidad(Long idModalidad, Long idEstudio) {
//		//Recupero el modalidad, sino tiene firma, la intento recuperar
//		Modalidad_VO modalidad = (Modalidad_VO) DAO_Utils.entityToValueObject(getDao().findById(idModalidad));
//		
//		if (modalidad.getUnEstudioPorModalidad()){
//			//Me quedo SOLO con el estudio modalidad
//			List<EstudioDeModalidad_VO> ests = new ArrayList<>();
//			for (EstudioDeModalidad_VO ep : modalidad.getEstudiosPaciente()) {
//				
//				if (ep.getId().equals(idEstudio)){
//					
//					ests.add(ep);
//					modalidad.setTexto(ep.getTexto());
//					modalidad.setEstudiosPaciente(ests);
//					
//					break;
//				}
//			}
//		}
//		return modalidad;
//	}

}