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

import com.api.epharmacy.service.OrderTypeService;
import com.api.epharmacy.ui.model.request.OrderTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderTypeResponseModel;

@RestController
@RequestMapping("/order-type")
public class OrderTypeController {
	
	@Autowired
	OrderTypeService orderTypeService;
	
	@PostMapping
	public OrderTypeResponseModel saveOrderType(@RequestBody OrderTypeRequestModel requestDetail) {
		OrderTypeResponseModel returnValue = orderTypeService.saveOrderType(requestDetail);
		return returnValue;
		
	}
	
	@GetMapping(path = "/{orderTypeId}")
	public OrderTypeResponseModel getOrderType(@PathVariable Integer orderTypeId) {
		
		OrderTypeResponseModel returnValue = orderTypeService.getOrderType(orderTypeId);
		return returnValue;
	}
	
	@GetMapping
	public List<OrderTypeResponseModel> getOrderTypes(@RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "limit", defaultValue = "25") int limit,
	        @RequestParam(value = "searchKey", defaultValue = "") String searchKey) {
		
		List<OrderTypeResponseModel> returnValue = orderTypeService.getOrderTypes(page, limit, searchKey);
		return returnValue;
	}
	
	@PutMapping(path = "/{orderTypeId}")
	public OrderTypeResponseModel updateOrderType(@PathVariable Integer orderTypeId,
	        @RequestBody OrderTypeRequestModel requestDetail) {
		
		OrderTypeResponseModel returnValue = orderTypeService.updateOrderType(orderTypeId, requestDetail);
		
		return returnValue;
	}
	
	@DeleteMapping(path = "/{orderTypeId}")
	public String deleteOrderType(@PathVariable Integer orderTypeId) {
		
		String returnValue = orderTypeService.deleteOrderType(orderTypeId);
		
		return returnValue;
	}
}
