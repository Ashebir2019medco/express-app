package com.api.epharmacy.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateRandomString {
	
	private final Random RANDOM = new SecureRandom();
	private final String AllLettersAndNumbers = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrsuvwxyz";
	private final String CapitalLettersAndNumbers = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";

	
	public String generateUserId(int length) {
		return generateRandomUserIdString(length);
	}
	
	public String generateAccountId(int length) {
		return generateAccountIdRandomString(length);
	}
	
	public String generateOrderNumber(int length) {
		
		return generateOrderNumberRandomString(length);
	}

	private String generateOrderNumberRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(CapitalLettersAndNumbers.charAt(RANDOM.nextInt(CapitalLettersAndNumbers.length())));
		}
		return new String(returnValue);
	}

	private String generateRandomUserIdString(int length) {

		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(AllLettersAndNumbers.charAt(RANDOM.nextInt(AllLettersAndNumbers.length())));
		}
		return new String(returnValue);
	}
	
	private String generateAccountIdRandomString(int length) {

		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(CapitalLettersAndNumbers.charAt(RANDOM.nextInt(CapitalLettersAndNumbers.length())));
		}
		return new String(returnValue);
	}

	
}
