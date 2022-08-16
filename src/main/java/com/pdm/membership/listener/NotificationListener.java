package com.pdm.membership.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.pdm.membership.dao.EmailDAO;
import com.pdm.membership.helper.EmailSender;
import com.pdm.membership.model.Email;
import com.pdm.membership.model.lookup.EmailStatus;
import com.pdm.membership.state.NotificationState;


@WebListener
public class NotificationListener implements ServletContextListener {

	@Autowired
	private EmailDAO emailDAO;
	
	@Autowired
	private NotificationState notificationState;
	
	private Timer timer;
	
	// Start time interval (in miliseconds) - 3 seconds
	private static final int START_TIME = 3 * 1000;
		
	// Original: Timer interval (in miliseconds) - 10 seconds
	private static final int INTERVAL = 10 * 1000;
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		ServletContext sc = sce.getServletContext();
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, sc);
		
		if (timer == null) {
			TimerTask timerTask = new ProcessorTask();
			timer = new Timer();
			timer.schedule(timerTask, START_TIME, INTERVAL);
			
			System.out.println("ProcessorTask is initialized on: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(new Date()));
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		
		if (timer != null ) {
			timer.cancel();
			timer = null;
		}
	}
	
	
	class ProcessorTask extends TimerTask {

		@Override
		public void run() {
			if (notificationState.isNotificationPending()) {
				Date now = new Date();
				List<Email> emailList = new ArrayList<Email>();
				
				try {
					emailList = emailDAO.findAllByStatus(EmailStatus.queue);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				if (emailList != null && !emailList.isEmpty()) {
					for (Email email : emailList) {
						if (email.getReceivers() != null) {
							String [] recipientEmails = processRecipients(email.getReceivers());
							
							try {
								EmailSender.sendEmail("muipsystem@gmail.com", recipientEmails, email.getSubject(), email.getMessage());
								
								email.setSendDate(now);
								email.setStatus(EmailStatus.sent);
								emailDAO.save(email);
							}
							catch (Exception e) {
								e.printStackTrace();
								email.setStatus(EmailStatus.queue);
								emailDAO.save(email);
							}
						}
					}
	
					emailList = null;
				}
				
				notificationState.setNotificationPending(false);
			}
		}
		
		private String[] processRecipients(String recipients) {
			String[] recipientEmails = null;
			
			// Email addresses is separated by ';' as delimiter
			if (recipients.contains(";")) {
				recipientEmails = recipients.split(";");
			}
			else {
				recipientEmails = new String[1];
				recipientEmails[0] = recipients;
			}
			
			return recipientEmails;
		}
	}
}
