package ar.org.hospitalespanol.dao;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.catalina.Globals;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.dao.core.DAO_Parametro;

/**
 * Simple class meant to read a properties file
 * 
 * @author Jaikiran Pai
 * 
 */
public class DBPropertiesReader {

	public final static String FILE_NAME = "sybase.properties";

	// Posibles propiedades del archivo "sybase.properties" ubicado en la
	// carpeta 'conf' del Apache

	// Turnos, Web, Quirofanos, Turnos, reservados
	//Parametros de conexion HE 
	public static final String URL_BDHE = "bdhe.driver.url";
	public static final String USR_BDHE = "bdhe.driver.user";
	public static final String PASS_BDHE = "bdhe.driver.password";
	
	public static final String JDBC_DRIVER = "horus.postgres.jdbc.driver.class_name";
	public static final String JDBC_URL = "horus.postgres.jdbc.url";
	public static final String JDBC_URL_LOCAL = "horus.postgres.jdbc.url_local";
	public static final String JDBC_USER = "horus.postgres.jdbc.user";
	public static final String JDBC_PASS = "horus.postgres.jdbc.password";

	public static final String SCHEMA_POSTGRES = "horus.postgres.schema_name";
	public static final String IP_HORUS = "ip.horus";

	public Properties properties = null;
	private final DAO_Parametro dao_Parametro = null;

	//instance es un singleton
	private static DBPropertiesReader instance = new DBPropertiesReader();
	
	private DBPropertiesReader() {
		//Creo un properties
		this.properties = new Properties();
		
		//fileSep suele ser "\" o "/" dependiendo del SO.
		//Armo el path del archivo de Conf a partir de la url del tomcat 
		String filesep = System.getProperty("file.separator");
		String serverHome = "file:"
				+ System.getProperty(Globals.CATALINA_HOME_PROP);
		String path = serverHome + filesep + "conf" + filesep + DBPropertiesReader.FILE_NAME;

		try {
			//abro el archivo de Properties armado anteriormente
			properties.load(new URL(path).openStream());

			DAO_Utils
					.info(LoggerFactory.getLogger(DBPropertiesReader.class),
							"DBPropertiesReader", "DBPropertiesReader", "sistema",
							"Se leyo las propiedades de la BD Sybase desde el archivo de configuracinón");
		
		} catch (IOException e) {
			//Si falla al abrir el archivo, cargo estos properties por default (no debería pasar)
			properties.setProperty(DBPropertiesReader.URL_BDHE,
					"jdbc:sybase:Tds:172.20.32.14:5000/desa");
			properties.setProperty(DBPropertiesReader.USR_BDHE, "horus_ldap");
			properties.setProperty(DBPropertiesReader.PASS_BDHE, "horus_1d4P");

			properties.setProperty(DBPropertiesReader.SCHEMA_POSTGRES,
					"horus_restfull_desa");
			properties.setProperty(DBPropertiesReader.IP_HORUS, "172.20.32.249");
		}
		
	}
	
	public static DBPropertiesReader getInstance() {
		//instance es un singleton
		if (instance==null)
			instance = new DBPropertiesReader();
		return instance;
	}
	

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * Devuelve el valor de la property con el key pasado como parametro(String)
	 * @param k
	 * @return
	 */
	public static String getProperty(String k) {
		return instance.properties.getProperty(k);
	}
	
	public static DAO_Parametro getDaoParam(){
		return instance.dao_Parametro;
	}
	
	/**
	 * Devuelve el esquema actual
	 * @return
	 */
	public static String getEsquema(){
		return instance.properties.getProperty(SCHEMA_POSTGRES);
	}
	
	

}