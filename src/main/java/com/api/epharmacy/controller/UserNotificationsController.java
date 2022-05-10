package com.api.epharmacy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.UserNotificationsService;
import com.api.epharmacy.ui.model.request.UserNotificationsRequestModel;
import com.api.epharmacy.ui.model.response.UserNotificationsResponseModel;

@RestController
@RequestMapping("/user-notifications")
public class UserNotificationsController {
	
	@Autowired
	UserNotificationsService userNotificationsService;

	@GetMapping(path="/seen/{userNotificationsId}")
	public UserNotificationsResponseModel setSeenUserNotification(@PathVariable long userNotificationsId) {
		UserNotificationsResponseModel returnValue = userNotificationsService.setSeenUserNotification(userNotificationsId);
		
		return returnValue;
	}

	@GetMapping(path="/{userNotificationId}")
	public UserNotificationsResponseModel getUserNotification(@PathVariable long userNotificationsId) {
		
		UserNotificationsResponseModel returnValue = userNotificationsService.getUserNotification(userNotificationsId);
		
		return returnValue;
	}
	@GetMapping(path="/receiver/{receiverId}")
	public List<UserNotificationsResponseModel> getUserNotificationsByReceiver(@PathVariable String receiverId, @RequestParam(value="page", defaultValue = "1") int page,
	        @RequestParam(value = "limit", defaultValue = "100	") int limit, HttpServletRequest request) {
		
		List<UserNotificationsResponseModel> returnValue = userNotificationsService.getUserNotificationsByReceiver(receiverId, page, limit, request);
		return returnValue;
	}

	@GetMapping(path="/sender/{senderId}")
	public List<UserNotificationsResponseModel> getUserNotificationsBySender(@PathVariable String senderId, @RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "25") int limit) {
		
		List<UserNotificationsResponseModel> returnValue = userNotificationsService.getUserNotificationsBySender(senderId, page, limit);
		
		return returnValue;
	}
	@GetMapping(path="/between-sender-receiver/{senderId}/{receiverId}")
	public List<UserNotificationsResponseModel> getUserNotificationsBySenderAndReceiver(@PathVariable String senderId, @PathVariable String receiverId, @RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "25") int limit) {
		
		List<UserNotificationsResponseModel> returnValue = userNotificationsService.getUserNotificationsBySenderAndReceiver(senderId, receiverId, page, limit);
		
		return returnValue;
	}


	@PostMapping(path="/add-user-notification")
	public UserNotificationsResponseModel saveUserNotification(@RequestBody UserNotificationsRequestModel userNotificationsRequestModel) {
		UserNotificationsResponseModel returnValue = userNotificationsService.saveUserNotification(userNotificationsRequestModel);
		
		return returnValue;
	}

	@PutMapping(path="/{userNotificationId}")
	public UserNotificationsResponseModel updateUserNotification(@PathVariable long userNotificationsId,@RequestBody UserNotificationsRequestModel userNotificationsRequestModel) {
		
		UserNotificationsResponseModel returnValue = userNotificationsService.updateUserNotification(userNotificationsId, userNotificationsRequestModel);
		
		return returnValue;
	}

	@DeleteMapping(path="/{userNotificationId}")
	public String updateUserNotification(@PathVariable long userNotificationsId) {
		
		String returnValue = userNotificationsService.deleteUserNotification(userNotificationsId);
		
		return returnValue;
	}
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public List<UserNotificationsResponseModel> greeting() throws Exception {
		
		Thread.sleep(1000);
		HttpServletRequest request = null;
		List<UserNotificationsResponseModel> returnValue = userNotificationsService
		        .getUserNotificationsByReceiver("dIbalsVT2KH4vKBIxQYDS3G14LECSL", 1, 100, request);
		System.out.print(returnValue);
		
		return returnValue;
	}
}
