package com.pdm.membership.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;
import com.pdm.membership.service.ForgetPasswordTokenService;


@RestController
public class ForgetPasswordTokenController {
	
	@Autowired
	private ForgetPasswordTokenService tokenService;
	

	@PostMapping("/tokens/changeStatus")
	public ForgetPasswordToken changeTokenStatus(@RequestBody Map<String, Object> map) {
		String tokenString = (String) map.get("tokenString");
		Boolean isActive = (Boolean) map.get("status");
		ForgetPasswordToken token = new ForgetPasswordToken();
		
		if (tokenString != null && isActive != null) {
			token = tokenService.getTokenFromTokenString(tokenString);
			
			if (isActive) {
				token.setForgetPasswordTokenStatus(ForgetPasswordTokenStatus.active);
			}
			else {
				token.setForgetPasswordTokenStatus(ForgetPasswordTokenStatus.inactive);
			}
			
			token = tokenService.updateToken(token);
			printPrettyJson(token);
		}
		
		return token;
	}
	
	@PostMapping("/tokens/changeStatusByPrefix")
	public ForgetPasswordToken changeTokenStatusByPrefix(@RequestBody Map<String, Object> map) {
		String prefix = (String) map.get("prefix");
		Boolean isActive = (Boolean) map.get("status");
		ForgetPasswordToken token = new ForgetPasswordToken();
		
		if (prefix != null && isActive != null) {
			token = tokenService.getTokenFromPrefixString(prefix);
			
			if (isActive) {
				token.setForgetPasswordTokenStatus(ForgetPasswordTokenStatus.active);
			}
			else {
				token.setForgetPasswordTokenStatus(ForgetPasswordTokenStatus.inactive);
			}
			
			token = tokenService.updateToken(token);
			printPrettyJson(token);
		}
		
		return token;
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
