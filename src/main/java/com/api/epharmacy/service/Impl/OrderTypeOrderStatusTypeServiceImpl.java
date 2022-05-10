package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.OrderStatusTypeEntity;
import com.api.epharmacy.io.entity.OrderTypeEntity;
import com.api.epharmacy.io.entity.OrderTypeOrderStatusTypeEntity;
import com.api.epharmacy.io.repositories.OrderStatusTypeRepository;
import com.api.epharmacy.io.repositories.OrderTypeOrderStatusTypeRepository;
import com.api.epharmacy.io.repositories.OrderTypeRepository;
import com.api.epharmacy.service.OrderTypeOrderStatusTypeService;
import com.api.epharmacy.ui.model.request.OrderTypeOrderStatusTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderTypeOrderStatusTypeResponseModel;

@Service
public class OrderTypeOrderStatusTypeServiceImpl implements OrderTypeOrderStatusTypeService {
	
	@Autowired
	OrderTypeOrderStatusTypeRepository orderTypeOrderStatusTypeRepository;
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	OrderStatusTypeRepository orderStatusTypeRepository;
	
	@Override
	public OrderTypeOrderStatusTypeResponseModel saveOrderTypeOrderStatusType(
	        OrderTypeOrderStatusTypeRequestModel requestDetail) {
		OrderTypeOrderStatusTypeResponseModel returnValue = new OrderTypeOrderStatusTypeResponseModel();
		OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity = new OrderTypeOrderStatusTypeEntity();
		
		BeanUtils.copyProperties(requestDetail, orderTypeOrderStatusTypeEntity);
		OrderTypeOrderStatusTypeEntity checkOrderTypeOrderStatusTypeEntity = orderTypeOrderStatusTypeRepository
		        .findByOrderTypeIdAndOrderStatusTypeIdAndIsDeleted(requestDetail.getOrderTypeId(),
		            requestDetail.getOrderStatusTypeId(), false);
		if (checkOrderTypeOrderStatusTypeEntity != null)
			throw new AppException("Record already exists");
		
		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(requestDetail.getOrderTypeId(),
		    false);
		if (orderTypeEntity == null)
			throw new AppException("No order type with this id");
		
		OrderStatusTypeEntity orderStatusTypeEntity = orderStatusTypeRepository
		        .findByOrderStatusTypeIdAndIsDeleted(requestDetail.getOrderStatusTypeId(), false);
		
		if (orderStatusTypeEntity == null)
			throw new AppException("No order status type with this id");
		
		OrderTypeOrderStatusTypeEntity savedDetail = orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
		
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public OrderTypeOrderStatusTypeResponseModel getOrderTypeOrderStatusType(Integer orderTypeOrderStatusTypeId) {
		OrderTypeOrderStatusTypeResponseModel returnValue = new OrderTypeOrderStatusTypeResponseModel();
		OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity = orderTypeOrderStatusTypeRepository
		        .findByOrderTypeOrderStatusTypeIdAndIsDeleted(orderTypeOrderStatusTypeId, false);
		if (orderTypeOrderStatusTypeEntity == null)
			throw new AppException("No Record with this id");
		BeanUtils.copyProperties(orderTypeOrderStatusTypeEntity, returnValue);
		
		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderTypeOrderStatusTypeEntity.getOrderTypeId(), false);
		if (orderTypeEntity != null)
			returnValue.setOrderType(orderTypeEntity.getOrderType());
		
		OrderStatusTypeEntity orderStatusTypeEntity = orderStatusTypeRepository
		        .findByOrderStatusTypeIdAndIsDeleted(orderTypeOrderStatusTypeEntity.getOrderStatusTypeId(), false);
		if (orderStatusTypeEntity != null)
			returnValue.setOrderStatusType(orderStatusTypeEntity.getOrderStatusType());
		
		return returnValue;
	}
	
	@Override
	public List<OrderTypeOrderStatusTypeResponseModel> getOrderTypeOrderStatusTypes(int page, int limit, int orderTypeId,
	        int orderStatusTypeId, String searchKey) {
		
		List<OrderTypeOrderStatusTypeResponseModel> returnValue = new ArrayList<>();
		
		if (page > 0)
			page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("orderTypeOrderStatusTypeId").descending());
		Page<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypePage = null;
		
		if (orderTypeId == 0 && orderStatusTypeId == 0)
			orderTypeOrderStatusTypePage = orderTypeOrderStatusTypeRepository.findByIsDeleted(false, pageableRequest);
		else if (orderTypeId != 0 && orderStatusTypeId == 0)
			orderTypeOrderStatusTypePage = orderTypeOrderStatusTypeRepository.findByOrderTypeIdAndIsDeleted(orderTypeId,
			    false,
			    pageableRequest);
		else if (orderTypeId == 0 && orderStatusTypeId != 0)
			orderTypeOrderStatusTypePage = orderTypeOrderStatusTypeRepository
			        .findByOrderStatusTypeIdAndIsDeleted(orderStatusTypeId, false, pageableRequest);
		else
			orderTypeOrderStatusTypePage = orderTypeOrderStatusTypeRepository
			        .findByOrderStatusTypeIdAndOrderTypeIdAndIsDeleted(orderStatusTypeId, orderTypeId, false,
			            pageableRequest);
		
		List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities = orderTypeOrderStatusTypePage.getContent();
		
		int totalPages = orderTypeOrderStatusTypePage.getTotalPages();
		for (OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity : orderTypeOrderStatusTypeEntities) {
			
			OrderTypeOrderStatusTypeResponseModel orderTypeOrderStatusTypeResponseModel = new OrderTypeOrderStatusTypeResponseModel();
			BeanUtils.copyProperties(orderTypeOrderStatusTypeEntity, orderTypeOrderStatusTypeResponseModel);
			if (returnValue.size() == 0) {
				orderTypeOrderStatusTypeResponseModel.setTotalPage(totalPages);
			}
			
			OrderTypeEntity orderTypeEntity = orderTypeRepository
			        .findByOrderTypeIdAndIsDeleted(orderTypeOrderStatusTypeEntity.getOrderTypeId(), false);
			if (orderTypeEntity != null)
				orderTypeOrderStatusTypeResponseModel.setOrderType(orderTypeEntity.getOrderType());
			
			OrderStatusTypeEntity orderStatusTypeEntity = orderStatusTypeRepository
			        .findByOrderStatusTypeIdAndIsDeleted(orderTypeOrderStatusTypeEntity.getOrderStatusTypeId(), false);
			if (orderStatusTypeEntity != null)
				orderTypeOrderStatusTypeResponseModel.setOrderStatusType(orderStatusTypeEntity.getOrderStatusType());
			
			returnValue.add(orderTypeOrderStatusTypeResponseModel);
		}
		return returnValue;
	}
	
	@Override
	public OrderTypeOrderStatusTypeResponseModel updateOrderTypeOrderStatusType(Integer orderTypeOrderStatusTypeId,
	        OrderTypeOrderStatusTypeRequestModel requestDetail) {
		OrderTypeOrderStatusTypeResponseModel returnValue = new OrderTypeOrderStatusTypeResponseModel();
		OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity = orderTypeOrderStatusTypeRepository
		        .findByOrderTypeOrderStatusTypeIdAndIsDeleted(orderTypeOrderStatusTypeId, false);
		
		if (orderTypeOrderStatusTypeEntity == null)
			throw new AppException("No record with this id");
		
		BeanUtils.copyProperties(requestDetail, orderTypeOrderStatusTypeEntity);
		OrderTypeOrderStatusTypeEntity savedDetail = orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		
		return returnValue;
	}
	
	@Override
	public String deleteOrderTypeOrderStatusType(Integer orderTypeOrderStatusTypeId) {
		
		OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity = orderTypeOrderStatusTypeRepository
		        .findByOrderTypeOrderStatusTypeIdAndIsDeleted(orderTypeOrderStatusTypeId, false);
		
		if (orderTypeOrderStatusTypeEntity == null)
			throw new RuntimeException("No record with this id");
		
		orderTypeOrderStatusTypeEntity.setDeleted(true);
		orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
		
		return "Record Deleted!";
	}
	
	@Override
	public OrderTypeOrderStatusTypeResponseModel filterOrderStatusType(Integer orderTypId) {
		return null;
	}
	

}
