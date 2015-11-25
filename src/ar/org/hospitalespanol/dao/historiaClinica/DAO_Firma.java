package ar.org.hospitalespanol.dao.historiaClinica;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.DBPropertiesReader;
import ar.org.hospitalespanol.fx.R_CrearEntidad;
import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.FirmaProfesional;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.FirmaProfesional_VO;

import com.google.gson.Gson;

@Service
public class DAO_Firma extends
		DAO<FirmaProfesional_VO> {
	public DAO_Firma() {
		this.setQueryEncabezado("SELECT new "
				+ FirmaProfesional_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nroMatricula ");
		this.setQueryCondiciones("");
	}

	@Override
	protected Class getClazz() {
		return FirmaProfesional.class;
	}

	@Override
	public String getIdClass() {
		return "firmaProfesional";
	}

	public R_CrearEntidad guardar(EntityManager entityManager,
			FirmaProfesional_VO firma) {
		R_CrearEntidad resul = new R_CrearEntidad();

		Connection con = getConexionHE();
		try {
			
			if (con != null) {
				CallableStatement pstmt = con
				 .prepareCall("{call sp_horus_set_firma_medico(?,?,?,?,?,?)}");

				Object[] s = new Object[6];

				// id, rol, usuario, estado
				s[0] = firma.getNroMatricula();
				s[1] = firma.getTipoDeMatricula().abreviar().toString();
				s[2] = firma.getEspecialidad();
				s[3] = firma.getEspecialidad_renglon();
				s[4] = firma.getEmail();
				
				pstmt.setString(1, (String) s[0]);
				pstmt.setString(2, ((String) s[1]));
				pstmt.setString(3, ((String) s[2]));
				pstmt.setString(4, ((String) s[3]));
				pstmt.setString(5,((String) s[4]));
				
				pstmt.registerOutParameter(6, -1);

				String detalle = "Llamando: 4.2.2	sp_horus_set_firma_medico (" + s[0]
						+ "," + s[1] + "," + s[2] + "," + s[3] +"," + s[4] +")";
				DAO_Utils.info(log, "DAO_Firma", "guardar", getUsuarioAccion(),detalle);

				pstmt.execute();
				String sp_horus_set_firma_medico = pstmt.getString(6);

				this.log.info("Resultado obtenido: " + sp_horus_set_firma_medico, new Object[0]);
				System.out.println(sp_horus_set_firma_medico);

				resul = new Gson().fromJson(sp_horus_set_firma_medico, R_CrearEntidad.class);
			}

		} catch (SQLException sql_e) {
			sql_e.printStackTrace();

			if (sql_e.getMessage().indexOf("duplicate key") >= 0) {
				//
				resul.setMensaje("ERROR, Ya existe una firma con ese ID");
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

	public List<FirmaProfesional_VO> buscarPorMatricula(String nroMatriculaActuante,
			String usuarioAccion) {
		
		this.resetQuery();
		
		this.setQueryCondiciones(" WHERE ("+this.getIdClass()+".nroMatricula = :nroMat ) ");
		this.getCondiciones().put("nroMat", nroMatriculaActuante.toLowerCase()); //
		
		DAO_Utils.info(log, "DAO_Firma", "buscarPorMatricula", getUsuarioAccion(),"Buscando la firma de :"+nroMatriculaActuante);
		
		return this.listarTodo();
		
		
	}

	/**
	 * Dado un nro de matricula, actualiza el email
	 * 
	 * @param matricula
	 * @param mail
	 */
	public void actualizarEmail(int matricula, String mail) {
	
		try{
			
			String sql = " UPDATE "+dbProperties.getProperty(DBPropertiesReader.SCHEMA_POSTGRES)+"."+getClass().getName() 
						+" SET email = :email "
						+" WHERE nro_matricula = :mat";
			
			getEntityManager().createNativeQuery(sql)
						.setParameter("email", mail)
						.setParameter("mat", mail)
						.getSingleResult().toString();
			
		}catch (Exception e ){
			DAO_Utils.error(log, "DAO_FIrma", "actualizarEmail", "-interno-", "No se pudo actualizar la firma");
			e.printStackTrace();
		}
		
	}

//	public List<TemplateDeDescripcionPrivado_VO> listarDeServicio(
//			Servicio_VO serv, String usuario) {
//
//		this.resetQuery();
//		
//		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".perfil.servicio.id = :idSrv ");
//		this.getCondiciones().put("idSrv", serv.getId());
//		
//		List<TemplateDeDescripcionPrivado_VO> pedidos = this.listarTodo();
//		
//		this.resetQuery();
//		return pedidos;
//	}
}