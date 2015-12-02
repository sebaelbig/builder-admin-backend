package ar.com.builderadmin.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import ar.com.builderadmin.controllers.WebContextHolder;
import ar.com.builderadmin.model.I_Bloqueable;
import ar.com.builderadmin.model.I_Entidad;
import ar.com.builderadmin.model.core.DatosAccion;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.ws.respuestas.R_Listador;
import sun.rmi.runtime.Log;

public abstract class DAO<T> {

	protected static final String BD_USER_HORUS_LDAP = "horus_ldap";
	protected static final String BD_PASS_HORUS_LDAP = "horus_1d4P";

	protected static final String BD_USER_HORUS_CONSULTAS = "horus_consultas";
	protected static final String BD_PASS_HORUS_CONSULTAS = "horus_consult4S";

	protected static final String BD_USER_HORUS_PEDIDOS = "horus_pedidos";
	protected static final String BD_PASS_HORUS_PEDIDOS = "horus_p3d1doS";

	// public static String usrBDHE = "openbiz";
	// public static String passBDHE = "0p3nb1z";

	// String home = System.getProperty("catalina.home");
	public DBPropertiesReader dbProperties = DBPropertiesReader.getInstance();
	
	// "jdbc:sybase:Tds:172.20.32.14:5000/desa";//Base de test (Asignacion de
	// roles)
	// public static String urlBDHE =
	// "jdbc:sybase:Tds:172.20.32.251:5000/espanol"; //Turnos Web, Quirofanos,
	// Turnos reservados

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Logger.
	 */
	protected final Logger log = LoggerFactory.getLogger(DAO.class);

	private String queryEncabezado;
	private String queryFinal;
	protected String queryCondiciones;

	protected R_Listador<T> resp_listar;

	private Query query;

	private String queryHQL;
	private String queryHQLContador;

	/**
	 * Spring Application Context
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.universe.core.fx.I_FxFactory#getNewFxInstance(java.lang.Class)
	 */
	public <F extends DAO> F getInstance(Class<F> clase) {
		this.log.debug("instanciando un nuevo DAO: " + clase.getSimpleName());

		return this.applicationContext.getBean(clase);
	}

	/**
	 * Cuenta cuantos elementos <T> existen dependiendo de la query actualmente
	 * en uso
	 * 
	 * @return
	 */
	public Integer contarTotal() {
		if (getResp_listar() != null) {
			return getResp_listar().getCantidadTotal();
		}
		return Integer.valueOf(0);
	}

	/**
	 * Devuelve el nombre de la clase <T>
	 * 
	 * @return Nombre
	 */
	protected abstract Class getClazz();

	/**
	 * Devuelve el id de la clase <T> para el armado de las querys
	 * 
	 * @return
	 */
	protected abstract String getIdClass();

	protected String getQueryContador() {
		if (getQueryCondiciones().equals("")) {
			return "SELECT COUNT(*) FROM " + getClazz().getCanonicalName()
					+ " " + getIdClass() + " ";
		}
		return "SELECT COUNT(*) FROM " + getClazz().getCanonicalName() + " "
				+ getIdClass() + " " + getQueryCondiciones();
	}

	Map<String, Object> condiciones = new HashMap<String, Object>();

	private void crearQuery() {

		Query q = null;

		try {
			
			StringBuffer str_q = new StringBuffer()
					.append(getQueryEncabezado()).append(getQueryCondiciones())
					.append(getQueryFinal());

			DAO_Utils.info(log, getClazz().getName(), "crearQuery", getUsuarioAccion(),
					"Query creada: " + str_q);

			q = getEntityManager().createQuery(str_q.toString());
			setQuery(q);

		} catch (Exception e) {
			// No se pudo armar la query, intento con la HQL
			DAO_Utils.error(log, this.getClazz().getSimpleName(),
					"[crearQuery]", "--", "ERROR: Falló al crear la query...");
			e.printStackTrace();

			q = getEntityManager().createQuery(getQueryHQL());
			setQuery(q);
		}

		// Si hubo condiciones, las itera
		for (String llave : getCondiciones().keySet()) {
			q.setParameter(llave, getCondiciones().get(llave));
		}

	}

	public void resetQuery() {
		setQueryCondiciones("");
		getCondiciones().clear();
	}

	@Transactional
	public Object guardar(I_ValueObject valueObject) {
		Object elemento = this.getObjeto(valueObject);

		try {
			elemento = getEntityManager().merge(elemento);
			getEntityManager().flush();

			try {

				valueObject.setId(((I_Entidad) elemento).getId());
				valueObject.setVersion(((I_Entidad) elemento).getVersion());

			} catch (Exception e) {
				DAO_Utils.error(log, this.getClazz().getSimpleName(),
						"guardar", "-interno-",
						"No se pudo setear la VERSION e ID al VO");
			}

		} catch (Exception e) {
			e.printStackTrace();
			DAO_Utils.error(log, this.getClazz().getSimpleName(), "guardar",
					"-interno-", "No se pudo grabar la entidad");
		}

		return elemento;
	}

	@Transactional
	public void eliminar(I_ValueObject valueObject) {
		valueObject.setBorrado(true);
		guardar(valueObject);
	}

	protected Object getObjeto(I_ValueObject valueObject) {
		return valueObject.toObject();
	}

	public Query getQuery() {
		this.crearQuery();
		return this.query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getQueryHQL() {
		return this.queryHQL;
	}

	public Map<String, Object> getCondiciones() {
		return this.condiciones;
	}

	public void setCondiciones(Map<String, Object> condiciones) {
		this.condiciones = condiciones;
	}

	public void setQueryHQL(String queryHQL) {
		this.queryHQL = queryHQL;
	}

	public String getQueryHQLContador() {
		return this.queryHQLContador;
	}

	public void setQueryHQLContador(String queryHQLContador) {
		this.queryHQLContador = queryHQLContador;
	}

	public String getQueryEncabezado() {
		return this.queryEncabezado;
	}

	public void setQueryEncabezado(String queryEncabezado) {
		this.queryEncabezado = queryEncabezado;
	}

	public String getQueryFinal() {
		return this.queryFinal;
	}

	public void setQueryFinal(String queryFinal) {
		this.queryFinal = queryFinal;
	}

	public String getQueryCondiciones() {
		return this.queryCondiciones;
	}

	// public void setQueryCondiciones(String queryCondiciones) {
	// this.queryCondiciones = queryCondiciones;
	// }
	// @Override
	public void setQueryCondiciones(String queryNuevo) {

		this.queryCondiciones = "";

		if (queryNuevo.equals("")) {
			this.queryCondiciones = " WHERE " + this.getIdClass()
					+ ".borrado = false ";
		} else {

			this.queryCondiciones = queryNuevo + " AND " + this.getIdClass()
					+ ".borrado = false ";
		}
	}

	public Connection getConexionHE() {
		try {

			Class.forName("com.sybase.jdbc4.jdbc.SybDataSource");
			
			//Levanto los parámetros necesarios para la configuración desde el Properties.
			String url = dbProperties.getProperty(DBPropertiesReader.URL_BDHE);
			String usr = dbProperties.getProperty(DBPropertiesReader.USR_BDHE);
			String pass = dbProperties.getProperty(DBPropertiesReader.PASS_BDHE);

			DAO_Utils.info(log, "DAO", "getConexionHE", getUsuarioAccion(), "URL: " + url
					+ ", Usr: " + usr + ", Pass: " + pass);

			Connection con = DriverManager.getConnection(url, usr, pass);

			return con;

		} catch (ClassNotFoundException e) {
			this.log.error("No se pudo conectar");
			return null;
		} catch (SQLException e) {
			this.log.error("No se pudo cerrar");
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConexionHE(String usr, String pass) {
		try {

			Class.forName("com.sybase.jdbc4.jdbc.SybDataSource");

			String url = dbProperties.getProperty(DBPropertiesReader.URL_BDHE);

			DAO_Utils.info(log, "DAO", "getConexionHE",getUsuarioAccion(), "URL: " + url
					+ ", Usr: " + usr + ", Pass: " + pass);

			Connection con = DriverManager.getConnection(url, usr, pass);

			return con;

		} catch (ClassNotFoundException e) {
			this.log.error("No se pudo conectar");
			return null;
		} catch (SQLException e) {
			this.log.error("No se pudo cerrar");
			e.printStackTrace();
		}

		DAO_Utils.info(log, "DAO", "getConexionHE",getUsuarioAccion(), "ERROR: Usr: " + usr
				+ ", Pass: " + pass);
		return null;
	}

	public static Connection getConexionLocal(Log log) {
		try {
			// log.info("Intentando conectarse al postgre SRV02.");

			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/openbiz", "postgres",
					"postgres");
			// log.info("Conexion exitosa!!", new Object[0]);

			return con;
		} catch (ClassNotFoundException e) {
			// log.info("No se pudo conectar", new Object[0]);
			return null;
		} catch (SQLException e) {
			// log.info("No se pudo cerrar", new Object[0]);
			e.printStackTrace();
		}
		return null;
	}

	protected void cerrarConexion(Connection con) {
		try {
			con.rollback();
			con.close();

			this.log.info("Se cerro las conexiones", new Object[0]);
		} catch (SQLException sqe) {
			this.log.error("No se pudo cerrar", new Object[0]);
			sqe.printStackTrace();
		}
	}

	public boolean probarConeccion() {
		Connection con = null;
		try {

			Class.forName("com.sybase.jdbc4.jdbc.SybDataSource");

			String urlBDHE = dbProperties
					.getProperty(DBPropertiesReader.URL_BDHE);

			con = DriverManager.getConnection(urlBDHE, BD_USER_HORUS_LDAP,
					BD_PASS_HORUS_LDAP);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con != null;
	}

	/**
	 * Lista los elementos de tipo <T> limitando el resultado
	 * 
	 * @param inicioResul
	 *            Inicio del resultado
	 * @param finResul
	 *            Fin del resultado
	 * @return Lista de elementos
	 */
	@SuppressWarnings("unchecked")
	public List<T> listar(int inicioResul, int finResul) {
		
		List<T> resul = null;
		long inicio = new Date().getTime();
				
		if (finResul > 0) {
			
			resul = this.getQuery().setFirstResult(inicioResul)
					.setMaxResults(finResul).getResultList();
		
		} else {
			
			resul = this.getQuery().getResultList();
		}
		
		long fin = new Date().getTime();
		if (fin-inicio>10000){
			DAO_Utils.info(log, "DAO", "listar",getUsuarioAccion(), "---------------->>> OJO!!!  ESTA CONSULTA TARDA DEMASIADO!!!! "+DAO_Utils.formatExactlyHour(new Date(fin-inicio)));
		}
		
		return resul;
	}

	/**
	 * Lista Todos los elementos encontrados de tipo <T>
	 * 
	 * @return
	 */
	public List<T> listarTodo() {
		return listar(0, -1);
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public R_Listador<T> getResp_listar() {
		return this.resp_listar;
	}

	public void setResp_listar(R_Listador<T> resp_listar) {
		this.resp_listar = resp_listar;
	}

	@SuppressWarnings("unchecked")
	public Object findById(Long id) {
		return this.getEntityManager().find(this.getClazz(), id);
	}

	/**
	 * Al recuperar el objeto lo marca como bloqueado
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public I_ValueObject bloquearPorId(Long id, DatosAccion data) {
		I_ValueObject vo = null;

		try {
			Object o = this.getEntityManager().find(this.getClazz(), id);

			((I_Bloqueable) o).setBloqueado(true);
			((I_Bloqueable) o).setDatosAccion(data);
			o = this.getEntityManager().merge(o);
			getEntityManager().flush();

			vo = DAO_Utils.entityToValueObject(o);
		} catch (OptimisticLockException e) {
			//Se estan intentando bloquear dos iguales
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	/**
	 * Desbloquea el objeto
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public I_ValueObject desbloquearPorId(Long id, DatosAccion data) {
		I_ValueObject vo = null;

		try {
			Object o = this.getEntityManager().find(this.getClazz(), id);

			((I_Bloqueable) o).setBloqueado(false);
			((I_Bloqueable) o).setDatosAccion(data);
			o = this.getEntityManager().merge(o);
			getEntityManager().flush();

			vo = DAO_Utils.entityToValueObject(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	@Autowired
	private WebContextHolder webContextHolder;
	
	protected String getUsuarioAccion(){
		String resul = "";
		try{
			resul = webContextHolder.getUsuario().getUsuario();
		}catch(Exception e){
			resul = "-interno-";
		}
		return resul; 
	}
	
	
	/************************************************************/
	/***  Envio Mail **/
	/**
	 * cuenta origen, asunto, cuerpo, cuenta destino, CC, CCO, adjunto, flag true o false(texto o html, no se usa), fecha en que se desea enviar el mail, Cant máxima de destinatarios(si es null asume 1) y devuelve un mensaje de resultado de la operación si es -1 hubo error.
	 * @param nombre
	 * @param mail
	 * @return
	 */
	public Boolean enviarMail(String asunto, String destinatario, String cuerpo )
    {
        R_EnvioMail resul = new R_EnvioMail();
        
        Connection con = getConexionHE();
        String sp_horus_get_postulante = null;
        System.out.println("Conectando con la bd espanol...");
       
        try
        {
            if(con != null)
            {
                CallableStatement pstmt = con.prepareCall("{call sp_registrar_mails_repo_java(?,?,?,?,?,?,?,?,?,?,?)}");
                System.out.println("Llamando: 4.1.1 sp_registrar_mails_repo_java ()");
                Object s[] = new Object[10];
                //Mail origen
                s[0] = "turnosweb@hospitalespanol.org.ar"; 
                //Asunto
                s[1] = asunto; 
                //Cuerpo
                s[2] = cuerpo;
                //Mail destino
                s[3] = destinatario;  
                //CC
                s[4] = null;
                //CCO
                s[5] = null;
                //Adjunto
                s[6] = null;
                //Flag HTML (no se usa)
                s[7] = null;
                //Fecha envio
                s[8] = null;
                //Cantidad max de destinatarios
                s[9] = 1;
                
                pstmt.setString(1, (String)s[0]);
                pstmt.setString(2, (String)s[1]);
                pstmt.setString(3, (String)s[2]);
                pstmt.setString(4, (String)s[3]);
                pstmt.setString(5, (String)s[4]);
                pstmt.setString(6, (String)s[5]);
                pstmt.setString(7, (String)s[6]);
                pstmt.setString(8, (String)s[7]);
                pstmt.setString(9, (String)s[8]);
                pstmt.setInt(10, ((Integer)s[9]).intValue());
                pstmt.setString(11, "");
                pstmt.registerOutParameter(11, -1);
                
                log.info("Llamando: 4.2.1 sp_registrar_mails_repo_java (#0,#1,#2,#3,#4,#5,#6, ...)", new Object[] {
                    s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9]
                });
                
                pstmt.execute();
                
                sp_horus_get_postulante = pstmt.getString(11);
                
                System.out.println("Resultado obtenido:  "+sp_horus_get_postulante);
                
                resul.setOk(true);
                
            } else {
                System.out.println("No se pudo obtener una conexión");
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
        
		return resul.getOk();
    }
	
	public class R_EnvioMail {
		private Boolean ok = false;
		private String mensaje;
		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}
		/**
		 * @param ok the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}
		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}
		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
	}

	/************************************************************/

}