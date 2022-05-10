package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.OrderTypeRequestModel;
import com.api.epharmacy.ui.model.response.OrderTypeResponseModel;

public interface OrderTypeService {
	
	OrderTypeResponseModel saveOrderType(OrderTypeRequestModel requestDetail);
	

	List<OrderTypeResponseModel> getOrderTypes(int page, int limit, String searchKey);
	
	OrderTypeResponseModel updateOrderType(Integer orderTypeId, OrderTypeRequestModel requestDetail);
	
	String deleteOrderType(Integer orderTypeId);
	
	OrderTypeResponseModel getOrderType(Integer orderTypeId);
	

}
