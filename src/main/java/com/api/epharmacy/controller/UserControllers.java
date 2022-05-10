package com.api.epharmacy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.UserService;
import com.api.epharmacy.shared.dto.UserDto;
import com.api.epharmacy.ui.model.request.ResetPasswordRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.UploadProfileRequestModel;
import com.api.epharmacy.ui.model.request.UserDetailRequestModel;
import com.api.epharmacy.ui.model.response.OperationStatusModel;
import com.api.epharmacy.ui.model.response.RequestOperationName;
import com.api.epharmacy.ui.model.response.RequestOperationStatus;
import com.api.epharmacy.ui.model.response.UserRest;

@RestController
@RequestMapping("/accounts") //http://localhost:8080/users
public class UserControllers {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/{id}")
	public UserRest getUser(@PathVariable String id) {
		UserRest returnValue= new UserRest();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}
	
	@GetMapping(path="checkemail/{email}")
	public String checkEmail(@PathVariable String email) {
		String returnValue = userService.checkEmail(email);
		return returnValue;
		
	}
	
	@GetMapping(path="/users")
	public List<UserRest> getUsers(@RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users= userService.getUsers(page,limit);
		
		for(UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
			
		}
		
		return returnValue;
	}
	
	@GetMapping(path="/customers")
	public List<UserRest> getCustomers(@RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users= userService.getCustomers(page,limit);
		
		for(UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
			
		}
		
		return returnValue;
	}
	
	@PostMapping(path="/search")
	public List<UserRest> searchUsers(@RequestBody SearchRequestModel searchkeyDetail, @RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users= userService.searchUsers(searchkeyDetail,page,limit);
		
		for(UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
			
		}
		
		return returnValue;
	}
	
	@PostMapping(path="/uploadprofile")
	public String uploadProfilePicture(@ModelAttribute UploadProfileRequestModel requestDetail) throws IOException{
		
		String returnValue = userService.uploadProfilePicture(requestDetail);
		
		return returnValue;
	}
	
	
	@PutMapping(path="/{id}")
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailRequestModel userDetails) {
		
		UserRest returnValue= new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="changestatus/{id}")
	public UserRest updateUserStatus(@PathVariable String id, @RequestBody UserDetailRequestModel userDetails) {
		
		UserRest returnValue= new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.updateUserStatus(id, userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		 
		return returnValue;
	}
	
	@PutMapping(path="/resetpassword")
	public String resetPassword(@RequestBody ResetPasswordRequestModel resetPasswordDetail){

		String returnValue = userService.resetPassword(resetPasswordDetail);
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOpreationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		
		returnValue.setOpreationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	
	
}
