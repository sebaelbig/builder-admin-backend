package ar.org.hospitalespanol.dao;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.catalina.Globals;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.eclipse.persistence.sessions.UnitOfWork;

public class StorageSchemaSessionCustomizer implements SessionCustomizer 
{
	
	@Override
	public void customize(Session session) throws Exception {
		
		session.getEventManager().addListener(new SessionEventAdapter() {
			
			public Properties properties = new Properties();
			
			@Override
			public void postConnect(SessionEvent event) {
				System.out.println("this is triggered after a connect");
			}

			@Override
			public void preLogin(SessionEvent event) {
				System.out.println("this is triggered before a login");
				String filesep = System.getProperty("file.separator");
				String serverHome = "file:"
						+ System.getProperty(Globals.CATALINA_HOME_PROP);
				String path = serverHome + filesep + "conf" + filesep + DBPropertiesReader.FILE_NAME;

				try {
					properties.load(new URL(path).openStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void postLogin(SessionEvent event) {
				System.out.println("this is triggered after a login");

				event.getSession().getLogin();
				
				//Obtengo el nombre del esquema a traves del archivo de propiedades
				String schemaName = this.properties.getProperty(DBPropertiesReader.SCHEMA_POSTGRES);
				
				//Seteo el esquema tomado del archivo de propiedades
				if (schemaName != null && !schemaName.isEmpty()) {
					
					UnitOfWork unit = event.getSession().acquireUnitOfWork();

//					String sql = "ALTER USER "+this.properties.getProperty(DBPropertiesReader.USER_POSTGRES)+" SET search_path to '"+schemaName+"'";
					String sql = "SET search_path = "+schemaName;
					System.out.println("[SessionCustomizer] [postLogin] Setting search_path: "+sql);
					
					unit.commitAndResumeOnFailure();
					System.out.println(unit.executeSQL("SHOW search_path"));
					unit.executeNonSelectingSQL(sql);
					System.out.println(unit.executeSQL("SHOW search_path"));
					unit.commit();
					
				}
			}

		});

	}

}
