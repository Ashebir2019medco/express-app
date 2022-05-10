package com.api.epharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.OrderTimeLimitService;
import com.api.epharmacy.ui.model.request.OrderTimeLimitRequestModel;
import com.api.epharmacy.ui.model.response.OrderTimeLimitResponseModel;

@RestController
@RequestMapping("/order-time-limit")
public class OrderTimeLimitController {
	
	@Autowired
	OrderTimeLimitService orderTimeLimitService;
	
	@PostMapping
	public OrderTimeLimitResponseModel saveOrderTimeLimit(@RequestBody OrderTimeLimitRequestModel requestDetail) {
		
		OrderTimeLimitResponseModel returnValue = orderTimeLimitService.saveOrderTimeLimit(requestDetail);
		
		return returnValue;
	}
	
	@GetMapping
	public OrderTimeLimitResponseModel getOrderTimeLimits() {
		
		OrderTimeLimitResponseModel returnValue = orderTimeLimitService.getOrderTimeLimits();
		
		return returnValue;
	}
	
	@GetMapping("/{orderTimeLimitId}")
	public OrderTimeLimitResponseModel getOrderTimeLimit(@PathVariable Integer orderTimeLimitId) {
		
		OrderTimeLimitResponseModel returnValue = orderTimeLimitService.getOrderTimeLimit(orderTimeLimitId);
		
		return returnValue;
	}
	
	@PutMapping("/{orderTimeLimitId}")
	public OrderTimeLimitResponseModel updateOrderTimeLimit(@PathVariable Integer orderTimeLimitId,
	        @RequestBody OrderTimeLimitRequestModel requestDetail) {
		
		OrderTimeLimitResponseModel returnValue = orderTimeLimitService.updateOrderTimeLimit(orderTimeLimitId,
		    requestDetail);
		
		return returnValue;
	}
	
	@DeleteMapping("/{orderTimeLimitId}")
	public String deleteOrderTimeLimit(@PathVariable Integer orderTimeLimitId) {
		
		String returnValue = orderTimeLimitService.deleteOrderTimeLimit(orderTimeLimitId);
		
		return returnValue;
	}
}
