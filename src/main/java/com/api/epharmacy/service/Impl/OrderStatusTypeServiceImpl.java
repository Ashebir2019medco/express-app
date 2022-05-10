package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.OrderStatusTypeEntity;
import com.api.epharmacy.io.entity.OrderTypeOrderStatusTypeEntity;
import com.api.epharmacy.io.repositories.OrderStatusTypeRepository;
import com.api.epharmacy.io.repositories.OrderTypeOrderStatusTypeRepository;
import com.api.epharmacy.service.OrderStatusTypeService;
import com.api.epharmacy.ui.model.request.OrderStatusTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderStatusTypeResponseModel;

@Service
public class OrderStatusTypeServiceImpl implements OrderStatusTypeService {
	
	@Autowired
	OrderStatusTypeRepository orderStatusTypeRepository;
	
	@Autowired
	OrderTypeOrderStatusTypeRepository orderTypeOrderStatusTypeRepository;
	
	@Override
	public OrderStatusTypeResponseModel saveOrderStatusType(OrderStatusTypeRequestModel requestDetail) {
		OrderStatusTypeResponseModel returnValue = new OrderStatusTypeResponseModel();
		OrderStatusTypeEntity orderStatusTypeEntity = new OrderStatusTypeEntity();
		
		BeanUtils.copyProperties(requestDetail, orderStatusTypeEntity);
		OrderStatusTypeEntity checkOrderStatusType = orderStatusTypeRepository
		        .findByOrderStatusTypeAndIsDeleted(requestDetail.getOrderStatusType(),
		    false);
		if (checkOrderStatusType != null)
			throw new AppException("Order status type already exists");
		OrderStatusTypeEntity savedDetail = orderStatusTypeRepository.save(orderStatusTypeEntity);
		
		if (requestDetail.getOrderTypeIds() != null) {
			for (Integer orderTypeId : requestDetail.getOrderTypeIds()) {
				
				OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity = new OrderTypeOrderStatusTypeEntity();
				orderTypeOrderStatusTypeEntity.setOrderTypeId(orderTypeId);
				orderTypeOrderStatusTypeEntity.setOrderStatusTypeId(savedDetail.getOrderStatusTypeId());
				orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
				
			}
		}
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public OrderStatusTypeResponseModel getOrderStatusType(Integer orderStatusTypeId) {
		OrderStatusTypeResponseModel returnValue = new OrderStatusTypeResponseModel();;
		OrderStatusTypeEntity orderStatusTypeEntity = orderStatusTypeRepository
		        .findByOrderStatusTypeIdAndIsDeleted(orderStatusTypeId, false);
		if (orderStatusTypeEntity == null)
			throw new AppException("No order status type with this id");
		
		List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities = orderTypeOrderStatusTypeRepository
		        .findByIsDeletedAndOrderStatusTypeId(false, orderStatusTypeId);
		List<Integer> orderTypeIds = new ArrayList<Integer>();
		for (OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity : orderTypeOrderStatusTypeEntities) {
			orderTypeIds.add(orderTypeOrderStatusTypeEntity.getOrderTypeId());
		}
		Integer[] ids = new Integer[orderTypeIds.size()];
		int i = 0;
		for (Integer id : orderTypeIds) {
			ids[i++] = id;
		}
		BeanUtils.copyProperties(orderStatusTypeEntity, returnValue);
		returnValue.setOrderTypeIds(ids);
		return returnValue;
	}
	
	@Override
	public List<OrderStatusTypeResponseModel> getOrderStatusTypes(int page, int limit, String searchKey) {
		
		List<OrderStatusTypeResponseModel> returnValue = new ArrayList<>();
		
		if (page > 0)
			page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("orderStatusTypeId").descending());
		Page<OrderStatusTypeEntity> orderStatusTypePage = null;
		
		if ("".equals(searchKey))
			orderStatusTypePage = orderStatusTypeRepository.findByIsDeleted(false, pageableRequest);
		else
			orderStatusTypePage = orderStatusTypeRepository.findByIsDeletedAndOrderStatusTypeContaining(false, searchKey,
			    pageableRequest);
		List<OrderStatusTypeEntity> orderStatusTypeEntities = orderStatusTypePage.getContent();
		
		int totalPages = orderStatusTypePage.getTotalPages();
		for (OrderStatusTypeEntity orderStatusTypeEntity : orderStatusTypeEntities) {
			
			OrderStatusTypeResponseModel orderStatusTypeResponseModel = new OrderStatusTypeResponseModel();
			BeanUtils.copyProperties(orderStatusTypeEntity, orderStatusTypeResponseModel);
			if (returnValue.size() == 0) {
				orderStatusTypeResponseModel.setTotalPage(totalPages);
			}
			
			returnValue.add(orderStatusTypeResponseModel);
		}
		return returnValue;
	}
	
	@Override
	public OrderStatusTypeResponseModel updateOrderStatusType(Integer orderStatusTypeId,
	        OrderStatusTypeRequestModel requestDetail) {
		OrderStatusTypeResponseModel returnValue = new OrderStatusTypeResponseModel();
		OrderStatusTypeEntity orderStatusTypeEntity = orderStatusTypeRepository
		        .findByOrderStatusTypeIdAndIsDeleted(orderStatusTypeId, false);
		
		if (orderStatusTypeEntity == null)
			throw new AppException("No order status type with this id");
		
		BeanUtils.copyProperties(requestDetail, orderStatusTypeEntity);
		OrderStatusTypeEntity savedDetail = orderStatusTypeRepository.save(orderStatusTypeEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		
		List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities = orderTypeOrderStatusTypeRepository
		        .findByIsDeletedAndOrderStatusTypeId(false, orderStatusTypeId);
		
		if (requestDetail.getOrderTypeIds() != null) {
			for (Integer orderTypeId : requestDetail.getOrderTypeIds()) {
				
				Stream filteredList = orderTypeOrderStatusTypeEntities.stream()
						.filter(item -> item.getOrderTypeId()==orderTypeId);
				if (filteredList.toArray().length != 0)
					continue;
				OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity = new OrderTypeOrderStatusTypeEntity();
				orderTypeOrderStatusTypeEntity.setOrderTypeId(orderTypeId);
				orderTypeOrderStatusTypeEntity.setOrderStatusTypeId(savedDetail.getOrderStatusTypeId());
				orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
				
			}
			
			for (OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity : orderTypeOrderStatusTypeEntities) {
				if (!Arrays.asList(requestDetail.getOrderTypeIds())
				        .contains(orderTypeOrderStatusTypeEntity.getOrderTypeId())) {
					orderTypeOrderStatusTypeEntity.setDeleted(true);
					orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
				}
				
			}
			
		}
		

		return returnValue;
	}
	
	@Override
	public String deleteOrderStatusType(Integer orderStatusTypeId) {
		OrderStatusTypeEntity orderStatusTypeEntity = orderStatusTypeRepository.findByOrderStatusTypeIdAndIsDeleted(
		    orderStatusTypeId,
		    false);
		if (orderStatusTypeEntity == null)
			throw new AppException("No order status type with this id");
		
		orderStatusTypeEntity.setDeleted(true);
		orderStatusTypeRepository.save(orderStatusTypeEntity);
		
		List<OrderTypeOrderStatusTypeEntity> orderTypeOrderStatusTypeEntities = orderTypeOrderStatusTypeRepository
		        .findByOrderStatusTypeIdAndIsDeleted(orderStatusTypeEntity.getOrderStatusTypeId(), false);
		for (OrderTypeOrderStatusTypeEntity orderTypeOrderStatusTypeEntity : orderTypeOrderStatusTypeEntities) {
			orderTypeOrderStatusTypeEntity.setDeleted(true);
			orderTypeOrderStatusTypeRepository.save(orderTypeOrderStatusTypeEntity);
		}
		
		return "Order Status Type Deleted";
	}
	
}
