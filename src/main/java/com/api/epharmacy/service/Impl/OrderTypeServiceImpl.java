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
import com.api.epharmacy.io.entity.OrderTypeEntity;
import com.api.epharmacy.io.entity.OrderTypeOrderStatusTypeEntity;
import com.api.epharmacy.io.repositories.OrderTypeOrderStatusTypeRepository;
import com.api.epharmacy.io.repositories.OrderTypeRepository;
import com.api.epharmacy.service.OrderTypeService;
import com.api.epharmacy.ui.model.request.OrderTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderTypeResponseModel;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	OrderTypeOrderStatusTypeRepository orderTypeOrderStatusTypeRepository;
	
	@Override
	public OrderTypeResponseModel saveOrderType(OrderTypeRequestModel requestDetail) {
		OrderTypeResponseModel returnValue = new OrderTypeResponseModel();
		OrderTypeEntity orderTypeEntity = new OrderTypeEntity();
		
		BeanUtils.copyProperties(requestDetail, orderTypeEntity);
		OrderTypeEntity checkOrderType = orderTypeRepository.findByOrderTypeAndIsDeleted(requestDetail.getOrderType(),
		    false);
		if (checkOrderType != null)
			throw new AppException("Order type already exists");
		OrderTypeEntity savedDetail = orderTypeRepository.save(orderTypeEntity);
		
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public OrderTypeResponseModel getOrderType(Integer orderTypeId) {
		OrderTypeResponseModel returnValue = new OrderTypeResponseModel();
		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderTypeId, false);
		if (orderTypeEntity == null)
			throw new AppException("No order type with this id");
		BeanUtils.copyProperties(orderTypeEntity, returnValue);
		return returnValue;
	}
	
	@Override
	public List<OrderTypeResponseModel> getOrderTypes(int page, int limit, String searchKey) {
		
		List<OrderTypeResponseModel> returnValue = new ArrayList<>();
		
		if (page > 0)
			page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("orderTypeId").descending());
		Page<OrderTypeEntity> orderTypePage = null;
		
		if ("".equals(searchKey))
			orderTypePage = orderTypeRepository.findByIsDeleted(false, pageableRequest);
		else
			orderTypePage = orderTypeRepository.findByIsDeletedAndOrderTypeContaining(false, searchKey, pageableRequest);
		List<OrderTypeEntity> orderTypeEntities = orderTypePage.getContent();
		
		int totalPages = orderTypePage.getTotalPages();
		for (OrderTypeEntity orderTypeEntity : orderTypeEntities) {
			
			OrderTypeResponseModel orderTypeResponseModel = new OrderTypeResponseModel();
			BeanUtils.copyProperties(orderTypeEntity, orderTypeResponseModel);
			if (returnValue.size() == 0) {
				orderTypeResponseModel.setTotalPage(totalPages);
			}
			
			returnValue.add(orderTypeResponseModel);
		}
		return returnValue;
	}
	
	@Override
	public OrderTypeResponseModel updateOrderType(Integer orderTypeId, OrderTypeRequestModel requestDetail) {
		OrderTypeResponseModel returnValue = new OrderTypeResponseModel();
		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderTypeId, false);
		
		if (orderTypeEntity == null)
			throw new AppException("No order type with this id");
		
		BeanUtils.copyProperties(requestDetail, orderTypeEntity);
		OrderTypeEntity savedDetail = orderTypeRepository.save(orderTypeEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		
		return returnValue;
	}
	
	@Override
	public String deleteOrderType(Integer orderTypeId) {
		
		OrderTypeEntity orderTypeEntity = orderTypeRepository.findByOrderTypeIdAndIsDeleted(orderTypeId, false);
		if (orderTypeEntity == null)
			throw new RuntimeException("No order type with this id");
		
		orderTypeEntity.setDeleted(true);
		orderTypeRepository.save(orderTypeEntity);
		
		List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities = orderTypeOrderStatusTypeRepository
		        .findByIsDeletedAndOrderTypeId(false, orderTypeEntity.getOrderTypeId());
		for (OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity : orderTypeOrderStatusTypeEntities) {
			orderTypeOrderStatusTypeEntity.setDeleted(true);
			orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
		}
		
		
		return "Order Type Deleted";
	}

	
}
