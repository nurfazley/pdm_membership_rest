package com.pdm.membership.restcontroller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.helper.ForgetPasswordTokenGenerator;
import com.pdm.membership.model.Email;
import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.Member;
import com.pdm.membership.service.EmailService;
import com.pdm.membership.service.ForgetPasswordTokenService;
import com.pdm.membership.service.MemberService;
import com.pdm.membership.state.NotificationState;


@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NotificationState notificationState;
	
	@Autowired
	private ForgetPasswordTokenService tokenService;
	
	
	@PostMapping("/emails")
	public Email createEmail(@RequestBody Email email) {
		email.setCreatedDateTime(new Date());
		return emailService.saveEmail(email);
	}
	
	@PostMapping("/emails/forgetpassword")
	public String forgetPassword(@RequestBody Map<String, String> body) {
		String emailAddress = body.get("email");
		Member member = memberService.findByEmail(emailAddress);
		ForgetPasswordToken token = null;
		
		if (member != null) {
			synchronized (this) {
				token = ForgetPasswordTokenGenerator.generateToken();
				token.setMember(member);
				token = tokenService.saveToken(token);
				printPrettyJson(token);	
			}
			
			Email email = emailService.generateAndSaveForgetPasswordEmail(member, token);
			notificationState.setNotificationPending(true);
			printPrettyJson(email);
		}
		
		return token == null ? null : token.getTokenPrefix();
	}
	
	private String printPrettyJson(Object obj) {
		String jsonStr = new String();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			System.out.println(jsonStr);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonStr;
	}
}
