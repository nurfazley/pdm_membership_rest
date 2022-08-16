package com.pdm.membership.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.EmailDAO;
import com.pdm.membership.model.Email;
import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.Member;
import com.pdm.membership.model.lookup.EmailStatus;


@Service("emailService")
@Transactional
public class EmailService {

	@Autowired
	private EmailDAO emailDAO;
	
	
	public Email saveEmail(Email email) {
		return emailDAO.save(email);
	}
	
	public Email updateEmail(Email email) {
		return emailDAO.save(email);
	}
	
	public Email generateAndSaveForgetPasswordEmail(Member member, ForgetPasswordToken token) {
		String content = String.format("Dear %s, <br/><br/>You have requested to reset your password for PDM membership. Please insert the token below in the mobile application: <br/><br/>", member.getFullName()) ;
		content += "<h1>Token: " + token.getTokenString() + "</h1><br/><br/><br/>";
		content += "PDM Membership System";
		
		Email email = new Email();
		email.setCreatedDateTime(new Date());
		email.setReceivers(member.getEmail());
		email.setSender("muipsystem@gmail.com");
		email.setStatus(EmailStatus.queue);
		email.setSubject("Forget Password - " + member.getFullName() + " (IC: " + member.getIc() + ")");
		email.setMessage(content);
		
		return emailDAO.save(email);
	}
}
