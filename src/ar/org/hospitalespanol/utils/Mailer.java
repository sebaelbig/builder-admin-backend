package ar.org.hospitalespanol.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.w3c.tidy.Tidy;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.Mail_VO;
import ar.org.hospitalespanol.ws.RespuestaCorta;

import com.google.gson.Gson;

@Component
public class Mailer  {
	
	private final Logger log = LoggerFactory.getLogger(Mailer.class);
	
	@Autowired
	private  JavaMailSender javaMailService;
	
	@Autowired
	private  VelocityEngine  velocityEngine;
    
	
	/**
	 * Trasnforma el HTML a XHTML 
	 * 
	 * @param html
	 * @return
	 */
    public String htmlStringToXhtml(String html ) {
    	
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	Tidy tidy = new Tidy();
    	
    	// queremos que la salida sea xhtml
    	tidy.setXHTML(true); 
    	tidy.parse(new ByteArrayInputStream(html.getBytes()), out);
    	
    	return out.toString();
	}

    
	public  String enviarMail(String pathMail, Mail_VO mail, Map<String, File> attachs) {
		
		RespuestaCorta resp = null;

		//Parse el mail con los datos del mail
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "ar/org/hospitalespanol/resources/mails/"+pathMail+".vm", "UTF-8", mail.getData());
        
        //Formateo a un XHTML correcto
//        String htmlSanado = htmlStringToXhtml(text);
        
        try{
		
        	//Armo el mail
        	MimeMessage message = this.javaMailService.createMimeMessage();
        
        	MimeMessageHelper helper = new MimeMessageHelper(message, true);
        	helper.setTo(mail.getEmailCandidato());
        	helper.setSubject(mail.getAsunto());
        	helper.setSentDate(new Date());
        	helper.setText(text, true);
        	
        	//Si viene con attachs, los agrego
        	if (attachs!=null)
        		for (String nombreArchivo : attachs.keySet()) {
					helper.addAttachment(nombreArchivo, attachs.get(nombreArchivo));
				}
        	
            DAO_Utils.info(log, "Mailer", "enviarMail", "mailer", "Se envió correctamente el mail: ");
//            DAO_Utils.info(log, "Mailer", "enviarMail", text);
            
            this.javaMailService.send(message);

        }
        catch (MailException | MessagingException ex) {
            System.err.println(ex.getMessage());
        }
		
		return new Gson().toJson(resp);
	}
	
	private static void addAttachment(Multipart multipart, String filename)
	{
	    // JavaMail 1.4
	    MimeBodyPart attachPart = new MimeBodyPart();
	    
	    String attachFile = "D:/Documents/MyFile.mp4";
	    try {
			attachPart.attachFile(attachFile);
			multipart.addBodyPart(attachPart);
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static String getSqlFileContents(String fileName) {
	    StringBuffer sb = new StringBuffer();
	    try {
	        Resource resource = new ClassPathResource(fileName);
	        DataInputStream in = new DataInputStream(resource.getInputStream());
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String strLine;
	        while ((strLine = br.readLine()) != null) {
	            sb.append(" " + strLine);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return sb.toString();
	}
	
	/**
	 * Dado un String crea un archivo
	 * 
	 * @param contenidoFile
	 * @return
	 */
	public static File crearArchivoDeTexto(String contenidoFile) {
		try
		{
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File("texto.txt");
	
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo, true);
	
			//Escribimos en el archivo con el metodo write 
			escribir.write(contenidoFile);
	
			//Cerramos la conexion
			escribir.close();
		
			return archivo;
			
		}catch(Exception e)	{
			//Si existe un problema al escribir cae aqui
			System.out.println("Error al escribir");
			return null;
		}
		
	}
	
}