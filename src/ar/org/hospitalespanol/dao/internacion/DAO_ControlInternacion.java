package ar.org.hospitalespanol.dao.internacion;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.internacion.Carpeta;
import ar.org.hospitalespanol.vo.internacion.Carpeta_VO;
import ar.org.hospitalespanol.vo.internacion.Pabellon_VO;
import ar.org.hospitalespanol.ws.respuestas.internacion.CarpetaSimpleAdapter;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@Service
public class DAO_ControlInternacion extends DAO<Carpeta_VO> {
	
	
	public DAO_ControlInternacion(){
		
	}
	
	public List<Carpeta_VO> listadoDeInternadosHoy(Pabellon_VO p){
		List<Carpeta_VO> resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_internados_xpabe(?,?)}");
				
				pstmt.setInt(1, p.getPabellon());
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_internados_xpabe("+p.getPabellon()+")", new Object[1]);
				pstmt.execute();
				String sp_horus_get_listadoInternados = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_ControlInternacion","listadoDeInternadosHoy", getUsuarioAccion(),"Se llama a sp_horus_get_internados_xpabe("+p.getPabellon()+") y se obtiene: Pciantes del pabellon");

				Type listType = new TypeToken<List<Carpeta_VO>>() {}.getType();
		   
				resul = new GsonBuilder()
						.registerTypeAdapter(Carpeta_VO.class, 
								new CarpetaSimpleAdapter()).create()
						.fromJson(sp_horus_get_listadoInternados, listType);
				
			
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
	
	public Carpeta_VO getDatosIntPaciente(String nroCarpeta){
		
		Carpeta_VO resul = null;
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_internacionpac(?,?)}");
				
				pstmt.setString(1,nroCarpeta);
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_internacionpac()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_internacionpac = pstmt.getString(2);


				DAO_Utils.info(log, "DAO_ControlInternacion","listadoDeInternadosHoy", getUsuarioAccion(), "Se llama a sp_horus_get_internacionpac y se obtiene: "+sp_horus_get_internacionpac);

		   
				resul = new GsonBuilder()
						.registerTypeAdapter(Carpeta_VO.class, 
								new CarpetaSimpleAdapter()).create()
						.fromJson(sp_horus_get_internacionpac, Carpeta_VO.class);
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
	
	public List<Carpeta_VO> getCapetasPorFiltro(Carpeta carpeta){
		List<Carpeta_VO> resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_carpetasfiltro(?,?,?,?)}");
				
				
				//pstmt.setString(1, carpeta.getFechaEgreso());
				pstmt.setString(1, carpeta.getCarpeta());
				pstmt.setString(2, carpeta.getDniPaciente());
				pstmt.setString(3, carpeta.getPaciente());
				pstmt.registerOutParameter(4, -1);
				pstmt.setString(4, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_carpetasfiltro()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_listadoInternados = pstmt.getString(4);

				DAO_Utils.info(log, "DAO_ControlInternacion","listadoDeInternadosFiltrados", getUsuarioAccion(), "Se llama a sp_horus_get_carpetasfiltro( 'carpeta': "+carpeta.getCarpeta()+"'dni': "+carpeta.getDniPaciente()+ " 'Paciente':"+carpeta.getPaciente()+") y se obtiene: "+sp_horus_get_listadoInternados);


				Type listType = new TypeToken<List<Carpeta_VO>>() {}.getType();
		   
				resul = new GsonBuilder()
						.registerTypeAdapter(Carpeta_VO.class, 
								new CarpetaSimpleAdapter()).create()
						.fromJson(sp_horus_get_listadoInternados, listType);
				
			
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
	
	public void setAltaMedica(Carpeta carpeta) {
	
		String resul= null;
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_altamedica(?,?,?,?,?,?,?,?)}");
				
				
				
				pstmt.setString(1, carpeta.getCarpeta());
				pstmt.setString(2, carpeta.getFechaAltaMedica());
				pstmt.setString(3, carpeta.getUsuarioAltaMedica());
				pstmt.setString(4, carpeta.getCondicionAlta().codigo());
				pstmt.setString(5, carpeta.getDiagnosticoEgreso());
				pstmt.setString(6, carpeta.getDiagnostico());
				pstmt.setString(7, Integer.toString(carpeta.getMatProfCabecera()));
				pstmt.registerOutParameter(8, -1);
				pstmt.setString(8, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_carpetasfiltro()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_altamedica = pstmt.getString(8);

				DAO_Utils.info(log, "DAO_ControlInternacion","setAltaMedica", getUsuarioAccion(), "Se llama a sp_horus_set_altamedica() y se obtiene: "+sp_horus_get_altamedica);

			
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Listado de internados por profesional
	 * @param matricula
	 * @return pacientes internados a cargo del profesional
	 */
	public List<Carpeta_VO> listadoDeInternadosPorProfesional(String matricula){
		List<Carpeta_VO> resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_internados_xprof(?,?)}");
				
				pstmt.setInt(1, Integer.parseInt(matricula));
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_internados_xprof("+matricula+")", new Object[1]);
				pstmt.execute();
				String sp_horus_get_internados_xprof = pstmt.getString(2);

				DAO_Utils.info(log, "DAO_ControlInternacion","listadoDeInternadosPorProfesional", getUsuarioAccion(), "Se llama a sp_horus_get_internados_xprof("+matricula+") y se obtiene: "+sp_horus_get_internados_xprof);
				//HOlii
				Type listType = new TypeToken<List<Carpeta_VO>>() {}.getType();
		   
				resul = new GsonBuilder()
						.registerTypeAdapter(Carpeta_VO.class, 
								new CarpetaSimpleAdapter()).create()
						.fromJson(sp_horus_get_internados_xprof, listType);
				
			
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
