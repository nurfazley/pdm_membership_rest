package com.pdm.membership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.ForgetPasswordTokenDAO;
import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;


@Service("forgetPasswordTokenService")
public class ForgetPasswordTokenService {
	
	@Autowired
	private ForgetPasswordTokenDAO tokenDAO;
	
	
	public ForgetPasswordToken saveToken(ForgetPasswordToken token) {
		return tokenDAO.save(token);
	}
	
	public ForgetPasswordToken updateToken(ForgetPasswordToken token) {
		return tokenDAO.save(token);
	}
	
	public ForgetPasswordToken getTokenFromTokenString(String tokenString) {
		return tokenDAO.getTokenFromTokenString(tokenString);
	}
	
	public ForgetPasswordToken getTokenFromPrefixString(String prefix) {
		return tokenDAO.getTokenFromPrefixString(prefix);
	}
	
	public ForgetPasswordToken getTokenFromTokenStringAndStatus(String tokenString, ForgetPasswordTokenStatus tokenStatus) {
		return tokenDAO.getTokenFromTokenStringAndStatus(tokenString, tokenStatus);
	}
}
