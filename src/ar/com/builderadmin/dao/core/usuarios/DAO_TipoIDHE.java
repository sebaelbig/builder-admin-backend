package ar.com.builderadmin.dao.core.usuarios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.usuarios.roles.TipoIDHE;
import ar.com.builderadmin.vo.core.usuarios.TipoIDHE_VO;

@Service
public class DAO_TipoIDHE extends
		DAO<TipoIDHE_VO> {

	private TipoIDHE_VO tipo;
			
	public DAO_TipoIDHE() {
		this.setQueryEncabezado("SELECT new "
				+ TipoIDHE_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".tipoID ");

		this.setQueryCondiciones("");
	}

	@Override
	protected Class getClazz() {
		return TipoIDHE.class;
	}

	@Override
	public String getIdClass() {
		return "tipoID";
	}

	public R_CrearTipoDeID guardar(TipoIDHE_VO tipo) {
		R_CrearTipoDeID resul = new R_CrearTipoDeID();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_tipoDeID(?,?,?,?,?)}");

				Object[] s = new Object[5];

				//id, rol, usuario, estado
				s[0] = tipo.getId_tipoId();
				s[1] = tipo.getTipoID();
				s[2] = tipo.getLogin();
				s[3] = tipo.getEstado();

				pstmt.setString(1, (String) s[0]);
				pstmt.setString(2, ((String) s[1]));
				pstmt.setString(3, ((String) s[2]));
				pstmt.setString(4, (String) s[3]);

				pstmt.setString(5, "");
				pstmt.registerOutParameter(5, -1);

				this.log.info(
						"Llamando: 4.3.3	sp_horus_set_tipoDeID (#0,#1,#2,#3)",
						new Object[] { s[0], s[1], s[2], s[3] });
				System.out.println("Llamando: 4.3.3	sp_horus_set_tipoDeID ("+s[0]+","+s[1]+","+s[2]+","+s[3]+")");
				pstmt.execute();
				String sp_horus_set_tipoDeID = pstmt.getString(5);

				this.log.info("Resultado obtenido: " + sp_horus_set_tipoDeID,
						new Object[0]);
				System.out.println(sp_horus_set_tipoDeID);
				
				resul = new Gson().fromJson(
						sp_horus_set_tipoDeID, R_CrearTipoDeID.class);
			}
		} catch (SQLException sql_e) {
			sql_e.printStackTrace();
			
			if (sql_e.getMessage().indexOf("duplicate key") >= 0){
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
	
//	public R_GetTipoDeID getTipoDeID(DAO_TipoIDHE dao, TipoIDHE_VO tipoDeID) {
//		R_GetTipoDeID resul = new R_GetTipoDeID();
//
//		Connection con = getConexionHE();
//		try {
//			if (con != null) {
//				CallableStatement pstmt = con
//						.prepareCall("{call sp_horus_get_tipoDeID(?,?)}");
//
//				pstmt.setString(1, tipoDeID.getId_tipoId());
//
//				pstmt.registerOutParameter(2, -1);
//				pstmt.setString(2, "");
//
//				this.log.info(
//						"Llamando: 4.3.2	sp_horus_get_tipoDeID("
//								+ tipoDeID.getId_tipoId() + ")", new Object[0]);
//				pstmt.execute();
//				String sp_horus_get_tipoDeID = pstmt.getString(2);
//
//				this.log.info("Resultado obtenido: "
//						+ sp_horus_get_tipoDeID, new Object[0]);
//
//				resul = new GsonBuilder()
//						.registerTypeAdapter(TipoIDHE_VO.class,
//								new TipoIDHESimpleAdapter())
//						.create()
//						.fromJson(sp_horus_get_tipoDeID,
//								R_GetTipoDeID.class);
//			}
//		} catch (NoResultException e) {
//			e.printStackTrace();
//			cerrarConexion(con);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			cerrarConexion(con);
//		} finally {
//			cerrarConexion(con);
//		}
//		return resul;
//	}
	
//	public R_GetTipoDeID getTipoDeIDes(TipoIDHE_VO tipoDeID) {
//		
//		R_GetTipoDeID resul = new R_GetTipoDeID();
		
		//Borrar
//		List<TipoIDHE_VO> ts = new ArrayList<>();
//		
//		TipoIDHE_VO tipo = new TipoIDHE_VO();
//		
//		tipo.setEstado("ACTIVO");
//		tipo.setFechaModifico("21/08/2014 11:00");
//		tipo.setId("LEG");
//		tipo.setId_tipoId("LEG");
//		tipo.setTipoID("LEGAJO");
//		tipo.setUsuarioModifico("sebastianga");
//		ts.add(tipo);
//		
//		tipo = new TipoIDHE_VO();
//		
//		tipo.setEstado("ACTIVO");
//		tipo.setFechaModifico("21/08/2014 11:00");
//		tipo.setId("MAT");
//		tipo.setId_tipoId("MAT");
//		tipo.setTipoID("MATRICULA");
//		tipo.setUsuarioModifico("sebastianga");
//		ts.add(tipo);
//		
//			
//		resul.setOk(true);
//		resul.setTipoDeIDs(ts);
//		Connection con = getConexionHE();
//		try {
//			if (con != null) {
//				CallableStatement pstmt = con
//						.prepareCall("{call sp_horus_get_tiposDeID(?,?)}");
//
//				if (tipoDeID.getTipoID() == null) {
//					pstmt.setString(1, "");
//				} else {
//					pstmt.setString(1, tipoDeID.getTipoID());
//				}
//
//				pstmt.registerOutParameter(2, -1);
//				pstmt.setString(2, "");
//
//				this.log.info(
//						"Llamando: 4.3.1	sp_horus_get_tiposDeID("
//								+ tipoDeID.getId_tipoId() + ")", new Object[0]);
//				pstmt.execute();
//				String sp_horus_get_tiposDeID = pstmt.getString(2);
//
//				this.log.info("Resultado obtenido: "
//						+ sp_horus_get_tiposDeID, new Object[0]);
//				System.out.println("Resultado obtenido: " + sp_horus_get_tiposDeID);
//				
//				resul = new GsonBuilder()
//						.registerTypeAdapter(TipoIDHE_VO.class,
//								new TipoIDHESimpleAdapter())
//						.create()
//						.fromJson(sp_horus_get_tiposDeID,
//								R_GetTipoDeID.class);
//			}
//		} catch (NoResultException e) {
//			e.printStackTrace();
//			cerrarConexion(con);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			cerrarConexion(con);
//		} finally {
//			cerrarConexion(con);
//		}
//		return resul;
//	}
	
	public class R_CrearTipoDeID{
		
		private String mensaje;
		private Boolean ok;
		
		public R_CrearTipoDeID(){}

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

//	public class R_GetTipoDeID{
//		
//		private String mensaje;
//		private Boolean ok;
//		
//		private List<TipoIDHE_VO> tipos = new ArrayList<>();
//		
//		public R_GetTipoDeID(){}
//
//		/**
//		 * @return the mensaje
//		 */
//		public String getMensaje() {
//			return mensaje;
//		}
//
//		/**
//		 * @param mensaje the mensaje to set
//		 */
//		public void setMensaje(String mensaje) {
//			this.mensaje = mensaje;
//		}
//
//		/**
//		 * @return the ok
//		 */
//		public Boolean getOk() {
//			return ok;
//		}
//
//		/**
//		 * @param ok the ok to set
//		 */
//		public void setOk(Boolean ok) {
//			this.ok = ok;
//		}
//
//		/**
//		 * @return the roles
//		 */
//		public List<TipoIDHE_VO> getTipoDeIDs() {
//			return tipos;
//		}
//
//		/**
//		 * @param roles the roles to set
//		 */
//		public void setTipoDeIDs(List<TipoIDHE_VO> ts) {
//			this.tipos = ts;
//		}
//		
//	}

	/**
	 * @return the rol
	 */
	public TipoIDHE_VO getTipoDeID() {
		return tipo;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setTipoDeID(TipoIDHE_VO rol) {
		this.tipo = rol;
	}

	/**
	 * Recupera un tipo segun el cogido
	 * 
	 * @param codigo
	 * @return
	 */
	public TipoIDHE_VO recuperarEntidadPorCodigo(String codigo) {
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".id_tipoId = :cod");
		this.getCondiciones().put("cod", codigo);
		
		List<TipoIDHE_VO> list = this.listarTodo();
		return (!list.isEmpty())?list.get(0):null;
	}
	
}