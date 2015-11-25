package ar.org.hospitalespanol.utils;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 * IMPORTANTE!!!
 * 
 * Para la JDK7 en adelante, se le debe pasar como parametro a la VM de java lo siguiente:
 * 
 * -Djava.net.preferIPv4Stack=true
 * 
 * --En eclipse
 * 1) Hacer doble click sobre el servidor (Por ej. Apache tomcat)
 * 2) Ir a "Open lunch configuration"	pestaña "Arguments" 
 * 3) En la sección VM arguments agregar: -Djava.net.preferIPv4Stack=true
 * 
 * @author danilamo
 *
 */
@Configuration 
public class MailConfig {

	//Configuracion
    private String from = "noresponder@hospitalespanol.org.ar";

    @Bean
    public JavaMailSender javaMailService() {
        
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        javaMailSender.setHost("smtp.hospitalespanol.org.ar");
        javaMailSender.setPort(25);
        javaMailSender.setUsername("noresponder@hospitalespanol.org.ar");
        javaMailSender.setPassword("automatica");
        
	        Properties propsJavaMail = new Properties();
	//      Use SMTP-AUTH to authenticate to SMTP server -->
	        propsJavaMail.put("mail.smtp.auth", true);
	        propsJavaMail.put("mail.smtp.starttls.enable", true);
	        propsJavaMail.put("mail.smtp.ssl.trust", "smtp.hospitalespanol.org.ar");
	        propsJavaMail.put("mail.debug", true);
        
        javaMailSender.setJavaMailProperties(propsJavaMail);

        return javaMailSender;
    }
    
    
    //Template para los pedidos
    
    @Bean
    public VelocityEngineFactoryBean velocityEngine() {
    	
    	VelocityEngineFactoryBean velocitiEngineFactory = new VelocityEngineFactoryBean();
    	
    	Properties propsVelocity = new Properties();
    	
    	propsVelocity.put("resource.loader", "class");
    	propsVelocity.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    	
    	velocitiEngineFactory.setVelocityProperties(propsVelocity);
    	
    	return velocitiEngineFactory;
    }
}
