package ar.org.hospitalespanol.dao.internacion;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.fx.R_CrearEntidad;
import ar.org.hospitalespanol.vo.internacion.CriterioDeAsignacionCama_VO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class DAO_CriterioDeAsignacionCama extends DAO<CriterioDeAsignacionCama_VO>{

	
	public List<CriterioDeAsignacionCama_VO>  listadoDeCriteriosDeAsignacion(){
		List<CriterioDeAsignacionCama_VO> resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_criteriosasigcama(?)}");
				
				pstmt.registerOutParameter(1, -1);
				pstmt.setString(1, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_criteriosasigcama()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_listadocriterios = pstmt.getString(1);

				DAO_Utils.info(log, "DAO_CriterioDeAsignacionCama","listadoDeCriteriosDeAsignacionDeCamas", getUsuarioAccion(), "Se llama a sp_horus_get_criteriosasigcama() y se obtiene: "+sp_horus_get_listadocriterios);

				Type listType = new TypeToken<List<CriterioDeAsignacionCama_VO>>() {}.getType();
		   
				resul =new GsonBuilder().create().fromJson(
						sp_horus_get_listadocriterios, listType);
		
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
	
	public R_CrearEntidad  guardarCriterioDeAsignacion(CriterioDeAsignacionCama_VO criterio){
		R_CrearEntidad resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_LDAP, BD_PASS_HORUS_LDAP);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_guardarcriterio(?,?,?)}");
				
				pstmt.setString(1, criterio.getCodigo());
				pstmt.setString(2, criterio.getDescripcion());
				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_guardarcriterio()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_guardarcriterio = pstmt.getString(3);

				DAO_Utils.info(log, "DAO_CriterioDeAsignacionCama","guardarCriterio", getUsuarioAccion(),"Se llama a sp_horus_get_guardarcriterio() y se obtiene: "+sp_horus_get_guardarcriterio);

		   
				resul = new Gson().fromJson(sp_horus_get_guardarcriterio, R_CrearEntidad.class);
		
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
	
	public R_CrearEntidad  modificarCriterioDeAsignacion(CriterioDeAsignacionCama_VO criterio){
		R_CrearEntidad resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_LDAP, BD_PASS_HORUS_LDAP);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_modifcriterio(?,?,?,?)}");
				
				pstmt.setInt(1, criterio.getId());
				pstmt.setString(2, criterio.getCodigo());
				pstmt.setString(3, criterio.getDescripcion());
				pstmt.registerOutParameter(4, -1);
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_set_modifcriterio()", new Object[0]);
				pstmt.execute();
				String sp_horus_set_modifcriterio = pstmt.getString(4);

				DAO_Utils.info(log, "DAO_CriterioDeAsignacionCama","modificarCriterio", getUsuarioAccion(),"Se llama a sp_horus_set_modifcriterio() y se obtiene: "+sp_horus_set_modifcriterio);

		   
				resul = new Gson().fromJson(sp_horus_set_modifcriterio, R_CrearEntidad.class);
		
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
	
	public R_CrearEntidad  eliminarCriterioDeAsignacion(CriterioDeAsignacionCama_VO criterio){
		R_CrearEntidad resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_LDAP, BD_PASS_HORUS_LDAP);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_borrarcriterio(?,?)}");
				
				pstmt.setInt(1, criterio.getId());
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_set_borrarcriterio()", new Object[0]);
				pstmt.execute();
				String sp_horus_set_borrarcriterio = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_CriterioDeAsignacionCama","eliminarCriterio", getUsuarioAccion(),"Se llama a sp_horus_set_borrarcriterio() y se obtiene: "+sp_horus_set_borrarcriterio);

		   
				resul = new Gson().fromJson(sp_horus_set_borrarcriterio, R_CrearEntidad.class);
		
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
	
	
	
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return null;
	}


}
