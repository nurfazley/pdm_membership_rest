package com.pdm.membership.helper;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public final class EmailSender {
	
	private static final String GMAIL_HOST = "smtp.gmail.com";
	
	private static final String PORT = "465";
	
	private static Properties properties;
	
	
	public static void sendEmail(String sender, String[] receivers, String subject, String content) {
		properties = getProperties();
		Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, "hidygsusnsmfmbyn");
            }
        });
		
		InternetAddress[] addressList = new InternetAddress[receivers.length];
		
		for (int i = 0; i < receivers.length; i++) {
			try {
				addressList[i] = new InternetAddress(receivers[i]);
			} 
			catch (AddressException e) {
				e.printStackTrace();
			}
		}
		
		// Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));
            // Set To: header field of the header.
            message.addRecipients(Message.RecipientType.TO, addressList);
            // Set Subject: header field
            message.setSubject(subject);
            // Now set the actual message
            message.setContent(content, "text/html; charset=utf-8");
            
            // Send message
            Transport.send(message);
        } 
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
	
	private static Properties getProperties() {
		if (properties == null) {
			properties = System.getProperties();

	        // Setup mail server
	        properties.put("mail.smtp.host", GMAIL_HOST);
	        properties.put("mail.smtp.port", PORT);
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
		}
		
		return properties;
	}
	
    public static void main(String[] args) {
    	EmailSender.sendEmail("muipsystem@gmail.com", new String[] {"fazley.yatim@muip-holdings.com"}, "This is the Subject Line!", "This is actual message");
    }
}