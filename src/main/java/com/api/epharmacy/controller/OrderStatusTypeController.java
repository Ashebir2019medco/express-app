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

import com.api.epharmacy.service.OrderStatusTypeService;
import com.api.epharmacy.ui.model.request.OrderStatusTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderStatusTypeResponseModel;

@RestController
@RequestMapping("/order-status-type")
public class OrderStatusTypeController {
	
	@Autowired
	OrderStatusTypeService orderStatusTypeService;
	
	@PostMapping
	public OrderStatusTypeResponseModel saveOrderStatusType(@RequestBody OrderStatusTypeRequestModel requestDetail) {
		OrderStatusTypeResponseModel returnValue = orderStatusTypeService.saveOrderStatusType(requestDetail);
		return returnValue;
		
	}
	
	@GetMapping(path = "/{orderStatusTypeId}")
	public OrderStatusTypeResponseModel getOrderStatusType(@PathVariable Integer orderStatusTypeId) {
		
		OrderStatusTypeResponseModel returnValue = orderStatusTypeService.getOrderStatusType(orderStatusTypeId);
		return returnValue;
	}
	
	@GetMapping
	public List<OrderStatusTypeResponseModel> getOrderStatusTypes(@RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "limit", defaultValue = "25") int limit,
	        @RequestParam(value = "searchKey", defaultValue = "") String searchKey) {
		
		List<OrderStatusTypeResponseModel> returnValue = orderStatusTypeService.getOrderStatusTypes(page, limit, searchKey);
		return returnValue;
	}
	
	@PutMapping(path = "/{orderStatusTypeId}")
	public OrderStatusTypeResponseModel updateOrderStatusType(@PathVariable Integer orderStatusTypeId,
	        @RequestBody OrderStatusTypeRequestModel requestDetail) {
		
		OrderStatusTypeResponseModel returnValue = orderStatusTypeService.updateOrderStatusType(orderStatusTypeId,
		    requestDetail);
		
		return returnValue;
	}
	
	@DeleteMapping(path = "/{orderStatusTypeId}")
	public String deleteOrderStatusType(@PathVariable Integer orderStatusTypeId) {
		
		String returnValue = orderStatusTypeService.deleteOrderStatusType(orderStatusTypeId);
		
		return returnValue;
	}
}
