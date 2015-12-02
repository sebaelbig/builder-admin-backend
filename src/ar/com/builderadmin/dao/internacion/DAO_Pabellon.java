package ar.com.builderadmin.dao.internacion;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.vo.internacion.Pabellon_VO;

@Service
public class DAO_Pabellon extends DAO<Pabellon_VO> {
	
	
	public DAO_Pabellon(){
		
	}
	
	public List<Pabellon_VO> getPabellones(){
		/**Llamo al sp de pabellones**/
		List<Pabellon_VO> pabe=null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_pabellones(?)}");
				
				pstmt.registerOutParameter(1, -1);
				pstmt.setString(1, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_pabellones()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_listadoPabellones = pstmt.getString(1);

				DAO_Utils.info(log, "DAO_Pabellon","listadoDeInternadosHoy", getUsuarioAccion(),"Se llama a sp_horus_get_pabellon y se obtiene: "+sp_horus_get_listadoPabellones);

				Type listType = new TypeToken<List<Pabellon_VO>>() {}.getType();

				pabe = new GsonBuilder().create()
					.fromJson(sp_horus_get_listadoPabellones, listType);
			
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
		
		return pabe;
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
