package com.pdm.membership.security;

import java.util.UUID;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;


@Component
public class MhPasswordEncoder {
	
	private static final String KEY = "MUIP Holdings";
	
	private static TextEncryptor encryptor;
	
	
	public boolean isPasswordMatches(String plainText, String encryptedText, String salt) {
		String plaintextTemp = decrypt(encryptedText, salt);
		return plainText.equals(plaintextTemp);
	}
	
	public static String encrypt(String plainText, String salt) {
		encryptor = Encryptors.delux(KEY, salt);
		return encryptor.encrypt(plainText);
	}
	
	public static String decrypt(String encryptedText, String salt) {
		encryptor = Encryptors.delux(KEY, salt);
		return encryptor.decrypt(encryptedText);
	}
	
	
	public static void main(String[] args) {
		final String salt = UUID.randomUUID().toString().replace("-", "");
        final String passwordToBeEncrypted = "myPassword321";
        final String encrypted = encrypt(passwordToBeEncrypted, salt);
        final String plainText = decrypt(encrypted, salt);
        
        System.out.println("Encrypted: " + encrypted + ", \nDecrypted: " + plainText + ", \nSalt: " + salt);
	}
}
