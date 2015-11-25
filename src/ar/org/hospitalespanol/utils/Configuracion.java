package ar.org.hospitalespanol.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * @EnableScheduling 	- Agrega la capacidad de marcar a un metodo como chronologico
 * @EnableAsync 		- Agrega la capacidad de marcar a un metodo como asincronico
 * 
 *      opt/apa
 */
@Configuration
@EnableScheduling 
@EnableAsync
@ComponentScan({ "ar.org.hospitalespanol" })
public class Configuracion {
	
	
	
}