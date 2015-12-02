package ar.com.builderadmin.fx.core.usuarios.perfiles;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_TipoDePerfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

public class FX_BuscarTipoDePerfil implements I_FX {

	private DAO_TipoDePerfil dao;
	private TipoDePerfil_VO sucursal;
	private String usuario;

	public FX_BuscarTipoDePerfil(DAO<TipoDePerfil_VO> dao, TipoDePerfil_VO sucursal,
			String nombreUsuario) {
		setDao((DAO_TipoDePerfil) dao);
		setTipoDePerfil(sucursal);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		this.getDao().resetQuery();
		
		String condicion = "";

		if (this.getTipoDePerfil().getNombre()!=null &&
				!this.getTipoDePerfil().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getTipoDePerfil().getNombre().toLowerCase() + "%");
		}

		if (this.getTipoDePerfil().getCodigo()!=null &&
				!this.getTipoDePerfil().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getTipoDePerfil().getCodigo().toLowerCase() + "%");
		}

		if (this.getTipoDePerfil().getTipoRol()!=null) {

			if (condicion.equals(""))
				condicion = " WHERE " + getDao().getIdClass()
						+ ".tipoRol.id = :idRol ";
			else
				condicion += " AND " + getDao().getIdClass()
						+ ".tipoRol.id = :idRol ";

			getDao().getCondiciones().put("idRol",
					this.getTipoDePerfil().getTipoRol().getId());
		}
		
		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public TipoDePerfil_VO getTipoDePerfil() {
		return sucursal;
	}

	public void setTipoDePerfil(TipoDePerfil_VO sucursal) {
		this.sucursal = sucursal;
	}

	public DAO_TipoDePerfil getDao() {
		return dao;
	}

	public void setDao(DAO_TipoDePerfil dao) {
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
