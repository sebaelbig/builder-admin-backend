package ar.org.hospitalespanol.utils;

import java.io.File;

public interface MailServer {

	public void send(String to, String subject, String text);
	
	public void send(String to, String subject, String text, File... attachments);
}
