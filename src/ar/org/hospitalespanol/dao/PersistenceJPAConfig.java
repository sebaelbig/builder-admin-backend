package ar.org.hospitalespanol.dao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * @EnableScheduling 	- Agrega la capacidad de marcar a un metodo como chronologico
 * @EnableAsync 		- Agrega la capacidad de marcar a un metodo como asincronico
 * @PropertySource({ "/META-INF/persistence-postgres.properties" })
 */
@Configuration
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@ComponentScan({ "ar.org.hospitalespanol" })
public class PersistenceJPAConfig {

//	@Autowired
//	private Environment env;

	/**
	 * Spring Application Context
	 */
	@Autowired
	private ApplicationContext applicationContext;

	public PersistenceJPAConfig() {
		super();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "ar.org.hospitalespanol.model" });

		final EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		// em.setLoadTimeWeaver(loadTimeWaWeaver);

		return em;
	}

	@Bean
	public DataSource dataSource() {
		
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(DBPropertiesReader.getProperty(DBPropertiesReader.JDBC_DRIVER));
		dataSource.setUrl(DBPropertiesReader.getProperty(DBPropertiesReader.JDBC_URL));
		dataSource.setUsername(DBPropertiesReader.getProperty(DBPropertiesReader.JDBC_USER));
		dataSource.setPassword(DBPropertiesReader.getProperty(DBPropertiesReader.JDBC_PASS));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			final EntityManagerFactory emf) {
		
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties additionalProperties() {
		
		final Properties eclipseLinkProperties = new Properties();
//		 eclipseLinkProperties.setProperty("eclipselink.ddl-generation","create-or-extend-tables");
		eclipseLinkProperties.setProperty("eclipselink.ddl-generation", "none");
		eclipseLinkProperties.setProperty("eclipselink.deploy-on-startup",
				"true");
		eclipseLinkProperties.setProperty(
				"eclipselink.ddl-generation.output-mode", "both");
		eclipseLinkProperties.setProperty(
				"eclipselink.create-ddl-jdbc-file-name",
				"createDDL_ddlGeneration.jdbc");
		
		eclipseLinkProperties.setProperty("eclipselink.logging.level", "INFO");
		eclipseLinkProperties.setProperty("eclipselink.logging.level.sql", "INFO");
		eclipseLinkProperties.setProperty("eclipselink.logging.timestamp", "true");
		
		eclipseLinkProperties.setProperty("eclipselink.weaving", "false");

		// IMPORTANTE, para que no cachee la BD
		eclipseLinkProperties.setProperty("eclipselink.cache.shared.default","false");

//		eclipseLinkProperties.setProperty("eclipselink.session.customizer", "ar.org.hospitalespanol.dao.StorageSchemaSessionCustomizer");
//		eclipseLinkProperties.setProperty("eclipselink.ddl-generation.index-foreign-keys","true");
//		eclipseLinkProperties.setProperty("eclipselink.cache.query-results","true");

		return eclipseLinkProperties;
	}


}