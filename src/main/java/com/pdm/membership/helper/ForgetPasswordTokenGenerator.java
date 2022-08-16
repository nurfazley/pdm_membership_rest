package com.pdm.membership.helper;

import java.util.Date;
import java.util.Random;

import com.pdm.membership.model.ForgetPasswordToken;
import com.pdm.membership.model.lookup.ForgetPasswordTokenStatus;


public final class ForgetPasswordTokenGenerator {
	
	final static String APPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	
	public static ForgetPasswordToken generateToken() {
		String alp = new String();
		String num = new String();
		
		for (int i = 0; i < 4; i++) {
			alp += getChar();
		}
		num = getNum();
		
		ForgetPasswordToken token = new ForgetPasswordToken();
		token.setCreatedDateTime(new Date());
		token.setTokenString(alp + num);
		token.setForgetPasswordTokenStatus(ForgetPasswordTokenStatus.active);
		
		return token;
	}
	
	private static char getChar() {
		Random rd = new Random();
		char letter = APPHABETS.charAt(rd.nextInt(APPHABETS.length()));
		
		return letter;
	}
	
	private static String getNum() {
		Random rd = new Random();
		return String.format("%04d", rd.nextInt(10000));
	}
}
