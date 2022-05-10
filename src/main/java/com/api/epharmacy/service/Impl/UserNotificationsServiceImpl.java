package com.api.epharmacy.service.Impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.UserEntity;
import com.api.epharmacy.io.entity.UserNotificationsEntity;
import com.api.epharmacy.io.repositories.UserNotificationsRepository;
import com.api.epharmacy.security.JwtTokenProvider;
import com.api.epharmacy.service.UserNotificationsService;
import com.api.epharmacy.ui.model.request.UserNotificationsRequestModel;
import com.api.epharmacy.ui.model.response.UserNotificationsResponseModel;

@Service
public class UserNotificationsServiceImpl implements UserNotificationsService {
	
	@Autowired
	UserNotificationsRepository userNotificationsRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

	@Override
	public UserNotificationsResponseModel setSeenUserNotification(long userNotificationsId) {
		UserNotificationsResponseModel returnValue=new UserNotificationsResponseModel();
		UserNotificationsEntity userNotificationsEntity=userNotificationsRepository.findByUserNotificationsId(userNotificationsId);
		if(userNotificationsEntity!=null) {
			userNotificationsEntity.setSeen(true);
			userNotificationsRepository.save(userNotificationsEntity);
			BeanUtils.copyProperties(userNotificationsEntity, returnValue);
		}
		return returnValue;
	}

	@Override
	public UserNotificationsResponseModel getUserNotification(long userNotificationsId) {
		UserNotificationsResponseModel returnValue=new UserNotificationsResponseModel();
		UserNotificationsEntity userNotificationsEntity=userNotificationsRepository.findByUserNotificationsId(userNotificationsId);
		if(userNotificationsEntity!=null)
			BeanUtils.copyProperties(userNotificationsEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<UserNotificationsResponseModel> getUserNotificationsBySender(String senderId, int page, int limit) {
		List<UserNotificationsResponseModel> returnValue=new ArrayList();
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("userNotificationsId").descending());

		List<UserNotificationsEntity> userNotificationsEntities=userNotificationsRepository.findBySenderId(senderId, pageableRequest);
		for(UserNotificationsEntity userNotificationsEntity: userNotificationsEntities) {
			UserNotificationsResponseModel userNotificationsResponseModel= new UserNotificationsResponseModel();
			BeanUtils.copyProperties(userNotificationsEntity, userNotificationsResponseModel);
			returnValue.add(userNotificationsResponseModel);
		}
		return returnValue;
	}

	@Override
	public List<UserNotificationsResponseModel> getUserNotificationsByReceiver(String receiverId, int page, int limit, HttpServletRequest request) {
		List<UserNotificationsResponseModel> returnValue=new ArrayList();
		if(page > 0) 
			page= page - 1;
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("userNotificationsId").descending());

		UserEntity userEntity = tokenProvider.getUserByToken(request);
		List<UserNotificationsEntity> userNotificationsEntities;
		if ("Finance".equalsIgnoreCase(userEntity.getUserType()))
			userNotificationsEntities = userNotificationsRepository.findByReceiverIdOrReceiverIsRoleAndReceiverId(receiverId,
			    true, "Finance", pageableRequest);
		else
	    	userNotificationsEntities=userNotificationsRepository.findByReceiverId(receiverId, pageableRequest);
		for(UserNotificationsEntity userNotificationsEntity: userNotificationsEntities) {
			UserNotificationsResponseModel userNotificationsResponseModel= new UserNotificationsResponseModel();
			BeanUtils.copyProperties(userNotificationsEntity, userNotificationsResponseModel);
			returnValue.add(userNotificationsResponseModel);
		}
		return returnValue;
	}

	@Override
	public List<UserNotificationsResponseModel> getUserNotificationsBySenderAndReceiver(String senderId,
			String receiverId, int page, int limit) {
		List<UserNotificationsResponseModel> returnValue=new ArrayList();
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("userNotificationsId").descending());

		List<UserNotificationsEntity> userNotificationsEntities=userNotificationsRepository.findBySenderIdAndReceiverId(senderId, receiverId, pageableRequest);
		for(UserNotificationsEntity userNotificationsEntity: userNotificationsEntities) {
			UserNotificationsResponseModel userNotificationsResponseModel= new UserNotificationsResponseModel();
			BeanUtils.copyProperties(userNotificationsEntity, userNotificationsResponseModel);
			returnValue.add(userNotificationsResponseModel);
		}
		return returnValue;
	}

	@Override
	public UserNotificationsResponseModel saveUserNotification(
			UserNotificationsRequestModel userNotificationsRequestModel) {
		UserNotificationsResponseModel returnValue=new UserNotificationsResponseModel();
		UserNotificationsEntity userNotificationsEntity=new UserNotificationsEntity();
		BeanUtils.copyProperties(userNotificationsRequestModel, userNotificationsEntity);
		userNotificationsEntity.setSentDateTime(Instant.now());
		UserNotificationsEntity saveUserNotificationsEntity = userNotificationsRepository.save(userNotificationsEntity);
		BeanUtils.copyProperties(saveUserNotificationsEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public UserNotificationsResponseModel updateUserNotification(long userNotificationsId,
			UserNotificationsRequestModel userNotificationsRequestModel) {

		UserNotificationsResponseModel returnValue=new UserNotificationsResponseModel();
		UserNotificationsEntity userNotificationsEntity=userNotificationsRepository.findByUserNotificationsId(userNotificationsId);
		if(userNotificationsEntity==null)
			throw new AppException("Requested notification not found");
		BeanUtils.copyProperties(userNotificationsRequestModel, userNotificationsEntity);
		UserNotificationsEntity saveUserNotificationsEntity = userNotificationsRepository.save(userNotificationsEntity);
		BeanUtils.copyProperties(saveUserNotificationsEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public String deleteUserNotification(long userNotificationsId) {
		UserNotificationsEntity userNotificationsEntity=userNotificationsRepository.findByUserNotificationsId(userNotificationsId);
		if(userNotificationsEntity==null)
			throw new AppException("Requested notification not found");
		
		userNotificationsRepository.delete(userNotificationsEntity);
		
		return "Deleted";
	}
	
}
