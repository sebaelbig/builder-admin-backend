package ar.org.hospitalespanol.dao.turnos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.turnos.Turno;
import ar.org.hospitalespanol.model.turnos.estadosTurno.EstadoTurno;
import ar.org.hospitalespanol.utils.adapters.turnos.BloqueTurnoHESimpleAdapter;
import ar.org.hospitalespanol.vo.core.especialidades.Especialidad_VO;
import ar.org.hospitalespanol.vo.core.panelDeControl.ItemPanelControl_DTO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.EspecialidadProfesional_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.Profesional_VO;
import ar.org.hospitalespanol.vo.turnos.BloqueTurno_VO;
import ar.org.hospitalespanol.vo.turnos.TurnoReservado_DTO;
import ar.org.hospitalespanol.vo.turnos.Turno_VO;
import ar.org.hospitalespanol.vo.turnos.estados.TurnoReservado_VO;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_BloqueTurnos;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_CancelarTurno;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_GetTurnosDesde;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_GetTurnosDesdeSimpleAdapter;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_GetTurnosReservados;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_GetTurnosReservadosSimpleAdapter;
import ar.org.hospitalespanol.ws.respuestas.reservaTurnos.R_SetTurnosInsatisfechos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("unchecked")
@Service
public class DAO_Turnos extends DAO<Turno_VO> {

	public DAO_Turnos() {

		this.setQueryEncabezado("SELECT new "
				+ Turno_VO.class.getCanonicalName() + "(" + this.getIdClass()
				+ ") FROM " + this.getClazz().getCanonicalName() + " "
				+ this.getIdClass() + " ");

		this.setQueryCondiciones("");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".fecha ");

	}

	@Override
	protected Class getClazz() {
		return Turno.class;
	}

	@Override
	protected String getIdClass() {
		return "t";
	}

	public Turno_VO buscarPrimerTurnoLibreDeBloqueTurno(
			BloqueTurno_VO bloqueTurno_VO, Date fecha, EntityManager em) {

		String query = " SELECT new " + Turno_VO.class.getCanonicalName()
				+ "(t) FROM " + this.getClazz().getCanonicalName() + " "
				+ this.getIdClass() + " ";

		query += " WHERE " + this.getIdClass()
				+ ".bloqueTurno.id = :idBloque AND :fecha BETWEEN "
				+ this.getIdClass() + ".bloqueTurno.fechaInicio AND "
				+ this.getIdClass() + ".bloqueTurno.fechaFin ";

		query += " AND " + this.getIdClass() + ".fecha.fecha >= :fecha AND "
				+ this.getIdClass() + ".estado.nombre = :estado ";

		query += " ORDER BY " + this.getIdClass() + ".fecha, "
				+ this.getIdClass() + ".numero ";

		List<Turno_VO> turnos = em.createQuery(query)
				.setParameter("fecha", fecha)
				.setParameter("idBloque", bloqueTurno_VO.getId())
				.setParameter("estado", EstadoTurno.SIN_RESERVAR)
				.setMaxResults(1).getResultList();

		return turnos.get(0);
	}

	public void generarTurnosParaBloqueTurnoEntreDosFechas(Long idBT,
			Date fecha_desde, Date fecha_hasta, EntityManager em) {
		// DateFormat sdf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
		// DateFormat.MEDIUM, Locale.instance());
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String query = "select " + "horus_restfull_sg"
				+ ".sp_crear_turnos_de_bloque_turno_para_un_rango_de_fechas("
				+ idBT + ",'" + sdf.format(fecha_desde) + "','"
				+ sdf.format(fecha_hasta) + "')";
		em.createNativeQuery(query).getSingleResult();
	}

	// public List<Turno_VO> turnosReservadosDePaciente(Long idPaciente,
	// EntityManager em) {
	//
	// String query = " SELECT NEW " + Turno_VO.class.getCanonicalName() + "("
	// + this.getIdClass() + ") FROM "
	// + this.getClazz().getCanonicalName() + " " + this.getIdClass()
	// + " ";
	//
	// query += " WHERE " + this.getIdClass()
	// + ".estado.nombre = :estado AND " + this.getIdClass()
	// + ".estado.paciente.id = :paciente ";
	//
	// List<Turno_VO> turnosReservados = em.createQuery(query)
	// .setParameter("estado", EstadoTurno.RESERVADO)
	// .setParameter("paciente", idPaciente).getResultList();
	//
	// return turnosReservados;
	// }

	public R_GetTurnosReservados turnosReservadosDePaciente(String usuario) {
		R_GetTurnosReservados resul = new R_GetTurnosReservados();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_turnosReservados(?,?)}");

				pstmt.setString(1, usuario);

				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info(
						"Llamando: 4.1.1 horus_fe.sp_horus_get_turnosReservados("
								+ usuario + ")", new Object[0]);
				pstmt.execute();
				String sp_horus_get_turnosReservados = pstmt.getString(2);

				this.log.info("Resultado obtenido: "
						+ sp_horus_get_turnosReservados, new Object[0]);

				resul = new GsonBuilder()
						.registerTypeAdapter(R_GetTurnosReservados.class,
								new R_GetTurnosReservadosSimpleAdapter())
						.create()
						.fromJson(sp_horus_get_turnosReservados,
								R_GetTurnosReservados.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}

	public void eliminarTurnosDeBloqueTurnoAPartirDeUnaFecha(Long idBT,
			Date fecha, EntityManager em) {
		// DateFormat sdf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
		// DateFormat.MEDIUM, Locale.instance());
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String query = "select " + "horus_restfull_sg"
				+ ".sp_eliminar_turnos_de_bloque_turno_a_partir_de_una_fecha("
				+ idBT + ",'" + sdf.format(fecha) + "')";
		em.createNativeQuery(query).getSingleResult();
	}

	public Turno_VO buscarUltimoTurnoReservadoDeBloqueTurno(Long idBT,
			Date date, EntityManager em) {

		String query = " SELECT NEW " + Turno_VO.class.getCanonicalName() + "("
				+ this.getIdClass() + ") " + " FROM "
				+ this.getClazz().getCanonicalName() + " t " + " WHERE "
				+ this.getIdClass() + ".estado.nombre != :estado " + "			AND "
				+ this.getIdClass() + ".bloqueTurno.id = :idBT " + " ORDER BY "
				+ this.getIdClass() + ".fecha.fecha ASC ";

		List<Turno_VO> turnosReservados = em.createQuery(query)
				.setParameter("estado", EstadoTurno.SIN_RESERVAR)
				.setParameter("idBT", idBT).getResultList();

		return (turnosReservados.size() > 0) ? turnosReservados.get(0) : null;
	}

	/**
	 * Retorna todos los profesionales que atienden una especialidad en un
	 * servicio determinado junto con el primer turno libre para ese contrato
	 * 
	 * @param idServicio
	 * @param idEspecialidad
	 * @param fecha
	 * @param em
	 * @return
	 */
	public List<BloqueTurno_VO> buscarProfesionalesConPrimerTurnoDeUnaEspecialidad(
			Integer codigoEspecialidad, Date fecha) {

		R_BloqueTurnos resul = new R_BloqueTurnos();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_franjahoraporespe(?,?,?)}");

				pstmt.setInt(1, codigoEspecialidad.intValue());
				pstmt.setString(2,
						new SimpleDateFormat("dd/MM/yyyy").format(fecha));

				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");

				/************************/
				this.log.info(
						"Llamando: 4.2.1 sp_horus_get_franjahoraporespe( "
								+ codigoEspecialidad
								+ ", "
								+ new SimpleDateFormat("dd/MM/yyyy")
										.format(fecha) + " )", new Object[0]);
				System.out.println("Llamando: 4.2.1 sp_horus_get_franjahoraporespe( "+ codigoEspecialidad
						+ ", " + new SimpleDateFormat("dd/MM/yyyy").format(fecha) + " )");
				/************************/
				pstmt.execute();
				/************************/
				String sp_horus_get_franjahoraporespe = pstmt.getString(3);
				this.log.info("Resultado obtenido: "
						+ sp_horus_get_franjahoraporespe, new Object[0]);
				System.out.println("Resultado obtenido: "+ sp_horus_get_franjahoraporespe);
				/************************/
				
				resul =
						new GsonBuilder()
							.registerTypeAdapter(BloqueTurno_VO.class,
									new BloqueTurnoHESimpleAdapter())
							.create()
							.fromJson(sp_horus_get_franjahoraporespe,
								R_BloqueTurnos.class);

				EspecialidadProfesional_VO espePrestada;
				for (BloqueTurno_VO bt : resul.getLista()) {

					// bt.setEspecialidad(resul.getEspecialidad());
					espePrestada = new EspecialidadProfesional_VO();
					espePrestada.setEspecialidad(new Especialidad_VO(resul
							.getEspecialidad()));

					bt.setAceptaListaDeEspera(resul.getAceptaListaDeEspera());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul.getLista();
	}

	// public List<ProfesionalPrimerTurno_VO>
	// buscarProfesionalesConPrimerTurnoDeUnaEspecialidad(Long idServicio, Long
	// idEspecialidad, Date fecha, EntityManager em) {
	// String query =
	// " SELECT u.id as id_usuario, u.apellido, u.nombres, u.nombre_usuario, t.id as id_turno, t.numero, t.id_bloque_turno, bt.hora_inicio, bt.hora_fin, f.fecha, f.hora, d.nombre, up.rol, up.categ, bt.mostrar_aviso, bt.mensaje_aviso  "
	// + " FROM "
	// +
	// "	(SELECT u.id as usuario, r.id as rol, cap.nombre as categ, (SELECT t.id "
	// + " 	 FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".turno t "
	// + "				  inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".bloque_turno bt on (t.id_bloque_turno = bt.id) "
	// + " 				  inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia d on (bt.id_dia = d.id) "
	// + "                 inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".agenda ag on (d.id_agenda = ag.id) "
	// + "                 inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".fecha f on(t.id_fecha = f.id) "
	// + "                 inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".estado_turno et on (t.id_estado = et.id )"
	// + "    WHERE ag.id_contrato = cp.id and bt.activo and et.nombre = '"
	// + EstadoTurno.SIN_RESERVAR
	// + "' and f.fecha >= :fecha_entrada "
	// + "          and f.fecha not in (SELECT dia_f.fecha FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia_feriado dia_f) and "
	// + "          f.fecha not in (SELECT dia_au.fecha FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia_ausente dia_au WHERE id_usuario_ausente = u.id ) "
	// + "    ORDER BY f.fecha asc "
	// + "     LIMIT 1 OFFSET 0) as primer_turno "
	// + " FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".especialidad_profesional ep inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".contrato_profesional cp on (ep.id_contrato_profesional = cp.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".rol r on (cp.id_profesional = r.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".usuario u on (r.id_usuario = u.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".categoria_profesional cap on (ep.id_categoria_profesional = cap.id) "
	// +
	// " WHERE ep.id_servicio = :id_serv and ep.id_especialidad = :id_espe and :fecha_entrada BETWEEN cp.fecha_desde "
	// + " AND cp.fecha_hasta) up, "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".usuario u, "
	// + " "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".turno t inner join "
	// + " "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".fecha f on(t.id_fecha = f.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".bloque_turno bt on (t.id_bloque_turno = bt.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia d on (bt.id_dia = d.id) "
	// + " WHERE up.usuario = u.id and t.id = up.primer_turno "
	// + " ORDER BY f.fecha, f.hora asc";
	//
	// List<Object[]> result = em.createNativeQuery(query)
	// .setParameter("fecha_entrada", fecha)
	// .setParameter("id_serv", idServicio)
	// .setParameter("id_espe", idEspecialidad).getResultList();
	//
	// List<ProfesionalPrimerTurno_VO> profesionales = new
	// ArrayList<ProfesionalPrimerTurno_VO>();
	//
	// for (Object[] object : result) {
	// profesionales.add(new ProfesionalPrimerTurno_VO(
	// (BigInteger) object[0], (String) object[1],
	// (String) object[2], (String) object[3],
	// (BigInteger) object[4], (Integer) object[5],
	// (BigInteger) object[6], (Time) object[7], (Time) object[8],
	// (Date) object[9], (Time) object[10], (String) object[11],
	// (BigInteger) object[12], (String) object[13],
	// (Boolean) object[14], (String) object[15]));
	// }
	//
	// return profesionales;
	// }

	/**
	 * Retorna los bloque turnos de un profesional para un servicio determinado
	 * junto con el primer turno libre para el bloque turno correspondiente
	 * 
	 * EX: buscarBloqueTurnosConPrimerTurnoDeUnProfesional
	 * 
	 * @param idProfesional
	 * @param idServicio
	 * @param em
	 * @return
	 */
	public R_BloqueTurnos obtenerBloqueTurnosDeProfesional(
			Integer nroMatricula, Date fecha) {
		R_BloqueTurnos resul = new R_BloqueTurnos();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				//CallableStatement pstmt = con.prepareCall("{call sp_horus_get_franjahorapormat(?,?)}");
				CallableStatement pstmt = con.prepareCall("{call sp_horus_get_turnosactpormat(?,?)}");

				pstmt.setInt(1, nroMatricula.intValue());
				pstmt.registerOutParameter(2, -1);
				pstmt.setString(2, "");

				this.log.info("Llamando: 4.2.2 sp_horus_get_turnosactpormat( "
						+ nroMatricula + " )", new Object[0]);
				System.out.println("Llamando: 4.2.2 sp_horus_get_turnosactpormat( "
						+ nroMatricula + " )");
				
				pstmt.execute();
				
				String sp_horus_get_turnosactpormat = pstmt.getString(2);

				this.log.info("Resultado obtenido: "
						+ sp_horus_get_turnosactpormat, new Object[0]);
				System.out.println("Resultado obtenido: "
						+ sp_horus_get_turnosactpormat);
				
				resul = new GsonBuilder()
						.registerTypeAdapter(BloqueTurno_VO.class,
								new BloqueTurnoHESimpleAdapter())
						.create()
						.fromJson(sp_horus_get_turnosactpormat,
								R_BloqueTurnos.class);
				
				for (BloqueTurno_VO bt : resul.getBloques()) {
					
					bt.setEspecialidadPrestada(new EspecialidadProfesional_VO(new Especialidad_VO(resul.getEspecialidad())));
				}
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}

	// public List<ProfesionalBloqueTurnoPrimerTurno_VO>
	// buscarBloqueTurnosConPrimerTurnoDeUnProfesional(
	// Long idProfesional, Long idServicio, EntityManager em) {
	//
	// String query =
	// " SELECT registro.profesional, registro.contrato, registro.id_especialidad, registro.nombre_especialidad, registro.dia,registro.id_bloque, "
	// +
	// "  registro.hora_inicio, registro.hora_fin, registro.categoria, registro.turno, f.fecha, f.hora, registro.precio_visita, registro.ver_aviso, registro.mens_aviso "
	// + " FROM   ("
	// + "    SELECT cp.id_profesional as profesional, "
	// + " 		cp.id as contrato, "
	// + " 		ep.id_especialidad as id_especialidad, "
	// + " 		espe.nombre as nombre_especialidad, "
	// + " 		d.nombre as dia, "
	// + " 		bt.id as id_bloque, "
	// + " 		bt.hora_inicio, "
	// + " 		bt.hora_fin, "
	// + " 		ep.primera_visita as precio_visita, "
	// + " 		cap.nombre as categoria, " + "(SELECT t.id " + " FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".turno t "
	// + "	inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".fecha f on(t.id_fecha = f.id) "
	// + " 	inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".estado_turno et on (t.id_estado = et.id) "
	// + " WHERE t.id_bloque_turno = bt.id and et.nombre = '"
	// + EstadoTurno.SIN_RESERVAR
	// + "' and f.fecha >= :fecha "
	// + // current_timestamp
	// " and f.fecha not in (SELECT dia_f.fecha "
	// + "FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia_feriado dia_f) "
	// + " and f.fecha not in (SELECT dia_au.fecha "
	// + "FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia_ausente dia_au "
	// + "WHERE id_usuario_ausente = u.id ) "
	// + " ORDER BY f.fecha asc"
	// + " LIMIT 1 offset 0) as turno, "
	// + "		d.numero_semana as ns, "
	// + " 		bt.mostrar_aviso as ver_aviso, "
	// + " 		bt.mensaje_aviso as mens_aviso"
	// + " 	  FROM "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".contrato_profesional cp "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// +
	// ".especialidad_profesional ep  on (ep.id_contrato_profesional = cp.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".categoria_profesional cap on (ep.id_categoria_profesional = cap.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".agenda ag on (cp.id = ag.id_contrato) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".dia d on (d.id_agenda = ag.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".bloque_turno bt on (bt.id_dia = d.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".rol r on (cp.id_profesional = r.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".usuario u on (r.id_usuario = u.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".especialidad espe on (espe.id = ep.id_especialidad) "
	// +
	// " 	  WHERE ep.id_servicio = :id_serv AND cp.id_profesional = :id_prof AND "
	// +
	// " current_timestamp BETWEEN cp.fecha_desde AND cp.fecha_hasta and bt.activo"
	// + " 	  ) AS registro "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".turno tur on (registro.turno = tur.id) "
	// + " inner join "
	// + VariablesGlobales.DEFAULT_SCHEMA
	// + ".fecha f on(tur.id_fecha = f.id) " + " ORDER BY ns ";
	//
	// List<Object[]> result = em.createNativeQuery(query)
	// .setParameter("id_serv", idServicio)
	// .setParameter("id_prof", idProfesional)
	// .setParameter("fecha", new Date()).getResultList();
	// List<ProfesionalBloqueTurnoPrimerTurno_VO> profesionales = new
	// ArrayList<ProfesionalBloqueTurnoPrimerTurno_VO>();
	//
	// for (Object[] object : result) {
	// profesionales.add(new ProfesionalBloqueTurnoPrimerTurno_VO(
	// (BigInteger) object[0], (BigInteger) object[1],
	// (BigInteger) object[2], (String) object[3],
	// (String) object[4], (BigInteger) object[5],
	// (Time) object[6], (Time) object[7], (String) object[8],
	// (BigInteger) object[9], (Date) object[10],
	// (Time) object[11], (Float) object[12],
	// (Boolean) object[13], (String) object[14]));
	// }
	//
	// return profesionales;
	//
	// }

	/**
	 * Retorna los turnos LIBRES de un bloque turno para una fecha determinada,
	 * 
	 * @param idServicio
	 * @param fecha
	 *            La fecha pasada como parametro debe coincidir con la fecha de
	 *            los turnos
	 * @param em
	 * @return
	 */
	public List<Turno_VO> turnosLibresDeBloqueTurnoParaUnaFecha(Long idBT,
			Date fecha, EntityManager em) {

		String query = " SELECT new " + Turno_VO.class.getCanonicalName()
				+ "(t) " + " FROM Turno t "
				+ " WHERE t.bloqueTurno.id = :idBT "
				+ "		AND t.fecha.fecha = :fecha "
				+ "		AND t.estado.nombre = :estado"
				+ " ORDER BY t.fecha.hora asc ";

		return em.createQuery(query).setParameter("idBT", idBT)
				.setParameter("fecha", fecha)
				.setParameter("estado", EstadoTurno.SIN_RESERVAR)
				.getResultList();

	}

	public List<Date> fechasConTurnoLibreEntreDosFechas(Long idProfesional,
			Long idServicio, Date fechaDesde, Date fechaHasta, EntityManager em) {
		String query = " SELECT t.fecha.fecha " + " FROM Turno t "
				+ "		inner join t.bloqueTurno bt " + "		inner join bt.dia d "
				+ "		inner join d.agenda  ag " + " 		inner join ag.contrato c "
				+ "		inner join c.especialidadProfesional ep " +

				" WHERE t.estado.nombre = :estado "
				+ "		AND t.fecha.fecha BETWEEN :fechaDesde AND :fechaHasta "
				+ "		AND c.profesional.id = :profesional "
				+ "		AND ep.servicio.id = :servicio  " +

				" GROUP BY t.fecha.fecha " + " ORDER BY t.fecha.fecha ";

		return em.createQuery(query)
				.setParameter("estado", EstadoTurno.SIN_RESERVAR)
				.setParameter("fechaDesde", fechaDesde)
				.setParameter("fechaHasta", fechaHasta)
				.setParameter("profesional", idProfesional)
				.setParameter("servicio", idServicio).getResultList();

	}

	public List<Date> fechasQueAtiendeEntreDosFechas(Long idProfesional,
			Long idServicio, Date fechaDesde, Date fechaHasta, EntityManager em) {
		String query = " SELECT t.fecha.fecha "
				+ " FROM Turno t inner join t.bloqueTurno bt inner join bt.dia d inner join d.agenda  ag "
				+ " inner join ag.contrato c inner join c.especialidadProfesional ep "
				+ " WHERE t.fecha.fecha BETWEEN :fechaDesde AND :fechaHasta AND "
				+ " c.profesional.id = :profesional AND ep.servicio.id = :servicio  "
				+ " GROUP BY t.fecha.fecha " + " ORDER BY t.fecha.fecha ";

		return em.createQuery(query).setParameter("fechaDesde", fechaDesde)
				.setParameter("fechaHasta", fechaHasta)
				.setParameter("profesional", idProfesional)
				.setParameter("servicio", idServicio).getResultList();
	}

	/**
	 * Turnos reservados de un paciente en un servicio determinado.
	 * 
	 * @param idPaciente
	 * @param idServicio
	 * @param em
	 * @return
	 */
	public List<TurnoReservado_DTO> turnosReservadosDePaciente(Long idPaciente,
			Long idServicio, EntityManager em) {
		String query = " SELECT new "
				+ TurnoReservado_DTO.class.getCanonicalName()
				+ "(t.id, t.version, t.numero, t.fecha.hora, d.nombre, c.profesional.usuario.apellido, ep.especialidad.nombre) "
				+ " FROM Turno t inner join t.bloqueTurno bt inner join bt.dia d inner join d.agenda  ag "
				+ " inner join ag.contrato c inner join c.especialidadProfesional ep "
				+ " WHERE t.estado.nombre = :estado AND t.estado.paciente.id = :id "
				+ " AND ep.servicio.id = :servicio "
				+ " ORDER BY t.fecha.fecha ";

		return em.createQuery(query).setParameter("id", idPaciente)
				.setParameter("estado", EstadoTurno.RESERVADO)
				.setParameter("servicio", idServicio).getResultList();

	}

	/**
	 * En caso de que la clase tenga complejidad, sera necesario que el dao
	 * correspondiente sea el encargado de transformar el value object en la
	 * entidad los objetos
	 * 
	 * @param em
	 * 
	 * @param valueObject
	 * @return El objeto correspondiente al value object
	 */
	// @Override
	// protected Object getObjeto(I_ValueObject valueObject) {
	//
	// Object o = null;
	//
	// // Si estoy persistiendo un rol en particular, el usuario no se
	// // convertira cuando le haga toObject
	// if (valueObject instanceof Turno_VO) {

	// Turno_VO t_vo = (Turno_VO) valueObject;
	// Turno t;

	// if (t_vo.getId() != null) {
	// t = this.getEntityManager().find(Turno.class, t_vo.getId());
	// } else {
	// t = t_vo.toObject();
	// }
	//
	// if (t_vo.estaReservado() || t_vo.estaPresente()
	// || t_vo.estaTomado()) {
	//
	// // Solo esta reservado
	// TurnoReservado estado = (TurnoReservado) t.getEstado();
	//
	// Paciente pac = this.getEntityManager().find(Paciente.class,
	// estado.getPaciente().getId());
	// estado.setPaciente(pac);
	//
	// if (estado.getProductoObraSocialPaciente() != null) {
	//
	// ProductoObraSocialPaciente prod = this.getEntityManager()
	// .find(ProductoObraSocialPaciente.class,
	// estado.getProductoObraSocialPaciente()
	// .getId());
	// estado.setProductoObraSocialPaciente(prod);
	//
	// }
	//
	// Rol r = this.getEntityManager().find(Rol.class,
	// estado.getPersonalAsignoTurno().getId());
	// estado.setPersonalAsignoTurno(r);
	//
	// if (t_vo.estaPresente()) {
	// // Esta presente
	//
	// TurnoPresente tp = (TurnoPresente) t.getEstado();
	//
	// Rol pp = this.getEntityManager().find(Rol.class,
	// tp.getPersonalPresentoTurno().getId());
	// tp.setPersonalPresentoTurno(pp);
	//
	// } else if (t_vo.estaTomado()) {
	//
	// TurnoTomado tt = (TurnoTomado) t.getEstado();
	//
	// Rol pp = this.getEntityManager().find(Rol.class,
	// tt.getPersonalPresentoTurno().getId());
	// tt.setPersonalPresentoTurno(pp);
	//
	// Rol pt = this.getEntityManager().find(Rol.class,
	// tt.getPersonalTomoTurno().getId());
	// tt.setPersonalTomoTurno(pt);
	// }

	// }else{ Esta sin reservar, no hay nada que recuperar
	// }
	//
	// o = t;
	//
	// } else {
	// o = valueObject.toObject();
	// }

	// return o;

	// }

	public List<Turno_VO> turnosDeBloqueTurnoParaUnaFechaComoPaciente(Long id,
			Date fecha, Long idPaciente, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String NO_USAR = "No usar";

	/**
	 * Devuelve los turnos RESERVADOS entre dos fechas segun la FECHA EN QUE SE
	 * RESERVO
	 * 
	 */
	public List<TurnoReservado_DTO> pc_getTurnosReservadosEntreFechas(
			EntityManager em, Date fechaDesde, Date fechaHasta,
			String estadoTurno, String especialidad, String dia, Long idSucu) {

		try {
			String query = " SELECT new "
					+ TurnoReservado_DTO.class.getCanonicalName()
					+ "(t.id, t.numero, f.fecha, f.hora, t.duracion, t.sobreTurno, "
					+ "est.nombre, d.nombre, presta.id, presta.nombre, "
					+ "profe.apellido,  profe.nombres, c.profesional.id, profe.nombreUsuario, "
					+ "e.nombre, e.id, prodOS.nombre, "
					+
					// Turno reservado
					"est.nombreUsuarioPersonalAsignoTurno, est.fechaEstablecido,  "
					+
					// Turno presente
					"est.nombrePersonalRegistroPresencia, est.horaPresentoPaciente, "
					+
					// Turno tomado
					"est.nombrePersonalRegistroToma, est.horaTomoPaciente, est.tiempoDeEspera, "
					+ "pac.id, pac.apellido, pac.nombres, pac.nombreUsuario  ) "

					+ " FROM Turno t "
					+ "	INNER JOIN t.estado est "
					+ "	INNER JOIN t.fecha f "
					+ "	INNER JOIN t.bloqueTurno bt "
					+ "	INNER JOIN bt.dia d"
					+ "	INNER JOIN bt.especialidadPrestada ep"
					+ "	INNER JOIN ep.especialidad e"
					+ "	INNER JOIN ep.contratoProfesional c"
					+ "	INNER JOIN c.profesional.usuario profe "
					+ "	INNER JOIN ep.servicio.area.sucursal sucu "
					+ "   LEFT JOIN sucu.circulosDeConfianza cc "
					+ " 	LEFT JOIN cc.sucursales cs "
					+ "	LEFT JOIN est.prestacion presta "
					+ "	LEFT JOIN est.productoObraSocialPaciente.productoObraSocial prodOS "
					+ "	LEFT JOIN est.paciente.usuario pac "
					+ " WHERE sucu.id = :idSucu "
					+ "	AND est.fechaEstablecido BETWEEN :fechaDesde AND :fechaHasta";

			Map<String, Object> condiciones = new HashMap<String, Object>();
			condiciones.put("idSucu", idSucu);
			condiciones.put("fechaDesde", fechaDesde);
			condiciones.put("fechaHasta", fechaHasta);

			if (estadoTurno != null && !estadoTurno.equalsIgnoreCase(NO_USAR)) {
				query += "	AND t.estado.nombre = :estado ";
				condiciones.put("estado", estadoTurno);
			}
			if (especialidad != null && !especialidad.equalsIgnoreCase(NO_USAR)) {
				query += "	AND e.nombre = :especialidad ";
				condiciones.put("especialidad", especialidad);
			}
			if (dia != null && !dia.equalsIgnoreCase(NO_USAR)) {
				query += "	AND d.nombre = :dia ";
				condiciones.put("dia", dia);
			}
			query += " ORDER BY f.fecha ";

			Query q = em.createQuery(query);
			// Si hubo condiciones, las itera
			for (String llave : condiciones.keySet()) {
				q.setParameter(llave, condiciones.get(llave));
			}

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Devuelve los turnos RESERVADOS entre dos fechas segun la FECHA DEL TURNO
	 * 
	 */
	public List<TurnoReservado_DTO> pc_getTurnosPorFechaEntreFechas(
			EntityManager em, Date fechaDesde, Date fechaHasta,
			String estadoTurno, String especialidad, String dia, Long idSucu) {

		try {
			String query = " SELECT new "
					+ TurnoReservado_DTO.class.getCanonicalName()
					+ "(t.id, t.numero, f.fecha, f.hora, t.duracion, t.sobreTurno, "
					+ "est.nombre, d.nombre, presta.id, presta.nombre, "
					+ "profe.apellido,  profe.nombres, c.profesional.id, profe.nombreUsuario, "
					+ "e.nombre, e.id, prodOS.nombre, "
					+
					// Turno reservado
					"est.nombreUsuarioPersonalAsignoTurno, est.fechaEstablecido,  "
					+
					// Turno presente
					"est.nombrePersonalRegistroPresencia, est.horaPresentoPaciente, "
					+
					// Turno tomado
					"est.nombrePersonalRegistroToma, est.horaTomoPaciente, est.tiempoDeEspera, "
					+ "pac.id, pac.apellido, pac.nombres, pac.nombreUsuario  ) "

					+ " FROM Turno t "
					+ "	INNER JOIN t.estado est "
					+ "	INNER JOIN t.fecha f "
					+ "	INNER JOIN t.bloqueTurno bt "
					+ "	INNER JOIN bt.dia d"
					+ "	INNER JOIN bt.especialidadPrestada ep"
					+ "	INNER JOIN ep.especialidad e"
					+ "	INNER JOIN ep.contratoProfesional c"
					+ "	INNER JOIN c.profesional.usuario profe "
					+ "	INNER JOIN ep.servicio.area.sucursal sucu "
					+ "   LEFT JOIN sucu.circulosDeConfianza cc "
					+ " 	LEFT JOIN cc.sucursales cs "
					+ "	LEFT JOIN est.prestacion presta "
					+ "	LEFT JOIN est.productoObraSocialPaciente.productoObraSocial prodOS "
					+ "	LEFT JOIN est.paciente.usuario pac "
					+ " WHERE sucu.id = :idSucu "
					+ "	AND f.fecha BETWEEN :fechaDesde AND :fechaHasta";

			Map<String, Object> condiciones = new HashMap<String, Object>();
			condiciones.put("idSucu", idSucu);
			condiciones.put("fechaDesde", fechaDesde);
			condiciones.put("fechaHasta", fechaHasta);

			if (estadoTurno != null && !estadoTurno.equalsIgnoreCase(NO_USAR)) {
				query += "	AND t.estado.nombre = :estado ";
				condiciones.put("estado", estadoTurno);
			}
			if (especialidad != null && !especialidad.equalsIgnoreCase(NO_USAR)) {
				query += "	AND e.nombre = :especialidad ";
				condiciones.put("especialidad", especialidad);
			}
			if (dia != null && !dia.equalsIgnoreCase(NO_USAR)) {
				query += "	AND d.nombre = :dia ";
				condiciones.put("dia", dia);
			}
			query += " ORDER BY f.fecha ";

			Query q = em.createQuery(query);
			// Si hubo condiciones, las itera
			for (String llave : condiciones.keySet()) {
				q.setParameter(llave, condiciones.get(llave));
			}

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Distribucion de turnos que se brindan por dia
	 * 
	 */
	public List<TurnoReservado_DTO> pc_getDistribucionDeOfertaDeTurnosEntreFechas(
			EntityManager em, Date fechaDesde, Date fechaHasta,
			String estadoTurno, String especialidad, String dia, Long idSucu) {

		try {

			String query = " SELECT new "
					+ ItemPanelControl_DTO.class.getCanonicalName()
					+ " (d.nombre, d.numero_semana," + " f.hora, "
					+ " COUNT(t.id) )" + " FROM Turno t "
					+ "	INNER JOIN t.estado est " + "	INNER JOIN t.fecha f "
					+ "	INNER JOIN t.bloqueTurno bt " + "	INNER JOIN bt.dia d"
					+ "	INNER JOIN bt.especialidadPrestada ep"
					+ "	INNER JOIN ep.servicio.area.sucursal sucu "
					+ "   LEFT JOIN sucu.circulosDeConfianza cc "
					+ " 	LEFT JOIN cc.sucursales cs "
					+ " WHERE sucu.id = :idSucu "
					+ "	AND f.fecha BETWEEN :fechaDesde AND :fechaHasta";

			Map<String, Object> condiciones = new HashMap<String, Object>();
			condiciones.put("idSucu", idSucu);
			condiciones.put("fechaDesde", fechaDesde);
			condiciones.put("fechaHasta", fechaHasta);

			if (especialidad != null && !especialidad.equalsIgnoreCase(NO_USAR)) {
				query += "	AND e.nombre = :especialidad ";
				condiciones.put("especialidad", especialidad);
			}
			if (dia != null && !dia.equalsIgnoreCase(NO_USAR)) {
				query += "	AND d.nombre = :dia ";
				condiciones.put("dia", dia);
			}
			query += " GROUP BY d.nombre, d.numero_semana, f.hora"
					+ " ORDER BY d.numero_semana, f.hora";

			Query q = em.createQuery(query);
			// Si hubo condiciones, las itera
			for (String llave : condiciones.keySet()) {
				q.setParameter(llave, condiciones.get(llave));
			}

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Profesional_VO recupurarProfesional(EntityManager em, Turno_VO turno) {
		// turno.getBloqueTurno().getEspecialidadPrestada().getContratoProfesional().getProfesional()
		String query = " SELECT new " + Profesional_VO.class.getCanonicalName()
				+ " (p) " + " FROM Turno t " + "	INNER JOIN t.bloqueTurno bt "
				+ "	INNER JOIN bt.especialidadPrestada ep"
				+ "	INNER JOIN ep.contratoProfesional c"
				+ "	INNER JOIN c.profesional p " + "	WHERE t.id=:idTurno ";
		return (Profesional_VO) em.createQuery(query)
				.setParameter("idTurno", turno.getId()).getSingleResult();
	}

	/**
	 * Retorna los turnos de un bloque turno para una fecha determinada,
	 * 
	 * @param idServicio
	 * @param fecha
	 *            La fecha pasada como parametro debe coincidir con la fecha de
	 *            los turnos
	 * @param em
	 * @return
	 */
	public R_GetTurnosDesde turnosDeBloqueTurnoParaUnaFecha(Integer matricula,
			String horaDesde, Date fecha, String nombreDia, String serv) {
		R_GetTurnosDesde resul = new R_GetTurnosDesde();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_turnosDesdeProf(?,?,?,?)}");

				pstmt.setInt(1, matricula.intValue());
				pstmt.setString(2,
						new SimpleDateFormat("dd/MM/yyyy").format(fecha));
				pstmt.setString(3, horaDesde);

				pstmt.registerOutParameter(4, -1);
				pstmt.setString(4, "");

				
				this.log.info(
						"Llamando: 4.3.1 sp_horus_get_turnosDesdeProf("
								+ matricula
								+ ", "
								+ new SimpleDateFormat("dd/MM/yyyy")
										.format(fecha) + ", " + horaDesde + ")",
						new Object[0]);
				System.out.println("Llamando: 4.3.1 sp_horus_get_turnosDesdeProf("+ matricula + ", " + 
						new SimpleDateFormat("dd/MM/yyyy").format(fecha) + ", " + 
						horaDesde + ")");
				
				//Ejecuto el llamado
				pstmt.execute();
				String sp_horus_get_turnosDesde = pstmt.getString(4);

				this.log.info(
						"Resultado obtenido: " + sp_horus_get_turnosDesde,
						new Object[0]);
				System.out.println("Resultado obtenido: " + sp_horus_get_turnosDesde);
				resul = new GsonBuilder()
							.registerTypeAdapter(R_GetTurnosDesde.class,
									new R_GetTurnosDesdeSimpleAdapter())
							.create()
							.fromJson(sp_horus_get_turnosDesde,
								R_GetTurnosDesde.class);
				
				if (resul != null) {
					for (TurnoReservado_VO turno : resul.getLista()) {
						turno.setMatricula(matricula);
						turno.setFecha(new SimpleDateFormat("dd/MM/yyyy")
								.parse(resul.getFechaTurnos()));
						turno.setHoraDesde(new SimpleDateFormat("HH:mm")
								.parse(horaDesde));
						turno.setNombre(nombreDia);
						turno.setFrecuencia(resul.getFrecuencia());
						turno.setServicio(serv);
					}
				} else {
					resul = new R_GetTurnosDesde();
					resul.setMensaje("No se pudo obtener los turnos del profesional con matr�cula: "
							+ matricula);
					resul.setOk(Boolean.valueOf(false));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}

	public R_GetTurnosDesde turnosDeBloqueTurnoParaUnaFechaReducido(Integer matricula,
			String horaDesde, Date fecha, String nombreDia, String serv) {
		R_GetTurnosDesde resul = new R_GetTurnosDesde();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				
				//
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_turnosDesdeRedu(?,?,?,?)}");

				pstmt.setInt(1, matricula.intValue());
				pstmt.setString(2,
						new SimpleDateFormat("dd/MM/yyyy").format(fecha));
				pstmt.setString(3, horaDesde);

				pstmt.registerOutParameter(4, -1);
				pstmt.setString(4, "");

				//
				this.log.info(
						"Llamando: 4.3.1 sp_horus_get_turnosDesde("
								+ matricula
								+ ", "
								+ new SimpleDateFormat("dd/MM/yyyy")
										.format(fecha) + ", " + horaDesde + ")",
						new Object[0]);
				pstmt.execute();
				String sp_horus_get_turnosDesde = pstmt.getString(4);

				//
				this.log.info(
						"Resultado obtenido: " + sp_horus_get_turnosDesde,
						new Object[0]);

				//
				resul =
				new GsonBuilder()
						.registerTypeAdapter(R_GetTurnosDesde.class,
								new R_GetTurnosDesdeSimpleAdapter())
						.create()
						.fromJson(sp_horus_get_turnosDesde,
								R_GetTurnosDesde.class);
				if (resul != null) {
					for (TurnoReservado_VO turno : resul.getLista()) {
						turno.setMatricula(matricula);
						turno.setFecha(new SimpleDateFormat("dd/MM/yyyy")
								.parse(resul.getFechaTurnos()));
						turno.setHoraDesde(new SimpleDateFormat("HH:mm")
								.parse(horaDesde));
						turno.setNombre(nombreDia);
						turno.setFrecuencia(resul.getFrecuencia());
						turno.setServicio(serv);
					}
				} else {
					resul = new R_GetTurnosDesde();
					resul.setMensaje("No se pudo obtener los turnos del profesional con matrícula: "
							+ matricula);
					resul.setOk(Boolean.valueOf(false));
				}
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}
	
	// public List<TurnoReservado_DTO> turnosDeBloqueTurnoParaUnaFecha(
	// Long idSucu, Long idBT, Date fecha, EntityManager em) {
	//
	// String query = " SELECT new "
	// + TurnoReservado_DTO.class.getCanonicalName()
	//
	// + "(t.id, t.numero, f.fecha, f.hora, t.duracion, t.sobreTurno, "
	// + "est.nombre, d.nombre, presta.id, presta.nombre, "
	// +
	// "profe.apellido,  profe.nombres, c.profesional.id, profe.nombreUsuario, "
	// + "e.nombre, e.id, prodOS.nombre, "
	// +
	// // Turno reservado
	// "est.nombreUsuarioPersonalAsignoTurno, est.fechaEstablecido,  "
	// +
	// // Turno presente
	// "est.nombrePersonalRegistroPresencia, est.horaPresentoPaciente, "
	// +
	// // Turno tomado
	// "est.nombrePersonalRegistroToma, est.horaTomoPaciente, est.tiempoDeEspera, "
	// + "pac.id, pac.apellido, pac.nombres, pac.nombreUsuario  ) "
	//
	// + " FROM Turno t "
	// + "	INNER JOIN t.fecha f"
	// + "	INNER JOIN t.bloqueTurno bt "
	// + "	INNER JOIN bt.dia d"
	// + "	INNER JOIN bt.especialidadPrestada ep"
	// + "	INNER JOIN ep.especialidad e"
	// + "	INNER JOIN ep.contratoProfesional c"
	// + "	INNER JOIN c.profesional.usuario profe "
	// + "	INNER JOIN t.estado est "
	// + "	LEFT JOIN est.prestacion presta "
	// + "	LEFT JOIN est.productoObraSocialPaciente.productoObraSocial prodOS "
	// + "	LEFT JOIN est.paciente.usuario pac "
	//
	// + " WHERE bt.id = :idBT " + "	AND f.fecha = :fecha "
	//
	// + " ORDER BY f.fecha ";
	//
	// return em.createQuery(query).setParameter("idBT", idBT)
	// .setParameter("fecha", fecha).getResultList();
	// }

	/**
	 * Cancelar turno
	 * 
	 * @param turno
	 * @param usuario
	 * @return R_CancelarTurno
	 */
	public R_CancelarTurno cancelarTurno(TurnoReservado_VO turno, String usuario) {
		R_CancelarTurno resul = new R_CancelarTurno();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_cancelarturno(?,?,?,?,?,?)}");

				Object[] s = new Object[7];

				s[0] = usuario;
				s[1] = turno.getMatricula();
				s[2] = turno.getStr_fecha();
				s[3] = turno.getStr_horaDesde();
				s[4] = turno.getNumero();

				pstmt.setString(1, (String) s[0]);
				pstmt.setInt(2, ((Integer) s[1]).intValue());
				pstmt.setString(3, (String) s[2]);
				pstmt.setString(4, (String) s[3]);
				pstmt.setInt(5, ((Integer) s[4]).intValue());
				pstmt.registerOutParameter(6, -1);
				pstmt.setString(6, "");

				this.log.info(
						"Llamando: 4.1.5 sp_horus_set_cancelarturno(#0,#1,#2,#3,#4)",
						new Object[] { s[0], s[1], s[2], s[3], s[4] });
				pstmt.execute();
				String sp_horus_set_cancelarTurno = pstmt.getString(6);

				this.log.info("Resultado obtenido: "
						+ sp_horus_set_cancelarTurno, new Object[0]);

				resul = new Gson().fromJson(sp_horus_set_cancelarTurno,
						R_CancelarTurno.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}

	/**
	 * Agregar a lista de espera
	 * 
	 * @param usuario
	 * @param matricula
	 * @return R_SetTurnosInsatisfechos
	 */
	public R_SetTurnosInsatisfechos agregarAListaDeEspera(String usuario,
			Integer matricula) {
		R_SetTurnosInsatisfechos resul = new R_SetTurnosInsatisfechos();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_turnosInsatis(?,?,?)}");

				Object[] s = new Object[3];
				s[0] = usuario;
				s[1] = matricula;

				pstmt.setString(1, (String) s[0]);
				pstmt.setInt(2, ((Integer) s[1]).intValue());
				pstmt.registerOutParameter(3, -1);
				pstmt.setString(3, "");

				this.log.info(
						"Llamando: 4.1.4 sp_horus_set_turnosInsatis(#0, #1)",
						new Object[] { s[0], s[1] });
				pstmt.execute();
				String sp_horus_set_turnosInsatis = pstmt.getString(3);

				this.log.info("Resultado obtenido: "
						+ sp_horus_set_turnosInsatis, new Object[0]);

				resul = new Gson().fromJson(sp_horus_set_turnosInsatis,
						R_SetTurnosInsatisfechos.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}

	public R_CancelarTurno liberarTurno(BloqueTurno_VO bt, Integer nroTurno,
			String str_fecha, String usuario) {
		R_CancelarTurno resul = new R_CancelarTurno();

		Connection con = getConexionHE(BD_USER_HORUS_CONSULTAS, BD_PASS_HORUS_CONSULTAS);
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_liberarTurno(?,?,?,?,?,?)}");

				Object[] s = new Object[7];

				s[0] = usuario;
				s[1] = bt.getMatricula();

				Date fecha = new SimpleDateFormat("dd/MM/yyyy")
						.parse(str_fecha);
				s[2] = new SimpleDateFormat("yyMMdd").format(fecha);
				s[3] = bt.getStr_horaInicio();
				s[4] = nroTurno;

				pstmt.setString(1, (String) s[0]);
				pstmt.setInt(2, ((Integer) s[1]).intValue());
				pstmt.setString(3, (String) s[2]);
				pstmt.setString(4, (String) s[3]);
				pstmt.setInt(5, ((Integer) s[4]).intValue());

				pstmt.registerOutParameter(6, -1);
				pstmt.setString(6, "");

				this.log.info(
						"Llamando: 4.1.4 sp_horus_set_liberarTurno(#0,#1,#2,#3,#4)",
						new Object[] { s[0], s[1], s[2], s[3], s[4] });
				pstmt.execute();
				String sp_horus_set_liberarTurno = pstmt.getString(6);

				this.log.info("Resultado obtenido: "
						+ sp_horus_set_liberarTurno, new Object[0]);

				resul = new Gson().fromJson(
						sp_horus_set_liberarTurno, R_CancelarTurno.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		} finally {
			try {
				con.rollback();
				con.close();

				this.log.info("Se cerro las conexiones", new Object[0]);
			} catch (SQLException sqe) {
				this.log.error("No se pudo cerrar", new Object[0]);
				sqe.printStackTrace();
			}
		}
		return resul;
	}
}