package ar.org.hospitalespanol.fx.historiaClinica.informes;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.historiaClinica.DAO_Informes;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.historiaClinica.informes.Informe_VO;

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
