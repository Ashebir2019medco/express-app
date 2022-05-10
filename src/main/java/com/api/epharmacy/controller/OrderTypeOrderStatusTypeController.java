package com.api.epharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.OrderTypeOrderStatusTypeService;
import com.api.epharmacy.ui.model.request.OrderTypeOrderStatusTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderTypeOrderStatusTypeResponseModel;

@RestController
@RequestMapping("/order-type-status-type")
public class OrderTypeOrderStatusTypeController {
	
	@Autowired
	OrderTypeOrderStatusTypeService orderTypeOrderStatusTypeService;
	
	@PostMapping
	public OrderTypeOrderStatusTypeResponseModel saveOrderTypeOrderStatusType(
	        @RequestBody OrderTypeOrderStatusTypeRequestModel requestDetail) {
		
		OrderTypeOrderStatusTypeResponseModel returnValue = orderTypeOrderStatusTypeService
		        .saveOrderTypeOrderStatusType(requestDetail);
		return returnValue;
		
	}
	
	@GetMapping(path = "/{orderTypeOrderStatusTypeId}")
	public OrderTypeOrderStatusTypeResponseModel getOrderTypeOrderStatusType(
	        @PathVariable Integer orderTypeOrderStatusTypeId) {
		
		OrderTypeOrderStatusTypeResponseModel returnValue = orderTypeOrderStatusTypeService
		        .getOrderTypeOrderStatusType(orderTypeOrderStatusTypeId);
		return returnValue;
	}
	
	@GetMapping
	public List<OrderTypeOrderStatusTypeResponseModel> getOrderTypeOrderStatusTypes(
	        @RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "limit", defaultValue = "25") int limit,
	        @RequestParam(value = "orderTypeId", defaultValue = "0") int orderTypeId,
	        @RequestParam(value = "orderStatusTypeId", defaultValue = "0") int orderStatusTypeId,
	        @RequestParam(value = "searchKey", defaultValue = "") String searchKey) {
		
		List<OrderTypeOrderStatusTypeResponseModel> returnValue = orderTypeOrderStatusTypeService
		        .getOrderTypeOrderStatusTypes(page, limit, orderTypeId, orderStatusTypeId, searchKey);
		return returnValue;
	}
	
	@PutMapping(path = "/{orderTypeOrderStatusTypeId}")
	public OrderTypeOrderStatusTypeResponseModel updateOrderTypeOrderStatusType(
	        @PathVariable Integer orderTypeOrderStatusTypeId,
	        @RequestBody OrderTypeOrderStatusTypeRequestModel requestDetail) {
		
		OrderTypeOrderStatusTypeResponseModel returnValue = orderTypeOrderStatusTypeService
		        .updateOrderTypeOrderStatusType(orderTypeOrderStatusTypeId, requestDetail);
		
		return returnValue;
	}
	
	@DeleteMapping(path = "/{orderTypeOrderStatusTypeId}")
	public String deleteOrderTypeOrderStatusType(@PathVariable Integer orderTypeOrderStatusTypeId) {
		
		String returnValue = orderTypeOrderStatusTypeService.deleteOrderTypeOrderStatusType(orderTypeOrderStatusTypeId);
		
		return returnValue;
	}
	
}
