package ar.com.builderadmin.dao.core.usuarios.roles;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.DBPropertiesReader;
import ar.com.builderadmin.ldap.modelo.RolDeUsuarioHE;
import ar.com.builderadmin.vo.core.usuarios.roles.RolHESimpleAdapter;
import ar.com.builderadmin.vo.core.usuarios.roles.RolHE_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

@Service
public class DAO_RolHE extends DAO<RolHE_VO> {

	private RolHE_VO rol;

	public DAO_RolHE() {
		setQueryHQL("FROM " + getClazz().getCanonicalName() + " "
				+ getIdClass() + " ");
	}

	@Override
	public Class getClazz() {
		return RolHE_VO.class;
	}

	@Override
	public String getIdClass() {
		return "templatePrivado";
	}

	public R_CrearRol guardar(EntityManager em, TipoDeRol_VO rol) {
		R_CrearRol resul = new R_CrearRol();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
				 .prepareCall("{call sp_horus_set_rol(?,?,?,?,?,?,?,?)}");

				Object[] s = new Object[7];

				// id, rol, usuario, estado
				s[0] = rol.getId_rol();
				s[1] = rol.getNombre();
				s[2] = rol.getLogin();
				s[3] = rol.getEstado();
				s[4] = rol.getPrioridad();
				s[5] = rol.getCodigoID_x_default();
				s[6] = rol.getSitio();

				pstmt.setString(1, (String) s[0]);
				pstmt.setString(2, ((String) s[1]));
				pstmt.setString(3, ((String) s[2]));
				pstmt.setString(4, (String) s[3]);
				pstmt.setString(5, (String) s[4]);
				pstmt.setString(6, (String) s[5]);
				pstmt.setString(7, (String) s[6]);

				pstmt.setString(8, "");
				pstmt.registerOutParameter(8, -1);

				this.log.info(
						"Llamando: 4.2.3	sp_horus_set_rol (#0,#1,#2,#3,#4,#5,#6)",
						new Object[] { s[0], s[1], s[2], s[3], s[4], s[5], s[6] });
				System.out.println("Llamando: 4.2.3	sp_horus_set_rol (" + s[0]
						+ "," + s[1] + "," + s[2] + "," + s[3] + "," + s[4] + "," + s[5]+ "," + s[6] + ")");

				pstmt.execute();
				String sp_horus_set_rol = pstmt.getString(8);

				this.log.info("Resultado obtenido: " + sp_horus_set_rol, new Object[0]);
				System.out.println(sp_horus_set_rol);

				resul = new Gson().fromJson(sp_horus_set_rol, R_CrearRol.class);
			}

		} catch (SQLException sql_e) {
			sql_e.printStackTrace();

			if (sql_e.getMessage().indexOf("duplicate key") >= 0) {
				//
				resul.setMensaje("ERROR, Ya existe un tipo con ese ID");
			}

			cerrarConexion(con);

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

	public R_GetRol getRol(DAO_RolHE dao, RolHE_VO rol) {
		R_GetRol resul = new R_GetRol();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_rol(?,?)}");

				pstmt.setString(1, rol.getId_rol());

				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info(
						"Llamando: 4.2.2	sp_horus_get_rol(" + rol.getId_rol()
								+ ")", new Object[0]);
				pstmt.execute();
				String sp_horus_get_rol = pstmt.getString(2);

				this.log.info("Resultado obtenido: " + sp_horus_get_rol,
						new Object[0]);
				System.out.println("Resultado obtenido: " + sp_horus_get_rol);

				resul = new GsonBuilder()
						.registerTypeAdapter(RolHE_VO.class,
								new RolHESimpleAdapter()).create()
						.fromJson(sp_horus_get_rol, R_GetRol.class);
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

	public R_GetRol getRoles(RolHE_VO rol) {

		R_GetRol resul = new R_GetRol();

		//Borrar
//		List<RolHE_VO> roles = new ArrayList<>();
//		
//		RolHE_VO rol1 = new RolHE_VO();
//		
//		rol1.setEstado("ACTIVO");
//		rol1.setFechaModifico("21/08/2014 11:00");
//		rol1.setId("EMP");
//		rol1.setId_rol("EMP");
//		rol1.setLogin("sebastianga");
//		rol1.setRol("EMPLEADO");
//		rol1.setPrioridad("3");
//		roles.add(rol1);
//		
//		rol1 = new RolHE_VO();
//		
//		rol1.setEstado("ACTIVO");
//		rol1.setFechaModifico("21/08/2014 11:00");
//		rol1.setId("PROF");
//		rol1.setId_rol("PROF");
//		rol1.setLogin("sebastianga");
//		rol1.setRol("PROFESIONAL");
//		rol1.setPrioridad("1");
//		roles.add(rol1);
//		
//			
//		resul.setOk(true);
//		resul.setRoles(roles);
		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_roles(?,?)}");

				if (rol.getRol() == null) {
					pstmt.setString(1, "");
				} else {
					pstmt.setString(1, rol.getRol());
				}

				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info(
						"Llamando: 4.2.1	sp_horus_get_roles(" + rol.getId_rol()
								+ ")", new Object[0]);
				pstmt.execute();
				String sp_horus_get_roles = pstmt.getString(2);

				this.log.info("Resultado obtenido: " + sp_horus_get_roles,
						new Object[0]);
				System.out.println("Resultado obtenido: " + sp_horus_get_roles);

				resul = new GsonBuilder()
						.registerTypeAdapter(RolHE_VO.class,
								new RolHESimpleAdapter()).create()
						.fromJson(sp_horus_get_roles, R_GetRol.class);
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

	public class R_CrearRol {

		private String mensaje;
		private Boolean ok;

		public R_CrearRol() {
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje
		 *            the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok
		 *            the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

	}

	public class R_GetRol {

		private String mensaje;
		private Boolean ok;

		private List<RolHE_VO> roles = new ArrayList<>();

		public R_GetRol() {
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje
		 *            the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok
		 *            the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

		/**
		 * @return the roles
		 */
		public List<RolHE_VO> getRoles() {
			return roles;
		}

		/**
		 * @param roles
		 *            the roles to set
		 */
		public void setRoles(List<RolHE_VO> roles) {
			this.roles = roles;
		}

	}

	/**
	 * @return the rol
	 */
	public RolHE_VO getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(RolHE_VO rol) {
		this.rol = rol;
	}

	public R_GetRolesDeUsuario getRolesDeUsuario(DAO_RolHE dao,
			String usuarioLDAP) {
		
		R_GetRolesDeUsuario resul = new R_GetRolesDeUsuario();
		
		
		//Borrar a partir de aca
////		resul.setOk(true);
////		
////		List<RolDeUsuarioHE> roles = new ArrayList<>();
////		RolDeUsuarioHE rol = new RolDeUsuarioHE();
////		
////		rol.setCodigo_tipo_id("LEG");		
////		rol.setEstado("ACTIVO");
////		rol.setFecha("23/08/2014 11:00");
////		rol.setId_rol("EMP");
////		rol.setIdhe("11222");
////		rol.setLogin("sebastianga");
////		rol.setRol("EMPLEADO");
////		rol.setUsuario("sebastianga");
////		
////		roles.add(rol);
////		
////		resul.setRoles(roles );
		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_rolesDeUsuario(?,?)}");

				pstmt.setString(1, usuarioLDAP);
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info(
						"Llamando: 4.1.1 sp_horus_get_rolesDeUsuario(" + usuarioLDAP
								+ ")", new Object[0]);
				System.out.println("Llamando: 4.1.1 sp_horus_get_rolesDeUsuario(" + usuarioLDAP+ ")");
				
				pstmt.execute();
				String sp_horus_get_rolesDeUsuario = pstmt.getString(2);

				this.log.info("Resultado obtenido: " + sp_horus_get_rolesDeUsuario,
						new Object[0]);
				System.out.println("Resultado obtenido: " + sp_horus_get_rolesDeUsuario);

				resul = new Gson()
						.fromJson(sp_horus_get_rolesDeUsuario, R_GetRolesDeUsuario.class);
				
				for (RolDeUsuarioHE rolUsr : resul.getRoles()) {
					rolUsr.setUsuario(usuarioLDAP);
				}
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (SQLException eNotFound) {
			//Stored procedure 'sp_horus_get_rolesDeUsuario' not found
			
			if (eNotFound.getMessage().indexOf("Stored procedure 'sp_horus_") >= 0 && eNotFound.getMessage().indexOf("not found") >= 0) {
				//No encontro el SP, lo busco con la conexion secundaria
				resul.setMensaje("ERROR, No encontro el SP en la BD: "+dbProperties.getProperty(DBPropertiesReader.SCHEMA_POSTGRES));
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}
		
		return resul;
	}

	
	public R_SetRolDeUsuario setRolDeUsuario(RolDeUsuarioHE rol){
		
		R_SetRolDeUsuario resul = new R_SetRolDeUsuario();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_rolDeUsuario(?,?,?,?,?,?,?)}");
				
				Object[] s = new Object[7];
				
				// id, rol, usuario, estado
				s[0] = rol.getUsuario();
				s[1] = rol.getId_rol();
				s[2] = rol.getCodigo_tipo_id();
				s[3] = rol.getIdhe();
				s[4] = rol.getLogin();
				s[5] = rol.getEstado();

				pstmt.setString(1, (String) s[0]);
				pstmt.setString(2, (String) s[1]);
				pstmt.setString(3, (String) s[2]);
				pstmt.setString(4, (String) s[3]);
				pstmt.setString(5, (String) s[4]);
				pstmt.setString(6, (String) s[5]);

				pstmt.setString(7, "");
				pstmt.registerOutParameter(7, -1);

				String detalle = "Llamando: 4.2.3	sp_horus_set_rolDeUsuario (" + s[0]
						+ "," + s[1] + "," + s[2] + "," + s[3] + "," + s[4]+ "," + s[5] + ")";
				DAO_Utils.info(log, "DAO_RolHE", "setRolDeUsuario",getUsuarioAccion(), detalle);
				
				System.out.println();
				
				pstmt.execute();
				String sp_horus_get_rolesDeUsuario = pstmt.getString(7);

				this.log.info("Resultado obtenido: " + sp_horus_get_rolesDeUsuario,
						new Object[0]);
				System.out.println("Resultado obtenido: " + sp_horus_get_rolesDeUsuario);

				resul = new Gson()
//				resul = new GsonBuilder()
//						.registerTypeAdapter(RolHE_VO.class,
//								new RolHESimpleAdapter()).create()
						.fromJson(sp_horus_get_rolesDeUsuario, R_SetRolDeUsuario.class);
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
	
	

	public class R_SetRolDeUsuario {

		private String mensaje;
		private Boolean ok;
		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}
		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}
		/**
		 * @param ok the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}
		
	}
	
	public class R_GetRolesDeUsuario {

		private String mensaje;
		private Boolean ok;

		private List<RolDeUsuarioHE> roles = new ArrayList<>();

		public R_GetRolesDeUsuario() {
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje
		 *            the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok
		 *            the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

		/**
		 * @return the roles
		 */
		public List<RolDeUsuarioHE> getRoles() {
			return roles;
		}

		/**
		 * @param roles
		 *            the roles to set
		 */
		public void setRoles(List<RolDeUsuarioHE> roles) {
			this.roles = roles;
		}

	}

}