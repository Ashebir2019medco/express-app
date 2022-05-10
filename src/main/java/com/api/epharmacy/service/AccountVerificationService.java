package com.api.epharmacy.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.api.epharmacy.ui.model.request.ResetPasswordRequestModel;
import com.api.epharmacy.ui.model.request.SendEmailRequestModel;

public interface AccountVerificationService {

	String sendPasswordResetCode(String email) throws AddressException, MessagingException, IOException;

	String verifyAccount(String emailVerificationToken);

	String checkResetCode(ResetPasswordRequestModel resetPasswordDetail);

	String reSendVerification(String email) throws AddressException, MessagingException, IOException;

}
