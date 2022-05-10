package com.api.epharmacy.service;

import com.api.epharmacy.ui.model.request.OrderTimeLimitRequestModel;
import com.api.epharmacy.ui.model.response.OrderTimeLimitResponseModel;

public interface OrderTimeLimitService {
	
	OrderTimeLimitResponseModel saveOrderTimeLimit(OrderTimeLimitRequestModel requestDetail);
	
	OrderTimeLimitResponseModel getOrderTimeLimits();
	
	OrderTimeLimitResponseModel updateOrderTimeLimit(Integer orderTimeLimitId, OrderTimeLimitRequestModel requestDetail);
	
	String deleteOrderTimeLimit(Integer orderTimeLimitId);
	
	OrderTimeLimitResponseModel getOrderTimeLimit(Integer orderTimeLimitId);
	
}
