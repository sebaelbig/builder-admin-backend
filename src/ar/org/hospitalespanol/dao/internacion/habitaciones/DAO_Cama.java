package ar.org.hospitalespanol.dao.internacion.habitaciones;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.fx.R_CrearEntidad;
import ar.org.hospitalespanol.vo.internacion.Pabellon_VO;
import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.Cama_VO;
import ar.org.hospitalespanol.ws.respuestas.internacion.CamaSimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class DAO_Cama extends DAO<Cama_VO>{
	
	public DAO_Cama (){
		
	}
	
	public List<Cama_VO> getCamasPorPabellon(Pabellon_VO pabe){
		List<Cama_VO> resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_camas_xpabe(?,?)}");
				
				pstmt.setInt(1, pabe.getPabellon());
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_camas()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_camas = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_Camas","listadoDeCamas", getUsuarioAccion(),"Se llama a sp_horus_get_camas_xpabe("+pabe.getPabellon()+") y se obtiene: "+sp_horus_get_camas);

				Type listType = new TypeToken<List<Cama_VO>>() {}.getType();
		   
				resul = new GsonBuilder()
						.registerTypeAdapter(Cama_VO.class, 
								new CamaSimpleAdapter()).create()
						.fromJson(sp_horus_get_camas, listType);
				
			
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
	
	public R_CrearEntidad setCriterioACama(Cama_VO cama) {
		R_CrearEntidad resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_LDAP, BD_PASS_HORUS_LDAP);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_criterioacama(?,?,?,?)}");
				
				pstmt.setString(1,cama.getPieza());
				pstmt.setString(2, cama.getCama());
				pstmt.setString(3, cama.getCodigoCriterioDeAsignacion());
				pstmt.registerOutParameter(4, -1);
				pstmt.setString(4, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_set_criterioacama()", new Object[0]);
				pstmt.execute();
				String sp_horus_set_criterioacama = pstmt.getString(4);

				DAO_Utils.info(log, "DAO_CriterioDeAsignacionCama","guardarCriterio", getUsuarioAccion(),"Se llama a sp_horus_set_criterioacama() y se obtiene: "+sp_horus_set_criterioacama);

		   
				resul = new Gson().fromJson(sp_horus_set_criterioacama, R_CrearEntidad.class);
		
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
		return DAO_Cama.class;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
