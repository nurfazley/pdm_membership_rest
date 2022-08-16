package com.pdm.membership.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pdm.membership.model.base.AbstractEmail;
import com.pdm.membership.model.lookup.EmailStatus;


@Entity
@Table(name = "Email")
public class Email extends AbstractEmail {

	private static final long serialVersionUID = -4290444866317326556L;
	
	private String sender;
	
	private String receivers;
	
	private String subject;
	
	private String message;
	
	private EmailStatus status = EmailStatus.queue;
	
	private Date sendDate;

	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceivers() {
		return receivers;
	}

	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailStatus getStatus() {
		return status;
	}

	public void setStatus(EmailStatus status) {
		this.status = status;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
