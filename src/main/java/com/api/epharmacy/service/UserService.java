package com.api.epharmacy.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.api.epharmacy.shared.dto.UserDto;
import com.api.epharmacy.ui.model.request.AddRoleRequestModel;
import com.api.epharmacy.ui.model.request.ResetPasswordRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UploadProfileRequestModel;
import com.api.epharmacy.ui.model.request.UserLoginRequestModel;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user) throws AddressException, MessagingException, IOException;
	UserDto getuser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);
	List<UserDto> getUsers(int page, int limit);
	UserDto updateUserStatus(String id, UserDto userDto);
	String checkEmail(String email);
	List<UserDto> searchUsers(SearchRequestModel searchkeyDetail, int page, int limit);
	String uploadProfilePicture(UploadProfileRequestModel requestDetail) throws IOException;
	String resetPassword(ResetPasswordRequestModel resetPasswordDetail);
	String changeAccountPassword(ResetPasswordRequestModel changePassRequest);
	List<UserDto> getCustomers(int page, int limit);
}
