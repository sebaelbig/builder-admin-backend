package ar.org.hospitalespanol.dao.core.usuarios.roles.profesionales;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.fx.core.usuarios.roles.profesionales.FX_CrearProfesional;
import ar.org.hospitalespanol.utils.adapters.core.usuarios.roles.ProfesionalHESimpleAdapter;
import ar.org.hospitalespanol.vo.core.areas.DiaDeAtencion_VO;
import ar.org.hospitalespanol.vo.core.especialidades.Especialidad_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.Profesional_VO;
import ar.org.hospitalespanol.ws.respuestas.datosDelPaciente.R_ProfesionalesHE;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
			
			setResp_listar(buscar("profesional", pagina, cantidad));
		} else {
			setResp_listar(buscar("profesional", pagina, cantidad));
		}
		return getResp_listar().getLista();
	}

	public R_ProfesionalesHE buscar(String parametro, Integer pagina,
			Integer cantidad) {
		R_ProfesionalesHE resul = new R_ProfesionalesHE();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_buscarProfes(?,?,?,?,?,?,?,?)}");

				Object[] s = new Object[8];

				Integer temp = Integer.valueOf(-1);
				if (parametro != null) {
					ProfesionalHE_VO profHE = (ProfesionalHE_VO) getCondiciones()
							.get(parametro);
					if (profHE.getNroMatricula() != null) {
						s[0] = profHE.getNroMatricula();
					} else {
						s[0] = temp;
					}
					if (profHE.getMutualesNoAceptadas().size() > 0) {
						s[1] = profHE.getMutualesNoAceptadas().get(0)
								.getCodigo();
					} else {
						s[1] = temp;
					}
					if (profHE.getEspecialidades().size() > 0) {
						s[2] = profHE.getEspecialidades().get(0).getCodigo();
					} else {
						s[2] = temp;
					}
					DiaDeAtencion_VO d = null;
					if (profHE.getDiasAtencion().size() > 0) {
						d = profHE.getDiasAtencion().get(0);
						if (d.getNombre() != null) {
							s[3] = DAO_Utils.quitarAcentos(d.getNombre());
						} else {
							s[3] = null;
						}
						if (d.getHoraInicioAtencion() != null) {
							s[4] = d.getStr_horaInicioAtencion();
						} else {
							s[4] = null;
						}
					} else {
						s[3] = null;
						s[4] = null;
					}
				} else {
					s[0] = temp;
					s[1] = temp;
					s[2] = temp;
					s[3] = null;
					s[4] = null;
				}
				s[5] = pagina;
				s[6] = cantidad;

				pstmt.setInt(1, ((Integer) s[0]).intValue());

				pstmt.setInt(2, ((Integer) s[1]).intValue());

				pstmt.setInt(3, ((Integer) s[2]).intValue());

				pstmt.setString(4, (String) s[3]);

				pstmt.setString(5, (String) s[4]);

				pstmt.setInt(6, ((Integer) s[5]).intValue());

				pstmt.setInt(7, ((Integer) s[6]).intValue());

				pstmt.setString(8, "");
				pstmt.registerOutParameter(8, -1);

				this.log.info(
						"Llamando: 4.2.1 sp_horus_get_buscarProfes (#0,#1,#2,#3,#4,#5,#6)",
						new Object[] { s[0], s[1], s[2], s[3], s[4], s[5], s[6] });
				System.out.println("Llamando: 4.2.1 sp_horus_get_buscarProfes ("+s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+","+s[5]+","+s[6]+")");

				pstmt.execute();
				String sp_horus_get_buscarProfes = pstmt.getString(8);

				this.log.info("Resultado obtenido: "
						+ sp_horus_get_buscarProfes, new Object[0]);
				System.out.println("Resultado obtenido: "
						+ sp_horus_get_buscarProfes);
				
				resul =
				new GsonBuilder()
						.registerTypeAdapter(ProfesionalHE_VO.class,
								new ProfesionalHESimpleAdapter())
						.create()
						.fromJson(sp_horus_get_buscarProfes,
								R_ProfesionalesHE.class);
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
		return resul;
	}

	public R_ProfesionalesHE buscarParaAsistidor(String parametro) {
		R_ProfesionalesHE resul = new R_ProfesionalesHE();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_profesPorNombre(?,?,?)}");

				Integer temp = Integer.valueOf(-1);
				if (parametro != null) {
					ProfesionalHE_VO profHE = (ProfesionalHE_VO) getCondiciones()
							.get(parametro);

					pstmt.setString(1, profHE.getApellido());
					if (profHE.getNroMatricula() != null) {
						pstmt.setInt(2, profHE.getNroMatricula().intValue());
					} else {
						pstmt.setInt(2, temp.intValue());
					}
				} else {
					pstmt.setString(1, null);
					pstmt.setInt(2, temp.intValue());
				}
				pstmt.setString(3, "");
				pstmt.registerOutParameter(3, -1);

				this.log.info(
						"Llamando: 4.2.2 sp_horus_get_profesPorNombre...",
						new Object[0]);
				pstmt.execute();
				String sp_horus_get_profesPorNombre = pstmt.getString(3);

				this.log.info("Resultado obtenido: "
						+ sp_horus_get_profesPorNombre, new Object[0]);

				resul = new Gson().fromJson(sp_horus_get_profesPorNombre,
						R_ProfesionalesHE.class);
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
		return resul;
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

	public R_ProfesionalesHE getProfesionalesPor(String nombre, int mat) {
		R_ProfesionalesHE resul = new R_ProfesionalesHE();
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_existeprofe(?,?,?)}");
				
				pstmt.setString(1, nombre);
				pstmt.setInt(2, mat);
				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");
				
				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_existeprofe()", new Object[1]);
				pstmt.execute();
				String sp_horus_get_existeprofe = pstmt.getString(3);

				DAO_Utils.info(log, "DAO_Profesional_HE","listadoProfesionalespormatonombre",getUsuarioAccion(), "Se llama a sp_horus_get_existeprofe() y se obtiene: "+sp_horus_get_existeprofe);
		   
				resul = new Gson().fromJson(sp_horus_get_existeprofe,
						R_ProfesionalesHE.class);
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
