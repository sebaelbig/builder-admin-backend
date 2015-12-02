package ar.com.builderadmin.fx.core.usuarios.perfiles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.model.E_Priority;
import ar.com.builderadmin.model.alerta.Alerta;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

public class FX_ActualizarFuncionesTipoPerfil implements I_FX {
	
	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_ActualizarFuncionesTipoPerfil.class);
	
	private DAO_Perfil dao;
	private TipoDePerfil_VO tipo;
	private Perfil_VO perfil;
	private String usuario;
	private EntityManager em;

	public FX_ActualizarFuncionesTipoPerfil() {
	}

	public FX_ActualizarFuncionesTipoPerfil(DAO_Perfil dao, TipoDePerfil_VO tipo, 
			String usuario) {
		setDao(dao);
		setTipo(tipo);
		setUsuario(usuario);
		setEm(getDao().getEntityManager());
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {
		JSON_Respuesta respuesta = new JSON_Respuesta();
		try {
				
			getDao().actualizarFuncionesDePerfil(getTipo());
			
			// Se genera y persiste el alerta correspondiente a la funcion
			// FX
			Alerta al = new Alerta(getUsuario(), new Date(), getTipo()
					.getId(), this.getClass().getCanonicalName(), "Se actualizaron las funciones asignadas a todos los perfiles de tipo: "+getTipo().getNombre(),
					E_Priority.MEDIA);

			adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
					this);
			
		} catch (Exception e) {
			e.printStackTrace();

			respuesta.setOk(Boolean.valueOf(false));
			respuesta
					.setMensaje("Ocurrió un error en la grabación dtipo de rol");
		}
		
		return respuesta;
	}

	
	public DAO_Perfil getDao() {
		return this.dao;
	}

	public void setDao(DAO_Perfil dao) {
		this.dao = dao;
	}

	public TipoDePerfil_VO getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoDePerfil_VO tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
	}

	/**
	 * @return the perfil
	 */
	public Perfil_VO getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Perfil_VO perfil) {
		this.perfil = perfil;
	}


	@Override
	public Boolean listar() {
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "Se actualizaron las funciones de todos los perfiles de tipo: "+getTipo().getNombre());

		return resp;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}