package ar.org.hospitalespanol.fx.core.usuarios.perfiles;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarPerfil implements I_FX {

	private DAO_Perfil dao;
	private Perfil_VO perfil;
	private String usuario;

	public FX_BuscarPerfil(DAO<Perfil_VO> dao, Perfil_VO perfil,
			String nombreUsuario) {
		setDao((DAO_Perfil) dao);
		setPerfil(perfil);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		this.getDao().resetQuery();
	
		String condicion = "";
		
		if (this.getPerfil().getIdServicio()!=null &&
				!this.getPerfil().getIdServicio().equals("")) {
			
			condicion = " WHERE " + getDao().getIdClass()
					+ ".servicio.id = :servicio ";
			getDao().getCondiciones().put("servicio",
					this.getPerfil().getIdServicio());
		}	

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
		
/*		if (this.getPerfil().getNombre()!=null &&
				!this.getPerfil().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getPerfil().getNombre().toLowerCase() + "%");
		}

		if (this.getPerfil().getCodigo()!=null &&
				!this.getPerfil().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getPerfil().getCodigo().toLowerCase() + "%");
		}

	if (this.getPerfil().getTipoRol()!=null) {

			if (condicion.equals(""))
				condicion = " WHERE " + getDao().getIdClass()
						+ ".tipoRol.id = :idRol ";
			else
				condicion += " AND " + getDao().getIdClass()
						+ ".tipoRol.id = :idRol ";

			getDao().getCondiciones().put("idRol",
					this.getPerfil().getTipoRol().getId());
		}*/
		

	}

	public Perfil_VO getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil_VO perfil) {
		this.perfil = perfil;
	}

	public DAO_Perfil getDao() {
		return dao;
	}

	public void setDao(DAO_Perfil dao) {
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

	@Override
	public Boolean listar() {
		return true;
	}
}
