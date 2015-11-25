package ar.org.hospitalespanol.fx.historiaClinica.pedidos.modalidad;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.DAO_Modalidad;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Modalidad_VO;

public class FX_BuscarModalidad implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Modalidad dao;
	private Modalidad_VO modalidad;
	private String usuario;

	public FX_BuscarModalidad(DAO dao, Modalidad_VO modalidad_VO,
			String nombreUsuario) {
		this.setDao((DAO_Modalidad) dao);
		this.setModalidad(modalidad_VO);
		this.setUsuario(nombreUsuario);
	}
	
	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {
		String condicion = "";

		if (this.getModalidad().getDescripcion()!=null &&
				!this.getModalidad().getDescripcion().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".descripcion) like :descripcion ";
			getDao().getCondiciones().put("descripcion",
					this.getModalidad().getDescripcion().toLowerCase() + "%");
		}

		if (this.getModalidad().getCodigo()!=null &&
				!this.getModalidad().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getModalidad().getCodigo().toLowerCase() + "%");
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public DAO_Modalidad getDao() {
		return dao;
	}

	public void setDao(DAO_Modalidad dao) {
		this.dao = dao;
	}

	public Modalidad_VO getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad_VO modalidad_VO) {
		this.modalidad = modalidad_VO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
//		return perfil.tieneModalidad();
		return true;
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		return new java.util.HashMap<String, Object>();
	}
}
