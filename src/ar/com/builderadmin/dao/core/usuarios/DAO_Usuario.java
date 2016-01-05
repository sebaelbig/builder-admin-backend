package ar.com.builderadmin.dao.core.usuarios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.DBPropertiesReader;
import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;
import ar.com.builderadmin.model.core.usuarios.perfiles.Perfil;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.FuncionHorus_VO;
import ar.com.builderadmin.vo.core.areas.Sucursal_VO;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.usuarios.UsuarioBDSimpleAdapter;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

@Service
@SuppressWarnings("unchecked")
public class DAO_Usuario extends DAO<Usuario_VO> {

	private Long idSucursal;
	private Boolean enCirculo = false;
	private final String cond = "sucu";

	public DAO_Usuario() {

		this.setQueryEncabezado("SELECT new "
				+ Usuario_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombreUsuario ");

		this.setQueryCondiciones("");
	}

	@Override
	protected Class getClazz() {
		return Usuario_VO.class;
	}

	@Override
	public String getIdClass() {
		return "usuario";
	}

	/**
	 * 
	 * En base al usuario pasado como parametro y los existentes en la BD, crea
	 * un nuevo nombre de usuario UNICO
	 * 
	 * @param em
	 * @param usuarioActual
	 * @return
	 */
	// public String obtenerNombreDeUsuario(EntityManager em,
	// Usuario_VO usuarioActual) {
	//
	// String nombre = usuarioActual.getNombres().toLowerCase()
	// .replace(' ', '_');
	// String apellido = usuarioActual.getApellido().toLowerCase()
	// .replace(' ', '_');
	// apellido = apellido.replace('\'', '_');
	//
	// int indiceFin = 1;
	// String nombreActual = nombre.substring(0, indiceFin);
	// String query = "FROM " + Usuario.class.getCanonicalName() + " "
	// + this.getIdClass() + " WHERE LOWER(" + this.getIdClass()
	// + ".nombreUsuario) = :nombreusuario ";
	// Query q = em.createQuery(query).setParameter("nombreusuario",
	// nombreActual + apellido);
	//
	// Integer nro = 0;
	//
	// if (!q.getResultList().iterator().hasNext()) {
	// q = em.createQuery(query).setParameter("nombreusuario",
	// nombreActual + nro + apellido);
	// }
	//
	// // Busco minetras haya un nombre de usuario como el que estoy queriendo
	// // crear
	// while (q.getResultList().iterator().hasNext()
	// && (indiceFin < nombre.length())) {
	// indiceFin++;
	// nombreActual = nombre.substring(0, indiceFin);
	// q = em.createQuery(query).setParameter("nombreusuario",
	// nombreActual + apellido);
	// }
	// // Se me acabaron las letras del nombre, busco con los numeros
	// if (indiceFin == nombre.length()) {
	// // Se acabaron los nombres
	// nro++;
	//
	// q = em.createQuery(query).setParameter("nombreusuario",
	// nombreActual + nro + apellido);
	// // Mientras exista el nombre de usuario
	// while (q.getResultList().iterator().hasNext()) {
	// nro++;
	// q = em.createQuery(query).setParameter("nombreusuario",
	// nombreActual + nro + apellido);
	// }
	// nombreActual += nro;
	// }
	//
	// return nombreActual + apellido;
	// }

	// public List<String> tiposDeDocumento(EntityManager em) {
	// return em.createQuery(
	// "SELECT t.nombre FROM "
	// + TipoDocumento.class.getCanonicalName()
	// + " t ORDER BY t.nombre").getResultList();
	// }
	//
	// public List<String> localidades(EntityManager em) {
	// return em.createQuery(
	// "SELECT l.nombre FROM " + Localidad.class.getCanonicalName()
	// + " l ORDER BY l.nombre").getResultList();
	// }
	//
	// public List<Rol_VO> roles_VO(EntityManager em) {
	// return em
	// .createQuery(
	// "SELECT new " + Rol_VO.class.getCanonicalName()
	// + "(r) FROM " + Rol.class.getCanonicalName()
	// + " r ORDER BY r.nombre").getResultList();
	// }

	// public List<Rol> roles(EntityManager em) {
	// return em
	// .createQuery(
	// "FROM " + Rol.class.getCanonicalName()
	// + " r ORDER BY r.nombre").getResultList();
	// }

	// public List<FuncionHorus_VO> funciones(EntityManager em) {
	// return em
	// .createQuery(
	// "SELECT new "
	// + FuncionHorus_VO.class.getCanonicalName()
	// + "(fx) FROM  "
	// + FuncionHorus.class.getCanonicalName()
	// + " fx ORDER BY fx.nombreMenu, fx.nombreSubMenu, fx.nombreAccion")
	// .getResultList();
	// }

	// public TipoDePerfil_VO getTipoPerfil(EntityManager em, String
	// rol_elegido) {
	// TipoDePerfil_VO resul = null;
	//
	// try {
	// resul = (TipoDePerfil_VO) em
	// .createQuery(
	// "SELECT new "
	// + TipoDePerfil_VO.class.getCanonicalName()
	// + "(tr) FROM "
	// + TipoDePerfil.class.getCanonicalName()
	// + " tr WHERE tr.nombre=:nombre")
	// .setParameter("nombre", rol_elegido).getSingleResult();
	// } catch (NoResultException e) {
	// e.printStackTrace();
	// }
	// return resul;
	// }

	// public FuncionHorus_VO getFuncion(EntityManager em, String fx_elegida) {
	// FuncionHorus_VO resul = null;
	//
	// try {
	// resul = (FuncionHorus_VO) em
	// .createQuery(
	// "SELECT new "
	// + FuncionHorus_VO.class.getCanonicalName()
	// + "(tr) FROM "
	// + FuncionHorus.class.getCanonicalName()
	// + " tr WHERE tr.nombreFuncion=:nombre")
	// .setParameter("nombre", fx_elegida).getSingleResult();
	// } catch (NoResultException e) {
	// e.printStackTrace();
	// }
	// return resul;
	// }

	/**
	 * Autentica los datos del usuario
	 * 
	 * 
	 * @param nombreUsuario
	 * @param pass
	 * @return El usuario, si es que los datos son correctos, sino
	 *         <span>null<span>
	 *         
	 *         
	 */
	public Usuario_VO autenticarUsuario(String nombreUsuario, String pass) {
		Usuario_VO resul = new Usuario_VO();
		try {
			
			String query = "SELECT fx_autenticar_usuario('"
					+ nombreUsuario + "','" + pass + "')";
			
			DAO_Utils.info(log, "DAO_Usuario", "autenticar", getUsuarioAccion(), "Ejecuto: \n"+query);
			Object fx_response = getEntityManager().createNativeQuery(query).getSingleResult();
			
			if (fx_response==null) 
				fx_response = "[]";
			
			String json_usuario = fx_response.toString();
			DAO_Utils.info(log, "DAO_Usuario", "autenticar", getUsuarioAccion(), "Respondió: \n"+json_usuario);
			
			List<Usuario_VO> usuarios = 
					(List<Usuario_VO>) 
						new GsonBuilder()
						.setDateFormat("yyyy-MM-dd HH:mm:ss")
						.registerTypeAdapter(Usuario_VO.class, new UsuarioBDSimpleAdapter())
						.create()
						.fromJson(json_usuario, new TypeToken<List<Usuario_VO>>() {}.getType());
			
			if (usuarios.size()==1){
				resul = usuarios.get(0);
			}else{
				resul = null;
			}
			
		} catch (NoResultException e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}

		return resul;
	}

	/**
	 * Autentica los datos del usuario
	 * 
	 * 
	 * @param em
	 * @param nombreUsuario
	 * @param pass
	 * @return El usuario, si es que los datos son correctos, sino
	 *         <span>null<span>
	 */
	public Boolean cambiarPassword(EntityManager em, String nombreUsuario,
			String pass, String passNuevo) {
		Boolean resul = false;
		try {

			resul = (Boolean) em.createNativeQuery(
					"SELECT " + DBPropertiesReader.getEsquema()
							+ ".sp_horus_set_nuevopassword('" + nombreUsuario
							+ "','" + pass + "', '" + passNuevo + "')")
					.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}

		return resul;
	}

	/*******************************************************************************************/
	/************ Recuperar contrasena con respuesta de seguridad *******************************/
	/*******************************************************************************************/
	public String recuperarPreguntaDeSeguridad(EntityManager em,
			String nombreUsuario) {
		String json_R_SetNuevoPassOlvidado = null;
		try {

			json_R_SetNuevoPassOlvidado = em
					.createNativeQuery(
							"SELECT " + DBPropertiesReader.getEsquema()
									+ ".sp_horus_get_pregdeseguridad('"
									+ nombreUsuario + "')").getSingleResult()
					.toString();

		} catch (NoResultException e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}

		return json_R_SetNuevoPassOlvidado;
	}

	public Boolean guardarContrasenaConRespuesta(EntityManager em,
			String nombreUsuario, String pass, String respSeguridad) {
		Boolean resul = false;

		try {

			resul = (Boolean) em.createNativeQuery(
					"SELECT " + DBPropertiesReader.getEsquema()
							+ ".sp_horus_set_nuevopassolvidado('"
							+ respSeguridad + "', '" + nombreUsuario + "', '"
							+ pass + "')").getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}

		return resul;
	}

	class R_SetNuevoPassOlvidado {

		private Boolean repuestaYCambioCorrecto;

		public R_SetNuevoPassOlvidado() {
		}

		public Boolean getRepuestaYCambioCorrecto() {
			return this.repuestaYCambioCorrecto;
		}

		public void setRepuestaYCambioCorrecto(Boolean repuestaYCambioCorrecto) {
			this.repuestaYCambioCorrecto = repuestaYCambioCorrecto;
		}
	}

	/*******************************************************************************************/
	/************ Setear nueva pregunta y respuesta de seguridad *******************************/
	/*******************************************************************************************/
	public Boolean guardarNuevaPreguntaYRespuesta(EntityManager em,
			String nombreUsuario, String pass, String pregSeguridad,
			String respSeguridad) {

		Boolean resul = false;

		try {

			resul = (Boolean) em.createNativeQuery(
					"SELECT " + DBPropertiesReader.getEsquema()
							+ ".sp_horus_set_pregyrespdesegur('"
							+ nombreUsuario + "', '" + pass + "', '"
							+ pregSeguridad + "', '" + respSeguridad + "')")
					.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}

		return resul;
	}

	/*******************************************************************************************/
	/******************************** Setear nuevo mail ****************************************/
	/*******************************************************************************************/
	public Boolean setNuevoEmail(EntityManager em, String nombreUsuario,
			String pass, String email) {
		Boolean resul = false;

		try {

			resul = (Boolean) em.createNativeQuery(
					"SELECT " + DBPropertiesReader.getEsquema()
							+ ".sp_horus_set_datosusuario('" + nombreUsuario
							+ "', '" + pass + "', '" + email + "')")
					.getSingleResult();

		} catch (NoResultException e) {
			e.printStackTrace();
			this.log.error(e.getMessage());
		}

		return resul;
	}

	/**
	 * Devuelve las funciones que tiene asignado el usuario pasado como
	 * parametro
	 * 
	 * @param em
	 * @param Usuario
	 * @return
	 */
	public List<FuncionHorus> buscarFuncionesDeUsuario(EntityManager em,
			Usuario_VO usr) {
		List<FuncionHorus> fxs = null;
		try {

			String query = "SELECT fx FROM " + Perfil.class.getCanonicalName()
					+ " p " + "	INNER JOIN p.funciones fx "
					+ " WHERE p.rol.usuario.id = :idUsuario ";

			fxs = em.createQuery(query).setParameter("idUsuario", usr.getId())
					.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fxs;
	}

	/**
	 * Devuelve las funciones VO que tiene asignado el usuario al rol pasado
	 * como parametro
	 * 
	 * @param em
	 * @param Usuario
	 * @return
	 */
	public List<FuncionHorus_VO> buscarFuncionesVODeUsuario(EntityManager em,
			Rol_VO r) {
		List<FuncionHorus_VO> fxs = null;
		try {

			String query = "SELECT new "
					+ FuncionHorus_VO.class.getCanonicalName() + "(fx) FROM "
					+ Perfil.class.getCanonicalName() + " p "
					+ "	INNER JOIN p.funciones fx "
					+ " WHERE p.rol.id = :idRol ";

			fxs = em.createQuery(query).setParameter("idRol", r.getId())
					.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fxs;
	}

	public Usuario_VO buscarUsuarioPorDocumento(EntityManager em,
			Usuario_VO usr_vo) throws NoResultException {

		String query = "SELECT new " + Usuario_VO.class.getCanonicalName()
				+ "(u) FROM " + Usuario_VO.class.getCanonicalName() + " u "
				+ "	WHERE nro_documento = :nroDoc "
				+ "		AND tipo_documento = :tipoDoc " +
				"	AND borrado = false ";

		return (Usuario_VO) em.createQuery(query)
				.setParameter("nroDoc", usr_vo.getNroDocumento())
				.setParameter("tipoDoc", usr_vo.getTipoDocumento())
				.getSingleResult();
	}

	public Usuario_VO buscarUsuarioPorDocumento(String tipoDoc, String nroDoc) {

		setQueryCondiciones("	WHERE " + getIdClass()
				+ ".nroDocumento = :nroDoc " + "	AND " + getIdClass()
				+ ".tipoDocumento = :tipoDoc ");

		getCondiciones().put("nroDoc", nroDoc);
		getCondiciones().put("tipoDoc", tipoDoc);

		List<Usuario_VO> elementos = this.listarTodo();
		this.resetQuery();

		return (elementos.size() > 0) ? elementos.get(0) : null;

	}

	/**
	 * Busca un usuario por el nombre de usuario
	 * 
	 * @param username
	 * @return
	 */
	public Usuario_VO buscarPorNombreUsuario(String username) {

		String query = "SELECT new " + Usuario_VO.class.getCanonicalName()
				+ "(" + this.getIdClass() + ") FROM "
				+ this.getClazz().getCanonicalName() + " " + this.getIdClass()
				+ " " + "	WHERE " + this.getIdClass()
				+ ".nombreUsuario = :user AND " + this.getIdClass()
				+ ".borrado = false ";

		Usuario_VO resul = null;

		try {

			resul = (Usuario_VO) getEntityManager().createQuery(query)
					.setParameter("user", username).getSingleResult();

		} catch (NoResultException noResul) {
		}

		return resul;
	}

	public Rol_VO buscarRolPorID(EntityManager em, Long idRol) {
		Rol r = em.find(Rol.class, idRol);

		if (r != null) {
			return r.toValueObject();
		}

		return null;
	}

	public Set<Sucursal_VO> buscarSucursalesDePerfiles(EntityManager em,
			Rol_VO r) {
		// return this.buscarSucursalesDePerfiles_he(em, r);
		Set<Sucursal_VO> sucus = new HashSet<Sucursal_VO>();

		try {
			String query = "SELECT DISTINCT new "
					+ Sucursal_VO.class.getCanonicalName()
					+ "(p.servicio.area.sucursal) FROM "
					+ Perfil.class.getCanonicalName() + " p "
					+ " WHERE p.rol.id = :idRol ";

			List<Sucursal_VO> ss = em.createQuery(query)
					.setParameter("idRol", r.getId()).getResultList();

			for (Sucursal_VO sucursal_VO : ss) {
				sucus.add(sucursal_VO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sucus;
	}

	/************************************************************************************/
	/************************************************************************************/
	/*
	 * *********************** HOSPITAL ESPA�OL
	 * ***********************************
	 */
	/************************************************************************************/
	/************************************************************************************/

	public Set<Sucursal_VO> buscarSucursalesDePerfiles_he(EntityManager em,
			Rol_VO r) {

		Set<Sucursal_VO> sucus = new HashSet<Sucursal_VO>();

		Sucursal_VO sucu = new Sucursal_VO();
		sucu.setNombre("Hospital español");
		sucu.setId(1L);

		sucus.add(sucu);
		return sucus;
	}

	public Long getIdSucursal() {
		return this.idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Boolean getEnCirculo() {
		return this.enCirculo;
	}

	public void setEnCirculo(Boolean enCirculo) {
		this.enCirculo = enCirculo;
	}

	/**
	 * 
	 * En base al usuario pasado como parametro y los existentes en la BD, crea
	 * un nuevo nombre de usuario UNICO
	 * 
	 * @param em
	 * @param usuarioActual
	 * @return
	 */
	public String obtenerNombreDeUsuario(Usuario_VO usuarioActual) {

		String nombre = (usuarioActual.getNombres() == null) ? " "
				: usuarioActual.getNombres().toLowerCase().replace(' ', '_');
		String apellido = usuarioActual.getApellido().toLowerCase()
				.replace(' ', '_');
		apellido = apellido.replace('\'', '_');

		int indiceFin = 1;
		String nombreActual = nombre.substring(0, indiceFin);
		String query = "FROM " + Usuario_VO.class.getCanonicalName() + " "
				+ this.getIdClass() + " WHERE LOWER(" + this.getIdClass()
				+ ".nombreUsuario) = :nombreusuario ";
		Query q = getEntityManager().createQuery(query).setParameter(
				"nombreusuario", nombreActual + apellido);

		Integer nro = 0;

		if (!q.getResultList().iterator().hasNext()) {
			q = getEntityManager().createQuery(query).setParameter(
					"nombreusuario", nombreActual + nro + apellido);
		}

		// Busco minetras haya un nombre de usuario como el que estoy queriendo
		// crear
		while (q.getResultList().iterator().hasNext()
				&& (indiceFin < nombre.length())) {
			indiceFin++;
			nombreActual = nombre.substring(0, indiceFin);
			q = getEntityManager().createQuery(query).setParameter(
					"nombreusuario", nombreActual + apellido);
		}
		// Se me acabaron las letras del nombre, busco con los numeros
		if (indiceFin == nombre.length()) {
			// Se acabaron los nombres
			nro++;

			q = getEntityManager().createQuery(query).setParameter(
					"nombreusuario", nombreActual + nro + apellido);
			// Mientras exista el nombre de usuario
			while (q.getResultList().iterator().hasNext()) {
				nro++;
				q = getEntityManager().createQuery(query).setParameter(
						"nombreusuario", nombreActual + nro + apellido);
			}
			nombreActual += nro;
		}

		return nombreActual + apellido;
	}

	public List<Servicio_VO> listarServiciosDeUsuario(String usuario) {

		/*
		 * String query = "SELECT new " + Servicio_VO.class.getCanonicalName() +
		 * "( p.servicio ) " + " FROM "+this.getClazz().getCanonicalName() +
		 * " "+this.getIdClass() + " INNER JOIN " + " "+this.getIdClass() +
		 * ".roles rol INNER JOIN rol.perfiles p " +
		 * "	WHERE "+this.getIdClass()+
		 * ".nombreUsuario = :user AND p.borrado = false";
		 */

		String query = "SELECT DISTINCT new "
				+ Servicio_VO.class.getCanonicalName() + "( p.servicio ) "
				+ " FROM " + this.getClazz().getCanonicalName() + " "
				+ this.getIdClass() + " INNER JOIN " + " " + this.getIdClass()
				+ ".roles rol INNER JOIN rol.perfiles p "
				+ "	WHERE rol.id=p.rol.id AND " + this.getIdClass()
				+ ".nombreUsuario = :user AND p.borrado = false";

		List<Servicio_VO> ss = getEntityManager().createQuery(query)
				.setParameter("user", usuario).getResultList();

		return ss;

	}

	public String getDatosPacienteWeb(String nombreUsuario){
		/*Llamo al SP que me devuelve tipo+nroDni del usuario web*/
		String resul=null;
		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_datospacienteweb(?,?)}");

				pstmt.setString(1, nombreUsuario);
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_datospacienteweb(" + nombreUsuario
								+ ")", new Object[0]);
				pstmt.execute();
				String sp_horus_get_datosPaciente = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_Usuario", "getDatosDePacienteWeb", getUsuarioAccion(),"Se llama a sp_horus_get_datospacienteweb y se obtiene: "+sp_horus_get_datosPaciente);
				resul=sp_horus_get_datosPaciente;

				/*resul = new GsonBuilder()
						.registerTypeAdapter(Pedido_VO.class, new PedidoSimpleAdapter())
						.create()
						.fromJson(sp_horus_get_detallepedido, Pedido_VO.class);*/
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}

		return resul;
		
	}

}