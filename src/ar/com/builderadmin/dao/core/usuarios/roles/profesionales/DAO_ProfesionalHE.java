package ar.com.builderadmin.dao.core.usuarios.roles.profesionales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.fx.core.usuarios.roles.profesionales.FX_CrearProfesional;
import ar.com.builderadmin.utils.adapters.core.usuarios.roles.ProfesionalHESimpleAdapter;
import ar.com.builderadmin.vo.core.areas.DiaDeAtencion_VO;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.Profesional_VO;

@Service
public class DAO_ProfesionalHE extends DAO<ProfesionalHE_VO> {

	public DAO_ProfesionalHE() {
	}

	@Override
	protected String getIdClass() {
		return "prof";
	}

	@Override
	protected Class getClazz() {
		return ProfesionalHE_VO.class;
	}

	public List<ProfesionalHE_VO> buscarProfesionalesPorNroMatriculaProvincialTurnos(
			String nroMatriculaProvincial) {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public List<ProfesionalHE_VO> buscarProfesionalPorApellidoTurnos(
			String valor) {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public List<ProfesionalHE_VO> buscarProfesionalPorApellido(String valor) {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public List<ProfesionalHE_VO> listarTodas() {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public Profesional_VO buscarProfesionalPorMatricula(String nroMatricula) {
		
		//Busco en MI BD si está, sino busco en el HE
		Profesional_VO profeActuante = null;
		
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".nroMatriculaNacional = :nroMat " +
				" OR "+this.getIdClass()+".nroMatriculaProvincial = :nroMat ");
		
		this.getCondiciones().put("nroMat", nroMatricula);
		
		//Listo todo
		List<Profesional_VO> profes = this.getQuery().getResultList();
		
		if(profes.isEmpty()){
			
			profeActuante = profes.get(0);
			
		}else{
			ProfesionalHE_VO profeHE = this.recuperarEntidad(Integer.parseInt(nroMatricula));
			
			FX_CrearProfesional fx_crearProfesional;
		
		}
		
		return profeActuante;
	}

	public List<ProfesionalHE_VO> listar(Integer pagina, Integer cantidad) {
		if ((getCondiciones().isEmpty()) || (cantidad.intValue() == 0)) {
			
			Especialidad_VO espe = new Especialidad_VO();
			espe.setCodigo(Integer.valueOf(6));

			ProfesionalHE_VO profe = new ProfesionalHE_VO();
			profe.getEspecialidades().add(espe);

			getCondiciones().put("profesional", profe);
			
		} else {
		}
		return getResp_listar().getLista();
	}


	public ProfesionalHE_VO recuperarEntidad(Integer nroMatricula) {

		// ProfesionalHE_VO resul;

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_datosprofesional(?,?)}");

				pstmt.setInt(1, nroMatricula.intValue());
				pstmt.setString(2, "");
				pstmt.registerOutParameter(2, -1);

				this.log.info("Llamando: 4.2.2 sp_horus_get_datosprofesional...",
						new Object[0]);
				System.out.println("Llamando: 4.2.2 sp_horus_get_datosprofesional("+nroMatricula+")");
				pstmt.execute();
				String sp_horus_get_profesional = pstmt.getString(2);

				this.log.info(
						"Resultado obtenido: " + sp_horus_get_profesional,
						new Object[0]);
				System.out.println("Resultado obtenido: "
						+ sp_horus_get_profesional);

				ProfesionalHE_VO resul = getGson().fromJson(sp_horus_get_profesional,
						ProfesionalHE_VO.class);
				resul.setNroMatricula(nroMatricula);
				
				return resul;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException e) {
				this.log.error("No se pudo cerrar", new Object[0]);
				e.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException e) {
				this.log.error("No se pudo cerrar", new Object[0]);
				e.printStackTrace();
			}
		}
		return null;
	}

	private Gson getGson() {
		return new GsonBuilder().registerTypeAdapter(ProfesionalHE_VO.class,
				new ProfesionalHESimpleAdapter()).create();
	}

	public R_Matricula getMatriculaDeUsuario(String usuarioProfesional) {

		// Connection con = getConexionHE();
		// try {
		// if (con != null) {
		// CallableStatement pstmt = con
		// .prepareCall("{call sp_horus_get_matricula(?,?)}");
		//
		// pstmt.setString(1, usuarioProfesional);
		// pstmt.setString(2, "");
		// pstmt.registerOutParameter(2, -1);
		//
		// this.log.info("Llamando: 4.1.1 sp_horus_get_matricula...",
		// new Object[0]);
		// pstmt.execute();
		// String sp_horus_get_matricula = pstmt.getString(2);
		//
		// this.log.info(
		// "Resultado obtenido: " + sp_horus_get_matricula,
		// new Object[0]);
		//
		// return new Gson().fromJson(
		// sp_horus_get_matricula, R_Matricula.class);
		// }
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// try {
		// con.rollback();
		// con.close();
		//
		// this.log.info("Se cerro las conexiones", new Object[0]);
		// } catch (SQLException e) {
		// this.log.error("No se pudo cerrar", new Object[0]);
		// e.printStackTrace();
		// }
		// } finally {
		// try {
		// con.rollback();
		// con.close();
		//
		// this.log.info("Se cerro las conexiones", new Object[0]);
		// } catch (SQLException e) {
		// this.log.error("No se pudo cerrar", new Object[0]);
		// e.printStackTrace();
		// }
		// }
		// return null;

		return new R_Matricula(true, "12123", "");
	}

	public class R_Matricula {

		private Boolean ok = Boolean.valueOf(true);
		private String matricula;
		private String mensaje;

		public R_Matricula() {
		}

		public R_Matricula(Boolean ok, String matricula, String mensaje) {
			this.ok = ok;
			this.matricula = matricula;
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
		 * @return the matricula
		 */
		public String getMatricula() {
			return matricula;
		}

		/**
		 * @param matricula
		 *            the matricula to set
		 */
		public void setMatricula(String matricula) {
			this.matricula = matricula;
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

	}


}
