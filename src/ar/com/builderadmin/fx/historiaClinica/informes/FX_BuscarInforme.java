package ar.com.builderadmin.fx.historiaClinica.informes;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.historiaClinica.DAO_Informes;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.informes.Informe_VO;

public class FX_BuscarInforme implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Informes dao;
	private Informe_VO area;
	private String usuario;

	public FX_BuscarInforme(DAO<Informe_VO> dao, Informe_VO area, String nombreUsuario) {
		setDao((DAO_Informes) dao);
		setInforme(area);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";

		if (this.getInforme().getNumero()!=null &&
				!this.getInforme().getNumero().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".numero) like :nro ";
			getDao().getCondiciones().put("nro",
					this.getInforme().getNumero().toLowerCase() + "%");
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Informe_VO getInforme() {
		return area;
	}

	public void setInforme(Informe_VO area) {
		this.area = area;
	}

	public DAO_Informes getDao() {
		return dao;
	}

	public void setDao(DAO_Informes dao) {
		this.dao = dao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
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
