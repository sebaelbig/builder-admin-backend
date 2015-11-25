package ar.org.hospitalespanol.dao.cirugias;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.FuncionHorus_VO;
import ar.org.hospitalespanol.ws.respuestas.cirugias.CirugiaProgramadaSimpleAdapter;
import ar.org.hospitalespanol.ws.respuestas.cirugias.CirugiaProgramada_VO;
import ar.org.hospitalespanol.ws.respuestas.cirugias.R_CirugiasProgramadas;

import com.google.gson.GsonBuilder;

@Service
public class DAO_CirugiaProgramada extends DAO<CirugiaProgramada_VO> {
	
	/**
	 * Logger.
	 */
	private final Logger log = LoggerFactory
			.getLogger(DAO_CirugiaProgramada.class);
	
	
	public DAO_CirugiaProgramada() {
		super();
	}

	@Override
	protected Class getClazz() {
		return CirugiaProgramada_VO.class;
	}

	@Override
	protected String getIdClass() {
		return "u";
	}

	@Override
	public void setQueryCondiciones(String queryCondiciones) {
		this.queryCondiciones = queryCondiciones;
	}

	public List<FuncionHorus_VO> funciones() {
		return null;
	}

	public FuncionHorus_VO getFuncion(String fx_elegida) {
		FuncionHorus_VO resul = null;
		try {
			resul = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	public R_CirugiasProgramadas getCirugiasProgramadasDeFecha(Date fecha, Integer nroSala) {

		R_CirugiasProgramadas resul = null;
		
		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_cirugiasprog(?,?,?)}");

				Object[] s = new Object[2];
				s[0] = fecha;
				s[1] = nroSala;

				pstmt.setString(1, new SimpleDateFormat("dd/MM/yyyy").format(s[0]));
				pstmt.setInt(2, ((Integer) s[1]).intValue());

				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");

				DAO_Utils.info(this.log, "DAO_CirugiaProgramada", "getCirugiasProgramadasDeFecha",getUsuarioAccion(), "Llamando: 4.1.1 sp_horus_get_cirugiasprog ("+s[0]+","+s[1]+")" );
				pstmt.execute();
				String sp_horus_get_cirugiasprogramadasdefecha = pstmt
						.getString(3);

				DAO_Utils.info(this.log, "DAO_CirugiaProgramada", "getCirugiasProgramadasDeFecha", getUsuarioAccion(),"Resultado obtenido"+sp_horus_get_cirugiasprogramadasdefecha);
				
				resul =
					new GsonBuilder()
							.setDateFormat("dd/MM/yyyy")
							.registerTypeAdapter(CirugiaProgramada_VO.class,
									new CirugiaProgramadaSimpleAdapter())
							.create()
							.fromJson(sp_horus_get_cirugiasprogramadasdefecha.toLowerCase(),
									R_CirugiasProgramadas.class);

			}else{
				resul = new R_CirugiasProgramadas();
				resul.setOk(false);
				resul.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(fecha));
				resul.setMensaje("No se pudo obtener una conexi�n");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones");
			} catch (SQLException e) {
				this.log.error("No se pudo cerrar");
				e.printStackTrace();
			}
		} finally {
			try {
				if (!con.isClosed()){
					con.rollback();
					con.close();
				}
				this.log.info("Se cerro las conexiones");
			} catch (SQLException e) {
				this.log.error("No se pudo cerrar");
				e.printStackTrace();
			}
		}
		
		return resul;
	}

}