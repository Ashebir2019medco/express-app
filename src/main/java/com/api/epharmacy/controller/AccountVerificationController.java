package com.api.epharmacy.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.AccountVerificationService;
import com.api.epharmacy.ui.model.request.ResetPasswordRequestModel;
import com.api.epharmacy.ui.model.request.SendEmailRequestModel;

@RestController
@RequestMapping("/verify")
public class AccountVerificationController {

	@Autowired
	AccountVerificationService accountVerificationService;
	
	@GetMapping(path="/account/{emailVerificationToken}")
	public String verifyAccount(@PathVariable String emailVerificationToken) {
		String returnValue = accountVerificationService.verifyAccount(emailVerificationToken);
		return returnValue;
		
	}
	
	@PostMapping(path="/resend/{email}")
	public String reSendVerification(@PathVariable String email) throws AddressException, MessagingException, IOException{

		String returnValue = accountVerificationService.reSendVerification(email);
		return returnValue;
	}
	
	@PostMapping(path="/sendpasswordresetcode/{email}")
	public String resetPassword(@PathVariable String email) throws AddressException, MessagingException, IOException{

		String returnValue = accountVerificationService.sendPasswordResetCode(email);
		return returnValue;
	}
	
	@PostMapping(path="/checkresetcode")
	public String checkResetCode(@RequestBody ResetPasswordRequestModel resetPasswordDetail){

		String returnValue = accountVerificationService.checkResetCode(resetPasswordDetail);
		return returnValue;
	}

}
