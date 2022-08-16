package com.pdm.membership.dao;

import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;

public interface ForgetPasswordTokenDAOCustom {

	public ForgetPasswordToken getTokenFromTokenString(String tokenString);
	
	public ForgetPasswordToken getTokenFromPrefixString(String prefix);
	
	public ForgetPasswordToken getTokenFromTokenStringAndStatus(String tokenString, ForgetPasswordTokenStatus status);
}
