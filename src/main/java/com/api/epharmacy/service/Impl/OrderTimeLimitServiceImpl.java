package com.api.epharmacy.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.OrderTimeLimitEntity;
import com.api.epharmacy.io.repositories.OrderTimeLimitRepository;
import com.api.epharmacy.service.OrderTimeLimitService;
import com.api.epharmacy.ui.model.request.OrderTimeLimitRequestModel;
import com.api.epharmacy.ui.model.response.OrderTimeLimitResponseModel;

@Service
public class OrderTimeLimitServiceImpl implements OrderTimeLimitService {
	
	@Autowired
	OrderTimeLimitRepository orderTimeLimitRepository;
	
	@Override
	public OrderTimeLimitResponseModel saveOrderTimeLimit(OrderTimeLimitRequestModel requestDetail) {
		
		OrderTimeLimitResponseModel returnValue = new OrderTimeLimitResponseModel();
		OrderTimeLimitEntity orderTimeLimitEntity = orderTimeLimitRepository
		        .findTopByIsDeletedOrderByOrderTimeLimitIdDesc(false);
		OrderTimeLimitEntity savedDetail;
		
		if (orderTimeLimitEntity != null) {
			System.out.print("Enteredddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
			BeanUtils.copyProperties(requestDetail, orderTimeLimitEntity);
			savedDetail = orderTimeLimitRepository.save(orderTimeLimitEntity);
		}
		
		else {
			OrderTimeLimitEntity orderTimeLimitEntity1 = new OrderTimeLimitEntity();
			BeanUtils.copyProperties(requestDetail, orderTimeLimitEntity1);
			savedDetail = orderTimeLimitRepository.save(orderTimeLimitEntity1);
		}
		
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public OrderTimeLimitResponseModel getOrderTimeLimits() {
		OrderTimeLimitResponseModel returnValue = new OrderTimeLimitResponseModel();
		OrderTimeLimitEntity orderTimeLimitEntity = orderTimeLimitRepository
		        .findTopByIsDeletedOrderByOrderTimeLimitIdDesc(false);
		
		if(orderTimeLimitEntity==null)
			throw new AppException("Order time limit is not set");
		
		BeanUtils.copyProperties(orderTimeLimitEntity, returnValue);
		return returnValue;
	}
	
	@Override
	public OrderTimeLimitResponseModel updateOrderTimeLimit(Integer orderTimeLimitId,
	        OrderTimeLimitRequestModel requestDetail) {
		
		OrderTimeLimitResponseModel returnValue = new OrderTimeLimitResponseModel();
		OrderTimeLimitEntity orderTimeLimitEntity = orderTimeLimitRepository
		        .findByOrderTimeLimitIdAndIsDeleted(orderTimeLimitId, false);
		
		if (orderTimeLimitEntity == null)
			throw new AppException("No record with this id!");
		BeanUtils.copyProperties(requestDetail, orderTimeLimitEntity);
		
		OrderTimeLimitEntity savedDetail = orderTimeLimitRepository.save(orderTimeLimitEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		
		return returnValue;
	}
	
	@Override
	public String deleteOrderTimeLimit(Integer orderTimeLimitId) {
		
		String returnValue = "Deleted Successfully";
		OrderTimeLimitEntity orderTimeLimitEntity = orderTimeLimitRepository
		        .findByOrderTimeLimitIdAndIsDeleted(orderTimeLimitId, false);
		
		if (orderTimeLimitEntity == null)
			throw new AppException("No record with this id!");
		
		orderTimeLimitEntity.setDeleted(true);
		OrderTimeLimitEntity savedDetail = orderTimeLimitRepository.save(orderTimeLimitEntity);
		if (savedDetail == null)
			returnValue = "Some thing goes wrong!";
		
		return returnValue;
	}
	
	@Override
	public OrderTimeLimitResponseModel getOrderTimeLimit(Integer orderTimeLimitId) {
		
		OrderTimeLimitResponseModel returnValue = new OrderTimeLimitResponseModel();
		OrderTimeLimitEntity orderTimeLimitEntity = orderTimeLimitRepository
		        .findByOrderTimeLimitIdAndIsDeleted(orderTimeLimitId, false);
		
		if (orderTimeLimitEntity == null)
			throw new AppException("No record with this id!");
		
		BeanUtils.copyProperties(orderTimeLimitEntity, returnValue);
		
		return returnValue;
	}
	
}
