package ar.org.hospitalespanol.dao.core.usuarios.roles.pacientes;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.core.especialidades.Especialidad;
import ar.org.hospitalespanol.model.core.obrasSociales.ObraSocial;
import ar.org.hospitalespanol.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.model.core.usuarios.roles.TipoDeRol;
import ar.org.hospitalespanol.model.core.usuarios.roles.pacientes.Paciente;
import ar.org.hospitalespanol.model.historiaClinica.derivaciones.Derivacion;
import ar.org.hospitalespanol.model.historiaClinica.derivaciones.estados.EstadoDerivacion;
import ar.org.hospitalespanol.model.turnos.Turno;
import ar.org.hospitalespanol.model.turnos.Visita;
import ar.org.hospitalespanol.model.turnos.estadosTurno.EstadoTurno;
import ar.org.hospitalespanol.model.turnos.estadosTurno.TurnoReservado;
import ar.org.hospitalespanol.utils.adapters.pacientes.PacienteTurnoSimpleAdapter;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.org.hospitalespanol.vo.turnos.PacienteTurno_VO;
import ar.org.hospitalespanol.vo.turnos.Turno_VO;
import ar.org.hospitalespanol.ws.respuestas.datosDelPaciente.PacienteHESimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
@SuppressWarnings("unchecked")
public class DAO_Paciente extends DAO<Paciente_VO> {

	// private Long idSucursal;

	public DAO_Paciente() {
		this.setQueryEncabezado("SELECT new "
				+ Paciente_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	// public DAO_Paciente(Long idSuc, Boolean enCirculo){
	//
	// this.setEnCirculo(enCirculo);
	// this.setIdSucursal(idSuc);
	//
	// String from =
	// " FROM "+Paciente.class.getCanonicalName()+" "+this.getIdClass()+" " +
	// " INNER JOIN "+this.getIdClass()+".perfiles per "+
	// " INNER JOIN per.servicio.area.sucursal sucu " +
	// " INNER JOIN "+this.getIdClass()+".usuario u ";
	// if (enCirculo){
	// from += " INNER JOIN sucu.circulosDeConfianza cc " +
	// " INNER JOIN cc.sucursales cs ";
	// this.cond="cs";
	// }
	//
	// this.setQueryEncabezado("SELECT DISTINCT new "+RolCirculoDeConfianza_VO.class.getCanonicalName()+"("+this.getIdClass()+".id, "+this.cond+".id, u.apellido, u.nombres, u.nroDocumento, u.sexo) "+from);
	// this.setQueryCondiciones(" WHERE "+this.cond+".id = :idSucu ");
	// this.setQueryFinal(" ORDER BY u.apellido, u.nombres, u.nroDocumento");
	//
	// this.getCondiciones().put("idSucu", getIdSucursal());
	// }

	// @Override
	// public void resetQuery(){
	//
	// String from =
	// " FROM "+Paciente.class.getCanonicalName()+" "+this.getIdClass()+" " +
	// " INNER JOIN "+this.getIdClass()+".perfiles per "+
	// " INNER JOIN per.servicio.area.sucursal sucu " +
	// " INNER JOIN "+this.getIdClass()+".usuario u ";
	// if (getEnCirculo()){
	// from += " INNER JOIN sucu.circulosDeConfianza cc " +
	// " INNER JOIN cc.sucursales cs ";
	// this.cond="cs";
	// }
	//
	// this.setQueryEncabezado("SELECT DISTINCT new "+RolCirculoDeConfianza_VO.class.getCanonicalName()+"("+this.getIdClass()+".id, "+this.cond+".id, u.apellido, u.nombres, u.nroDocumento, u.sexo) "+from);
	// this.setQueryCondiciones(" WHERE "+this.cond+".id = :idSucu ");
	// this.setQueryFinal(" ORDER BY u.apellido, u.nombres, u.nroDocumento");
	//
	// this.getCondiciones().put("idSucu", getIdSucursal());
	// }
	//
	// @Override
	// public Integer contarTotal(EntityManager em) {
	//
	// String from =
	// " FROM "+Paciente.class.getCanonicalName()+" "+this.getIdClass()+" " +
	// " INNER JOIN "+this.getIdClass()+".perfiles per "+
	// " INNER JOIN per.servicio.area.sucursal sucu " +
	// " INNER JOIN "+this.getIdClass()+".usuario u ";
	//
	// if (getEnCirculo()){
	// from += " INNER JOIN sucu.circulosDeConfianza cc " +
	// " INNER JOIN cc.sucursales cs ";
	// this.cond="cs";
	// }
	//
	// String resul =
	// "SELECT COUNT("+this.getIdClass()+".id) "+from+" WHERE "+this.cond+".id = "+getIdSucursal();
	//
	// return ((Long)em.createQuery(resul).getSingleResult()).intValue();
	// }

	@Override
	protected Class getClazz() {
		return Paciente.class;
	}

	@Override
	public String getIdClass() {
		return "paciente";
	}

	// public void setQueryCondiciones(String queryCondiciones) {
	//
	// if (queryCondiciones.equals("")){
	// queryCondiciones = " WHERE "+this.cond+".id = :idSucu ";
	//
	// }else if (!queryCondiciones.contains(""+this.cond+".id") ){
	// queryCondiciones = queryCondiciones+" AND "+this.cond+".id = :idSucu ";
	// }
	//
	// this.getCondiciones().put("idSucu", getIdSucursal());
	//
	// this.queryCondiciones = queryCondiciones;
	// }

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
	@Override
	protected Object getObjeto(I_ValueObject valueObject) {

		Object o = null;

		// Si estoy persistiendo un rol en particular, el usuario no se
		// convertira cuando le haga toObject
		if (valueObject instanceof Paciente_VO) {

			Paciente_VO p_vo = (Paciente_VO) valueObject;
			o = p_vo.toObject();
			Paciente p = (Paciente) o;

			Usuario usr = this.getEntityManager().find(Usuario.class,
					p_vo.getUsuario().getId());
			p.setUsuario(usr);

			TipoDeRol tr = this.getEntityManager().find(TipoDeRol.class,
					p_vo.getTipoRol().getId());
			p.setTipoRol(tr);
			// TipoRol tr = new
			// DAO_TipoRol().buscarPorNombre(this.getEntityManager(),
			// Rol.PACIENTE);
			// p.setTipoRol(tr);

			// p.actualizarProductos_OS();

			// p.getHistoriaClinica().setPaciente(p);

		} else {
			o = valueObject.toObject();
		}

		return o;

	}

	public Paciente_VO buscarPacientePorDocumento(EntityManager em,
			Usuario_VO usr) throws NoResultException {

		String query = "SELECT new " + Paciente_VO.class.getCanonicalName()
				+ "(" + this.getIdClass() + ", 0, 1) FROM "
				+ getClazz().getCanonicalName() + " " + this.getIdClass() + " "
				+ "	WHERE " + this.getIdClass()
				+ ".usuario.nroDocumento = :nroDoc " + "		AND "
				+ this.getIdClass() + ".usuario.tipoDocumento = :tipoDoc";

		return (Paciente_VO) em.createQuery(query)
				.setParameter("nroDoc", usr.getNroDocumento())
				.setParameter("tipoDoc", usr.getTipoDocumento())
				.getSingleResult();

	}

	public Paciente_VO buscarUsuarioDePerfil(EntityManager em, Long idPerfil) {
		String query = "SELECT new " + Paciente_VO.class.getCanonicalName()
				+ "(" + this.getIdClass() + ") " + " 	FROM "
				+ getClazz().getCanonicalName() + " " + this.getIdClass() + " "
				+ "	INNER JOIN " + this.getIdClass() + ".perfiles perfi	"
				+ "	WHERE perfi.id  = :idPerfil ";

		return (Paciente_VO) em.createQuery(query)
				.setParameter("idPerfil", idPerfil).getSingleResult();
	}

	// public List<Paciente_VO> buscarPacientesPorNroDocumento(EntityManager em,
	// String valor) {
	// this.setQueryCondiciones("WHERE "+this.getIdClass()+".usuario.nroDocumento LIKE :doc ");
	//
	// getCondiciones().put("doc", "%"+valor.toLowerCase()+"%");
	//
	// return this.listarTodo(em);
	// }

	// public List<Paciente_VO> buscarPacientesPorApellido(EntityManager em,
	// String valor) {
	// this.setQueryCondiciones("WHERE (LOWER("+this.getIdClass()+".usuario.apellido) LIKE :ape OR LOWER("+this.getIdClass()+".usuario.nombres) LIKE :nom)");
	//
	// getCondiciones().put("ape", "%"+valor.toLowerCase()+"%");
	// getCondiciones().put("nom", "%"+valor.toLowerCase()+"%");
	//
	// return this.listarTodo(em);
	//
	// }

	/**************************************************************************************/
	/**************************************************************************************/
	/********************************* VIEJO **************************************/
	/**************************************************************************************/

	public void buscarPacientePorDatosPersonales(Paciente paciente) {

		getCondiciones().clear();
		// boolean hayCondicion = false;

		String query = "SELECT " + this.getIdClass() + " FROM "
				+ Paciente.class.getCanonicalName() + " " + this.getIdClass()
				+ " INNER JOIN " + this.getIdClass() + ".usuario usr";

		// hayCondicion= (paciente.getUsuario().getApellido() != null) ||
		// (paciente.getUsuario().getSexo() != null)||
		// ((paciente.getUsuario().getNroDocumento() !=
		// null)&&(!paciente.getUsuario().getNroDocumento().equals("")))||
		// (!paciente.getUsuario().getNombres().equals("")) ||
		// ((paciente.getUsuario().getNroCUIT() !=
		// null)&&(!paciente.getUsuario().getNroCUIT().equals("P")));
		//
		// if (hayCondicion){
		// query += " WHERE ";
		//
		// if (paciente.getUsuario().getApellido() != null){
		// //Ingreso apellido
		// query += " LOWER(usr.apellido) LIKE :ape " ;
		// getCondiciones().put("ape",
		// paciente.getUsuario().getApellido().toLowerCase()+"%");
		// }
		//
		// if (paciente.getUsuario().getSexo() != null){
		// //Ingreso sexo
		// if (getCondiciones().size()>0){
		// query += " AND ";
		// }
		// query += " LOWER(usr.sexo) = :sex " ;
		// getCondiciones().put("sex",
		// paciente.getUsuario().getSexo().toLowerCase());
		// }
		// if (!paciente.getUsuario().getNombres().equals("")){
		// if (getCondiciones().size()>0){
		// query += " AND ";
		// }
		// query += " LOWER(usr.nombres) LIKE :nom " ;
		// getCondiciones().put("nom",
		// "%"+paciente.getUsuario().getNombres().toLowerCase()+"%");
		// }
		//
		// if ((paciente.getUsuario().getNroDocumento() !=
		// null)&&(!paciente.getUsuario().getNroDocumento().equals(""))){
		// //Ingreso un nro de documento
		// if (getCondiciones().size()>0){
		// query += " AND ";
		// }
		// query += " usr.nroDocumento LIKE :doc " ;
		// getCondiciones().put("doc",
		// "%"+paciente.getUsuario().getNroDocumento()+"%");
		// }
		//
		// if ((paciente.getUsuario().getNroCUIT() !=
		// null)&&(paciente.getUsuario().getNroCUIT().equals("P"))){
		// //No incluye a los provicionales
		// if (getCondiciones().size()>0){
		// query += " AND ";
		// }
		// query += " NOT(usr.nroDocumento LIKE :prov) " ;
		// getCondiciones().put("prov", "P%");
		// }
		// }
		setQueryHQL(query
				+ " ORDER BY usr.apellido, usr.nombres, usr.nombreUsuario ");
	}

	public void buscarPacientePorObraSocial(ObraSocial obraSocial) {
		getCondiciones().clear();
		setQueryHQL("SELECT v FROM "
				+ Paciente.class.getCanonicalName()
				+ " "
				+ this.getIdClass()
				+ " "
				+ " INNER JOIN "
				+ this.getIdClass()
				+ ".obrasSocialesPaciente.obraSocial o"
				+ " INNER JOIN "
				+ this.getIdClass()
				+ ".usuario usr "
				+ " WHERE :idObra = o.id ORDER BY usr.apellido, usr.nombres, usr.nombreUsuario ");

		getCondiciones().put("idObra", obraSocial.getId());

	}

	public static List<Turno> buscarTurnos(EntityManager entityManager,
			Paciente paciente, String estadoTurno) {

		String query = "FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.estado.nombre = :estadoTurno "
				+ "	AND t.estado.paciente.id = :paciente_id"
				+ "	AND t.fecha.fecha > :fechaDeHoy"
				+ " ORDER BY t.fecha.fecha, t.fecha.hora";

		return entityManager.createQuery(query)
				.setParameter("estadoTurno", estadoTurno)
				.setParameter("paciente_id", paciente.getId())
				.setParameter("fechaDeHoy", new Date()).getResultList();
	}

	public static List<Turno> buscarTurnos(EntityManager entityManager,
			Paciente paciente) {

		String query = "FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.estado.paciente.id = :paciente_id"
				+ " ORDER BY t.fecha.fecha, t.fecha.hora";

		return entityManager.createQuery(query)
				.setParameter("paciente_id", paciente.getId()).getResultList();
	}

	public static List<Turno_VO> buscarTurnosDTO(EntityManager entityManager,
			Paciente paciente) {

		String query = "SELECT new " + Turno_VO.class.getCanonicalName()
				+ "(t) FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.estado.paciente.id = :paciente_id"
				+ " ORDER BY t.fecha.fecha, t.fecha.hora";
		return entityManager.createQuery(query)
				.setParameter("paciente_id", paciente.getId()).getResultList();
	}

	public static List<Turno_VO> buscarTurnosAntesDe(EntityManager em,
			Paciente paciente, Date fechaHasta) {

		String query = "SELECT new " + Turno_VO.class.getCanonicalName()
				+ "(t) FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.estado.paciente.id = :paciente_id"
				+ "	AND t.fecha.fecha 	<= :fechaHasta "
				+ "	AND t.fecha.fecha 	> :fechaDesde "
				+ "	AND t.estado.nombre  = :nombreEstado "
				+ " ORDER BY t.fecha.fecha, t.fecha.hora";

		return em.createQuery(query)
				.setParameter("paciente_id", paciente.getId())
				.setParameter("fechaHasta", fechaHasta)
				.setParameter("fechaDesde", new Date())
				.setParameter("nombreEstado", EstadoTurno.RESERVADO)
				.getResultList();

	}

	public static List<Turno> buscarTurnosAntesDe(EntityManager em,
			Date fechaHasta) {

		String query = " FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.fecha.fecha 	<= :fechaHasta "
				+ "	AND t.fecha.fecha 	> :fechaDesde "
				+ "	AND t.estado.nombre  = :nombreEstado "
				+ " ORDER BY t.fecha.fecha, t.fecha.hora";

		return em.createQuery(query).setParameter("fechaHasta", fechaHasta)
				.setParameter("fechaDesde", new Date())
				.setParameter("nombreEstado", EstadoTurno.RESERVADO)
				.getResultList();

	}

	/**
	 * Recupera los turnos del paciente
	 * 
	 * @param entityManager
	 * @param paciente
	 * @return
	 */
	public List<Turno> buscarTodosLosTurnos(EntityManager entityManager,
			Paciente paciente) {
		String query = "FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.estado.paciente.id = :paciente_id";

		return entityManager.createQuery(query)
				.setParameter("paciente_id", paciente.getId()).getResultList();
	}

	/**
	 * Recupera los turnos del paciente que tengan el estado pasado como
	 * parametro
	 * 
	 * @param entityManager
	 * @param paciente
	 * @param id_estado
	 * @return
	 */
	public Turno buscarTodosLosTurnos(EntityManager entityManager,
			Paciente paciente, Long id_estado) {
		String query = "FROM " + Turno.class.getCanonicalName() + " t "
				+ " WHERE t.estado.paciente.id = :paciente_id"
				+ "	AND t.estado.id = :id_estado";

		return (Turno) entityManager.createQuery(query)
				.setParameter("paciente_id", paciente.getId())
				.setParameter("id_estado", id_estado).getSingleResult();
	}

	/**
	 * Recupera los turnos del paciente
	 */
	public List<EstadoTurno> buscarTodosLosTurnosHistoricos(
			EntityManager entityManager, Paciente paciente) {
		String query = "FROM " + EstadoTurno.class.getCanonicalName() + " et "
				+ " WHERE et.paciente.id = :paciente_id";

		return entityManager.createQuery(query)
				.setParameter("paciente_id", paciente.getId()).getResultList();
	}

	/**
	 * Busca los pacientes en orden alfabetico
	 * 
	 * @param letra
	 */
	public void buscarAlfabeticamente(String letra) {
		getCondiciones().clear();
		setQueryHQL("SELECT " + this.getIdClass() + " FROM "
				+ Paciente.class.getCanonicalName() + " " + this.getIdClass()
				+ " " + " WHERE " + this.getIdClass()
				+ ".usuario.apellido LIKE :ape ORDER BY " + this.getIdClass()
				+ ".usuario.apellido, " + this.getIdClass()
				+ ".usuario.nombres, " + this.getIdClass()
				+ ".usuario.nombreUsuario ");

		getCondiciones().put("ape", letra.toLowerCase() + "%'");

	}

	public List<Visita> buscarTodasLasVisitas(EntityManager em,
			Paciente paciente) {
		String query = "FROM " + Visita.class.getCanonicalName() + " v "
				+ " WHERE v.turno.paciente.id = :paciente_id";

		return em.createQuery(query)
				.setParameter("paciente_id", paciente.getId()).getResultList();
	}

	public static List<TurnoReservado> buscarTurnosReservadosDesde(
			EntityManager em, ProductoObraSocialPaciente opac, Date fechaDesde) {

		String query = "SELECT t.estado FROM " + Turno.class.getCanonicalName()
				+ " t  "
				+ " WHERE t.estado.obraSocialPaciente.id = :obra_paciente_id"
				+ "	AND t.estado.nombre = :estado_turno"
				+ "	AND t.fecha.fecha >= :fecha_desde";

		return em.createQuery(query)
				.setParameter("obra_paciente_id", opac.getId())
				.setParameter("estado_turno", EstadoTurno.RESERVADO)
				.setParameter("fecha_desde", fechaDesde).getResultList();
	}

	/**
	 * Devuelve una lista de pacientes que poseen mail correcto
	 * 
	 * @param em
	 * @return
	 */
	public static List<Paciente> buscarPacientesConMailValido(EntityManager em) {

		String query = "FROM " + Paciente.class.getCanonicalName() + " p "
				+ " WHERE p.usuario.email LIKE '%@%.%' ";

		return em.createQuery(query).getResultList();
	}

	public static Paciente buscarPacientesPorNombreUsuario(EntityManager em,
			String nombreUsuarioPaciente) {
		String query = "FROM " + Paciente.class.getCanonicalName() + " p "
				+ " WHERE p.usuario.nombreUsuario = :nombreUsuario ";
		Paciente p;
		try {
			p = (Paciente) em.createQuery(query)
					.setParameter("nombreUsuario", nombreUsuarioPaciente)
					.getSingleResult();
		} catch (NoResultException e) {
			p = null;
		}

		return p;
	}

	public static List<Paciente> buscarPacientesSinHistoriaClinica(
			EntityManager em) {
		String query = "FROM " + Paciente.class.getCanonicalName() + " p  "
				+ " WHERE p.historiaClinica IS NULL ";

		return em.createQuery(query).setMaxResults(500).getResultList();
	}

	public static Integer cantidadDePacientesSinHistoriaClinica(EntityManager em) {
		String query = "SELECT COUNT(*) FROM "
				+ Paciente.class.getCanonicalName() + " p  "
				+ " WHERE p.historiaClinica IS NULL ";

		return Integer.parseInt(em.createQuery(query).getSingleResult()
				.toString());
	}

	public static Collection<Derivacion> buscarDerivacionesPendientesSegunEspecialidad(
			EntityManager em, Paciente paciente, Especialidad especialidad) {

		String query = " FROM " + Derivacion.class.getCanonicalName() + " d "
				+ " WHERE d.historiaClinica.id = :idHCPaciente"
				+ "		AND d.estado.nombre = :estadoPendiente"
				+ "		AND d.prestacion.especialidad.id = :idEspe"
				+ " ORDER BY d.fechaEmicion";

		return em
				.createQuery(query)
				.setParameter("idHCPaciente",
						paciente.getHistoriaClinica().getId())
				.setParameter("estadoPendiente", EstadoDerivacion.PENDIENTE)
				.setParameter("idEspe", especialidad.getId()).getResultList();
	}

	public Paciente_VO recuperarEntidadDeHE(String tipoDniPaciente,
			String nroDniPaciente) {

		// ProfesionalHE_VO resul;

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_datospaciente(?,?,?)}");

				pstmt.setString(1, tipoDniPaciente);
				pstmt.setString(2, nroDniPaciente);
				pstmt.setString(3, "");
				pstmt.registerOutParameter(3, -1);

				DAO_Utils
						.info(log, "DAO_Paciente", "recuperarEntidadDeHE",getUsuarioAccion(),
								"Llamando: 4.2.3 sp_horus_get_datospaciente("
										+ nroDniPaciente + ", "
										+ tipoDniPaciente + ")");

				pstmt.execute();
				String sp_horus_get_paciente = pstmt.getString(3);

				DAO_Utils.info(log, "DAO_Paciente", "recuperarEntidadDeHE",getUsuarioAccion(),
						"Resultado obtenido: " + sp_horus_get_paciente);

				Paciente_VO resul = getGson().fromJson(sp_horus_get_paciente,
						Paciente_VO.class);
				resul.getUsuario().setTipoDocumento(tipoDniPaciente);
				resul.getUsuario().setNroDocumento(nroDniPaciente);
				
				if (resul.getUsuario().getFechaNacimiento()==null){
					resul.getUsuario().setFechaNacimiento(DAO_Utils.getFechaNaciemientoPorDefault());
				}
				
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

	/* Datos de paciente de turno* */
	public PacienteTurno_VO datosDePacienteTurno(String tipoDniPaciente,
			String nroDniPaciente) {

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_datosDePaciente(?,?,?)}");

				pstmt.setString(1, tipoDniPaciente);
				pstmt.setString(2, nroDniPaciente);
				pstmt.setString(3, "");
				pstmt.registerOutParameter(3, -1);

				DAO_Utils
						.info(log, "DAO_Paciente", "datosDePacienteTurno",getUsuarioAccion(),
								"Llamando: 4.2.3 sp_horus_get_datosDePaciente("
										+ nroDniPaciente + ", "
										+ tipoDniPaciente + ")");

				pstmt.execute();
				String sp_horus_get_paciente = pstmt.getString(3);

				DAO_Utils.info(log, "DAO_Paciente", "datosDePacienteTurno",getUsuarioAccion(),
						"Resultado obtenido: " + sp_horus_get_paciente);

				PacienteTurno_VO resul = getGson().fromJson(
						sp_horus_get_paciente, PacienteTurno_VO.class);
				resul.setTipoDocumento(tipoDniPaciente);
				resul.setNroDocumento(nroDniPaciente);
				if (resul.getFechaNacimiento() != null) {
					resul.setEdad(new SimpleDateFormat("dd/MM/yyyy")
							.parse(resul.getFechaNacimiento()));
				}
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

	/**
	 * Identificacion de Paciente
	 * 
	 * @return
	 */
	/* Datos de paciente de turno* */
	public List<PacienteTurno_VO> buscarPacientePorNroDocApellido(
			String apellido, String nombre, String nroDoc) {
		List<PacienteTurno_VO> resul = null;
		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_buscarpacientes(?,?,?,?)}");

				pstmt.setString(1, apellido);
				pstmt.setString(2, nombre);
				pstmt.setString(3, nroDoc);
				pstmt.setString(4, "");
				pstmt.registerOutParameter(4, -1);

				DAO_Utils.info(log, "DAO_Paciente", "buscarpaciente",getUsuarioAccion(),
						"Llamando: 4.2.3 sp_horus_get_buscarpaciente("
								+ apellido +", "+ nombre + " o " + nroDoc + ")");

				pstmt.execute();
				String sp_horus_get_paciente = pstmt.getString(4);

				DAO_Utils.info(log, "DAO_Paciente", "buscarpaciente",getUsuarioAccion(),
						"Resultado obtenido: " + sp_horus_get_paciente);

				Type listType = new TypeToken<List<PacienteTurno_VO>>() {
				}.getType();

				resul = new GsonBuilder()
						.registerTypeAdapter(PacienteTurno_VO.class,
								new PacienteTurnoSimpleAdapter()).create()
						.fromJson(sp_horus_get_paciente, listType);

				/*
				 * if(resul.getFechaNacimiento() != null){ resul.setEdad(new
				 * SimpleDateFormat
				 * ("dd/MM/yyyy").parse(resul.getFechaNacimiento())); }
				 */
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
		return new GsonBuilder().registerTypeAdapter(Paciente_VO.class,
				new PacienteHESimpleAdapter()).create();
	}

	public Paciente_VO recuperarEntidad(String tipoDniPaciente,
			String nroDniPaciente) {

		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
				+ ".usuario.tipoDocumento) = :tipo " + " AND LOWER("
				+ this.getIdClass() + ".usuario.nroDocumento) = :nro ");

		this.getCondiciones().put("tipo", tipoDniPaciente.toLowerCase());
		this.getCondiciones().put("nro", nroDniPaciente.toLowerCase());

		List<Paciente_VO> elementos = this.listarTodo();
		this.resetQuery();

		return (elementos.size() > 0) ? elementos.get(0) : null;

	}

	// public Long getIdSucursal() {
	// return idSucursal;
	// }
	//
	// public void setIdSucursal(Long idSucursal) {
	// this.idSucursal = idSucursal;
	// }
	//
	// public Boolean getEnCirculo() {
	// return enCirculo;
	// }
	//
	// public void setEnCirculo(Boolean enCirculo) {
	// this.enCirculo = enCirculo;
	// }

}
