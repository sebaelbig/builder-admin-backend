package ar.com.builderadmin.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds the functionality for EntityManager creation and access.
 *  
 * @author segarcia
 *
 */
public class EntityManagerHolder {

	/**
	 * Default Schema Constant
	 * 
	 * TODO CAMBIAR CUANDO SE GENERA EL WAR PARA PRODUCCION
 */
//	public final static String DEFAULT_SCHEMA = "horus_restfull"; //Produccion 246
//	public final static String DEFAULT_SCHEMA = "horus_restfull_desa";//QA 249
//	public final static String DEFAULT_SCHEMA = "horus_restfull_cl"; //Desa mia
	
//	public final static String DEFAULT_SCHEMA = "horus_restfull_sg"; //Desa mia
	
	/**
	 * Entity Manager Factory for getting new entity managers.
	 */
	private final EntityManagerFactory entityManagerFactory;
	
	/** 	
	 * Logger for this class.
	 */
	private final Logger logger = LoggerFactory.getLogger(EntityManagerHolder.class);
	
	/**
	 * EntityManagerHolder Constructor.
	 * 
	 * @param persistenceFile the path to the persistence file
	 * @param persistenceUnit the name of the persistence unit
	 */
	public EntityManagerHolder(String persistenceFile, String persistenceUnit) {
		this.logger.debug("creating entity factory manager with persistence file: {} and persistence unit: {}",persistenceFile, persistenceUnit);
		
		//this tells the persistence unit file to the factory
		Map<String,String> properties = new HashMap<>();
		if (persistenceFile != null) {
			properties.put("eclipselink.persistencexml", persistenceFile);
		}
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit, properties);
//		this.entityManagerFactory = new PersistenceProvider().createEntityManagerFactory(persistenceUnit, properties);
		//creates entity manager just to generate the DDL on the DDBB
		this.entityManagerFactory.createEntityManager();
	}

	/**
	 * Returns an EntityManager.
	 * 
	 * @return an EntityManager or null
	 */
	public EntityManager getEntityManager() {
		this.logger.debug("obtaining entity manager");
		if (this.entityManagerFactory != null) {
			EntityManager em = this.entityManagerFactory.createEntityManager();
			return em;
		}
		this.logger.error("EntityManagerFactory wass null, EntityManager couldn't be created");
		return null;
	}
	
}
