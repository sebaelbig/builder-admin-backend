package ar.org.hospitalespanol.dao.historiaClinica.episodios;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.EpisodioPaciente_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author carlalu
 * 
 */
@Service
public class DAO_EpisodioPaciente extends DAO<EpisodioPaciente_VO> {

	public DAO_EpisodioPaciente() {

	}

	@Override
	public void resetQuery() {
		super.resetQuery();

	}
	
//	public List<EpisodioPaciente_VO> getTurnosCarpetasDePaciente(int tipoDocu,
//			int nroDocu) {
//		/** Llamo al sp de get turnos paciente **/
//		List<EpisodioPaciente_VO> turnos = null;
//
//		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS,
//				BD_PASS_HORUS_CONSULTAS);
//		try {
//			if (con != null) {
//				CallableStatement pstmt = con
//						.prepareCall("{call sp_horus_get_episodiospac(?,?,?)}");
//
//				pstmt.setInt(1, tipoDocu);
//				pstmt.setInt(2, nroDocu);
//				pstmt.registerOutParameter(3, -1);
//				pstmt.setString(3, "");
//
//				this.log.info("Llamando: 4.1.1	sp_horus_get_episodiospac()",
//						new Object[0]);
//				pstmt.execute();
//				String sp_horus_get_turnospaciente = pstmt.getString(3);
//
//				DAO_Utils.info(log, "DAO_EpisodioPaciente", "listadoDeEpisodiosHoy",
//						"Se llama a sp_horus_get_episodiospac y se obtiene: "
//								+ sp_horus_get_turnospaciente);
//
//				Type listType = new TypeToken<List<EpisodioPaciente_VO>>() {
//				}.getType();
//
//				turnos = new GsonBuilder().create().fromJson(
//						sp_horus_get_turnospaciente, listType);
//
//			}
//
//		} catch (NoResultException e) {
//			e.printStackTrace();
//			cerrarConexion(con);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			cerrarConexion(con);
//		} finally {
//			cerrarConexion(con);
//		}
//
//		return turnos;
//	}


	public List<EpisodioPaciente_VO> getTurnosDePaciente(int tipoDocu,
			int nroDocu) {
		/** Llamo al sp de get turnos paciente **/
		List<EpisodioPaciente_VO> turnos = null;

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS,
				BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_turnospaciente(?,?,?)}");

				pstmt.setInt(1, tipoDocu);
				pstmt.setInt(2, nroDocu);
				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");

				this.log.info("Llamando: 4.1.1	sp_horus_get_turnospaciente()",
						new Object[0]);
				pstmt.execute();
				String sp_horus_get_turnospaciente = pstmt.getString(3);

				DAO_Utils.info(log, "DAO_EpisodioPaciente", "listadoDeInternadosHoy",getUsuarioAccion(),
						"Se llama a sp_horus_get_turnospaciente y se obtiene: "
								+ sp_horus_get_turnospaciente);

				Type listType = new TypeToken<List<EpisodioPaciente_VO>>() {
				}.getType();

				turnos = new GsonBuilder().create().fromJson(
						sp_horus_get_turnospaciente, listType);

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

		return turnos;
	}

	public List<EpisodioPaciente_VO> getCarpetasDePaciente(int tipoDocu,
			int nroDocu) {
		/** Llamo al sp de get carpetas paciente **/
		List<EpisodioPaciente_VO> carpetas = null;

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS,
				BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_carpetaspaciente(?,?,?)}");

				pstmt.setInt(1, tipoDocu);
				pstmt.setInt(2, nroDocu);
				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");

				this.log.info(
						"Llamando: 4.1.1	sp_horus_get_carpetaspaciente()",
						new Object[0]);
				pstmt.execute();
				String sp_horus_get_carpetaspaciente = pstmt.getString(3);

				DAO_Utils.info(log, "DAO_EpisodioPaciente",
						"listado de carpetas de paciente",getUsuarioAccion(),
						"Se llama a sp_horus_get_carpetaspaciente y se obtiene: "
								+ sp_horus_get_carpetaspaciente);

				Type listType = new TypeToken<List<EpisodioPaciente_VO>>() {
				}.getType();

				carpetas = new GsonBuilder().create().fromJson(
						sp_horus_get_carpetaspaciente, listType);

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

		return carpetas;
	}

	public List<EpisodioPaciente_VO> getPedidosDePaciente(int tipoDocu,
			int nroDocu) {
		/** Llamo al sp de get pedidos paciente **/
		List<EpisodioPaciente_VO> pedidos = new ArrayList<>();

		Query query = getEntityManager()
				.createQuery(
						"SELECT p.id, p.fechaPedida,   "
								+ "p.matriculaProfesionalActuante, p.actuante,"
								+ "p.servicio.nombre, p.estudios"
								+ " FROM Pedido p"
								+ " WHERE p.tipoDniPaciente = :tipoDocu AND p.nroDniPaciente = :nroDocu "
								+ "AND p.estado='Informado'");
		query.setParameter("tipoDocu", Integer.toString(tipoDocu));
		query.setParameter("nroDocu", Integer.toString(nroDocu));

		Object[] objects = query.getResultList().toArray();

		EpisodioPaciente_VO pedido;
		for (Object ob : objects) {
			Object[] objeto = (Object[]) ob;
			/*
			 * pedido = new
			 * EpisodioPaciente_VO(Integer.parseInt(objeto[0].toString()),
			 * objeto[1].toString(), Integer.parseInt(objeto[2].toString()),
			 * objeto[3].toString(), objeto[4].toString(), objeto[5].toString(),
			 * "Estudio");
			 */
			pedido = new EpisodioPaciente_VO();
			if (objeto[0] != null) {
				pedido.setIdEpisodio(Integer.parseInt(objeto[0].toString()));
			}
			if (objeto[1] != null) {
				pedido.setFechast(objeto[1].toString());
			}
			if (objeto[2] != null) {
				pedido.setMatriculaProfesional(objeto[2].toString());
			}
			if (objeto[3] != null) {
				pedido.setNombreProfesional(objeto[3].toString());
			}
			if (objeto[4] != null) {
				pedido.setEspecialidadAtencion(objeto[4].toString());
			}
			if (objeto[5] != null) {
				pedido.setObservacion(objeto[5].toString());
			}
			pedido.setTipoEpisodio("Estudio");
			pedidos.add(pedido);

		}

		DAO_Utils
				.info(log,
						"DAO_EpisodioPaciente",
						"listado de pedidos de paciente",getUsuarioAccion(),
						"ejecuta la consulta  SELECT p.id, p.fecha,   "
								+ "p.matriculaProfesionalActuante, p.actuante,"
								+ "p.servicio, p.estudios"
								+ " FROM Pedido p"
								+ " WHERE p.tipoDniPaciente = :tipoDocu AND p.nroDniPaciente = :nroDocu AND p.estado='Informado' y se obtiene: "
								+ pedidos);

		return pedidos;
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
