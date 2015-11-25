package ar.org.hospitalespanol.dao.core.especialidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.vo.core.especialidades.Especialidad_VO;
import ar.org.hospitalespanol.ws.respuestas.datosDelPaciente.R_Especialidades;

import com.google.gson.Gson;

@Service
public class DAO_Especialidad extends DAO<Especialidad_VO> {
	
	public DAO_Especialidad() {
		setCondiciones(new HashMap<String, Object>());
	}

	@Override
	protected String getIdClass() {
		return "e";
	}

	public List<Especialidad_VO> listar(Integer pagina, Integer cantidad) {
		if ((getCondiciones().isEmpty()) || (cantidad.intValue() == 0)) {
			setResp_listar(buscar(null, pagina, cantidad));
		} else {
			Especialidad_VO espe = (Especialidad_VO) getCondiciones().get(
					"especialidad");
			setResp_listar(buscar(espe.getNombre(), pagina, cantidad));
		}
		return getResp_listar().getLista();
	}

	public R_Especialidades buscar(String especialidad, Integer pagina,
			Integer cantidad) {
		R_Especialidades resul = new R_Especialidades();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_especialidades(?,?,?,?)}");

				pstmt.setString(1, especialidad);

				pstmt.setInt(2, pagina.intValue());

				pstmt.setInt(3, cantidad.intValue());

				pstmt.setString(4, "parametro_de_salida_json");
				pstmt.registerOutParameter(4, -1);

				// this.log.info("Llamando: 4.3.1 sp_horus_get_especialidades...",
				// new Object[0]);
				pstmt.execute();
				String sp_horus_get_especialidades = pstmt.getString(4);

				// this.log.info("Resultado obtenido: " +
				// sp_horus_get_especialidades, new Object[0]);

				resul = new Gson().fromJson(sp_horus_get_especialidades,
						R_Especialidades.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				// this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException e) {
				// this.log.error("No se pudo cerrar", new Object[0]);
				e.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				// this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException e) {
				// this.log.error("No se pudo cerrar", new Object[0]);
				e.printStackTrace();
			}
		}
		return resul;
	}

	public List<Especialidad_VO> listarTodas() {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	@Override
	protected Class getClazz() {
		return Especialidad_VO.class;
	}

	public List<Especialidad_VO> buscarEspecialidadPorNombre(String nombre) {
		return buscar(nombre, Integer.valueOf(1), Integer.valueOf(0))
				.getLista();
	}
}